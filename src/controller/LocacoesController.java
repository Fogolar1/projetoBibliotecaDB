package controller;

import bean.LocacoesBean;
import model.LocacoesModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LocacoesController extends Controller {

    public LocacoesController(){
        this.model = new LocacoesModel();
    }
    @Override
    public void adicionar(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Adição de Locação : ");
        System.out.print("Id do Livro : ");
        int idLivro = scanner.nextInt();
        System.out.print("Id do Locador : ");
        int idLocador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Exemplo de inserção de data : 20/07/2023");
        System.out.print("Data de início da locação : ");
        String dataInicioStr = scanner.nextLine();
        System.out.print("Data final da locação : ");
        String dataFimStr = scanner.nextLine();

        LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
        LocalDate dataFim = LocalDate.parse(dataFimStr, formatter);

        LocacoesBean lb = new LocacoesBean(idLivro, idLocador, dataInicio,dataFim);
        model.insert(con,lb);

        System.out.println("Locação adicionada com sucesso");
    }

    @Override
    public String manipularAtualizacao(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do campo que você gostaria de atualizar");
        System.out.println("1 - Id do Livro");
        System.out.println("2 - Id do Locador");
        System.out.println("3 - Data de início da locação");
        System.out.println("4 - Data final da locação");
        int campoNumero = scanner.nextInt();

        return encontraNomeCampo(campoNumero);
    }

    public String encontraNomeCampo(int campoNumero){
        switch (campoNumero){
            case 1 :
                return "idlivro";
            case 2 :
                return "idlocador";
            case 3 :
                return "datainicio";
            case 4 :
                return "datafim";
            default:
                throw new RuntimeException("Campo Inválido");
        }
    }
}
