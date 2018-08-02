package com.mad.java.samples.dataholders;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class LookUpData {

    @SerializedName("Gantry_Latitude")
    private double gantryLatitude;

    @SerializedName("Gantry_Longitude")
    private double gantryLongitude;

    @SerializedName("Gantry_Number")
    private int gantryNumber;

    @SerializedName("Zone1_Latitude")
    private double zone1Latitude;

    @SerializedName("Zone1_Longitude")
    private double zone1Longitude;

    @SerializedName("Zone2_Latitude")
    private double zone2Latitude;

    @SerializedName("Zone2_Longitude")
    private double zone2Longitude;

    @SerializedName("Zone1_Delta_Latitude")
    private double zone1DeltaLatitude;

    @SerializedName("Zone1_Delta_Longitude")
    private double zone1DeltaLongitude;

    @SerializedName("Heading")
    private int heading;

    @SerializedName("Zone2_Delta_Latitude")
    private double zone2DeltaLatitude;

    @SerializedName("Zone2_Delta_Longitude")
    private double zone2DeltaLongitude;

    @SerializedName("Heading2")
    private int heading2;

    @SerializedName("Direction_of_Travel")
    private String directionOfTravel;

    @SerializedName("Asset_Number_Lane1")
    private String assetNumberLane1;

    @SerializedName("Asset_Number_Lane2")
    private String assetNumberLane2;

    @SerializedName("Asset_Number_Lane3")
    private String assetNumberLane3;

    @SerializedName("Asset_Number_Lane4")
    private String assetNumberLane4;

    public double getGantryLatitude() {
        return gantryLatitude;
    }

    public void setGantryLatitude(double gantryLatitude) {
        this.gantryLatitude = gantryLatitude;
    }

    public double getGantryLongitude() {
        return gantryLongitude;
    }

    public void setGantryLongitude(double gantryLongitude) {
        this.gantryLongitude = gantryLongitude;
    }

    public int getGantryNumber() {
        return gantryNumber;
    }

    public void setGantryNumber(int gantryNumber) {
        this.gantryNumber = gantryNumber;
    }

    public double getZone1Latitude() {
        return zone1Latitude;
    }

    public void setZone1Latitude(double zone1Latitude) {
        this.zone1Latitude = zone1Latitude;
    }

    public double getZone1Longitude() {
        return zone1Longitude;
    }

    public void setZone1Longitude(double zone1Longitude) {
        this.zone1Longitude = zone1Longitude;
    }

    public double getZone2Latitude() {
        return zone2Latitude;
    }

    public void setZone2Latitude(double zone2Latitude) {
        this.zone2Latitude = zone2Latitude;
    }

    public double getZone2Longitude() {
        return zone2Longitude;
    }

    public void setZone2Longitude(double zone2Longitude) {
        this.zone2Longitude = zone2Longitude;
    }

    public double getZone1DeltaLatitude() {
        return zone1DeltaLatitude;
    }

    public void setZone1DeltaLatitude(double zone1DeltaLatitude) {
        this.zone1DeltaLatitude = zone1DeltaLatitude;
    }

    public double getZone1DeltaLongitude() {
        return zone1DeltaLongitude;
    }

    public void setZone1DeltaLongitude(double zone1DeltaLongitude) {
        this.zone1DeltaLongitude = zone1DeltaLongitude;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public double getZone2DeltaLatitude() {
        return zone2DeltaLatitude;
    }

    public void setZone2DeltaLatitude(double zone2DeltaLatitude) {
        this.zone2DeltaLatitude = zone2DeltaLatitude;
    }

    public double getZone2DeltaLongitude() {
        return zone2DeltaLongitude;
    }

    public void setZone2DeltaLongitude(double zone2DeltaLongitude) {
        this.zone2DeltaLongitude = zone2DeltaLongitude;
    }

    public int getHeading2() {
        return heading2;
    }

    public void setHeading2(int heading2) {
        this.heading2 = heading2;
    }

    public String getDirectionOfTravel() {
        return directionOfTravel;
    }

    public void setDirectionOfTravel(String directionOfTravel) {
        this.directionOfTravel = directionOfTravel;
    }

    public String getAssetNumberLane1() {
        return assetNumberLane1;
    }

    public void setAssetNumberLane1(String assetNumberLane1) {
        this.assetNumberLane1 = assetNumberLane1;
    }

    public String getAssetNumberLane2() {
        return assetNumberLane2;
    }

    public void setAssetNumberLane2(String assetNumberLane2) {
        this.assetNumberLane2 = assetNumberLane2;
    }

    public String getAssetNumberLane3() {
        return assetNumberLane3;
    }

    public void setAssetNumberLane3(String assetNumberLane3) {
        this.assetNumberLane3 = assetNumberLane3;
    }

    public String getAssetNumberLane4() {
        return assetNumberLane4;
    }

    public void setAssetNumberLane4(String assetNumberLane4) {
        this.assetNumberLane4 = assetNumberLane4;
    }
}
