package controller;

import bean.LocadoresBean;
import menu.MenuUtils;
import model.LocadoresModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LocadoresController extends Controller{
    public LocadoresController(){
        this.model = new LocadoresModel();
        this.campos = new LinkedHashMap<>();
        this.campos.put("Id", 1);
        this.campos.put("Nome", 2);
        this.campos.put("Email", 2);
        this.campos.put("Telefone", 1);
        this.campos.put("Id Endereco", 1);
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        ArrayList<Object> valores = MenuUtils.mostraCamposAdicionar(campos);
        LocadoresBean lb = new LocadoresBean((Integer)valores.get(0), (String) valores.get(1),(String) valores.get(2), (Integer) valores.get(3), (Integer)valores.get(4));
        model.insert(con,lb);

        System.out.println("Locador adicionado com sucesso");
    }
}
