package edu.illinois.cs.cogcomp.dpm.sourcesupply;

import edu.illinois.cs.cogcomp.dpm.listener.OnDownloaderStatusUpdateListener;
import org.sonatype.aether.graph.Dependency;

import java.io.File;
import java.util.List;

/**
 * Source downloader that saves remote source to local directory.
 */
public interface Downloader {
    /**
     * Download source and save to <code>destDir</code> using list of dependencies.
     *
     * @param dependencyList List of dependencies.
     * @param destDir Local cache directory for downloaded source.
     * @return List of open {@link File}s, each corresponding to a .class file.
     * @throws Exception Failure on download.
     */
    List<File> download(List<Dependency> dependencyList, String destDir) throws Exception;

    /**
     * Set downloader status update listener.
     *
     * @param listener Downloader status update listener.
     */
    void setOnDownloaderStatusUpdateListener(OnDownloaderStatusUpdateListener listener);

    /**
     * Get downloader status update listener.
     *
     * @return Get downloader status update listener.
     */
    OnDownloaderStatusUpdateListener getOnDownloaderStatusUpdateListener();
}
