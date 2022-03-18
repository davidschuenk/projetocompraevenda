package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends Usuario {

	public void cadastraradmin(String caminho) {

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(caminho, true));
			bw.write(this.getNome() + "#" + this.getCpf() + "$" + this.getIdade() + "%" + this.getEmail() + "¨"
					+ this.getSenha() + "&" + this.getTel() + "*" + "admin" + "!");
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editaradm(String caminho, String caminhotemp, String emaillog) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {

				String linha = br.readLine();
				String email = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));

				if (emaillog.equals(email)) {

					bw.write(this.getNome() + "#" + this.getCpf() + "$" + this.getIdade() + "%" + this.getEmail() + "¨" + this.getSenha() + "*" + "admin" + "!");
					bw.newLine();
					System.out.println("editado com sucesso!!!");

				} else {
					bw.write(linha);
					bw.newLine();
				}

			}

			br.close();
			bw.close();

			BufferedReader br1 = new BufferedReader(new FileReader(caminhotemp));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(caminho));

			while (br1.ready()) {
				String linha = br1.readLine();

				bw1.write(linha);
				bw1.newLine();
			}

			br1.close();
			bw1.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void excluirprodutoadm(String caminho, String caminhotemp, int id) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {

				String linha = br.readLine();
				int ident = Integer.parseInt(linha.substring(0, linha.indexOf("!")));
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);

				if (ident == id) {

					System.out.println("excluido com sucesso!!!");

				} else {

					bw.write(ident + "!" + nome + "@" + preco + "#" + data + "$" + email + "%" + descricao);
					bw.newLine();
				}

			}

			br.close();
			bw.close();

			BufferedReader br1 = new BufferedReader(new FileReader(caminhotemp));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(caminho));
			int auxid = 0;
			while (br1.ready()) {
				String linha = br1.readLine();
				auxid++;
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);

				bw1.write(auxid + "!" + nome + "@" + preco + "#" + data + "$" + email + "%" + descricao);
				bw1.newLine();
			}

			br1.close();
			bw1.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluirclienteadm(String caminho, String caminhotemp, String emaillog) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {
				String linha = br.readLine();
				String email = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));

				if (emaillog.equals(email)) {

					System.out.println("excluido com sucesso!!!");

				} else {
					bw.write(linha);
					bw.newLine();
				}

			}

			br.close();
			bw.close();

			BufferedReader br1 = new BufferedReader(new FileReader(caminhotemp));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(caminho));

			while (br1.ready()) {
				String linha = br1.readLine();

				bw1.write(linha);
				bw1.newLine();
			}

			br1.close();
			bw1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void relatorioDepessoas(String caminho) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));

			while (br.ready()) {
				String linha = br.readLine();
				String nome = linha.substring(0, linha.indexOf("#"));
				String cpf = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String idade = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String email = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));
				String hier = linha.substring(linha.indexOf("*") + 1, linha.indexOf("!"));

				if (hier.equals("cliente")) {
					System.out.println(nome + "#" + cpf + "$" + idade + "%" + email);
				}

			}
			System.out.println("");
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	public void deletartxt(String caminhoj) {



		try {
		File caminho1 = new File(caminhoj);



		caminho1.delete();
		System.out.println("excluido com sucesso");


		} catch (Exception e) {
		System.out.println("erro");
		}



		}

	public void excluirloja(String caminho, String caminhotemp) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {

				bw.write("");
				bw.newLine();
			}

			br.close();
			bw.close();

			BufferedReader br1 = new BufferedReader(new FileReader(caminhotemp));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(caminho));

			while (br1.ready()) {
				String linha = br1.readLine();

				bw1.write(linha);
				bw1.newLine();
			}

			br1.close();
			bw1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
