import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;

public class MenuLocacao {

	public static void menuLocacao() {
		while (true) {
			mostrarMenuLocacao();

			int opcao = Integer.parseInt(Menu.obterEntrada("Por favor escolha uma opção: "));

			switch (opcao) {
			case 1:
				novaLocacao();
				break;
			case 2:
				buscarPeloID();
				break;
			case 3:
				listarLocacoes();
				break;
			case 4:
				finalizarLocacao();
				break;
			case 5:
				return;
			case 9:
				Menu.sc.close();
				System.exit(0);

			default:
				System.out.print("Opção inválida, digite novamente: ");
				break;
			}
		}
	}

	public static void finalizarLocacao() {
	    String idLocacao = Menu.obterEntrada("Por favor, insira o ID da locação:");
	    
	    Locacao locacao = Menu.locadora.buscarLocacao(Long.parseLong(idLocacao));
	    System.out.println(locacao);
	    Menu.sc.nextLine();

	    if (locacao != null) {
	        String dataHoraDevolucao = Menu.obterEntrada("Por favor, insira a data e hora da devolução no formato 'dd/mm/aaaa HH:mm':");

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        LocalDateTime devolucao = LocalDateTime.parse(dataHoraDevolucao, formatter);

	        try {
	            locacao.finalizarLocacao(devolucao);
	            System.out.println("Locação finalizada com sucesso!");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    } else {
	        System.out.println("Não foi possível encontrar uma locação com o ID fornecido.");
	    }
	}

	private static void mostrarMenuLocacao() {
		System.out.println("\n----Gerenciar Locação----");
		System.out.println("1 - alugar");
		System.out.println("2 - buscar");
		System.out.println("3 - listar");
		System.out.println("4 - finalizar locação");
		System.out.println("5 - Retornar ao menu anterior");
		System.out.println("9 - sair");
	}

	private static void listarLocacoes() {
		List<Locacao> locacoes = Menu.locadora.listar();

		if (locacoes == null || locacoes.isEmpty()) {
			System.out.println("Nenhuma locação encontrada.");
			Menu.sc.nextLine();
		} else {
			System.out.println(locacoes);
		}
	}

	private static void buscarPeloID() {
		try {
			Long idLocacao = Long.parseLong(Menu.obterEntrada("Digite o ID da locação:"));

			Locacao locacao = Menu.locadora.buscarLocacao(idLocacao);

			if (locacao == null) {
				throw new NullPointerException("Nenhuma locação encontrada com o ID fornecido.");
			}

			System.out.println("Informações da locação:\n" + locacao);

		} catch (InputMismatchException e) {
			System.out.println("Por favor, insira um número válido para o ID da locação.");
			Menu.sc.nextLine();
		} catch (NullPointerException e) {
			System.out.println("Ocorreu um erro ao buscar a locação: " + e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}

	private static void novaLocacao() {
		System.out.println("NOVA LOCAÇÃO");
		String id = Menu.obterEntrada("Informe o CPF ou CNPJ do cliente: ");
		Pessoa cliente = buscarCliente(id);
		if (cliente == null)
			return;

		String placa = Menu.obterEntrada("Informe a placa do veículo: ");
		Veiculo carro = buscarVeiculo(placa);
		if (carro == null)
			return;

		LocalDateTime retirada = obterDataHora("Informe a data e hora da retirada (dd/mm/aaaa hh:mm): ");
		if (retirada == null)
			return;

		String agencia = Menu.obterEntrada("Informe a agência de retirada do veículo: ");
		alugarVeiculo(cliente, carro, agencia, retirada);
	}

	private static Pessoa buscarCliente(String id) {
		Pessoa cliente = Menu.locadora.getControlePessoa().buscar(id);
		if (cliente == null) {
			System.out.println("Cliente não encontrado! Tente novamente. \n");
			return null;
		}
		System.out.println(cliente.toString());
		System.out.println();
		return cliente;
	}

	private static Veiculo buscarVeiculo(String placa) {
		Veiculo carro = Menu.locadora.getControleVeiculo().buscar(placa);
		if (carro == null) {
			System.out.println("Veiculo não encontrado! Tente novamente. \n");
			return null;
		}
		System.out.println(carro.toString());
		System.out.println();
		return carro;
	}

	private static LocalDateTime obterDataHora(String mensagem) {
		String dataHora = Menu.obterEntrada(mensagem);
		LocalDateTime retirada;
		try {
			retirada = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			if (ChronoUnit.MINUTES.between(retirada, LocalDateTime.now()) < 0) {
				System.out.println("Impossível retirar um veículo com data futura!\n");
				return null;
			}
			return retirada;
		} catch (Exception e) {
			System.out.println("Data/hora em formato inválido! \n");
			return null;
		}
	}

	private static void alugarVeiculo(Pessoa cliente, Veiculo carro, String agencia, LocalDateTime retirada) {
		try {
			Menu.locadora.alugar(cliente, carro, agencia, retirada);
		} catch (Exception e) {
			System.out.println("Ooops, algo deu errado! Tente novamente. \n");
		}
	}

}
