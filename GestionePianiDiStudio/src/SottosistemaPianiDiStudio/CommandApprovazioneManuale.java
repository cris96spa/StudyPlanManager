package SottosistemaPianiDiStudio;

import Persistenza.GestoreDB;
import SottosistemaPianiDiStudio.PianoDiStudio.Stato;

/**
 * Classe che consente di gestire le operazioni legate all'approvazione manuale
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class CommandApprovazioneManuale extends CommandPianiDiStudio {
	
	private GestoreDB gestoreBD;
	
	public CommandApprovazioneManuale() {
		gestoreBD =  GestoreDB.getInstance();
	}

	
	/**
	 * Consente di inserire il piano di studio presentato nella
	 * lista dei piani di studio da approvare
	 */
	@Override
	public boolean presentStudyPlan(PianoDiStudio p) {
		p.setStatus(Stato.inApprovazione);
		boolean op = gestoreBD.insertStudyPlan(p);
		return op;
	}

}
