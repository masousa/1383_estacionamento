package tech.ada.estacionamento.dominio;

import lombok.Data;

import java.io.Serializable;

@Data
public class Proprietario implements Serializable {
    private String cpf;
    private String nome;
}
