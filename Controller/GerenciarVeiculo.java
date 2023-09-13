import java.util.ArrayList;
import java.util.List;

public class GerenciarVeiculo implements Gerenciador<Veiculo, String>{
	private final List<Veiculo> listaVeiculo = new ArrayList<>();

	@Override
	public void cadastrar(Veiculo veiculo) {
		listaVeiculo.add(veiculo);
	}

	@Override
	public Veiculo buscar(String placa) {
		for (Veiculo veiculo : listaVeiculo) {
			if (veiculo.getPlaca().equals(placa)) {
				return veiculo;
			}
		}
		return null; // Retorna null se não encontrar o veículo com a placa especificada
	}

	@Override
	public List<Veiculo> listar() {
		return listaVeiculo;
	}

	@Override
	public void alterar(String placa, Object... parametros) {
		Veiculo veiculoParaAlterar = buscar(placa);
		if (veiculoParaAlterar != null) {
			// Inverte o valor de estaAlugado
			veiculoParaAlterar.setEstaAlugado(!veiculoParaAlterar.isAlugado());
		}
	}


	@Override
	public void excluir(String placa) {
		Veiculo veiculoParaExcluir = buscar(placa);
		if (veiculoParaExcluir != null) {
			listaVeiculo.remove(veiculoParaExcluir);
		}
	}

}
