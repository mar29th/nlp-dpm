package edu.illinois.cs.cogcomp.dpm.test.application.integration;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;

import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.runner.CLI;
import edu.illinois.cs.cogcomp.dpm.test.TestFramework;

import static junit.framework.TestCase.assertTrue;

public class TestCLI extends TestFramework {

    private static final PipelineConfig pipelineConfig = new MockPipelineConfig();

    private static final String CONFIG_PATH = "src/test/resources/test-pipeline-config.json";

    @AfterClass
    public static void clearUp() {
        new File(pipelineConfig.getOutputPath()).delete();
    }

    @Test
    public void testCLI() {
        CLI cli = new CLI(new String[] {CONFIG_PATH});
        cli.run();

        File outputFile = new File(pipelineConfig.getOutputPath());
        assertTrue(outputFile.exists());
    }
}
