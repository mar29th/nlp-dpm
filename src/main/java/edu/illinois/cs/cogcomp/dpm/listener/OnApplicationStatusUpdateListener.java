package edu.illinois.cs.cogcomp.dpm.listener;

public interface OnApplicationStatusUpdateListener {
    void onStarted();
    void onCompleted();
    void onUpdate(StatusUpdateEvent event);
    void onError(Exception e);
}
