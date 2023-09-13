import java.util.List;

public class MenuPessoa {
	public static void menuPessoa() {
		while (true) {
			mostrarMenuPessoa();

			int opcao = Integer.parseInt(Menu.obterEntrada("Por favor escolha uma opção: "));

			switch (opcao) {
			case 1:
				cadastrarPessoa();
				break;

			case 2:
				buscarPessoaID();
				break;

			case 3:
				alterarPessoa();
				break;

			case 4:
				listarPessoas();
				break;

			case 5:
				return;

			case 9:
				Menu.sc.close();
				System.exit(0);

			default:
				System.out.println("Opção inválida, digite novamente: ");
				break;
			}
		}
	}

	private static void mostrarMenuPessoa() {
		System.out.println("\n----Gerenciar pessoa----");
		System.out.println("Por favor escolha uma opção:");
		System.out.println("1 - cadastrar");
		System.out.println("2 - buscar");
		System.out.println("3 - alterar");
		System.out.println("4 - listar");
		System.out.println("5 - Retornar ao menu anterior");
		System.out.println("9 - sair");
	}

	private static void alterarPessoa() {
		try {
			String novoId = Menu.obterEntrada("Digite o CPF ou CNPJ da pessoa que deseja alterar: ");

			if (novoId == null || novoId.isEmpty()) {
				throw new IllegalArgumentException("CPF ou CNPJ não pode ser vazio.");
			}
			Pessoa pessoaParaAlterar = Menu.locadora.getControlePessoa().buscar(novoId);

			if (pessoaParaAlterar != null) {

				String novoEndereco = Menu.obterEntrada("Novo endereço: ");
				if (novoEndereco == null || novoEndereco.isEmpty()) {
					throw new IllegalArgumentException("Endereço não pode ser vazio.");
				}

				String novoContato = Menu.obterEntrada("Novo contato: ");
				if (novoContato == null || novoContato.isEmpty()) {
					throw new IllegalArgumentException("Contato não pode ser vazio.");
				}

				Menu.locadora.getControlePessoa().alterar(novoId, novoEndereco, novoContato);
				System.out.println("Pessoa alterada com sucesso.");
			} else {
				throw new NullPointerException("Nenhuma pessoa encontrada com o CPF ou CNPJ fornecido.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}

	private static void listarPessoas() {
		List<Pessoa> pessoas = Menu.locadora.getControlePessoa().listar();

		if (pessoas == null || pessoas.isEmpty()) {
			System.out.println("Nenhuma pessoa encontrada.");
			Menu.sc.nextLine();
		} else {
			System.out.println(pessoas);
		}
	}

	private static void buscarPessoaID() {
		try {
			String id = Menu.obterEntrada("Digite o cpf ou cnpj da pessoa:");

			if (id == null || id.isEmpty()) {
				throw new IllegalArgumentException("CPF ou CNPJ não pode ser vazio.");
			}

			Pessoa pessoa = Menu.locadora.getControlePessoa().buscar(id);

			if (pessoa == null) {
				throw new NullPointerException("Nenhuma pessoa encontrada com o CPF ou CNPJ fornecido.");
			}

			System.out.println("Informações da pessoa:\n" + pessoa);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println("Ocorreu um erro ao buscar a pessoa: " + e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}

	public static void cadastrarPessoa() {
		try {
			String id = Menu.obterEntrada("CPF ou CNPJ da pessoa: ");
			if (id == null || id.isEmpty()) {
				throw new IllegalArgumentException("CPF ou CNPJ não pode ser vazio.");
			}

			String nome = Menu.obterEntrada("Nome da pessoa: ");
			if (nome == null || nome.isEmpty()) {
				throw new IllegalArgumentException("Nome não pode ser vazio.");
			}

			String contato = Menu.obterEntrada("Contato da pessoa: ");
			if (contato == null || contato.isEmpty()) {
				throw new IllegalArgumentException("Contato não pode ser vazio.");
			}

			String endereco = Menu.obterEntrada("Endereço da pessoa: ");
			if (endereco == null || endereco.isEmpty()) {
				throw new IllegalArgumentException("Endereço não pode ser vazio.");
			}

			// Verifique se a pessoa já existe
			Pessoa pessoaExistente = Menu.locadora.getControlePessoa().buscar(id);
			if (pessoaExistente != null) {
				System.out.println("Pessoa já cadastrada com este CPF ou CNPJ.");
				Menu.sc.nextLine();
			} else {
				Pessoa novaPessoa;
				if (id.length() == 11) { // CPF tem 11 caracteres
					novaPessoa = new PessoaFisica(id, nome, contato, endereco);
				} else if (id.length() == 14) { // CNPJ tem 14 caracteres
					novaPessoa = new PessoaJuridica(id, nome, contato, endereco);
				} else {
					throw new IllegalArgumentException("CPF ou CNPJ inválido.");
				}

				Menu.locadora.getControlePessoa().cadastrar(novaPessoa);
				System.out.println("Pessoa cadastrada com sucesso.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}
}
