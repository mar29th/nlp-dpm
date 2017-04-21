package edu.illinois.cs.cogcomp.dpm.module;

import java.io.File;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public interface Loader {
    Class load(String fullQualifiedName) throws ClassNotFoundException;
    void setResources(List<File> resources);
}
