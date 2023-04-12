package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final Connection con;

    public Conexao(){
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "root";
        String url = "jdbc:postgresql://localhost:5432/bibliotecaDB";

        try{
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){return this.con;}

    public void closeConnection(){
        try{
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
