package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import SottosistemaPianiDiStudio.CorsoDiStudio;
import SottosistemaPianiDiStudio.Insegnamento;
import SottosistemaPianiDiStudio.PianoDiStudio;
import SottosistemaStudenti.Studente;
import Util.Constants;
import net.miginfocom.swing.MigLayout;

/**
 * Form per la gestione del piano di studio.
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class FormGestisciPianoDiStudio extends JDialog {
	private JTable table;
	private DefaultTableModel tableModelInsegnamenti;
	private Studente studente;
	private PianoDiStudio pianoDiStudio;
	private List<Insegnamento> insegnamenti;
	private JLabel cfu;
	private GUIController controller;
	private JButton btnModifica, btnConferma;

	public FormGestisciPianoDiStudio(Studente studente, List<Insegnamento> teachings) {
		
		setTitle("Presenzazione piano di studio");
		controller = GUIController.getInstance();
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.studente = studente;
		setBounds(100, 100, 686, 480);
		this.setMinimumSize(new Dimension(667, 460));
		this.setMaximumSize(new Dimension(667, 460));
		this.setPreferredSize(new Dimension(667, 460));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 27, SpringLayout.WEST, getContentPane());
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 344, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -35, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, panel, -16, SpringLayout.WEST, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 120, SpringLayout.NORTH, getContentPane());
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 24, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 27, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, 251, SpringLayout.SOUTH, panel);
		panel.setLayout(new MigLayout("", "[][][][][][][]", "[][][][][]"));
		
		JLabel lblNome= new JLabel("Nome:");
		panel.add(lblNome, "cell 0 0");
		
		JLabel nome = new JLabel(studente != null? studente.getName() : "");
		panel.add(nome, "cell 2 0");
		
		JLabel lblCognome = new JLabel("Cognome:");
		panel.add(lblCognome, "cell 0 1");
		
		JLabel cognome = new JLabel(studente != null? studente.getSurname() : "");
		panel.add(cognome, "cell 2 1");
		
		JLabel lblNascita = new JLabel("Data nascita:");
		panel.add(lblNascita, "cell 0 2");
		
		JLabel nascita = new JLabel(studente != null? Constants.SD.format(studente.getbirthDate()) : "");
		panel.add(nascita, "cell 2 2");
		
		springLayout.putConstraint(SpringLayout.EAST, panel_2, 627, SpringLayout.WEST, getContentPane());
		getContentPane().add(panel_2);
		
		btnModifica = new JButton("Modifica");
		getContentPane().add(btnModifica);
		btnModifica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editTeachings();
				
			}
		});
		
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnConferma, 14, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.NORTH, btnModifica, 0, SpringLayout.NORTH, btnConferma);
		springLayout.putConstraint(SpringLayout.EAST, btnModifica, -28, SpringLayout.WEST, btnConferma);
		springLayout.putConstraint(SpringLayout.EAST, btnConferma, 0, SpringLayout.EAST, panel_1);
		panel_1.setLayout(new MigLayout("", "[][][]", "[][][][]"));
		
		JLabel lblMatricola = new JLabel("Matricola:");
		panel_1.add(lblMatricola, "cell 0 0");
		
		JLabel matricola = new JLabel(studente != null? studente.getSerialNumber() : "");
		panel_1.add(matricola, "cell 2 0");
		
		CorsoDiStudio corso = null;
		if(studente != null && studente.getRegistration() != null)
			corso = studente.getRegistration().getCourse();
		
		JLabel lblNomeCorso = new JLabel("Nome corso:");
		panel_1.add(lblNomeCorso, "cell 0 1");
		
		JLabel nomeCorso = new JLabel(corso != null? corso.getName() : "");
		panel_1.add(nomeCorso, "cell 2 1");
		
		JLabel lblLivello = new JLabel("Livello corso:");
		panel_1.add(lblLivello, "cell 0 2");
		
		JLabel livello = new JLabel(corso != null? corso.getLevel()+"" : "");
		panel_1.add(livello, "cell 2 2");
		
		JLabel lblAnno = new JLabel("Anno di corso:");
		panel_1.add(lblAnno, "cell 0 3");
		
		JLabel anno = new JLabel((studente != null && studente.getRegistration() != null)? studente.getRegistration().getCourseYear()+"" : "");
		panel_1.add(anno, "cell 2 3");
		getContentPane().add(btnConferma);
		
		JLabel lblNumeroCfu = new JLabel("Numero CFU: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumeroCfu, 6, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNumeroCfu, 26, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNumeroCfu);
		
		cfu = new JLabel(0+"");
		springLayout.putConstraint(SpringLayout.NORTH, cfu, 6, SpringLayout.SOUTH, panel_2);
		
		table = new JTable();
		table.setSelectionMode(0);
		tableModelInsegnamenti = new DefaultTableModel(0, 0) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		String intestazione[] = new String [] {"Codice", "Nome", "CFU"};
		tableModelInsegnamenti.setColumnIdentifiers(intestazione);
		table.setModel(tableModelInsegnamenti);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setGridColor(Color.LIGHT_GRAY);
			
		table.setBounds(0, 0, 1, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		panel_2.setBorder(BorderFactory.createTitledBorder(border, "Insegnamenti inseriti"));
		panel_2.setLayout(new BorderLayout());
		panel_2.add(scrollPane, BorderLayout.CENTER);
		panel_2.setMinimumSize(new Dimension(500, 200));
		panel_2.setPreferredSize(new Dimension(500, 200));
		panel_2.setMaximumSize(new Dimension(500, 200));
		panel_2.setAlignmentX(CENTER_ALIGNMENT);
		panel_2.setVisible(true);
		panel_2.setBounds(0, 0, 600, 227);
		panel_2.add(scrollPane);
		
		springLayout.putConstraint(SpringLayout.WEST, cfu, 6, SpringLayout.EAST, lblNumeroCfu);
		getContentPane().add(cfu);
		setTeachings(teachings);
		this.pack();
		this.setVisible(true);
		
	}

	/**
	 * Consente di settare la lista di insegnamenti
	 * @param insegnamenti
	 */
	public void setTeachings(List<Insegnamento> insegnamenti) {
		this.insegnamenti = insegnamenti;
		updateGUI();
	}

	/**
	 * Metodo invocato per il refresh della GUI
	 */
	private void updateGUI() {
		int totalCfu = 0;
		int c = 0;
		String id;
		String name = "";
		tableModelInsegnamenti.setRowCount(0);
		if(insegnamenti != null) {
			for(Insegnamento i : insegnamenti) {
				if(i != null) {
					c = i.getCfu();
					totalCfu += c;
					name = i.getName();
					id = i.getID();
					tableModelInsegnamenti.addRow(new Object[] {id, name, c});
				}
			}
		}
		this.cfu.setText(totalCfu+"");
		repaint();
	}
	
	/**
	 * Metodo invocato quando viene confermata la presentazione del piano di studio
	 */
	private void confirm() {
		dispose();
		controller.registerStudyPlan(insegnamenti);
	}
	
	/**
	 * Medoto invocato quando viene selezionata la modifica degli insegnamenti inseriti
	 */
	public void editTeachings() {
		dispose();
		controller.editTeachings();
	}
}

