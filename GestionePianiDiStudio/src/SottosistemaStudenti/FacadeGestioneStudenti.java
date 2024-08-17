package SottosistemaStudenti;

import Util.CommandNotValidException;

public class FacadeGestioneStudenti {

	private static FacadeGestioneStudenti instance;
	private CommandStudenti commandMatricola;
	
	private FacadeGestioneStudenti() {
		commandMatricola = new CommandMatricola();
	}
	
	/**
	 * Metodo static per il DP Singleton
	 * */
	public static FacadeGestioneStudenti getInstance() {
		if(instance == null)
			instance = new FacadeGestioneStudenti();
		return instance;
	}

	/**
	 * Consente di recuperare lo studente che possiede la matricola passata
	 * @param la matricola dello studente di cui vogliamo recuparare il piano.
	 * @return matricola
	 */
	public Studente getStudent(String matricola) throws CommandNotValidException {
		return commandMatricola.getStudent(matricola);
	}
	
	/**
	 * Consente di recuperare lo studente con annessi tutti i suoi piani di studio (approvato e in approvazione) se esistono.
	 * @param la matricola dello studente di cui vogliamo recuparare il piano.
	 * @return studente
	 */
	public Studente getStudentWithStudyPlan(String matricola) throws CommandNotValidException {
		return commandMatricola.getStudentWithStudyPlan(matricola);
	}
}
