package logica;

import java.sql.SQLException;

import dao.EstablishedRouteDAO;

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

	public EstablishedRoute(Contract contract, Vehicle vehicle,
			String descriptionRout, double costGoing, double costLap) { // constructor a nivel de logica
		super(contract, vehicle);
		this.descriptionRout = descriptionRout;
		this.costGoing = costGoing;
		this.costLap = costLap;
		this.typeTransportModality = "Established Route";
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

	@Override
	public void insert() throws SQLException {

		this.id = EstablishedRouteDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		EstablishedRouteDAO.getInstancie().update(this);

	}

}
