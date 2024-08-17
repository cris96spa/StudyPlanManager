package SottosistemaStudenti;

import Persistenza.GestoreDB;
import Util.CommandNotValidException;

public class CommandMatricola extends CommandStudenti {

	private GestoreDB gestoreDB;
	
	/**
	 * Metodo static relativo al DP Singleton
	 */
	public CommandMatricola () {
		gestoreDB = GestoreDB.getInstance();
	}

	/**
	 * Consente di recuperare lo studente che possiede una certa matricola.
	 * @param la matricola dello studente da recuperare
	 */
	@Override
	public Studente getStudent(String matricola) {
		return gestoreDB.getStudent(matricola);
	}
	
	/**
	 * Consente di recuperare lo studente con annessi tutti i suoi piani di studio (approvato e in approvazione) se esistono.
	 * @param la matricola dello studente di cui vogliamo recuparare il piano.
	 */
	@Override
	public Studente getStudentWithStudyPlan(String matricola) throws CommandNotValidException {
		return gestoreDB.getStudentWithStudyPlan(matricola);
	}

}
