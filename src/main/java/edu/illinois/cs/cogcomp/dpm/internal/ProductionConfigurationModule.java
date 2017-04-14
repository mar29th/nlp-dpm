package edu.illinois.cs.cogcomp.dpm.internal;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductionConfigurationModule {

    private Configuration config;

    ProductionConfigurationModule(Configuration config) {
        this.config = config;
    }

    @Provides
    public Configuration provideStandardConfiguration() {
        return config;
    }
}
