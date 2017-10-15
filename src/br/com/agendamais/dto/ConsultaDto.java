package br.com.agendamais.dto;

import java.util.List;

import br.com.agendamais.model.Consulta;

public class ConsultaDto
{
    private boolean        ok;
    private String         mensagem;
    private Consulta       consulta;
    private List<Consulta> lista;

    public ConsultaDto()
    {

    }

    public ConsultaDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public ConsultaDto(boolean pOk, String pMensagem, Consulta pConsulta)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        consulta = pConsulta;
    }

    public ConsultaDto(boolean pOk, String pMensagem, List<Consulta> pLista)
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

    public Consulta getConsulta()
    {
        return consulta;
    }

    public void setConsulta(Consulta pConsulta)
    {
        consulta = pConsulta;
    }

    public List<Consulta> getLista()
    {
        return lista;
    }

    public void setLista(List<Consulta> pLista)
    {
        lista = pLista;
    }

}
