package edu.illinois.cs.cogcomp.dpm.test.application;

import edu.illinois.cs.cogcomp.core.io.LineIO;
import edu.illinois.cs.cogcomp.dpm.config.DefaultGlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.DefaultPipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.GlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.runner.Application;
import edu.illinois.cs.cogcomp.dpm.runner.ApplicationException;
import edu.illinois.cs.cogcomp.dpm.test.TestFramework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestApplication extends TestFramework {

    Application app;
    GlobalConfig globalConfig;
    PipelineConfig pipelineConfig;

    @Before
    public void setUp() throws IOException {
        globalConfig = new DefaultGlobalConfig();
        pipelineConfig = DefaultPipelineConfig.fromJson("src/test/resources/test-application-config.json");
        app = new Application(globalConfig, pipelineConfig, new MockDownloader(), new MockModuleLoader());
    }

    @After
    public void tearDown() {
        // Remove annotation cache
        File file = new File("annotation-cache");
        file.delete();
    }

    @Test
    public void testApplication() throws ApplicationException, FileNotFoundException {
        app.run();
        System.out.println(LineIO.slurp(pipelineConfig.getOutputPath()));
    }

}