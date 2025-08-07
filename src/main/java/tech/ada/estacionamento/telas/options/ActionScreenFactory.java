package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.services.ClienteService;
import tech.ada.estacionamento.services.EntradaSaidaService;
import tech.ada.estacionamento.services.VeiculoService;
import tech.ada.estacionamento.telas.GetVeiculoScreen;
import tech.ada.estacionamento.telas.OpcaoTela;
import tech.ada.estacionamento.telas.GetProprietarioScreen;

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
			case REGISTRAR_SAIDA -> screenAction = new SaidaScreenAction(scanner, entradaSaidaService);

			default -> screenAction = new ExitScreenAction();
		}
		screenAction.execute();
	}
}
