package com.oscar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MarcaCalcado {

    private Integer idMarcaCalcado;

    private String descricaoMarcaCalcado;

    private LocalDateTime dataCadastroMarca;

    @Override
    public String toString() {
        return descricaoMarcaCalcado;
    }
}
