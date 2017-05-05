package edu.illinois.cs.cogcomp.dpm.runner;

import edu.illinois.cs.cogcomp.annotation.Annotator;
import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.annotation.AnnotatorServiceConfigurator;
import edu.illinois.cs.cogcomp.annotation.BasicAnnotatorService;
import edu.illinois.cs.cogcomp.annotation.TextAnnotationBuilder;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.utilities.configuration.ResourceManager;
import edu.illinois.cs.cogcomp.dpm.listener.OnDownloaderStatusUpdateListener;
import edu.illinois.cs.cogcomp.dpm.listener.StatusUpdateEvent;
import edu.illinois.cs.cogcomp.dpm.listener.OnApplicationStatusUpdateListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.graph.Dependency;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import edu.illinois.cs.cogcomp.dpm.config.DefaultGlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.DefaultPipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.GlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.StandardConfigModule;
import edu.illinois.cs.cogcomp.dpm.config.bean.ViewBean;
import edu.illinois.cs.cogcomp.dpm.module.Loader;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.Downloader;
import edu.illinois.cs.cogcomp.nlp.tokenizer.StatefulTokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;

public class Application {

    private static final boolean SPLIT_ON_DASH = false;

    private GlobalConfig globalConfig = null;
    private PipelineConfig pipelineConfig = null;
    private Downloader downloader = null;
    private Loader loader = null;

    private OnApplicationStatusUpdateListener listener = null;

    private Logger logger = LogManager.getLogger();

    private class DummyApplicationStatusUpdateListener implements OnApplicationStatusUpdateListener {
        @Override
        public void onUpdate(StatusUpdateEvent event) {
            logger.debug("Run status update");
        }
    }

    private class DefaultDownloaderStatusUpdateListener implements OnDownloaderStatusUpdateListener {
        @Override
        public void onUpdate(StatusUpdateEvent event) {
            listener.onUpdate(event);
        }
    }

    public static Application create(String configFilePath) throws IOException {
        return create(new DefaultGlobalConfig(),
            DefaultPipelineConfig.fromJson(new File(configFilePath)));
    }

    public static Application create(GlobalConfig globalConfig, PipelineConfig pipelineConfig) {
        StandardConfigModule configModule = new StandardConfigModule(
            globalConfig,
            pipelineConfig);
        return DaggerApplicationFactory.builder()
            .standardConfigModule(configModule)
            .build()
            .get();
    }

    @Inject
    public Application(
            GlobalConfig globalConfig,
            PipelineConfig pipelineConfig,
            Downloader downloader,
            Loader loader) {
        this.globalConfig = globalConfig;
        this.pipelineConfig = pipelineConfig;
        this.downloader = downloader;
        this.loader = loader;
        this.listener = new DummyApplicationStatusUpdateListener();

        // Add status listener to downloader
        this.downloader.setOnDownloaderStatusUpdateListener(new DefaultDownloaderStatusUpdateListener());

        init();
    }

    private void init() {
        // Check if Maven repo exists
        File f = new File(globalConfig.getMavenRepoPath());
        if (!f.exists()) {
            // If not then create
            f.mkdir();
        }
    }

    public File runAndSaveFile(String text, String saveFile) throws ApplicationException {
        File file = new File(saveFile);
        runAndSaveFile(text, file);
        return file;
    }

    public void runAndSaveFile(String text, File saveFile) throws ApplicationException {
        String retval = run(text);
        try {
            FileOutputStream stream = new FileOutputStream(saveFile);
            stream.write(retval.getBytes());
        } catch (FileNotFoundException e) {
            throw new ApplicationException("Cannot open file");
        } catch (IOException e) {
            throw new ApplicationException("Cannot write to save file");
        }
    }

    public String run(String text) throws ApplicationException {
        // Extract dependencies from PipelineConfig
        List<ViewBean> views = pipelineConfig.getViews();
        List<Dependency> dependencies = new ArrayList<>();
        for (ViewBean view : views) {
            dependencies.add(toDependency(view));
        }

        // Download from repo
        try {
            downloader.download(dependencies, globalConfig.getMavenRepoPath());
        } catch (Exception e) {
            throw new ApplicationException("Error when downloading dependencies", e);
        }

        /*
         * For each view, load from Maven repo, get method by reflection and execute.
         */
        Map<String, Annotator> annotators = new HashMap<>();

        for (ViewBean view : views) {
            Class<?> clazz = null;
            try {
                clazz = loader.load(view.getEntrypoint());
                Method getAnnotatorMethod = clazz.getMethod("getAnnotator");
                Object instance = clazz.newInstance();
                Annotator annotator = (Annotator) getAnnotatorMethod.invoke(instance);
                annotators.put(annotator.getViewName(), annotator);
            } catch (ClassNotFoundException e) {
                String errorText = "Failed to load class " +
                        view.getEntrypoint() + " for dependency " +
                        view.getGroupId() + "." + view.getArtifactId();
                throw new ApplicationException(errorText, e);
            } catch (IllegalAccessException e) {
                throw new ApplicationException("Should not happen", e);
            } catch (InstantiationException e) {
                throw new ApplicationException("Should not happen", e);
            } catch (NoSuchMethodException e) {
                String errorText = "No getAnnotator() method in " + clazz.getName();
                throw new ApplicationException(errorText, e);
            } catch (InvocationTargetException e) {
                throw new ApplicationException("getAnnotator() throws an exception", e);
            }
        }

        // Now we have a map of annotators. Run AnnotatorService
        TextAnnotationBuilder builder = new TokenizerTextAnnotationBuilder(new StatefulTokenizer(SPLIT_ON_DASH));
        ResourceManager rm = new AnnotatorServiceConfigurator().getDefaultConfig();
        try {
            BasicAnnotatorService bas = new BasicAnnotatorService(builder, annotators, rm);
            TextAnnotation ta = bas.createAnnotatedTextAnnotation("", "", text);
            return ta.toString();
        } catch (AnnotatorException e) {
            throw new ApplicationException("AnnotatorService throws error", e);
        }
    }

    public OnApplicationStatusUpdateListener getOnRunnerStatusUpdateListener() {
        return listener;
    }

    public void setOnRunnerStatusUpdateListener(OnApplicationStatusUpdateListener listener) {
        if (listener == null) {
            listener = new DummyApplicationStatusUpdateListener();
        }
        this.listener = listener;
    }

    private Dependency toDependency(ViewBean view) {
        Artifact artifact = new DefaultArtifact(
                view.getGroupId(),
                view.getArtifactId(),
                "jar",
                view.getVersion());
        return new Dependency(artifact, "compile");
    }
}
