package logica;

public class HoursKilometers extends TransportModality{
private double costKilometersRout;
private double costHours;
private double costKilometersRoutAdditionals;
private double costHoursAdditionals;


public HoursKilometers(int id, Contract contract, String typeOfModality, Vehicle vehicle, String typeTransportModality,
		double costKilometersRout, double costHours, double costKilometersRoutAdditionals,
		double costHoursAdditionals) { // constructor a nivel de base de datos
	super(id, contract, typeOfModality, vehicle, typeTransportModality);
	this.costKilometersRout = costKilometersRout;
	this.costHours = costHours;
	this.costKilometersRoutAdditionals = costKilometersRoutAdditionals;
	this.costHoursAdditionals = costHoursAdditionals;
}

public HoursKilometers(Contract contract, String typeOfModality, Vehicle vehicle, String typeTransportModality,
		double costKilometersRout, double costHours, double costKilometersRoutAdditionals,
		double costHoursAdditionals) { // constructor a nivel de logica
	super(contract, typeOfModality, vehicle, typeTransportModality);
	this.costKilometersRout = costKilometersRout;
	this.costHours = costHours;
	this.costKilometersRoutAdditionals = costKilometersRoutAdditionals;
	this.costHoursAdditionals = costHoursAdditionals;
}

public double getCostKilometersRout() {
	return costKilometersRout;
}
public void setCostKilometersRout(double costKilometersRout) {
	this.costKilometersRout = costKilometersRout;
}
public double getCostHours() {
	return costHours;
}
public void setCostHours(double costHours) {
	this.costHours = costHours;
}
public double getCostKilometersRoutAdditionals() {
	return costKilometersRoutAdditionals;
}
public void setCostKilometersRoutAdditionals(
		double costKilometersRoutAdditionals) {
	this.costKilometersRoutAdditionals = costKilometersRoutAdditionals;
}
public double getCostHoursAdditionals() {
	return costHoursAdditionals;
}
public void setCostHoursAdditionals(double costHoursAdditionals) {
	this.costHoursAdditionals = costHoursAdditionals;
}

}
