package bd.dbos;


/**
A classe Jogador � feita para armazenar e gerenciar jogadores da tabela Jogador,ela � implemetada
com 4 vari�veis feitas para armazenar cada campo da tabela do banco de dados
ela possui m�todos para guardar e retornar os valores de cada campo da tabela.
@author Vicente Pinto Tom�s Junior & Vitor Mugnol Estevam de Araujo.
@since 2019.
*/

public class Jogador implements Cloneable,Comparable<Jogador>
{
	
	
    private int  pontuacao,codigo;
    private String nome,senhaJogador;
    
   /**
    * Valida o valor do c�digo do jogador e o atribui a vari�vel
    * Verifica se o c�digo  � v�lido lan�ando exce��o caso contr�rio
    * @param codigo O codigo a ser verificado e atribu�do
    * @throws Exception caso o c�digo do jogador n�o seja v�lido
    */
    
    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = codigo;
    }   

    /**
     * Valida o nome passado e o atribui a vari�vel
     * Verifica se o nome � nulo ou vazio lan�ando exce��o nesse caso
     * @param nome O nome a ser verificado e atribu�do
     * @throws Exception caso o nome seja vazio ou nulo
     */
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }
 /**
  * Valida a pontua��o passada e a atribui
  * Verifica se a pontua��o est� no intervalo aceito
  * @param pont A pontua��o m�xima do jogador,a ser atribu�da
  * @throws Exception caso a pontua��o esteja em um intervalo n�o aceito
  */
    public void setPontuacao (int pont) throws Exception
    {
        if (pont > 999999999)
            throw new Exception ("Pontuacao invalido");

        this.pontuacao = pont;
    }
    
    /**
     * Valida a senha passada e a atrbui
     * Verifica se a senha � vazia ou nula
     * @param senha Senha do jogador atual
     * @throws Exception Caso a senha seja nula ou vazia!
     */
    
    public void setSenha(String senha) throws Exception
    {
    	if(senha == null || senha.equals(""))
    		throw new Exception ("Senha n�o fornecido");
    	this.senhaJogador = senha;
    }
/**
 * Retorna o c�digo do jogador chamante do m�todo
 * @return c�digo do jogador
 */
    public int getCodigo ()
    {
        return this.codigo;
    }
/**
 * Retorna o nome do jogador o qual chama o m�todo
 * @return O nome do jogador
 */
    public String getNome ()
    {
        return this.nome;
    }
/**
 * Retorna a pontua��o m�xima do jogador chamador
 * @return Pontua��o do jogador
 */
    public int getPontuacao ()
    {
        return this.pontuacao;
    }
    /**
     * Retorna a senha do jogador chamante do m�todo
     * @return senha do jogador
     */
    public String getSenha()
    {
    	return this.senhaJogador;
    }

    /**
     * Instancia um novo jogador,os valores armazenados ser�o usados em opera��es relacionadas
     * a inser��o,dele��o,altera��o entre outras no banco de dados
     * @param codigo O c�digo do jogador armazenado
     * @param nome O nome do jogador
     * @param senha A senha do jogador
     * @param pont Pontua��o m�xima do jogador
     * @throws Exception Caso algum dos par�metros n�o seja v�lido lan�a exce��o
     */
    public Jogador (int codigo, String nome, String senha,int pont) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
        this.setPontuacao (pont);
        this.setSenha(senha);
    }
    /**
     * Retorna os dados do jogador de maneira organizada na ordem:Codigo,Nome,Senha,Pontua��o 
     * (A senha est� criptogradafada para n�o ser facilmente roubada)
     * @return um String contendo todas as informa��es de um jogador
     */
    public String toString ()
    {
        String ret="";

        ret+="Codigo: "+this.codigo+"\n";
        ret+="Nome..: "+this.nome  +"\n";
        ret+="Pontuacao.: "+this.pontuacao +"\n";

        return ret;
    }
    /**Verifica a igualdade entre dois jogadores.
    Verifica se o Object fornecido como par�metro representa um
    jogador igual �quele representade pela inst�ncia � qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
            for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem jogadores iguais, ou false, caso contr�rio.
      */
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

      Jogador jog =(Jogador) obj;

        if (this.codigo!=jog.codigo)
            return false;

        if (this.nome.equals(jog.nome))
            return false;

       if (this.senhaJogador!=jog.senhaJogador)
            return false;
       
       if(this.pontuacao != jog.pontuacao)
    	   return false;

        return true;
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de um jogador.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) do jogador representado pela inst�ncia � qual o m�todo for aplicado.
    *@return o c�digo de espalhamento do jogador chamante do m�todo.
    */
    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.codigo).hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.senhaJogador.hashCode();
        ret = 7*ret + new Integer(this.pontuacao).hashCode();

        return ret;
    }

/**
 * Constr�i uma nova instancia da classe Jogador.
 * Para esse prop�sito,deve ser fornecida uma instancia que sera usada como modelo
 * @param modelo A instancia que servira de modelo
 * @throws Exception caso o modelo seja nulo
 */
    public Jogador (Jogador modelo) throws Exception
    {
    	if(modelo == null)
    		throw new Exception("Modelo nulo!");
        this.codigo = modelo.codigo; // nao clono, pq nao eh objeto
        this.nome   = modelo.nome;   // nao clono, pq nao eh clonavel
        this.senhaJogador  = modelo.senhaJogador;  // nao clono, pq nao eh objeto
        this.pontuacao = modelo.pontuacao;
    }
    /**
    Produz uma c�pia fiel de um jogador.
    Produz e resulta em uma c�pia exata do jogador o qual o m�todo for chamado.
    @return a c�pia do jogador chamante do m�todo.
    */
    public Object clone ()
    {
        Jogador ret=null;

        try
        {
            ret = new Jogador (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca � null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
    
    
    /**
     * Compara dois jogadores por meio de suas pontua��es
     */
    public int compareTo(Jogador j) {
    	return new Integer (j.getPontuacao()).compareTo(this.getPontuacao());
    }
    
}