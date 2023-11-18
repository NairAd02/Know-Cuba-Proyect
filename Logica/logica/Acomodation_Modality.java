package logica;

public class Acomodation_Modality extends Modality{
	
	private int cant_days_acommodation;

	public Acomodation_Modality(int id, int cant_days_acommodation) {
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
