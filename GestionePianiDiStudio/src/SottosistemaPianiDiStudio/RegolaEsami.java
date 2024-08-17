package SottosistemaPianiDiStudio;

import java.util.ArrayList;
import java.util.List;

import SottosistemaStudenti.Esame;

public class RegolaEsami extends Regola {

	public RegolaEsami(CorsoDiStudio c) {
		super(c);
	}

	
	/**
	 * Consente di verificare che gli insegnamenti degli esami sostenuti dallo studente siano stati insertiti nel nuovo piano.
	 * @return true se ci sono, false altrimenti
	 * @param piano di studi da approvare
	 */
	@Override
	public boolean checkRule(PianoDiStudio p) {
		List<Esame> esami = p.getStudent().getExams();
		List<Insegnamento> insegnamentiInseriti = p.getTeachings() != null ? p.getTeachings() : new ArrayList<>();
		List<Insegnamento> insegnamenti = new ArrayList<Insegnamento>();
		for(Esame e : esami) {
			if(e != null) {
				Insegnamento i = e.getTeaching();
				if(!insegnamenti.contains(i))
					insegnamenti.add(i);			//creo una lista d'appoggio
			}
		}
		return insegnamentiInseriti.containsAll(insegnamenti); //verifico che tutti gli insegnamenti degli esami siano contenuti in quelli inseriti
	}

}
