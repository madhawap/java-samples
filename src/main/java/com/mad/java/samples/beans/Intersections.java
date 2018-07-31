package com.mad.java.samples.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Intersections {


    private List<IntersectionState> intersectionStateList = new ArrayList<IntersectionState>();

    public List<IntersectionState> getIntersectionStateList() {
        return intersectionStateList;
    }

    public void setIntersectionStateList(List<IntersectionState> intersectionStateList) {
        this.intersectionStateList = intersectionStateList;
    }

}
