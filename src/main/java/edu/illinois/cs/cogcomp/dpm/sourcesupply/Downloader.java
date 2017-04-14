package edu.illinois.cs.cogcomp.dpm.sourcesupply;

import org.sonatype.aether.graph.Dependency;

import java.util.List;

public interface Downloader {
    void download(List<Dependency> dependencyList, String destDir) throws Exception;
}
