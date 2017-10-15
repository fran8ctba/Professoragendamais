package br.com.agendamais.controller;

import br.com.agendamais.dao.ConsultaDao;
import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.dto.MedicoDto;
import br.com.agendamais.model.Medico;

public class MedicoController
{
    public MedicoDto cadastrarMedico(Medico pMedico)
    {
        // Verificar as informações
        if (pMedico == null)
        {
            return new MedicoDto(false, "Tentativa de inclusão de médico nulo");
        }

        // Criando o objeto de persistência
        MedicoDao tDao = new MedicoDao();

        // Verificando se o medico já existe
        Medico tMedico = tDao.recoveryByCrm(pMedico.getCrm());
        if (tMedico != null)
        {
            return new MedicoDto(false, "Já existe médico com o CRM informado");
        }

        // Incluindo o medico
        tMedico = tDao.create(pMedico);
        if (tMedico == null)
        {
            return new MedicoDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new MedicoDto(true, "Médico incluído com sucesso", tMedico);
    }

    public MedicoDto recuperarMedico(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new MedicoDto(false, "Identificador do médico inválido");
        }

        // Criando o objeto de persistência
        MedicoDao tDao = new MedicoDao();

        // Recuperando o medico
        Medico tMedico = tDao.recovery(pId);
        if (tMedico == null)
        {
            return new MedicoDto(false, "Não existe médico com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new MedicoDto(true, "Médico recuperado com sucesso", tMedico);
    }

    public MedicoDto atualizarMedico(Medico pMedico)
    {
        // Verificar as informações
        if (pMedico == null)
        {
            return new MedicoDto(false, "Tentativa de atualização de médico nulo");
        }

        // Criando o objeto de persistência
        MedicoDao tDao = new MedicoDao();

        // Verificando se existe um medico com o novo CPF
        Medico tMedico = tDao.recoveryByCrm(pMedico.getCrm());
        if (tMedico != null)
        {
            return new MedicoDto(false, "Já existe médico com o CRM informado");
        }

        // Atualizando o medico
        tMedico = tDao.update(pMedico);
        if (tMedico == null)
        {
            return new MedicoDto(false, "Não existe médico com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new MedicoDto(true, "Médico alterado com sucesso", tMedico);
    }

    public MedicoDto removeMedico(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new MedicoDto(false, "Identificador do médico inválido");
        }

        // Criando o objeto de persistência
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Verificando se o medico já existe
        int tQtde = tDaoConsulta.countByMedico(pId);
        if (tQtde != 0)
        {
            return new MedicoDto(false, "Médico já tem consultas no sistema. Remoção proíbida");
        }

        // Criando o objeto de persistência
        MedicoDao tDao = new MedicoDao();

        // Incluindo o medico
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new MedicoDto(true, "Médico removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new MedicoDto(false, "Erro no processo de remoção");
    }
}
