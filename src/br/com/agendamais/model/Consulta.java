package br.com.agendamais.model;

import java.math.BigDecimal;
import java.util.Date;

public class Consulta
{
    // Atributos
    private int        id;
    private Date       dataConsulta;
    private BigDecimal valor;
    private String     endereco;
    private int        idPaciente;
    private int        idMedico;

    // Construtores
    public Consulta()
    {
        super();
    }

    public Consulta(int pId, Date pDataConsulta, BigDecimal pValor, String pEndereco, int pIdPaciente, int pIdMedico)
    {
        super();
        setId(pId);
        setDataConsulta(pDataConsulta);
        setValor(pValor);
        setEndereco(pEndereco);
        setIdPaciente(pIdPaciente);
        setIdMedico(pIdMedico);
    }

    // Métodos de acesso
    public int getId()
    {
        return id;
    }

    public void setId(int pId)
    {
        id = pId;
    }

    public Date getDataConsulta()
    {
        return dataConsulta;
    }

    public void setDataConsulta(Date pDataConsulta)
    {
        dataConsulta = pDataConsulta;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal pValor)
    {
        valor = pValor;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String pEndereco)
    {
        endereco = pEndereco;
    }

    public int getIdPaciente()
    {
        return idPaciente;
    }

    public void setIdPaciente(int pIdPaciente)
    {
        idPaciente = pIdPaciente;
    }

    public int getIdMedico()
    {
        return idMedico;
    }

    public void setIdMedico(int pIdMedico)
    {
        idMedico = pIdMedico;
    }

    // Métodos gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("[");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getDataConsulta());
        tBuilder.append(", ");
        tBuilder.append(getValor());
        tBuilder.append(", ");
        tBuilder.append(getEndereco());
        tBuilder.append(", ");
        tBuilder.append(getIdPaciente());
        tBuilder.append(", ");
        tBuilder.append(getIdMedico());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
