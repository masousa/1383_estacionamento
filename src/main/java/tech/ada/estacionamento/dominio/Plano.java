package tech.ada.estacionamento.dominio;

import lombok.Getter;

@Getter
public enum Plano {
    PLANO_QUARENTA("Plano 40", 45.00, 40),
    PLANO_NOVENTA("Plano 90", 85.00, 90),
    PLANO_CENTO_E_OITENTA("Plano 180", 160.00, 180);
    private String nome;
    private double valor;
    private int quantidadeHoras;

    private Plano(String nome, double valor, int quantidadeHoras){
        this.nome = nome;
        this.valor = valor;
        this.quantidadeHoras = quantidadeHoras;
    }
}
