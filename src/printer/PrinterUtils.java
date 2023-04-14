package printer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class PrinterUtils {
    public static String findHeaderValue(Field[] fields, Object context){
        StringBuilder header = new StringBuilder();
        for(Field field : fields ){
            if(hasChild(field)) {
                if(hasValidChild(field, context))
                    header.append(String.format("%-45s|",findHeaderValue(field.getType().getDeclaredFields(), context)));
            }else{
                header.append(String.format("%-45s|",field.getName()));
            }
        }
        return header.toString().replaceAll("\\|\\|", "|");
    }

    public static String primeiraLetraMaiuscula(String string){
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static Object encontraValor(Field field, Object context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(context.getClass() != field.getDeclaringClass())
            return null;
        Method getter = context.getClass().getMethod("get" + primeiraLetraMaiuscula(field.getName()));
        return getter.invoke(context);
    }
    public static boolean hasValidChild(Field field, Object context){
        if(!hasChild(field))
            return false;
        try
        {
            if(Objects.nonNull(encontraValor(field, context)))
                return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean hasChild(Field field){
        return field.getType().getName().contains("bean");
    }

    public static String adicionarPadding(String str){
        String[] campos = str.split("\\|");
        StringBuilder camposFormatados = new StringBuilder();
        for(String campo : campos){
            camposFormatados.append(String.format("%-45s|", campo));
        }

        return camposFormatados.toString();
    }

}
