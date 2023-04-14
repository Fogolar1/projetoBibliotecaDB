import database.Conexao;
import menu.MenuUtils;

import java.sql.Connection;
public class Main {
    public static void main(String[] args) {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        boolean continuar;
        do
        {
            continuar = MenuUtils.menuPrincipal(con);
        }
        while (continuar);
    }


}