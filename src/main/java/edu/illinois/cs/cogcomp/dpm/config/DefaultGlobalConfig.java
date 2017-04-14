package edu.illinois.cs.cogcomp.dpm.config;

public class DefaultGlobalConfig implements GlobalConfig {

    /**
     * Base path to local cache. Fixed to "~/.dpm".
     */
    private static final String CONFIG_PATH = System.getProperty("user.home") + "/.dpm";

    /**
     * Full path to maven jars and POMs. Fixed to ~/.m2/repository".
     * TODO: This works under the assumption that user doesn't change Maven local repo path.
     */
    private static final String REPO_PATH = System.getProperty("user.home") + "/.m2/repository";

    public String getGlobalConfigPath() {
        return CONFIG_PATH;
    }

    public String getMavenRepoPath() {
        return REPO_PATH;
    }
}
