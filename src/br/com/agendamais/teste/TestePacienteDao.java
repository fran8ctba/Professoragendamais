package br.com.agendamais.teste;

import java.time.LocalDate;

import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.model.Paciente;

public class TestePacienteDao
{
    public static void main(String[] pArgs)
    {
        // Criar um paciente
        Paciente tPaciente1 = new Paciente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com", 37834534452L);

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Incluir o paciente
        System.out.println();
        System.out.println("Incluindo");
        Paciente tPaciente2 = tDao.create(tPaciente1);
        if (tPaciente2 != null)
            System.out.println("OK...... : " + tPaciente2);
        else
            System.out.println("ERRO.... : " + tPaciente2);

        // Recuperando o paciente
        System.out.println();
        System.out.println("Recuperando");
        Paciente tPaciente3 = tDao.recovery(tPaciente2.getId());
        if (tPaciente3 != null)
            System.out.println("OK...... : " + tPaciente3);
        else
            System.out.println("ERRO.... : " + tPaciente3);

        // Atualizando o paciente
        System.out.println();
        System.out.println("Atualizando");
        tPaciente2.setNome(tPaciente2.getNome() + " Sildacio");
        tPaciente2.setDataNascimento(LocalDate.of(2000, 1, 31));
        tPaciente2.setTelefone(985008888);
        tPaciente2.setEmail("silda@outlook.com");
        tPaciente2.setCpf(12345678901L);
        Paciente tPaciente4 = tDao.update(tPaciente2);
        if (tPaciente3 != null)
            System.out.println("OK...... : " + tPaciente4);
        else
            System.out.println("ERRO.... : " + tPaciente4);

        // Recuperando o paciente
        System.out.println();
        System.out.println("Recuperando");
        Paciente tPaciente5 = tDao.recovery(tPaciente2.getId());
        if (tPaciente3 != null)
            System.out.println("OK...... : " + tPaciente5);
        else
            System.out.println("ERRO.... : " + tPaciente5);

        // Remover o paciente
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tPaciente2.getId()))
            System.out.println("OK...... : " + tPaciente2);
        else
            System.out.println("ERRO.... : " + tPaciente2);

        // Verificando se removeue
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tPaciente2.getId()))
            System.out.println("ERRO.... : " + tPaciente2);
        else
            System.out.println("OK...... : " + tPaciente2);
    }
}
