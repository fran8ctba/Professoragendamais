package br.com.agendamais.model;

public abstract class Usuario
{
    // Atributos
    private int    id;
    private String email;
    private String senha;
    private String nome;

    // Construtores
    public Usuario()
    {
        super();
    }

    public Usuario(int pId, String pEmail, String pSenha, String pNome)
    {
        super();
        setId(pId);
        setEmail(pEmail);
        setSenha(pSenha);
        setNome(pNome);
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String pEmail)
    {
        email = pEmail;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String pSenha)
    {
        senha = pSenha;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getEmail());
        tBuilder.append(", ");
        tBuilder.append(getSenha());
        tBuilder.append(", ");
        tBuilder.append(getNome());
        tBuilder.append("]");
        return tBuilder.toString();
    }

}
