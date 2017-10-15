package br.com.agendamais.model;

public class Medico extends Usuario
{
    // Atributos
    private int    crm;
    private String especialidade;

    // Construtores
    public Medico()
    {
        super();
    }

    public Medico(int pId, String pEmail, String pSenha, String pNome, int pCrm, String pEspecialidade)
    {
        super(pId, pEmail, pSenha, pNome);
        setCrm(pCrm);
        setEspecialidade(pEspecialidade);
    }

    // Métodos de acesso
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
        tBuilder.append(super.toString());
        tBuilder.append(", ");
        tBuilder.append(getCrm());
        tBuilder.append(", ");
        tBuilder.append(getEspecialidade());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
