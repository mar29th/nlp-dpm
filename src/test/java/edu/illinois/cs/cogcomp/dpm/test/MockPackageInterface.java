package edu.illinois.cs.cogcomp.dpm.test;

import edu.illinois.cs.cogcomp.annotation.Annotator;

public class MockPackageInterface {
    public Annotator getAnnotator() {
        return new MockAnnotator("nil", new String[]{});
    }
}