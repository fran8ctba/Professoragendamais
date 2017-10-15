package br.com.agendamais.controller;

import java.util.Date;
import java.util.List;

import br.com.agendamais.dao.ConsultaDao;
import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.dto.ConsultaDto;
import br.com.agendamais.model.Consulta;
import br.com.agendamais.model.Medico;
import br.com.agendamais.model.Paciente;

public class ConsultaController
{
    public ConsultaDto agendarConsulta(Consulta pConsulta)
    {
        // Verificar as informações
        Date tDataAtual = new Date();
        if (pConsulta.getDataConsulta().before(tDataAtual))
        {
            return new ConsultaDto(false, "Data de consulta anterior a data atual");
        }

        // Criando os objetos DAO
        MedicoDao tDaoMedico = new MedicoDao();
        PacienteDao tDaoPaciente = new PacienteDao();
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Validando se os identificadores existem na base de dados
        Medico tMedico = tDaoMedico.recovery(pConsulta.getIdMedico());
        if (tMedico == null)
        {
            return new ConsultaDto(false, "Não existe médico com o identificador informado");
        }
        Paciente tPaciente = tDaoPaciente.recovery(pConsulta.getIdPaciente());
        if (tPaciente == null)
        {
            return new ConsultaDto(false, "Não existe paciente com o identificador informado");
        }

        // Verificando se o médico está disponível na data
        Consulta tConsulta = tDaoConsulta.recoveryByMedicoData(pConsulta.getIdMedico(), pConsulta.getDataConsulta());
        if (tConsulta != null)
        {
            return new ConsultaDto(false, "Médico com consulta agendada na data informada");
        }

        // Incluindo a consulta
        tConsulta = tDaoConsulta.create(pConsulta);
        if (tConsulta == null)
        {
            return new ConsultaDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new ConsultaDto(true, "Consulta incluída com sucesso", tConsulta);
    }

    public ConsultaDto consultarConsulta(int pIdConsulta)
    {
        // Verificar as informações
        if (pIdConsulta <= 0)
        {
            return new ConsultaDto(false, "Identificador da consulta inválido");
        }

        // Criando os objetos DAO
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Validando se os identificadores existem na base de dados
        Consulta tConsulta = tDaoConsulta.recovery(pIdConsulta);
        if (tConsulta == null)
        {
            return new ConsultaDto(false, "Não existe consulta com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ConsultaDto(true, "Consulta recuperada com sucesso", tConsulta);
    }

    public ConsultaDto desmarcarConsulta(int pIdConsulta)
    {
        // Verificar as informações
        if (pIdConsulta <= 0)
        {
            return new ConsultaDto(false, "Identificador da consulta inválido");
        }

        // Criando os objetos DAO
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Validando se os identificadores existem na base de dados
        Consulta tConsulta = tDaoConsulta.recovery(pIdConsulta);
        if (tConsulta == null)
        {
            return new ConsultaDto(false, "Não existe consulta com o identificador informado");
        }

        // Removendo a consulta
        if (!tDaoConsulta.delete(pIdConsulta))
        {
            return new ConsultaDto(false, "Erro no processo de remoção");
        }

        // Retornando o indicativo de sucesso
        return new ConsultaDto(true, "Consulta desmarcada com sucesso", tConsulta);
    }

    public ConsultaDto remarcarConsulta(int pIdConsulta, Date pDataConsulta)
    {
        // Verificar as informações
        if (pIdConsulta <= 0)
        {
            return new ConsultaDto(false, "Identificador da consulta inválido");
        }
        if (pDataConsulta == null)
        {
            return new ConsultaDto(false, "Data de remarcação não pode ser nula");
        }
        Date tDataAtual = new Date();
        if (pDataConsulta.before(tDataAtual))
        {
            return new ConsultaDto(false, "Data de consulta anterior a data atual");
        }

        // Criando os objetos DAO
        ConsultaDao tDaoConsulta = new ConsultaDao();

        // Validando se os identificadores existem na base de dados
        Consulta tConsulta = tDaoConsulta.recovery(pIdConsulta);
        if (tConsulta == null)
        {
            return new ConsultaDto(false, "Não existe consulta com o identificador informado");
        }

        // Verificando se o médico está disponível na data
        Consulta tConsultaMarcada = tDaoConsulta.recoveryByMedicoData(tConsulta.getIdMedico(), pDataConsulta);
        if (tConsultaMarcada != null)
        {
            return new ConsultaDto(false, "Médico com consulta agendada na data informada");
        }

        // Remarcando a consulta
        tConsulta.setDataConsulta(pDataConsulta);
        tConsulta = tDaoConsulta.update(tConsulta);
        if (tConsulta == null)
        {
            return new ConsultaDto(false, "Erro no processo de remarcação");
        }

        // Retornando o indicativo de sucesso
        return new ConsultaDto(true, "Consulta remarcada com sucesso", tConsulta);
    }

    public ConsultaDto pesquisarConsultaPorPaciente(int pIdPaciente)
    {
        // Verificar as informações
        if (pIdPaciente <= 0)
        {
            return new ConsultaDto(false, "Identificador do paciente inválido");
        }

        // Criando os objetos DAO
        ConsultaDao tDaoConsulta = new ConsultaDao();

        List<Consulta> tLista =tDaoConsulta.searchByIdPaciente(pIdPaciente);

        // Retornar a lista
        return new ConsultaDto(true, "Lista de consultas recuperada com sucesso", tLista);
    }

    public ConsultaDto pesquisarConsultaPorMedico(int pIdMedico)
    {
        // Verificar as informações
        if (pIdMedico <= 0)
        {
            return new ConsultaDto(false, "Identificador do médico inválido");
        }

        // Criando os objetos DAO
        ConsultaDao tDaoConsulta = new ConsultaDao();

        List<Consulta> tLista =tDaoConsulta.searchByIdMedico(pIdMedico);

        // Retornar a lista
        return new ConsultaDto(true, "Lista de consultas recuperada com sucesso", tLista);
    }

    public ConsultaDto pesquisarConsultaPorData(Date pDataConsulta)
    {
        // Verificar as informações
        if (pDataConsulta == null)
        {
            return new ConsultaDto(false, "Data da consulta não pode ser nula");
        }

        // Criando os objetos DAO
        ConsultaDao tDaoConsulta = new ConsultaDao();

        List<Consulta> tLista =tDaoConsulta.searchByData(pDataConsulta);

        // Retornar a lista
        return new ConsultaDto(true, "Lista de consultas recuperada com sucesso", tLista);
    }

}
