import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocadoraDeVeiculos {
	private GerenciarVeiculo controleVeiculo = new GerenciarVeiculo();;
	private GerenciarPessoa controlePessoa = new GerenciarPessoa();
	private List<Locacao> listaLocacoes = new ArrayList<>();

	public static void inicializarSistema() {
		// Carregar BD na listaLocacoes
	}

	public void alugar(Pessoa pessoa, Veiculo veiculo, String agencia, LocalDateTime retirada) throws Exception {
		if (veiculo.isAlugado()) {
			throw new Exception("Veículo já está alugado! Selecione outro.");
		} else {
			veiculo.setEstaAlugado(true);
			Locacao novaLocacao = new Locacao(pessoa, veiculo, agencia, retirada);
			listaLocacoes.add(novaLocacao);
		}
	}

	public Locacao buscarLocacao(long id) {
		for (Locacao locacao: listaLocacoes) {
			if (locacao.getIdLocacao() == id) {
				return locacao;
			}
		}
		return null; // Retorna null se não encontrar nenhuma locação com o ID informado.
	}

	public List<Locacao> listar() {
		return this.listaLocacoes;
	}

	public GerenciarVeiculo getControleVeiculo() {
		return controleVeiculo;
	}

	public GerenciarPessoa getControlePessoa() {
		return controlePessoa;
	}

}
