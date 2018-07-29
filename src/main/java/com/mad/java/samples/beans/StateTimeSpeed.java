package com.mad.java.samples.beans;

import java.util.List;

/**
 *
 */
public class StateTimeSpeed {

    private MovementEvent movementEvent;
    private List<MovementEvent> movementEventList;


    public MovementEvent getMovementEvent() {
        return movementEvent;
    }

    public void setMovementEvent(MovementEvent movementEvent) {
        this.movementEvent = movementEvent;
    }

    public List<MovementEvent> getMovementEventList() {
        return movementEventList;
    }

    public void setMovementEventList(List<MovementEvent> movementEventList) {
        this.movementEventList = movementEventList;
    }
}
