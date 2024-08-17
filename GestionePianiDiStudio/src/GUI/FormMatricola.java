package GUI;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe astratta dei FormMatricola, dovrà essere estesa per aggiungere il comportamento
 * del metodo confirm()
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public abstract class FormMatricola extends JDialog{
	
	protected GUIController controller;
	
	public FormMatricola(){
		controller=GUIController.getInstance();
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(250, 150));
		this.setMaximumSize(new Dimension(250, 150));
		this.setPreferredSize(new Dimension(250, 150));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JLabel messageLabel = new JLabel("Inserisci matricola studente");
		messageLabel.setFont(new Font("Arial",Font.BOLD,11));
		messageLabel.setMinimumSize(new Dimension(200, 30));
		messageLabel.setMaximumSize(new Dimension(200, 30));
		messageLabel.setPreferredSize(new Dimension(200, 30));
		messageLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextField text= new JTextField();
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setFont(new Font("Arial",Font.PLAIN,11));
		text.setMinimumSize(new Dimension(200, 30));
		text.setMaximumSize(new Dimension(200, 30));
		text.setPreferredSize(new Dimension(200, 30));
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		mainPanel.add(messageLabel);
		mainPanel.add(text);
		mainPanel.setVisible(true);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JButton conferma = new JButton("Conferma");
		conferma.setMinimumSize(new Dimension(100, 35));
		conferma.setPreferredSize(new Dimension(100, 35));
		conferma.setMaximumSize(new Dimension(100, 35));
		conferma.setAlignmentX(CENTER_ALIGNMENT);
		
		conferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm(text.getText());
				dispose();
			}
		});
		
		JButton annulla = new JButton("Annulla");
		annulla.setMinimumSize(new Dimension(100, 35));
		annulla.setPreferredSize(new Dimension(100, 35));
		annulla.setMaximumSize(new Dimension(100, 35));
		annulla.setAlignmentX(CENTER_ALIGNMENT);
		
		annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		buttonPanel.add(conferma);
		buttonPanel.add(annulla);
		buttonPanel.setVisible(true);
		
		
		this.add(mainPanel);
		this.add(buttonPanel);
		
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Metodo invocato in fase di conferma
	 * @param matricola
	 */
	public abstract void confirm(String matricola);
}

