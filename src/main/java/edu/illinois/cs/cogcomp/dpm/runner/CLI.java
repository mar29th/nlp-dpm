package edu.illinois.cs.cogcomp.dpm.runner;

import java.io.IOException;

public class CLI {

    private static final String HEADER = "usage: java -jar {file}.jar {configFilePath}";

    private String[] args;

    public static void main(String[] args) {
        new CLI(args).run();
    }

    public CLI(String[] args) {
        this.args = args;
    }

    public void run() {
        if (args.length != 1) {
            System.out.println(HEADER);
            return;
        }

        String configFilePath = args[0];
        try {
            Application app = Application.create(configFilePath);
            app.run();
        } catch (IOException e) {
            System.err.println("Cannot open config file at " + configFilePath);
        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }
    }
}
