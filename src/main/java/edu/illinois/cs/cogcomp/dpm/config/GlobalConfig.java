package edu.illinois.cs.cogcomp.dpm.config;

/**
 * Provides global configuration parameters. Should be implemented (populated) by subclasses.
 *
 * Global configuration provides application with its default parameters (e.g. Downloader cache directory).
 */
public interface GlobalConfig {
    /**
     * Get global configuration file path.
     *
     * When global config is populated from files, this method returns the absolute path of that config file.
     *
     * @return Absolute path of config file corresponding to this instance. Null if not instantiated through file.
     */
    String getGlobalConfigPath();

    /**
     * Get Maven cache directory on user's machine.
     *
     * @return Absolute path of user's Maven cache directory.
     */
    String getMavenRepoPath();
}
