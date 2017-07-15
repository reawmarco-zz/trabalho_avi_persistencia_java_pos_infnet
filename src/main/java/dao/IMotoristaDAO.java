package dao;

import entity.Motorista;
import java.sql.SQLException;
import java.util.List;

public interface IMotoristaDAO {

    void inserir(Motorista motorista) throws SQLException;
    void atualizar(Motorista motorista) throws SQLException;
    void deletar(String CNH) throws SQLException;
    List<Motorista> buscarTodos() throws SQLException;
    Motorista buscar (String nome) throws SQLException;
}
