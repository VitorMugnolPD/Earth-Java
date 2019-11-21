package programa;
import bd.daos.*;



public class Programa
{

    public static void main (String[] args)
    {
        try
        {
Jogadores jooj = new Jogadores();
System.out.println(jooj.getJogadores());
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage());
        }
    }

}
