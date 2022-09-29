package com.oscar.util;

import org.apache.hc.core5.http.HttpStatus;

import javax.swing.*;

public class HttpResponseHandler {

    public static void handle(int responseCode){

        switch (responseCode) {
            case HttpStatus.SC_CREATED: {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso!", 1);
            }
            break;
            case HttpStatus.SC_OK: {
                JOptionPane.showMessageDialog(null, "Operação bem sucedida!", "Sucesso!", 1);
            }
            break;
            case HttpStatus.SC_CONFLICT: {
                JOptionPane.showMessageDialog(null, "Registro já existe!", "Erro", 3);
            }
            break;
            case HttpStatus.SC_NOT_FOUND: {
                JOptionPane.showMessageDialog(null, "Registro não encontrado!", "Erro", 3);
            }
            break;
            default: {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro: (" + responseCode + ")", "Erro", 0);
            }

        }
    }

}
