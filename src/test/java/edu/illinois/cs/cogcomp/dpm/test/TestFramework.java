package edu.illinois.cs.cogcomp.dpm.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public abstract class TestFramework {

    @Rule
    public EnvironmentVariables ev = new EnvironmentVariables();

    @Before
    public void setDebugEnvironmentVariable() {
        ev.set("DEBUG", "TRUE");
    }
}
