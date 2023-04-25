package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public abstract class Model {
    public  abstract void insert(Connection con, Object b) throws SQLException;
    public void update(Connection con, int id, HashMap<String, Object> campos) throws SQLException{
        StringBuilder string = manipulaUpdate();
        List<Object> valores = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Object> entry : campos.entrySet()) {
            if(i != 0){
                string.append(" , ");
            }
            String campo = entry.getKey().replaceAll("\\s", "");
            Object valor = entry.getValue();
            valores.add(valor);
            string.append(campo).append(" = ? ");
            i++;
        }
        string.append("WHERE id = ?");
        i = 0;
        PreparedStatement st;
        st = con.prepareStatement(String.valueOf(string));
        for (Object obj : valores){
            i++;
            if(obj instanceof String){
                st.setString(i, (String) obj);
            }else if(obj instanceof Integer) {
                st.setInt(i, (Integer) obj );
            }else if(obj instanceof LocalDate){
                st.setObject(i, obj);
            }else{
                throw new RuntimeException("Este valor não pode ser adicionado a esse campo");
            }
        }
        st.setInt(i+1, id);
        st.execute();
        st.close();
    }
    public abstract StringBuilder manipulaUpdate();
    public abstract void delete(Connection con, int id) throws SQLException;
    public abstract HashSet<Object> selectAll(Connection con) throws SQLException;
    public abstract Object select(Connection con, int id) throws SQLException;
}
