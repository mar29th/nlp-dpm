package edu.illinois.cs.cogcomp.dpm.test;

import edu.illinois.cs.cogcomp.annotation.Annotator;
import edu.illinois.cs.cogcomp.pos.POSAnnotator;

public class MockPackageInterface {
    public Annotator getAnnotator() {
        return new POSAnnotator();
    }
}