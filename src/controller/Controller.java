package controller;

import menu.MenuUtils;
import model.Model;
import printer.Printer;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Controller {
    Model model;

    LinkedHashMap<String, Integer> campos;
    protected final Scanner scanner = new Scanner(System.in);
    private final String[] methods = {"adicionar", "atualizar", "deletar", "listarTodos", "listar"};

    public void manipulaOperacao(int op, Connection con) {
        try{
            this.getClass().getSuperclass().getMethod(methods[op - 1], Connection.class).invoke(this, con);
        }catch(IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public abstract void adicionar(Connection con) throws SQLException;

    public void atualizar(Connection con) throws SQLException{
        listarTodos(con);
        HashMap<String, Object> campos = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Qual id você gostaria de atualizar?");
        int id = scanner.nextInt();
        int continuar;
        do {
            String nomeCampo = this.manipularAtualizacao();
            int tipoDoCampo =  this.campos.get(nomeCampo);
            Object novoValor = null;
            System.out.println("Digite o novo valor desejado para o campo");
            scanner.nextLine(); //resolve problemas de buffer
            switch (tipoDoCampo){
                case 1 :
                    novoValor = scanner.nextInt();
                    scanner.nextLine(); //resolver buffer
                    break;
                case 2 :
                    novoValor = scanner.nextLine();
                    break;
            }
            if(nomeCampo.contains("Data") && Objects.nonNull(novoValor) && novoValor instanceof String){
                novoValor = LocalDate.parse((String) novoValor, formatter);
            }

            campos.remove(nomeCampo);
            campos.put(nomeCampo, novoValor);
            System.out.println("Deseja editar mais algum campo do registro " + id + "? \n Digite 1 para editar outro campo");
            continuar = scanner.nextInt();
        }while (continuar == 1);

        this.model.update(con, id, campos);
        System.out.println("Registro alterado com sucesso!");
    }

    public void deletar(Connection con) throws SQLException{
        listarTodos(con);
        System.out.println("Qual id você gostaria de remover?");
        int id = scanner.nextInt();
        this.model.delete(con, id);
        System.out.println("Deletado com sucesso!");
    }
    public void listarTodos(Connection con) throws SQLException {
        HashSet<Object> hash = this.model.selectAll(con);
        Printer.printHeader(hash.iterator().next());
        Printer.printList(hash);
    }
    public void listar(Connection con) throws SQLException, IllegalAccessException {
        System.out.println("Insira o id que deseja selecionar : ");
        int id = scanner.nextInt();
        Object bean = this.model.select(con, id);
        Printer.printHeader(bean);
        Printer.printObject(bean);
        Printer.printLine();
    }

    public String manipularAtualizacao(){
        return campos.keySet().toArray()[MenuUtils.mostraCamposAtualizar(campos)].toString();
    }
}
