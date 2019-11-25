package janelas;
import bd.daos.*;
import bd.dbos.*;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
/**
 * Menu de manutencao de jogadores,permitindo o cadastro,alteracao,exclusao e exibicao de jogadores
 * Cadastro feito com senha,alteracao da senha requer a senha anterior,
 * pontuacao e exibida mas nao pode ser alterada
 * @author Vicente Pinto Tomás Junior & Vitor Mugnol Estevam de Araujo
 * @since 2019
 */
public class MenuManutencao extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtPontuacao;
	private JPasswordField senhaJogador;
	private static Jogador jogador = null;
	private static Jogadores jogadores = null;
	private JPasswordField novaSenha;
	private String confirmarSenha;
	private static JTable tabelaJogadores;
	private static List<Jogador> Listarjogadores = null;
	private boolean excluir;

	
	private enum Situacao{
		adicionando,alterando,navegando
	}
	
	
	private Situacao situacaoAtual = Situacao.navegando;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuManutencao frame = new MenuManutencao();
					jogadores = new Jogadores();//Instancia jogadores
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	private static void preencherTabela() {
		ArrayList dados = new ArrayList();//ArrayList que armazenará os jogadores
		String [] colunas = {"Código","Nome","Pontuação Máxima"};
		try {
			
			Listarjogadores = jogadores.getJogadores();//A lista de jogadores recebe os jogadores da tabela
			for(int i= 0; i< Listarjogadores.size();i++)
			  dados.add(new Object[] {Listarjogadores.get(i).getCodigo(),Listarjogadores.get(i).getNome(),
					  Listarjogadores.get(i).getPontuacao()});//A ArrayList recebe as informações de cada jogador
			
			ModeloTabela modTab = new ModeloTabela(dados,colunas);//Criamos o modelo

			tabelaJogadores.setModel(modTab);//Criamos a tabela
			tabelaJogadores.getColumnModel().getColumn(0).setPreferredWidth(120);//Configuramos o tamanho da coluna
			tabelaJogadores.getColumnModel().getColumn(0).setResizable(false);//Tamanho não pode ser redefinido
			tabelaJogadores.getColumnModel().getColumn(1).setPreferredWidth(120);//Configuramos o tamanho da coluna
			tabelaJogadores.getColumnModel().getColumn(1).setResizable(false);//Tamanho não pode ser redefinido
			tabelaJogadores.getColumnModel().getColumn(2).setPreferredWidth(120);//Configuramos o tamanho da coluna
			tabelaJogadores.getColumnModel().getColumn(2).setResizable(false);//Tamanho não pode ser redefinido
			tabelaJogadores.getTableHeader().setReorderingAllowed(false);//Cabecalho nao pode ser reordenado
			
		}
		catch(Exception erro)
		{
			
		}
		
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public MenuManutencao() {
		setTitle("Menu de manuten\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 483, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JDesktopPane desktopPane = new JDesktopPane();
		tabbedPane.addTab("Manutenção", null, desktopPane, null);
		desktopPane.setBackground(SystemColor.menu);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(72, 28, 46, 14);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(72, 69, 46, 14);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setBounds(72, 114, 46, 14);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Pontua\u00E7\u00E3o M\u00E1xima:");
		lblNewLabel_3.setBounds(72, 160, 107, 14);
		desktopPane.add(lblNewLabel_3);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(128, 25, 86, 20);
		desktopPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(128, 66, 86, 20);
		desktopPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtPontuacao = new JTextField();
		txtPontuacao.setText("0");
		txtPontuacao.setEnabled(false);
		txtPontuacao.setBounds(176, 157, 86, 20);
		desktopPane.add(txtPontuacao);
		txtPontuacao.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		/**
		 * Cadastra o jogador
		 */
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					excluir = false;
					
					if(senhaJogador.getText().trim().equals("") || txtNome.getText().trim().equals(""))//se algum campo está vazio
					{
						JOptionPane.showMessageDialog(null,"Digite a senha e o nome!","Alerta",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						if(jogadores.nomeJaEscolhido(txtNome.getText().trim()))//se o nome já foi escolhido
						{
							JOptionPane.showMessageDialog(null,"Nome ja escolhido!","Alerta",JOptionPane.INFORMATION_MESSAGE);
						}
						else {
					if(situacaoAtual == Situacao.navegando)//checar se não está em outra operação
					{
						situacaoAtual = Situacao.adicionando;
						confirmarSenha = senhaJogador.getText();//armazaena a senha temporariamente
						senhaJogador.setText("");//apaga a senha escrita
						JOptionPane.showMessageDialog(null,"Digite a senha novamente para confirmar","Importante",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
					if(situacaoAtual == Situacao.adicionando && 
							confirmarSenha.equals(senhaJogador.getText())) //checa se está adicionando e se a senha digitada novamente corresponde a anterior
					{
										
					jogador = new Jogador(666,txtNome.getText(),senhaJogador.getText(),0);
					jogadores.incluir(jogador);
					//cadastro feito
					situacaoAtual = Situacao.navegando;//nenhuma operacao
					  }
					 }
					}
				  }
				}
				catch(Exception erro)
				{
					erro.printStackTrace();
					JOptionPane.showMessageDialog(null,"Algum campo não estava preenchido","Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCadastrar.setBounds(10, 209, 90, 32);
		desktopPane.add(btnCadastrar);
		
		JButton btnExcluir = new JButton("Excluir");
		
		/**
		 * Exclui o jogador
		 */
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(situacaoAtual == Situacao.navegando)//nenhuma operacao
					{
						excluir = true;
						JOptionPane.showMessageDialog(null,"Tem certeza que deseja excluir? Se sim pressione novamente o botão","Alerta",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						if(excluir)//se excluir ja foi pressionado
					{
					jogadores.excluir(Integer.parseInt(txtCodigo.getText()));
					txtCodigo.setText("");
					txtNome.setText("");
					senhaJogador.setText("");
					txtPontuacao.setText("0");
					excluir = false;
					}
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Jogador não foi encontrado","Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnExcluir.setBounds(110, 209, 90, 32);
		desktopPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		/**
		 * Altera o jogador
		 */
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					excluir = false;
					if(situacaoAtual == Situacao.navegando) //nenhuma operacao
					{
						situacaoAtual = Situacao.alterando;
						JOptionPane.showMessageDialog(null,"Digite novamente a nova senha","Importante",JOptionPane.INFORMATION_MESSAGE);
						confirmarSenha = novaSenha.getText();
						novaSenha.setText("");
						senhaJogador.setText("");
					}
					
				else 
					{
					if(situacaoAtual == Situacao.alterando) {
					jogador = jogadores.getJogador(Integer.parseInt(txtCodigo.getText()));
					situacaoAtual = Situacao.navegando;
						if(jogador.getSenha().equals(senhaJogador.getText()) &&
								confirmarSenha.equals(novaSenha.getText()))//se a senha foi digitada corretamente e a nova senha foi digitada corretamente 2 vezes
						{
						
						jogador = new Jogador(Integer.parseInt(txtCodigo.getText()),
								txtNome.getText(),novaSenha.getText(),
								Integer.parseInt(txtPontuacao.getText()));
						jogadores.alterar(jogador);//altera
						//deixa tudo vazio
						txtCodigo.setText("");
						txtNome.setText("");
						senhaJogador.setText("");
						txtPontuacao.setText("0");
						novaSenha.setText("");
						}
					else
						JOptionPane.showMessageDialog(null,"Erro nas senhas!","Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
			  }
			}
				catch(Exception erro) {
					JOptionPane.showMessageDialog(null,"Erro ao alterar jogador,tente alterar algum campo","Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAlterar.setBounds(210, 209, 90, 32);
		desktopPane.add(btnAlterar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(310, 209, 90, 32);
		desktopPane.add(btnProcurar);
		/**
		 * Procura o jogador
		 */
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					excluir = false;
					
					jogador = jogadores.getJogador(Integer.parseInt(txtCodigo.getText()));//procura pelo codigo
					//escreve o nome e o codigo de jogador
					txtNome.setText(jogador.getNome());
					txtPontuacao.setText(Integer.toString(jogador.getPontuacao()));
					
					
				}
				catch(Exception erro) {
					txtCodigo.setText("");
					JOptionPane.showMessageDialog(null,"Jogador não pode ser encontrado!","Alerta",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		senhaJogador = new JPasswordField();
		senhaJogador.setBounds(128, 111, 86, 20);
		desktopPane.add(senhaJogador);
		
		novaSenha = new JPasswordField();
		novaSenha.setBounds(328, 111, 86, 20);
		desktopPane.add(novaSenha);
		
		JLabel lblNewLabel_4 = new JLabel("Nova senha:");
		lblNewLabel_4.setBounds(254, 114, 70, 14);
		desktopPane.add(lblNewLabel_4);
		
		JButton btnExibir = new JButton("Exibir");
		btnExibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir = false;
				preencherTabela();
			}
		});
		btnExibir.setBounds(293, 28, 107, 44);
		desktopPane.add(btnExibir);
		


		JScrollPane scrollPane = new JScrollPane();
		JPanel PainelJogadores = new JPanel();
		PainelJogadores.setBackground(SystemColor.menu);
		tabelaJogadores = new JTable();
		tabelaJogadores.setBounds(394, 258, -362, -247);
		scrollPane.setViewportView(tabelaJogadores);
		PainelJogadores.add(scrollPane);
		preencherTabela();
		
		tabbedPane.addTab("Exibição", PainelJogadores);
		
		
	}
}
