package edu.illinois.cs.cogcomp.dpm.sourcesupply;

import dagger.Module;
import dagger.Provides;

@Module
public class MavenDownloaderModule {
    @Provides
    public Downloader provideDownloader(MavenDownloader mavenDownloader) {
        return mavenDownloader;
    }
}
