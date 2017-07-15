package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import entity.Aluguel;
import factory.ConnectionFactory;
import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AluguelImpl implements IAluguelDAO {

    Connection conn = new ConnectionFactory().getConnection();

    @Override
    public void inserir(Aluguel aluguel) throws SQLException {

        String query = "INSERT INTO tb_aluguel ("
                + " data_aluguel, "
                + " data_devolucao, "
                + " data_entrega, "
                + " valor_total, "
                + " cnh_motorista ) "
                + " VALUES ( "
                + "?, ?, ?, ?,?)";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Util.convertToDateSql(aluguel.getDataAluguel().getTime()));
            st.setDate(2, Util.convertToDateSql(aluguel.getDataDevolucao()));
            st.setDate(3, Util.convertToDateSql(aluguel.getDataEntrega()));
            st.setDouble(4,aluguel.getValorTotal().doubleValue());
            st.setString(5, aluguel.getCnhMotorista());

            st.execute();
            System.out.println("Aluguel realizado com sucesso: "
                    + " Data Entrega: " + aluguel.getDataEntrega()
                    + " Cnh Motorista: " + aluguel.getCnhMotorista()) ;
            st.close();
        } catch (SQLException se) {
            System.out.println("Exception: " + se.getStackTrace());
            se.printStackTrace();
        } catch (Exception ex){
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
    }

    @Override
    public void atualizar(Aluguel aluguel) {

        String query = " update tb_aluguel set "
                + " data_aluguel = ?, "
                + " data_devolucao  = ?, "
                + " data_entrega = ?, "
                + " valor_total = ?, "
                + " cnh_motorista = ? "
                + " where id = ? ";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Util.convertToDateSql(aluguel.getDataAluguel().getTime()));
            st.setDate(2, Util.convertToDateSql(aluguel.getDataDevolucao()));
            st.setDate(3, Util.convertToDateSql(aluguel.getDataEntrega()));
            st.setDouble(4,aluguel.getValorTotal().doubleValue());
            st.setString(5, aluguel.getCnhMotorista());
            st.setInt(6, aluguel.getId());

            if(st.executeUpdate() != 0) {
                System.out.println("Aluguel atualizado: " + aluguel.getId() + " Data Entrega:" + aluguel.getDataEntrega());
            }else {
                System.out.println("Erro ao atualizar o aluguel: ");
            }

            st.close();
        } catch (SQLException se) {
            System.out.println("Exception: " + se.getStackTrace());
            se.printStackTrace();
        } catch (Exception ex){
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
    }

    @Override
    public void deletar(Aluguel aluguel) {

        String query = "DELETE FROM tb_aluguel WHERE id = ? ";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, aluguel.getId());

            st.execute();
            st.close();

            System.out.println("Aluguel Finalizado: " + aluguel.getId()
                    +" , Data Devolução : " + aluguel.getDataDevolucao() +
                    " Data Entrega: " + aluguel.getDataEntrega());

        }catch (SQLException se){
            System.out.println("Exception: " + se.getStackTrace());
            se.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
    }

    @Override
    public List<Aluguel> buscarTodos() {
        List<Aluguel> listAluguel = new ArrayList<>();

        String query = "select * from tb_aluguel";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            int i = 0;
            while (rs.next()) {

                Aluguel aluguel = new Aluguel();
                aluguel.setDataAluguel(Util.convertToCalendar(rs.getDate("data_aluguel")));
                aluguel.setDataDevolucao(rs.getDate("data_devolucao"));
                aluguel.setDataEntrega(rs.getDate("data_entrega"));
                aluguel.setValorTotal(BigDecimal.valueOf(rs.getDouble("valor_total")));
                aluguel.setCnhMotorista(rs.getString("cnh_motorista"));
                aluguel.setId(rs.getInt("id"));
                listAluguel.add(i, aluguel);

                i++;
            }

            rs.close();
            st.close();

        } catch (SQLException se) {
            System.out.println("Exception: " + se.getStackTrace());
            se.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
        return listAluguel;
    }

    @Override
    public Aluguel buscar(int id) {
        Aluguel aluguel = new Aluguel();

        String query = "select  * from tb_aluguel where id = ?";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,  id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                aluguel.setDataAluguel(Util.convertToCalendar(rs.getDate("data_aluguel")));
                aluguel.setDataDevolucao(rs.getDate("data_devolucao"));
                aluguel.setDataEntrega(rs.getDate("data_entrega"));
                aluguel.setValorTotal(BigDecimal.valueOf(rs.getDouble("valor_total")));
                aluguel.setCnhMotorista(rs.getString("cnh_motorista"));
                aluguel.setId(rs.getInt("id"));
            }

            rs.close();
            st.close();

        } catch (SQLException se) {
            System.out.println("Exception: " + se.getStackTrace());
            se.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }

        return aluguel;
    }
}
