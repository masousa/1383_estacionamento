package tech.ada.estacionamento.telas;

import tech.ada.estacionamento.telas.options.ActionScreenFactory;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {

		Scanner leitorEntrada = new Scanner(System.in);
		var actionScreenFactory = new ActionScreenFactory(leitorEntrada);
		int option = 0;
		do{
			option = imprimirOpcoes(leitorEntrada,actionScreenFactory);

		}while(option!=OpcaoTela.SAIR.getOpcao());

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
}
