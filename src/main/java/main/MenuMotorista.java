package main;

import control.MotoristaController;
import entity.ESexo;
import entity.Motorista;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class MenuMotorista {

    public static void menu() {
        System.out.println("#### MENU Motorista #### \n");
        System.out.println("1 - Cadastro Motorista");
        System.out.println("2 - Atualização Cadastro Motorista");
        System.out.println("3 - Deletar Cadastro Motorista");
        System.out.println("4 - Listar Todos os Motorista");
        System.out.println("5 - Buscar Motorista por nome");


        Scanner s = new Scanner(System.in);
        MotoristaController motoristaController = new MotoristaController();
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("#### MENU Cadastro Motorista #### \n \n");
                try {
                    motoristaController.addMotorista(carregaInfoMotorista());
                    System.out.println("Cadastro realizada com sucesso! \n");
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro no cadastro do motorista, retornando para o menu inicial \n");
                    Start.menuInicial();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Erro no cadastro do motorista, retornando para o menu inicial \n");
                    Start.menuInicial();
                }
                break;

            case 2:
                try {
                    listarMotoristas(motoristaController.listMotorista());
                    motoristaController.atualizaMotorista(carregaInfoMotorista());
                    System.out.println("Atualização realizada com sucesso! \n");
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao atualizar cadastro do motorista, retornando para o menu inicial \n");
                    Start.menuInicial();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao atualizar cadastro do motorista, retornando para o menu inicial \n");
                    Start.menuInicial();
                }
                break;

            case 3:
                deletarMotorista();
                Start.menuInicial();
                break;

            case 4:
                try {
                    listarMotoristas(motoristaController.listMotorista());
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro na pesquisa.Nenhum Motorista localizado! \n");
                    Start.menuInicial();
                }
                break;

            case 5:
                try {
                    listaMotorista();
                    Start.menuInicial();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum Motorista localizado com este nome! \n");
                    Start.menuInicial();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Erro na pesquisa. Nenhum Motorista localizado com este nome! \n");
                    Start.menuInicial();
                }
                break;

            default:
                System.out.println("Opção errada, voltando para o menu inicial \n");
                Start.menuInicial();

        }
    }

    public static Motorista carregaInfoMotorista() throws ParseException {
        Motorista motorista = new Motorista();
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o nome: ");
        motorista.setNome(s.nextLine());

        System.out.println("Digite a data de nascimento: dd/MM/yyyy ");
        motorista.setDataNascimento(s.nextLine());

        System.out.println("Digite o número da CNH: ");
        motorista.setNumCNH(s.nextLine());

        System.out.println("Digite o sexo: 1:Masculino / 2:Feminino ");
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                motorista.setSexo(ESexo.MASCULINO);
                break;
            case 2:
                motorista.setSexo(ESexo.FEMININO);
                break;
            default:
                System.out.println("Opção errada! Sexo Masculino setado");
                motorista.setSexo(ESexo.MASCULINO);
        }

        System.out.println("Digite o cpf (este valor é usado para localizar o Motorista): ");
        motorista.setCpf(s.nextLine());
        return motorista;
    }

    public static void listarMotoristas(List<Motorista> list) {
        System.out.println("#### MENU Resultado Pesquisa Motoristas #### \n");

        if (list.size() > 0) {
            for (Motorista motorista : list) {
                System.out.println("Nome: " + motorista.getNome());
                System.out.println("CPF: " + motorista.getCpf());
                System.out.println("Num CNH: " + motorista.getNumCNH());
                System.out.println("Data Nascimento: " + motorista.getDataNascimento());
                System.out.println("Sexo: " + motorista.getSexo());
                System.out.println("######################################################## \n ");
            }
        } else {
            System.out.println("Nenhum cadastro de Motorista localizado! \n ");
            System.out.println("######################################################## \n ");
        }
    }

    public static void listaMotorista() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("#### MENU Busca Motorista #### \n");
        System.out.println("Digite o nome: ");
        String nome = s.nextLine();

        MotoristaController motoristaController = new MotoristaController();
        Motorista motorista = motoristaController.buscarMotorista(nome);

        if (motorista != null) {
            System.out.println("#### MENU Resultado Pesquisa Motorista #### \n ");
            System.out.println("Nome: " + motorista.getNome());
            System.out.println("CPF: " + motorista.getCpf());
            System.out.println("Num CNH: " + motorista.getNumCNH());
            System.out.println("Data Nascimento: " + motorista.getDataNascimento());
            System.out.println("Sexo: " + motorista.getSexo());
            System.out.println("######################################################## \n ");
        } else {
            System.out.println("Nenhum cadastro de motorista com este nome localizado! \n ");
            System.out.println("######################################################## \n ");
        }
    }

    public static void deletarMotorista() {
        MotoristaController motoristaController = new MotoristaController();
        System.out.println("#### MENU Deletar Motorista #### \n ");

        try {
            listarMotoristas(motoristaController.listMotorista());
            System.out.println("Digite o número da CNH do motorista que deseja deletar: ");
            Scanner s = new Scanner(System.in);

            String cnh = s.nextLine();

            motoristaController.deleteMotorista(cnh);

            System.out.println("Motorista deletado com sucesso!");
            Start.menuInicial();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar o motorista! \n");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao deletar o motorista! \n");
        }
    }
}