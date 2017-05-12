package edu.illinois.cs.cogcomp.dpm.module;

import java.io.File;
import java.util.List;

import javax.inject.Singleton;

/**
 * Dynamic module loader interface. Should be subclassed.
 *
 * Loader loads a module dynamically from a set of files. The files should be first stored to certain local directory.
 */
@Singleton
public interface Loader {
    /**
     * Loads a class based on full classpath (e.g. edu.illinois.cs.cogcomp.dpm.Loader).
     *
     * Beware during implmentations: If {@link ClassLoader} is used, do not instantiate a {@link Class} from
     * {@link ClassLoader} twice. Always cache instance right after obtaining it from {@link ClassLoader}. Otherwise
     * inexplicable bug may happen.
     *
     * @param fullQualifiedName Full package name and class name.
     * @return Uninstantiated class representation if a class is found.
     * @throws ClassNotFoundException The specified class does not exist or cannot be extracted.
     */
    Class load(String fullQualifiedName) throws ClassNotFoundException;

    /**
     * Provide a list of file resources for loader to find classes from.
     *
     * This method is mostly used for loaders that find classes from downloaded local files.
     * Due to dependency injection restrictions, we cannot provide a constructor that takes in this value.
     * Ignore this method if not applicable.
     *
     * @param resources List of resource {@link File}s that loader implementation potentially uses.
     */
    void setResources(List<File> resources);
}
