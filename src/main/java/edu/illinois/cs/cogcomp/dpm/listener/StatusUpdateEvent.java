package edu.illinois.cs.cogcomp.dpm.listener;

import java.util.HashMap;
import java.util.Map;

/**
 * An event container serializing event type and extra information.
 *
 * This event container is used by both {@link OnApplicationStatusUpdateListener} and
 * {@link OnDownloaderStatusUpdateListener}. Extra information is simply put into a {@link Map}, and the corresponding
 * keys are defined differently for either {@link OnApplicationStatusUpdateListener} or
 * {@link OnDownloaderStatusUpdateListener}.
 */
public class StatusUpdateEvent {

    /**
     * Contains different types of event
     */
    public static enum Type {
        DOWNLOADER_STARTED,
        DOWNLOADER_UPDATE,
        DOWNLOADER_COMPLETED
    }

    private Type type;
    private Map<String, Object> args = null;

    /**
     * Constructor for an event.
     *
     * @param type One of {@link Type}.
     */
    public StatusUpdateEvent(Type type) {
        this.type = type;
        this.args = new HashMap<>();
    }

    /**
     * Get type contained in this event.
     *
     * @return Type of this event.
     */
    public Type getType() {
        return type;
    }

    /**
     * Set type container in this event.
     *
     * @param type Type of this event.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Get extra value passed from caller.
     *
     * @param key Key of extra information. Same idea as {@link Map#get(Object)}
     * @return Extra info provided by the caller. Should be manually cast to appropriate types.
     */
    public Object getValue(String key) {
        return args.get(key);
    }

    /**
     * Set extra value passed from caller.
     *
     * For maximum compatibility, values are cast to {@link Object} and put into a map.
     *
     * @param key Key of extra information. Same idea as {@link Map#put(Object, Object)}
     * @param value Extra info provided by the caller.
     */
    public void putValue(String key, Object value) {
        args.put(key, value);
    }

    /**
     * Remove extra information. Same idea as {@link Map#remove(Object)}.
     *
     * @param key Key of extra information. Same as a key in a {@link Map}.
     */
    public void removeValue(String key) {
        args.remove(key);
    }
}
