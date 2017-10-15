package teste;

public class Filha extends Mae
{
    protected String hobby;

    public Filha(int pIdade)
    {
        super(pIdade);
        System.out.println("Construtor padrão da classe filha");
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String pHobby)
    {
        hobby = pHobby;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(super.toString());
        tBuilder.append(", ");
        tBuilder.append(hobby);
        tBuilder.append("]");
        return tBuilder.toString();
    }

















}
