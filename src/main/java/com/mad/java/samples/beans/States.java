package com.mad.java.samples.beans;

import java.util.List;

/**
 *
 */
public class States {

    private MovementState movementState;

    private List<MovementState> movementStateList;

    public MovementState getMovementState() {
        return movementState;
    }

    public void setMovementState(MovementState movementState) {
        this.movementState = movementState;
    }

    public List<MovementState> getMovementStateList() {
        return movementStateList;
    }

    public void setMovementStateList(List<MovementState> movementStateList) {
        this.movementStateList = movementStateList;
    }
}
