package GUI;

/**
 * Form usato per inserire la matricola dello studente per poi presentare il piano di studio
 * @author De Lorenzo, Massaro, Spagnuolo
 *
 */
public class FormMatricolaPresentazione extends FormMatricola {
	
	public FormMatricolaPresentazione() {
		super();
	}
	
	@Override
	public void confirm(String matricola) {
		controller.send(this, matricola);
	}
}
