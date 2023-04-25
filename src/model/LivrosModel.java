package model;

import bean.LivrosBean;

import java.sql.*;
import java.util.*;

public class LivrosModel extends Model{
    @Override
    public void insert(Connection con, Object b) throws SQLException {
        LivrosBean lb = (LivrosBean) b;
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO livros (id, nome, idCategoria, idAutor) VALUES(?,?,?,?)");
        st.setInt(1, lb.getId());
        st.setString(2, lb.getNome());
        st.setInt(3, lb.getIdCategoria());
        st.setInt(4, lb.getIdAutor());
        st.execute();
        st.close();
    }

    @Override
    public StringBuilder manipulaUpdate() {
        return new StringBuilder("UPDATE livros SET ");
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM livros WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    @Override
    public HashSet<Object>  selectAll(Connection con) throws SQLException {
        Statement st;
        HashSet<Object>  hashList = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, nome, idCategoria, idAutor FROM livros ORDER BY id DESC";
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            hashList.add(new LivrosBean(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4)));
        }
        return hashList;
    }

    @Override
    public Object select(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("SELECT id, nome, idCategoria, idAutor FROM livros WHERE id = ? ORDER BY id DESC");
        st.setInt(1,id);
        ResultSet result = st.executeQuery();
        result.next();
        return new LivrosBean(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
    }
}
