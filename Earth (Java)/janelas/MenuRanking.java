package janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.util.List;
import javax.swing.JOptionPane;
import bd.daos.*;
import bd.dbos.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MenuRanking extends JFrame {

	private JPanel contentPane;
	private static JTable tabelaRanking;
	/**
	 * Instancia da classe jogadores para acessar a tabela
	 */
	private static Jogadores jogadores = null;
	
	/**
	 * Lista que armazenará jogadores
	 */
	private static List<Jogador> jogador = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MenuRanking frame = new MenuRanking();
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/**
 * Método responsável por preencher tabela
 * Criando um novo modelo de tabela 
 * e criando uma nova tabela com ele
 */
	private static void preencherTabela() {
		
		String [] colunas = {"Nome","Pontuação Máxima"};//Colunas da tabela
		try {
			ArrayList dados = new ArrayList();//ArrayList que armazenará os jogadores
			jogadores = new Jogadores();//instancia o Jogadores
			jogador = jogadores.getJogadores();//A lista de jogadores recebe os jogadores da tabela
			Collections.sort(jogador);//Organizamos a lista por pontuação
			for(int i= 0; i< jogador.size();i++)
			  dados.add(new Object[] {jogador.get(i).getNome(),jogador.get(i).getPontuacao()});//A ArrayList recebe as informações de cada jogador
			
			ModeloTabela modTab = new ModeloTabela(dados,colunas);//Criamos o modelo

			tabelaRanking.setModel(modTab);//Criamos a tabela
			tabelaRanking.getColumnModel().getColumn(0).setPreferredWidth(210);//Configuramos o tamanho da coluna
			tabelaRanking.getColumnModel().getColumn(0).setResizable(false);//Tamanho não pode ser redefinido
			tabelaRanking.getColumnModel().getColumn(1).setPreferredWidth(211);//Configuramos o tamanho da coluna
			tabelaRanking.getColumnModel().getColumn(1).setResizable(false);//Tamanho não pode ser redefinido
			tabelaRanking.getTableHeader().setReorderingAllowed(false);//Cabecalho nao pode ser reordenado
			
		}
		catch(Exception erro)
		{
			
		}
		
		
	}
	
	/**
	 * Create the frame.
	 */
	public MenuRanking() {
		setTitle("Display de rankings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tabelaRanking = new JTable();
		scrollPane.setViewportView(tabelaRanking);
		tabelaRanking.setToolTipText("");
		tabelaRanking.setForeground(Color.BLACK);
		preencherTabela();
	}
}
