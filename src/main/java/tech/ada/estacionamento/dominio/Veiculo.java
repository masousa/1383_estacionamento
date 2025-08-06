package tech.ada.estacionamento.dominio;

import lombok.Data;

@Data
public class Veiculo {
    private String placa;
    private TipoVeiculo tipo;
    private String cor;
    private String marca;
}
