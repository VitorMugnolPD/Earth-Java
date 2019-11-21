package janelas;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
;
/**
 * Esta classe tem como fun��o auxiliar na cria��o de uma tabela Jtable
 * possuindo metodos para criar um novo modelo de tabela baseado nas linhas e colunas definidas
 * @author Vicente Pinto Tom�s Junior & Vitor Mugnol de Estevam de Araujo
 * @since 2019
 */


public class ModeloTabela extends AbstractTableModel {
	/**
	 * variaveis contendo as informacoes da tabela
	 */
	private ArrayList linhas = null;
	private String[] colunas = null;
	
	/**
	 * Instancia um novo modelo de tabela a ser utilizado
	 * @param lin Informa��es necess�rias a cria��o das linhas
	 * @param col Informa��es necess�rias a cria��o das colunas
	 */
	public ModeloTabela(ArrayList lin,String[] col)throws Exception
	{
		setLinhas(lin);
		setColunas(col);
	}
	
	
	/**
	 * Retorna um array contendo as linhas do modelo
	 * @return ArrayList com todas as informa��es das linhas
	 */
	public ArrayList getLinhas() {
		return linhas;
	}
	/**
	 * Define as linhas do modelo de tabela
	 * @param linhas ArrayList contendo as linhas da tabela
	 * @throws Exception se o ArrayList de linhas vier nulo
	 */
	private void setLinhas(ArrayList linhas) throws Exception
	{
		if(linhas == null)
			throw new Exception("Informa��es de linha n�o passadas");
		
		this.linhas = (ArrayList)linhas.clone();
	}
	
	/**
	 * Define as colunas do modelo de tabela
	 * @param colunas vetor de String contendo o cabe�alho das colunas da tabela
	 * @throws Exception se o vetor de String vier nulo ou se alguma coluna vier vazia
	 */
	private void setColunas(String[] colunas)throws Exception
	{
		if(colunas == null)
			throw new Exception("Informa��es de coluna n�o passadas");
		for(int i=0; i< colunas.length; i++)
			if(colunas[i].trim().equals(""))
				throw new Exception("Coluna vazia");
				
		this.colunas = colunas.clone();
	}
	
	/**
	 * Retorna o n�mero de colunas
	 */
	public int getColumnCount() {
		return colunas.length;
	}
	
	/**
	 * Retorna o n�mero de linhas
	 */
	public int getRowCount() {
		return linhas.size();
	}
	
	
/**
 * Retorna o nome da coluna na posi��o passada
 */
	public String getColumnName(int numCol)
	{
		try {
		return colunas[numCol];
		}
		catch(Exception erro)
		{
		 throw erro;
		}
	}
	
	/**
	 * Retorna o valor da linha especificada
	 */
	public Object getValueAt(int numLin, int numCol)
	{

		try{
			Object[] linha = (Object[])getLinhas().get(numLin);
		return linha[numCol];
		}
		catch(Exception erro)
		{
			throw erro;
		}
	}
	
	/**
	 * Retorna as linhas e colunas de uma tabela
	 */
	public String toString() {
		String ret = "Colunas: ";
		for(int i = 0; i<colunas.length;i++)
			ret = ret+colunas[i] +", ";
		ret = ret +" Linhas: ";
		
		for(int i =0; i<linhas.size(); i++)
			ret = ret+linhas.get(i) + ", ";
		return ret;
			
	}
	/**
	 * Checa se uma tabela � igual a outra 
	 */
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(this.getClass() != obj.getClass())
			return false;
		
		ModeloTabela modTab = (ModeloTabela) obj;
		
		if(modTab.colunas.length != this.colunas.length 
				|| this.linhas.size() != modTab.linhas.size())
			return false;
		
		for(int i = 0; i<colunas.length;i++)
			if(!this.colunas[i].equals(modTab.colunas[i]))
				return false;
		for(int i = 0; i<linhas.size();i++)
			if(!this.linhas.get(i).equals(modTab.linhas.get(i)))
				return false;
		
		return true;
	}
	/**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de um modelo de tabela.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) do modelo de tabela representado pela inst�ncia � qual o m�todo for aplicado.
    *@return o c�digo de espalhamento do modelo chamante do m�todo.
    */
	public int hashCode()
	{
		int ret= 666;
		for(int i= 0; i< linhas.size(); i++)
			ret =ret*5 + linhas.get(i).hashCode();
		for(int i = 0; i<colunas.length;i++)	
		   ret = ret*5 + colunas[i].hashCode();
		
		if(ret<0)
			ret =-ret;
		return ret;
	}

}
