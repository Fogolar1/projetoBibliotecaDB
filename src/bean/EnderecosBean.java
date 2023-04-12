package bean;

public class EnderecosBean {
    private int id;
    private final String bairro;
    private final String cidade;
    private final String logradouro;
    private final int numero;

    public EnderecosBean(int id, String bairro, String cidade, String logradouro, int numero) {
        this.id = id;
        this.bairro = bairro;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

}
