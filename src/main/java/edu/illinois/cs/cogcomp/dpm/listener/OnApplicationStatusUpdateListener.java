package edu.illinois.cs.cogcomp.dpm.listener;

/**
 * Listener that receives application status updates.
 */
public interface OnApplicationStatusUpdateListener {
    /**
     * Called when {@link edu.illinois.cs.cogcomp.dpm.runner.Application} has status updates.
     *
     * Note that this method is called only in the middle of execution; exceptions are thrown regularly and there
     * is no callback in this listener that handles failures.
     *
     * @param event An event container that serializes event type and extra information.
     */
    void onUpdate(StatusUpdateEvent event);
}
