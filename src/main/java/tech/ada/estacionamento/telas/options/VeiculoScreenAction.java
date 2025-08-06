package tech.ada.estacionamento.telas.options;

import tech.ada.estacionamento.dominio.Proprietario;
import tech.ada.estacionamento.dominio.TipoVeiculo;
import tech.ada.estacionamento.dominio.Veiculo;
import tech.ada.estacionamento.services.VeiculoService;

import java.util.Scanner;

public class VeiculoScreenAction implements ScreenAction{
	private final Scanner scanner;
	private final VeiculoService veiculoService;

	public VeiculoScreenAction(Scanner scanner, VeiculoService veiculoService, Proprietario proprietario) {
		this.scanner = scanner;
		this.veiculoService = veiculoService;
	}

	@Override
	public void execute() {
		Veiculo veiculo = new Veiculo();
		System.out.println("Informe a placa do seu veiculo");
		veiculo.setPlaca(scanner.next());
		System.out.println("Informe a marca do seu veiculo");
		veiculo.setMarca(scanner.next());
		System.out.println("Qual o tipo do seu veiculo");
		for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()){
			System.out.printf("%d - %s %n", tipoVeiculo.ordinal(), tipoVeiculo.name());
		}
		veiculo.setTipo(TipoVeiculo.getFromOrdinal(scanner.nextInt()));
		System.out.println("Informe a cor do seu veiculo");
		veiculo.setCor(scanner.next());
		veiculoService.add(veiculo);
		System.out.println("Veiculo adicionado com sucesso");
	}
}
