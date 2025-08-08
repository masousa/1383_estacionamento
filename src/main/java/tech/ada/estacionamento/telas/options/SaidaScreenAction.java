package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.services.EntradaSaidaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
		LocalDateTime horaEntrada = saida.getDataHoraEntrada();
		LocalDateTime horaSaida = saida.getDataHoraSaida();
		long horas = ChronoUnit.HOURS.between(horaEntrada,horaSaida);
		DateTimeFormatter isoDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.printf("Veículo de placa %s entrou as %s e saiu as %s ficando estacionado por %d hora(s) %n"
				, saida.getVeiculo().getPlaca(),horaEntrada.format(isoDateTime),
				horaSaida.format(isoDateTime),  horas);
	}
}
