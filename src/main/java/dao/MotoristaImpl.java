package dao;

import java.sql.PreparedStatement;
import entity.Motorista;
import factory.ConnectionFactory;
import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaImpl implements IMotoristaDAO {

    Connection conn = new ConnectionFactory().getConnection();

    @Override
    public void inserir(Motorista motorista) throws SQLException {

        String query = "INSERT INTO tb_motorista ( "
                + " nome, "
                + " data_nascimento, "
                + " cpf, "
                + " num_cnh, "
                + " sexo  ) "
                + " VALUES ( "
                + " ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, motorista.getNome());
            st.setDate(2, Util.convertToDateSql(motorista.getDataNascimento()));
            st.setString(3, motorista.getCpf());
            st.setString(4, motorista.getNumCNH());
            st.setString(5, motorista.getSexo());

            st.execute();
            System.out.println("Motorista cadastrado: " +  motorista.getNome());
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
    public void atualizar(Motorista motorista) throws SQLException {

        String query = "UPDATE tb_motorista SET "
                + " nome = ?,"
                + " data_nascimento  = ?, "
                + " num_cnh = ?, "
                + " sexo = ? "
                + " WHERE cpf = ? ";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, motorista.getNome());
            st.setDate(2, Util.convertToDateSql(motorista.getDataNascimento()));
            st.setString(3,motorista.getNumCNH());
            st.setString(4, motorista.getSexo());
            st.setString(5, motorista.getCpf());

            if(st.executeUpdate() != 0) {
                System.out.println("Motorista atualizado: " + motorista.getNome());
            }else{
                System.out.println("Erro ao atualizar o motorista: " + motorista.getNome());
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
    public void deletar(String CNH) throws SQLException {

        String query = "DELETE FROM tb_motorista WHERE cnh = ? ";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, CNH);

            st.execute();
            st.close();

        }catch (SQLException se){
            System.out.println("Exception: " + se.getStackTrace());
            se.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
    }

    @Override
    public List<Motorista> buscarTodos() throws SQLException {
        List<Motorista> listMotorista = new ArrayList<>();

        String query = "select nome, "
                + " data_nascimento,"
                + " cpf,"
                + " num_cnh,"
                + " sexo "
                + "from tb_motorista";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            int i = 0;
            while (rs.next()) {

                Motorista motorista = new Motorista();
                motorista.setNome(rs.getString("nome"));
                motorista.setDataNascimento(rs.getDate("data_nascimento"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setNumCNH(rs.getString("num_cnh"));
                motorista.setSexo(rs.getString("sexo"));

                listMotorista.add(i, motorista);

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
        return listMotorista;
    }


    @Override
    public Motorista buscar(String nome) throws SQLException {

        Motorista motorista = new Motorista();

        String query = "select nome, "
                + " data_nascimento, "
                + " cpf,"
                + " num_cnh,"
                + " sexo "
                + " from tb_motorista "
                + " where nome = ? ";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                motorista.setNome(rs.getString("nome"));
                motorista.setDataNascimento(rs.getDate("data_nascimento"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setNumCNH(rs.getString("num_cnh"));
                motorista.setSexo(rs.getString("sexo"));
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

        return motorista;
    }
}
