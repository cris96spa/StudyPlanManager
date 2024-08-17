package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Mainframe dell'applicazione.
 * Da qui il segretario può effettuare le operazioni di gestione dei piani di studio
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class MainFrame extends JFrame{
	
	private GUIController controller;
	
	public MainFrame(String name){
		super(name);
		
		controller = GUIController.getInstance();

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 250));
		this.setMaximumSize(new Dimension(400, 250));
		this.setPreferredSize(new Dimension(400, 250));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		
		JPanel labelPanel = new JPanel();
		JLabel label = new JLabel("Gestione Piani Di Studio");
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setFont(new Font("Arial",Font.ITALIC,18));
		
		labelPanel.add(label);
		labelPanel.setVisible(true);
		
		JPanel pannelloPiani = new JPanel();
		pannelloPiani.setMinimumSize(new Dimension(300, 163));
		pannelloPiani.setPreferredSize(new Dimension(300, 163));
		pannelloPiani.setMaximumSize(new Dimension(300, 163));
		pannelloPiani.setLayout(new BoxLayout(pannelloPiani, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		pannelloPiani.setBorder(BorderFactory.createTitledBorder(border, "Operazioni disponibili"));
		
		JButton presentaPiano = new JButton("Presenta piano di studio");
		presentaPiano.setMinimumSize(new Dimension(220, 45));
		presentaPiano.setPreferredSize(new Dimension(220, 45));
		presentaPiano.setMaximumSize(new Dimension(220, 45));
		presentaPiano.setAlignmentX(CENTER_ALIGNMENT);
		
		presentaPiano.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickPresentaPiano();
			}
		});
		
		JButton visualizzaPiano = new JButton("Visualizza Piano");
		visualizzaPiano.setMinimumSize(new Dimension(220, 45));
		visualizzaPiano.setPreferredSize(new Dimension(220, 45));
		visualizzaPiano.setMaximumSize(new Dimension(220, 45));
		visualizzaPiano.setAlignmentX(CENTER_ALIGNMENT);
		
		visualizzaPiano.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickVisualizzaPiano();
			}	
		});
		
		JButton visualizzaPianoDaValidare = new JButton("Visualizza piano da validare");
		visualizzaPianoDaValidare.setMinimumSize(new Dimension(220, 45));
		visualizzaPianoDaValidare.setPreferredSize(new Dimension(220, 45));
		visualizzaPianoDaValidare.setMaximumSize(new Dimension(220, 45));
		visualizzaPianoDaValidare.setAlignmentX(CENTER_ALIGNMENT);
		
		visualizzaPianoDaValidare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickVisualizzaPianiDaApprovare();
			}	
		});
		
		pannelloPiani.add(presentaPiano);
		pannelloPiani.add(visualizzaPiano);
		pannelloPiani.add(visualizzaPianoDaValidare);

		pannelloPiani.setVisible(true);
		
		this.add(labelPanel);
		this.add(pannelloPiani);
		
		this.pack();
		this.setVisible(true); 
	}
	
	/**
	 * Metodo invocato per presentare un nuovo piano di studio
	 */
	public void clickPresentaPiano() {
		controller.submitStudyPlan();
	}

	/**
	 * Metodo invocato per la visualizzazione di un piano di studio
	 */
	public void clickVisualizzaPiano() {
		controller.showStudyPlan();
	}

	/**
	 * Metodo invocato per visualizzare i piani di studio in attesa di un' approvazione manuale
	 */
	public void clickVisualizzaPianiDaApprovare() {
		controller.showOnApprovationStudyPlans();
	}
	
	public static void main(String[] args) {
		JFrame mainFrame=new MainFrame("Gestione dei piani di studio");
		
	}
}

