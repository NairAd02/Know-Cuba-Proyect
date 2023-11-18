package logica;

import java.util.ArrayList;

public class Tourist_Package {
	private int id;
	ArrayList<Modality> modalities;
	
	public Tourist_Package(int id) {
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
