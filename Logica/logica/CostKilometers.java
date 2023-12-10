package logica;

public class CostKilometers extends TransportModality{
	private double costKilometersGoing;
	private double costKilometersLap;
	private double costHoursWait;
	
	
	public CostKilometers(int id, Contract contract, String typeOfModality, Vehicle vehicle,
			String typeTransportModality, double costKilometersGoing, double costKilometersLap, double costHoursWait) { // constructor a nivel de base de datos
		super(id, contract, typeOfModality, vehicle, typeTransportModality);
		this.costKilometersGoing = costKilometersGoing;
		this.costKilometersLap = costKilometersLap;
		this.costHoursWait = costHoursWait;
	}
	
	public CostKilometers(Contract contract, String typeOfModality, Vehicle vehicle,
			String typeTransportModality, double costKilometersGoing, double costKilometersLap, double costHoursWait) { // constructor a nivel de logica
		super(contract, typeOfModality, vehicle, typeTransportModality);
		this.costKilometersGoing = costKilometersGoing;
		this.costKilometersLap = costKilometersLap;
		this.costHoursWait = costHoursWait;
	}

	public double getCostKilometersGoing() {
		return costKilometersGoing;
	}

	public void setCostKilometersGoing(double costKilometersGoing) {
		this.costKilometersGoing = costKilometersGoing;
	}

	public double getCostKilometersLap() {
		return costKilometersLap;
	}

	public void setCostKilometersLap(double costKilometersLap) {
		this.costKilometersLap = costKilometersLap;
	}

	public double getCostHoursWait() {
		return costHoursWait;
	}

	public void setCostHoursWait(double costHoursWait) {
		this.costHoursWait = costHoursWait;
	}
	
}
