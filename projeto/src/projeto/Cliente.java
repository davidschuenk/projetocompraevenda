package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente extends Usuario {

	public void cadastrarclientetxt(String caminho) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true));

			bw.write(this.getNome() + "#" + this.getCpf() + "$" + this.getIdade() + "%" + this.getEmail() + "¨"
					+ this.getSenha() + "&" + this.getTel() + "*" + "cliente" + "!");
			bw.newLine();

			bw.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void editarcliente(String caminho, String caminhotemp, String caminhop, String caminhoptemp,
			String emaillog) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));
			BufferedReader brp = new BufferedReader(new FileReader(caminhop));
			BufferedWriter bwp = new BufferedWriter(new FileWriter(caminhoptemp));

			while (br.ready()) {

				String linha = br.readLine();

				String nome = linha.substring(0, linha.indexOf("#"));
				String cpf = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String idade = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String email = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));

				if (emaillog.equals(email)) {

					bw.write(nome + "#" + cpf + "$" + idade + "%" + this.getEmail() + "¨" + this.getSenha() + "&"
							+ this.getTel() + "*" + "cliente" + "!");
					bw.newLine();
					System.out.println("editado com sucesso!!!");

				} else {
					bw.write(linha);
					bw.newLine();
				}

			}

			while (brp.ready()) {
				String linha = brp.readLine();

				String ident = linha.substring(0, linha.indexOf("!"));
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);
				if (emaillog.equals(email)) {
					bwp.write(ident + "!" + nome + "@" + preco + "#" + data + "$" + this.getEmail() + "%" + descricao);
				} else {
					bwp.write(linha);
					bwp.newLine();
				}
			}
			brp.close();
			bwp.close();
			br.close();
			bw.close();

			BufferedReader br1 = new BufferedReader(new FileReader(caminhotemp));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(caminho));
			BufferedReader brp1 = new BufferedReader(new FileReader(caminhoptemp));
			BufferedWriter bwp1 = new BufferedWriter(new FileWriter(caminhop));

			while (br1.ready()) {
				String linha = br1.readLine();

				bw1.write(linha);
				bw1.newLine();
			}
			while (brp1.ready()) {
				String linha = brp1.readLine();
				bwp1.write(linha);
				bwp1.newLine();
			}

			bwp1.close();
			brp1.close();
			br1.close();
			bw1.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}


}
