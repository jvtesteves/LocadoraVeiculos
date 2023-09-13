public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(String cpf, String nome, String contato, String endereco) {
        super(nome, contato, endereco);
        this.cpf = cpf;
    }

    // Getter e setter para o atributo cpf

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Implementação do método toString para PessoaFisica
    @Override
    public String toString() {
        return "Pessoa Física | cpf = " + cpf + " | nome = " + getNome() + " | contato = " + getContato() + " | endereço = " + getEndereco();
    }
}

