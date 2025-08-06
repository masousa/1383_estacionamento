package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.services.VeiculoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EntradaScreenAction implements ScreenAction {
	private final VeiculoService veiculoService;
	private final Scanner scanner;

	public EntradaScreenAction(Scanner scanner, VeiculoService veiculoService) {
		this.scanner = scanner;
		this.veiculoService = veiculoService;
	}

	@Override
	public void execute() {
		var entradaSaida = new EntradaSaida();
		System.out.println("Informe a placa do veículo");
		Veiculo veiculo = veiculoService.findByPlaca(scanner.next());
		entradaSaida.setVeiculo(veiculo);
		entradaSaida.setDataHoraEntrada(LocalDateTime.now());
		System.out.println("Entrada realiza com sucesso");
	}
}
