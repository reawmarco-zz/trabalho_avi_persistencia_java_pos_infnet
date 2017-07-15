package dao;

import entity.Aluguel;
import java.sql.SQLException;
import java.util.List;

public interface IAluguelDAO {

    void inserir(Aluguel aluguel) throws SQLException;
    void atualizar(Aluguel aluguel) throws SQLException;
    void deletar(Aluguel aluguel) throws SQLException;
    List<Aluguel> buscarTodos() throws SQLException;
    Aluguel buscar (int id) throws SQLException;
}