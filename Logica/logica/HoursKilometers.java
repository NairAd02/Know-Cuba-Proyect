package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.HoursKilometersDAO;

public class HoursKilometers extends TransportModality{
	private double costKilometersRout;
	private double costHours;
	private double costKilometersRoutAdditionals;
	private double costHoursAdditionals;


	public HoursKilometers(int id, Contract contract, String typeOfModality, ArrayList<Vehicle> vehicles, String typeTransportModality,
						   double costKilometersRout, double costHours, double costKilometersRoutAdditionals,
						   double costHoursAdditionals) { // constructor a nivel de base de datos
		super(id, contract, typeOfModality, vehicles, typeTransportModality);
		this.costKilometersRout = costKilometersRout;
		this.costHours = costHours;
		this.costKilometersRoutAdditionals = costKilometersRoutAdditionals;
		this.costHoursAdditionals = costHoursAdditionals;
	}

	public HoursKilometers(Contract contract, ArrayList<Vehicle> vehicles,
			double costKilometersRout, double costHours, double costKilometersRoutAdditionals,
			double costHoursAdditionals) { // constructor a nivel de logica
		super(contract, vehicles);
		this.costKilometersRout = costKilometersRout;
		this.costHours = costHours;
		this.costKilometersRoutAdditionals = costKilometersRoutAdditionals;
		this.costHoursAdditionals = costHoursAdditionals;
		this.typeTransportModality = "Hours Kilometers";
	}
	
	public HoursKilometers(ArrayList<Vehicle> vehicles,
			double costKilometersRout, double costHours, double costKilometersRoutAdditionals,
			double costHoursAdditionals) { // constructor a nivel de logica (proceso de creación del objeto)
		super(vehicles);
		this.costKilometersRout = costKilometersRout;
		this.costHours = costHours;
		this.costKilometersRoutAdditionals = costKilometersRoutAdditionals;
		this.costHoursAdditionals = costHoursAdditionals;
		this.typeTransportModality = "Hours Kilometers";
	}

	public HoursKilometers () { // constructor para crear una instancia temporal
		super();
		this.typeTransportModality = "Hours Kilometers";
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

	@Override
	public void insert() throws SQLException {
		this.id = HoursKilometersDAO.getInstancie().insert(this);
		this.insertVehiclesIntoTransportModality(); // Se insertan todos los vehiculos asignados como parte de esta modalidad en la base de datos
	}

	@Override
	public void update() throws SQLException {
		HoursKilometersDAO.getInstancie().update(this);
	}

	// Operaciones

	public double price () {
		return this.costHours + this.costHoursAdditionals + this.costKilometersRout + this.costKilometersRoutAdditionals;
	}

	// Fin de operaciones

}
