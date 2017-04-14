package edu.illinois.cs.cogcomp.dpm.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.graph.Dependency;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.illinois.cs.cogcomp.dpm.config.DefaultGlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.DefaultPipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.GlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.StandardConfigModule;
import edu.illinois.cs.cogcomp.dpm.config.bean.ViewBean;
import edu.illinois.cs.cogcomp.dpm.module.Loader;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.Downloader;

public class Application {

    private GlobalConfig globalConfig = null;
    private PipelineConfig pipelineConfig = null;
    private Downloader downloader = null;
    private Loader loader = null;

    private Logger logger = LogManager.getLogger();

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

        init();
    }

    private void init() {
        // Check for global correctness
        // 1. Check if Maven repo exists
        File f = new File(globalConfig.getMavenRepoPath());
        if (!f.exists()) {
            // If not then create
            f.mkdir();
        }
    }

    public void run() {
        List<ViewBean> views = pipelineConfig.getViews();
        List<Dependency> dependencies = new ArrayList<>();
        for (ViewBean view : views) {
            dependencies.add(toDependency(view));
        }
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
