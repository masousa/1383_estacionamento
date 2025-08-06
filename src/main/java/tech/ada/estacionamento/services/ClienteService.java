package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.Proprietario;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

	private final List<Proprietario> proprietarios;

	public ClienteService(){
		proprietarios = new ArrayList<>();
	}

	public void addProprietario(Proprietario proprietario){
		proprietarios.add(proprietario);
	}

	public Proprietario findByCPF(String cpf){
		for (Proprietario proprietario : proprietarios){
			if(proprietario.getCpf().equals(cpf)){
				return proprietario;
			}

		}
		throw new RuntimeException("cpf não encontrado");
	}
}
