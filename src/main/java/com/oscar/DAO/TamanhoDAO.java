package com.oscar.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oscar.model.TamanhoCalcado;
import com.oscar.util.RESTMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TamanhoDAO {

    final String baseDAOEndpoint = "/tamanho-calcado";

    public void SalvarTamanho(TamanhoCalcado tamanhoCalcado)throws Exception{


        JsonObject tamanhoJSON = new JsonObject();

        tamanhoJSON.addProperty("descricaoTamanhoCalcado",tamanhoCalcado.getDescricaoTamanhoCalcado());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndpoint, "POST", tamanhoJSON);

    }

    public void atualizarTamanhoCalcado(TamanhoCalcado tamanhoCalcado)throws Exception{

        JsonObject tamanhoJSON = new JsonObject();

        tamanhoJSON.addProperty("descricaoTamanhoCalcado",tamanhoCalcado.getDescricaoTamanhoCalcado());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndpoint + "/" + tamanhoCalcado.getIdTamanhoCalcado().toString(), "PUT", tamanhoJSON);

    }

    public List<TamanhoCalcado> buscarTodosOsTamanhos() throws Exception{

        List<TamanhoCalcado> tamanhoCalcadoList = new ArrayList<>();

        JsonObject rootObject = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint+"?size=999"), JsonObject.class);

        JsonArray content = rootObject.getAsJsonArray("content");

        content.forEach(jsonElement -> {

            JsonObject tamanhoElement = jsonElement.getAsJsonObject();

            TamanhoCalcado t = new TamanhoCalcado(
                    tamanhoElement.get("idTamanhoCalcado").getAsInt(),
                    tamanhoElement.get("descricaoTamanhoCalcado").getAsString(),
                    LocalDateTime.parse(tamanhoElement.get("dataCadastroTamanho").getAsString()));

            tamanhoCalcadoList.add(t);

        });

        return tamanhoCalcadoList;

    }

    public List<TamanhoCalcado> buscarTodosOsTamanhosDescricao(String descricaoTamanho) throws Exception{

        List<TamanhoCalcado> tamanhoCalcadoList = new ArrayList<>();

        JsonArray content = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint + "/descricao/" + descricaoTamanho), JsonArray.class);

        if(!content.isEmpty()){
            content.forEach(jsonElement -> {

                JsonObject tamanhoElement = jsonElement.getAsJsonObject();

                TamanhoCalcado t = new TamanhoCalcado(
                        tamanhoElement.get("idTamanhoCalcado").getAsInt(),
                        tamanhoElement.get("descricaoTamanhoCalcado").getAsString(),
                        LocalDateTime.parse(tamanhoElement.get("dataCadastroTamanho").getAsString()));

                tamanhoCalcadoList.add(t);

            });
        }

        System.out.println(tamanhoCalcadoList);
        return tamanhoCalcadoList;

    }

    public TamanhoCalcado buscarTamanhoID(int idTamanhoCalcado) throws Exception{

        JsonObject rootObject = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint + "/id/" + idTamanhoCalcado), JsonObject.class);

        TamanhoCalcado tamanhoCalcado = new TamanhoCalcado(rootObject.get("idTamanhoCalcado").getAsInt(),
                rootObject.get("descricaoTamanhoCalcado").getAsString(),
                LocalDateTime.parse(rootObject.get("dataCadastroTamanho").getAsString()));

        return tamanhoCalcado;

    }

    public void deletarTamanhoCalcado(TamanhoCalcado tamanhoCalcado) throws Exception{
        RESTMethods.deleteFromApi(baseDAOEndpoint + "/" + tamanhoCalcado.getIdTamanhoCalcado());
    }

}
