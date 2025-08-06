package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.services.VeiculoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SaidaScreenAction implements ScreenAction {
	private final Scanner scanner;
	private final VeiculoService veiculoService;
	public SaidaScreenAction(Scanner scanner, VeiculoService veiculoService) {
		this.scanner = scanner;
		this.veiculoService = veiculoService;
	}

	@Override
	public void execute() {
		System.out.println("Informar a placa do veiculo");
		String placaVeiculo = scanner.next();
		Veiculo veiculo = veiculoService.findByPlaca(placaVeiculo);
		EntradaSaida entradaSaida = new EntradaSaida();
		entradaSaida.setVeiculo(veiculo);
		entradaSaida.setDataHoraEntrada(LocalDateTime.now());
	}
}
