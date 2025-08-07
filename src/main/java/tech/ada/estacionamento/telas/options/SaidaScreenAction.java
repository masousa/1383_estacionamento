package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.services.EntradaSaidaService;
import tech.ada.estacionamento.services.VeiculoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SaidaScreenAction implements ScreenAction {
	private final Scanner scanner;
	private final EntradaSaidaService entradaSaidaService;
	public SaidaScreenAction(Scanner scanner, EntradaSaidaService entradaSaidaService) {
		this.scanner = scanner;
		this.entradaSaidaService = entradaSaidaService;
	}

	@Override
	public void execute() {
		System.out.println("Informar a placa do  veiculo");
		String placaVeiculo = scanner.next();

		EntradaSaida saida = entradaSaidaService.findByPlaca(placaVeiculo);
		saida.setDataHoraSaida(LocalDateTime.now());

	}
}
