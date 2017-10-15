package br.com.agendamais.model;

import java.time.LocalDate;

public class Paciente extends Usuario
{
    // Atributos
    private LocalDate dataNascimento;
    private long      telefone;
    private long      cpf;

    // Construtores
    public Paciente()
    {
        super();
    }

    public Paciente(int pId, String pEmail, String pSenha, String pNome, LocalDate pDataNascimento, long pTelefone, long pCpf)
    {
        super(pId, pEmail, pSenha, pNome);
        setDataNascimento(pDataNascimento);
        setTelefone(pTelefone);
        setCpf(pCpf);
    }


    // Métodos de acesso
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
        tBuilder.append(super.toString());
        tBuilder.append(", ");
        tBuilder.append(getDataNascimento());
        tBuilder.append(", ");
        tBuilder.append(getTelefone());
        tBuilder.append(", ");
        tBuilder.append(getCpf());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
