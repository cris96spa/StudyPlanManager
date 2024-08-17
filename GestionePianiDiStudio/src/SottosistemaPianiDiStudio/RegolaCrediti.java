package SottosistemaPianiDiStudio;

public class RegolaCrediti extends Regola {
	
	public RegolaCrediti(CorsoDiStudio c) {
		super(c);
	}

	/**
	 * Consente di verificare che la somma dei cfu degli insegnamenti scelti per il piano, sia pari al
	 * numero totale dei cfu del corso di studio.
	 * @return true se è uguale, false altrimenti
	 * @param piano di studi da approvare
	 */
	@Override
	public boolean checkRule(PianoDiStudio p) {
		return p.getTotalCFU()==super.getCourse().getCfu();
	}

}
