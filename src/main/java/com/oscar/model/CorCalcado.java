package com.oscar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CorCalcado {

    private Integer idCorCalcado;

    private String descricaoCorCalcado;

    private LocalDateTime dataCadastroCor;

    @Override
    public String toString() {
        return descricaoCorCalcado;
    }
}
