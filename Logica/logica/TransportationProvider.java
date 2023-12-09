package logica;

import java.util.ArrayList;

public class TransportationProvider extends Provider {
	ArrayList<Vehicle> vehicles;

	public TransportationProvider(int id, String name, String province,
			ArrayList<Vehicle> vehicles) {
		super(id, name, province);
		this.vehicles = vehicles;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	

}
