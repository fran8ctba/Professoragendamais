package br.com.agendamais.dto;

import java.util.List;

import br.com.agendamais.model.Paciente;

public class PacienteDto
{
    private boolean        ok;
    private String         mensagem;
    private Paciente       paciente;
    private List<Paciente> lista;

    public PacienteDto()
    {

    }

    public PacienteDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public PacienteDto(boolean pOk, String pMensagem, Paciente pPaciente)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        paciente = pPaciente;
    }

    public PacienteDto(boolean pOk, String pMensagem, List<Paciente> pLista)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        lista = pLista;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setOk(boolean pOk)
    {
        ok = pOk;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String pMensagem)
    {
        mensagem = pMensagem;
    }

    public Paciente getPaciente()
    {
        return paciente;
    }

    public void setPaciente(Paciente pPaciente)
    {
        paciente = pPaciente;
    }

    public List<Paciente> getLista()
    {
        return lista;
    }

    public void setLista(List<Paciente> pLista)
    {
        lista = pLista;
    }

}
