
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Locacao {
	private static long idGlobal = 0;
	private long idLocacao;
	private Pessoa locatario;
	private Veiculo veiculo;
	private LocalDateTime retirada;
	private LocalDateTime devolucao;
	private double valorTotal;
	private boolean locacaoFinalizada;
	private String agencia;

	public String getAgencia() {
		return agencia;
	}
	
	public long getIdLocacao() {
		return this.idLocacao;
	}

	public Locacao(Pessoa locatario, Veiculo veiculo, String agencia, LocalDateTime retirada) {
		this.idLocacao = ++idGlobal;
		this.locatario = locatario;
		this.veiculo = veiculo;
		this.agencia = agencia;
		this.retirada = retirada;
		this.devolucao = null;
		this.valorTotal = -1;
		this.locacaoFinalizada = false;
	}

	public void finalizarLocacao(LocalDateTime devolucao) throws Exception {
		this.locacaoFinalizada = true;
		this.devolucao = devolucao;
		int quantidadeDiarias = calcularDiarias(devolucao);
		if (quantidadeDiarias != -1) {
			if (calcularValorTotal(quantidadeDiarias)) {
				System.out.println("Sucesso");
			} else {
				throw new Exception("Erro ao calcular o valor total da locação.");
			}
		} else {
			throw new Exception("Erro no cálculo das diárias.");
		}
	}

	private int calcularDiarias(LocalDateTime devolucao) {
		if (this.locacaoFinalizada) {
			// implementar calculo da quantidade de diárias.
			long tempoLocacaoMinutos = ChronoUnit.MINUTES.between(this.retirada, devolucao);
			int diasLocado = (int) tempoLocacaoMinutos / 1440;
			if (diasLocado <= 0) {
				return -1;
			} else {
				if (tempoLocacaoMinutos % 1440 > 0) {
					diasLocado++;
				}
				return diasLocado;
			}
		} else {
			return -1;
		}
	}

	private boolean calcularValorTotal(int quantideDiarias) {
		double valorDiaria, valorTotal = 0;
		switch (this.veiculo.getTipo()) {
		case PEQUENO:
			valorDiaria = 100;
			break;
		case MEDIO:
			valorDiaria = 150;
			break;
		case SUV:
			valorDiaria = 200;
			break;
		default:
			return false;
		}
		valorTotal = quantideDiarias * valorDiaria;

		if (this.locatario.getClass().equals(PessoaFisica.class)) {
			if (quantideDiarias > 5) {
				valorTotal *= 0.95;
			}
		} else if (this.locatario.getClass().equals(PessoaJuridica.class)) {
			if (quantideDiarias > 3) {
				valorTotal *= 0.90;
			}
		} else {
			return false;
		}

		this.valorTotal = valorTotal;
		return true;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); // Formato personalizado
		String formattedRetirada = retirada.format(formatter); // Formate a data e hora de retirada

		String dadosLocacao = "--------------" + "\nIdentificador: " + this.idLocacao + "\nLocatário....: "
				+ this.locatario.toString() + "\nVeículo......: " + this.veiculo.toString() + "\nRetirada.....: "
				+ formattedRetirada; // Use a data e hora formatada

		if (this.locacaoFinalizada) {
			String formattedDevolucao = devolucao.format(formatter); // Formate a data e hora de devolução
			dadosLocacao += "\nDevolução....: " + formattedDevolucao + "\nValor Total..: R$ " + this.valorTotal
					+ "\n--------------";
		} else {
			dadosLocacao += "\nDevolução....: Locação em Andamento" + "\nValor Total..: A processar"
					+ "\n--------------";
		}

		return dadosLocacao;
	}

}
