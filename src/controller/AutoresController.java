package controller;

import bean.AutoresBean;
import menu.MenuUtils;
import model.AutoresModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AutoresController extends Controller {
    public AutoresController(){
        this.model = new AutoresModel();
        this.campos  = new LinkedHashMap<>();
        this.campos.put("Id" , 1);
        this.campos.put("Nome" , 2);
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        ArrayList<Object> valores = MenuUtils.mostraCamposAdicionar(campos);
        AutoresBean ab = new AutoresBean((Integer) valores.get(0), (String) valores.get(1));
        model.insert(con,ab);
        System.out.println("Autor adicionado com sucesso");
    }
}