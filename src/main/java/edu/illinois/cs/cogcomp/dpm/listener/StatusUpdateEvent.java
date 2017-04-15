package edu.illinois.cs.cogcomp.dpm.listener;

import java.util.HashMap;
import java.util.Map;

public class StatusUpdateEvent {

    public static enum Type {
        DOWNLOADER_STARTED,
        DOWNLOADER_UPDATE,
        DOWNLOADER_COMPLETED
    }

    private Type type;
    private Map<String, Object> args = null;

    public StatusUpdateEvent(Type type) {
        this.type = type;
        this.args = new HashMap<>();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public <T> T getValue(String key, Class<T> clazz) {
        return (T) args.get(key);
    }

    public <T> void putValue(String key, T value) {
        args.put(key, value);
    }

    public void removeValue(String key) {
        args.remove(key);
    }
}
