package com.mad.java.samples.beans;

/**
 *
 */
public class PictoCodeLookUpData {

    private String Sign_Description;
    private String Sign;
    private int Service_Category_Code;
    private int Pictogram_Code;
    private int Attr_Ind_Code;
    private int HORUS_Code;
    private String Lane_Status;

    public String getSign_Description() {
        return Sign_Description;
    }

    public void setSign_Description(String sign_Description) {
        Sign_Description = sign_Description;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public int getService_Category_Code() {
        return Service_Category_Code;
    }

    public void setService_Category_Code(int service_Category_Code) {
        Service_Category_Code = service_Category_Code;
    }

    public int getPictogram_Code() {
        return Pictogram_Code;
    }

    public void setPictogram_Code(int pictogram_Code) {
        Pictogram_Code = pictogram_Code;
    }

    public int getAttr_Ind_Code() {
        return Attr_Ind_Code;
    }

    public void setAttr_Ind_Code(int attr_Ind_Code) {
        Attr_Ind_Code = attr_Ind_Code;
    }

    public int getHORUS_Code() {
        return HORUS_Code;
    }

    public void setHORUS_Code(int HORUS_Code) {
        this.HORUS_Code = HORUS_Code;
    }

    public String getLane_Status() {
        return Lane_Status;
    }

    public void setLane_Status(String lane_Status) {
        Lane_Status = lane_Status;
    }
}
