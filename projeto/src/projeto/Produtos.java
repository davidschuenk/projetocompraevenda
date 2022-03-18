package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Produtos {
	private String nome = null;
	private String data = null;
	private String preco = null;
	private String descricao = null;
	private String ident = null;
	private String emailpertencente = null;

	public String getEmailpertencente() {
		return emailpertencente;
	}

	public void setEmailpertencente(String emailpertencente) {
		this.emailpertencente = emailpertencente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public void CadastrarProduto(String caminho, String email) {
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true));

			bw.write(somarid(caminho) + "!" + this.getNome() + "@" + this.getPreco() + "#" + this.getData() + "$"
					+ email + "%" + this.getDescricao());
			bw.newLine();
			bw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public int somarid(String caminho) {
		int id = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));

			while (br.ready()) {
				String linha = br.readLine();
				int identarq = Integer.parseInt(linha.substring(0, linha.indexOf("!")));
				if (identarq > id) {
					id = identarq;
				}
			}
			br.close();

		} catch (IOException e) {
			System.out.println(e);
		}
		return ++id;
	}

	public void relatorioDeProdutos(String caminho) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));

			
			while (br.ready()) {
				
				String linha = br.readLine();
				String ident = linha.substring(0, linha.indexOf("!"));
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);

				System.out.printf("%-5.5s %-50.50s %-7.7s %-8.8s %-40.40s %-200.200s \n", ident, nome, preco, data,
						email, descricao);
			}
			System.out.println("");
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	public void relatorioDeProdutoscompra(String caminho, String emaillog) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));

			System.out.printf("%-6.6s %-50.50s %-7.7s %-8.8s %-40.40s %-200.200s \n", "id", "nome", "preco", "data",
					"email", "descricao");
			while (br.ready()) {

				String linha = br.readLine();
				String ident = linha.substring(0, linha.indexOf("!"));
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);
				if (email.equals(emaillog)) {

				} else {
					System.out.printf("%-6.6s %-50.50s %-7.7s %-8.8s %-40.40s %-200.200s \n", ident, nome, preco, data,
							email, descricao);

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

	public boolean produtosdocliente(String caminho, String email) {
		int cont = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			System.out.println("\nseus produtos sao:");
			System.out.printf("%-6.6s %-50.50s %-7.7s %-8.8s %-40.40s %-200.200s \n", "id", "nome", "preco", "data",
					"email", "descricao");
			while (br.ready()) {
				String linha = br.readLine();
				String ident = linha.substring(0, linha.indexOf("!"));
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email1 = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);
				if (email.equals(email1)) {
					System.out.printf("%-6.6s %-50.50s %-7.7s %-8.8s %-40.40s %-200.200s \n", ident, nome, preco, data,
							email, descricao);
					cont++;

				}
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (cont == 0) {
			return false;
		} else {
			return true;
		}

	}

	public void comprarproduto(String caminho, String caminhotemp, int id, String emaillog) {
		try {

			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {
				String linha = br.readLine();

				int ident = Integer.parseInt(linha.substring(0, linha.indexOf("!")));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));

				if (ident == id && emaillog.equals(email)) {
					System.out.println("\nvoce nao pode comprar o seu produto\n");
				} else {
					if (id == ident) {
						this.setIdent(linha.substring(0, linha.indexOf("!")));
						this.setNome(linha.substring(linha.indexOf("!") + 1, linha.indexOf("@")));
						this.setPreco(linha.substring(linha.indexOf("@") + 1, linha.indexOf("#")));
						this.setData(linha.substring(linha.indexOf("#") + 1, linha.indexOf("$")));
						this.setEmailpertencente(linha.substring(linha.indexOf("$") + 1, linha.indexOf("%")));
						this.setDescricao(linha.substring(linha.indexOf("%") + 1));
						bw.write(ident + "!" + this.getNome() + "@" + this.getPreco() + "#" + this.getData() + "$"
								+ emaillog + "%" + this.getDescricao());
						bw.newLine();
						System.out.println("comprado com sucesso!!!");
					} else {
						bw.write(linha);
						bw.newLine();
					}

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

	public void editarproduto(String caminho, String caminhotemp, String emaillog, int id) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {

				String linha = br.readLine();

				int ident = Integer.parseInt(linha.substring(0, linha.indexOf("!")));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));

				if (ident == id && emaillog.equals(email)) {

					bw.write(id + "!" + this.getNome() + "@" + this.getPreco() + "#" + this.getData() + "$" + emaillog
							+ "%" + this.getDescricao());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void arrumarId(String caminho, String caminhotemp) {
		try {

			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhotemp));

			while (br.ready()) {
				String linha = br.readLine();

				int id=0;
				String nome = linha.substring(linha.indexOf("!") + 1, linha.indexOf("@"));
				String preco = linha.substring(linha.indexOf("@") + 1, linha.indexOf("#"));
				String data = linha.substring(linha.indexOf("#") + 1, linha.indexOf("$"));
				String email = linha.substring(linha.indexOf("$") + 1, linha.indexOf("%"));
				String descricao = linha.substring(linha.indexOf("%") + 1);
				
				bw.write(id++ + "!" + nome + "@" + preco + "#" + data + "$" + email + "%"
						+ descricao);
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

	public void excluirproduto(String caminho, String caminhotemp, int id, String emaillog) {
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

				if (ident == id && emaillog.equals(email)) {

					System.out.println("excluido com sucesso!!!");

				} else {
					
					bw.write(ident + "!" + nome + "@" + preco + "#" + data + "$" + email + "%"
							+ descricao);
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
				
				bw1.write(auxid + "!" + nome + "@" + preco + "#" + data + "$" + email + "%"
						+ descricao);
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
