package br.com.agendamais.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agendamais.jdbc.Conexao;
import br.com.agendamais.model.Medico;
import br.com.agendamais.util.ExceptionUtil;

public class MedicoDao
{
    private String comandoCreate   = "INSERT INTO MEDICO "
                    + "(ID, NOME, CRM, ESPECIALIDADE)"
                    + "VALUES (MEDICO_SEQ.NEXTVAL, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, NOME, CRM, ESPECIALIDADE "
                    + "FROM MEDICO "
                    + "WHERE ID = ?";
    private String comandoUpdate   = "UPDATE MEDICO "
                    + "SET NOME = ?, "
                    + "CRM = ?, "
                    + "ESPECIALIDADE = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM MEDICO "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, NOME, CRM, ESPECIALIDADE "
                    + "FROM MEDICO";

    public Medico create(Medico pMedico)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pMedico.getNome());
            tComandoJdbc.setInt(i++, pMedico.getCrm());
            tComandoJdbc.setString(i++, pMedico.getEspecialidade());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Medico tMedico = pMedico;

                // Recuperando o código gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave primária gerada no objeto
                pMedico.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tMedico;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do medico");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Medico recovery(int pId)
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
                Medico tMedico = recuperarMedico(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tMedico;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do medico");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Medico update(Medico pMedico)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pMedico.getNome());
            tComandoJdbc.setInt(i++, pMedico.getCrm());
            tComandoJdbc.setString(i++, pMedico.getEspecialidade());
            tComandoJdbc.setInt(i++, pMedico.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Medico tMedico = pMedico;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tMedico;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do medico");
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
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do medico");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Medico> search()
    {
        List<Medico> tLista = new ArrayList<>();

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
                Medico tMedico = recuperarMedico(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tMedico);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do medico");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    private Medico recuperarMedico(ResultSet tResultSet) throws SQLException
    {
        Medico tMedico = new Medico();

        // Recuperando os dados do resultSet
        tMedico.setId(tResultSet.getInt("ID"));
        tMedico.setNome(tResultSet.getString("NOME"));
        tMedico.setCrm(tResultSet.getInt("CRM"));
        tMedico.setEspecialidade(tResultSet.getString("ESPECIALIDADE"));
        return tMedico;
    }
}
