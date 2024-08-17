package SottosistemaPianiDiStudio;

import Persistenza.GestoreDB;
import SottosistemaStudenti.Studente;

/**
 * Classe usata per la gestione delle operazioni per la respinta del piano di studio
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class CommandRespintaPianoDiStudio extends CommandPianiDiStudio {

	private GestoreDB gestoreDB;

	public CommandRespintaPianoDiStudio() {
		gestoreDB = GestoreDB.getInstance();
	}
	
	/**
	 * Consente di effettuare la respinta del piano di studio
	 */
	@Override
	public boolean refuseStudyPlan(Studente studente) {
		boolean op = gestoreDB.refuseStudyPlan(studente);
		if(op) {
			PianoDiStudio piano = studente.getOnApprovationStudyPlan();
			gestoreDB.removeStudyPlan(piano);
			studente.setOnApprovationStudyPlan(null);
		}
		return op;
	}

}
