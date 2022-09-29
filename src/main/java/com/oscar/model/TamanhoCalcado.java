package com.oscar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TamanhoCalcado implements Serializable {
    private static final long serialVersionUID = 5L;

    private Integer idTamanhoCalcado;

    private String descricaoTamanhoCalcado;

    private LocalDateTime dataCadastroTamanho;

    @Override
    public String toString() {
        return descricaoTamanhoCalcado;
    }
}
