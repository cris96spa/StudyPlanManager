package SottosistemaPianiDiStudio;

import java.util.List;

import Persistenza.GestoreDB;

/**
 * Classe per la il recupero della lista di insegnamenti
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class CommandListaInsegnamenti extends CommandPianiDiStudio {
	
	private GestoreDB gestoreDB;

	public CommandListaInsegnamenti() {
		gestoreDB = GestoreDB.getInstance();
	}
	
	/**
	 * Consente di recuperare la lista di insegnamenti
	 */
	@Override
	public List<Insegnamento> getTeachingsList() {
		return gestoreDB.getTeachings();
	}

}
