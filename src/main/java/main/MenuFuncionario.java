package main;

import control.FuncionarioController;
import entity.ESexo;
import entity.Funcionario;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MenuFuncionario {


    public static void menu() {
        System.out.println("#### MENU Funcionario #### \n ");
        System.out.println("1 - Cadastro Funcionário");
        System.out.println("2 - Atualização Cadastro Funcionário");
        System.out.println("3 - Deletar Cadastro Funcionário");
        System.out.println("4 - Listar Todos os Funcionários");
        System.out.println("5 - Buscar Funcionário por nome");

        Scanner s = new Scanner(System.in);
        FuncionarioController funcionarioController = new FuncionarioController();
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("#### MENU Cadastro Funcionário #### \n \n");
                try {
                    funcionarioController.addFuncionario(carregaInfoFuncionario());
                    System.out.println("Cadastro realizada com sucesso! \n");
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro no cadastro do funcionario, retornando para o menu inicial \n");
                    Start.menuInicial();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Erro no cadastro do funcionario, retornando para o menu inicial \n");
                    Start.menuInicial();
                }
                break;

            case 2:
                try {
                    listarFuncionarios(funcionarioController.listFuncionario());
                    funcionarioController.atualizaFuncionario(carregaInfoFuncionario());
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
                deletarFuncionario();
                Start.menuInicial();
                break;

            case 4:
                try {
                    listarFuncionarios(funcionarioController.listFuncionario());
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro na pesquisa.Nenhum funcionário localizado! \n");
                    Start.menuInicial();
                }
                break;

            case 5:
                try {
                    listaFuncionario();
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum Funcionario localizado com este nome! \n");
                    Start.menuInicial();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum Funcionario localizado com este nome! \n");
                    Start.menuInicial();
                }
                break;

            default:
                System.out.println("Opção errada, voltando para o menu inicial \n");
                Start.menuInicial();

        }
    }

    public static Funcionario carregaInfoFuncionario() throws ParseException {
        Funcionario funcionario = new Funcionario();
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o nome: ");
        funcionario.setNome(s.nextLine());

        System.out.println("Digite a data de nascimento: dd/MM/yyyy ");
        funcionario.setDataNascimento(s.nextLine());

       System.out.println("Digite o número da matrícula: ");
        funcionario.setNumMatricula(s.nextLine());

        System.out.println("Digite o sexo: 1:Masculino / 2:Feminino ");
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                funcionario.setSexo(ESexo.MASCULINO);
                break;
            case 2:
                funcionario.setSexo(ESexo.FEMININO);
                break;
            default:
                System.out.println("Opção errada! Sexo Masculino setado");
                funcionario.setSexo(ESexo.MASCULINO);
        }

        System.out.println("Digite o cpf (este valor é usado para localizar o Funcionário): ");
        funcionario.setCpf(s.nextLine());

        return funcionario;
    }

    public static void listarFuncionarios(List<Funcionario> list) {
        System.out.println("#### MENU Resultado Pesquisa Funcionários #### \n");

        if (list.size() > 0) {
            for (Funcionario funcionario : list) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("CPF: " + funcionario.getCpf());
                System.out.println("Num Matrícula: " + funcionario.getNumMatricula());
                System.out.println("Data Nascimento: " + funcionario.getDataNascimento());
                System.out.println("Sexo: " + funcionario.getSexo());
                System.out.println("######################################################## \n ");
            }
        } else {
            System.out.println("Nenhum cadastro de funcionário localizado! \n ");
            System.out.println("######################################################## \n ");
        }
    }

    public static void listaFuncionario() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("#### MENU Busca Funcionário #### \n");
        System.out.println("Digite o nome: ");
        String nome = s.nextLine();

        FuncionarioController funcionarioController = new FuncionarioController();
        Funcionario funcionario = funcionarioController.buscarFuncionario(nome);

        if (funcionario != null) {
            System.out.println("#### MENU Resultado Pesquisa Funcionário #### \n ");
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("Num Matrícula: " + funcionario.getNumMatricula());
            System.out.println("Data Nascimento: " + funcionario.getDataNascimento());
            System.out.println("Sexo: " + funcionario.getSexo());
            System.out.println("######################################################## \n ");
        } else {
            System.out.println("Nenhum cadastro de funcionário com este nome localizado! \n ");
            System.out.println("######################################################## \n ");
        }
    }

    public static void deletarFuncionario() {
        FuncionarioController funcionarioController = new FuncionarioController();
        System.out.println("#### MENU Deletar Funcionário #### \n ");

        try {
            listarFuncionarios(funcionarioController.listFuncionario());
            System.out.println("Digite o número de matrícula do Funcionário que deseja deletar: ");
            Scanner s = new Scanner(System.in);
            String numMatricula = s.nextLine();
            funcionarioController.deleteFuncionario(numMatricula);
            System.out.println("Funcionário deletado com sucesso!");
            Start.menuInicial();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar o funcionário! \n");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao deletar o funcionário! \n");
        }
    }
}