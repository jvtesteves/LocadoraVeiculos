public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String nome, String contato, String endereco, String cnpj) {
        super(nome, contato, endereco);
        this.cnpj = cnpj;
    }

    // Getter e setter para o atributo cnpj

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Implementação do método toString para PessoaJuridica
    @Override
    public String toString() {
        return "Pessoa Jurídica | cnpj = " + cnpj + " | nome = " + getNome() + " | contato = " + getContato() + " | endereço = " + getEndereco();
    }
}
