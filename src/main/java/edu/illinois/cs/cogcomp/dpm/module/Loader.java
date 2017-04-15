package edu.illinois.cs.cogcomp.dpm.module;

import javax.inject.Singleton;

@Singleton
public interface Loader {
    Class load(String fullQualifiedName) throws ClassNotFoundException;
}
