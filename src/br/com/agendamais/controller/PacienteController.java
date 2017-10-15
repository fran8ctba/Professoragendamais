package br.com.agendamais.controller;

import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.dto.PacienteDto;
import br.com.agendamais.model.Paciente;

public class PacienteController
{
    public PacienteDto cadastrarPaciente(Paciente pPaciente)
    {
        // Verificar as informa��es
        if (pPaciente == null)
        {
            return new PacienteDto(false, "Tentativa de inclus�o de paciente nulo");
        }

        // Criando o objeto de persist�ncia
        PacienteDao tDao = new PacienteDao();

        // Verificando se o paciente j� existe
        Paciente tPaciente = tDao.recoveryByCpf(pPaciente.getCpf());
        if (tPaciente != null)
        {
            return new PacienteDto(false, "J� existe paciente com o cpf informado");
        }

        // Incluindo o paciente
        tPaciente = tDao.create(pPaciente);
        if (tPaciente == null)
        {
            return new PacienteDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new PacienteDto(true, "Paciente inclu�do com sucesso", tPaciente);
    }

    public PacienteDto recuperarPaciente(int pId)
    {
        return null;
    }

    public PacienteDto atualizarPaciente(Paciente pPaciente)
    {
        return null;
    }

    public PacienteDto removePaciente(int pId)
    {
        return null;
    }

    public PacienteDto listarPacientes()
    {
        return null;
    }


}
