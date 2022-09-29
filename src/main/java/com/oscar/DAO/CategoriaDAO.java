package com.oscar.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oscar.model.CategoriaCalcado;
import com.oscar.util.RESTMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final String baseDAOEndPoint = "/categoria-calcado";


    public void salvarCategoria(CategoriaCalcado categoriaCalcado) throws Exception{

        JsonObject categoriaJson = new JsonObject();

        categoriaJson.addProperty("descricaoCategoria", categoriaCalcado.getDescricaoCategoria());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndPoint,"POST", categoriaJson);


    }

    public void atualizarCategoria(CategoriaCalcado categoriaCalcado)throws Exception{

        JsonObject categoriaJson = new JsonObject();

        categoriaJson.addProperty("descricaoCategoria", categoriaCalcado.getDescricaoCategoria());

        RESTMethods.postOrPutJsonToAPI(baseDAOEndPoint + "/" + categoriaCalcado.getIdCategoriaCalcado(), "PUT", categoriaJson);

    }

    public void deletarCategoria(CategoriaCalcado categoriaCalcado) throws Exception{

        JsonObject categoriaJson = new JsonObject();

        categoriaJson.addProperty("descricaoCategoria", categoriaCalcado.getDescricaoCategoria());

        RESTMethods.deleteFromApi(baseDAOEndPoint + "/" + categoriaCalcado.getIdCategoriaCalcado());


    }

    public List<CategoriaCalcado> buscarTodasAsCategorias()throws Exception{

        List<CategoriaCalcado> categoriaCalcadoList = new ArrayList<>();

        JsonObject categoriasJson = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndPoint +"?size=999"), JsonObject.class);

        JsonArray content = categoriasJson.getAsJsonArray("content");

        content.forEach(jsonElement -> {

            JsonObject categoriaJson = jsonElement.getAsJsonObject();

            CategoriaCalcado categoriaCalcado = null;

                categoriaCalcado = new CategoriaCalcado(categoriaJson.get("idCategoriaCalcado").getAsInt(),
                        categoriaJson.get("descricaoCategoria").getAsString(),
                        LocalDateTime.parse(categoriaJson.get("dataCadastroCategoria").getAsString()));
                categoriaCalcadoList.add(categoriaCalcado);




        });

        return categoriaCalcadoList;
    }

    public CategoriaCalcado buscarCategoriaID(int idCategoriaCalcado)throws Exception{

        JsonObject categoriaJson = new Gson().fromJson(RESTMethods.getFullJsonString(baseDAOEndPoint +"/id/" + idCategoriaCalcado), JsonObject.class);

        CategoriaCalcado categoriaCalcado = new CategoriaCalcado(categoriaJson.get("idCategoriaCalcado").getAsInt(),
                categoriaJson.get("descricaoCategoria").getAsString(),
                LocalDateTime.parse(categoriaJson.get("dataCadastroCategoria").getAsString()));

        return categoriaCalcado;

    }

}
