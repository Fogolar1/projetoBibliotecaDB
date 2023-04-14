package bean;

public class LocadoresBean {
    private int id;
    private String nome;
    private final String email;
    private final int telefone;
    private final int idEndereco;
    private EnderecosBean endereco;

    public LocadoresBean(int id, String nome, int telefone, String email, int idEndereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public EnderecosBean getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecosBean endereco) {
        this.endereco = endereco;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

}
