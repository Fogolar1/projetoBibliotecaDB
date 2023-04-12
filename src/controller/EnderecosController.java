package controller;

import bean.EnderecosBean;
import model.EnderecosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class EnderecosController extends Controller{
    public EnderecosController(){
        this.model = new EnderecosModel();
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adição de Endereço : ");
        System.out.print("Id : ");
        int id = scanner.nextInt();
        System.out.print("Cidade : ");
        String cidade = scanner.next();
        System.out.print("Bairro : ");
        String bairro = scanner.next();
        System.out.print("Logradouro : ");
        String logradouro = scanner.next();
        System.out.print("Numero : ");
        int numero = scanner.nextInt();
        EnderecosBean eb = new EnderecosBean(id,bairro, cidade, logradouro, numero);
        model.insert(con,eb);
        System.out.println("Endereço adicionado com sucesso");
    }

    @Override
    public String manipularAtualizacao(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do campo que você gostaria de atualizar");
        System.out.println("1 - Cidade");
        System.out.println("2 - Bairro");
        System.out.println("3 - Logradouro");
        System.out.println("4 - Numero");
        int campo = scanner.nextInt();

        return encontraNomeCampo(campo);
    }

    public String encontraNomeCampo(int campoNumero){
        switch (campoNumero){
            case 1 :
                return "cidade";
            case 2 :
                return "bairro";
            case 3 :
                return "logradouro";
            case 4 :
                return "numero";
            default:
                throw new RuntimeException("Campo Inválido");
        }
    }
}
