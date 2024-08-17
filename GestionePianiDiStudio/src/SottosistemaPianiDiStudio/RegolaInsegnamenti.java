package SottosistemaPianiDiStudio;

import java.util.List;

public class RegolaInsegnamenti extends Regola {

	public RegolaInsegnamenti(CorsoDiStudio c) {
		super(c);
	}

	/**
	 * Consente di verificare che gli insegnamenti scelti siano quelli assegnati
	 * al corso di studio.
	 * @return true se ci sono, false altrimenti
	 * @param piano di studio da approvare
	 */
	@Override
	public boolean checkRule(PianoDiStudio p) {
		List<Insegnamento> insegnamentiCorso = super.getCourse().getTeaching();
		return insegnamentiCorso.containsAll(p.getTeachings());
	}
}
