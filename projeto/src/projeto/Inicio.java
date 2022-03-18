package projeto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Inicio {
	public static void main(String[] args) {

		String caminhocliente = "C:\\Users\\david\\Desktop\\modulojava\\projeto\\src\\bancotxt\\usuario.txt";
		String caminhoclientetemp = "C:\\Users\\david\\Desktop\\modulojava\\projeto\\src\\bancotxt\\usuariotemp.txt";
		String caminhoproduto = "C:\\Users\\david\\Desktop\\modulojava\\projeto\\src\\bancotxt\\produto.txt";
		String caminhoprodutotemp = "C:\\Users\\david\\Desktop\\modulojava\\projeto\\src\\bancotxt\\produtotemp.txt";
		
		Scanner ler = new Scanner(System.in);
		int op = 0;

		do {
			verificarArquivo(caminhocliente);
			verificarArquivo(caminhoclientetemp);
			verificarArquivo(caminhoproduto);
			verificarArquivo(caminhoprodutotemp);
			menuprincipal();
			try {
				op = Integer.parseInt(ler.nextLine());
			} catch (Exception e) {
				System.out.println("\nincorreto\n");
			}

			switch (op) {

			case 1: {

				Cliente cl = new Cliente();

				System.out.println("\n cadastro de cliente \n");

				String auxnome;
				do {
					System.out.println("digite seu nome:");
					auxnome = ler.nextLine();
					cl.setNome(auxnome);
				} while (auxnome.isBlank() || new Usuario().verificarnome(cl.getNome()));

				String auxidade;
				do {
					System.out.println("digite sua idade:");
					auxidade = ler.nextLine();
					cl.setIdade(auxidade);
				} while (auxidade.isBlank() || new Usuario().verificarnumero(cl.getIdade()));

				String auxcpf;
				do {
					System.out.println("digite seu cpf (somente os numeros):");
					auxcpf = ler.nextLine();
					cl.setCpf(auxcpf);
				} while (auxcpf.isBlank() || new Usuario().verificarnumero(cl.getCpf()) || cl.getCpf().length() != 11);

				String auxtel;
				do {
					System.out.println("digite seu numero de telefone com o ddd (somente os numeros):");
					auxtel = ler.nextLine();
					cl.setTel(auxtel);
				} while (auxtel.isBlank() || new Usuario().verificarnumero(cl.getTel()) || cl.getTel().length() != 11);

				String auxemail;
				do {
					System.out.println("digite seu email:");
					auxemail = ler.nextLine();
					cl.setEmail(auxemail);
				} while (auxemail.isBlank() || new Usuario().validarEmail(cl.getEmail())
						|| new Usuario().emailrepitido(caminhocliente, cl.getEmail()));

				String auxsenha;
				do {
					System.out.println("digite sua senha:(minimo de 8 caracteres e somente numeros)");
					auxsenha = ler.nextLine();
					cl.setSenha(auxsenha);
				} while (auxemail.isBlank() || new Usuario().verificarnumero(cl.getSenha())
						|| cl.getSenha().length() < 8);

				cl.cadastrarclientetxt(caminhocliente);
				System.out.println("\ncadastrado com sucesso\n");
				break;
			}
//----------------------------------------------------------------------------------------------------------------caso 2
			case 2: {

//----------------------------------------------------------------------------------------------------------------login
				String emaillog = null;
				String senhalog = null;
				do {
					System.out.println("digite seu email:");
					emaillog = ler.nextLine();
					System.out.println("digite sua senha");
					senhalog = ler.nextLine();

					System.out.println();

				} while (new Usuario().verificarlogin(caminhocliente, emaillog, senhalog));

//-----------------------------------------------------------------------------------------------------------------menu do login
				switch (new Usuario().verificarhierarquia(caminhocliente, emaillog)) {
//=================================================================================================================menu do usuario
				case "usuario": {

					int opmenucliente;

					do {

						menucliente();

						opmenucliente = Integer.parseInt(ler.nextLine());

						switch (opmenucliente) {

						case 1: {
							Produtos pr = new Produtos();
							if (pr.produtosdocliente(caminhoproduto, emaillog)) {

							} else {
								System.out.println("\nvoce nao tem nenhum produto\n");
							}

							break;
						}

						case 2: {
							Produtos pr = new Produtos();
							pr.relatorioDeProdutoscompra(caminhoproduto, emaillog);
							System.out.println("digite o id do produto que voce deseja comprar");
							System.out.println("0 - para sair");
							int idprodutocompra = Integer.parseInt(ler.nextLine());
							switch (idprodutocompra) {
							case 0: {
								System.out.println("voltando para o menu");
								break;
							}
							default: {
								pr.comprarproduto(caminhoproduto, caminhoprodutotemp, idprodutocompra, emaillog);
								break;
							}
							}

							break;
						}
						case 3: {
							Produtos pr = new Produtos();

							String auxcadastrarprodutonome;

							do {
								System.out.println("\ndigite o nome do produto");
								auxcadastrarprodutonome = ler.nextLine();
								pr.setNome(auxcadastrarprodutonome);
							} while (auxcadastrarprodutonome.isBlank() || new Usuario().verificarnome(pr.getNome()));

							String auxcadastrarprodutopreco;
							do {
								System.out.println("\ndigite o preco do produto");
								auxcadastrarprodutopreco = ler.nextLine();
								pr.setPreco(auxcadastrarprodutopreco);
							} while (auxcadastrarprodutopreco.isBlank()
									|| new Usuario().verificarnumero(pr.getPreco()));

							String auxcadastrarprodutodata;
							do {
								System.out.println("\ndigite a data do produto (exemplo dd/mm/aaaa)");
								auxcadastrarprodutodata = ler.nextLine();
								pr.setData(auxcadastrarprodutodata);
							} while (auxcadastrarprodutodata.isBlank()
									|| new Usuario().isDateValid(auxcadastrarprodutodata));

							String auxcadastrarprodutodescricao;
							do {
								System.out.println("\ndigite a descricao do produto");
								auxcadastrarprodutodescricao = ler.nextLine();
								pr.setDescricao(auxcadastrarprodutodescricao);
							} while (auxcadastrarprodutodescricao.isBlank());

							pr.CadastrarProduto(caminhoproduto, emaillog);
							break;
						}
						case 4: {
							Produtos pr = new Produtos();

							pr.produtosdocliente(caminhoproduto, emaillog);
							System.out.println("");
							System.out.println("digite o id do seu produto para modifica-lo");
							System.out.println("0 - para sair");
							int idmod = Integer.parseInt(ler.nextLine());

							switch (idmod) {
							case 0:
								System.out.println("voltando para o menu!");
								break;

							default: {
								String auxcadastrarprodutonome;
								do {
									System.out.println("\ndigite o nome do produto");
									auxcadastrarprodutonome = ler.nextLine();
									pr.setNome(auxcadastrarprodutonome);
								} while (auxcadastrarprodutonome.isBlank()
										|| new Usuario().verificarnome(pr.getNome()));

								String auxcadastrarprodutopreco;

								do {
									System.out.println("\ndigite o preco do produto");
									auxcadastrarprodutopreco = ler.nextLine();
									pr.setPreco(auxcadastrarprodutopreco);
								} while (auxcadastrarprodutopreco.isBlank()
										|| new Usuario().verificarnumero(pr.getPreco()));

								String auxcadastrarprodutodata;
								do {
									System.out.println("\ndigite a data do produto (exemplo dd/mm/aaaa)");
									auxcadastrarprodutodata = ler.nextLine();
									pr.setData(auxcadastrarprodutodata);
								} while (auxcadastrarprodutodata.isBlank()
										|| new Usuario().isDateValid(auxcadastrarprodutodata));

								do {
									System.out.println("\ndigite a descricao do produto");
									String auxcadastrarprodutodescricao = ler.nextLine();
									pr.setDescricao(auxcadastrarprodutodescricao);
								} while (auxcadastrarprodutodata.isBlank());

								pr.editarproduto(caminhoproduto, caminhoprodutotemp, emaillog, idmod);
								break;
							}
							}

							break;
						}
						case 5: {

							Produtos pr = new Produtos();

							if (pr.produtosdocliente(caminhoproduto, emaillog)) {
								System.out.println("");
								System.out.println("digite o id do seu produto para excluir-lo");
								System.out.println("0 - para sair");
								int idmod = Integer.parseInt(ler.nextLine());

								switch (idmod) {
								case 0:
									System.out.println("voltando para o menu!");
									break;

								default: {
									pr.excluirproduto(caminhoproduto, caminhoprodutotemp, idmod, emaillog);
									break;
								}
								}

							} else {
								System.out.println("\nvoce nao tem nenhum produto\n");
							}

							break;
						}

						case 6: {

							int opmenuclienteconfig = 0;

							do {
								menuclienteconfig();
								opmenuclienteconfig = Integer.parseInt(ler.nextLine());
								switch (opmenuclienteconfig) {

								case 1: {
									Cliente cl = new Cliente();

									String auxeditaremail;
									do {
										System.out.println("\ndigite o email");
										auxeditaremail = ler.nextLine();
										cl.setEmail(auxeditaremail);
									} while (auxeditaremail.isBlank() || new Usuario().validarEmail(cl.getEmail())
											|| new Usuario().emailrepitido(caminhocliente, cl.getEmail()));

									String auxeditarsenha;
									do {
										System.out.println("\ndigite o senha");
										auxeditarsenha = ler.nextLine();
										cl.setSenha(auxeditarsenha);
									} while (auxeditarsenha.isBlank() || new Usuario().verificarnumero(cl.getSenha())
											|| cl.getSenha().length() < 8);

									String auxeditartel;
									do {
										System.out.println("\ndigite o telefone");
										auxeditartel = ler.nextLine();
										cl.setTel(auxeditartel);
									} while (auxeditartel.isBlank() || new Usuario().verificarnumero(cl.getTel())
											|| cl.getTel().length() != 11);

									cl.editarcliente(caminhocliente, caminhoclientetemp, caminhoproduto,
											caminhoprodutotemp, emaillog);
									emaillog = auxeditaremail;
									senhalog = auxeditarsenha;

									break;
								}
								case 2: {

									Usuario us = new Usuario();

									us.exibirperfil(caminhocliente, caminhoclientetemp, emaillog);
									break;
								}
								case 3: {

									Usuario us = new Usuario();

									System.out.println(
											"voce tem certeza de excluir seu perfil, se excluir, voce nao tera mais acesso aos seus produtos");
									System.out.println("1 - sim ");
									System.out.println("0 - nao");

									int opescola = Integer.parseInt(ler.nextLine());

									switch (opescola) {
									case 0: {
										System.out.println("voltando para o menu");
										break;
									}
									default: {
										us.excluircliente(caminhocliente, caminhoclientetemp, senhalog, emaillog);
										us.excluirtodosprodutos(caminhoproduto, caminhoprodutotemp, emaillog);
										opmenuclienteconfig = 0;
										opmenucliente = 0;
										break;
									}
									}
									break;
								}

								default:
									break;
								}

							} while (opmenuclienteconfig != 0);

							break;
						}

						default:

							break;
						}

					} while (opmenucliente != 0);

					break;
				}

//===================================================================================================================menu do admin
				case "admin": {
					int opadm = 0;

					do {
						menuadm();
						opadm = Integer.parseInt(ler.nextLine());

						switch (opadm) {

						case 1: {
							Produtos pr = new Produtos();
							Admin adm = new Admin();

							pr.relatorioDeProdutos(caminhoproduto);
							System.out.println("digite o id do produto que voce deseja excluir");
							System.out.println("0 - para sair");
							int idexcluir = Integer.parseInt(ler.nextLine());

							switch (idexcluir) {
							case 0:
								System.out.println("saindo");
								break;

							default:
								adm.excluirprodutoadm(caminhoproduto, caminhoprodutotemp, idexcluir);
								break;
							}

							break;
						}
						case 2: {
							Admin adm = new Admin();
							adm.relatorioDepessoas(caminhocliente);
							System.out.println(
									"digite o email da conta que voce deseja excluir(voce excluira todos os produtos dele)");
							System.out.println("0 - para sair");
							String emailexcluir = ler.nextLine();
							adm.excluirclienteadm(caminhocliente, caminhoclientetemp, emailexcluir);
							adm.excluirtodosprodutos(caminhoproduto, caminhoprodutotemp, emailexcluir);
							break;
						}
						case 3: {
							Admin adm = new Admin();
							adm.deletartxt(caminhoproduto);
							adm.deletartxt(caminhoprodutotemp);

							break;
						}
						case 4: {
							Admin adm = new Admin();
							adm.deletartxt(caminhocliente);
							adm.deletartxt(caminhoclientetemp);
							opadm = 0;

							break;
						}
						case 5: {
							Admin adm = new Admin();

							String auxnome;
							do {
								System.out.println("digite seu nome:");
								auxnome = ler.nextLine();
								adm.setNome(auxnome);
							} while (auxnome.isBlank() || new Usuario().verificarnome(adm.getNome()));

							String auxidade;
							do {
								System.out.println("digite sua idade:");
								auxidade = ler.nextLine();
								adm.setIdade(auxidade);
							} while (auxidade.isBlank() || new Usuario().verificarnumero(adm.getIdade()));

							String auxcpf;
							do {
								System.out.println("digite seu cpf (somente os numeros):");
								auxcpf = ler.nextLine();
								adm.setCpf(auxcpf);
							} while (auxcpf.isBlank() || new Usuario().verificarnumero(adm.getCpf())
									|| adm.getCpf().length() != 11);

							String auxtel;
							do {
								System.out.println("digite seu numero de telefone com o ddd (somente os numeros):");
								auxtel = ler.nextLine();
								adm.setTel(auxtel);
							} while (auxtel.isBlank() || new Usuario().verificarnumero(adm.getTel())
									|| adm.getTel().length() != 11);

							String auxemail;
							do {
								System.out.println("digite seu email:");
								auxemail = ler.nextLine();
								adm.setEmail(auxemail);
							} while (auxemail.isBlank() || new Usuario().validarEmail(adm.getEmail())
									|| new Usuario().emailrepitido(caminhocliente, adm.getEmail()));

							String auxsenha;
							do {
								System.out.println("digite sua senha:(minimo de 8 caracteres e somente numeros)");
								auxsenha = ler.nextLine();
								adm.setSenha(auxsenha);
							} while (auxemail.isBlank() || new Usuario().verificarnumero(adm.getSenha())
									|| adm.getSenha().length() < 8);

							adm.cadastraradmin(caminhocliente);
							break;
						}
						case 6: {

							Admin adm = new Admin();

							String auxnome;
							do {
								System.out.println("digite seu nome:");
								auxnome = ler.nextLine();
								adm.setNome(auxnome);
							} while (auxnome.isBlank() || new Usuario().verificarnome(adm.getNome()));

							String auxidade;
							do {
								System.out.println("digite sua idade:");
								auxidade = ler.nextLine();
								adm.setIdade(auxidade);
							} while (auxidade.isBlank() || new Usuario().verificarnumero(adm.getIdade()));

							String auxcpf;
							do {
								System.out.println("digite seu cpf (somente os numeros):");
								auxcpf = ler.nextLine();
								adm.setCpf(auxcpf);
							} while (auxcpf.isBlank() || new Usuario().verificarnumero(adm.getCpf())
									|| adm.getCpf().length() != 11);

							String auxtel;
							do {
								System.out.println("digite seu numero de telefone com o ddd (somente os numeros):");
								auxtel = ler.nextLine();
								adm.setTel(auxtel);
							} while (auxtel.isBlank() || new Usuario().verificarnumero(adm.getTel())
									|| adm.getTel().length() != 11);

							String auxemail;
							do {
								System.out.println("digite seu email:");
								auxemail = ler.nextLine();
								adm.setEmail(auxemail);
							} while (auxemail.isBlank() || new Usuario().validarEmail(adm.getEmail())
									|| new Usuario().emailrepitido(caminhocliente, adm.getEmail()));

							String auxsenha;
							do {
								System.out.println("digite sua senha:(minimo de 8 caracteres e somente numeros)");
								auxsenha = ler.nextLine();
								adm.setSenha(auxsenha);
							} while (auxemail.isBlank() || new Usuario().verificarnumero(adm.getSenha())
									|| adm.getSenha().length() < 8);

							adm.editaradm(caminhocliente, caminhoclientetemp, emaillog);

							break;
						}

						case 7: {
							Usuario us = new Usuario();
							us.exibirperfil(caminhocliente, caminhoclientetemp, emaillog);
						}

						default:
							break;
						}

					} while (opadm != 0);

					break;
				}
//===================================================================================================================fim dos menus
				default:
					break;
				}

//------------------------------------------------------------------------------------------------------------------fim
				break;
			}

			case 3: {
				System.out.println("fim.");
				break;
			}

			default:
				System.out.println("escolha incorreta");
				break;

			}

		} while (op != 3);

		ler.close();

	}

	public static void menuadm() {
		System.out.println("------menu de admistrador------");
		System.out.println("1 - deletar produto");
		System.out.println("2 - deletar conta");
		System.out.println("3 - deletar todos os produtos");
		System.out.println("4 - deletar todas as contas");
		System.out.println("5 - cadastrar admin");
		System.out.println("6 - editar conta");
		System.out.println("7 - para exibir dados da conta");
		System.out.println("0 - para sair");
	}

	public static void menuclienteconfig() {
		System.out.println("\nmenu do de configuracao do cliente\n");
		System.out.println("1 - para editar perfil");
		System.out.println("2 - para ver perfil");
		System.out.println("3 - para excluir perfil");
		System.out.println("0 - para sair\n");
	}

	public static void menucliente() {
		System.out.println("\nmenu do cliente\n");
		System.out.println("1 - para ver meus produtos");
		System.out.println("2 - para comprar produtos");
		System.out.println("3 - para vender produtos");
		System.out.println("4 - para editar produtos");
		System.out.println("5 - para excluir produtos");
		System.out.println("6 - para configuraçoes");
		System.out.println("0 - para sair\n");
	}

	public static void menuprincipal() {
		System.out.println("1  - cadastrar-se");
		System.out.println("2  - login");
		System.out.println("3  - sair");
	}

	public static void verificarArquivo(String caminho) {
		// System.out.println("caminho " + caminho);
		String diretorio = caminho.substring(0, caminho.lastIndexOf("\\"));

		// System.out.println("diretorio " + diretorio);
		File fdir = new File(diretorio);

		if (fdir.exists()) {
			// System.out.println("diretorio ja existe");
		} else {
			if (fdir.mkdir()) {
				// System.out.println("diretorio criado com sucesso");
			} else {
				System.out.println("erro");
			}
		}

		File farq = new File(caminho);

		if (farq.exists()) {
			// System.out.println("aquivo ja existe");
		} else {
			try {
				if (farq.createNewFile()) {
					// System.out.println("aquivo criado ");
				} else {
					// System.out.println("erro");
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
