package edu.illinois.cs.cogcomp.dpm.internal;

import java.util.List;

public interface Downloader {
    void download(List<String> packageList, String destDir) throws Exception;
}
