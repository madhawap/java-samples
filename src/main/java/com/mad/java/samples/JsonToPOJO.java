package com.mad.java.samples;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.java.samples.dataholders.LookUpData;
import com.mad.java.samples.dataholders.PictoCodeLookUpData;
import org.apache.log4j.Logger;

import java.io.Reader;
import java.util.List;

/**
 *
 */
public class JsonToPOJO {

    private static Logger log = Logger.getLogger(JsonToPOJO.class);


    public List jsonArrayToList(Reader reader) {

        Gson gson = new Gson();

        // Convert JSON to Java Object
        TypeToken<List<LookUpData>> token = new TypeToken<List<LookUpData>>() {};
        List<LookUpData> lookUpDataList = gson.fromJson(reader, token.getType());
        //printJsonArrays(lookUpDataList);
        return lookUpDataList;

    }

    public List jsonPictoArrayToList(Reader reader) {

        Gson gson1 = new Gson();

        // Convert JSON to Java Object
        TypeToken<List<PictoCodeLookUpData>> token = new TypeToken<List<PictoCodeLookUpData>>() {};
        List<PictoCodeLookUpData> pictolookUpDataList = gson1.fromJson(reader, token.getType());
        //printPictoJsonArrays(pictolookUpDataList);
        return pictolookUpDataList;

    }

    private static void printJsonArrays(List<LookUpData> jsonArray) {
        int i = 0;

        for (LookUpData lookUpData : jsonArray) {
            log.error("----------------------- Look up table Row " + i + "----------------------------");
            log.error(lookUpData.getGantryLatitude());
            log.error(lookUpData.getGantryLongitude());
            log.error(lookUpData.getGantryNumber());
            log.error(lookUpData.getZone1Latitude());
            log.error(lookUpData.getZone1Longitude());
            log.error(lookUpData.getZone2Longitude());
            log.error(lookUpData.getZone2Latitude());
            log.error(lookUpData.getZone1DeltaLatitude());
            log.error(lookUpData.getZone1DeltaLongitude());
            log.error(lookUpData.getHeading());
            log.error(lookUpData.getZone2DeltaLatitude());
            log.error(lookUpData.getZone2DeltaLongitude());
            log.error(lookUpData.getHeading2());
            log.error(lookUpData.getDirectionOfTravel());
            log.error(lookUpData.getAssetNumberLane1());
            log.error(lookUpData.getAssetNumberLane2());
            log.error(lookUpData.getAssetNumberLane3());
            log.error(lookUpData.getAssetNumberLane4());
            i++;
        }
    }

    private static void printPictoJsonArrays(List<PictoCodeLookUpData> jsonArray) {
        int j = 0;

        for (PictoCodeLookUpData pictolookUpData : jsonArray) {
            log.error("----------------------- Picto Code data Row " + j + "------------------------------");
            log.error(pictolookUpData.getSignDescription());
            log.error(pictolookUpData.getSign());
            log.error(pictolookUpData.getServiceCategoryCode());
            log.error(pictolookUpData.getPictogramCode());
            log.error(pictolookUpData.getAttrIndCode());
            log.error(pictolookUpData.getHorusCode());
            log.error(pictolookUpData.getLaneStatus());
            j++;
        }
    }
}
