package com.oscar.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;

public class Config {

    public static String baseEndpointUrl;
    public static String osName;
    public static boolean localApi;
    public static File rootDir;
    public static File apiJarLocation;

    public static boolean setupConfig() throws Exception{

        osName = System.getProperty("os.name").toLowerCase();

        File currentJar = new File(Config.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath());
        rootDir = new File(currentJar.getParent());

        File configFile = new File(rootDir.getPath() + File.separator + "config.json");

        if (configFile.exists()) {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = g.fromJson(new FileReader(configFile), JsonObject.class);
            baseEndpointUrl = json.get("base-endpoint-url").toString().replace("\"", "").trim();
            apiJarLocation = new File(json.get("api-jar-location").toString().replace("\"", "").trim());
            localApi = Boolean.parseBoolean(json.get("local-api").toString().replace("\"", "").trim());
            return true;
        }else{
            return false;
        }

    }


}
