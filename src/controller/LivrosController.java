package controller;

import bean.LivrosBean;
import menu.MenuUtils;
import model.LivrosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LivrosController extends Controller {
    public LivrosController(){
        this.model = new LivrosModel();
        this.campos = new LinkedHashMap<>();
        this.campos.put("Id", 1);
        this.campos.put("Nome", 2);
        this.campos.put("Id Categoria", 1);
        this.campos.put("Id Autor", 1);
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        System.out.println("Adição de Livro : ");
        ArrayList<Object> valores = MenuUtils.mostraCamposAdicionar(campos);
        LivrosBean lb = new LivrosBean((Integer) valores.get(0),(String) valores.get(1),(Integer) valores.get(2),(Integer) valores.get(3));
        model.insert(con,lb);
        System.out.println("Livro adicionado com sucesso");
    }

}
