package edu.illinois.cs.cogcomp.dpm.runner;

import java.io.IOException;

/**
 * Simple command line wrapper for {@link Application}.
 *
 * Run as: <code>java -jar {file}.jar {configFilePath}</code>,
 * where {file} is the .jar's filename.
 *
 * The CLI takes in one parameter - absolute path to pipeline configuration.
 * It will then execute {@link Application#run()} for exactly once.
 *
 */
public class CLI {

    private static final String HEADER = "usage: java -jar {file}.jar {configFilePath}";

    private String[] args;

    /**
     * Main function.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new CLI(args).run();
    }

    /**
     * Constructor that takes in command line arguments.
     *
     * @param args Command line arguments.
     */
    public CLI(String[] args) {
        this.args = args;
    }

    /**
     * Run the pipeline for exactly once.
     */
    public void run() {
        if (args.length != 1) {
            System.out.println(HEADER);
            return;
        }

        String configFilePath = args[0];
        int exitStatus = 0;

        try {
            Application app = Application.create(configFilePath);
            app.run();
        } catch (IOException e) {
            System.err.println("Cannot open config file at " + configFilePath);
            exitStatus = 1;
        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
            exitStatus = 1;
        }

        if (exitStatus != 0) {
            System.exit(exitStatus);
        }
    }
}
