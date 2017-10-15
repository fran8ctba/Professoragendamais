package br.com.agendamais.controller;

import br.com.agendamais.dao.ConsultaDao;
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
        // Verificar as informa��es
        if (pId <=0)
        {
            return new PacienteDto(false, "Identificador do paciente inv�lido");
        }

        // Criando o objeto de persist�ncia
        PacienteDao tDao = new PacienteDao();

        // Recuperando o paciente
        Paciente tPaciente = tDao.recovery(pId);
        if (tPaciente == null)
        {
            return new PacienteDto(false, "N�o existe paciente com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new PacienteDto(true, "Paciente recuperado com sucesso", tPaciente);
    }

    public PacienteDto atualizarPaciente(Paciente pPaciente)
    {
        // Verificar as informa��es
        if (pPaciente == null)
        {
            return new PacienteDto(false, "Tentativa de atualiza��o de paciente nulo");
        }

        // Criando o objeto de persist�ncia
        PacienteDao tDao = new PacienteDao();

        // Verificando se existe um paciente com o novo CPF
        Paciente tPaciente = tDao.recoveryByCpf(pPaciente.getCpf());
        if (tPaciente != null)
        {
            return new PacienteDto(false, "J� existe paciente com o cpf informado");
        }

        // Atualizando o paciente
        tPaciente = tDao.update(pPaciente);
        if (tPaciente == null)
        {
            return new PacienteDto(false, "N�o existe paciente com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new PacienteDto(true, "Paciente alterado com sucesso", tPaciente);
    }

    public PacienteDto removePaciente(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new PacienteDto(false, "Identificador do paciente inv�lido");
        }

        // Criando o objeto de persist�ncia
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Verificando se o paciente j� existe
        int tQtde = tDaoConsulta.countByPaciente(pId);
        if (tQtde != 0)
        {
            return new PacienteDto(false, "Paciente j� tem consultas no sistema. Remo��o pro�bida");
        }

        // Criando o objeto de persist�ncia
        PacienteDao tDao = new PacienteDao();

        // Incluindo o paciente
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new PacienteDto(true, "Paciente removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new PacienteDto(false, "Erro no processo de remo��o");
    }
}
