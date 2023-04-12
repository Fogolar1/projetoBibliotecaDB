package controller;

import bean.CategoriasBean;
import model.CategoriasModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CategoriasController extends Controller{
    public CategoriasController(){
        this.model = new CategoriasModel();
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adição de Categoria : ");
        System.out.print("Id : ");
        int id = scanner.nextInt();
        System.out.print("Nome : ");
        String nome = scanner.next();
        CategoriasBean cb = new CategoriasBean(id,nome);
        model.insert(con,cb);

        System.out.println("Categoria adicionada com sucesso");
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
