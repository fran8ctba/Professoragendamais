package teste;

public class Mae extends Object
{
    protected String nome;
    protected int idade;
    protected String profissao;
    protected int   telefone;

    public Mae(int pIdade)
    {
        idade = pIdade;
        System.out.println("Construtor padrão da classe mae");
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    public int getIdade()
    {
        return idade;
    }

    public void setIdade(int pIdade)
    {
        idade = pIdade;
    }

    public String getProfissao()
    {
        return profissao;
    }

    public void setProfissao(String pProfissao)
    {
        profissao = pProfissao;
    }

    public int getTelefone()
    {
        return telefone;
    }

    public void setTelefone(int pTelefone)
    {
        telefone = pTelefone;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(nome);
        tBuilder.append(", ");
        tBuilder.append(idade);
        tBuilder.append(", ");
        tBuilder.append(profissao);
        tBuilder.append(", ");
        tBuilder.append(telefone);
        tBuilder.append("]");
        return tBuilder.toString();
    }

}
















