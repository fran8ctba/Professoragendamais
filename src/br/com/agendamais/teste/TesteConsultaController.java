package br.com.agendamais.teste;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.agendamais.controller.ConsultaController;
import br.com.agendamais.dao.ConsultaDao;
import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.dto.ConsultaDto;
import br.com.agendamais.model.Consulta;
import br.com.agendamais.model.Medico;
import br.com.agendamais.model.Paciente;

public class TesteConsultaController
{
    private static SimpleDateFormat sFormatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] pArgs) throws ParseException
    {
        //
        // Pré Teste
        //
        // Criar um paciente
        Paciente tPacienteA = new Paciente(0, "landia@hotmail.com", "jurubeba", "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767,  37834534452L);
        Paciente tPacienteB = new Paciente(0, "restro@outlook.com", "arapuca", "Restronco Geudulto", LocalDate.of(1998, 6, 4), 3677676, 11111111111L);
        Paciente tPacienteC = new Paciente(0, "xumbrega@gmail.com", "xumbra2016", "Xumbrega Xunxolfo", LocalDate.of(1998, 12, 5), 3898787, 22222222222L);

        // Criando o objeto de persistência
        PacienteDao tPacienteDao = new PacienteDao();

        // Incluir o paciente
        System.out.println();
        System.out.println("Incluindo o paciente");
        Paciente tPaciente2a = tPacienteDao.create(tPacienteA);
        if (tPaciente2a != null)
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);
        Paciente tPaciente2b = tPacienteDao.create(tPacienteB);
        if (tPaciente2b != null)
            System.out.println("OK...... : " + tPaciente2b);
        else
            System.out.println("ERRO.... : " + tPaciente2b);
        Paciente tPaciente2c = tPacienteDao.create(tPacienteC);
        if (tPaciente2c != null)
            System.out.println("OK...... : " + tPaciente2c);
        else
            System.out.println("ERRO.... : " + tPaciente2c);

        // Criar um medico
        Medico tMedicoA = new Medico(0, "estroncio@gmail.com", "pororoca", "Estroncio Vago", 3432, "Urologista");
        Medico tMedicoB = new Medico(0, "favo@gmail.com", "cafune","Favilda Freldinca", 222, "Pediatra");

        // Criando o objeto de persistência
        MedicoDao tMedicoDao = new MedicoDao();

        // Incluir o medico
        System.out.println();
        System.out.println("Incluindo o médico");
        Medico tMedico2a = tMedicoDao.create(tMedicoA);
        if (tMedico2a != null)
            System.out.println("OK...... : " + tMedico2a);
        else
            System.out.println("ERRO.... : " + tMedico2a);
        Medico tMedico2b = tMedicoDao.create(tMedicoB);
        if (tMedico2b != null)
            System.out.println("OK...... : " + tMedico2b);
        else
            System.out.println("ERRO.... : " + tMedico2b);

        // Obtendo a data de amanhã a partir da data atual
        Date tAmanha = new Date(new Date().getTime() + /* 1 dia */ 24L * 60 * 60 * 1000);
        String tAmanhaStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(tAmanha);
        Date tData1 = sFormatador.parse(tAmanhaStr + " 18:30");
        Consulta tConsultaA = new Consulta(0, tData1, new BigDecimal("100.00"), "Av. Sete de Setembro, 234, cj 3", tPaciente2a.getId(), tMedico2a.getId());

        // Criando o objeto de persistência
        ConsultaDao tDao = new ConsultaDao();

        // Incluir a consulta
        System.out.println();
        System.out.println("Incluindo a consulta");
        Consulta tConsulta2a = tDao.create(tConsultaA);
        if (tConsulta2a != null)
            System.out.println("OK...... : " + tConsulta2a);
        else
            System.out.println("ERRO.... : " + tConsulta2a);

        //
        // Teste
        //

        // Criando o objeto de Controller
        ConsultaController tController = new ConsultaController();

        // Agendar uma consulta
        Date tData2 = sFormatador.parse(tAmanhaStr + " 10:00");
        Consulta tConsultaB = new Consulta(0, tData2, new BigDecimal("250.00"), "Rua getulio vargas, 902", tPaciente2b.getId(), tMedico2b.getId());

        // Agendar a consulta
        System.out.println();
        System.out.println("Agendando a consulta via controller");
        ConsultaDto tDto = tController.agendarConsulta(tConsultaB);
        if (tDto.isOk())
        {
            // Recuperando o paciente incluído para obter o ID gerado
            tConsultaB = tDto.getConsulta();
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tConsultaB);
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Agendar a consulta para paciente que nao existe
        System.out.println();
        System.out.println("Agendando a consulta para paciente que não existe");
        tConsultaB.setIdPaciente(0);
        tDto = tController.agendarConsulta(tConsultaB);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Agendar a consulta para médico que nao existe
        System.out.println();
        System.out.println("Agendando a consulta para médico que não existe");
        tConsultaB.setIdMedico(0);
        tDto = tController.agendarConsulta(tConsultaB);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Agendar a consulta para ontem
        System.out.println();
        System.out.println("Agendando a consulta para ontem via controller");
        // Acertando a data de consulta para ontem
        Date tDataOntem = new Date(new Date().getTime() - /* 1 dia */ 24L * 60 * 60 * 1000);
        tConsultaB.setDataConsulta(tDataOntem);
        tDto = tController.agendarConsulta(tConsultaB);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Agendar a consulta para horario já alocado
        System.out.println();
        System.out.println("Agendando a consulta para horário já alocado");
        Consulta tConsultaC = new Consulta(0, tData2, new BigDecimal("250.00"), "Rua getulio vargas, 902", tPaciente2c.getId(), tMedico2b.getId());
        tDto = tController.agendarConsulta(tConsultaC);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar a consulta
        System.out.println();
        System.out.println("Recuperando a consulta via controller");
        tDto = tController.consultarConsulta(tConsultaB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getConsulta());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando uma consulta com id inválido");
        tDto = tController.consultarConsulta(-32432);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando uma consulta não existente");
        tDto = tController.consultarConsulta(9999999);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Acertando a nova data da consulta
        tData2 = sFormatador.parse(tAmanhaStr + " 15:00");

        // Reagendando a consulta
        System.out.println();
        System.out.println("Reagendando a consulta via controller");
        tDto = tController.remarcarConsulta(tConsultaB.getId(), tData2);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getConsulta());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Reagendando para um identificador não existente");
        tDto = tController.remarcarConsulta(-32432, tData2);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Reagendando para uma data nula");
        tDto = tController.remarcarConsulta(tConsultaB.getId(), null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Reagendando para uma data anterior");
        tDto = tController.remarcarConsulta(tConsultaB.getId(), tDataOntem);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }


        System.out.println();
        System.out.println("Reagendando para um horário já alocado");
        tDto = tController.remarcarConsulta(tConsultaB.getId(), tData2);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar a consulta
        System.out.println();
        System.out.println("Recuperando a consulta via controller");
        tDto = tController.consultarConsulta(tConsultaB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getConsulta());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperando as consultas por paciente
        System.out.println();
        System.out.println("Recuperando as consultas por paciente");
        tDto = tController.pesquisarConsultaPorPaciente(tPaciente2b.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            List<Consulta> tLista = tDto.getLista();
            for (Consulta tConsulta : tLista)
            {
                System.out.println("         : " + tConsulta);
            }
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperando as consultas por médico
        System.out.println();
        System.out.println("Recuperando as consultas por médico");
        tDto = tController.pesquisarConsultaPorMedico(tMedico2b.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            List<Consulta> tLista = tDto.getLista();
            for (Consulta tConsulta : tLista)
            {
                System.out.println("         : " + tConsulta);
            }
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperando as consultas por data
        System.out.println();
        System.out.println("Recuperando as consultas por data");
        tDto = tController.pesquisarConsultaPorData(tData2);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            List<Consulta> tLista = tDto.getLista();
            for (Consulta tConsulta : tLista)
            {
                System.out.println("         : " + tConsulta);
            }
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Desmarcando a consulta
        System.out.println();
        System.out.println("Desmarcando uma consulta via controller");
        tDto = tController.desmarcarConsulta(tConsultaB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

//        System.out.println();
//        System.out.println("Removendo um paciente com id inválido");
//        tDto = tController.removePaciente(-4);
//        if (!tDto.isOk())
//        {
//            System.out.println("OK...... : " + tDto.getMensagem());
//        }
//        else
//        {
//            System.out.println("ERRO.... : " + tDto.getMensagem());
//        }
//
//        System.out.println();
//        System.out.println("Removendo um paciente com consulta");
//        tDto = tController.removePaciente(tPacienteA.getId());
//        if (!tDto.isOk())
//        {
//            System.out.println("OK...... : " + tDto.getMensagem());
//        }
//        else
//        {
//            System.out.println("ERRO.... : " + tDto.getMensagem());
//        }


        //
        // Pós teste
        //
        // Remover o paciente
        System.out.println();
        System.out.println("Removendo a consulta");
        if (tDao.delete(tConsulta2a.getId()))
            System.out.println("OK...... : " + tConsulta2a);
        else
            System.out.println("ERRO.... : " + tConsulta2a);

        System.out.println();
        System.out.println("Removendo o paciente");
        if (tPacienteDao.delete(tPaciente2a.getId()))
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);
        if (tPacienteDao.delete(tPaciente2b.getId()))
            System.out.println("OK...... : " + tPaciente2b);
        else
            System.out.println("ERRO.... : " + tPaciente2b);
        if (tPacienteDao.delete(tPaciente2c.getId()))
            System.out.println("OK...... : " + tPaciente2c);
        else
            System.out.println("ERRO.... : " + tPaciente2c);

        // Remover o medico
        System.out.println();
        System.out.println("Removendo o médico");
        if (tMedicoDao.delete(tMedico2a.getId()))
            System.out.println("OK...... : " + tMedico2a);
        else
            System.out.println("ERRO.... : " + tMedico2a);
        if (tMedicoDao.delete(tMedico2b.getId()))
            System.out.println("OK...... : " + tMedico2b);
        else
            System.out.println("ERRO.... : " + tMedico2b);
    }
}
