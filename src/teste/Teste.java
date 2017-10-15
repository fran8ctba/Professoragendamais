package teste;

import javax.swing.JButton;

import sun.font.StrikeCache;

public class Teste
{
    public static void main(String[] args)
    {

        Object o1 = new StrikeCache();
        Object o2 = new Mae(20);
        Object o3 = new JButton("OK");

        System.out.println("Construindo m");

        Mae m = new Mae(50);

        System.out.println("Construindo f");

        Mae f = new Filha(30);

        System.out.println("Construindo n");

        Mae n = new Neta(10);

        m.setNome("Ana");
        m.setProfissao("Advogado");
        m.setTelefone(323213123);

        f.setNome("Tereza");
        ((Filha)f).setHobby("Games");
        f.setProfissao("Dentista");
        f.setTelefone(994939493);

        n.setNome("Viviane");
        ((Neta)n).setHobby("skate");
        ((Neta)n).setEscola("Opet");
        n.setProfissao("Estudante");
        n.setTelefone(929392913);

        System.out.println("Mãe   : " + m.toString());
        System.out.println("Filha : " + f.toString());
        System.out.println("Neta  : " + n.toString());
    }
}
