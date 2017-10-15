package br.com.agendamais.teste;

import java.time.LocalDate;
import java.util.List;

import br.com.agendamais.dao.PacienteDao;
import br.com.agendamais.model.Paciente;

public class TestePacienteDao
{
    public static void main(String[] pArgs)
    {
        // Criar um paciente
        Paciente tPacienteA = new Paciente(0, "landia@hotmail.com", "jurubeba", "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767,  37834534452L);
        Paciente tPacienteB = new Paciente(0, "restro@outlook.com", "arapuca", "Restronco Geudulto", LocalDate.of(1998, 6, 4), 3677676, 11111111111L);

        // Criando o objeto de persistência
        PacienteDao tDao = new PacienteDao();

        // Incluir o paciente
        System.out.println();
        System.out.println("Incluindo");
        Paciente tPaciente2a = tDao.create(tPacienteA);
        if (tPaciente2a != null)
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);
        Paciente tPaciente2b = tDao.create(tPacienteB);
        if (tPaciente2b != null)
            System.out.println("OK...... : " + tPaciente2b);
        else
            System.out.println("ERRO.... : " + tPaciente2b);

        // Recuperando o paciente
        System.out.println();
        System.out.println("Recuperando");
        Paciente tPaciente3a = tDao.recovery(tPaciente2a.getId());
        if (tPaciente3a != null)
            System.out.println("OK...... : " + tPaciente3a);
        else
            System.out.println("ERRO.... : " + tPaciente3a);
        Paciente tPaciente3b = tDao.recovery(tPaciente2b.getId());
        if (tPaciente3b != null)
            System.out.println("OK...... : " + tPaciente3b);
        else
            System.out.println("ERRO.... : " + tPaciente3b);

        // Atualizando o paciente
        System.out.println();
        System.out.println("Atualizando");
        tPaciente2a.setNome(tPaciente2a.getNome() + " Sildacio");
        tPaciente2a.setDataNascimento(LocalDate.of(2000, 1, 31));
        tPaciente2a.setTelefone(985008888);
        tPaciente2a.setEmail("silda@outlook.com");
        tPaciente2a.setCpf(12345678901L);
        Paciente tPaciente4a = tDao.update(tPaciente2a);
        if (tPaciente4a != null)
            System.out.println("OK...... : " + tPaciente4a);
        else
            System.out.println("ERRO.... : " + tPaciente4a);

        tPaciente2b.setNome(tPaciente2b.getNome() + " Sundreco");
        tPaciente2b.setDataNascimento(LocalDate.of(2014, 3, 3));
        tPaciente2b.setTelefone(987886776);
        tPaciente2b.setEmail("troncio@outlook.com");
        tPaciente2b.setCpf(22222222222L);
        Paciente tPaciente4b = tDao.update(tPaciente2b);
        if (tPaciente4a != null)
            System.out.println("OK...... : " + tPaciente4b);
        else
            System.out.println("ERRO.... : " + tPaciente4b);

        // Recuperando o paciente
        System.out.println();
        System.out.println("Recuperando");
        Paciente tPaciente5a = tDao.recovery(tPaciente2a.getId());
        if (tPaciente5a != null)
            System.out.println("OK...... : " + tPaciente5a);
        else
            System.out.println("ERRO.... : " + tPaciente5a);
        Paciente tPaciente5b = tDao.recovery(tPaciente2b.getId());
        if (tPaciente5b != null)
            System.out.println("OK...... : " + tPaciente5b);
        else
            System.out.println("ERRO.... : " + tPaciente5b);

        // Listar os pacientes
        List<Paciente> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Paciente tPaciente : tLista)
        {
            System.out.println("OK...... : " + tPaciente);
        }

        // Remover o paciente
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tPaciente2a.getId()))
            System.out.println("OK...... : " + tPaciente2a);
        else
            System.out.println("ERRO.... : " + tPaciente2a);
        if (tDao.delete(tPaciente2b.getId()))
            System.out.println("OK...... : " + tPaciente2b);
        else
            System.out.println("ERRO.... : " + tPaciente2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tPaciente2a.getId()))
            System.out.println("ERRO.... : " + tPaciente2a);
        else
            System.out.println("OK...... : " + tPaciente2a);
        if (tDao.delete(tPaciente2b.getId()))
            System.out.println("ERRO.... : " + tPaciente2b);
        else
            System.out.println("OK...... : " + tPaciente2b);
    }
}
