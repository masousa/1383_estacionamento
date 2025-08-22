package tech.ada.estacionamento.telas;

import tech.ada.estacionamento.dominio.Proprietario;
import tech.ada.estacionamento.services.ClienteService;

import java.util.Optional;
import java.util.Scanner;

public class GetProprietarioScreen {
	private final ClienteService clienteService;
	private final Scanner scanner;
	public GetProprietarioScreen(ClienteService clienteService, Scanner scanner) {
		this.clienteService = clienteService;
		this.scanner = scanner;
	}

	public Proprietario getProprietario() {
		System.out.println("Informe o cpf do proprietário");
		String cpf = scanner.next();
		Optional<Proprietario> proprietarioOptional =  clienteService.findByCPF(cpf);
		if(proprietarioOptional.isPresent()){
			return proprietarioOptional.get();
		}
		System.err.println("Cliente não encontrado");
		return getProprietario();
	}
}
