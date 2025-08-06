package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoService {
	private final List<Veiculo> veiculoList;

	public VeiculoService(){
		this.veiculoList = new ArrayList<>();
	}

	public void add(Veiculo veiculo){
		veiculoList.add(veiculo);
	}

	public Veiculo findByPlaca(String numeroPlaca){
		for (Veiculo veiculo: veiculoList){
			if(veiculo.getPlaca().equals(numeroPlaca)){
				return veiculo;
			}
		}
		throw new RuntimeException("Não há veiculos cadastrados");
	}
}
