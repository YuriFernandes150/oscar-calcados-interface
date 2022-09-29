package com.oscar.DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oscar.DTO.CustomSearchParamDTO;
import com.oscar.model.*;
import com.oscar.util.RESTMethods;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalcadoDAO {

    private final String baseDAOEndPoint = "/calcado";

    public void salvarCalcado(Calcado calcado) throws Exception{

        JsonObject calcadoJson = new JsonObject();

        JsonObject marcaJson = new JsonObject();
        JsonObject categoriaJson = new JsonObject();
        JsonObject tamanhoJson = new JsonObject();
        JsonObject corJson = new JsonObject();

        marcaJson.addProperty("idMarcaCalcado", calcado.getMarcaCalcado().getIdMarcaCalcado());
        marcaJson.addProperty("descricaoMarcaCalcado", calcado.getMarcaCalcado().getDescricaoMarcaCalcado());
        marcaJson.addProperty("dataCadastroMarca", calcado.getMarcaCalcado().getDataCadastroMarca().toString());

        categoriaJson.addProperty("idCategoriaCalcado", calcado.getCategoriaCalcado().getIdCategoriaCalcado());
        categoriaJson.addProperty("descricaoCategoria", calcado.getCategoriaCalcado().getDescricaoCategoria());
        categoriaJson.addProperty("dataCadastroCategoria", calcado.getCategoriaCalcado().getDataCadastroCategoria().toString());

        tamanhoJson.addProperty("idTamanhoCalcado", calcado.getTamanhoCalcado().getIdTamanhoCalcado());
        tamanhoJson.addProperty("descricaoTamanhoCalcado", calcado.getTamanhoCalcado().getDescricaoTamanhoCalcado());
        tamanhoJson.addProperty("dataCadastroTamanho", calcado.getTamanhoCalcado().getDataCadastroTamanho().toString());

        corJson.addProperty("idCorCalcado", calcado.getCorCalcado().getIdCorCalcado());
        corJson.addProperty("descricaoCorCalcado", calcado.getCorCalcado().getDescricaoCorCalcado());
        corJson.addProperty("dataCadastroCor", calcado.getCorCalcado().getDataCadastroCor().toString());

        calcadoJson.addProperty("codCalcado", calcado.getCodCalcado());
        calcadoJson.addProperty("descricaoCalcado", calcado.getDescricaoCalcado());
        calcadoJson.addProperty("qtdEstoque", calcado.getQtdEstoque());
        calcadoJson.addProperty("precoCalcado", calcado.getPrecoCalcado());


        calcadoJson.add("corCalcado", corJson);
        calcadoJson.add("marcaCalcado", marcaJson);
        calcadoJson.add("categoriaCalcado", categoriaJson);
        calcadoJson.add("tamanhoCalcado", tamanhoJson);

        RESTMethods.postOrPutJsonToAPI(baseDAOEndPoint, "POST", calcadoJson);

    }

    public void atualizarCalcado(Calcado calcado) throws Exception{

        JsonObject calcadoJson = new JsonObject();

        JsonObject marcaJson = new JsonObject();
        JsonObject categoriaJson = new JsonObject();
        JsonObject tamanhoJson = new JsonObject();
        JsonObject corJson = new JsonObject();

        marcaJson.addProperty("idMarcaCalcado", calcado.getMarcaCalcado().getIdMarcaCalcado());
        marcaJson.addProperty("descricaoMarcaCalcado", calcado.getMarcaCalcado().getDescricaoMarcaCalcado());
        marcaJson.addProperty("dataCadastroMarca", calcado.getMarcaCalcado().getDataCadastroMarca().toString());

        categoriaJson.addProperty("idCategoriaCalcado", calcado.getCategoriaCalcado().getIdCategoriaCalcado());
        categoriaJson.addProperty("descricaoCategoria", calcado.getCategoriaCalcado().getDescricaoCategoria());
        categoriaJson.addProperty("dataCadastroCategoria", calcado.getCategoriaCalcado().getDataCadastroCategoria().toString());

        tamanhoJson.addProperty("idTamanhoCalcado", calcado.getTamanhoCalcado().getIdTamanhoCalcado());
        tamanhoJson.addProperty("descricaoTamanhoCalcado", calcado.getTamanhoCalcado().getDescricaoTamanhoCalcado());
        tamanhoJson.addProperty("dataCadastroTamanho", calcado.getTamanhoCalcado().getDataCadastroTamanho().toString());

        corJson.addProperty("idCorCalcado", calcado.getCorCalcado().getIdCorCalcado());
        corJson.addProperty("descricaoCorCalcado", calcado.getCorCalcado().getDescricaoCorCalcado());
        corJson.addProperty("dadaCadastroCor", calcado.getCorCalcado().getDataCadastroCor().toString());

        calcadoJson.addProperty("codCalcado", calcado.getCodCalcado());
        calcadoJson.addProperty("descricaoCalcado", calcado.getDescricaoCalcado());
        calcadoJson.addProperty("qtdEstoque", calcado.getQtdEstoque());
        calcadoJson.addProperty("precoCalcado", calcado.getPrecoCalcado());


        calcadoJson.add("corCalcado", corJson);
        calcadoJson.add("marcaCalcado", marcaJson);
        calcadoJson.add("categoriaCalcado", categoriaJson);
        calcadoJson.add("tamanhoCalcado", tamanhoJson);

        RESTMethods.postOrPutJsonToAPI(baseDAOEndPoint + "/" + calcado.getIdCalcado(), "PUT", calcadoJson);

    }

    public List<Calcado> buscarTodosOsCalcados(int page, int maxItems) throws Exception{

        List<Calcado> calcadoList = new ArrayList<>();

        String endPoint = baseDAOEndPoint + "?size=" + maxItems + "&page=" + page;

        System.out.println(endPoint);

        JsonObject calcadoJsonPage = new Gson().fromJson(RESTMethods.getFullJsonString(endPoint), JsonObject.class);

        JsonArray content = calcadoJsonPage.getAsJsonArray("content");
        JsonObject pageableJson = calcadoJsonPage.getAsJsonObject("pageable");

        content.forEach(jsonElement -> {

            JsonObject calcadoJson = jsonElement.getAsJsonObject();
            JsonObject categoriaJson = calcadoJson.get("categoriaCalcado").getAsJsonObject();
            JsonObject corJson = calcadoJson.get("corCalcado").getAsJsonObject();
            JsonObject marcaJson = calcadoJson.get("marcaCalcado").getAsJsonObject();
            JsonObject tamanhoJson = calcadoJson.get("tamanhoCalcado").getAsJsonObject();

            CategoriaCalcado categoriaCalcado = new CategoriaCalcado(
                    categoriaJson.get("idCategoriaCalcado").getAsInt(),
                    categoriaJson.get("descricaoCategoria").getAsString(),
                    LocalDateTime.parse(categoriaJson.get("dataCadastroCategoria").getAsString()));

            CorCalcado corCalcado = new CorCalcado(
                    corJson.get("idCorCalcado").getAsInt(),
                    corJson.get("descricaoCorCalcado").getAsString(),
                    LocalDateTime.parse(corJson.get("dataCadastroCor").getAsString()));

            MarcaCalcado marcaCalcado = new MarcaCalcado(
                    marcaJson.get("idMarcaCalcado").getAsInt(),
                    marcaJson.get("descricaoMarcaCalcado").getAsString(),
                    LocalDateTime.parse(marcaJson.get("dataCadastroMarca").getAsString()));

            TamanhoCalcado tamanhoCalcado = new TamanhoCalcado(
                    tamanhoJson.get("idTamanhoCalcado").getAsInt(),
                    tamanhoJson.get("descricaoTamanhoCalcado").getAsString(),
                    LocalDateTime.parse(tamanhoJson.get("dataCadastroTamanho").getAsString()));

            Calcado calcado =  new Calcado(
                    calcadoJson.get("idCalcado").getAsInt(),
                    calcadoJsonPage.get("totalPages").getAsInt(),
                    pageableJson.get("pageNumber").getAsInt(),
                    calcadoJsonPage.get("last").getAsBoolean(),
                    calcadoJson.get("codCalcado").getAsString(),
                    calcadoJson.get("descricaoCalcado").getAsString(),
                    calcadoJson.get("qtdEstoque").getAsDouble(),
                    LocalDateTime.parse(calcadoJson.get("dataCadastro").getAsString()),
                    calcadoJson.get("precoCalcado").getAsDouble(),
                    corCalcado,
                    marcaCalcado,
                    categoriaCalcado,
                    tamanhoCalcado
            );

            calcadoList.add(calcado);

        });

        return calcadoList;

    }

    public  List<Calcado> buscarCalcadosPorFiltro(int page, int maxItems, CustomSearchParamDTO params) throws Exception{

        List<Calcado> calcadoList = new ArrayList<>();

        String endPoint = baseDAOEndPoint + "/buscar?" + buildUrlParams(params) +  "size=" + maxItems + "&page=" + page;
        System.out.println(endPoint);
        JsonObject calcadoJsonPage = new Gson().fromJson(RESTMethods.getFullJsonString(endPoint), JsonObject.class);

        JsonArray content = calcadoJsonPage.getAsJsonArray("content");
        JsonObject pageableJson = calcadoJsonPage.getAsJsonObject("pageable");

        content.forEach(jsonElement -> {

            JsonObject calcadoJson = jsonElement.getAsJsonObject();

            JsonObject categoriaJson = calcadoJson.get("categoriaCalcado").getAsJsonObject();
            JsonObject corJson = calcadoJson.get("corCalcado").getAsJsonObject();
            JsonObject marcaJson = calcadoJson.get("marcaCalcado").getAsJsonObject();
            JsonObject tamanhoJson = calcadoJson.get("tamanhoCalcado").getAsJsonObject();

            CategoriaCalcado categoriaCalcado = new CategoriaCalcado(
                    categoriaJson.get("idCategoriaCalcado").getAsInt(),
                    categoriaJson.get("descricaoCategoria").getAsString(),
                    LocalDateTime.parse(categoriaJson.get("dataCadastroCategoria").getAsString()));

            CorCalcado corCalcado = new CorCalcado(
                    corJson.get("idCorCalcado").getAsInt(),
                    corJson.get("descricaoCorCalcado").getAsString(),
                    LocalDateTime.parse(corJson.get("dataCadastroCor").getAsString()));

            MarcaCalcado marcaCalcado = new MarcaCalcado(
                    marcaJson.get("idMarcaCalcado").getAsInt(),
                    marcaJson.get("descricaoMarcaCalcado").getAsString(),
                    LocalDateTime.parse(marcaJson.get("dataCadastroMarca").getAsString()));

            TamanhoCalcado tamanhoCalcado = new TamanhoCalcado(
                    tamanhoJson.get("idTamanhoCalcado").getAsInt(),
                    tamanhoJson.get("descricaoTamanhoCalcado").getAsString(),
                    LocalDateTime.parse(tamanhoJson.get("dataCadastroTamanho").getAsString()));

            Calcado calcado =  new Calcado(
                    calcadoJson.get("idCalcado").getAsInt(),
                    calcadoJsonPage.get("totalPages").getAsInt(),
                    pageableJson.get("pageNumber").getAsInt(),
                    calcadoJsonPage.get("last").getAsBoolean(),
                    calcadoJson.get("codCalcado").getAsString(),
                    calcadoJson.get("descricaoCalcado").getAsString(),
                    calcadoJson.get("qtdEstoque").getAsDouble(),
                    LocalDateTime.parse(calcadoJson.get("dataCadastro").getAsString()),
                    calcadoJson.get("precoCalcado").getAsDouble(),
                    corCalcado,
                    marcaCalcado,
                    categoriaCalcado,
                    tamanhoCalcado
            );

            calcadoList.add(calcado);

        });

        return calcadoList;

    }

    private String buildUrlParams(CustomSearchParamDTO params) throws Exception {

        StringBuilder urlParams = new StringBuilder();

        if(params.getDescricaoCalcado() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("descricaoCalcado=").append(params.getDescricaoCalcado());

        }
        if(params.getPrecoCalcado() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("precoCalcado=").append(params.getPrecoCalcado());
        }
        if(params.getPrecoCalcadoGT() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("precoCalcadoGT=").append(params.getPrecoCalcadoGT());
        }
        if(params.getPrecoCalcadoLT() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("precoCalcadoLT=").append(params.getPrecoCalcadoLT());
        }
        if(params.getCategoriaCalcado() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("categoriaCalcado=").append(params.getCategoriaCalcado());
        }
        if(params.getCorCalcado() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("corCalcado=").append(params.getCorCalcado());
        }
        if(params.getMarcaCalcado() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("marcaCalcado=").append(params.getMarcaCalcado());
        }
        if(params.getTamanhoCalcado() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            urlParams.append("tamanhoCalcado=").append(params.getTamanhoCalcado());
        }
        if(params.getDataCadastroGT() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            urlParams.append("dataCadastroGT=").append(sdf.format(Date.from(params.getDataCadastroGT().atZone(ZoneId.systemDefault()).toInstant())));
        }
        if(params.getDataCadastroLT() != null){
            if(urlParams.length() > 0) urlParams.append("&");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            urlParams.append("dataCadastroLT=").append(sdf.format(Date.from(params.getDataCadastroLT().atZone(ZoneId.systemDefault()).toInstant())));
        }

        if(urlParams.length() > 0) urlParams.append("&");

        return urlParams.toString();
    }

}
