package com.mad.java.samples;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class ValidateJson {

    private static Logger log = Logger.getLogger(ValidateJsonWithGSON.class);

    public static void main(String[] args) {
        String sampleJson = "{ \"name\":\"John\", \"age\":30, \"car\":null }\n";
        if (isJSONValid(sampleJson)) {
            log.info("Valid JSON");
        } else {
            log.error("Invalid JSON");
        }
    }

    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            //in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
