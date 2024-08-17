package SottosistemaPianiDiStudio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegolaPropedeuticita extends Regola {

	public RegolaPropedeuticita(CorsoDiStudio c) {
		super(c);
	}

	/**
	 * Consente di verificare che tutte le propedeuticità degli insegnamenti scelti siano riportate nel piano.
	 * @return true se lo sono, false altrimenti
	 * @param piano di studio da approvare
	 */
	@Override
	public boolean checkRule(PianoDiStudio p) {
		Set<Insegnamento> prop = new HashSet();
		List<Insegnamento> insegnamentiInseriti = p.getTeachings() != null ? p.getTeachings() : new ArrayList<>();
		for(Insegnamento i : insegnamentiInseriti) {
			if(i != null) {
				prop.addAll(i.getPropedeutics());
			}
		}
		return insegnamentiInseriti.containsAll(prop);
	}

}