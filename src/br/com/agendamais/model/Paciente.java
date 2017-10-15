package br.com.agendamais.model;

import java.time.LocalDate;

public class Paciente
{
    // Atributos
    private int       id;
    private String    nome;
    private LocalDate dataNascimento;
    private long      telefone;
    private String    email;
    private long      cpf;

    // Construtores
    public Paciente()
    {
        super();
    }

    public Paciente(int pId, String pNome, LocalDate pDataNascimento, long pTelefone, String pEmail, long pCpf)
    {
        super();
        setId(pId);
        setNome(pNome);
        setDataNascimento(pDataNascimento);
        setTelefone(pTelefone);
        setEmail(pEmail);
        setCpf(pCpf);
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

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate pDataNascimento)
    {
        dataNascimento = pDataNascimento;
    }

    public long getTelefone()
    {
        return telefone;
    }

    public void setTelefone(long pTelefone)
    {
        telefone = pTelefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String pEmail)
    {
        email = pEmail;
    }

    public long getCpf()
    {
        return cpf;
    }

    public void setCpf(long pCpf)
    {
        cpf = pCpf;
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
        tBuilder.append(getDataNascimento());
        tBuilder.append(", ");
        tBuilder.append(getTelefone());
        tBuilder.append(", ");
        tBuilder.append(getEmail());
        tBuilder.append(", ");
        tBuilder.append(getCpf());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
