package com.mad.java.samples.dataholders;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class PictoCodeLookUpData {

    @SerializedName("Sign_Description")
    private String signDescription;

    @SerializedName("Sign")
    private String sign;

    @SerializedName("Service_Category_Code")
    private int serviceCategoryCode;

    @SerializedName("Pictogram_Code")
    private int pictogramCode;

    @SerializedName("Attr_Ind_Code")
    private int attrIndCode;

    @SerializedName("HORUS_Code")
    private int horusCode;

    @SerializedName("ISO14823Attributes")
    private String iso14823Attributes;

    @SerializedName("SPM")
    private int spm;

    @SerializedName("Unit")
    private String unit;

    @SerializedName("Lane_Status")
    private String laneStatus;

    public String getSignDescription() {
        return signDescription;
    }

    public void setSignDescription(String signDescription) {
        this.signDescription = signDescription;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getServiceCategoryCode() {
        return serviceCategoryCode;
    }

    public void setServiceCategoryCode(int serviceCategoryCode) {
        this.serviceCategoryCode = serviceCategoryCode;
    }

    public int getPictogramCode() {
        return pictogramCode;
    }

    public void setPictogramCode(int pictogramCode) {
        this.pictogramCode = pictogramCode;
    }

    public int getAttrIndCode() {
        return attrIndCode;
    }

    public void setAttrIndCode(int attrIndCode) {
        this.attrIndCode = attrIndCode;
    }

    public int getHorusCode() {
        return horusCode;
    }

    public void setHorusCode(int horusCode) {
        this.horusCode = horusCode;
    }

    public String getIso14823Attributes() {
        return iso14823Attributes;
    }

    public void setIso14823Attributes(String iso14823Attributes) {
        this.iso14823Attributes = iso14823Attributes;
    }

    public int getSpm() {
        return spm;
    }

    public void setSpm(int spm) {
        this.spm = spm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLaneStatus() {
        return laneStatus;
    }

    public void setLaneStatus(String laneStatus) {
        this.laneStatus = laneStatus;
    }
}
