package main;

import javafx.application.Application;

import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        menuInicial();
    }

    public static void menuInicial() {

        System.out.println("#### Marco Antônio de Oliveira - Trab AV1 #### \n \n \n");
        System.out.println("#### MENU Inicial ####");
        System.out.println("1 - Funcionário");
        System.out.println("2 - Motorista");
        System.out.println("3 - Aluguel");

        Scanner s = new Scanner(System.in);
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                MenuFuncionario.menu();
                break;
            case 2:
                MenuMotorista.menu();
                break;
            case 3:
                MenuAluguel.menu();
                break;
            default:
                menuInicial();
        }
    }
}
