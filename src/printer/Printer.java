package printer;

import bean.*;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;



public class Printer {

    private static int tableLength = 0;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public static void printObject(Object bean){
        StringBuilder resultado = new StringBuilder();
        if(bean instanceof AutoresBean){
            resultado.append(printAutor((AutoresBean) bean));

        }else if(bean instanceof CategoriasBean){
            resultado.append(printCategoria((CategoriasBean) bean));

        }else if(bean instanceof EnderecosBean){
            resultado.append(printEndereco((EnderecosBean) bean));

        }else if(bean instanceof LivrosBean){
            LivrosBean lb = (LivrosBean) bean;
            resultado.append(printLivros(lb));
            if(Objects.nonNull(lb.getCategorias()) && Objects.nonNull(lb.getAutor()) ){
                resultado.append(" | ").append(printCategoria(lb.getCategorias())).
                        append(" | ").append(printAutor(lb.getAutor()));
            }
        }else if(bean instanceof LocadoresBean){
            LocadoresBean lb = (LocadoresBean) bean;
            resultado.append(printLocadores(lb));
            if(Objects.nonNull(lb.getEndereco()))
                resultado.append(printEndereco(lb.getEndereco()));

        }else if(bean instanceof LocacoesBean){
            LocacoesBean lb = (LocacoesBean) bean;
            resultado.append(printLocacoes(lb));
            if(Objects.nonNull(lb.getLivros()) && Objects.nonNull(lb.getLocadores()))
                resultado.append(printLivros(lb.getLivros())).
                        append(printLocadores(lb.getLocadores()));
        }
        System.out.println("|" + resultado);
    }

    public static void printList(HashSet<Object> lista){
        for(Object bean : lista){
            printObject(bean);
        }
        printLine();
    }

    public static void printLine(){
        System.out.println(" " + "-".repeat(tableLength - 1));
    }
    public static void printHeader(Object bean) {
        String header = PrinterUtils.findHeaderValue(bean.getClass().getDeclaredFields(), bean);
        tableLength = header.length();
        printLine();
        System.out.println("|" + header);
        printLine();
    }

    public static String printAutor(AutoresBean ab){
        return PrinterUtils.adicionarPadding(ab.getId() + " | " +
                ab.getNome());
    }

    public static String printCategoria(CategoriasBean cb){
        return PrinterUtils.adicionarPadding(cb.getId() + " | " +
                cb.getNome());
    }

    public static String printEndereco(EnderecosBean eb){
        return PrinterUtils.adicionarPadding(eb.getId() + " | " +
                eb.getCidade() + " | " +
                eb.getBairro() + " | " +
                eb.getLogradouro() + " | " +
                eb.getNumero());
    }

    public static String printLivros(LivrosBean lb){
        return PrinterUtils.adicionarPadding(lb.getId() + " | " +
                lb.getNome() + " | " +
                lb.getIdAutor() + " | " +
                lb.getIdCategoria());
    }

    public static String printLocadores(LocadoresBean lb){
        return PrinterUtils.adicionarPadding(lb.getId() + " | " +
                lb.getNome() + " | " +
                lb.getEmail() + " | " +
                lb.getTelefone() + " | " +
                lb.getIdEndereco());
    }

    public static String printLocacoes(LocacoesBean lb){
        return PrinterUtils.adicionarPadding(lb.getIdLocador() + " | " +
                lb.getIdLivro() + " | " +
                lb.getDataInicio().format(formatter) + " | " +
                lb.getDataFim().format(formatter));
    }

}
