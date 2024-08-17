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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import SottosistemaPianiDiStudio.PianoDiStudio;

/**
 * Form che consente di visualizzare i piani di studio che devono essere validati manualmente
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class FormListaPianiDaValidare extends JDialog{

	private JTable table;
	private DefaultTableModel tableModelPiani;
	private List<PianoDiStudio> piani;
	protected GUIController controller;

	/**
	 * Create the application.
	 */
	public FormListaPianiDaValidare(List<PianoDiStudio> piani) {
		super();
		controller = GUIController.getInstance();
		this.setTitle("Selezione piani di studio");
		this.piani = piani;
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setModal(true);
		setBounds(100, 100, 729, 417);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(730, 390));
		setMaximumSize(new Dimension(730, 390));
		setPreferredSize(new Dimension(730, 390));
		setResizable(false);
		setLocationRelativeTo(null);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 90, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 33, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -29, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);
		
		table = new JTable();
		tableModelPiani = new DefaultTableModel(0, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				}
			};
		
		String intestazione[] = new String [] {"Codice", "Studente", "Nome corso", "Livello"};
		tableModelPiani.setColumnIdentifiers(intestazione);
		table.setModel(tableModelPiani);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		table.setBounds(0, 0, 1, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 2, 2);
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		panel.setBorder(BorderFactory.createTitledBorder(border, "Piani da validare"));
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.setMinimumSize(new Dimension(500, 200));
		panel.setPreferredSize(new Dimension(500, 200));
		panel.setMaximumSize(new Dimension(500, 200));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.setVisible(true);
		panel.setBounds(0, 0, 600, 227);
		panel.add(scrollPane);
		
		
		JButton btnAnnulla = new JButton("Annulla");
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, btnAnnulla);
		springLayout.putConstraint(SpringLayout.EAST, btnAnnulla, -412, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAnnulla, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnAnnulla);
		btnAnnulla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		JButton btnControlla = new JButton("Controlla");
		springLayout.putConstraint(SpringLayout.NORTH, btnControlla, 0, SpringLayout.NORTH, btnAnnulla);
		springLayout.putConstraint(SpringLayout.WEST, btnControlla, 96, SpringLayout.EAST, btnAnnulla);
		getContentPane().add(btnControlla);
		
		btnControlla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control();
			}
		});
		
		JLabel label = new JLabel("Selezione piani di studio");
		label.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		label.setFont(new Font("Arial",Font.ITALIC,18));
		
		
		JPanel labelPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, labelPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelPanel, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, labelPanel, 58, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelPanel, 411, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelPanel);
		SpringLayout sl_labelPanel = new SpringLayout();
		sl_labelPanel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, labelPanel);
		sl_labelPanel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, labelPanel);
		labelPanel.setLayout(sl_labelPanel);
		labelPanel.add(label);
		labelPanel.setVisible(true);
		updateGUI();
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Metodo invocato per controllare il piano di studio selezionato
	 */
	private void control() {
		int position = table.getSelectedRow();	
		dispose();
		if(position < 0)
			position = 0;
		controller.showPlanToBeApproved(position);
	}
	
	/**
	 * Metodo invocato per il refresh della GUI
	 */
	private void updateGUI() {
		tableModelPiani.setRowCount(0);
		for(PianoDiStudio p : piani) {
			String matricola = "", nomeCorso = "";
			int id = 0, liv = 0;
			if(p != null) {
				id = p.getID();
				if(p.getStudent() != null)
					matricola = p.getStudent().getSerialNumber();
				if(p.getCourse() != null){
					nomeCorso = p.getCourse().getName();
					liv = p.getCourse().getLevel();
				}
				tableModelPiani.addRow(new Object[] {id, matricola, nomeCorso, liv});
			}
		}
		repaint();
	}
}
