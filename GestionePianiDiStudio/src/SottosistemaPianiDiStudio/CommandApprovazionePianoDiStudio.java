package SottosistemaPianiDiStudio;

import java.util.Date;

import Persistenza.GestoreDB;
import SottosistemaPianiDiStudio.PianoDiStudio.Stato;
import SottosistemaStudenti.Studente;
import Util.CommandNotValidException;
import Util.NotValidApprovationDataException;

/**
 *Classe che consente di gestire le operazioni legate all'approvazione del piano di studio
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class CommandApprovazionePianoDiStudio extends CommandPianiDiStudio {

	private GestoreDB gestoreDB;

	public CommandApprovazionePianoDiStudio() {
		gestoreDB = GestoreDB.getInstance();
	}
	
	/**
	 * Metodo invocato quando viene richiesta la validazione di un piano di studio presentato precedentemente
	 */
	@Override
	public boolean validateStudyPlan(Studente studente) {
		boolean op1 = true;
		boolean op2 = false;
		PianoDiStudio pianoVecchio = studente.getStudyPlan();
		if(pianoVecchio != null) {
			op1 = gestoreDB.changeStudyPlan(studente);
		}
		if(op1) {
			PianoDiStudio pianoNuovo = studente.getOnApprovationStudyPlan();
			op1 = gestoreDB.validateStudyPlan(pianoNuovo);
			if(op1) {
				studente.setOnApprovationStudyPlan(null);
				studente.setStudyPlan(pianoNuovo);
				gestoreDB.removeStudyPlan(pianoNuovo);
				op2 = true;
			}
		}
		return op2;
	}

	/**
	 * Metodo invocato per l'approvazione di un nuovo piano di studio
	 */
	@Override
	public boolean approveStudyPlan(PianoDiStudio pianoNuovo) throws CommandNotValidException {
		boolean op1 = true;
		boolean op2 = false;
		Studente studente = pianoNuovo.getStudent();
		pianoNuovo.setStatus(Stato.approvato);
		PianoDiStudio pianoVecchio = studente.getStudyPlan();
		if(pianoVecchio != null) {
			op1 = gestoreDB.changeStudyPlan(studente);
		}
		if(op1) {
			op1 = gestoreDB.insertStudyPlan(pianoNuovo);
			if(op1) {
				studente.setOnApprovationStudyPlan(null);
				studente.setStudyPlan(pianoNuovo);
				try {
					pianoNuovo.setApprovationDate(new Date());
				} catch (NotValidApprovationDataException e) {
					e.printStackTrace();
				}
				op2 = true;
			}
		}
		pianoNuovo.setStatus(Stato.nonApprovato);
		return op2;
	}
}
