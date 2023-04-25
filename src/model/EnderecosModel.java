package model;

import bean.EnderecosBean;

import java.sql.*;
import java.util.*;

public class EnderecosModel extends Model{
    @Override
    public void insert(Connection con, Object b) throws SQLException {
        EnderecosBean eb = (EnderecosBean) b;
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO enderecos (id, bairro, cidade, logradouro, numero) VALUES (?,?,?,?,?) ");
        st.setInt(1,eb.getId());
        st.setString(2, eb.getBairro());
        st.setString(3, eb.getCidade());
        st.setString(4, eb.getLogradouro());
        st.setInt(5, eb.getNumero());
        st.execute();
        st.close();
    }

    @Override
    public StringBuilder manipulaUpdate() {
        return new StringBuilder("UPDATE enderecos SET ");
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM enderecos WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    @Override
    public HashSet<Object> selectAll(Connection con) throws SQLException {
        Statement st;
        HashSet<Object>  hashList = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, cidade, bairro, logradouro, numero FROM enderecos ORDER BY id DESC";
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            hashList.add(new EnderecosBean(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4),result.getInt(5)));
        }
        return hashList;
    }

    @Override
    public Object select(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("SELECT id, cidade, bairro, logradouro,  numero FROM enderecos WHERE id = ? ORDER BY id DESC");
        st.setInt(1,id);
        ResultSet result = st.executeQuery();
        result.next();
        return new EnderecosBean(result.getInt(1), result.getString(2), result.getString(3),
                result.getString(4), result.getInt(5));
    }
}
