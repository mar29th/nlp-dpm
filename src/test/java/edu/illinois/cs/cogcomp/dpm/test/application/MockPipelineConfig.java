package edu.illinois.cs.cogcomp.dpm.test.application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.bean.RepositoryBean;
import edu.illinois.cs.cogcomp.dpm.config.bean.ViewBean;
import edu.illinois.cs.cogcomp.dpm.test.util.FileHelper;

public class MockPipelineConfig implements PipelineConfig {

    @Override
    public String getProjectDirectoryPath() {
        try {
            Path tmpPath = Files.createTempDirectory("dpm");
            tmpPath.toFile().deleteOnExit();
            return tmpPath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RepositoryBean> getRepositories() {
        return Arrays.asList(
            new RepositoryBean("central", "http://repo1.maven.org/maven2"),
            new RepositoryBean("cogcomp", "http://cogcomp.cs.illinois.edu/m2repo")
        );
    }

    @Override
    public List<ViewBean> getViews() {
        return Collections.singletonList(
            new ViewBean(
                    "edu.illinois.edu.cogcomp", "doesnt-matter-yet", "3.1.11", "edu.illinois.cs.cogcomp.dpm.test.MockPackageInterface")
        );
    }

    @Override
    public String getCorpusPath() {
        return "src/test/resources/pipelinePosText.txt";
    }

    @Override
    public String getOutputPath() {
        try {
            return FileHelper.createFile("output").getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
