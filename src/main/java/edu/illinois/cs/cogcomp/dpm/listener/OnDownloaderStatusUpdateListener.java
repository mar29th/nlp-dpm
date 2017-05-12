package edu.illinois.cs.cogcomp.dpm.listener;

/**
 * Listener that receives downloader status updates.
 */
public interface OnDownloaderStatusUpdateListener {
    /**
     * Called when {@link edu.illinois.cs.cogcomp.dpm.sourcesupply.Downloader} has status updates.
     *
     * Note that this method is called only in the middle of execution; exceptions are thrown regularly and there
     * is no callback in this listener that handles failures.
     *
     * @param event An event container that serializes event type and extra information.
     */
    void onUpdate(StatusUpdateEvent event);
}
