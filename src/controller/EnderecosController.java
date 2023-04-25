package controller;

import bean.EnderecosBean;
import menu.MenuUtils;
import model.EnderecosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EnderecosController extends Controller{
    public EnderecosController(){
        this.model = new EnderecosModel();
        this.campos = new LinkedHashMap<>();
        this.campos.put("Id", 1);
        this.campos.put("Cidade", 2);
        this.campos.put("Bairro", 2);
        this.campos.put("Logradouro", 2);
        this.campos.put("Numero", 1);
    }

    @Override
    public void adicionar(Connection con) throws SQLException {
        System.out.println("Adição de Endereço : ");
        ArrayList<Object> valores = MenuUtils.mostraCamposAdicionar(campos);
        EnderecosBean eb = new EnderecosBean((Integer) valores.get(0),(String) valores.get(1),(String) valores.get(2),(String) valores.get(3),(Integer) valores.get(4));
        model.insert(con,eb);
        System.out.println("Endereço adicionado com sucesso");
    }


}
