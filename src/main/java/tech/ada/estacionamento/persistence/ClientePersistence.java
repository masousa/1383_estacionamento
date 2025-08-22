package tech.ada.estacionamento.persistence;

import tech.ada.estacionamento.dominio.Proprietario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ClientePersistence {
	private final Path caminhoArquivo;
	public ClientePersistence(){
		try {
			caminhoArquivo = getPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Proprietario proprietario){
		String proprietarioFormatted = getClientFormatted(proprietario);
		try (BufferedWriter writer =
				Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)){
			writer.write(proprietarioFormatted);
			writer.newLine();
		}catch (IOException ioException){
			throw new RuntimeException();
		}
	}

	public List<Proprietario> getAll(){


		 /*return Files.lines(caminhoArquivo).map(this::converter)
				 .toList();*/

		List<Proprietario> proprietarios = new ArrayList<>();
		/*return Files.lines(caminhoArquivo).map(this::converter)
				.forEachOrdered(proprietarios::add);*/

		try(BufferedReader reader = Files.newBufferedReader(caminhoArquivo)){
			String linha;

			while((linha= reader.readLine()) != null){
				proprietarios.add(converter(linha));

			}
		}catch (IOException ioException){
			throw new RuntimeException();
		}
		return proprietarios;
	}

	private Proprietario converter(String linha) {
		var proprietario = new Proprietario();
		List<String> strings = Arrays.asList(linha.split(","));
		Iterator<String> iterator = strings.iterator();
		proprietario.setCpf(iterator.next());
		proprietario.setNome(iterator.next());
		return proprietario;
	}

	private Path getPath() throws IOException {
		Path caminho = Paths.get("src", "main", "resources"
				, "proprietarios.txt");
		if(!caminho.toFile().exists()){
			caminho.toFile().createNewFile();
		}
		return caminho;
	}

	private String getClientFormatted(Proprietario proprietario){
		return String.format("%s,%s", proprietario.getCpf(), proprietario.getNome());
	}
}
