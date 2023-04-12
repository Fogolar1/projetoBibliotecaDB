package controller;

import bean.LivrosBean;
import model.LivrosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LivrosController extends Controller {
    public LivrosController(){
        this.model = new LivrosModel();
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adição de Livro : ");
        System.out.print("Id : ");
        int id = scanner.nextInt();
        System.out.print("Nome : ");
        String nome = scanner.next();
        System.out.print("Id da Categoria : ");
        int idCategoria = scanner.nextInt();
        System.out.print("Id do Autor : ");
        int idAutor = scanner.nextInt();
        LivrosBean lb = new LivrosBean(id,nome, idCategoria, idAutor);
        model.insert(con,lb);

        System.out.println("Livro adicionado com sucesso");
    }

    @Override
    public String manipularAtualizacao(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do campo que você gostaria de atualizar");
        System.out.println("1 - Nome");
        System.out.println("2 - Id da Categoria");
        System.out.println("3 - Id do Autor");
        int campo = scanner.nextInt();

        return encontraNomeCampo(campo);
    }

    public String encontraNomeCampo(int campoNumero){
        switch (campoNumero){
            case 1 :
                return "nome";
            case 2 :
                return "idcategoria";
            case 3 :
                return "idautor";
            default:
                throw new RuntimeException("Campo Inválido");
        }
    }
}
