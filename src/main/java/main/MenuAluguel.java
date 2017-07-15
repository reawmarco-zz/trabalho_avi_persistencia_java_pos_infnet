package main;

import control.AluguelController;
import control.MotoristaController;
import entity.Aluguel;
import entity.Motorista;
import util.Util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class MenuAluguel {

    public static void menu() {
        System.out.println("#### MENU Aluguel #### \n ");
        System.out.println("1 - Cadastro Aluguel");
        System.out.println("2 - Atualização Cadastro Aluguel");
        System.out.println("3 - Deletar Cadastro Aluguel");
        System.out.println("4 - Listar Todos os Alugueis");
        System.out.println("5 - Buscar Aluguel por id");

        Scanner s = new Scanner(System.in);
        AluguelController aluguelController = new AluguelController();
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("#### MENU Cadastro Aluguel #### \n \n");
                try {
                    aluguelController.addAluguel(carregaInfoAluguel(false));
                    System.out.println("Cadastro realizada com sucesso! \n");
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro no cadastro do aluguel, retornando para o menu inicial \n");
                    Start.menuInicial();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Erro no cadastro do aluguel, retornando para o menu inicial \n");
                    Start.menuInicial();
                }
                break;

            case 2:
                try {
                    listarAlugueis(aluguelController.listAluguel());
                    aluguelController.atualizaAluguel(carregaInfoAluguel(true));
                    System.out.println("Atualização realizada com sucesso! \n");
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao atualizar cadastro do funcionario, retornando para o menu inicial \n");
                    Start.menuInicial();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao atualizar cadastro do funcionario, retornando para o menu inicial \n");
                    Start.menuInicial();
                }
                break;

            case 3:
                deletarAluguel();
                Start.menuInicial();
                break;

            case 4:
                try {
                    listarAlugueis(aluguelController.listAluguel());
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum aluguel localizado! \n");
                    Start.menuInicial();
                }
                break;

            case 5:
                try {
                    listaAluguel();
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum Aluguel localizado com este nome! \n");
                    Start.menuInicial();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum Aluguel localizado com este nome! \n");
                    Start.menuInicial();
                }
                break;

            default:
                System.out.println("Opção errada, voltando para o menu inicial \n");
                Start.menuInicial();

        }
    }

    public static Aluguel carregaInfoAluguel(boolean alterar) throws ParseException, SQLException {
        Aluguel aluguel = new Aluguel();
        AluguelController aluguelController = new AluguelController();
        String CNH;

        Scanner s = new Scanner(System.in);

        if(alterar){
            System.out.println("Digite o ID do Aluguel que deseja alterar:  ");
            int id = (Integer.valueOf(s.nextLine()));

            aluguel  = aluguelController.buscarAluguel(id);

            if(aluguel.getId() == null){
                System.out.println("Não foi localizado nenhum aluguel com este ID.");
                MenuAluguel.menu();
            }

            aluguel.setId(id);
        }

        Calendar calendar = Calendar.getInstance();
        aluguel.setDataAluguel(Util.convertToCalendar(calendar.getTime()));

        System.out.println("Data de Aluguel: " + aluguel.getDataAluguel().getTime());
        System.out.println("Digite a data de devolução: dd/MM/yyyy ");
        aluguel.setDataDevolucao(Util.convertToDate(s.nextLine()));

        System.out.println("Digite a data de entrega: dd/MM/yyyy ");
        aluguel.setDataEntrega(Util.convertToDate(s.nextLine()));

        System.out.println("Digite o Valor Total ");
        aluguel.setValorTotal(BigDecimal.valueOf(Double.valueOf(s.nextLine())));

        do{
            CNH = carregaInfoMotorista();
        }
        while (CNH == null);

        System.out.println("Número da CNH do Motorista: " + CNH);
        aluguel.setCnhMotorista(CNH);

        return aluguel;
    }

    public static void listarAlugueis(List<Aluguel> list) {
        System.out.println("#### MENU Resultado Pesquisa Aluguel #### \n");

        if (list.size() > 0) {
            for (Aluguel aluguel : list) {

                System.out.println("Data Aluguel: " + aluguel.getDataAluguel().getTime());
                System.out.println("Data Entrega: " + aluguel.getDataEntrega());
                System.out.println("Data Devolução: " + aluguel.getDataDevolucao());
                System.out.println("CNH Motorista: " + aluguel.getCnhMotorista());
                System.out.println("Valor Total: " + Util.convertBigDecimalToStringFormat(aluguel.getValorTotal()));
                System.out.println("ID Aluguel: " + aluguel.getId());
                System.out.println("######################################################## \n ");
            }
        } else {
            System.out.println("Nenhum cadastro de aluguel localizado! \n ");
            System.out.println("######################################################## \n ");
        }
    }

    public static void listaAluguel() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("#### MENU Busca Aluguel #### \n");

        System.out.println("Digite o ID: ");
        String id = s.nextLine();

        AluguelController aluguelController = new AluguelController();
        Aluguel aluguel = aluguelController.buscarAluguel(Integer.valueOf(id));

        if (aluguel != null) {
            System.out.println("Data Aluguel: " + aluguel.getDataAluguel().getTime());
            System.out.println("Data Entrega: " + aluguel.getDataEntrega());
            System.out.println("Data Devolução: " + aluguel.getDataDevolucao());
            System.out.println("CNH Motorista: " + aluguel.getCnhMotorista());
            System.out.println("Valor Total: " + Util.convertBigDecimalToStringFormat(aluguel.getValorTotal()));
            System.out.println("ID Aluguel: " + aluguel.getId());
            System.out.println("######################################################## \n ");
        } else {
            System.out.println("Nenhum cadastro de aluguel com este ID localizado! \n ");
            System.out.println("######################################################## \n ");
        }
    }

    public static void deletarAluguel() {

        AluguelController aluguelController = new AluguelController();

        System.out.println("#### MENU Deletar Aluguel #### \n ");

        try {
            listarAlugueis(aluguelController.listAluguel());
            System.out.println("Digite o número de ID do Aluguel que deseja deletar: ");
            Scanner s = new Scanner(System.in);
            String id = s.nextLine();

            Aluguel aluguel = aluguelController.buscarAluguel(Integer.valueOf(id));

            aluguelController.deleteAluguel(aluguel);
            System.out.println("Aluguel deletado com sucesso! \n");
            Start.menuInicial();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar o Aluguel! \n");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao deletar o Aluguel! \n");
        }
    }

    public static String carregaInfoMotorista() throws SQLException {

        Scanner s = new Scanner(System.in);
        System.out.println("#### MENU Busca Motorista #### \n");
        System.out.println("Digite o nome: ");
        String nome = s.nextLine();

        MotoristaController motoristaController = new MotoristaController();
        Motorista motorista = motoristaController.buscarMotorista(nome);

        if (motorista.getNumCNH() != null) {
            System.out.println("#### MENU Resultado Pesquisa Motorista #### \n ");
            System.out.println("Nome: " + motorista.getNome());
            System.out.println("CPF: " + motorista.getCpf());
            System.out.println("Num CNH: " + motorista.getNumCNH());
            System.out.println("######################################################## \n ");
        } else {
            System.out.println("Nenhum cadastro de motorista com este nome localizado! \n ");
            carregaListInfoMotorista();
        }

        return motorista.getNumCNH();
    }

    public static void carregaListInfoMotorista() throws SQLException {

        System.out.println("#### MENU Resultado Pesquisa Motoristas #### \n");
        MotoristaController motoristaController = new MotoristaController();
        List<Motorista> list =  motoristaController.listMotorista();

        if (list.size() > 0) {
            for (Motorista motorista : list) {
                System.out.println("Nome: " + motorista.getNome());
                System.out.println("CPF: " + motorista.getCpf());
                System.out.println("Num CNH: " + motorista.getNumCNH());
                System.out.println("######################################################## \n ");
            }
        } else {
            System.out.println("Nenhum cadastro de Motorista localizado! \n ");
            System.out.println("Cadastre um Motorista para continuar o aluguel... Você será direcionado para o cadastro de Motoristas.");
            MenuMotorista.menu();
        }
    }
}