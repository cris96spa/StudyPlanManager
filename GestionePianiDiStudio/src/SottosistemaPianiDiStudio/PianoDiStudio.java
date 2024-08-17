package SottosistemaPianiDiStudio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import SottosistemaStudenti.Studente;
import Util.NotValidApprovationDataException;

public class PianoDiStudio {

	private int ID;
	private Stato stato; 
	private Date dataApprovazione;
	private Studente studente;
	private CorsoDiStudio corso;
	private List<Insegnamento> insegnamenti;
	
	public enum Stato {
		approvato, inApprovazione, nonApprovato, sostituito;
	}
	
	public PianoDiStudio(int id, PianoDiStudio.Stato status, Date appDate) {
		ID = id;
		stato = status;
		insegnamenti = new ArrayList<Insegnamento>();
		corso = null;
		dataApprovazione = appDate;
		studente = null;
	}
	
	public PianoDiStudio(List<Insegnamento> teachings, CorsoDiStudio course) {
		ID = -1;
		stato = Stato.nonApprovato;
		insegnamenti = teachings;
		corso = course;
		dataApprovazione = null;
		studente = null;
	}
	
	public PianoDiStudio(int id, PianoDiStudio.Stato status, Date appDate, List<Insegnamento> teachings) {
		ID = id;
		stato = status;
		insegnamenti = teachings;
		corso = null;
		dataApprovazione = appDate;
		studente = null;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		if(this.ID != ID)
			this.ID = ID;
	}

	/*
	 * In fase di approvazione fare un set sulla data
	 */
	public Date getApprovationDate() {
		return dataApprovazione;
	}

	public void setApprovationDate(Date appDate) throws NotValidApprovationDataException {
		if(!appDate.after(new Date()))
			dataApprovazione = appDate;
		else throw new NotValidApprovationDataException("Approvation Date is after then today");
	}
	
	public Studente getStudent() {
		return studente;
	}

	public List<Insegnamento> getTeachings() {
		return insegnamenti;
	}

	public CorsoDiStudio getCourse() {
		return corso;
	}

	/*
	 * Caso particolare, poiché lo studente ha 2 diversi piani di studio
	 * non è possibile mantenere qui la consistenza dei collegamenti. Essa dovrà
	 * essere garantita dal lato studente
	 */
	public void setStudent(Studente s) {
		if(s != studente) 
			studente = s;
	}

	public Stato getStatus() {
		return stato;
	}
	
	public void setStatus(Stato stato) {
		if(this.stato != stato)
			this.stato = stato;
	}

	public void setTeaching(List<Insegnamento> insegnamenti) {
		if(this.insegnamenti != insegnamenti)
			this.insegnamenti = insegnamenti;
	}

	public void setCourse(CorsoDiStudio c) {
		if(corso != c)
			corso = c;
	}

	public void addTeaching(Insegnamento i) {
		if(i != null && !insegnamenti.contains(i)) {
			insegnamenti.add(i);
			Collections.sort(insegnamenti, new Comparator<Insegnamento>() {
				
				@Override
				public int compare(Insegnamento o1, Insegnamento o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
		}
	}

	public void removeTeaching(Insegnamento i) {
		if(i != null && insegnamenti.contains(i))
			insegnamenti.remove(i);
	}

	/**
	 * Consente calcolare il totale dei CFU relativi agli insegnamenti della lista del piano.
	 */
	public int getTotalCFU() {
		int cfu = 0;
		for(Insegnamento i : insegnamenti) {
			cfu += i.getCfu();
		}
		return cfu;
	}
	
	@Override
	public String toString() {
		return "PianoDiStudio [ID=" + ID + ", stato=" + stato + ", dataApprovazione=" + dataApprovazione
				+ ", studente=" + (studente != null? studente.getSerialNumber() : null )+ ", corso=" + (corso != null? (corso.getName() +", "+ corso.getLevel()) : null) + ", insegnamenti=" + insegnamenti + "]";
	}	
}
