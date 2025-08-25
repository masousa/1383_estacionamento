package tech.ada.estacionamento.persistence;

import tech.ada.estacionamento.dominio.EntradaSaida;
import tech.ada.estacionamento.dominio.Veiculo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class EntradaSaidaPersistence {
	private final Path caminhoArquivo;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

	public EntradaSaidaPersistence(){
		try {
			this.caminhoArquivo = getPath();
		} catch (IOException e) {
			System.err.println("Não foi possível acessar o arquivo");
			throw new RuntimeException(e);

		}
	}

	public void add(EntradaSaida entradaSaida){
		CompletableFuture.runAsync(()->{
			String entradaSaidaFormatted = getEntradaFormatted(entradaSaida);
			CompletableFuture.runAsync(()->{
				try (BufferedWriter writer =
						     Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)){
					writeNewLine(entradaSaidaFormatted, writer);
				}catch (IOException ioException){
					System.err.println("Não foi possível salvar o arquivo");
				}
			});
		}).whenComplete((noReturn, exception) -> System.out.println("registro inserido"));
	}

	private String getEntradaFormatted(EntradaSaida entradaSaida) {
		if(Objects.isNull(entradaSaida.getDataHoraSaida())){
			return String.format("%s,%s", entradaSaida.getDataHoraEntrada()
					, entradaSaida.getVeiculo().getPlaca());
		}else{
			return String.format("%s,%s,%s", entradaSaida.getDataHoraEntrada()
					, entradaSaida.getVeiculo().getPlaca(),
					entradaSaida.getDataHoraSaida().format(dateTimeFormatter));
		}

	}

	public List<EntradaSaida> getAll() {
		File caminhoArquivoFile = caminhoArquivo.toFile();
		try( Stream<String> files = Files.lines(caminhoArquivo)) {
			return caminhoArquivoFile.length()>0? files.map(this::converter)
					.toList():new ArrayList<>();
		}catch (IOException io){
			System.err.println("Não foi possível realizar a leitura do arquivo");
			return new ArrayList<>();
		}
	}

	private EntradaSaida converter(String s) {
		Iterator<String> object = Arrays.stream(s.split(",")).iterator();
		EntradaSaida entradaSaida = new EntradaSaida();
		entradaSaida.setDataHoraEntrada(LocalDateTime.parse(object.next(),dateTimeFormatter));
		entradaSaida.setVeiculo(new Veiculo());
		entradaSaida.getVeiculo().setPlaca(object.next());
		if(object.hasNext()){
			entradaSaida.setDataHoraSaida(LocalDateTime.parse(object.next(),dateTimeFormatter));
		}
		return entradaSaida;
	}

	public void sincronize(List<EntradaSaida> entradaSaidas){
		CompletableFuture.runAsync(() -> {

			List<String> clientesFormatados = entradaSaidas.stream().map(this::getEntradaFormatted)
					.toList();
			eraseContent();
			try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)) {

				clientesFormatados
						.forEach(cliente -> writeNewLine(cliente, writer));


			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).whenComplete(( noReturn,exception)-> System.out.println("completo"));

	}

	private void eraseContent()  {
		try {
			Files.write(caminhoArquivo, new byte[0]);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeNewLine(String entradaSaida, BufferedWriter writer)  {

		try {
			writer.write(entradaSaida);
			writer.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private Path getPath() throws IOException {
		Path caminho = Paths.get("src", "main", "resources"
				, "entradas_saidas.txt");
		if(!caminho.toFile().exists()){
			caminho.toFile().createNewFile();
		}
		return caminho;
	}
}
