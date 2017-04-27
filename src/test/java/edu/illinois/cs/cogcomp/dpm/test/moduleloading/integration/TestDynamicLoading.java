package edu.illinois.cs.cogcomp.dpm.test.moduleloading.integration;

import edu.illinois.cs.cogcomp.dpm.test.MockPipelineConfig;
import org.junit.Before;
import org.junit.Test;
import org.sonatype.aether.graph.Dependency;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.File;
import java.util.Collections;
import java.util.List;

import edu.illinois.cs.cogcomp.dpm.config.DefaultGlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.GlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.StandardConfigModule;
import edu.illinois.cs.cogcomp.dpm.module.Loader;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.Downloader;

import static org.junit.Assert.*;

public class TestDynamicLoading {

    private GlobalConfig globalConfig;
    private PipelineConfig pipelineConfig;
    private Loader loader;
    private Downloader downloader;

    @Before
    public void before() {
        ModuleFactory factory = DaggerModuleFactory
            .builder()
            .standardConfigModule(new StandardConfigModule(new DefaultGlobalConfig(), new MockPipelineConfig()))
            .build();
        loader = factory.getLoader();
        downloader = factory.getDownloader();
        globalConfig = factory.getGlobalConfig();
        pipelineConfig = factory.getPipelineConfig();
    }

    @Test
    public void testDynamicLoading() throws Exception {
        List<Dependency> deps = Collections.singletonList(
            new Dependency(new DefaultArtifact("com.rabbitmq", "amqp-client", "jar", "4.1.0"), "compile")
        );
        List<File> resources = downloader.download(deps, globalConfig.getMavenRepoPath());
        loader.setResources(resources);
        Class<?> clazz = loader.load("com.rabbitmq.client.ConnectionFactory");
        assertEquals(clazz.getName(), "com.rabbitmq.client.ConnectionFactory");
    }
}
