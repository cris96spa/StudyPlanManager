package SottosistemaPianiDiStudio;

import Persistenza.GestoreDB;
import SottosistemaPianiDiStudio.PianoDiStudio.Stato;
import SottosistemaStudenti.Studente;

/**
 * Classe per l'applicazione delle regole al piano di studio presentato
 * Si tratta del Creator delle regole (Design Pattern - GRASP).
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class CommandRegole extends CommandPianiDiStudio {

	private GestoreDB gestoreDB;
	private Regola regolaCrediti, regolaInsegnamenti, regolaEsami, regolaPropedeuticità;
	
	public CommandRegole() {
		gestoreDB = GestoreDB.getInstance();
	}
	
	/**
	 * Metodo invocato per la verifica delle regole
	 */
	@Override
	public PianoDiStudio.Stato checkApprovation(Studente studente) {
		PianoDiStudio.Stato esito = Stato.approvato;
		PianoDiStudio piano = null;
		CorsoDiStudio corso = null;
		if(studente != null && studente.getRegistration() != null) {
			piano = studente.getOnApprovationStudyPlan();
			corso = studente.getRegistration().getCourse();
			regolaCrediti = new RegolaCrediti(corso);
			regolaInsegnamenti = new RegolaInsegnamenti(corso);
			regolaEsami = new RegolaEsami(corso);
			regolaPropedeuticità = new RegolaPropedeuticita(corso);
	
			System.err.println("Regola Crediti: "+(regolaCrediti.checkRule(piano) ? "I crediti inseriti: "+piano.getTotalCFU()+" rispettano quelli vincolati dal piano: "+piano.getCourse().getCfu() : "I crediti inseriti: "+piano.getTotalCFU()+" non rispettano quelli vincolati dal piano: "+piano.getCourse().getCfu()));
			if(!regolaCrediti.checkRule(piano)) {
				return Stato.nonApprovato;
			}
			
			System.err.println("Regola Esami: "+(regolaEsami.checkRule(piano) ? "Sono stati inseriti tutti gli insegnamenti per cui è stato sostenuto un esame" : "Non sono stati inseriti tutti gli insegnamenti per cui è stato sostenuto un esame."));
			if(!regolaEsami.checkRule(piano))
				return Stato.nonApprovato;
			
			System.err.println("Regola Insegnamenti: "+(regolaInsegnamenti.checkRule(piano) ? "Tutti gli insegnamenti inseriti appartengono al corso di laurea. Approvazione automatica." : "Non tutti gli insegnamenti inseriti appartengono al corso di laurea. Approvazione manuale."));
			if(!regolaInsegnamenti.checkRule(piano))
				esito = Stato.inApprovazione;
			
			System.err.println("Regola propedeuticita': "+(regolaPropedeuticità.checkRule(piano) ? "Le propedeuticità sono rispettate" : "Le propedeuticita' non sono state rispettate"));
			if(!regolaPropedeuticità.checkRule(piano))
				return Stato.nonApprovato;
		}
		
		return esito;
	}

}
