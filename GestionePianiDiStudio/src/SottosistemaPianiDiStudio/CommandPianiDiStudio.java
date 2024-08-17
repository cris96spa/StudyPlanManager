package SottosistemaPianiDiStudio;

import java.util.List;

import SottosistemaStudenti.Studente;
import Util.CommandNotValidException;

/**
 * Classe astratta che definisce l'implementazione di base dei metodi invocabili
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public abstract class CommandPianiDiStudio {

	public boolean removeStudyPlan(Studente studente) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public List<PianoDiStudio> getStudyPlansList() throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public void removeStudyPlan(Studente studente, int ID) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public boolean validateStudyPlan(Studente studente) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public PianoDiStudio.Stato checkApprovation(Studente studente) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public List<Insegnamento> getTeachingsList() throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public boolean presentStudyPlan(PianoDiStudio p) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public boolean refuseStudyPlan(Studente studente) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
	}

	public boolean approveStudyPlan(PianoDiStudio piano) throws CommandNotValidException {
		throw new CommandNotValidException("Command not valid");
		
	}
}
