package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.Proprietario;
import tech.ada.estacionamento.services.ClienteService;

import java.util.Scanner;

public class ClienteScreenAction implements ScreenAction {

	private final ClienteService clienteService;
	private final Scanner scanner;
	public ClienteScreenAction(final ClienteService clienteService, Scanner scanner){
		this.clienteService = clienteService;
		this.scanner = scanner;
	}

	@Override
	public void execute() {
		System.out.println("Informe o CPF");
		var proprietario = new Proprietario();
		proprietario.setCpf(scanner.next());
		System.out.println("Informe o seu nome");
		proprietario.setNome(scanner.next());
		clienteService.addProprietario(proprietario);
		System.out.println("Cliente adicionado com sucesso");
	}
}
