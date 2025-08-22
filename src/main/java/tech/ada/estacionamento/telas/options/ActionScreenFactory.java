package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.services.ClienteService;
import tech.ada.estacionamento.services.EntradaSaidaService;
import tech.ada.estacionamento.services.VeiculoService;
import tech.ada.estacionamento.telas.GetProprietarioScreen;
import tech.ada.estacionamento.telas.GetVeiculoScreen;
import tech.ada.estacionamento.telas.OpcaoTela;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ActionScreenFactory {

	private final ClienteService clienteService;
	private final VeiculoService veiculoService;
	private final EntradaSaidaService entradaSaidaService;
	private final Scanner scanner;
	public ActionScreenFactory(final Scanner scanner){
		this.clienteService = new ClienteService();
		this.veiculoService = new VeiculoService();
		this.entradaSaidaService = new EntradaSaidaService();
		this.scanner = scanner;
	}

	public void getCorrectScreen(OpcaoTela opcaoTela){
		ScreenAction screenAction = null;

		switch (opcaoTela){
			case CADASTRAR_CLIENTE -> screenAction = new ClienteScreenAction(clienteService, scanner);
			case CADASTRAR_VEICULO -> screenAction =  new VeiculoScreenAction(scanner, veiculoService,
					new GetProprietarioScreen(clienteService, scanner).getProprietario());
			case REGISTRAR_ENTRADA -> screenAction = new EntradaScreenAction(scanner,
					new GetVeiculoScreen(scanner,veiculoService).getVeiculo(), entradaSaidaService);

			case REGISTRAR_SAIDA -> screenAction= this::registrarSaida;
			case VER_PARCIAL -> screenAction = this::getParcial;

			default -> screenAction = () -> System.out.println("Obrigado por utilizar nossos sistemas");
		}

		screenAction.execute();
	}



	private void registrarSaida() {
		System.out.println("Informar a placa do  veiculo");
		String placaVeiculo = scanner.next();
		entradaSaidaService.sair(placaVeiculo).ifPresent(entradaSaida ->

		    System.out.println(String.format("Veiculo %s Teve permanência de %d horas",
				    entradaSaida.getVeiculo().getPlaca(),
				    ChronoUnit.HOURS.between(entradaSaida.getDataHoraEntrada()
						    ,entradaSaida.getDataHoraSaida())))
		);
	}

	private void getParcial() {
		System.out.println("Informar a placa do  veiculo");
		entradaSaidaService.findByPlaca(scanner.next())
				.ifPresentOrElse(entradaSaida -> {
					System.out.println(String.format("Veiculo de placa %s está estacionado desde %s. %n" +
							"Estando portando estacionado por %d horas", entradaSaida.getVeiculo().getPlaca(),
							entradaSaida.getDataHoraEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
									ChronoUnit.HOURS.between(entradaSaida.getDataHoraEntrada()
											, LocalDateTime.now())));
				}, ()-> System.err.println("Veiculo não encontrado"));
	}
}
