package br.com.agendamais.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.agendamais.jdbc.Conexao;
import br.com.agendamais.model.Paciente;
import br.com.agendamais.util.ExceptionUtil;

public class PacienteDao
{
    private String comandoCreate   = "INSERT INTO PACIENTE "
                    + "(ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, CPF)"
                    + "VALUES (PACIENTE_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, CPF "
                    + "FROM PACIENTE "
                    + "WHERE ID = ?";
    private String comandoUpdate   = "UPDATE PACIENTE "
                    + "SET NOME = ?, "
                    + "DATA_NASCIMENTO = ?, "
                    + "TELEFONE = ?, "
                    + "EMAIL = ?, "
                    + "CPF = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM PACIENTE "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, CPF "
                    + "FROM PACIENTE";

    public Paciente create(Paciente pPaciente)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pPaciente.getNome());
            tComandoJdbc.setDate(i++, Date.valueOf(pPaciente.getDataNascimento()));
            tComandoJdbc.setLong(i++, pPaciente.getTelefone());
            tComandoJdbc.setString(i++, pPaciente.getEmail());
            tComandoJdbc.setLong(i++, pPaciente.getCpf());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Paciente tPaciente = pPaciente;

                // Recuperando o c�digo gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave prim�ria gerada no objeto
                pPaciente.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tPaciente;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do paciente");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Paciente recovery(int pId)
    {
        try
        {
            // Obter a conex�o
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
                Paciente tPaciente = new Paciente();

                // Recuperando os dados do resultSet
                tPaciente.setId(tResultSet.getInt("ID"));
                tPaciente.setNome(tResultSet.getString("NOME"));
                tPaciente.setDataNascimento(tResultSet.getDate("DATA_NASCIMENTO").toLocalDate());
                tPaciente.setTelefone(tResultSet.getLong("TELEFONE"));
                tPaciente.setEmail(tResultSet.getString("EMAIL"));
                tPaciente.setCpf(tResultSet.getLong("CPF"));

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tPaciente;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do paciente");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Paciente update(Paciente pPaciente)
    {
        try
        {
            // Obter a conex�o
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pPaciente.getNome());
            tComandoJdbc.setDate(i++, Date.valueOf(pPaciente.getDataNascimento()));
            tComandoJdbc.setLong(i++, pPaciente.getTelefone());
            tComandoJdbc.setString(i++, pPaciente.getEmail());
            tComandoJdbc.setLong(i++, pPaciente.getCpf());
            tComandoJdbc.setInt(i++, pPaciente.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Paciente tPaciente = pPaciente;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tPaciente;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na cria��o do paciente");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public boolean delete(int pId)
    {
        try
        {
            // Obter a conex�o
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
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remo��o do paciente");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Paciente> search()
    {
        return null;
    }
}