// Enum para os tipos de veículos
enum TipoVeiculo {
    PEQUENO,
    MEDIO,
    SUV
}

// Classe Veiculo
public class Veiculo {
    private String placa;
    private boolean estaAlugado;
    private TipoVeiculo tipo;

    public Veiculo(String placa, TipoVeiculo tipo) {
        this.placa = placa;
        this.tipo = tipo;
        this.estaAlugado = false; // Inicialmente, o veículo não está alugado
    }

    // Getters e setters para os atributos

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isAlugado() {
        return estaAlugado;
    }

    public void setEstaAlugado(boolean estaAlugado) {
        this.estaAlugado = estaAlugado;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    // Método toString para representar o veículo como uma String
    @Override
    public String toString() {
        return "Veiculo | placa = " + placa + " | estaAlugado=" + estaAlugado + " | tipo=" + tipo;
    }
}
