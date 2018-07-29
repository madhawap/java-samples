package com.mad.java.samples.beans;

/**
 *
 */
public enum EventState {

    STOP_AND_REMAIN("stop-And-Remain"),
    PROTECTED_CLEARNANCE("protected-clearance"),
    PROTECTED_MOVEMENT_ALLOWED("protected-Movement-Allowed");

    private String eventState;

    EventState(String eventState) {
        this.eventState = eventState;
    }

    public String getEventState() {
        return eventState;
    }

}
