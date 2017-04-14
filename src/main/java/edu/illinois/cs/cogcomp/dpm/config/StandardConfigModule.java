package edu.illinois.cs.cogcomp.dpm.config;

import dagger.Module;
import dagger.Provides;

@Module
public class StandardConfigModule {

    private GlobalConfig globalConfig;
    private PipelineConfig pipelineConfig;

    public StandardConfigModule(GlobalConfig globalConfig, PipelineConfig pipelineConfig) {
        this.globalConfig = globalConfig;
        this.pipelineConfig = pipelineConfig;
    }

    @Provides
    public GlobalConfig provideGlobalConfig() {
        return globalConfig;
    }

    @Provides
    public PipelineConfig providePipelineConfig() {
        return pipelineConfig;
    }
}
