package edu.illinois.cs.cogcomp.dpm.test.application.integration;

import edu.illinois.cs.cogcomp.dpm.config.DefaultGlobalConfig;
import edu.illinois.cs.cogcomp.dpm.runner.Application;
import edu.illinois.cs.cogcomp.dpm.runner.ApplicationException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestApplication {

    Application app;

    @Before
    public void setUp() {
        // Remove annotation cache
        File file = new File("annotation-cache");
        file.delete();

        app = new Application(
                new DefaultGlobalConfig(), new MockPipelineConfig(), new MockDownloader(), new MockModuleLoader());
    }

    @Test
    public void testApplication() throws ApplicationException {
        System.out.println(app.run("a"));
    }

}