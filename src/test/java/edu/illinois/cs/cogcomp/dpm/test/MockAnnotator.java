package edu.illinois.cs.cogcomp.dpm.test;

import edu.illinois.cs.cogcomp.annotation.Annotator;
import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.SpanLabelView;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.utilities.configuration.ResourceManager;

public class MockAnnotator extends Annotator {
    public MockAnnotator(String viewName, String[] requiredViews) {
        super(viewName, requiredViews);
    }

    public MockAnnotator(String viewName, String[] requiredViews, boolean isLazilyInitialized) {
        super(viewName, requiredViews, isLazilyInitialized);
    }

    public MockAnnotator(String viewName, String[] requiredViews, ResourceManager rm) {
        super(viewName, requiredViews, rm);
    }

    public MockAnnotator(String viewName, String[] requiredViews, boolean isLazilyInitialized, ResourceManager config) {
        super(viewName, requiredViews, isLazilyInitialized, config);
    }

    @Override
    public void initialize(ResourceManager resourceManager) {
        // Does absolutely nothing
    }

    @Override
    protected void addView(TextAnnotation textAnnotation) throws AnnotatorException {
        SpanLabelView view = new SpanLabelView(getViewName(), textAnnotation);
        view.addSpanLabel(0, textAnnotation.getSentence(0).getEndSpan(), "absolutely nothing", 1d);
        textAnnotation.addView(viewName, view);
    }
}
