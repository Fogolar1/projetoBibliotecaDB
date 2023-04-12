package util;

import bean.*;

import java.lang.reflect.Field;

public class PrinterUtils {
    public static void printObject(Object bean){
        StringBuilder resultado = new StringBuilder();
        if(bean instanceof AutoresBean){
            resultado.append(((AutoresBean) bean).getId()).append("  ")
                    .append(((AutoresBean) bean).getNome());
        }else if(bean instanceof CategoriasBean){
            resultado.append(((CategoriasBean) bean).getId()).append("  ").
                    append(((CategoriasBean) bean).getNome());
        }else if(bean instanceof EnderecosBean){
            resultado.append(((EnderecosBean) bean).getId()).append("  ").
                    append(((EnderecosBean) bean).getCidade()).append("  ").
                    append(((EnderecosBean) bean).getBairro()).append("  ").
                    append(((EnderecosBean) bean).getLogradouro()).append("  ").
                    append(((EnderecosBean) bean).getNumero());
        }else if(bean instanceof LivrosBean){
            resultado.append(((LivrosBean) bean).getId()).append("  ").
                    append(((LivrosBean) bean).getNome()).append("  ").
                    append(((LivrosBean) bean).getIdAutor()).append("  ").
                    append(((LivrosBean) bean).getIdCategoria());
        }else if(bean instanceof LocadoresBean){
            resultado.append(((LocadoresBean) bean).getId()).append("  ").
                    append(((LocadoresBean) bean).getNome()).append("  ").
                    append(((LocadoresBean) bean).getEmail()).append("  ").
                    append(((LocadoresBean) bean).getTelefone()).append("  ").
                    append(((LocadoresBean) bean).getIdEndereco());
        }else if(bean instanceof LocacoesBean){
            resultado.append(((LocacoesBean) bean).getIdLocador()).append("  ").
                    append(((LocacoesBean) bean).getIdLivro()).append("  ").
                    append(((LocacoesBean) bean).getDataInicio()).append("  ").
                    append(((LocacoesBean) bean).getDataFim());
        }
        System.out.println(resultado);
    }

    public static void printHeader(Object bean){
        Field[] fields = bean.getClass().getDeclaredFields();
        StringBuilder resultado = new StringBuilder("Resultado : \n");
        for(Field field : fields ){
            resultado.append(field.getName()).append("  ");
        }
        resultado.append("\n");

        System.out.print(resultado);
    }

}
