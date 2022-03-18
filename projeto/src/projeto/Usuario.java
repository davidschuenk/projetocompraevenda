package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Usuario {
	protected String nome = null;
	protected String senha = null;
	protected String email = null;
	protected String cpf = null;
	protected String idade = null;
	protected String tel = null;

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public void exibirperfil(String caminho, String caminhotemp, String emaillog) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			System.out.printf("%-30.30s %-11.11s %-5.5s %-30.30s %-30.30s %-11.11s \n","nome","cpf","idade","email","senha","telefone");
			while (br.ready()) {

				String linha = br.readLine();
				String nome = linha.substring(0, linha.indexOf("#"));
				String cpf = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String idade = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String email = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));
				String senha = linha.substring(linha.indexOf("¨") + 1, linha.indexOf("&"));
				String tel = linha.substring(linha.indexOf("&") + 1, linha.indexOf("*"));
				if (emaillog.equals(email)) {

					
					System.out.printf("%-30.30s %-11.11s %-5.5s %-30.30s %-30.30s %-11.11s \n",nome,cpf,idade,email,senha,tel);
				}

			}

			br.close();

		} catch (IOException e) {
			System.err.println(e);
		}
	}
	public boolean isDateValid(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate d = LocalDate.parse(date, formatter);
			return false;
		} catch (DateTimeParseException e) {
			return true;
		}
	}

	public boolean verificarlogin(String caminho, String email, String senha) {

		int cont = 0;
		try {

			BufferedReader br;

			br = new BufferedReader(new FileReader(caminho));

			while (br.ready()) {
				String linha = br.readLine();

				String emailprocurar = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));
				String senhaprocurar = linha.substring(linha.indexOf("¨") + 1, linha.indexOf("&"));

				if (emailprocurar.equals(email) && senhaprocurar.equals(senha)) {
					cont++;
					break;
				}

			}

			br.close();

		} catch (Exception e) {

			System.err.println(e.toString());
		}

		if (cont == 0) {
			System.out.println("----email nao encontrado----");
			return true;

		} else {
			return false;
		}

	}

	public String verificarhierarquia(String caminho, String email) {

		try {

			BufferedReader br = new BufferedReader(new FileReader(caminho));

			while (br.ready()) {
				String linha = br.readLine();
				String emailprocura = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));
				String hierarquia1 = linha.substring(linha.indexOf("*") + 1, linha.indexOf("!"));
				if (hierarquia1.equals("admin") && emailprocura.equals(email)) {

					br.close();
					return "admin";
				}

			}
			br.close();
		} catch (IOException e) {
			System.err.println(e);
		}

		return "usuario";

	}

	public boolean verificarnome(String nome) {
		int count = 0;
		String numero = "1234567890";

		for (int i = 0; i < numero.length(); i++) {

			if (nome.contains(numero.substring(numero.indexOf(0) + 1, i))) {
				count++;
			}

		}
		// System.out.println(count);
		if (count == 1) {
			return false;
		} else if (count > 1) {
			return true;
		}
		return false;

	}

	public boolean verificarnumero(String numero) {
		try {
			Double.parseDouble(numero);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}

	}

	public boolean emailrepitido(String caminho, String email) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));

			while (br.ready()) {
				String linha = br.readLine();
				String emaillinha = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));

				if (email.equals(emaillinha)) {
					System.out.println("esse email ja esta cadastrado.");
					br.close();
					return true;
				}
			}
			br.close();
			return false;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean validarEmail(String email) {

		int indiceEmail = email.indexOf('@');
		int indicecom = email.indexOf(".com");
		String idicemeio = email.substring(email.indexOf("@")+1,email.indexOf(".com"));
		if (indiceEmail > 0 && indicecom > 0 && idicemeio.length() >0)
			return false;
		else
			System.out.println("\nemail incorreto\n");
		return true;

	}

	public void excluirtodosprodutos(String caminho, String caminhotemp, String emailpro) {

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

				if (email.equals(emailpro)) {

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

	public void excluircliente(String caminho, String caminhotemp, String senhalog, String emaillog) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {

				String linha = br.readLine();

				String email = linha.substring(linha.indexOf("%") + 1, linha.indexOf("¨"));
				String senha = linha.substring(linha.indexOf("¨") + 1, linha.indexOf("&"));

				if (senha.equals(senhalog) && emaillog.equals(email)) {

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

}
