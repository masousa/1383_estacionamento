package tech.ada.estacionamento.telas;

import tech.ada.estacionamento.dominio.Proprietario;
import tech.ada.estacionamento.services.ClienteService;

import java.util.Scanner;

public class getProprietarioScreen {
	private final ClienteService clienteService;
	private final Scanner scanner;
	public getProprietarioScreen(ClienteService clienteService, Scanner scanner) {
		this.clienteService = clienteService;
		this.scanner = scanner;
	}

	public Proprietario getProprietario() {
		System.out.println("Informe o cpf do proprietário");
		String cpf = scanner.next();
		return clienteService.findByCPF(cpf);
	}
}
