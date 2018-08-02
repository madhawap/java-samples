package com.mad.java.samples.spatbeans;

/**
 *
 */
public class MovementState {

    private String movementName;
    private String signalGroup;
    private StateTimeSpeed stateTimeSpeed;

    public String getMovementName() {
        return movementName;
    }

    public void setMovementName(String movementName) {
        this.movementName = movementName;
    }

    public String getSignalGroup() {
        return signalGroup;
    }

    public void setSignalGroup(String signalGroup) {
        this.signalGroup = signalGroup;
    }

    public StateTimeSpeed getStateTimeSpeed() {
        return stateTimeSpeed;
    }

    public void setStateTimeSpeed(StateTimeSpeed stateTimeSpeed) {
        this.stateTimeSpeed = stateTimeSpeed;
    }
}
