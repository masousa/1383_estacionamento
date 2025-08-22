package tech.ada.estacionamento.telas;

import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.services.VeiculoService;

import java.util.Optional;
import java.util.Scanner;

public class GetVeiculoScreen {
	private final VeiculoService veiculoService;
	private final Scanner scanner;
	
	public GetVeiculoScreen(Scanner scanner, VeiculoService veiculoService){
		this.veiculoService = veiculoService;
		this.scanner = scanner;
	}

	public Veiculo getVeiculo(){
		System.out.println("Informe a placa do veículo");
		var placa = scanner.next();
		Optional<Veiculo> optionalVeiculo= veiculoService.findByPlaca(placa);
		if(optionalVeiculo.isPresent()){
			return optionalVeiculo.get();
		} else{
			System.err.println("Veiculo não encontrado");
			return getVeiculo();
		}
	}


}
