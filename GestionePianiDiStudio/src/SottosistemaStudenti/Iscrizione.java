package SottosistemaStudenti;

import java.util.Date;

import SottosistemaPianiDiStudio.CorsoDiStudio;
import Util.NotValidApprovationDataException;

public class Iscrizione {

	private Date dataIscrizione;
	private int annoCorso;
	private String annoAccademico;
	private CorsoDiStudio corso;
	private Studente studente;

	public Iscrizione(Studente s, CorsoDiStudio c) {
		studente = s;
		studente.setRegistration(this);
		corso = c;
		corso.addRegistration(this);
	}
	
	public Iscrizione(String annoAcc, int annoCorso, Date iscrDate) {
		annoAccademico = annoAcc;
		this.annoCorso = annoCorso;
		setRegistrationDate(iscrDate);
	}

	
	
	public Date getRegistrationDate() {
		return dataIscrizione;
	}

	public void setRegistrationDate(Date dataIscrizione) {
		if(!dataIscrizione.after(new Date()))
			this.dataIscrizione = dataIscrizione;
		else
			try {
				throw new NotValidApprovationDataException("La data inserita è superiore a quella odierna");
			} catch (NotValidApprovationDataException e) {
				System.err.println(e.getMessage());
			}	
	}

	public int getCourseYear() {
		return annoCorso;
	}

	public void setCourseYear(int annoCorso) {
		this.annoCorso = annoCorso;
	}

	public String getAccademicYear() {
		return annoAccademico;
	}

	public void setAccademicYear(String annoAccademico) {
		this.annoAccademico = annoAccademico;
	}

	public CorsoDiStudio getCourse() {
		return corso;
	}

	public Studente getStudent() {
		return studente;
	}

	public void setStudent(Studente s) {
		if(s != studente) {
			Studente oldStud = studente;
			studente = s;
			if(studente != null ) {
				studente.setRegistration(this);
			}
			if(oldStud != null) {
				oldStud.setRegistration(null);
			}
		}
	}

	public void setCourse(CorsoDiStudio c) {
		if(c != corso) {
			CorsoDiStudio oldCorso = corso;
			corso = c;
			if(corso != null) 
				corso.addRegistration(this);
			
			if(oldCorso != null)
				oldCorso.removeRegistration(this);	
		}
	}

	@Override
	public String toString() {
		return "Iscrizione [dataIscrizione=" + dataIscrizione + ", annoCorso=" + annoCorso + ", annoAccademico="
				+ annoAccademico + ", corso=" + (corso!= null ? (corso.getName() +", "+ "livello = "+ corso.getLevel()) : null)+ ", studente=" 
				+ (studente != null ? studente.getSerialNumber() : null )+ "]";
	}
}
