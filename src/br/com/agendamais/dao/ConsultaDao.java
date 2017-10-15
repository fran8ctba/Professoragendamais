package br.com.agendamais.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.agendamais.jdbc.Conexao;
import br.com.agendamais.model.Consulta;
import br.com.agendamais.util.ExceptionUtil;

public class ConsultaDao
{
    private String comandoCreate   = "INSERT INTO CONSULTA "
                    + "(ID, DATA_CONSULTA, VALOR, ENDERECO, ID_PACIENTE, ID_MEDICO)"
                    + "VALUES (CONSULTA_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, DATA_CONSULTA, VALOR, ENDERECO, ID_PACIENTE, ID_MEDICO "
                    + "FROM CONSULTA "
                    + "WHERE ID = ?";
    private String comandoUpdate   = "UPDATE CONSULTA "
                    + "SET DATA_CONSULTA = ?, "
                    + "VALOR = ?, "
                    + "ENDERECO = ?, "
                    + "ID_PACIENTE = ?, "
                    + "ID_MEDICO = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM CONSULTA "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, DATA_CONSULTA, VALOR, ENDERECO, ID_PACIENTE, ID_MEDICO "
                    + "FROM CONSULTA";

    public Consulta create(Consulta pConsulta)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });


            // Preencher o comando
            int i = 1;
            tComandoJdbc.setTimestamp(i++, new Timestamp(pConsulta.getDataConsulta().getTime()));
            tComandoJdbc.setBigDecimal(i++, pConsulta.getValor());
            tComandoJdbc.setString(i++, pConsulta.getEndereco());
            tComandoJdbc.setInt(i++, pConsulta.getIdPaciente());
            tComandoJdbc.setInt(i++, pConsulta.getIdMedico());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Consulta tConsulta = pConsulta;

                // Recuperando o código gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave primária gerada no objeto
                pConsulta.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tConsulta;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do consulta");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Consulta recovery(int pId)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecovery);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pId);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            if (tResultSet.next())
            {
                // Criando o objeto
                Consulta tConsulta = recuperarConsulta(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tConsulta;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do consulta");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Consulta update(Consulta pConsulta)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setTimestamp(i++, new Timestamp(pConsulta.getDataConsulta().getTime()));
            tComandoJdbc.setBigDecimal(i++, pConsulta.getValor());
            tComandoJdbc.setString(i++, pConsulta.getEndereco());
            tComandoJdbc.setInt(i++, pConsulta.getIdPaciente());
            tComandoJdbc.setInt(i++, pConsulta.getIdMedico());
            tComandoJdbc.setInt(i++, pConsulta.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Consulta tConsulta = pConsulta;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tConsulta;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do consulta");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public boolean delete(int pId)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoDelete);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pId);

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o indicativo de sucesso
                return true;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do consulta");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Consulta> search()
    {
        List<Consulta> tLista = new ArrayList<>();

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Consulta tConsulta = recuperarConsulta(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tConsulta);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do consulta");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    private Consulta recuperarConsulta(ResultSet tResultSet) throws SQLException
    {
        Consulta tConsulta = new Consulta();
        //ID, DATA_CONSULTA, VALOR, ENDERECO, ID_PACIENTE, ID_CONSULTA
        // Recuperando os dados do resultSet
        tConsulta.setId(tResultSet.getInt("ID"));
        tConsulta.setDataConsulta(new java.util.Date(tResultSet.getTimestamp("DATA_CONSULTA").getTime()));
        tConsulta.setValor(tResultSet.getBigDecimal("VALOR"));
        tConsulta.setEndereco(tResultSet.getString("ENDERECO"));
        tConsulta.setIdPaciente(tResultSet.getInt("ID_PACIENTE"));
        tConsulta.setIdMedico(tResultSet.getInt("ID_MEDICO"));
        return tConsulta;
    }
}
