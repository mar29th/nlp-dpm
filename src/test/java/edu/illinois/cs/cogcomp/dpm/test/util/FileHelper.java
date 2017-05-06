package edu.illinois.cs.cogcomp.dpm.test.util;

import java.io.File;
import java.io.IOException;

public class FileHelper {

    public static File createFile(String filename) throws IOException {
        File retval = new File(filename);
        retval.createNewFile();
        retval.deleteOnExit();
        return retval;
    }
}
