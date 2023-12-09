package logica;

import java.util.ArrayList;

public class TouristPackage {
	private int id;
	private String name;
	ArrayList<Modality> modalities;
	
	
	public TouristPackage(int id, String name, ArrayList<Modality> modalities) {
		super();
		this.id = id;
		this.name = name;
		this.modalities = modalities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Modality> getModalities() {
		return modalities;
	}

	public void setModalities(ArrayList<Modality> modalities) {
		this.modalities = modalities;
	}
		
}
