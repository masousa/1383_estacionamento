package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.services.ClienteService;
import tech.ada.estacionamento.services.VeiculoService;
import tech.ada.estacionamento.telas.OpcaoTela;
import tech.ada.estacionamento.telas.getProprietarioScreen;

import java.util.Scanner;

public class ActionScreenFactory {

	private final ClienteService clienteService;
	private final VeiculoService veiculoService;
	private final Scanner scanner;
	public ActionScreenFactory(final Scanner scanner){
		this.clienteService = new ClienteService();
		this.veiculoService = new VeiculoService();
		this.scanner = scanner;
	}

	public void getCorrectScreen(OpcaoTela opcaoTela){
		ScreenAction screenAction = null;
		switch (opcaoTela){
			case CADASTRAR_CLIENTE -> screenAction = new ClienteScreenAction(clienteService, scanner);
			case CADASTRAR_VEICULO -> screenAction =  new VeiculoScreenAction(scanner, veiculoService,
					new getProprietarioScreen(clienteService, scanner).getProprietario());
			case REGISTRAR_ENTRADA -> screenAction = new EntradaScreenAction(scanner, veiculoService);
			case REGISTRAR_SAIDA -> screenAction = new SaidaScreenAction(scanner, veiculoService);

			default -> screenAction = new ExitScreenAction();
		}
		screenAction.execute();
	}
}
