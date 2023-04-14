package menu;

import controller.*;
import report.Report;
import util.PrinterUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class MenuUtils {

    public static boolean menuPrincipal(Connection con){
        int op = MenuUtils.menuOperacao();
        int rel = 0;
        if(op > 0 && op < 5){
            if(op == 4){
                rel = MenuUtils.menuRelatorio();
            }
            if(rel >= 0 && rel < 6) {
                if(rel > 2){
                    try
                    {
                        MenuUtils.manipulaRelatorioMenu(rel, con);
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                }else{
                    if(rel != 0) {
                        rel--;
                    }
                    MenuUtils.manipulaOperacaoMenu(MenuUtils.menuTabela(), op + rel, con);
                }
            }
        }

        return (op > 0 && op < 5);
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

    public static int menuRelatorio(){
        System.out.println("Selecione o numero do relatório desejado");
        System.out.println("1 - Selecionar todos os itens de uma tabela");
        System.out.println("2 - Selecionar um único item de uma tabela");
        System.out.println("3 - Relatórios de livro por categoria e autor");
        System.out.println("4 - Relatório de locações por livro");
        System.out.println("5 - Relatório de locadores por endereço");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void manipulaRelatorioMenu(int rel, Connection con) throws SQLException {
        HashSet<Object> relatorio = new HashSet<>();
        switch (rel){
            case 3:
                relatorio = Report.relatorioLivroPorCategoriaEAutor(con);
                break;
            case 4:
                relatorio = Report.relatorioLocacoesPorLivro(con);
                break;
            case 5:

                relatorio = Report.relatorioLocadoresEndereco(con);
                break;
        }
        resolvePrintRelatorio(relatorio);
    }

    public static void resolvePrintRelatorio(HashSet<Object> relatorio){
        PrinterUtils.printHeader(relatorio.iterator().next());
        for(Object bean : relatorio){
            PrinterUtils.printObject(bean);
        }
    }

    public static void manipulaOperacaoMenu(int tabela, int op, Connection con){
        switch (tabela) {
            case 1:
                (new LivrosController()).manipulaOperacao(op, con);
                break;
            case 2:
                (new AutoresController()).manipulaOperacao(op, con);
                break;
            case 3:
                (new CategoriasController()).manipulaOperacao(op, con);
                break;
            case 4:
                (new LocadoresController()).manipulaOperacao(op, con);
                break;
            case 5:
                (new EnderecosController()).manipulaOperacao(op, con);
                break;
            case 6:
                (new LocacoesController()).manipulaOperacao(op, con);
                break;
            default:
                throw new RuntimeException("Tabela inválida");
        }
    }

}
