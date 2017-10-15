package br.com.agendamais.teste;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.agendamais.dao.ConsultaDao;
import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.model.Consulta;
import br.com.agendamais.model.Medico;
import br.com.agendamais.model.Paciente;

public class TesteConsultaDao
{
    private static SimpleDateFormat sFormatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] pArgs) throws ParseException
    {
        //
        // Pré Teste
        //
        // Criar um paciente
        Paciente tPacienteA = new Paciente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com", 37834534452L);
        Paciente tPacienteB = new Paciente(0, "Restronco Geudulto", LocalDate.of(1998, 6, 4), 3677676, "restro@gmail.com", 11111111111L);

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

        // Criar um medico
        Medico tMedicoA = new Medico(0, "Estroncio Vago", 3432, "Urologista");
        Medico tMedicoB = new Medico(0, "Favilda Freldinca", 222, "Pediatra");

        // Criando o objeto de persistência
        MedicoDao tMedicoDao = new MedicoDao();

        // Incluir o medico
        System.out.println();
        System.out.println("Incluindo");
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

        //
        // Teste
        //
        // Criar uma consulta
        Date tData1 = sFormatador.parse("15/09/2017 18:30");
        Date tData2 = sFormatador.parse("04/10/2017 10:45");
        Consulta tConsultaA = new Consulta(0, tData1, new BigDecimal("250.00"), "Rua getulio vargas, 902", tPaciente2a.getId(), tMedico2a.getId());
        Consulta tConsultaB = new Consulta(0, tData2, new BigDecimal("150.00"), "Rua Iguacu, 9", tPaciente2b.getId(), tMedico2b.getId());

        // Criando o objeto de persistência
        ConsultaDao tDao = new ConsultaDao();

        // Incluir a consulta
        System.out.println();
        System.out.println("Incluindo");
        Consulta tConsulta2a = tDao.create(tConsultaA);
        if (tConsulta2a != null)
            System.out.println("OK...... : " + tConsulta2a);
        else
            System.out.println("ERRO.... : " + tConsulta2a);
        Consulta tConsulta2b = tDao.create(tConsultaB);
        if (tConsulta2b != null)
            System.out.println("OK...... : " + tConsulta2b);
        else
            System.out.println("ERRO.... : " + tConsulta2b);

        // Recuperando a consulta
        System.out.println();
        System.out.println("Recuperando");
        Consulta tConsulta3a = tDao.recovery(tConsulta2a.getId());
        if (tConsulta3a != null)
            System.out.println("OK...... : " + tConsulta3a);
        else
            System.out.println("ERRO.... : " + tConsulta3a);
        Consulta tConsulta3b = tDao.recovery(tConsulta2b.getId());
        if (tConsulta3b != null)
            System.out.println("OK...... : " + tConsulta3b);
        else
            System.out.println("ERRO.... : " + tConsulta3b);

        // Atualizando a consulta
        Date tData3 = sFormatador.parse("25/09/2017 10:30");
        Date tData4 = sFormatador.parse("14/11/2017 08:00");
        System.out.println();
        System.out.println("Atualizando");
        tConsulta2a.setDataConsulta(tData3);
        tConsulta2a.setValor(new BigDecimal("100.00"));
        tConsulta2a.setEndereco("Avenida Getulio Vargas, 902");
        tConsulta2a.setIdPaciente(tPaciente2b.getId());
        tConsulta2a.setIdMedico(tMedico2b.getId());
        Consulta tConsulta4a = tDao.update(tConsulta2a);
        if (tConsulta4a != null)
            System.out.println("OK...... : " + tConsulta4a);
        else
            System.out.println("ERRO.... : " + tConsulta4a);
        tConsulta2b.setDataConsulta(tData4);
        tConsulta2b.setValor(new BigDecimal("800.00"));
        tConsulta2b.setEndereco("Avenida Sete de Setembro, 10, cj 13a");
        tConsulta2b.setIdPaciente(tPaciente2a.getId());
        tConsulta2b.setIdMedico(tMedico2a.getId());
        Consulta tConsulta4b = tDao.update(tConsulta2b);
        if (tConsulta4a != null)
            System.out.println("OK...... : " + tConsulta4b);
        else
            System.out.println("ERRO.... : " + tConsulta4b);

        // Recuperando a consulta
        System.out.println();
        System.out.println("Recuperando");
        Consulta tConsulta5a = tDao.recovery(tConsulta2a.getId());
        if (tConsulta5a != null)
            System.out.println("OK...... : " + tConsulta5a);
        else
            System.out.println("ERRO.... : " + tConsulta5a);
        Consulta tConsulta5b = tDao.recovery(tConsulta2b.getId());
        if (tConsulta5b != null)
            System.out.println("OK...... : " + tConsulta5b);
        else
            System.out.println("ERRO.... : " + tConsulta5b);

        // Listar os consultas
        List<Consulta> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Consulta tConsulta : tLista)
        {
            System.out.println("OK...... : " + tConsulta);
        }

        // Remover a consulta
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tConsulta2a.getId()))
            System.out.println("OK...... : " + tConsulta2a);
        else
            System.out.println("ERRO.... : " + tConsulta2a);
        if (tDao.delete(tConsulta2b.getId()))
            System.out.println("OK...... : " + tConsulta2b);
        else
            System.out.println("ERRO.... : " + tConsulta2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tConsulta2a.getId()))
            System.out.println("ERRO.... : " + tConsulta2a);
        else
            System.out.println("OK...... : " + tConsulta2a);
        if (tDao.delete(tConsulta2b.getId()))
            System.out.println("ERRO.... : " + tConsulta2b);
        else
            System.out.println("OK...... : " + tConsulta2b);

        //
        // Pós teste
        //
        // Remover o paciente
        System.out.println();
        System.out.println("Removendo");
        if (tPacienteDao.delete(tPaciente2a.getId()))
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);
        if (tPacienteDao.delete(tPaciente2b.getId()))
            System.out.println("OK...... : " + tPaciente2b);
        else
            System.out.println("ERRO.... : " + tPaciente2b);

        // Remover o medico
        System.out.println();
        System.out.println("Removendo");
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
