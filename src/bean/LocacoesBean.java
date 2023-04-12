package bean;

import java.util.Date;

public class LocacoesBean {
    private final int idLivro;
    private final int idLocador;
    private final Date dataInicio;
    private final Date dataFim;
    private  LivrosBean livros;
    private LocadoresBean locadores;

    public LocacoesBean(int idLivro, int idLocador, Date dataInicio, Date dataFim) {
        this.idLivro = idLivro;
        this.idLocador = idLocador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getIdLocador() {
        return idLocador;
    }
}
