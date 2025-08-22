package tech.ada.estacionamento.services;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.persistence.EntradaSaidaPersistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EntradaSaidaService {

	private final List<EntradaSaida> entradaSaidaList;
	private final EntradaSaidaPersistence persistence;
	public EntradaSaidaService() {
		this.persistence = new EntradaSaidaPersistence();
		entradaSaidaList = new ArrayList<>(persistence.getAll());

	}

	public void add(EntradaSaida entradaSaida){
		entradaSaidaList.add(entradaSaida);
		persistence.add(entradaSaida);
	}

	public Optional<EntradaSaida> findByPlaca(String placaVeiculo) {
		return entradaSaidaList.stream().filter(entradaSaida ->
				Objects.isNull(entradaSaida.getDataHoraSaida()))
				.filter(entradaSaida -> entradaSaida.getVeiculo()
						.getPlaca().equals(placaVeiculo))
				.findFirst();

	}

	public Optional<EntradaSaida> sair(String placaVeiculo){
		Optional<EntradaSaida> entradaSaidaOptional = findByPlaca(placaVeiculo);
		if(entradaSaidaOptional.isPresent()){
			EntradaSaida entradaJaRealizada = entradaSaidaOptional.get();
			entradaJaRealizada.setDataHoraSaida(LocalDateTime.now());
			persistence.sincronize(entradaSaidaList);
			return Optional.of(entradaJaRealizada);
		}
		System.err.println("Veiculo não encontrado");
		return Optional.empty();
	}
}
