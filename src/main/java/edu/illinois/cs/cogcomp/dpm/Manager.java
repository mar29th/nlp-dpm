package edu.illinois.cs.cogcomp.dpm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

import edu.illinois.cs.cogcomp.dpm.internal.Configuration;
import edu.illinois.cs.cogcomp.dpm.internal.Constants;

public class Manager {

    private Configuration config = null;
    private Logger logger = LogManager.getLogger();

    public Manager(Configuration config) {
        this.config = config;
        init();
    }

    private void init() {
        // Check for global correctness
        // 1. Base cache path must be in CLASSPATH
        Map<String, String> env = System.getenv();
        String classPath = env.get("CLASSPATH");
        if (classPath == null || !classPath.contains(Constants.BASE_PATH)) {
            logger.error("Expect " + Constants.BASE_PATH + " in CLASSPATH");
            System.exit(255);
        }

        // 2. Check if cache folder actually exists
        File f = new File(Constants.BASE_PATH);
        if (!f.exists() || !f.isDirectory()) {
            // If not then create
            f.mkdir();
        }
    }

}
