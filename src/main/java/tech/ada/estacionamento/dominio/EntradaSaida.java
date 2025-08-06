package tech.ada.estacionamento.dominio;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntradaSaida {
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Veiculo veiculo;

}
