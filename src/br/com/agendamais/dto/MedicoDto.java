package br.com.agendamais.dto;

import java.util.List;

import br.com.agendamais.model.Medico;

public class MedicoDto
{
    private boolean        ok;
    private String         mensagem;
    private Medico       medico;
    private List<Medico> lista;

    public MedicoDto()
    {

    }

    public MedicoDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public MedicoDto(boolean pOk, String pMensagem, Medico pMedico)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        medico = pMedico;
    }

    public MedicoDto(boolean pOk, String pMensagem, List<Medico> pLista)
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

    public Medico getMedico()
    {
        return medico;
    }

    public void setMedico(Medico pMedico)
    {
        medico = pMedico;
    }

    public List<Medico> getLista()
    {
        return lista;
    }

    public void setLista(List<Medico> pLista)
    {
        lista = pLista;
    }

}
