package entity;

import util.Util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Pessoa {

    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String sexo;

    public Pessoa(String nome, Date dataNascimento, String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = Util.convertToDate(dataNascimento);
    }

    public void setDataNascimento(java.sql.Date dataNascimento){
        new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
        if (this.sexo.equals("M")) {
            this.sexo = ESexo.MASCULINO.toString();
        } else {
            this.sexo = ESexo.FEMININO.toString();
        }
    }

    public void setSexo(ESexo eSexo) {
        this.sexo = eSexo.getSexo();
    }



}
