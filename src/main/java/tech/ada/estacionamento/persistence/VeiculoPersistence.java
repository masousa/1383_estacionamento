package tech.ada.estacionamento.persistence;


import tech.ada.estacionamento.dominio.Veiculo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class VeiculoPersistence {

	private final Path caminhoArquivo;


	public VeiculoPersistence(){
		try {
			caminhoArquivo = getPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Veiculo veiculo){
		CompletableFuture.runAsync(()-> {
			try (var out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(caminhoArquivo.toFile(), true)))) {

				out.writeObject(veiculo);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).whenComplete((Void, exception) -> System.out.println("registro inserido"));
	}

	public List<Veiculo> getAll(){
		var veiculosList = new ArrayList<Veiculo>();
		File caminhoArquivoFile = caminhoArquivo.toFile();
		if(caminhoArquivoFile.length()>0) {
			try (var in = new ObjectInputStream
					(new BufferedInputStream(new FileInputStream(caminhoArquivoFile)))) {
				Object object;
				boolean shouldContinue = true;
				while (shouldContinue) {

					object = in.readObject();
					if (Objects.nonNull(object) && object instanceof Veiculo) {
						veiculosList.add((Veiculo) object);
					}else{
						shouldContinue=false;
					}
				}

			}catch (EOFException eofException){
				System.out.println("Leitura finalizada");
			}
			catch (IOException | ClassNotFoundException e) {
				System.err.println("Não foi possível pegar os valores de veículos no arquivo.");
			}
		}
		return veiculosList;
	}

	private Path getPath() throws IOException {
		Path caminho = Paths.get("src", "main", "resources"
				, "veiculos.txt");
		if(!caminho.toFile().exists()){
			caminho.toFile().createNewFile();
		}
		return caminho;
	}
}
