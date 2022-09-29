package com.oscar.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oscar.model.MarcaCalcado;
import com.oscar.util.RESTMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO {

    private final String baseDAOEndpoint = "/marca-calcado";

    public void salvarMarca(MarcaCalcado marcaCalcado) throws Exception{

        JsonObject marcaJson = new JsonObject();

        marcaJson.addProperty("descricaoMarcaCalcado", marcaCalcado.getDescricaoMarcaCalcado());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndpoint,"POST", marcaJson);


    }

    public void atualizarMarca(MarcaCalcado marcaCalcado)throws Exception{

        JsonObject marcaJson = new JsonObject();

        marcaJson.addProperty("descricaoMarcaCalcado", marcaCalcado.getDescricaoMarcaCalcado());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndpoint + "/" + marcaCalcado.getIdMarcaCalcado(), "PUT", marcaJson);

    }

    public void deletarMarca(MarcaCalcado marcaCalcado) throws Exception{

        JsonObject marcaJson = new JsonObject();

        marcaJson.addProperty("descricaoMarcaCalcado", marcaCalcado.getDescricaoMarcaCalcado());

        RESTMethods.deleteFromApi(baseDAOEndpoint + "/" + marcaCalcado.getIdMarcaCalcado());


    }

    public List<MarcaCalcado> buscarTodasAsMarcas()throws Exception{

        List<MarcaCalcado> marcaCalcadoList = new ArrayList<>();

        JsonObject marcasJson = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint+"?size=999"), JsonObject.class);

        JsonArray content = marcasJson.getAsJsonArray("content");

        content.forEach(jsonElement -> {

            JsonObject marcaJson = jsonElement.getAsJsonObject();

            MarcaCalcado marcaCalcado = new MarcaCalcado(marcaJson.get("idMarcaCalcado").getAsInt(),
                    marcaJson.get("descricaoMarcaCalcado").getAsString(),
                    LocalDateTime.parse(marcaJson.get("dataCadastroMarca").getAsString()));

            marcaCalcadoList.add(marcaCalcado);

        });

        return marcaCalcadoList;
    }

    public MarcaCalcado buscarMarcaID(int idMarcaCalcado)throws Exception{

        JsonObject marcaJson = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndpoint+"/id/" + idMarcaCalcado), JsonObject.class);

        MarcaCalcado marcaCalcado = new MarcaCalcado(marcaJson.get("idMarcaCalcado").getAsInt(),
                marcaJson.get("descricaoMarcaCalcado").getAsString(),
                LocalDateTime.parse(marcaJson.get("dataCadastroMarca").getAsString()));

        return marcaCalcado;

    }

}
