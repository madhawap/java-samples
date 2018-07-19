package com.mad.java.samples;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * Created by madhawa on 7/19/18.
 */
public class ValidateJsonWithGSON {
    static Logger log = Logger.getLogger(ValidateJsonWithGSON.class);
    private static final Gson gson = new Gson();
    private static String sampleJson = "{ \"name\":\"John\", \"age\":30, \"car\":null }\n";


    public static void main(String[] args) {
        if(isJSONValid(sampleJson)){
            log.info("Valid JSON");
        } else {
            log.error("Invalid JSON");
        }
    }

    public static boolean isJSONValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}
