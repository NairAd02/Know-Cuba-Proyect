package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class AccommodationContract extends Contract{
	ArrayList<Season> seasons;
	private int surcharge;
	
	public AccommodationContract(int id, LocalDate startDate,
			LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider,
			LinkedList<Modality> modalitys, boolean state,
			ArrayList<Season> seasons, int surcharge) {
		super(id, startDate, terminationDate, reconciliationDate, description,
				provider, modalitys, state);
		this.seasons = seasons;
		this.surcharge = surcharge;
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

	public int getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(int surcharge) {
		this.surcharge = surcharge;
	}
	
}
