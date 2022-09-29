package com.oscar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalApiManagement {

    public static void startApi() throws  IOException{

        Runtime runtime = Runtime.getRuntime();

        Process runApi = runtime.exec("java -jar " + Config.apiJarLocation);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(runApi.getInputStream()));

        BufferedReader errors = new BufferedReader(new
                InputStreamReader(runApi.getInputStream()));

        String s = null;
        while (!(s = stdInput.readLine()).contains(": Started OscarCalcadosTestApplication")) {
            System.out.println(s);
        }

        Thread errorCatcher = new Thread(() -> {
            try {
                String e = null;
                while ((e = stdInput.readLine()) != null) {
                    System.out.println(e);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        errorCatcher.start();


        Thread stopApi = new Thread(() ->{

            if(runApi.isAlive()){
                runApi.destroyForcibly();
            }

        });
        runtime.addShutdownHook(stopApi);

    }

}
