package util;

import bean.*;

import java.lang.reflect.Field;
import java.util.Objects;

public class PrinterUtils {
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
            if(Objects.nonNull(lb.getEndereco())){
                resultado.append(" | ").append(printEndereco(lb.getEndereco()));
            }
        }else if(bean instanceof LocacoesBean){
            LocacoesBean lb = (LocacoesBean) bean;
            resultado.append(printLocacoes(lb));
            if(Objects.nonNull(lb.getLivros()) && Objects.nonNull(lb.getLocadores())){
                resultado.append(" | ").append(printLivros(lb.getLivros())).
                        append(" | ").append(printLocadores(lb.getLocadores()));
            }
        }
        System.out.println(resultado);
    }

    public static void printHeader(Object bean){
        System.out.print("Resultado : \n" + findHeaderValue(bean.getClass().getDeclaredFields()) + "\n");
    }

    public static String findHeaderValue(Field[] fields){
        StringBuilder header = new StringBuilder();
        for(Field field : fields ){
            if(field.getType().getName().contains("bean")){
                header.append(findHeaderValue(field.getType().getDeclaredFields()));
            }else{
                header.append(field.getName()).append("   ");
            }
        }

        return header.toString();
    }

    public static String printAutor(AutoresBean ab){
        return ab.getId() + " | " +
                ab.getNome();
    }

    public static String printCategoria(CategoriasBean cb){
        return cb.getId() + " | " +
                cb.getNome();
    }

    public static String printEndereco(EnderecosBean eb){
        return eb.getId() + " | " +
                eb.getCidade() + " | " +
                eb.getBairro() + " | " +
                eb.getLogradouro() + " | " +
                eb.getNumero();
    }

    public static String printLivros(LivrosBean lb){
        return lb.getId() + " | " +
                lb.getNome() + " | " +
                lb.getIdAutor() + " | " +
                lb.getIdCategoria();
    }

    public static String printLocadores(LocadoresBean lb){
        return lb.getId() + " | " +
                lb.getNome() + " | " +
                lb.getEmail() + " | " +
                lb.getTelefone() + " | " +
                lb.getIdEndereco();
    }

    public static String printLocacoes(LocacoesBean lb){
        return lb.getIdLocador() + " | " +
                lb.getIdLivro() + " | " +
                lb.getDataInicio() + " | " +
                lb.getDataFim();
    }

}
