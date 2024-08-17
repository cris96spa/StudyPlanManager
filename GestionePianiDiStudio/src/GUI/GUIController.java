package GUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import SottosistemaPianiDiStudio.FacadeGestionePianiDiStudio;
import SottosistemaPianiDiStudio.Insegnamento;
import SottosistemaPianiDiStudio.PianoDiStudio;
import SottosistemaStudenti.Studente;

/**
 * Classe di controllo dell'interazione tra l'utente e il resto del sistema
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class GUIController {

	private static GUIController instance;
	private FacadeGestionePianiDiStudio facadePianiDiStudio;
	private List<Insegnamento> insegnamenti;
	private List<PianoDiStudio> piani;
	private Studente studente;
	private PianoDiStudio piano;
	private ArrayList<Integer> restorePosition;

	/**
	 * Costruttore privato
	 */
	private GUIController() {
		facadePianiDiStudio = FacadeGestionePianiDiStudio.getInstance();
		insegnamenti = null;
		studente = null;
		piani = null;
		piano = null;
		restorePosition = null;
	}
	
	/**
	 * Metodo usato per l'implementazione del pattern Singleton
	 * @return
	 */
	public static GUIController getInstance() {
		if(instance == null)
			instance = new GUIController();
		return instance;
	}
	
	/**
	 * Creazione di un messaggio di errore
	 * @param message
	 */
	public void createErrorForm(String message) {
		JOptionPane opt = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
		final JDialog dlg = opt.createDialog("Errore");
		dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlg.setVisible(true);
	}

	/**
	 * Creazione di un messaggio di conferma
	 * @param message
	 */
	public void createConfirmForm(String message) {
		JOptionPane opt = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		final JDialog dlg = opt.createDialog("Conferma operazione");
		dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		/*new Thread(new Runnable(){
			@Override
			public void run(){
		    	try {
		        	Thread.sleep(1100);
		        	dlg.dispose();
		    	} catch ( Throwable th ) {
		    		th.printStackTrace();
		        }
			}
		}).start();*/
		dlg.setVisible(true);
	}
	
	/**
	 * Creazione di un messaggio di notifica 
	 * @param message
	 */
	public void createNotifyForm(String message) {
		JOptionPane opt = new JOptionPane(message, JOptionPane.WARNING_MESSAGE);
		final JDialog dlg = opt.createDialog("Attenzione");
		dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		new Thread(new Runnable(){
			@Override
			public void run(){
		    	try {
		        	Thread.sleep(4000);
		        	dlg.dispose();
		    	} catch ( Throwable th ) {
		    		th.printStackTrace();
		        }
			}
		}).start();
		dlg.setVisible(true);
	}
	
	/**
	 * Metodo che consente di creare il form di inserimento matricola che porterà poi alla presentazione del 
	 * piano di studio
	 */
	private void createPresentationSerialNumberForm() {
		FormMatricola formMatricola = new FormMatricolaPresentazione();
	}
	
	/**
	 * Metodo che consente di creare il form di inserimento matricola che porterà poi alla visualizzazione del 
	 * piano di studio
	 */
	private void createVisualizationSerialNumberForm() {
		FormMatricola formMatricola = new FormMatricolaVisualizzazione();
	}
	
	/**
	 * Creazione del form di presentazione del piano di studio
	 * @param insegnamenti insegnamenti del piano
	 * @return
	 */
	private FormGestisciPianoDiStudio createSubmitStudyPlanForm(List<Insegnamento> insegnamenti) {
		return new FormGestisciPianoDiStudio(studente, insegnamenti);
	}
	
	/**
	 * Metodo che consente di creare il form con cui saranno scegli gli insegnamenti da inserire nel piano di studio
	 */
	private void createTeachingsListForm() {
		HashSet<Integer> selectedPos = new HashSet<Integer>();
		if(restorePosition != null) {
			Iterator<Integer> it = restorePosition.iterator();
			while(it.hasNext()) {
				selectedPos.add(it.next());
			}
		}
		FormListaInsegnamenti form = new FormListaInsegnamenti(insegnamenti, selectedPos);
	}
	
	/**
	 * Metodo che consente di creare il form con i piani di studio in attesa di approvazione manuale
	 */
	public void createStudyPlansList() {
		FormListaPianiDaValidare form = new FormListaPianiDaValidare(piani);
	}
	
	/**
	 * Metodo che consente di creare il form di visualizzazione del piano di studio dello studente
	 * @param insegnamenti
	 * @return
	 */
	private FormVisualizzaPianoDiStudio createStudyPlanForm(List<Insegnamento> insegnamenti) {
		return new FormVisualizzaPianoDiStudio(studente, insegnamenti);
	}

	/**
	 * Metodo che consente di creare il form tramite il quale potrà essere effettuata la validazione manuale
	 * dei piani di studio.
	 */
	private void createStudyPlanToValidateForm() {
		FormPianoDaValidare form = new FormPianoDaValidare(studente, piano.getTeachings());
	}
	
	/**
	 * Metodo invocato quando viene scelta la modifica degli insegnamenti inseriti nel piano di studio.
	 */
	public void editTeachings() {
		insegnamenti = facadePianiDiStudio.getTeachingsList();
		if(insegnamenti != null)
			createTeachingsListForm();
		else
			createErrorForm("Nessun insegnamento disponibile!");
	}

	/**
	 * Metodo invocato dal mainFrame quando richiesta la presentazione di un piano di studio
	 */
	public void submitStudyPlan() {
		restorePosition = null;
		createPresentationSerialNumberForm();
	}
	
	/**
	 * Metodo invocato dal mainFrame quando richiesta la visualizzazione di un piano di studio
	 */
	public void showStudyPlan() {
		restorePosition = null;
		createVisualizationSerialNumberForm();
	}
	
	/**
	 * Metodo invocato dal mainFrame quando richiesta la visualizzazione dei piani di studio da validare manualmente
	 */
	public void showOnApprovationStudyPlans() {
		restorePosition = null;
		piani = facadePianiDiStudio.getStudyPlansList();
		if(piani != null && piani.size() > 0)
			createStudyPlansList();
		else
			createConfirmForm("Nessun piano di studio da validare!");
	}
	
	/**
	 * Metodo invocato dal Form lista insegnamenti quando vengono confermati gli insegnamenti da aggiungere
	 * nel piano di studio
	 * @param positions vettore contenente le posizioni degli insegnamenti selezionati
	 */
	public void updateFormGestisciPianoDiStudi(ArrayList<Integer> pos) {
		if(pos != null) {
			restorePosition = pos;
			Iterator<Integer> it = restorePosition.iterator();
			List<Insegnamento> teachings = new ArrayList<Insegnamento>();
			int i = 0;
			while(it.hasNext()) {
				i = it.next();
				teachings.add(insegnamenti.get(i));
			}
			FormGestisciPianoDiStudio form = createSubmitStudyPlanForm(teachings);
		}
	}

	/**
	 * Metodo che consente di ripristinare gli insegnamenti precedentemente selezionati qualora si annullasse
	 * l'operazione di aggiunta insegnamenti al piano
	 */
	public void restoreFormGestisciPianoDiStudi() {
		if(restorePosition != null) {
			Iterator<Integer> it = restorePosition.iterator();
			List<Insegnamento> teachings = new ArrayList<Insegnamento>();
			int i = 0;
			while(it.hasNext()) {
				i = it.next();
				teachings.add(insegnamenti.get(i));
			}
			FormGestisciPianoDiStudio form = createSubmitStudyPlanForm(teachings);
		}
	}

	/**
	 * Metodo invocato dal FormMatricolaPresentazione quando è confermata la matricola inserita
	 * @param form
	 * @param matricola
	 */
	public void send(FormMatricolaPresentazione form, String matricola) {
		studente = facadePianiDiStudio.getStudent(matricola);
		form.dispose();
		if(studente == null) {
			createErrorForm("Non sono presenti studenti con la matricola : "+matricola);
			createVisualizationSerialNumberForm();
		} else {
			if(studente.getStudyPlan() == null && studente.getOnApprovationStudyPlan() == null) {
				createConfirmForm("Studente trovato!");
				createSubmitStudyPlanForm(null);
			} else {
				createErrorForm("Piano di studio già presentato per lo studente : "+matricola);
			}
		}
	}
	
	/**
	 * Metodo invocato dal FormMatricolaVisualizzazione quando è confermata la matricola inserita
	 * @param form
	 * @param matricola
	 */
	public void send(FormMatricolaVisualizzazione form, String matricola) {
		studente = facadePianiDiStudio.getStudentWithStudyPlan(matricola);
		form.dispose();
		if(studente == null) {
			createErrorForm("Non sono presenti studenti con la matricola: "+matricola);
			createVisualizationSerialNumberForm();
		} else {
			if(studente.getStudyPlan() != null) {
				createConfirmForm("Studente trovato!");
				FormVisualizzaPianoDiStudio formPiano = createStudyPlanForm(studente.getStudyPlan().getTeachings());
			} else {
				createErrorForm("Nessun piano di studio valido per lo studente: "+matricola);
			}
		} 
	}

	/**
	 * Metodo invocato quando viene respinto un piano di studio in fase di validazione manuale
	 * @param studente
	 */
	public void refuseStudyPlan(Studente studente) {
		boolean op = facadePianiDiStudio.refuseStudyPlan(studente);
		if(op)
			createConfirmForm("Operazione avvenuta con successo");	
		else
			createErrorForm("Errore, qualcosa è andato storto");
		showOnApprovationStudyPlans();
	}

	/**
	 * Metodo invocato quando viene approvato un piano di studio in fase di validazione manuale
	 * @param studente
	 */
	public void validateStudyPlan(Studente studente) {
		boolean op = facadePianiDiStudio.validateStudyPlan(studente);
		if(op)
			createConfirmForm("Operazione avvenuta con successo");	
		else
			createErrorForm("Errore, qualcosa è andato storto");
		showOnApprovationStudyPlans();
	}

	/**
	 * Ottieni la collezione di insegnamenti
	 * @return
	 */
	public List<Insegnamento> getTeachingsList() {
		return insegnamenti;
	}

	/**
	 * Metodo invocato per la presentazione del piano di studio
	 * @param teachings
	 */
	public void registerStudyPlan(List<Insegnamento> teachings) {
		PianoDiStudio.Stato stato = facadePianiDiStudio.registerStudyPlan(teachings);
		switch(stato) {
		case nonApprovato:
			createErrorForm("Piano di studio non approvato");
			restoreFormGestisciPianoDiStudi();
			break;
		case approvato:
			approveStudyPlan(studente.getOnApprovationStudyPlan());
			break;
		case inApprovazione:
			presentStudyPlan(studente.getOnApprovationStudyPlan());
		}
	}

	/**
	 * Metodo invocato quando il piano di studio non ha superato tutti i controlli e dovrà essere approvato manualmente
	 * @param piano
	 */
	private void presentStudyPlan(PianoDiStudio piano) {
		boolean op = facadePianiDiStudio.presentStudyPlan(piano);
		if(op)
			createNotifyForm("E' richiesta un'approvazione manuale del piano di studio");
		else
			createErrorForm("Qualcosa è andato storto durante l'inserimento del piano di studio");
		
	}

	/**
	 * Metodo invocato quando il piano di studio ha superato i controlli di approvazione automatica
	 * @param piano
	 */
	private void approveStudyPlan(PianoDiStudio piano) {
		boolean op = facadePianiDiStudio.approveStudyPlan(piano);
		if(op)
			createConfirmForm("Il piano di studio inserito è stato approvato");
		else
			createErrorForm("Qualcosa è andato storto durante l'inserimento del piano di studio");
		
	}

	/**
	 * Metodo invocato dal FormListaPiani per mandare la posizione del piano selezionato 
	 * e avviarne la visualizzazione per l'approvazione manuale
	 * @param position
	 */
	public void showPlanToBeApproved(int position) {
		if(position >= 0 && piani.size() > 0) {
			piano = piani.get(position);
			studente = piano.getStudent();
			createStudyPlanToValidateForm();
		}
	}
}
