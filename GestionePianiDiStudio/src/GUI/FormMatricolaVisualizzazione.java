package GUI;

/**
 * Form usato per inserire la matricola dello studente per poi visualizzare il piano di studio
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class FormMatricolaVisualizzazione extends FormMatricola {
	
	public FormMatricolaVisualizzazione() {
		super();
	}
	
	@Override
	public void confirm(String matricola) {
		controller.send(this, matricola);
	}
}
