package edu.illinois.cs.cogcomp.dpm.internal;

import edu.illinois.cs.cogcomp.dpm.api.Portable;

public interface Loader {
    Class<Portable> load(String fullQualifiedName) throws ClassNotFoundException;
}
