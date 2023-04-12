package model;

import bean.LocadoresBean;
import java.sql.*;
import java.util.*;

public class LocadoresModel extends Model{
    @Override
    public void insert(Connection con, Object b) throws SQLException {
        LocadoresBean lb = (LocadoresBean) b;
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO locadores (id, nome, telefone, email, idEndereco) VALUES (?,?,?,?,?)");
        st.setInt(1, lb.getId());
        st.setString(2, lb.getNome());
        st.setInt(3, lb.getTelefone());
        st.setString(4, lb.getEmail());
        st.setInt(5, lb.getIdEndereco());
        st.execute();
        st.close();
    }

    @Override
    public StringBuilder manipulaUpdate(){
       return new StringBuilder("UPDATE locadores SET  ");
    }

    @Override
    public void delete(Connection con, int id) throws SQLException{
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM locadores WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    @Override
    public HashSet<Object>  selectAll(Connection con) throws SQLException{
        Statement st;
        HashSet<Object>  hashList = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT id, nome, telefone, email, idEndereco FROM locadores";
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            hashList.add(new LocadoresBean(result.getInt(1), result.getString(2), result.getInt(3),
                    result.getString(4),result.getInt(5)));
        }
        return hashList;
    }

    @Override
    public Object select(Connection con, int id) throws SQLException{
        PreparedStatement st;
        st = con.prepareStatement("SELECT id, nome, telefone, email, idEndereco FROM locadores WHERE id = ?");
        st.setInt(1,id);
        ResultSet result = st.executeQuery();
        result.next();
        return new LocadoresBean(result.getInt(1), result.getString(2), result.getInt(3),
                result.getString(4), result.getInt(5));
    }
}
