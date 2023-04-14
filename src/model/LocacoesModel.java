package model;

import bean.LocacoesBean;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class LocacoesModel extends Model {
    @Override
    public void insert(Connection con, Object b) throws SQLException {
        LocacoesBean lb = (LocacoesBean) b;
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO locacoes(idLivro, idLocador, dataInicio, dataFim) VALUES (?,?,?,?)");
        st.setInt(1, lb.getIdLivro());
        st.setInt(2, lb.getIdLocador());
        st.setObject(3, lb.getDataInicio());
        st.setObject(4, lb.getDataFim());
        st.execute();
        st.close();
    }

    @Override
    public StringBuilder manipulaUpdate() {
        return new StringBuilder("UPDATE locacoes SET ");
    }

    @Override
    public void delete(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("DELETE FROM locacoes WHERE id = ?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    @Override
    public HashSet<Object>  selectAll(Connection con) throws SQLException {
        Statement st;
        HashSet<Object>  hashList = new HashSet<>();
        st = con.createStatement();
        String sql = "SELECT idlivro, idlocador, dataInicio, dataFim FROM locacoes";
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            hashList.add(new LocacoesBean(result.getInt(1), result.getInt(2), ((Date) result.getObject(3)).toLocalDate(),
                    ((Date) result.getObject(4)).toLocalDate()));
        }
        return hashList;
    }

    @Override
    public Object select(Connection con, int id) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("SELECT idlivro, idlocador, dataInicio, dataFim FROM locacoes WHERE id = ?");
        st.setInt(1,id);
        ResultSet result = st.executeQuery();
        result.next();
        return new LocacoesBean(result.getInt(1), result.getInt(2),(LocalDate) result.getObject(3),
                (LocalDate) result.getObject(4));
    }
}
