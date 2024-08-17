package SottosistemaStudenti;

import Util.CommandNotValidException;


/**
 * Classe astratta Command del SottosistemaStudenti
 * Prevede una pre implementazione dei metodi che poi saranno socrascritti nei Command specializzati
 */
public abstract class CommandStudenti {
	
	public Studente getStudent(String matricola) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}
	
	public Studente getStudentWithStudyPlan(String matricola) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

}
