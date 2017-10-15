package br.com.agendamais.teste;

import java.util.List;

import br.com.agendamais.dao.MedicoDao;
import br.com.agendamais.model.Medico;

public class TesteMedicoDao
{
    public static void main(String[] pArgs)
    {
        // Criar um medico
        Medico tMedicoA = new Medico(0, "Estroncio Vago", 3432, "Urologista");
        Medico tMedicoB = new Medico(0, "Favilda Freldinca", 222, "Pediatra");

        // Criando o objeto de persistência
        MedicoDao tDao = new MedicoDao();

        // Incluir o medico
        System.out.println();
        System.out.println("Incluindo");
        Medico tMedico2a = tDao.create(tMedicoA);
        if (tMedico2a != null)
            System.out.println("OK...... : " + tMedico2a);
        else
            System.out.println("ERRO.... : " + tMedico2a);
        Medico tMedico2b = tDao.create(tMedicoB);
        if (tMedico2b != null)
            System.out.println("OK...... : " + tMedico2b);
        else
            System.out.println("ERRO.... : " + tMedico2b);

        // Recuperando o medico
        System.out.println();
        System.out.println("Recuperando");
        Medico tMedico3a = tDao.recovery(tMedico2a.getId());
        if (tMedico3a != null)
            System.out.println("OK...... : " + tMedico3a);
        else
            System.out.println("ERRO.... : " + tMedico3a);
        Medico tMedico3b = tDao.recovery(tMedico2b.getId());
        if (tMedico3b != null)
            System.out.println("OK...... : " + tMedico3b);
        else
            System.out.println("ERRO.... : " + tMedico3b);

        // Atualizando o medico
        System.out.println();
        System.out.println("Atualizando");
        tMedico2a.setNome(tMedico2a.getNome() + " Sentercio");
        tMedico2a.setCrm(4444);
        tMedico2a.setEspecialidade("Urologista Oncologista");
        Medico tMedico4a = tDao.update(tMedico2a);
        if (tMedico4a != null)
            System.out.println("OK...... : " + tMedico4a);
        else
            System.out.println("ERRO.... : " + tMedico4a);

        tMedico2b.setNome(tMedico2b.getNome() + " Sundreco");
        tMedico2b.setCrm(2222);
        tMedico2b.setEspecialidade("Pediatra Cardíaco");
        Medico tMedico4b = tDao.update(tMedico2b);
        if (tMedico4a != null)
            System.out.println("OK...... : " + tMedico4b);
        else
            System.out.println("ERRO.... : " + tMedico4b);

        // Recuperando o medico
        System.out.println();
        System.out.println("Recuperando");
        Medico tMedico5a = tDao.recovery(tMedico2a.getId());
        if (tMedico5a != null)
            System.out.println("OK...... : " + tMedico5a);
        else
            System.out.println("ERRO.... : " + tMedico5a);
        Medico tMedico5b = tDao.recovery(tMedico2b.getId());
        if (tMedico5b != null)
            System.out.println("OK...... : " + tMedico5b);
        else
            System.out.println("ERRO.... : " + tMedico5b);

        // Listar os medicos
        List<Medico> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Medico tMedico : tLista)
        {
            System.out.println("OK...... : " + tMedico);
        }

        // Remover o medico
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tMedico2a.getId()))
            System.out.println("OK...... : " + tMedico2a);
        else
            System.out.println("ERRO.... : " + tMedico2a);
        if (tDao.delete(tMedico2b.getId()))
            System.out.println("OK...... : " + tMedico2b);
        else
            System.out.println("ERRO.... : " + tMedico2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tMedico2a.getId()))
            System.out.println("ERRO.... : " + tMedico2a);
        else
            System.out.println("OK...... : " + tMedico2a);
        if (tDao.delete(tMedico2b.getId()))
            System.out.println("ERRO.... : " + tMedico2b);
        else
            System.out.println("OK...... : " + tMedico2b);
    }
}
