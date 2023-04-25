package controller;

import bean.CategoriasBean;
import menu.MenuUtils;
import model.CategoriasModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CategoriasController extends Controller{
    public CategoriasController(){
        this.model = new CategoriasModel();
        this.campos = new LinkedHashMap<>();
        this.campos.put("Id", 1);
        this.campos.put("Nome", 2);
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        System.out.println("Adição de Categoria : ");
        ArrayList<Object> valores = MenuUtils.mostraCamposAdicionar(campos);
        CategoriasBean cb = new CategoriasBean((Integer) valores.get(0), (String) valores.get(1));
        model.insert(con,cb);

        System.out.println("Categoria adicionada com sucesso");
    }

}
