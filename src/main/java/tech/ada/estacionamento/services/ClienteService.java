package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.Proprietario;
import tech.ada.estacionamento.persistence.ClientePersistence;

import java.util.List;
import java.util.Optional;

public class ClienteService {

	private final List<Proprietario> proprietarios;
	private final ClientePersistence clientePersistence;
	public ClienteService(){
		clientePersistence = new ClientePersistence();
		proprietarios = clientePersistence.getAll();


	}

	public void addProprietario(Proprietario proprietario){
		proprietarios.add(proprietario);
		clientePersistence.add(proprietario);
	}



	public Optional<Proprietario> findByCPF(String cpf){
		return proprietarios.stream()
				.filter(proprietario -> proprietario.getCpf()
						.equals(cpf)).findFirst();

	}
}
