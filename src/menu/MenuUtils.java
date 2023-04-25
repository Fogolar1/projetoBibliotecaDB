package menu;

import controller.*;
import printer.Printer;
import report.Report;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuUtils {

    private static final String[] controllers = {"Livros", "Autores", "Categorias", "Locadores", "Enderecos", "Locacoes"};

    private static final Scanner scanner = new Scanner(System.in);

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
        return scanner.nextInt();
    }

    public static void manipulaOperacaoMenu(int tabela, int op, Connection con){
        try{
            Controller controller = (Controller) Class.forName("controller." + controllers[tabela - 1] + "Controller").getConstructor().newInstance();
            controller.manipulaOperacao(op, con);
        }catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static int menuRelatorio(){
        System.out.println("Selecione o numero do relatório desejado");
        System.out.println("1 - Selecionar todos os itens de uma tabela");
        System.out.println("2 - Selecionar um único item de uma tabela");
        System.out.println("3 - Relatórios de livro por categoria e autor");
        System.out.println("4 - Relatório de locações por livro");
        System.out.println("5 - Relatório de locadores por endereço");
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
        mostraRelatorio(relatorio);
    }

    public static void mostraRelatorio(HashSet<Object> relatorio){
        Printer.printHeader(relatorio.iterator().next());
        Printer.printList(relatorio);
    }

    public static ArrayList<Object> mostraCamposAdicionar(LinkedHashMap<String, Integer> campos){
        scanner.nextLine();
        ArrayList<Object> lista = new ArrayList<>();
        campos.forEach((campo, tipo) -> {
            System.out.println(campo + " :");
            switch (tipo){
                case 1 :
                    lista.add(scanner.nextInt());
                    scanner.nextLine(); //resolver buffer
                    break;
                case 2 :
                    lista.add(scanner.nextLine());
                    break;
            }
        });
        return lista;
    }

    public static Integer mostraCamposAtualizar(LinkedHashMap<String, Integer> campos){
        AtomicInteger i = new AtomicInteger();
        System.out.println("Digite o número do campo que você gostaria de atualizar");
        campos.forEach((campo, tipo) -> {
            if(!campo.equalsIgnoreCase("id")){
                i.getAndIncrement();
                System.out.println(i + " - " + campo);
            }
        });

        return scanner.nextInt();
    }
}
