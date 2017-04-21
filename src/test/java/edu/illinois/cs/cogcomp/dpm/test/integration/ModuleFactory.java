package edu.illinois.cs.cogcomp.dpm.test.integration;

import dagger.Component;
import edu.illinois.cs.cogcomp.dpm.config.GlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.StandardConfigModule;
import edu.illinois.cs.cogcomp.dpm.module.CachedLoaderModule;
import edu.illinois.cs.cogcomp.dpm.module.Loader;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.Downloader;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.MavenDownloaderModule;

@Component(modules = {
    StandardConfigModule.class,
    MavenDownloaderModule.class,
    CachedLoaderModule.class
})
public interface ModuleFactory {
    Loader getLoader();
    Downloader getDownloader();
    GlobalConfig getGlobalConfig();
    PipelineConfig getPipelineConfig();
}
