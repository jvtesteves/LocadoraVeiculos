import java.util.ArrayList;
import java.util.List;

public class GerenciarPessoa implements Gerenciador<Pessoa, String>{
	private final List<Pessoa> listaDePessoas = new ArrayList<>();

	@Override
	public void cadastrar(Pessoa pessoa) {
		listaDePessoas.add(pessoa);
	}

	@Override
	public Pessoa buscar(String id) {
		for (Pessoa pessoa : listaDePessoas) {
			if (pessoa instanceof PessoaFisica) {
				PessoaFisica pessoaFisica = (PessoaFisica) pessoa;
				if (pessoaFisica.getCpf().equals(id)) {
					return pessoaFisica;
				}
			} else if (pessoa instanceof PessoaJuridica) {
				PessoaJuridica pessoaJuridica = (PessoaJuridica) pessoa;
				if (pessoaJuridica.getCnpj().equals(id)) {
					return pessoaJuridica;
				}
			}
		}
		return null; // Retorna null se não encontrar a pessoa com o ID especificado
	}

	@Override
	public List<Pessoa> listar() {
		return listaDePessoas;
	}

	@Override
	public void alterar(String id, Object... parametros) {
		Pessoa pessoaParaAlterar = buscar(id);
		if (pessoaParaAlterar != null) {
			if (parametros.length >= 2 && parametros[0] instanceof String && parametros[1] instanceof String) {
				String novoEndereco = (String) parametros[0];
				String novoContato = (String) parametros[1];
				pessoaParaAlterar.setEndereco(novoEndereco);
				pessoaParaAlterar.setContato(novoContato);
			}
		}
	}



	@Override
	public void excluir(String id) {
		Pessoa pessoaParaExcluir = buscar(id);
		if (pessoaParaExcluir != null) {
			listaDePessoas.remove(pessoaParaExcluir);
		}
	}

}
