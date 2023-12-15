package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.TransportationProviderDAO;


public class TransportationProvider extends Provider {
	ArrayList<Vehicle> vehicles;

	public TransportationProvider(int id, String name, String province,
			ArrayList<Vehicle> vehicles) { // Constructor a nivel de base de datos
		super(id, name, province);
		this.vehicles = vehicles;
	}

	public TransportationProvider(String name, String province,
			ArrayList<Vehicle> vehicles) { // Constructor a nivel de logica
		super(name, province);
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
		vehicle.insert(); // se inserta el vehiculo en la base de datos
		this.vehicles.add(vehicle); // se inserta el vehiculo en la logica del negocio
	}

	public void deleteVehicle (Vehicle vehicle) throws SQLException {
		vehicle.delete(); // se elimina el vehiculo de la base de datos
		this.vehicles.remove(vehicle); // se elimina el vehiculo de la logica del negocio
	}

	public void updateVehicle (Vehicle vehicle, String lock) throws SQLException {
		// se actualizan los datos del vehiculo en la logica del negocio
		vehicle.setLock(lock);
		vehicle.update(); // se actualiza la informacion del vehiculo en la base de datos
	}

	@Override
	public void insert() throws SQLException {
		this.id = TransportationProviderDAO.getInstancie().insert(this);
		this.insertVehiclesIntoDataBase(); // se insertantas los vehiculos en la base de datos (muy importante la llamada a este metodo)
	}

	@Override
	public void update() throws SQLException {
		TransportationProviderDAO.getInstancie().update(this);
	}
	
	private void insertVehiclesIntoDataBase () throws SQLException { // metodo para insertar los vehiculos en la base de datos (Este metodo solo va a ser llamado despues de la inserccion del provedor de transporte)
		
		for (Vehicle vehicle : this.vehicles) {
			vehicle.setTransportationProviderId(this.id); // se le asigna a cada vehiculo el id del provedor
			vehicle.insert(); // se inserta el vehiculo en la base de datos
		}
	}

	// Fin Metodos para el control de los vehiculos

}
