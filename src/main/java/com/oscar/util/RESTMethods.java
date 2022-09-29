package com.oscar.util;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RESTMethods {

    public static String getFullJsonString(String endPoint) throws Exception{
        URL object = new URL(Config.baseEndpointUrl+endPoint);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder jsonText = new StringBuilder();
        String line;
        while((line = rd.readLine()) != null){

            jsonText.append(line);
            jsonText.append(System.getProperty("line.separator"));

        }
        return jsonText.toString();

    }

    public static void postOrPutJsonToAPI(String endPoint, String method, JsonObject jsonObject)throws Exception{

        URL object = new URL(Config.baseEndpointUrl+endPoint);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod(method);

        OutputStreamWriter ow = new OutputStreamWriter(con.getOutputStream());
        ow.write(jsonObject.toString());
        ow.flush();

        HttpResponseHandler.handle(con.getResponseCode());

    }

    public static void deleteFromApi(String endPoint) throws Exception{

        URL object = new URL(Config.baseEndpointUrl+endPoint);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("DELETE");

        HttpResponseHandler.handle(con.getResponseCode());
    }

}
