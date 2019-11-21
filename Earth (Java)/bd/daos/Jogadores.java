package bd.daos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import bd.*;
import bd.core.*;
import bd.dbos.*;

/**
 * A classe Jogadores tem como objetivo o acesso à tabela Jogador no banco de dados
 * tendo métodos para retornar jogadores,procurar jogador,deletar jogador,
 * incluir jogador e alterar jogador
 * @author Vicente Pinto Tomás Junior & Vitor Mugnol Estevam de Araujo
 * @since 2019
 */

public class Jogadores
{
	/**
	 * Checa a tabela Jogador utilizando o código passado com o 
	 * objetivo de descobrir se o jogador procurado está cadastrado
	 * @param codigo Código do jogador que se quer buscar
	 * @return caso encontre o jogador com esse codigo retorna true,caso contrário retorna falso
	 * @throws Exception Caso algum erro ocorra na busca do jogador no banco de dados
	 */
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM JOGADOR " +
                  "WHERE CODJOGADOR = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); 


        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar jogador");
        }

        return retorno;
    }
/**
 * Adiciona o jogador passado como parâmetro para a tabela Jogador
 * @param jogador O Jogador que se deseja adicionar
 * @throws Exception Caso o jogador seja nulo 
 */
    public static void incluir (Jogador jogador) throws Exception
    {
        if (jogador==null)
            throw new Exception ("Jogador nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO JOGADOR " +
                  "(NOMEJOGADOR,SENHAJOGADOR,PONTUACAOMAXIMA) " +
                  "VALUES " +
                  "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);


            BDSQLServer.COMANDO.setString (1, jogador.getNome ());
            BDSQLServer.COMANDO.setString (2, jogador.getSenha ());
            BDSQLServer.COMANDO.setInt    (3, jogador.getPontuacao());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao inserir jogador");
        }
    }
/**
 * Exclui o jogador cujo código é igual ao passado como parâmetro
 * @param codigo Código do jogador que se deseja excluir
 * @throws Exception Caso o codigo passado não seja de um jogador cadastrado
 */
    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM JOGADOR " +
                  "WHERE CODJOGADOR = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
        	erro.printStackTrace();
        }
    }
/**
 * Altera um jogador na tabela utilizando as informações
 * do jogador passado como parâmetro como fonte
 * @param jogador Os novos dados do Jogador que se deseja alterar
 * @throws Exception caso o jogador seja nulo ou não esteja cadastrado
 */
    public static void alterar (Jogador jogador) throws Exception
    {
        if (jogador==null)
            throw new Exception ("Jogador nao fornecido");

        if (!cadastrado (jogador.getCodigo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE JOGADOR " +
                  "SET NOMEJOGADOR=? " +
                  ", SENHAJOGADOR=? " +
                  ", PONTUACAOMAXIMA=? " +
                  "WHERE CODJOGADOR = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, jogador.getNome ());
            BDSQLServer.COMANDO.setString (2, jogador.getSenha ());
            BDSQLServer.COMANDO.setInt    (3, jogador.getPontuacao());
            BDSQLServer.COMANDO.setInt    (4, jogador.getCodigo ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao atualizar dados de jogador");
            
        }
    }
/**
 * Retorna o jogador cujo código seja igual ao passado como parâmetro
 * @param codigo O código do jogador que se deseja 
 * @return O jogador contendo todas as informações dele na tabela
 * @throws Exception Caso o jogador não esteja cadastrado
 */
    public static Jogador getJogador (int codigo) throws Exception
    {
        Jogador jogador = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM JOGADOR " +
                  "WHERE CODJOGADOR = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            jogador = new Jogador(resultado.getInt("CODJOGADOR"),
                               resultado.getString("NOMEJOGADOR"),
                               resultado.getString ("SENHAJOGADOR"),
                               resultado.getInt("PONTUACAOMAXIMA"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar jogador");
        }

        return jogador;
    }

/**
 * Gera uma lista contendo todos os jogadores da tabela Jogador    
 * @return Retorna um List<> contendo todos os jogadores da tabela
 * @throws Exception Caso algum erro ocorra ao selecionar os jogadores
 */
    public static List<Jogador> getJogadores() throws Exception
    {
    	List<Jogador> jogadores = null;
    	try {
    		jogadores = new ArrayList<Jogador>();
    		String sql;
    		sql = "SELECT * " +
    			  "FROM JOGADOR";
    		BDSQLServer.COMANDO.prepareStatement(sql);
    		
    		MeuResultSet resultado =(MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
    		
    		while(resultado.next())
    		{
    			Jogador jogador = new Jogador(resultado.getInt("CODJOGADOR"),
                        resultado.getString("NOMEJOGADOR"),
                        resultado.getString ("SENHAJOGADOR"),
                        resultado.getInt("PONTUACAOMAXIMA"));
    			
    			jogadores.add(jogador);
    		}

    	}
    	catch(Exception erro)
    	{
    		throw erro;
    	}
    	
    	return jogadores;
    }
    /**
     * Procura por um jogador com o nome especificado
     * na tabela
     * @param nome que deseja ser procurado
     * @return true se o nome foi encontrado falso caso contrario
     * @throws Exception caso algum erro ocorra na busca pelo nome
     */
    public static boolean nomeJaEscolhido(String nome)throws Exception
    {
    	boolean ret = false;
    	try {
    		String sql;

            sql = "SELECT * " +
                  "FROM JOGADOR " +
                  "WHERE NOMEJOGADOR = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, nome);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
            
            ret = resultado.first();
    	}
    	catch(Exception erro)
    	{
    		throw new Exception ("Erro ao buscar nome de jogador");
    	}
    	return ret;
    }
    
}