package control;

import dao.FuncionarioImpl;
import dao.IFuncionarioDAO;
import entity.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    IFuncionarioDAO funcionarioDAO = new FuncionarioImpl();


    public void addFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioDAO.inserir(funcionario);
    }

    public void atualizaFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioDAO.atualizar(funcionario);
    }

    public void deleteFuncionario(String numMatricula) throws SQLException {
        funcionarioDAO.deletar(numMatricula);
    }

    public List<Funcionario> listFuncionario() throws SQLException {
        return funcionarioDAO.buscarTodos();
    }

    public Funcionario buscarFuncionario(String nome) throws SQLException {
        return funcionarioDAO.buscar(nome);
    }

}
