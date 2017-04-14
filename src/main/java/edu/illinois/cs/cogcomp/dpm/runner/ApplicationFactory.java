package edu.illinois.cs.cogcomp.dpm.runner;

import dagger.Component;
import edu.illinois.cs.cogcomp.dpm.config.StandardConfigModule;

@Component(modules={StandardConfigModule.class})
interface ApplicationFactory {
    Application get();
}
