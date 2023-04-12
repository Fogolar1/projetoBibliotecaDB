import controller.*;
import database.Conexao;
import report.Report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op;
        int tabela;
        int rel;
        do{
            op = menuOperacao();
            rel = 0;
            if(op > 0 && op < 5){
                if(op == 4){
                    System.out.println("Selecione o numero do relatório desejado");
                    System.out.println("1 - Selecionar todos os itens de uma tabela");
                    System.out.println("2 - Selecionar um único item de uma tabela");
                    System.out.println("3 - Relatórios de livro por categoria e autor");
                    System.out.println("4 - Relatório de locações por livro");
                    System.out.println("5 - Relatório de locadores por endereço");
                    Scanner scanner = new Scanner(System.in);
                    rel = scanner.nextInt();
                }
                if(rel >= 0 && rel < 6) {
                    if(rel > 2){
                        switch (rel){
                            case 3:
                                try
                                {
                                    Report.relatorioLivroPorCategoriaEAutor(con);
                                }
                                catch (SQLException e)
                                {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case 4:
                                try
                                {
                                    Report.relatorioLocacoesPorLivro(con);
                                }
                                catch (SQLException e)
                                {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case 5:
                                try
                                {
                                    Report.relatorioLocadoresEndereco(con);
                                }
                                catch (SQLException e)
                                {
                                    throw new RuntimeException(e);
                                }
                                break;
                        }
                    }else{
                        if(rel != 0) {
                            rel--;
                        }
                        tabela = menuTabela();
                        switch (tabela) {
                            case 1:
                                (new LivrosController()).manipulaOperacao(op + rel, con);
                                break;
                            case 2:
                                (new AutoresController()).manipulaOperacao(op + rel, con);
                                break;
                            case 3:
                                (new CategoriasController()).manipulaOperacao(op + rel, con);
                                break;
                            case 4:
                                (new LocadoresController()).manipulaOperacao(op + rel, con);
                                break;
                            case 5:
                                (new EnderecosController()).manipulaOperacao(op + rel, con);
                                break;
                            case 6:
                                (new LocacoesController()).manipulaOperacao(op + rel, con);
                                break;
                            default:
                                throw new RuntimeException("Tabela inválida");
                        }
                    }
                }
            }

        }while (op > 0 && op < 5);
    }

    public static int menuOperacao(){
        System.out.println("Informe o número da operação desejada");
        System.out.println("1 - Adicionar Valor");
        System.out.println("2 - Editar Valor");
        System.out.println("3 - Deletar Valor");
        System.out.println("4 - Relatórios");
        Scanner scanner = new Scanner((System.in));
        return scanner.nextInt();
    }

    public static int menuTabela(){
        System.out.println("Informe o número da tabela que deseja executar a operação");
        System.out.println("1 - Livros");
        System.out.println("2 - Autores");
        System.out.println("3 - Categorias");
        System.out.println("4 - Locadores");
        System.out.println("5 - Endereços");
        System.out.println("6 - Locações");
        Scanner scanner = new Scanner((System.in));
        return scanner.nextInt();
    }
}