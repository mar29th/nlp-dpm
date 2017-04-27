package edu.illinois.cs.cogcomp.dpm.test;

import edu.illinois.cs.cogcomp.annotation.Annotator;
import edu.illinois.cs.cogcomp.annotation.AnnotatorServiceConfigurator;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.utilities.configuration.ResourceManager;
import edu.illinois.cs.cogcomp.ner.NerAnnotatorManager;

public class MockPackageInterface {
    public Annotator getAnnotator() {
        ResourceManager manager = new AnnotatorServiceConfigurator().getDefaultConfig();
        return NerAnnotatorManager.buildNerAnnotator(manager, ViewNames.NER_CONLL);
    }
}