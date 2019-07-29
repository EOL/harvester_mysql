package org.bibalex.eol.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.globalnames.parser.ScientificNameParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amr.Morad on 4/16/2017.
 */
public class GlobalNamesHandler {

    private JSONParser parser;
    private static Logger logger = LogManager.getLogger(GlobalNamesHandler.class);

    public GlobalNamesHandler(){
        parser = new JSONParser();
    }

    private JSONObject getParsedJson(String name){
        String jsonStr = ScientificNameParser.instance()
                .fromString(name)
                .renderCompactJson();
        try {
            return (JSONObject)parser.parse(jsonStr);
        } catch (ParseException e) {
//            e.printStackTrace();
            logger.error("Parse Exception: ", e);
        }
        return null;
    }

    private boolean parseAndGetResult(String name, String attribute){
        Object att = (getParsedJson(name)).get(attribute);
        return att == null ? false : (Boolean)att;
    }

    public boolean isVirus(String name){
        return parseAndGetResult(name, "virus");
    }

    public boolean isSurrogate(String name){
        return parseAndGetResult(name, "surrogate");
    }

    public boolean isHybrid(String name){
        return parseAndGetResult(name, "hybrid");
    }

    public String getCanonicalForm(String name){
        JSONObject jsonObject = getParsedJson(name);
        return (Boolean) jsonObject.get("parsed") ? (String) ((JSONObject) jsonObject.get("canonical_name")).get("value") : "";
    }

    public boolean hasAuthority(String name){
        JSONArray nameParts = (JSONArray) getParsedJson(name).get("positions");
        if (nameParts != null) {
            for (int i = 0; i < nameParts.size(); i++) {
                JSONArray partArray = (JSONArray) nameParts.get(i);
                if (partArray.get(0).toString().contains("author")) {
//                    System.out.println("has authority");
                    logger.info("Name: " + name + " Has Authorship");
                    return true;
                }
            }
        }
        logger.info("Name: " + name + " Has No Authorship");
//        System.out.println("will return false");
        return false;
    }

    public static void main(String [] args){

//        ResourceHandler.initialize("config.properties");
//        LogHandler.initializeHandler();

//        GlobalNamesHandler gnh = new GlobalNamesHandler();
////        gnh.hasAuthority("Parus major Linnaeus, 1788");
//        System.out.println(gnh.getCanonicalForm("Parus major Linnaeus, 1788"));
//        gnh.hasAuthority("test");


//        try {
//            Date start = sdf.parse("1536850285303");
//            System.out.println(start);
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }


//        Date start_mill=new Date(Long.valueOf("1536850285303"));
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date start = null;
//        try {
//            start = formatter.parse(String.valueOf(start_mill));
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(start);
    }
}
