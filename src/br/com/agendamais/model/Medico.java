package br.com.agendamais.model;

public class Medico
{
    // Atributos
    private int    id;
    private String nome;
    private int    crm;
    private String especialidade;

    // Construtores
    public Medico()
    {
        super();
    }

    public Medico(int pId, String pNome, int pCrm, String pEspecialidade)
    {
        super();
        setId(pId);
        setNome(pNome);
        setCrm(pCrm);
        setEspecialidade(pEspecialidade);
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

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    public int getCrm()
    {
        return crm;
    }

    public void setCrm(int pCrm)
    {
        crm = pCrm;
    }

    public String getEspecialidade()
    {
        return especialidade;
    }

    public void setEspecialidade(String pEspecialidade)
    {
        especialidade = pEspecialidade;
    }

    // Métodos gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("[");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getNome());
        tBuilder.append(", ");
        tBuilder.append(getCrm());
        tBuilder.append(", ");
        tBuilder.append(getEspecialidade());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
