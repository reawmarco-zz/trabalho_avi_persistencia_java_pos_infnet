package control;

import dao.AluguelImpl;
import dao.IAluguelDAO;
import entity.Aluguel;

import java.sql.SQLException;
import java.util.List;

public class AluguelController {

    IAluguelDAO aluguelDAO = new AluguelImpl();

    public void addAluguel(Aluguel aluguel) throws SQLException {
        aluguelDAO.inserir(aluguel);
    }

    public void atualizaAluguel(Aluguel aluguel) throws SQLException {
        aluguelDAO.atualizar(aluguel);
    }

    public void deleteAluguel(Aluguel aluguel )throws SQLException {
        aluguelDAO.deletar(aluguel);
    }

    public List<Aluguel> listAluguel() throws SQLException {
        return aluguelDAO.buscarTodos();
    }

    public Aluguel buscarAluguel(int id) throws SQLException {
        return aluguelDAO.buscar(id);
    }
}
