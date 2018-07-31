package com.mad.java.samples;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.java.samples.beans.LookUpData;
import com.mad.java.samples.beans.PictoCodeLookUpData;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 *
 */
public class JsonToPOJO {

    private static Logger log = Logger.getLogger(JsonToPOJO.class);


    public static void main(String[] args) {
        Reader readerJsonArray = null;
        Reader readerPictoJsonArray = null;

        //Json look up table Array to a JavaList
        try {
                readerJsonArray = new FileReader(
                        "/Users/madhawa/Downloads/CustomerSupport/TfL/ASN/Lookuptables/gantry_lookup_table"
                                + ".json");

                jsonArrayToList(readerJsonArray);
            } catch (IOException e) {
               log.error("");
            } finally {
                try {
                    if (readerJsonArray != null) {
                        readerJsonArray.close();
                    }

                } catch (IOException e){
                    log.error("");
                }
            }


        //Json Picto look up table Array to a JavaList
        try {
            readerPictoJsonArray = new FileReader(
                    "/Users/madhawa/Downloads/CustomerSupport/TfL/ASN/Lookuptables/Picto_lookup_table"
                            + ".json");

            jsonPictoArrayToList(readerPictoJsonArray);
        } catch (IOException e) {
            log.error("");
        } finally {
            try {
                if (readerPictoJsonArray != null) {
                    readerPictoJsonArray.close();
                }

            } catch (IOException e){
                log.error("");
            }
        }
    }


    private static void jsonArrayToList(Reader reader) {

        Gson gson = new Gson();

        // Convert JSON to Java Object
        TypeToken<List<LookUpData>> token = new TypeToken<List<LookUpData>>(){};
        List<LookUpData> lookUpDataList = gson.fromJson(reader, token.getType());
        printJsonArrays(lookUpDataList);

    }

    private static void jsonPictoArrayToList(Reader reader) {

        Gson gson1 = new Gson();

        // Convert JSON to Java Object
        TypeToken<List<PictoCodeLookUpData>> token = new TypeToken<List<PictoCodeLookUpData>>(){};
        List<PictoCodeLookUpData> pictolookUpDataList = gson1.fromJson(reader, token.getType());
        printPictoJsonArrays(pictolookUpDataList);

    }

    private static void printJsonArrays(List<LookUpData> jsonArray){
        int i = 0;

            for (LookUpData lookUpData : jsonArray) {
                log.error("----------------------- Look up table Row " + i + "----------------------------");
                log.error(lookUpData.getGantry_Latitude());
                log.error(lookUpData.getGantry_Longitude());
                log.error(lookUpData.getGantry_Number());
                log.error(lookUpData.getZone1_Latitude());
                log.error(lookUpData.getZone1_Longitude());
                log.error(lookUpData.getZone2_Longitude());
                log.error(lookUpData.getZone2_Latitude());
                log.error(lookUpData.getZone1_Delta_Latitude());
                log.error(lookUpData.getZone1_Delta_Longitude());
                log.error(lookUpData.getHeading());
                log.error(lookUpData.getZone2_Delta_Latitude());
                log.error(lookUpData.getZone2_Delta_Longitude());
                log.error(lookUpData.getHeading2());
                log.error(lookUpData.getDirection_of_Travel());
                log.error(lookUpData.getAsset_Number_Lane1());
                log.error(lookUpData.getAsset_Number_Lane2());
                log.error(lookUpData.getAsset_Number_Lane3());
                log.error(lookUpData.getAsset_Number_Lane4());
                i++;
            }
    }


    private static void printPictoJsonArrays(List<PictoCodeLookUpData> jsonArray){
        int j = 0;

        for (PictoCodeLookUpData pictolookUpData : jsonArray) {
            log.error("----------------------- Picto Code data Row " + j + "------------------------------");
            log.error(pictolookUpData.getSign_Description());
            log.error(pictolookUpData.getSign());
            log.error(pictolookUpData.getService_Category_Code());
            log.error(pictolookUpData.getPictogram_Code());
            log.error(pictolookUpData.getAttr_Ind_Code());
            log.error(pictolookUpData.getHORUS_Code());
            log.error(pictolookUpData.getLane_Status());
            j++;
        }
    }
}
