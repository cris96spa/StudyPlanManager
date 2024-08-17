package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
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

import SottosistemaPianiDiStudio.Insegnamento;

/**
 * Form per la selezione degli insegnanti da inserire nel piano di studio
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class FormListaInsegnamenti extends JDialog{

	private JTable table;
	private DefaultTableModel tableModelInsegnamenti;
	private List<Insegnamento> insegnamenti;
	private GUIController controller;
	private HashSet<Integer> selectedPos;
	private JLabel cfu;

	
	public FormListaInsegnamenti(List<Insegnamento> insegnamenti, HashSet<Integer> selectedPos) {
		super();
		controller = GUIController.getInstance();
		this.setTitle("Selezione insegnamenti");
		this.insegnamenti = insegnamenti;
		this.selectedPos = selectedPos;
		initialize();
	}

	
	private void initialize() {
		setModal(true);
		setBounds(100, 100, 729, 417);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				controller.restoreFormGestisciPianoDiStudi();
			}
		});
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
		
		tableModelInsegnamenti = new DefaultTableModel(0, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 4)
					return true;
				return false;
				}
			};
		
		String intestazione[] = new String [] {"Codice", "Nome", "CFU", "Settore SD", "Inserito"};
		tableModelInsegnamenti.setColumnIdentifiers(intestazione);
		table = new JTable(tableModelInsegnamenti) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
		};
	
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
	              	updateCFU();
	            }
		});
		
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(0, 0, 1, 1);		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 2, 2);
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		panel.setBorder(BorderFactory.createTitledBorder(border, "Insegnamenti disponibili"));
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
				controller.restoreFormGestisciPianoDiStudi();
				
			}
		});
		
		JButton btnConferma = new JButton("Conferma");
		springLayout.putConstraint(SpringLayout.NORTH, btnConferma, 0, SpringLayout.NORTH, btnAnnulla);
		springLayout.putConstraint(SpringLayout.WEST, btnConferma, 96, SpringLayout.EAST, btnAnnulla);
		getContentPane().add(btnConferma);
		
		btnConferma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		
		JLabel label = new JLabel("Selezione Insegnamenti");
		label.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		label.setFont(new Font("Arial",Font.ITALIC,18));
		
		JLabel lblNumeroCfu = new JLabel("CFU inseriti: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumeroCfu, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblNumeroCfu, 36, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNumeroCfu);
		
		cfu = new JLabel(0+"");
		springLayout.putConstraint(SpringLayout.NORTH, cfu, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, cfu, 6, SpringLayout.EAST, lblNumeroCfu);
		getContentPane().add(cfu);
		
		
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
	 * Metodo invocato quando vengono confermati gli insegnamenti inseriti
	 */
	private void confirm() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i = 0; i< tableModelInsegnamenti.getRowCount(); i++) {
			if((boolean) tableModelInsegnamenti.getValueAt(i, 4))
				positions.add(i);
		}
		dispose();
		controller.updateFormGestisciPianoDiStudi(positions);
	}
	
	/**
	 * Metodo invocato per il refresh della GUI
	 */
	private void updateGUI() {
		int c = 0;
		String id = "";
		String name = "";
		String ssd = "";
		boolean inserito = false;
		tableModelInsegnamenti.setRowCount(0);
		for(int j =0; j< insegnamenti.size(); j++) {
			Insegnamento i = insegnamenti.get(j);
			if(i != null) {
				c = i.getCfu();
				name = i.getName();
				id = i.getID();
				ssd = i.getScientificSector();
				if(selectedPos != null) {
					inserito = selectedPos.contains(j);
				}
				tableModelInsegnamenti.addRow(new Object[] {id, name, c, ssd, inserito});
			}
		}
		updateCFU();
	}
	
	/**
	 * Metodo invocato per aggiornare il numero di cfu man mano che vengono selezionati degli
	 * insegnamenti.
	 */
	private void updateCFU() {
		int cfu = 0;
		boolean selected = false;
		for(int j =0; j< insegnamenti.size(); j++) {
			selected = (boolean) table.getModel().getValueAt(j, 4);
			if(selected)
				cfu += (int) table.getModel().getValueAt(j, 2);
		}
		this.cfu.setText(cfu+"");
		repaint();
	}
}
