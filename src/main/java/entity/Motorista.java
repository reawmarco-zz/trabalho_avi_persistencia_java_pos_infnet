package entity;

import java.util.Date;

public class Motorista extends Pessoa {

    private String numCNH;

    public Motorista(String nome, Date dataNascimento, String cpf, String numCNH) {
        super(nome, dataNascimento, cpf);
        this.numCNH = numCNH;
    }

    public Motorista(){

    }

    public String getNumCNH() {
        return numCNH;
    }

    public void setNumCNH(String numCNH) {
        this.numCNH = numCNH;
    }
}
