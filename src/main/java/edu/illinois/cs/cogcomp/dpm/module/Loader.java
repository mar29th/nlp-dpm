package edu.illinois.cs.cogcomp.dpm.module;

public interface Loader {
    Class load(String fullQualifiedName) throws ClassNotFoundException;
}
