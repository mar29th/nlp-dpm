package edu.illinois.cs.cogcomp.dpm.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Loader that fetches class from cache. If not exist, then fetch from jars.
 * Must be singleton.
 */
public class CachedLoader implements Loader {

    private HashMap<String, Class> cache;
    private ClassLoader loader = null;

    private Logger logger = LogManager.getLogger();

    public CachedLoader() {
        cache = new HashMap<>();
        loader = this.getClass().getClassLoader();
    }

    public Class load(String fullQualifiedName) throws ClassNotFoundException {
        // Get class from cache if any
        Class clazz = cache.get(fullQualifiedName);
        if (clazz != null) {
            return clazz;
        }

        // If not found, then load using URLClassLoader
        clazz = loader.loadClass(fullQualifiedName);
        cache.put(fullQualifiedName, clazz);
        return clazz;
    }

    public void setResources(List<File> resources) {
        List<URL> urlList = new ArrayList<>();
        for (File resource : resources) {
            try {
                urlList.add(resource.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                logger.error("Loader failed load class from a JAR URL {}, which shouldn't happen", resource.getName());
            }
        }

        URL[] urls = new URL[urlList.size()];
        urlList.toArray(urls);
        loader = new URLClassLoader(urls, this.getClass().getClassLoader());
    }
}
