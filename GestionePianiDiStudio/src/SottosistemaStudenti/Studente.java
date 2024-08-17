package SottosistemaStudenti;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SottosistemaPianiDiStudio.CorsoDiStudio;
import SottosistemaPianiDiStudio.Insegnamento;
import SottosistemaPianiDiStudio.PianoDiStudio;

public class Studente {

	private String matricola;
	private List<Esame> esami;
	private PianoDiStudio pianoInApprovazione;
	private PianoDiStudio pianoDiStudio;
	private Iscrizione iscrizione;
	private String nome;
	private String cognome;
	private Date dataNascita;

	public Studente(String sNumber, String name, String surname, Date birthDate) {
		matricola = sNumber;
		nome = name;
		cognome = surname;
		esami = new ArrayList<Esame>();
		dataNascita = birthDate;
		pianoDiStudio = null;
		pianoInApprovazione = null;
	}
	
	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public String getSurname() {
		return cognome;
	}

	public void setSurname(String surname) {
		this.cognome = surname;
	}
	
	public Date getbirthDate() {
		return dataNascita;
	}

	public String getSerialNumber() {
		return matricola;
	}

	public PianoDiStudio getStudyPlan() {
		return pianoDiStudio;
	}

	public Iscrizione getRegistration() {
		return iscrizione;
	}

	public List<Esame> getExams() {
		return esami;
	}

	public PianoDiStudio getOnApprovationStudyPlan() {
		return pianoInApprovazione;
	}
	
	/**
	 * Consente di settare allo studente un nuovo piano di studio se questo è diverso da quello precedente completado
	 * i collegamenti e scollegando gli oggetti da distruggere.
	 * @param p
	 */

	public void setStudyPlan(PianoDiStudio p) {
		if(p != pianoDiStudio) {
			PianoDiStudio oldPiano = pianoDiStudio;
			pianoDiStudio = p;
			if(pianoDiStudio != null)
				pianoDiStudio.setStudent(this);
			if(oldPiano != null)
				oldPiano.setStudent(null);
		}
	}

	/**
	 * Consente di settare allo studente l'iscrizione al corso di studi completando tutti
	 * i collegamenti e scollegando gli oggetti da distruggere.
	 * @param 
	 */
	public void setRegistration(Iscrizione i) {
		if(i != iscrizione) {
			Iscrizione oldIsc = iscrizione;
			iscrizione = i;
			if(iscrizione != null ) {
				iscrizione.setStudent(this);
			}
			if(oldIsc != null) {
				oldIsc.setStudent(null);
			}
		}
	}

	/**
	 * Consente di settare allo studente un nuovo piano di studio se questo è diverso da quello precedente completado
	 * i collegamenti e scollegando gli oggetti da distruggere.
	 * @param p
	 */
	public void setOnApprovationStudyPlan(PianoDiStudio p) {
		if(p != pianoInApprovazione) {
			PianoDiStudio oldPiano = pianoInApprovazione;
			pianoInApprovazione = p;
			if(pianoInApprovazione != null)
				pianoInApprovazione.setStudent(this);
			if(oldPiano != null)
				oldPiano.setStudent(null);
		}
	}

	public void addExam(Esame e) {
		if(e!= null && !esami.contains(e)) {
			esami.add(e);
			e.setStudent(this);
		}
	}

	public void removeExam(Esame e) {
		if(e!= null && esami.contains(e)) {
			esami.remove(e);
			e.setStudent(null);
		}
	}

	public Esame getExam(int pos) {
		return esami.get(pos);
	}

	/**
	 * Consente allo studente di presentare un nuovo piano di studio
	 * @param p
	 */
	public void presentStudyPlan(List<Insegnamento> insegnamenti) {
		CorsoDiStudio corso = iscrizione.getCourse();
		PianoDiStudio piano = new PianoDiStudio(insegnamenti, corso);
		setOnApprovationStudyPlan(piano);
	}

	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", esami=" + esami + ", pianoInApprovazione=" + pianoInApprovazione
				+ ", pianoDiStudio=" + pianoDiStudio + ", iscrizione=" + iscrizione + ", nome=" + nome + ", cognome="
				+ cognome + ", dataNascita=" + dataNascita + "]";
	}
}
