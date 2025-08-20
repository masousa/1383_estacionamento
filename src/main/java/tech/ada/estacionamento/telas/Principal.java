package tech.ada.estacionamento.telas;

import tech.ada.estacionamento.telas.options.ActionScreenFactory;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {

		//Scanner leitorEntrada = new Scanner(System.in);
		//try {
			Scanner leitorEntrada = new Scanner(System.in);
			//Scanner leitorEntrada = new Scanner(getInput().toFile());
			var actionScreenFactory = new ActionScreenFactory(leitorEntrada);
			int option = 0;
			do {
				option = imprimirOpcoes(leitorEntrada, actionScreenFactory);

			} while (option != OpcaoTela.SAIR.getOpcao());
		/*} catch (FileNotFoundException fne) {
			throw new RuntimeException(fne);
		}*/

	}

	private static int imprimirOpcoes(Scanner scanner, ActionScreenFactory actionScreenFactory) {

		System.out.println("---- Estacionamento ----");
		System.out.println("O que gostarias de fazer?");
		for (var opcaoTela:OpcaoTela.values()){
			System.out.println(String.format("%d - %s",
					opcaoTela.getOpcao(), opcaoTela.getDescricao()));
		}
		int option = scanner.nextInt();
		actionScreenFactory.getCorrectScreen(OpcaoTela.getFromOption(option));
		return option;

	}

	private static Path getInput(){
		return Paths.get("src", "main", "resources","input.entry").toAbsolutePath();
	}

}
