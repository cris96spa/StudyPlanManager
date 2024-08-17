package SottosistemaPianiDiStudio;

import java.util.List;

import SottosistemaStudenti.FacadeGestioneStudenti;
import SottosistemaStudenti.Studente;
import Util.CommandNotValidException;

//Rappresenta l'invoker del command per il sottosistema GestionePianiDiStudio
//Implementato come Singleton
public class FacadeGestionePianiDiStudio {

	private static FacadeGestionePianiDiStudio instance;
	private FacadeGestioneStudenti facadeStudenti;
	private Studente studente;
	private CommandPianiDiStudio commandApprovazioneManuale;
	private CommandPianiDiStudio commandListaInsegnamenti;
	private CommandPianiDiStudio commandListaPianiDiStudioDaApprovare;
	private CommandPianiDiStudio commandApprovazionePianoDiStudio;
	private CommandPianiDiStudio commandRespintaPianoDiStudio;
	private CommandPianiDiStudio commandRegole;

	private FacadeGestionePianiDiStudio() {
		facadeStudenti = FacadeGestioneStudenti.getInstance();
		commandApprovazioneManuale = new CommandApprovazioneManuale();
		commandListaInsegnamenti = new CommandListaInsegnamenti();
		commandListaPianiDiStudioDaApprovare = new CommandListaPianiDiStudioDaApprovare();
		commandApprovazionePianoDiStudio = new CommandApprovazionePianoDiStudio();
		commandRespintaPianoDiStudio = new CommandRespintaPianoDiStudio();
		commandRegole = new CommandRegole();
		studente = null;
	}
	
	
	/**
	 * Metodo statico per il recupero/creazione dell'unica istanza, nell'intero sistema, di tale classe.
	 */
	public static FacadeGestionePianiDiStudio getInstance() {
		if(instance == null)
			instance = new FacadeGestionePianiDiStudio();
		return instance;
	}
	
	/**
	 * Consente di recuperare lo studente a cui appartiene la matricola passata come paramentro.
	 * Lo studente viene recuperato attravero il layer sottostante dato dal SottosistemaStudenti
	 * acceduto tramite Facade.
	 * @param matricola dello studente che si vuole recuperare
	 * @return studente se la matricola esiste della base di dati, null altrimenti
	 */
	public Studente getStudent(String matricola) {
		studente = null;
		try {
			studente = facadeStudenti.getStudent(matricola);
		} catch (CommandNotValidException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			studente = null;
		}
		return studente;
	}

	
	/**
	 * Metodo invocato per recuperare lo studente, settando inoltre tutti gli insegnamenti 
	 * del piano di studio. Accede al Facade del livello SottosistemaStudenti dell'architettura.
	 * @param matricola
	 * @return studente
	 */
	public Studente getStudentWithStudyPlan(String matricola) {
		studente = null;
		try {
			studente = facadeStudenti.getStudentWithStudyPlan(matricola);
		} catch (CommandNotValidException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			studente = null;
		}
		return studente;
	}
	
	/**
	 * Consente di recuperare La lista dei piani di studio da approvare. 
	 * Accede al database tramite un Command specializzato.
	 * @return 
	 */
	public List<PianoDiStudio> getStudyPlansList() {
		try {
			return commandListaPianiDiStudioDaApprovare.getStudyPlansList();
		} catch (CommandNotValidException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void removeStudyPlan(Studente studente, int ID) {
		//non implementato poichè non facente parte degli use cases selezionati.
	}

	
	/**
	 * Metodo invocato quando viene richiesta la validazione di un piano di studio presentato precedentemente
	 */
	public boolean validateStudyPlan(Studente studente) {
		try {
			return commandApprovazionePianoDiStudio.validateStudyPlan(studente);
		} catch (CommandNotValidException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Consente di recuperare la lista di tutti gli insegnamenti attivati dall'ateneo al fine di permettere allo studente di poterli
	 * scegliere per il proprio piano. 
	 * Accede al database tramite un Command specializzato.
	 * @return 
	 */
	public List<Insegnamento> getTeachingsList() {
		List<Insegnamento> insegnamenti = null;
		try {
			insegnamenti = commandListaInsegnamenti.getTeachingsList();
		} catch (CommandNotValidException e) {
			e.printStackTrace();
		}
		return insegnamenti;
	}

	/**
	 * Consente di recuperare la lista di tutti gli insegnamenti attivati dall'ateneo al fine di permettere allo studente di poterli
	 * scegliere per il proprio piano. 
	 * Accede al database tramite un Command specializzato.
	 * @return 
	 */
	public PianoDiStudio.Stato registerStudyPlan(List<Insegnamento> teachings) {
		studente.presentStudyPlan(teachings);
		PianoDiStudio.Stato esito = null;
		try {
			esito = commandRegole.checkApprovation(studente);
		} catch (CommandNotValidException e) {
			
			e.printStackTrace();
		}
		return esito;
	}

	/**
	 * Consente di respingere manualmente uno piano di studi.
	 * @return true se è andato tutto bene, false altrimenti
	 */
	public boolean refuseStudyPlan(Studente studente) {
		if(studente != null && this.studente != studente)
			this.studente = studente;
		try {
			 return commandRespintaPianoDiStudio.refuseStudyPlan(studente);
		} catch (CommandNotValidException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Consente approvare manualmente il piano di studio presentato ma non confermato automaticamente dal sistema. 
	 * Accede al database tramite un Command specializzato.
	 * @return true se è andato a buon fine, false altrimenti
	 */
	public boolean approveStudyPlan(PianoDiStudio piano) {
		boolean op = false;
		try {
			op = commandApprovazionePianoDiStudio.approveStudyPlan(piano);
		} catch (CommandNotValidException e) {
			e.printStackTrace();
		}
		return op;
	}

	/**
	 * Consente di inserire il piano di studio presentato nella
	 * lista dei piani di studio da approvare
	 */
	public boolean presentStudyPlan(PianoDiStudio piano) {
		boolean op = false;
		try {
			op = commandApprovazioneManuale.presentStudyPlan(piano);
		} catch (CommandNotValidException e) {
			e.printStackTrace();
		}
		return op;
	}

}
