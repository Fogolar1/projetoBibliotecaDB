package bean;

public class LivrosBean {
    private int id;
    private String nome;
    private final int idCategoria;
    private final int idAutor;
    private AutoresBean autor;
    private CategoriasBean categorias;

    public LivrosBean(int id, String nome, int idCategoria, int idAutor) {
        this.id = id;
        this.nome = nome;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
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

    public AutoresBean getAutor() {
        return autor;
    }

    public void setAutor(AutoresBean autor) {
        this.autor = autor;
    }

    public CategoriasBean getCategorias() {
        return categorias;
    }

    public void setCategorias(CategoriasBean categorias) {
        this.categorias = categorias;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdAutor() {
        return idAutor;
    }

}
