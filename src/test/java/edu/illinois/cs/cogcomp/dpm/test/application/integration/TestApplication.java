package edu.illinois.cs.cogcomp.dpm.test.application.integration;

import edu.illinois.cs.cogcomp.dpm.config.DefaultGlobalConfig;
import edu.illinois.cs.cogcomp.dpm.runner.Application;
import edu.illinois.cs.cogcomp.dpm.runner.ApplicationException;
import edu.illinois.cs.cogcomp.dpm.test.MockPipelineConfig;
import org.junit.Before;
import org.junit.Test;

public class TestApplication {

    Application app;

    @Before
    public void setUp() {
        app = new Application(
                new DefaultGlobalConfig(), new MockPipelineConfig(), new MockDownloader(), new MockModuleLoader());
    }

    @Test
    public void testApplication() throws ApplicationException {
        app.run("a");
    }

}