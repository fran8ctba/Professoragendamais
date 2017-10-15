package teste;

public class Neta extends Filha
{
    private String escola;

    public Neta(int pIdade)
    {
        super(pIdade);
        System.out.println("Construtor padrão da classe neta");
    }

    public String getEscola()
    {
        return escola;
    }

    public void setEscola(String pEscola)
    {
        escola = pEscola;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(super.toString());
        tBuilder.append(", ");
        tBuilder.append(escola);
        tBuilder.append("]");
        return tBuilder.toString();
    }















}
