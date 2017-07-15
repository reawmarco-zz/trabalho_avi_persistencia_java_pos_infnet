package dao;

import entity.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface IFuncionarioDAO {

    void inserir(Funcionario funcionario) throws SQLException;
    void atualizar(Funcionario funcionario) throws SQLException;
    void deletar(String numMatricula) throws SQLException;
    List<Funcionario> buscarTodos() throws SQLException;
    Funcionario buscar (String nome) throws SQLException;

}
