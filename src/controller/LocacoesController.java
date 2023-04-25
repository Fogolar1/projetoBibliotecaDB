package controller;

import bean.LocacoesBean;
import menu.MenuUtils;
import model.LocacoesModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LocacoesController extends Controller {

    public LocacoesController(){
        this.model = new LocacoesModel();
        this.campos =  new LinkedHashMap<>();
        this.campos.put("Id", 1);
        this.campos.put("Id Livro", 1);
        this.campos.put("Id Locador", 1);
        this.campos.put("Data Inicio", 2);
        this.campos.put("Data Fim", 2);
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        ArrayList<Object> valores = MenuUtils.mostraCamposAdicionar(campos);
        LocalDate dataInicio = LocalDate.parse((String) valores.get(3), formatter);
        LocalDate dataFim = LocalDate.parse((String) valores.get(4), formatter);

        LocacoesBean lb = new LocacoesBean((Integer) valores.get(0),(Integer) valores.get(1), (Integer) valores.get(2), dataInicio,dataFim);
        model.insert(con,lb);

        System.out.println("Locação adicionada com sucesso");
    }
}
