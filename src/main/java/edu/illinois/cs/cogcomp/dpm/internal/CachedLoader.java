package edu.illinois.cs.cogcomp.dpm.internal;

import java.util.HashMap;

import edu.illinois.cs.cogcomp.dpm.api.Portable;

/**
 * Loader that fetches class from cache. If not exist, then fetch from jars.
 * Must be singleton.
 */
public class CachedLoader implements Loader {

    private HashMap<String, Class<Portable>> cache;

    public CachedLoader() {
        cache = new HashMap<String, Class<Portable>>();
    }

    public Class<Portable> load(String fullQualifiedName) throws ClassNotFoundException {
        Class<Portable> clazz = cache.get(fullQualifiedName);
        if (clazz != null) {
            return clazz;
        }
        ClassLoader loader = this.getClass().getClassLoader();
        clazz = (Class<Portable>) loader.loadClass(fullQualifiedName);
        cache.put(fullQualifiedName, clazz);
        return clazz;
    }
}
