package br.com.agendamais.controller;

import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.dto.PacienteDto;
import br.com.agendamais.model.Paciente;

public class PacienteController
{
    public PacienteDto cadastrarPaciente(Paciente pPaciente)
    {
        // Verificar as informações
        if (pPaciente == null)
        {
            return new PacienteDto(false, "Tentativa de inclusão de paciente nulo");
        }

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Verificando se o paciente já existe
        Paciente tPaciente = tDao.recoveryByCpf(pPaciente.getCpf());
        if (tPaciente != null)
        {
            return new PacienteDto(false, "Já existe paciente com o cpf informado");
        }

        // Incluindo o paciente
        tPaciente = tDao.create(pPaciente);
        if (tPaciente == null)
        {
            return new PacienteDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new PacienteDto(true, "Paciente incluído com sucesso", tPaciente);
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
