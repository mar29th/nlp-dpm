package edu.illinois.cs.cogcomp.dpm.runner;

import dagger.Component;
import edu.illinois.cs.cogcomp.dpm.config.StandardConfigModule;
import edu.illinois.cs.cogcomp.dpm.module.CachedLoaderModule;
import edu.illinois.cs.cogcomp.dpm.sourcesupply.MavenDownloaderModule;

@Component(modules={
        StandardConfigModule.class,
        CachedLoaderModule.class,
        MavenDownloaderModule.class
})
interface ApplicationFactory {
    Application get();
}
