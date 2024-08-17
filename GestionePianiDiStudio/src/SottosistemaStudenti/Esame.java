package SottosistemaStudenti;

import java.util.Date;

import SottosistemaPianiDiStudio.Insegnamento;
import Util.InvalidMarkException;
import Util.NotValidApprovationDataException;

public class Esame {

	private int voto;
	private Studente studente;
	private Date data;
	private Insegnamento insegnamento;

	/*
	 * Mandenere saldo il collegamento con il piano di studio in fase di 
	 * registrazione dell'esame. 
	 */
	public Esame(Studente s, Insegnamento i) {
		studente = s;
		studente.addExam(this);
		insegnamento = i;
	}
	
	public void setStudent(Studente s) {
		if(s != studente) {
			Studente oldStud = studente;
			studente = s;
			if(studente != null ) {
				studente.addExam(this);
			}
			if(oldStud != null) {
				oldStud.removeExam(this);
			}
		}
	}

	public void setTeaching(Insegnamento i) {
		if(i != insegnamento) {
			insegnamento = i;
		}
	}

	public Studente getStudent() {
		return studente;
	}

	public int getMark() {
		return voto;
	}
	
	public void setMark(int mark) {
		if(mark >= 18)
			voto = mark;
		else
			try {
				throw new InvalidMarkException("Il voto inserito non è valido");
			} catch (InvalidMarkException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
	}
	
	public Date getDate() {
		return data;
	}
	
	public void setDate(Date date) {
		if(!date.after(new Date()))
			this.data = date;
		else
			try {
				throw new NotValidApprovationDataException("La data inserita è superiore a quella odierna.");
			} catch (NotValidApprovationDataException e) {
				System.err.println(e.getMessage());
			}	
	}
	
	public Insegnamento getTeaching() {
		return insegnamento;
	}
	
	@Override
	public String toString() {
		return "Esame [voto=" + voto + ", studente=" + studente != null ? studente.getSerialNumber() : studente + ", data=" + data + ", insegnamento=" + insegnamento
				+ "]";
	}
	
}
