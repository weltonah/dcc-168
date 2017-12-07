package telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.capivara.Ast;
import br.com.capivara.Colorir;
import br.com.capivara.Leitura;
import br.com.grafo.Grafo;
import br.com.image.CriarImagem;

public class Home {

	private JFrame frame;
	private File fileJava;
	private File fileTeste;
	private File fileXml;
	private Grafo gra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Botão para selecionar Classe .Java que vai ser utilizada para teste
		JButton btnNewButton = new JButton("Classe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File(""));
				fs.setDialogTitle("Abrir Classe");
				fs.setAcceptAllFileFilterUsed(false);
				fs.addChoosableFileFilter(new FileNameExtensionFilter("Java class File", "java"));
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					//captura arquivo selecionado pelo usuario
					fileJava  = fs.getSelectedFile();
					// Cria Grafo de fluxo de controle atravez de AST
					Leitura lei = new Leitura();
					gra = lei.leituraArvore(fileJava.getPath());
				}
			}
		});
		btnNewButton.setBounds(12, 28, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		//Botão responsavel por escolher arquivo .XML
		JButton btnSelecionarXml = new JButton("Xml");
		btnSelecionarXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs3 = new JFileChooser(new File(""));
				fs3.setDialogTitle("Abrir Classe");
				fs3.setAcceptAllFileFilterUsed(false);
				fs3.addChoosableFileFilter(new FileNameExtensionFilter("XML File", "xml"));
				int result3 = fs3.showSaveDialog(null);
				if(result3 == JFileChooser.APPROVE_OPTION) {
					//captura arquivo selecionado pelo usuario
					fileXml  = fs3.getSelectedFile();
					//Colore estrutura do Grafo
					Colorir color = new Colorir();
					Leitura lei = new Leitura();
					color.ColorirGrafo(lei.leituraXML(fileXml.getPath()), gra, fileJava.getName());
				}
			}
		});
		btnSelecionarXml.setBounds(161, 28, 117, 25);
		frame.getContentPane().add(btnSelecionarXml);
		
		//Botão responsavel pela vizualização do Grafo sem estar colorido
		JButton btnNewButton_2 = new JButton("Vizualizar Grafo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cria tela com a imagem do grafo
				JFrame frame = new CriarImagem(gra,false);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setSize(1400, 700);
				frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(161, 162, 213, 25);
		frame.getContentPane().add(btnNewButton_2);
		//Botão responsavel pela vizualização do Grafo colorido
		JButton btnNewButton_3 = new JButton("Vizualizar Grafo Coberto");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crita Tela com imagem do grafo colorido
				JFrame frame = new CriarImagem(gra,true);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setSize(1400, 700);
				frame.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(161, 213, 213, 25);
		frame.getContentPane().add(btnNewButton_3);
	}
}
