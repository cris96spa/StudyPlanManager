package SottosistemaPianiDiStudio;

import java.util.Date;

public class CorsoTriennale extends CorsoDiStudio{

	private final static int LIVELLO_TRIENNALE = 1;
	private final static int CFU_TRIENNALE = 180;
	
	public CorsoTriennale(String name, Date creationDate) {
		super(name, creationDate);
		livello = LIVELLO_TRIENNALE;
		cfu = CFU_TRIENNALE;
	}
}
