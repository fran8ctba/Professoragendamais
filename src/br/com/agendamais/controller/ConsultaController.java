package br.com.agendamais.controller;

import java.util.Date;

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
        // Verificar as informa��es
        if (pConsulta.getIdMedico() <= 0)
        {
            return new ConsultaDto(false, "Identificador do m�dico inv�lido");
        }
        if (pConsulta.getIdPaciente() <= 0)
        {
            return new ConsultaDto(false, "Identificador do paciente inv�lido");
        }
        if (pConsulta.getDataConsulta() == null)
        {
            return new ConsultaDto(false, "Tentativa de agendamento para data nula");
        }
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
            return new ConsultaDto(false, "N�o existe m�dico com o identificador informado");
        }
        Paciente tPaciente = tDaoPaciente.recovery(pConsulta.getIdPaciente());
        if (tPaciente == null)
        {
            return new ConsultaDto(false, "N�o existe paciente com o identificador informado");
        }

        // Verificando se o m�dico est� dispon�vel na data
        Consulta tConsulta = tDaoConsulta.recoveryByMedicoData(pConsulta.getIdMedico(), pConsulta.getDataConsulta());
        if (tConsulta != null)
        {
            return new ConsultaDto(false, "M�dico com consulta agendada na data informada");
        }

        // Incluindo a consulta
        tConsulta = tDaoConsulta.create(pConsulta);
        if (tConsulta == null)
        {
            return new ConsultaDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new ConsultaDto(true, "Consulta inclu�da com sucesso", tConsulta);
    }

    public ConsultaDto desmarcarConsulta(int pIdConsulta)
    {
        return null;
    }

    public ConsultaDto remarcarConsulta(int pIdConsulta, Date pDataConsulta)
    {
        return null;
    }

    public ConsultaDto pesquisarConsultaPorPaciente(int pIdPaciente)
    {
        return null;
    }

    public ConsultaDto pesquisarConsultaPorMedico(int pIdMedico)
    {
        return null;
    }

    public ConsultaDto pesquisarConsultaPorData(Date pDataConsulta)
    {
        return null;
    }

}
