package SottosistemaPianiDiStudio;

import java.util.Date;

public class CorsoMagistrale extends CorsoDiStudio{

	private final static int LIVELLO_MAGISTRALE = 2;
	private final static int CFU_MAGISTRALE = 120;
	

	public CorsoMagistrale(String name, Date creationDate) {
		super(name, creationDate);
		livello = LIVELLO_MAGISTRALE;
		cfu = CFU_MAGISTRALE;
	}

}
