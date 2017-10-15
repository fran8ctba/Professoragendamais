package br.com.agendamais.controller;

import br.com.agendamais.dao.ConsultaDao;
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
        // Verificar as informações
        if (pId <=0)
        {
            return new PacienteDto(false, "Identificador do paciente inválido");
        }

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Recuperando o paciente
        Paciente tPaciente = tDao.recovery(pId);
        if (tPaciente == null)
        {
            return new PacienteDto(false, "Não existe paciente com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new PacienteDto(true, "Paciente recuperado com sucesso", tPaciente);
    }

    public PacienteDto atualizarPaciente(Paciente pPaciente)
    {
        // Verificar as informações
        if (pPaciente == null)
        {
            return new PacienteDto(false, "Tentativa de atualização de paciente nulo");
        }

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Verificando se existe um paciente com o novo CPF
        Paciente tPaciente = tDao.recoveryByCpf(pPaciente.getCpf());
        if (tPaciente != null)
        {
            return new PacienteDto(false, "Já existe paciente com o cpf informado");
        }

        // Atualizando o paciente
        tPaciente = tDao.update(pPaciente);
        if (tPaciente == null)
        {
            return new PacienteDto(false, "Não existe paciente com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new PacienteDto(true, "Paciente alterado com sucesso", tPaciente);
    }

    public PacienteDto removePaciente(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new PacienteDto(false, "Identificador do paciente inválido");
        }

        // Criando o objeto de persistência
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Verificando se o paciente já existe
        int tQtde = tDaoConsulta.countByPaciente(pId);
        if (tQtde != 0)
        {
            return new PacienteDto(false, "Paciente já tem consultas no sistema. Remoção proíbida");
        }

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Incluindo o paciente
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new PacienteDto(true, "Paciente removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new PacienteDto(false, "Erro no processo de remoção");
    }
}
