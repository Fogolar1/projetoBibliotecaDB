package controller;

import bean.LocadoresBean;
import model.LocadoresModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LocadoresController extends Controller{
    public LocadoresController(){
        this.model = new LocadoresModel();
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adição de Locador : ");
        System.out.print("Id : ");
        int id = scanner.nextInt();
        scanner.nextLine(); //resolve problemas de buffer
        System.out.print("Nome : ");
        String nome = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Telefone : ");
        int telefone = scanner.nextInt();
        System.out.print("Id do Endereco : ");
        int idEndereco = scanner.nextInt();
        LocadoresBean lb = new LocadoresBean(id, nome, telefone, email, idEndereco);
        model.insert(con,lb);

        System.out.println("Locador adicionado com sucesso");
    }

    @Override
    public String manipularAtualizacao(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do campo que você gostaria de atualizar");
        System.out.println("1 - Nome");
        System.out.println("2 - Telefone");
        System.out.println("3 - Email");
        System.out.println("4 - Id do Endereço");
        int campo = scanner.nextInt();

        return encontraNomeCampo(campo);
    }
    public String encontraNomeCampo(int campoNumero){
        switch (campoNumero){
            case 1 :
                return "nome";
            case 2 :
                return "telefone";
            case 3 :
                return "email";
            case 4 :
                return "idendereco";
            default:
                throw new RuntimeException("Campo Inválido");
        }
    }
}
