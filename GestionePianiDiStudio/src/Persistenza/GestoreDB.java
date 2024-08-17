package Persistenza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import SottosistemaPianiDiStudio.CorsoDiStudio;
import SottosistemaPianiDiStudio.CorsoMagistrale;
import SottosistemaPianiDiStudio.CorsoTriennale;
import SottosistemaPianiDiStudio.Insegnamento;
import SottosistemaPianiDiStudio.PianoDiStudio;
import SottosistemaPianiDiStudio.PianoDiStudio.Stato;
import SottosistemaStudenti.Iscrizione;
import SottosistemaStudenti.Studente;
import Util.Constants;
/**
 * Classe implementata per la gestione dell'accesso alla base di dati.
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class GestoreDB {

	private static GestoreDB instance;
	private Studente studente;
	private List<PianoDiStudio> piani;
	private List<Insegnamento> insegnamenti;
	private Connection connection;
	private Statement statement;
	
	/**
	 * Costruttore privato
	 */
	private GestoreDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//cambiare in base al database
			String url = "jdbc:mysql://127.0.0.1:3306/pds?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
				
			this.connection = DriverManager.getConnection(url,"root", "");
			if(!connection.isClosed())
				System.out.println("Succesfully connected to MySQL server");
			this.statement= this.connection.createStatement();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per l'implementazione del pattern Singleton
	 * @return
	 */
	public static GestoreDB getInstance() {
		if(instance==null)
			instance=new GestoreDB();
		return instance;
	}

	
	/*
	 * Qualora la matricola inserita corrispondesse con quella dello studente precedentemente ottenuto
	 * sarà evitato l'accesso al DB.
	 */
	/**
	 * Consente di recuperare lo studente collegandolo al suo piano di studio e alla sua iscrizione.
	 * Qualora la matricola inserita corrispondesse con quella dello studente precedentemente ottenuto
	 * sarà evitato l'accesso al DB.
	 * @param matricola matricola dello studente che vuole essere recuperato.
	 * @return
	 */
	public Studente getStudent(String matricola) {
		if((studente != null && studente.getSerialNumber() != matricola) || (studente == null))  {
			String nome, cognome;
			Date dataNascita;
			studente = null;
			ResultSet rs = null;
			try{
				rs = statement.executeQuery("select * from studenti where matricola = '"+matricola+"'");
				while(rs.next()){
					nome = rs.getString("nome");
					cognome = rs.getString("cognome");
					dataNascita = rs.getDate("dataDiNascita");
					
					studente = new Studente(matricola, nome, cognome, dataNascita);
					setStudentRegistration();
					setStudentStudyPlan();
					
				}
				
			}catch(Exception exc) {
				exc.printStackTrace();
			} finally {
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return studente;
	}
	
	/**
	 * Metodo privato invocato per recuparare il piano di studio dello studente
	 */
	private void setStudentStudyPlan() {
		String matricola = studente.getSerialNumber();
		int idPiano = 0;
		Date appDate = null;
		String statoS = "";
		PianoDiStudio.Stato stato = Stato.nonApprovato;
		String query = "select * from pianiDiStudio where studente = '"+matricola+"'";
		try{
			PreparedStatement prepStament = connection.prepareStatement(query);
			ResultSet rs = prepStament.executeQuery();
			while(rs.next()){
				statoS = rs.getString("stato");
				idPiano = rs.getInt("IDPiano");
				appDate = rs.getDate("dataApprovazione");
				
				PianoDiStudio piano = null;
				switch(statoS) {
					case "approvato":
						stato = Stato.approvato;
						piano = new PianoDiStudio(idPiano, stato, appDate);
						studente.setStudyPlan(piano);
						break;
					case "inApprovazione":
						stato = Stato.inApprovazione;
						piano = new PianoDiStudio(idPiano, stato, appDate);
						studente.setOnApprovationStudyPlan(piano);
						break;
					default :
						break;
				}
				if(piano != null && studente.getRegistration()!= null) {
					piano.setCourse(studente.getRegistration().getCourse());
				}
			}
			rs.close();
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Metodo privato usato per recuperare l'iscrizione al corso di studio dello studente
	 */
	private void setStudentRegistration() {
		
		String matricola = studente.getSerialNumber(), nomeCorso, tipoCorso, annoAcc;
		int annoCorso = 0;
		Date iscrDate = null;
		String query = "select * from iscrizioni where studente = '"+matricola+"'";
		try{
			PreparedStatement prepStament = connection.prepareStatement(query);
			ResultSet rs = prepStament.executeQuery();
			while(rs.next()){
				nomeCorso = rs.getString("nomeCorso");
				tipoCorso = rs.getString("tipoCorso");
				annoAcc = rs.getString("annoAccademico");
				annoCorso = rs.getInt("annoCorso");
				iscrDate = rs.getDate("dataIscrizione");
				CorsoDiStudio corso = getCourse(nomeCorso, tipoCorso);
				
				Iscrizione iscrizione = new Iscrizione(annoAcc, annoCorso, iscrDate);
				studente.setRegistration(iscrizione);
				iscrizione.setCourse(corso);
				
			}
			rs.close();
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Metodo privato usato per settare gli insegnamenti del corso di studio
	 * @param corso
	 */
	private void setCourseTeachings(CorsoDiStudio corso) {
		if(corso != null) {
			String nomeCorso = corso.getName();
			String tipoCorso = corso.getLevel()== 1 ? "triennale" : "magistrale";
			String codIns = "";
			if(insegnamenti == null)
				getTeachings();
			String query = "select * from corsiinsegnamenti where nomeCorso = '"+nomeCorso+"' and tipoCorso = '"+tipoCorso+"'";
			try{
				PreparedStatement prepStament = connection.prepareStatement(query);
				ResultSet rs = prepStament.executeQuery();
				while(rs.next()){
					codIns = rs.getString("insegnamento");
					corso.addTeaching(getTeachingByCode(codIns));
				}
				rs.close();
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}
		}
		
	}

	/**
	 * Metodo privato usato per settare il corso di studio dell'iscrizione
	 * @param nomeCorso
	 * @param tipoCorso
	 * @return
	 */
	private CorsoDiStudio getCourse(String nomeCorso, String tipoCorso) {
		
		Date creationDate = null;
		String query = "select * from corsiDiStudio where nome = '"+nomeCorso +"' and tipo = '"+tipoCorso+"'";
		CorsoDiStudio corso = null;
		try{
			PreparedStatement prepStament = connection.prepareStatement(query);
			ResultSet rs = prepStament.executeQuery();
			while(rs.next()){
				creationDate = rs.getDate("dataCreazione");
				
				if(tipoCorso.equalsIgnoreCase("triennale"))
					corso = new CorsoTriennale(nomeCorso, creationDate);
				else if(tipoCorso.equalsIgnoreCase("magistrale"))
					corso = new CorsoMagistrale(nomeCorso, creationDate);
				
				setCourseTeachings(corso);
			}
			rs.close();
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return corso;
	}

	/**
	 * Metodo invocato per recuperare lo studente con i suoi piani di studio
	 * @param matricola
	 * @return
	 */
	public Studente getStudentWithStudyPlan(String matricola) {
		getTeachings();			//carico tutti gli insegnamenti in memoria per poterli prendere direttamente e avvolorare il piano di studio
		studente = getStudent(matricola);
		if(studente != null) {
			setStudyPlanTeachings(studente.getStudyPlan());
			setStudyPlanTeachings(studente.getOnApprovationStudyPlan());
		}
		return studente;
	}


	/**
	 * Metodo usato per settare gli insegnamenti del piano di studio
	 * @param studyPlan
	 */
	private void setStudyPlanTeachings(PianoDiStudio studyPlan) {
		if(studyPlan != null) {
			String query = "select * from pianoinsegnamenti where piano = '"+studyPlan.getID()+"'";
			String codIns = "";
			try{
				PreparedStatement prepStament = connection.prepareStatement(query);
				ResultSet rs = prepStament.executeQuery();
				while(rs.next()){
					codIns = rs.getString("insegnamento");
					studyPlan.addTeaching(getTeachingByCode(codIns));
				}
				rs.close();
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	/**
	 * Consente di recuperare dalla base di dati tutti gli insegnamenti che possono essere inseriti in un 
	 * piano di studio in fase di presentazione
	 * @return
	 */
	public List<Insegnamento> getTeachings() {
		if(insegnamenti == null) {
			String id = "", nome = "", ssd = "";
			int cfu = 0;
			insegnamenti = new ArrayList<Insegnamento>();
			ResultSet rs = null;
			String query = "select * from insegnamenti";
			try{
				PreparedStatement prepStament = connection.prepareStatement(query);
				rs = prepStament.executeQuery();
				while(rs.next()){
					id = rs.getString("idInsegnamento");
					nome = rs.getString("nome");
					ssd = rs.getString("SettoreScientifico");
					cfu = rs.getInt("CFU");
					
					Insegnamento i = new Insegnamento(id, nome, cfu, ssd);
					addTeaching(i);
				}
				setTeachingsPropedeutics();
				
			}catch(Exception exc) {
				exc.printStackTrace();
			} finally {
				if(rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			Collections.sort(insegnamenti, new Comparator<Insegnamento>() {
	
				@Override
				public int compare(Insegnamento o1, Insegnamento o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
		}
		return insegnamenti;
	}

	/**
	 * Consente di aggiungere un insegnamento alla collezione
	 * @param i
	 */
	private void addTeaching(Insegnamento i) {
		if(insegnamenti == null)
			insegnamenti = new ArrayList<Insegnamento>();
		if(i != null && !insegnamenti.contains(i))
			insegnamenti.add(i);
	}
	
	/**
	 * Consente di aggiungere un piano di studio alla collezione
	 * @param p
	 */
	public void addStudyPlan(PianoDiStudio p) {
		if(piani == null)
			getStudyPlansList();
		if(p != null && !piani.contains(p))
			piani.add(p);
	}

	/**
	 * Consente di ottenere un insegnamento dato il suo codice.
	 * @param code
	 * Poiché gli insegnamenti sono già stati portati in memoria, in questo modo sarà possibile aggiungere
	 * le varie propedeuticità senza 
	 * @return
	 */
	private Insegnamento getTeachingByCode(String code) {
		boolean found = false;
		int i = 0;
		Insegnamento ins = null;
		if(insegnamenti != null) {
			while(i < insegnamenti.size() && !found) {
				if(insegnamenti.get(i).getID().equalsIgnoreCase(code)) {
					ins = insegnamenti.get(i);
					found = true;
				}
				else i++;
			}
		}
		return ins;
	}
	
	/**
	 * Metodo privato usato settare le propedeuticità degli insegnamenti
	 */
	private void setTeachingsPropedeutics() {
		if(insegnamenti != null) {
			for(Insegnamento i : insegnamenti) {
				String id = i.getID();
				String idProp = "";
				String query = "select * from propedeuticita where insegnamento = '"+id+"'";
				try{
					PreparedStatement prepStament = connection.prepareStatement(query);
					ResultSet rs = prepStament.executeQuery();
					while(rs.next()){
						idProp = rs.getString("propedeuticita");
						Insegnamento prop = getTeachingByCode(idProp);
						i.addPropedeutic(prop);	
					}
					rs.close();
					
				} catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Consente di recuperare dalla base di dati tutti gli insegnamenti che possono essere inseriti in un 
	 * piano di studio in fase di presentazione
	 * @return
	 */
	public List<PianoDiStudio> getStudyPlansList() {
				String matricola = "";
				piani = new ArrayList<PianoDiStudio>();
				ResultSet rs = null;
				String query = "select * from pianidistudio where stato = 'inApprovazione'";
				try{
					PreparedStatement prepStament = connection.prepareStatement(query);
					rs = prepStament.executeQuery();
					while(rs.next()){
						matricola = rs.getString("studente");
						studente = getStudentWithStudyPlan(matricola);
						PianoDiStudio p = studente.getOnApprovationStudyPlan();
						addStudyPlan(p);
					}
					setTeachingsPropedeutics();
					
				}catch(Exception exc) {
					exc.printStackTrace();
				} finally {
					if(rs != null)
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
				Collections.sort(piani, new Comparator<PianoDiStudio>() {
					@Override
					public int compare(PianoDiStudio o1, PianoDiStudio o2) {
						return o1.getID() - o2.getID();
					}
				});
		
		return piani;
	}

	/**
	 * Consente di rimuovere un piano di studio
	 * @param piano
	 */
	public void removeStudyPlan(PianoDiStudio piano) {
		if(piano != null)
			piani.remove(piano);
	}


	/**
	 * Metodo utilizzato per aggiornare sul DB lo stato del piano di studio
	 * @param studente
	 * @return
	 */
	public boolean refuseStudyPlan(Studente studente) {
		int res = 0;
		if(studente != null) {
			this.studente = studente;
			PianoDiStudio piano = studente.getOnApprovationStudyPlan();
			int idPiano = 0;
			if(piano != null)
				idPiano = piano.getID();
		
			String query = "UPDATE pianidistudio SET stato='nonApprovato' WHERE IDPiano ='"+ idPiano+"'";
			try{
				PreparedStatement prepStament = connection.prepareStatement(query);
				res = prepStament.executeUpdate();
				if(res != 0) {
					piano.setStatus(PianoDiStudio.Stato.nonApprovato);
					return true;
				}
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Metodo utilizzato per aggiornare lo stato del piano di studio precedente sul DB
	 * @param studente
	 * @return
	 */
	public boolean changeStudyPlan(Studente studente) {
		int res = 0;
		if(studente != null) {
			this.studente = studente;
			PianoDiStudio piano = studente.getStudyPlan();
			int idPiano = 0;
			if(piano != null)
				idPiano = piano.getID();
		
			String query = "UPDATE pianidistudio SET stato='sostituito' WHERE IDPiano ='"+ idPiano+"'";
			try{
				PreparedStatement prepStament = connection.prepareStatement(query);
				res = prepStament.executeUpdate();
				if(res != 0) {
					piano.setStatus(Stato.sostituito);
					return true;
				}
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Metodo utilizzato per la validazione del piano di studio
	 * @param studente
	 * @return
	 */
	public boolean validateStudyPlan(PianoDiStudio pianoDiStudio) {
		int res = 0;
		int idPiano = 0;
		if(pianoDiStudio != null) {
			studente = pianoDiStudio.getStudent();
			idPiano = pianoDiStudio.getID();
			String appDate = Constants.SD.format(new Date());
		
			String query = "UPDATE pianidistudio SET stato='approvato', dataApprovazione = '"+appDate+"' WHERE IDPiano ='"+ idPiano+"'";
			try{
				PreparedStatement prepStament = connection.prepareStatement(query);
				res = prepStament.executeUpdate();
				if(res != 0) {
					pianoDiStudio.setStatus(Stato.approvato);
					pianoDiStudio.setApprovationDate(new Date());
					return true;
				}
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Consente di inserire il piano di studio presentato nel DB
	 * @param pianoDiStudio
	 * @return
	 */
	public boolean insertStudyPlan(PianoDiStudio pianoDiStudio) {
		int res = 0;
		if(pianoDiStudio != null) {
			studente = pianoDiStudio.getStudent();
			String appDate = pianoDiStudio.getStatus() == Stato.approvato ? "'"+Constants.SD.format(new Date())+"'" : "NULL";
			CorsoDiStudio corso = pianoDiStudio.getCourse();
			String nomeCorso = corso.getName();
			int pianoID = calculateStudyPlanID();
			String tipoCorso = corso.getLevel()== 1 ? "triennale" : "magistrale";
			
			try{
				String query = "INSERT INTO pianidistudio (IDPiano, stato, dataApprovazione, nomeCorso, tipoCorso, studente) VALUES ('"+pianoID+"', '"+pianoDiStudio.getStatus()+"', "+appDate+", '"+nomeCorso+"', '"+tipoCorso+"', '"+studente.getSerialNumber()+"')";
				PreparedStatement prepStament = connection.prepareStatement(query);
				res = prepStament.executeUpdate();
				if(res != 0) {
					pianoDiStudio.setID(pianoID);
					insertStudyPlanTeachings(pianoDiStudio);
					return true;
				}
				
			} catch(Exception exc) {
				exc.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Consente di ottenere L'ID del prossimo piano da inserire nel DB in modo da poter mantenere la consistenza dell'ID incrementale
	 * @return
	 */
	private int calculateStudyPlanID() {
		ResultSet res = null;
		try{
			String query = "Select IDPiano from pianidistudio order by IDPiano";
			PreparedStatement prepStament = connection.prepareStatement(query);
			res = prepStament.executeQuery();
			boolean op = res.last();
			if(op)
				return res.getInt("IDPiano")+1;
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return 0;
	}

	/**
	 * Consente di inserire gli insegnamenti del piano nel DB
	 * @param piano
	 */
	private void insertStudyPlanTeachings(PianoDiStudio piano) {
		if(piano != null){
			Iterator<Insegnamento> insegnamenti = piano.getTeachings().iterator();
			Insegnamento insegnamento = null;
			String query ="INSERT INTO pianoinsegnamenti (piano, insegnamento) VALUES ('"+piano.getID()+"', ?)";
			while(insegnamenti.hasNext()) {
				insegnamento = insegnamenti.next();
				if(insegnamento != null) {
					try{
						PreparedStatement prepStament = connection.prepareStatement(query);
						prepStament.setString(1, insegnamento.getID());
						prepStament.executeUpdate();
						
					} catch(Exception exc) {
						exc.printStackTrace();
					}
				}
			}
		}
	}

}
