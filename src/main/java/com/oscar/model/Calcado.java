package com.oscar.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calcado {

    private Integer idCalcado;

    private int pageCount;

    private int curPage;

    private boolean lastPage;

    private String codCalcado;

    private String descricaoCalcado;

    private Double qtdEstoque;

    private LocalDateTime dataCadastro;

    private Double precoCalcado;

    private CorCalcado corCalcado;

    private MarcaCalcado marcaCalcado;

    private CategoriaCalcado categoriaCalcado;

    private TamanhoCalcado tamanhoCalcado;

    @Override
    public String toString() {
        return descricaoCalcado + " " + tamanhoCalcado + " " + marcaCalcado + " " + corCalcado;
    }
}
