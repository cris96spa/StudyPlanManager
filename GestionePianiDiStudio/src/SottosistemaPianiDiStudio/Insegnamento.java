package SottosistemaPianiDiStudio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Insegnamento {

	private String codiceIdentificativo;
	private String nome;
	private List<Insegnamento> prop;
	private int cfu;
	private String settoreScientifico;
	
	public Insegnamento(String cod, String name, int cfu, String sciSect) {
		codiceIdentificativo = cod;
		nome = name;
		this.cfu = cfu;
		settoreScientifico = sciSect;
		prop = new ArrayList<Insegnamento>();
	}
	
	public Insegnamento(String cod, String name, int cfu, String sciSect, Iterator<Insegnamento> prop) {
		codiceIdentificativo = cod;
		nome = name;
		this.cfu = cfu;
		settoreScientifico = sciSect;
		this.prop = new ArrayList<Insegnamento>();
		while(prop.hasNext()) {
			Insegnamento i = prop.next();
			if(i != null) {
				this.prop.add(i);
			}
		}
	}

	public String getID() {
		return codiceIdentificativo;
	}

	public String getName() {
		return nome;
	}
	
	public void setName(String name) {
		if(nome != name)
			nome = name;
	}

	public List<Insegnamento> getPropedeutics() {
		return prop;
	}

	public int getCfu() {
		return cfu;
	}

	public String getScientificSector() {
		return settoreScientifico;
	}

	public void setPropedeutics(List<Insegnamento> prop) {
		if(this.prop != prop)
			this.prop = prop;
	}

	/**
	 * Consente di aggiungere insegnamenti alla lista delle propedeuticità.
	 * Se l'insegnamento passato non è presente nella lista allora viene effettivamente aggiunto.
	 * @param l'insegnamento da aggiungere
	 */
	public void addPropedeutic(Insegnamento i) {
		if(i != null  && !prop.contains(i)) {
			prop.add(i);
			Collections.sort(prop, new Comparator<Insegnamento>() {
				@Override
				public int compare(Insegnamento o1, Insegnamento o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
		}
	}

	/**
	 * Consente di rimuovere insegnamenti dalla lista delle propedeuticità.
	 * Se l'insegnamento passato è presente nella lista allora viene effettivamente rimosso.
	 * @param l'insegnamento da rimuovere
	 */
	public void removePropedeutic(Insegnamento i) {
		if(i != null  && prop.contains(i))
			prop.remove(i);
	}

	@Override
	public String toString() {
		return "Insegnamento [codiceIdentificativo=" + codiceIdentificativo + ", nome=" + nome + ", prop=" + prop
				+ ", cfu=" + cfu + ", settoreScientifico=" + settoreScientifico + "]";
	}
}
