package br.com.agendamais.teste;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import br.com.agendamais.controller.PacienteController;
import br.com.agendamais.dao.ConsultaDao;
import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.dto.PacienteDto;
import br.com.agendamais.model.Consulta;
import br.com.agendamais.model.Medico;
import br.com.agendamais.model.Paciente;

public class TestePacienteController
{
    private static SimpleDateFormat sFormatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] pArgs) throws ParseException
    {
        //
        // Pré Teste
        //
        // Criar um paciente
        Paciente tPacienteA = new Paciente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com", 37834534452L);

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

        // Criar um medico
        Medico tMedicoA = new Medico(0, "Estroncio Vago", 3432, "Urologista");

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

        Date tData1 = sFormatador.parse("15/09/2017 18:30");
        Consulta tConsultaA = new Consulta(0, tData1, new BigDecimal("250.00"), "Rua getulio vargas, 902", tPaciente2a.getId(), tMedico2a.getId());

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
        PacienteController tController = new PacienteController();

        // Criar um paciente
        Paciente tPacienteB = new Paciente(0, "Restronco Geudulto", LocalDate.of(1998, 6, 4), 3677676, "restro@gmail.com", 11111111111L);

        // Criar o paciente
        System.out.println();
        System.out.println("Incluindo um paciente via controller");
        PacienteDto tDto = tController.cadastrarPaciente(tPacienteB);
        if (tDto.isOk())
        {
            // Recuperando o paciente incluído para obter o ID gerado
            tPacienteB = tDto.getPaciente();
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tPacienteB);
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Incluindo um paciente nulo");
        tDto = tController.cadastrarPaciente(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o paciente
        System.out.println();
        System.out.println("Recuperando um paciente via controller");
        tDto = tController.recuperarPaciente(tPacienteB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getPaciente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um paciente com id inválido");
        tDto = tController.recuperarPaciente(-32432);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um paciente não existente");
        tDto = tController.recuperarPaciente(9999999);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Atualizar o paciente
        tPacienteB.setNome(tPacienteB.getNome() + " Silvantino");
        tPacienteB.setDataNascimento(LocalDate.of(2000, 1, 1));
        tPacienteB.setTelefone(998975511);
        tPacienteB.setEmail("silvantino@gmail.com");
        tPacienteB.setCpf(tPaciente2a.getCpf());

        System.out.println();
        System.out.println("Atualizando um paciente para um cpf que existe");
        tDto = tController.atualizarPaciente(tPacienteB);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Acertando o CPF para a atualização
        tPacienteB.setCpf(88888888888L);

        // Atualizando o paciente
        System.out.println();
        System.out.println("Atualizando um paciente via controller");
        tDto = tController.atualizarPaciente(tPacienteB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getPaciente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Atualizando um paciente nulo");
        tDto = tController.atualizarPaciente(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        Paciente tPacienteC = new Paciente(99999, "nono nono", LocalDate.now(), 1, "nono@gmail", 1L);

        System.out.println();
        System.out.println("Atualizando um paciente que não existe");
        tDto = tController.atualizarPaciente(tPacienteC);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o paciente
        System.out.println();
        System.out.println("Recuperando um paciente via controller");
        tDto = tController.recuperarPaciente(tPacienteB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getPaciente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Removendo o paciente
        System.out.println();
        System.out.println("Removendo um paciente via controller");
        tDto = tController.removePaciente(tPacienteB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um paciente com id inválido");
        tDto = tController.removePaciente(-4);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um paciente com consulta");
        tDto = tController.removePaciente(tPacienteA.getId());
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }


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
        if (tDao.delete(tPaciente2a.getId()))
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);

        // Remover o medico
        System.out.println();
        System.out.println("Removendo o medico");
        if (tMedicoDao.delete(tMedico2a.getId()))
            System.out.println("OK...... : " + tMedico2a);
        else
            System.out.println("ERRO.... : " + tMedico2a);
    }
}
