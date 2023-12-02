package logica;

public class TransportModality extends Modality{
protected Vehicle vehicle;

public TransportModality(int id, Contract contract, Vehicle vehicle) {
	super(id, contract);
	this.vehicle = vehicle;
}

public Vehicle getVehicle() {
	return vehicle;
}

public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
}

}
