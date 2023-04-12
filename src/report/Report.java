package report;

import bean.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class Report {
    public static HashSet<Object> relatorioLivroPorCategoriaEAutor(Connection con) throws SQLException {
        Statement st;
        HashSet<Object> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT l.id, l.nome, a.id, a.nome, c.id, c.nome FROM livros l INNER JOIN autores a ON a.id = l.idautor INNER JOIN categorias c ON c.id = l.idcategoria";
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            LivrosBean lb = new LivrosBean(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(5));
            CategoriasBean cb = new CategoriasBean(result.getInt(5),result.getString(6));
            AutoresBean ab = new AutoresBean(result.getInt(3), result.getString(4));
            lb.setAutor(ab);
            lb.setCategorias(cb);
            list.add(lb);
        }
        return list;
    }
    public static HashSet<Object>  relatorioLocacoesPorLivro(Connection con) throws SQLException {
        Statement st;
        HashSet<Object> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT lo.datainicio, lo.datafim, l.id, l.nome,l.idcategoria, l.idautor, loc.id, loc.nome, loc.telefone, loc.email, loc.idendereco FROM locacoes lo INNER JOIN livros l ON l.id = lo.idlivro" +
                " INNER JOIN locadores loc ON loc.id = lo.idlocador";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            LocacoesBean locacoesBean = new LocacoesBean(result.getInt(3), result.getInt(5), result.getDate(1), result.getDate(2));
            LivrosBean livrosBean = new LivrosBean(result.getInt(3), result.getString(4), result.getInt(5), result.getInt(6));
            LocadoresBean locadoresBean = new LocadoresBean(result.getInt(7), result.getString(8),
                    result.getInt(9), result.getString(10), result.getInt(11));
            locacoesBean.setLivros(livrosBean);
            locacoesBean.setLocador(locadoresBean);
            list.add(locacoesBean);
        }
        return list;
    }
    public static HashSet<Object>  relatorioLocadoresEndereco(Connection con) throws SQLException {
        Statement st;
        HashSet<Object> list = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT l.id, l.nome, l.telefone, l.email, e.id, e.cidade, e.bairro, e.logradouro, e.numero FROM locadores l INNER JOIN enderecos e ON e.id = l.idendereco";
        ResultSet result = st.executeQuery(sql);
        while (result.next()){
            LocadoresBean lb = new LocadoresBean(result.getInt(1),result.getString(2),
                    result.getInt(3), result.getString(4), result.getInt(5));
            EnderecosBean eb = new EnderecosBean(result.getInt(5), result.getString(6),
                    result.getString(7),result.getString(8),result.getInt(9));
            lb.setEndereco(eb);
            list.add(lb);
        }
        return list;
    }
}
