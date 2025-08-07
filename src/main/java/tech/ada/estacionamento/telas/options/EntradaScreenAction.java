package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.services.EntradaSaidaService;
import tech.ada.estacionamento.services.VeiculoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EntradaScreenAction implements ScreenAction {
	private final Veiculo veiculo;
	private final Scanner scanner;
	private final EntradaSaidaService entradaSaidaService;

	public EntradaScreenAction(Scanner scanner, Veiculo veiculo, EntradaSaidaService entradaSaidaService) {
		this.scanner = scanner;
		this.veiculo = veiculo;
		this.entradaSaidaService = entradaSaidaService;
	}

	@Override
	public void execute() {
		var entrada = new EntradaSaida();
		entrada.setVeiculo(veiculo);
		entrada.setDataHoraEntrada(LocalDateTime.now());
		entradaSaidaService.add(entrada);
		System.out.println("Entrada realiza com sucesso");
	}
}
