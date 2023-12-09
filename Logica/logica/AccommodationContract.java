package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import dao.AccommodationModalityDAO;

public class AccommodationContract extends Contract{
	ArrayList<Season> seasons;
	private double surcharge;
	
	public AccommodationContract(int id, LocalDate startDate,
			LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider,
			ArrayList<AccommodationModality> modalitys, boolean state, String typeOfContract,
			ArrayList<Season> seasons, double surcharge) {
		super(id, startDate, terminationDate, reconciliationDate, description,
				provider, new ArrayList<Modality>(modalitys), state, typeOfContract);
		this.seasons = seasons;
		this.surcharge = surcharge;
	} // Constructor Nivel de logica
	

	public AccommodationContract (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		super(id);
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

	public double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}

}
