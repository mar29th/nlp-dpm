package edu.illinois.cs.cogcomp.dpm.module;

import java.util.HashMap;

/**
 * Loader that fetches class from cache. If not exist, then fetch from jars.
 * Must be singleton.
 */
public class CachedLoader implements Loader {

    private HashMap<String, Class> cache;

    public CachedLoader() {
        cache = new HashMap<String, Class>();
    }

    public Class load(String fullQualifiedName) throws ClassNotFoundException {
        Class clazz = cache.get(fullQualifiedName);
        if (clazz != null) {
            return clazz;
        }
        ClassLoader loader = this.getClass().getClassLoader();
        clazz = loader.loadClass(fullQualifiedName);
        cache.put(fullQualifiedName, clazz);
        return clazz;
    }
}
