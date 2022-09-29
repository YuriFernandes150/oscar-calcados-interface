package com.oscar.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oscar.model.CorCalcado;
import com.oscar.util.RESTMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CorDAO {

    private final String baseDAOEndpoint = "/cor-calcado";

    public void salvarCor(CorCalcado corCalcado) throws Exception{

        JsonObject corJSON = new JsonObject();

        corJSON.addProperty("descricaoCorCalcado", corCalcado.getDescricaoCorCalcado());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndpoint,"POST", corJSON);


    }

    public void atualizarCor(CorCalcado corCalcado)throws Exception{

        JsonObject corJsonObject = new JsonObject();

        corJsonObject.addProperty("descricaoCorCalcado", corCalcado.getDescricaoCorCalcado());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndpoint + "/" + corCalcado.getIdCorCalcado(), "PUT", corJsonObject);

    }

    public void deletarCor(CorCalcado corCalcado) throws Exception{

        JsonObject corJsonObject = new JsonObject();

        corJsonObject.addProperty("descricaoCorCalcado", corCalcado.getDescricaoCorCalcado());

        RESTMethods.deleteFromApi(baseDAOEndpoint + "/" + corCalcado.getIdCorCalcado());


    }

    public List<CorCalcado> buscarTodasAsCores()throws Exception{

        List<CorCalcado> corCalcadoList = new ArrayList<>();

        JsonObject coresJson = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint+"?size=999"), JsonObject.class);

        JsonArray content = coresJson.getAsJsonArray("content");

        content.forEach(jsonElement -> {

            JsonObject corJson = jsonElement.getAsJsonObject();

            CorCalcado corCalcado = null;

                corCalcado = new CorCalcado(corJson.get("idCorCalcado").getAsInt(),
                        corJson.get("descricaoCorCalcado").getAsString(),
                        LocalDateTime.parse(corJson.get("dataCadastroCor").getAsString()));

                corCalcadoList.add(corCalcado);


        });

        return corCalcadoList;
    }

    public CorCalcado buscarCorID(int idCorCalcado)throws Exception{

        JsonObject corJson = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint+"/id/" + idCorCalcado), JsonObject.class);

        CorCalcado corCalcado = new CorCalcado(corJson.get("idCorCalcado").getAsInt(),
                corJson.get("descricaoCorCalcado").getAsString(),
                LocalDateTime.parse(corJson.get("dataCadastroCor").getAsString()));

        return corCalcado;

    }



}
