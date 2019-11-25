package bd.dbos;


/**
A classe Jogador é feita para armazenar e gerenciar jogadores da tabela Jogador,ela é implemetada
com 4 variáveis feitas para armazenar cada campo da tabela do banco de dados
ela possui métodos para guardar e retornar os valores de cada campo da tabela.
@author Vicente Pinto Tomás Junior & Vitor Mugnol Estevam de Araujo.
@since 2019.
*/

public class Jogador implements Cloneable,Comparable<Jogador>
{
	
	
    private int  pontuacao,codigo;
    private String nome,senhaJogador;
    
   /**
    * Valida o valor do código do jogador e o atribui a variável
    * Verifica se o código  é válido lançando exceção caso contrário
    * @param codigo O codigo a ser verificado e atribuído
    * @throws Exception caso o código do jogador não seja válido
    */
    
    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = codigo;
    }   

    /**
     * Valida o nome passado e o atribui a variável
     * Verifica se o nome é nulo ou vazio lançando exceção nesse caso
     * @param nome O nome a ser verificado e atribuído
     * @throws Exception caso o nome seja vazio ou nulo
     */
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }
 /**
  * Valida a pontuação passada e a atribui
  * Verifica se a pontuação está no intervalo aceito
  * @param pont A pontuação máxima do jogador,a ser atribuída
  * @throws Exception caso a pontuação esteja em um intervalo não aceito
  */
    public void setPontuacao (int pont) throws Exception
    {
        if (pont > 999999999)
            throw new Exception ("Pontuacao invalido");

        this.pontuacao = pont;
    }
    
    /**
     * Valida a senha passada e a atrbui
     * Verifica se a senha é vazia ou nula
     * @param senha Senha do jogador atual
     * @throws Exception Caso a senha seja nula ou vazia!
     */
    
    public void setSenha(String senha) throws Exception
    {
    	if(senha == null || senha.equals(""))
    		throw new Exception ("Senha não fornecido");
    	this.senhaJogador = senha;
    }
/**
 * Retorna o código do jogador chamante do método
 * @return código do jogador
 */
    public int getCodigo ()
    {
        return this.codigo;
    }
/**
 * Retorna o nome do jogador o qual chama o método
 * @return O nome do jogador
 */
    public String getNome ()
    {
        return this.nome;
    }
/**
 * Retorna a pontuação máxima do jogador chamador
 * @return Pontuação do jogador
 */
    public int getPontuacao ()
    {
        return this.pontuacao;
    }
    /**
     * Retorna a senha do jogador chamante do método
     * @return senha do jogador
     */
    public String getSenha()
    {
    	return this.senhaJogador;
    }

    /**
     * Instancia um novo jogador,os valores armazenados serão usados em operações relacionadas
     * a inserção,deleção,alteração entre outras no banco de dados
     * @param codigo O código do jogador armazenado
     * @param nome O nome do jogador
     * @param senha A senha do jogador
     * @param pont Pontuação máxima do jogador
     * @throws Exception Caso algum dos parâmetros não seja válido lança exceção
     */
    public Jogador (int codigo, String nome, String senha,int pont) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
        this.setPontuacao (pont);
        this.setSenha(senha);
    }
    /**
     * Retorna os dados do jogador de maneira organizada na ordem:Codigo,Nome,Senha,Pontuação 
     * (A senha está criptogradafada para não ser facilmente roubada)
     * @return um String contendo todas as informações de um jogador
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
    Verifica se o Object fornecido como parâmetro representa um
    jogador igual àquele representade pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem jogadores iguais, ou false, caso contrário.
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
    Calcula o código de espalhamento (ou código de hash) de um jogador.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) do jogador representado pela instância à qual o método for aplicado.
    *@return o código de espalhamento do jogador chamante do método.
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
 * Constrói uma nova instancia da classe Jogador.
 * Para esse propósito,deve ser fornecida uma instancia que sera usada como modelo
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
    Produz uma cópia fiel de um jogador.
    Produz e resulta em uma cópia exata do jogador o qual o método for chamado.
    @return a cópia do jogador chamante do método.
    */
    public Object clone ()
    {
        Jogador ret=null;

        try
        {
            ret = new Jogador (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca é null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
    
    
    /**
     * Compara dois jogadores por meio de suas pontuações
     */
    public int compareTo(Jogador j) {
    	return new Integer (j.getPontuacao()).compareTo(this.getPontuacao());
    }
    
}