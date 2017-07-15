package dao;

import java.sql.PreparedStatement;
import entity.Funcionario;
import factory.ConnectionFactory;
import util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioImpl implements IFuncionarioDAO {

    Connection conn = new ConnectionFactory().getConnection();

    @Override
    public void inserir(Funcionario funcionario) throws SQLException {

        String query = "INSERT INTO tb_funcionario ("
                + " nome,"
                + " data_nascimento,"
                + " cpf,"
                + " num_matricula,"
                + " sexo  ) "
                + " VALUES ( "
                + " ?, ?, ?, ?, ?)";

        try {
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, funcionario.getNome());
                st.setDate(2,  Util.convertToDateSql(funcionario.getDataNascimento()));
                st.setString(3, funcionario.getCpf());
                st.setString(4, funcionario.getNumMatricula());
                st.setString(5, funcionario.getSexo());

                st.execute();
                System.out.println("Funcionario cadastrado: " +  funcionario.getNome());
                st.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex){
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
    }


    @Override
    public void atualizar(Funcionario funcionario) {

        String query = " UPDATE tb_funcionario SET "
                + " nome = ?, "
                + " data_nascimento  = ?, "
                + " num_matricula = ?, "
                + " sexo = ? "
                + " WHERE cpf = ? ";

        try {

                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, funcionario.getNome());
                st.setDate(2,  Util.convertToDateSql(funcionario.getDataNascimento()));
                st.setString(3,funcionario.getNumMatricula());
                st.setString(4, funcionario.getSexo());
                st.setString(5, funcionario.getCpf());

              if(st.executeUpdate() != 0) {
                  System.out.println("Funcionario atualizado: " + funcionario.getNome());
              }else{
                  System.out.println("Erro ao atualizar o funcionario: " + funcionario.getNome());
              }
                st.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex){
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }
    }

    @Override
    public void deletar(String numMatricula) {

        String query = "DELETE FROM tb_funcionario WHERE num_matricula = ? ";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, numMatricula);

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
    public List<Funcionario> buscarTodos() {

        List<Funcionario> listFuncionario = new ArrayList<>();

        String query = "select nome, "
                + " data_nascimento,"
                + " cpf,"
                + " num_matricula,"
                + " sexo "
                + "from tb_funcionario";

        try {

            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            int i = 0;
            while (rs.next()) {

                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataNascimento(rs.getDate("data_nascimento"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNumMatricula(rs.getString("num_matricula"));
                funcionario.setSexo(rs.getString("sexo"));

                listFuncionario.add(i, funcionario);

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
        return listFuncionario;

    }

    @Override
    public Funcionario buscar(String nome) {

        Funcionario funcionario = new Funcionario();

        String query = " select nome, "
                + " data_nascimento, "
                + " cpf, "
                + " num_matricula, "
                + " sexo "
                + "from tb_funcionario "
                + "where nome = ? ";

        try{

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataNascimento(rs.getDate("data_nascimento"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNumMatricula(rs.getString("num_matricula"));
                funcionario.setSexo(rs.getString("sexo"));
            }

            rs.close();
            st.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getStackTrace());
            throw ex;
        }

        return funcionario;
    }
}
