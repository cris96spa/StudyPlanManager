package SottosistemaPianiDiStudio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SottosistemaStudenti.Iscrizione;

public abstract class CorsoDiStudio {

	private String nome;
	private Date dataCreazione;
	protected int cfu;
	protected int livello;
	private List<Insegnamento> insegnamenti;
	private List<Iscrizione> iscrizioni;
	
	/**
	 * Costruttore della classe CorsoDiStudio
	 * Il livello, così come i cfu saranno settati sulla base della classe specializzata scelta.
	 * @param name univoco per il livello
	 * @param creationDate data di creazione dell'istanza
	 */
	public CorsoDiStudio(String name, Date creationDate) {
		nome = name;
		dataCreazione = creationDate;
		insegnamenti = new ArrayList<Insegnamento>();
		iscrizioni = new ArrayList<Iscrizione>();
	}
	
	public String getName() {
		return nome;
	}

	public Date getCreationDate() {
		return dataCreazione;
	}

	public int getCfu() {
		return cfu;
	}

	public int getLevel() {
		return livello;
	}

	/**
	 * Restituisce la lista di insegnamenti fissata per lo specifico corso:
	 * da essa e da altre regole, dipenderà l'approvazione automatica del piano di studio 
	 * per gli studenti iscritti a tale corso
	 * @return insegnamenti
	 */
	public List<Insegnamento> getTeaching() {
		return insegnamenti;
	}	
	
	public void setName(String name) {
		nome = name;
	}

	public void setCreationDate(Date creationDate) {
		dataCreazione = creationDate;
	}

	public void setTeachings(List<Insegnamento> insegnamenti) {
		this.insegnamenti = insegnamenti;
	}

	public void setRegistrations(List<Iscrizione> iscrizioni) {
		this.iscrizioni = iscrizioni;
	}

	/**
	 * Consente di aggiungere insegnamenti alla lista del corso.
	 * Se l'insegnamento passato non è presente nella lista allora viene effettivamente aggiunto.
	 * @param l'insegnamento da aggiungere
	 */
	public void addTeaching(Insegnamento i) {
		if(i!= null && !insegnamenti.contains(i))
			insegnamenti.add(i);
	}

	/**
	 * Consente di rimuovere insegnamenti dalla lista del corso.
	 * Se l'insegnamento passato è presente nella lista allora viene effettivamente rimosso.
	 * @param l'insegnamento da rimuovere
	 */
	public void removeTeaching(Insegnamento i) {
		if(i!= null && insegnamenti.contains(i))
			insegnamenti.remove(i);
	}

	/**
	 * Consente di aggiungere un'iscrizione alla lista del corso.
	 * Se l'iscrizione passata non è presente nella lista allora viene effettivamente aggiunta.
	 * All'iscrizione viene settato il corso corrispondente per questioni di consistenza e allineamento
	 * @param l'iscrizione da aggiungere
	 */
	public void addRegistration(Iscrizione i) {
		if(i!= null && !iscrizioni.contains(i)) {
			iscrizioni.add(i);
			i.setCourse(this);
		}
	}
	
	/**
	 * Consente di rimuovere un'iscrizione alla lista del corso.
	 * Se l'iscrizione passata è presente nella lista allora viene effettivamente rimossa.
	 * All'iscrizione viene tolto il corso corrispondente per questioni di consistenza e allineamento
	 * @param l'iscrizione da rimuovere
	 */
	public void removeRegistration(Iscrizione i) {
		if(i!= null && iscrizioni.contains(i)) {
			iscrizioni.remove(i);
			i.setCourse(null);
		}
	}

	@Override
	public String toString() {
		return "CorsoDiStudio [nome=" + nome + ", dataCreazione=" + dataCreazione + ", cfu=" + cfu + ", livello="
				+ livello + ", insegnamenti=" + insegnamenti + ", iscrizioni=" + iscrizioni
				+ "]";
	}
}
