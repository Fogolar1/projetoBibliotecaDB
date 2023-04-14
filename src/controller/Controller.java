package controller;

import model.Model;
import printer.Printer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Controller {

    Model model;

    public void manipulaOperacao(int op, Connection con) {
        try{
            switch (op){
                case 1:
                    adicionar(con);
                    break;
                case 2:
                    atualizar(con);
                    break;
                case 3:
                    deletar(con);
                    break;
                case 4:
                    listarTodos(con);
                    break;
                case 5:
                    listar(con);
                    break;
            }
        }catch(SQLException | IllegalAccessException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public abstract  void adicionar(Connection con) throws SQLException;

    public void listar(Connection con) throws SQLException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o id que deseja selecionar : ");
        int id = scanner.nextInt();
        Object bean = this.model.select(con, id);
        Printer.printObject(bean);
    }

    public void listarTodos(Connection con) throws SQLException {
        HashSet<Object> hash = this.model.selectAll(con);
        Printer.printHeader(hash.iterator().next());
        Printer.printList(hash);
    }

    public void deletar(Connection con) throws SQLException{
        listarTodos(con);
        System.out.println("Qual id você gostaria de remover?");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        this.model.delete(con, id);
    }

    public void atualizar(Connection con) throws SQLException{
        listarTodos(con);
        HashMap<String, Object> campos = new HashMap<>();
        System.out.println("Qual id você gostaria de atualizar?");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        int continuar;
        do {
            String nomeCampo = this.manipularAtualizacao();
            System.out.println("Digite o novo valor desejado para o campo");
            String novoValor = scanner.next();
            campos.remove(nomeCampo);
            campos.put(nomeCampo, novoValor);
            System.out.println("Deseja editar mais algum campo do registro " + id + "? \n Digite 1 para editar outro campo");
            continuar = scanner.nextInt();
        }while (continuar == 1);

        this.model.update(con, id, campos);
    }


    public abstract String manipularAtualizacao();
}
