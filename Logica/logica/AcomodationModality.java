package logica;

public class AcomodationModality extends Modality{
	
	private int cant_days_acommodation;

	public AcomodationModality(int id, int cant_days_acommodation) {
		super(id);
		this.cant_days_acommodation = cant_days_acommodation;
	}

	public int getCant_days_acommodation() {
		return cant_days_acommodation;
	}

	public void setCant_days_acommodation(int cant_days_acommodation) {
		this.cant_days_acommodation = cant_days_acommodation;
	}

}
