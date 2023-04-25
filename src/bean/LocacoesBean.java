package bean;

import java.time.LocalDate;

public class LocacoesBean {

    private final int id;
    private final int idLivro;
    private final int idLocador;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private  LivrosBean livros;
    private LocadoresBean locadores;

    public LocacoesBean(int id, int idLivro, int idLocador, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.idLivro = idLivro;
        this.idLocador = idLocador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() {
        return id;
    }

    public void setLivros(LivrosBean livros) {
        this.livros = livros;
    }

    public LivrosBean getLivros() {
        return livros;
    }

    public LocadoresBean getLocadores() {
        return locadores;
    }

    public void setLocador(LocadoresBean locador) {
        this.locadores = locador;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getIdLocador() {
        return idLocador;
    }
}
