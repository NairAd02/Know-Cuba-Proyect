package logica;

public class TransportModality extends Modality{
	protected Vehicle vehicle;
	protected String typeTransportModality;
	
	public TransportModality(int id, Contract contract, String typeOfModality, Vehicle vehicle,
			String typeTransportModality) {
		super(id, contract, typeOfModality);
		this.vehicle = vehicle;
		this.typeTransportModality = typeTransportModality;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public int getVehicleId () {
		return this.vehicle.getId();
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getTypeTransportModality() {
		return typeTransportModality;
	}
	public void setTypeTransportModality(String typeTransportModality) {
		this.typeTransportModality = typeTransportModality;
	}

	
}
