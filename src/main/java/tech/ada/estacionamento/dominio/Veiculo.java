package tech.ada.estacionamento.dominio;

import lombok.Data;

import java.io.Serializable;

@Data
public class Veiculo implements Serializable {

    private String placa;
    private TipoVeiculo tipo;
    private String cor;
    private String marca;
	private Proprietario proprietario;
}
