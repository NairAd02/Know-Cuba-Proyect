package logica;

import java.util.ArrayList;

public class TouristPackage {
	private int id;
	ArrayList<Modality> modalities;
	
	public TouristPackage(int id) {
		this.id = id;
		this.modalities = new ArrayList<Modality>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
