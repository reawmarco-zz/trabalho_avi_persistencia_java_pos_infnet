package entity;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Aluguel {

    public Integer id;
    public Calendar dataAluguel;
    public Date dataDevolucao;
    public Date dataEntrega;
    public BigDecimal valorTotal;
    public String cnhMotorista;

    public Aluguel(Integer id, Calendar dataAluguel, Date dataDevolucao, Date dataEntrega, BigDecimal valorTotal, String cnhMotorista) {
        this.id = id;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
        this.dataEntrega = dataEntrega;
        this.valorTotal = valorTotal;
        this.cnhMotorista = cnhMotorista;
    }

    public Aluguel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Calendar dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        new SimpleDateFormat("dd/MM/yyyy").format(dataEntrega);
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    public String getCnhMotorista() {
        return cnhMotorista;
    }

    public void setCnhMotorista(String cnhMotorista) {
        this.cnhMotorista = cnhMotorista;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        new SimpleDateFormat("dd/MM/yyyy").format(dataDevolucao);
        this.dataDevolucao = dataDevolucao;
    }
}
