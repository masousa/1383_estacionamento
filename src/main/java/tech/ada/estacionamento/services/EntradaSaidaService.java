package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.EntradaSaida;

import java.util.ArrayList;
import java.util.List;

public class EntradaSaidaService {

	private final List<EntradaSaida> entradaSaidaList;

	public EntradaSaidaService(){
		this.entradaSaidaList = new ArrayList<>();
	}

	public void add(EntradaSaida entradaSaida){
		entradaSaidaList.add(entradaSaida);
	}

	public EntradaSaida findByPlaca(String placaVeiculo) {
		for (EntradaSaida entradaSaida : entradaSaidaList){
			if(entradaSaida.getVeiculo().getPlaca().equals(placaVeiculo)){
				return entradaSaida;
			}
		}
		throw new RuntimeException("Entrada não encontrada");
	}
}
