package br.com.agendamais.teste;

import java.time.LocalDate;

import br.com.agendamais.controller.PacienteController;
import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.dto.PacienteDto;
import br.com.agendamais.model.Paciente;

public class TestePacienteController
{
    public static void main(String[] pArgs)
    {
        //
        // Pré Teste
        //
        // Criar um paciente
        Paciente tPacienteA = new Paciente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com", 37834534452L);

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Incluir o paciente
        System.out.println();
        System.out.println("Incluindo um paciente");
        Paciente tPaciente2a = tDao.create(tPacienteA);
        if (tPaciente2a != null)
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);

        //
        // Teste
        //
        // Criar um paciente
        Paciente tPacienteB = new Paciente(0, "Restronco Geudulto", LocalDate.of(1998, 6, 4), 3677676, "restro@gmail.com", 11111111111L);

        // Criando o objeto de Controller
        PacienteController tController = new PacienteController();

        // Incluindo o paciente
        System.out.println();
        System.out.println("Incluindo um paciente via controller");
        PacienteDto tDto = tController.cadastrarPaciente(tPacienteB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getPaciente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Incluindo o paciente
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

        // Incluindo o paciente
        System.out.println();
        System.out.println("Incluindo um paciente já existente");
        tDto = tController.cadastrarPaciente(tPacienteA);
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
        System.out.println("Removendo um paciente");
        if (tDao.delete(tPaciente2a.getId()))
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);
    }
}
