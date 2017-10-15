package br.com.agendamais.controller;

import br.com.agendamais.dao.ConsultaDao;
import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.dto.MedicoDto;
import br.com.agendamais.model.Medico;

public class MedicoController
{
    public MedicoDto cadastrarMedico(Medico pMedico)
    {
        // Verificar as informa��es
        if (pMedico == null)
        {
            return new MedicoDto(false, "Tentativa de inclus�o de m�dico nulo");
        }

        // Criando o objeto de persist�ncia
        MedicoDao tDao = new MedicoDao();

        // Verificando se o medico j� existe
        Medico tMedico = tDao.recoveryByCrm(pMedico.getCrm());
        if (tMedico != null)
        {
            return new MedicoDto(false, "J� existe m�dico com o CRM informado");
        }

        // Incluindo o medico
        tMedico = tDao.create(pMedico);
        if (tMedico == null)
        {
            return new MedicoDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new MedicoDto(true, "M�dico inclu�do com sucesso", tMedico);
    }

    public MedicoDto recuperarMedico(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new MedicoDto(false, "Identificador do m�dico inv�lido");
        }

        // Criando o objeto de persist�ncia
        MedicoDao tDao = new MedicoDao();

        // Recuperando o medico
        Medico tMedico = tDao.recovery(pId);
        if (tMedico == null)
        {
            return new MedicoDto(false, "N�o existe m�dico com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new MedicoDto(true, "M�dico recuperado com sucesso", tMedico);
    }

    public MedicoDto atualizarMedico(Medico pMedico)
    {
        // Verificar as informa��es
        if (pMedico == null)
        {
            return new MedicoDto(false, "Tentativa de atualiza��o de m�dico nulo");
        }

        // Criando o objeto de persist�ncia
        MedicoDao tDao = new MedicoDao();

        // Verificando se existe um medico com o novo CPF
        Medico tMedico = tDao.recoveryByCrm(pMedico.getCrm());
        if (tMedico != null)
        {
            return new MedicoDto(false, "J� existe m�dico com o CRM informado");
        }

        // Atualizando o medico
        tMedico = tDao.update(pMedico);
        if (tMedico == null)
        {
            return new MedicoDto(false, "N�o existe m�dico com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new MedicoDto(true, "M�dico alterado com sucesso", tMedico);
    }

    public MedicoDto removeMedico(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new MedicoDto(false, "Identificador do m�dico inv�lido");
        }

        // Criando o objeto de persist�ncia
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Verificando se o medico j� existe
        int tQtde = tDaoConsulta.countByMedico(pId);
        if (tQtde != 0)
        {
            return new MedicoDto(false, "M�dico j� tem consultas no sistema. Remo��o pro�bida");
        }

        // Criando o objeto de persist�ncia
        MedicoDao tDao = new MedicoDao();

        // Incluindo o medico
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new MedicoDto(true, "M�dico removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new MedicoDto(false, "Erro no processo de remo��o");
    }
}
