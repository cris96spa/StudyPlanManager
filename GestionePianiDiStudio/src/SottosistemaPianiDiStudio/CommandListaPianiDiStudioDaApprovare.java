package SottosistemaPianiDiStudio;

import java.util.List;

import Persistenza.GestoreDB;

/**
 * Classe per la il recupero della lista dei piani di studio da approvare manualmente
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class CommandListaPianiDiStudioDaApprovare extends CommandPianiDiStudio {

	private GestoreDB gestoreDB;

	public CommandListaPianiDiStudioDaApprovare() {
		gestoreDB = GestoreDB.getInstance();
	}
	
	/**
	 * Consente di ottenere la lista di piani di studio in approvazione
	 */
	@Override
	public List<PianoDiStudio> getStudyPlansList() {
		return gestoreDB.getStudyPlansList();
	}

}
