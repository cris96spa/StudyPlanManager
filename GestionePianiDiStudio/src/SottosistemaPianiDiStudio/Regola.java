package SottosistemaPianiDiStudio;

public abstract class Regola {

	private CorsoDiStudio corso;

	public Regola(CorsoDiStudio c) {
		corso = c;
	}
	
	public CorsoDiStudio getCourse() {
		return corso;
	}

	public void setCourse(CorsoDiStudio c) {
		if(corso != c)
			corso = c;
	}

	public abstract boolean checkRule(PianoDiStudio p);

}
