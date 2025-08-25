package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.persistence.VeiculoPersistence;

import java.util.List;
import java.util.Optional;

public class VeiculoService {
	private final List<Veiculo> veiculoList;
	private final VeiculoPersistence veiculoPersistence;
	public VeiculoService(){
		this.veiculoPersistence = new VeiculoPersistence();
		this.veiculoList = veiculoPersistence.getAll();
	}

	public void add(Veiculo veiculo){
		veiculoList.add(veiculo);
		veiculoPersistence.add(veiculo);
	}

	public Optional<Veiculo> findByPlaca(String numeroPlaca){
		return veiculoList.stream().filter(carro -> carro.getPlaca()
						.equals(numeroPlaca)).findFirst();
	}
}
