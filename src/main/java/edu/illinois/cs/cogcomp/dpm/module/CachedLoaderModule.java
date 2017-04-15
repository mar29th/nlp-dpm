package edu.illinois.cs.cogcomp.dpm.module;

import dagger.Module;
import dagger.Provides;

@Module
public class CachedLoaderModule {
    @Provides
    public Loader provideLoader() {
        return new CachedLoader();
    }
}
