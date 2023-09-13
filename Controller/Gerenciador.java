import java.util.List;

public interface Gerenciador<T, S> {
	
	public void cadastrar(T objeto);
	
	public T buscar(S id);
	
	public List<T> listar();

	public void alterar(S id, Object... parametros);
	
	public void excluir(S id);
}
