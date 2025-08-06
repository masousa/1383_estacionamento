package tech.ada.estacionamento.telas;

import lombok.Getter;

@Getter
public enum OpcaoTela {

	CADASTRAR_CLIENTE(1, "Cadastrar Cliente"),
	CADASTRAR_VEICULO(2, "Cadastrar novo veiculo"),
	REGISTRAR_ENTRADA(3, "Registrar nova entrada de veículo"),
	REGISTRAR_SAIDA(4, "Registrar saida de veiculo"),
	VER_PARCIAL(5, "Ver parcial"),
	SAIR(0,"Sair");

	private int opcao;
	private String descricao;

	private OpcaoTela(int opcao, String descricao){
		this.descricao = descricao;
		this.opcao = opcao;
	}

	public static OpcaoTela getFromOption(int option) {
		for(OpcaoTela ot : OpcaoTela.values()){
			if(ot.getOpcao() == option){
				return ot;
			}
		}
		throw new RuntimeException("Não há opção informada");
	}
}
