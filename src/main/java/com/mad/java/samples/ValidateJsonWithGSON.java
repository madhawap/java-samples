package com.mad.java.samples;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 *
 */
public class ValidateJsonWithGSON {
    private static Logger log = Logger.getLogger(ValidateJsonWithGSON.class);
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String sampleJson = "{ \"name\":\"John\", \"age\":30, \"car\":null }\n";
        if(isJSONValid(sampleJson)){
            log.info("Valid JSON");
        } else {
            log.error("Invalid JSON");
        }
    }

    private static boolean isJSONValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}
