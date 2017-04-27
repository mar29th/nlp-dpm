package edu.illinois.cs.cogcomp.dpm.test.application.integration;

import edu.illinois.cs.cogcomp.dpm.listener.OnDownloaderStatusUpdateListener;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.Downloader;
import org.sonatype.aether.graph.Dependency;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class MockDownloader implements Downloader {

    @Override
    public List<File> download(List<Dependency> dependencyList, String destDir) throws Exception {
        return Collections.emptyList();
    }

    @Override
    public void setOnDownloaderStatusUpdateListener(OnDownloaderStatusUpdateListener listener) {

    }

    @Override
    public OnDownloaderStatusUpdateListener getOnDownloaderStatusUpdateListener() {
        return null;
    }
}
