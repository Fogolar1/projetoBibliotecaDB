package bean;

public class LocadoresBean {
    private int id;
    private String nome;
    private final String email;
    private final int telefone;
    private final int idEndereco;
    private EnderecosBean endereco;

    public LocadoresBean(int id, String nome, String email, int telefone, int idEndereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idEndereco = idEndereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefone() {
        return telefone;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public EnderecosBean getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecosBean endereco) {
        this.endereco = endereco;
    }
}
