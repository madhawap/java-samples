package com.mad.java.samples.beans;

/**
 *
 */
public class Timing {

    private String minEndTime;
    private String maxEndTime;
    private String likelyTime;
    private String confidence;

    public String getMinEndTime() {
        return minEndTime;
    }

    public void setMinEndTime(String minEndTime) {
        this.minEndTime = minEndTime;
    }

    public String getMaxEndTime() {
        return maxEndTime;
    }

    public void setMaxEndTime(String maxEndTime) {
        this.maxEndTime = maxEndTime;
    }

    public String getLikelyTime() {
        return likelyTime;
    }

    public void setLikelyTime(String likelyTime) {
        this.likelyTime = likelyTime;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
