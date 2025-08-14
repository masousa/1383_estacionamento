package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class VeiculoService {
	private final List<Veiculo> veiculoList;

	public VeiculoService(){
		this.veiculoList = new ArrayList<>();
	}

	public void add(Veiculo veiculo){
		veiculoList.add(veiculo);
	}

	public Veiculo findByPlaca(String numeroPlaca){
		Optional<Veiculo> first = veiculoList.stream().filter(carro -> carro.getPlaca().equals(numeroPlaca))
				.findFirst();

		return first.orElseThrow();


	}
}
