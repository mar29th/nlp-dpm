package edu.illinois.cs.cogcomp.dpm.sourcesupply;

import edu.illinois.cs.cogcomp.dpm.listener.OnDownloaderStatusUpdateListener;
import org.sonatype.aether.graph.Dependency;

import java.io.File;
import java.util.List;

public interface Downloader {
    List<File> download(List<Dependency> dependencyList, String destDir) throws Exception;
    void setOnDownloaderStatusUpdateListener(OnDownloaderStatusUpdateListener listener);
    OnDownloaderStatusUpdateListener getOnDownloaderStatusUpdateListener();
}
