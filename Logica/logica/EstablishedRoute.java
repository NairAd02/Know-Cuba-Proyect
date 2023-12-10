package logica;

public class EstablishedRoute extends TransportModality{
private String descriptionRout;
private double costGoing;
private double costLap;


public EstablishedRoute(int id, Contract contract, String typeOfModality, Vehicle vehicle, String typeTransportModality,
		String descriptionRout, double costGoing, double costLap) { // constructor a nivel de base de datos
	super(id, contract, typeOfModality, vehicle, typeTransportModality);
	this.descriptionRout = descriptionRout;
	this.costGoing = costGoing;
	this.costLap = costLap;
}

public EstablishedRoute(Contract contract, String typeOfModality, Vehicle vehicle, String typeTransportModality,
		String descriptionRout, double costGoing, double costLap) { // constructor a nivel de logica
	super(contract, typeOfModality, vehicle, typeTransportModality);
	this.descriptionRout = descriptionRout;
	this.costGoing = costGoing;
	this.costLap = costLap;
}

public String getDescriptionRout() {
	return descriptionRout;
}
public void setDescriptionRout(String descriptionRout) {
	this.descriptionRout = descriptionRout;
}
public double getCostGoing() {
	return costGoing;
}
public void setCostGoing(double costGoing) {
	this.costGoing = costGoing;
}
public double getCostLap() {
	return costLap;
}
public void setCostLap(double costLap) {
	this.costLap = costLap;
}

}
