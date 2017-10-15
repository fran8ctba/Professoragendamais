package br.com.agendamais.jsf.javabean;

import java.time.LocalDate;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.agendamais.controller.PacienteController;
import br.com.agendamais.dto.PacienteDto;
import br.com.agendamais.model.Paciente;

@ViewScoped
@ManagedBean(name = "PacienteVB")
public class PacienteJavaBean
{
    // Atributos - Valores dos componentes visuais
    private Integer id;
    private String  email;
    private String  senha;
    private String  nome;
    private Date    dataNascimento;
    private Long    telefone;
    private Long    cpf;
    private boolean edicao;
    private String  tela;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer pId)
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

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date pDataNascimento)
    {
        dataNascimento = pDataNascimento;
    }

    public Long getTelefone()
    {
        return telefone;
    }

    public void setTelefone(Long pTelefone)
    {
        telefone = pTelefone;
    }

    public Long getCpf()
    {
        return cpf;
    }

    public void setCpf(Long pCpf)
    {
        cpf = pCpf;
    }

    public boolean isEdicao()
    {
        return edicao;
    }

    public void setEdicao(boolean pEdicao)
    {
        edicao = pEdicao;
    }

    public String getTela()
    {
        return tela;
    }

    public void setTela(String pTela)
    {
        tela = pTela;
    }

    // Métodos da Controller
    public String limpar()
    {
        id = null;
        email = null;
        senha = null;
        nome = null;
        dataNascimento = null;
        telefone = null;
        cpf = null;
        edicao = false;

        return tela;
    }

    public String cadastrar()
    {
        System.out.println("PacienteVB - Cadastrar : " + this);

        Paciente tPaciente = new Paciente();
        tPaciente.setEmail(email);
        tPaciente.setSenha(senha);
        tPaciente.setNome(nome);
        LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
        tPaciente.setDataNascimento(tDataNascimento);
        tPaciente.setTelefone(telefone);
        tPaciente.setCpf(cpf);

        PacienteController tController = new PacienteController();

        PacienteDto tDto = tController.cadastrarPaciente(tPaciente);
        if (tDto.isOk())
        {
            // Ok, incluído
            id = tDto.getPaciente().getId();

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
        }
        else
        {
            // Erro de inclusão

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }
        return null;
    }

    public String alterar()
    {
        System.out.println("PacienteVB - Alterar : " + this);

        Paciente tPaciente = new Paciente();
        tPaciente.setId(id);
        tPaciente.setEmail(email);
        tPaciente.setSenha(senha);
        tPaciente.setNome(nome);
        LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
        tPaciente.setDataNascimento(tDataNascimento);
        tPaciente.setTelefone(telefone);
        tPaciente.setCpf(cpf);

        PacienteController tController = new PacienteController();

        PacienteDto tDto = tController.atualizarPaciente(tPaciente);
        if (tDto.isOk())
        {
            // Ok, alterado
            id = tDto.getPaciente().getId();

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
        }
        else
        {
            // Erro de alteração

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }
        return null;
    }

    public String consultar()
    {
        System.out.println("PacienteVB - Consultar : " + this);

        PacienteController tController = new PacienteController();

        PacienteDto tDto = tController.recuperarPaciente(id);
        if (tDto.isOk())
        {
            // Ok, recuperado
            Paciente tPaciente = tDto.getPaciente();
            id = tPaciente.getId();
            email = tPaciente.getEmail();
            senha = tPaciente.getSenha();
            nome = tPaciente.getNome();
            dataNascimento = java.sql.Date.valueOf(tPaciente.getDataNascimento());
            telefone = tPaciente.getTelefone();
            cpf = tPaciente.getCpf();

            // indicando que a pesquisa deu certo
            edicao = true;
        }
        else
        {
            // Erro de consulta
            edicao = false;

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }

        return null;
    }

    public String excluir()
    {
        System.out.println("PacienteVB - Excluir : " + this);

        PacienteController tController = new PacienteController();

        PacienteDto tDto = tController.removePaciente(id);
        if (tDto.isOk())
        {
            // Ok, exluido
            limpar();

            // indicando que a pesquisa deu certo
            edicao = false;

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));

        }
        else
        {
            // Erro de consulta
            edicao = false;

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }

        return null;
    }

    // Métodos Gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(id);
        tBuilder.append(", ");
        tBuilder.append(email);
        tBuilder.append(", ");
        tBuilder.append(senha);
        tBuilder.append(", ");
        tBuilder.append(nome);
        tBuilder.append(", ");
        tBuilder.append(dataNascimento);
        tBuilder.append(", ");
        tBuilder.append(telefone);
        tBuilder.append(", ");
        tBuilder.append(cpf);
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
