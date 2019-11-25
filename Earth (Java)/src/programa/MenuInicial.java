package programa;

import java.awt.EventQueue;

import javax.swing.JFrame;

import janelas.MenuManutencao;
import janelas.MenuRanking;

import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuInicial {

	private JFrame frmAplicaoDeJogadores; 
	private static MenuRanking rank = null;
	private static MenuManutencao manut =null;
	private Font fonte = new Font("Times New Roman", Font.PLAIN, 46);//Intancia a fonte a ser usada 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rank =new MenuRanking();// instancia um menu de ranking
					 manut =new MenuManutencao();//instancia um menu de manutenção
					MenuInicial window = new MenuInicial();
					window.frmAplicaoDeJogadores.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAplicaoDeJogadores = new JFrame();
		frmAplicaoDeJogadores.setTitle("Jogadores");
		frmAplicaoDeJogadores.setBounds(100, 100, 450, 300);
		frmAplicaoDeJogadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.menu);
		frmAplicaoDeJogadores.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnManutencao = new JButton("Manuten\u00E7\u00E3o");
		/**
		 * Abre o formulario de manutencao
		 */
		btnManutencao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manut.setVisible(true);
			}
		});
		btnManutencao.setFont(fonte);
		btnManutencao.setBounds(0, 0, 434, 139);
		desktopPane.add(btnManutencao);
		
		JButton btnRanking = new JButton("Ranking");
		
		/**
		 * Abre o formulario de ranks
		 */
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rank.setVisible(true);
			}
		});
		btnRanking.setFont(fonte);
		btnRanking.setBounds(0, 136, 434, 126);
		desktopPane.add(btnRanking);
	}
}
