package entity;

import java.util.Date;

public class Funcionario extends Pessoa{

    private String numMatricula;

    public Funcionario(String nome, Date dataNascimento, String cpf, String numMatricula) {
        super(nome, dataNascimento, cpf);
        this.numMatricula = numMatricula;
    }

    public Funcionario(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public Funcionario(){

    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }
}
