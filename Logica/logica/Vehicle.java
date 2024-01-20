package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import dao.VehicleDAO;

public class Vehicle implements DUILogic {
	private int id;
	private String lock;
	private String brand;
	private int capacityWithoutLuggage;
	private int capacityWithLuggage;
	private int totalCapacity;
	private LocalDate dateOfProduction;
	private int transportationProviderId;



	public Vehicle(int id, String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
			int totalCapacity, LocalDate dateOfProduction, int transportationProviderId) {
		super();
		this.id = id;
		this.lock = lock;
		this.brand = brand;
		this.capacityWithoutLuggage = capacityWithoutLuggage;
		this.capacityWithLuggage = capacityWithLuggage;
		this.totalCapacity = totalCapacity;
		this.dateOfProduction = dateOfProduction;
		this.transportationProviderId = transportationProviderId;
	}

	public Vehicle(String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
			int totalCapacity, LocalDate dateOfProduction, int transportationProviderId) { // Constructor a nivel de logica
		this.lock = lock;
		this.brand = brand;
		this.capacityWithoutLuggage = capacityWithoutLuggage;
		this.capacityWithLuggage = capacityWithLuggage;
		this.totalCapacity = totalCapacity;
		this.dateOfProduction = dateOfProduction;
		this.transportationProviderId = transportationProviderId;
	}

	public Vehicle(String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
			int totalCapacity, LocalDate dateOfProduction) { // Constructor Temporal
		this.lock = lock;
		this.brand = brand;
		this.capacityWithoutLuggage = capacityWithoutLuggage;
		this.capacityWithLuggage = capacityWithLuggage;
		this.totalCapacity = totalCapacity;
		this.dateOfProduction = dateOfProduction;
	}

	public String getLock() {
		return lock;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCapacityWithoutLuggage() {
		return capacityWithoutLuggage;
	}

	public void setCapacityWithoutLuggage(int capacityWithoutLuggage) {
		this.capacityWithoutLuggage = capacityWithoutLuggage;
	}

	public int getCapacityWithLuggage() {
		return capacityWithLuggage;
	}

	public void setCapacityWithLuggage(int capacityWithLuggage) {
		this.capacityWithLuggage = capacityWithLuggage;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public LocalDate getDateOfProduction() {
		return this.dateOfProduction;
	}

	public void setDateOfProduction(LocalDate dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public int getTransportationProviderId() {
		return transportationProviderId;
	}

	public void setTransportationProviderId(int transportationProviderId) {
		this.transportationProviderId = transportationProviderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void insert() throws SQLException {
		this.id = VehicleDAO.getInstancie().insert(this);
	}

	public void insertIntoTransportModality (int idTransportModality) throws SQLException {
		VehicleDAO.getInstancie().insertIntoTransportModality(idTransportModality, this.id);
	}

	@Override
	public void update() throws SQLException {
		VehicleDAO.getInstancie().update(this);
	}

	@Override
	public void delete() throws SQLException {
		VehicleDAO.getInstancie().delete(this.id);
	}

	public void deleteFromTransportModality (int idTransportModality) throws SQLException {
		VehicleDAO.getInstancie().deleteIntoTransportModality(idTransportModality, this.id);
	}

	public String toString () { // Metodo para definir como se va a mostrar la informacion de la clase
		return this.lock;
	}
}
