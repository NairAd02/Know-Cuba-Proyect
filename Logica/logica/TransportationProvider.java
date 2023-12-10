package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.VehicleDAO;

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

	// Metodos para el control de los vehiculos

	public void addVehicle (Vehicle vehicle) throws SQLException {
		int idInsertado = VehicleDAO.getInstancie().insert(vehicle); // se inserta el vehiculo en la base de datos
		this.vehicles.add(vehicle); // se inserta el vehiculo en la logica del negocio
		vehicle.setId(idInsertado); // se asigna el id autoincrementable de la base de datos
	}
	
	public void deleteVehicle (Vehicle vehicle) throws SQLException {
		VehicleDAO.getInstancie().delete(vehicle.getId()); // se elimina el vehiculo de la base de datos
		this.vehicles.remove(vehicle); // se elimina el vehiculo de la logica del negocio
	}
	
	public void updateVehicle (Vehicle vehicle) throws SQLException {
		VehicleDAO.getInstancie().update(vehicle); // se actualiza la informacion del vehiculo en la base de datos
	}

	// Fin Metodos para el control de los vehiculos

}
