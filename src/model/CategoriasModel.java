package model;

import bean.CategoriasBean;

import java.sql.*;
import java.util.*;

public class CategoriasModel extends Model{
    @Override
    public void insert(Connection con, Object b) throws SQLException {
        CategoriasBean cb = (CategoriasBean) b;
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO categorias (id, nome) VALUES(?,?)");
        st.setInt(1, cb.getId());
        st.setString(2, cb.getNome());
        st.execute();
        st.close();
    }

    @Override
    public StringBuilder manipulaUpdate() {
        return new StringBuilder("UPDATE categorias SET ");
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM categorias WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    @Override
    public HashSet<Object>  selectAll(Connection con) throws SQLException {
        Statement st;
        HashSet<Object>  hashList = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, nome FROM categorias ORDER BY id DESC";
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            hashList.add(new CategoriasBean(result.getInt(1), result.getString(2)));
        }
        return hashList;
    }

    @Override
    public Object select(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("SELECT id, nome FROM categorias WHERE id = ? ORDER BY id DESC");
        st.setInt(1,id);
        ResultSet result = st.executeQuery();
        result.next();
        return new CategoriasBean(result.getInt(1), result.getString(2));
    }
}
