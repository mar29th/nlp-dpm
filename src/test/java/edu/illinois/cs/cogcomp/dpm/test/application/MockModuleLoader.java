package edu.illinois.cs.cogcomp.dpm.test.application;

import edu.illinois.cs.cogcomp.dpm.module.Loader;
import edu.illinois.cs.cogcomp.dpm.test.MockPackageInterface;

import java.io.File;
import java.util.List;

public class MockModuleLoader implements Loader {
    @Override
    public Class load(String fullQualifiedName) throws ClassNotFoundException {
        return MockPackageInterface.class;
    }

    @Override
    public void setResources(List<File> resources) {

    }
}
