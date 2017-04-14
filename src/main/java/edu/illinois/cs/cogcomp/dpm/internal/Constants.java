package edu.illinois.cs.cogcomp.dpm.internal;

/**
 * Hardcoded constants, such as static path to local repo.
 */
public class Constants {

    /**
     * Base path to local cache. Fixed to "~/.dpm".
     */
    public static final String BASE_PATH = System.getProperty("user.home") + "/.dpm";

    /**
     * Full path to maven jars and POMs. Fixed to ~/.m2/repository".
     * TODO: This works under the assumption that user doesn't change Maven local repo path.
     */
    public static final String REPO_PATH = System.getProperty("user.home") + "/.m2/repository";

}
