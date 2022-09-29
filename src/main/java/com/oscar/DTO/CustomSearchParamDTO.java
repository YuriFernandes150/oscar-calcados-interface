package com.oscar.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomSearchParamDTO {

    private String descricaoCalcado;
    private String tamanhoCalcado;
    private String marcaCalcado;
    private String categoriaCalcado;
    private String corCalcado;
    private Double precoCalcado;
    private Double precoCalcadoGT;
    private Double precoCalcadoLT;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataCadastroGT;
    private LocalDateTime dataCadastroLT;

}