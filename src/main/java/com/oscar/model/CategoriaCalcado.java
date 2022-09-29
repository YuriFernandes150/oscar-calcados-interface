package com.oscar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaCalcado {

    private static final long serialVersionUID = 2L;


    private Integer idCategoriaCalcado;

    private String descricaoCategoria;

    private LocalDateTime dataCadastroCategoria;

    @Override
    public String toString() {
        return descricaoCategoria;
    }
}
