package edu.illinois.cs.cogcomp.dpm.config.bean;

import java.util.HashMap;
import java.util.Map;

public class ViewBean {
    private String groupId;
    private String artifactId;
    private String version;
    private String entrypoint;
    private Map<String, Object> options;

    public ViewBean(String groupId) {
        this.groupId = groupId;
    }

    public ViewBean(
            String groupId,
            String artifactId,
            String version,
            String entrypoint) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.entrypoint = entrypoint;
        this.options = new HashMap<>();
    }

    public ViewBean(
            String groupId,
            String artifactId,
            String version,
            String entrypoint,
            Map<String, Object> options) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.entrypoint = entrypoint;
        this.options = options;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String entrypoint) {
        this.entrypoint = entrypoint;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
