package controller;

import bean.AutoresBean;
import model.AutoresModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AutoresController extends Controller {
    public AutoresController(){
        this.model = new AutoresModel();
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adição de Autor : ");
        System.out.print("Id : ");
        int id = scanner.nextInt();
        System.out.print("Nome : ");
        String nome = scanner.next();
        AutoresBean ab = new AutoresBean(id,nome);
        model.insert(con,ab);
        System.out.println("Autor adicionado com sucesso");
    }
    @Override
    public String manipularAtualizacao(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do campo que você gostaria de atualizar");
        System.out.println("1 - Nome");
        int campo = scanner.nextInt();
        return campo == 1 ? "nome" : null;
    }
}