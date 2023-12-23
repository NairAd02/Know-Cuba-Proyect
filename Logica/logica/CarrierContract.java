package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.CarrierContractDAO;
import dao.TransportModalityDAO;



public  class CarrierContract extends Contract{



	public CarrierContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, boolean state, String typeOfContract,
			double surcharge) throws SQLException { // Constructor a nivel de base de datos
		super(id, startDate, terminationDate, reconciliationDate, description, provider, state, typeOfContract,
				surcharge);
		// TODO Auto-generated constructor stub
	}

	public CarrierContract(LocalDate startDate, LocalDate terminationDate,
			String description, Provider provider, ArrayList<Modality> modalitys,
			double surcharge) { // Constructor a nivel de logica
		super(startDate, terminationDate, description, provider, modalitys,
				surcharge);
		this.typeOfContract = "Carrier Contract";
	}

	public CarrierContract() { // Constructor temporal
		super();
	}

	// Metodos de obtencion

	public ArrayList<Vehicle> getVehiclesProvider () {
		return ((TransportationProvider) this.provider).getVehicles();
	}

	public ArrayList<CostKilometers> getCostKilometers () {
		ArrayList<CostKilometers> modalitys = new ArrayList<CostKilometers>();

		for (Modality m : this.modalitys) {
			if (m instanceof CostKilometers)
				modalitys.add((CostKilometers) m);
		}

		return modalitys;
	}


	public ArrayList<HoursKilometers> getHoursKilometers () {
		ArrayList<HoursKilometers> modalitys = new ArrayList<HoursKilometers>();

		for (Modality m : this.modalitys) {
			if (m instanceof HoursKilometers)
				modalitys.add((HoursKilometers) m);
		}

		return modalitys;
	}


	public ArrayList<EstablishedRoute> getEstablishedRoute () {
		ArrayList<EstablishedRoute> modalitys = new ArrayList<EstablishedRoute>();

		for (Modality m : this.modalitys) {
			if (m instanceof EstablishedRoute)
				modalitys.add((EstablishedRoute) m);
		}

		return modalitys;
	}
	// Fin Metodos de obtencion


	// Metodos para el control de las modalidades



	public void updateTransportModalityCostKilometers (CostKilometers costKilometers, Vehicle vehicle, double costKilometersGoing, double costKilometersLap, double costHoursWait) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		this.updateTransportModalityCostKilometersLogic(costKilometers, vehicle, costKilometersGoing, costKilometersLap, costHoursWait);
		costKilometers.update(); // se actualiza la informacion de la modalidad en la base de datos
	}

	public void updateTransportModalityCostKilometersLogic (CostKilometers costKilometers, Vehicle vehicle, double costKilometersGoing, double costKilometersLap, double costHoursWait) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		costKilometers.setVehicle(vehicle);
		costKilometers.setCostKilometersGoing(costKilometersGoing);
		costKilometers.setCostKilometersLap(costKilometersLap);
		costKilometers.setCostHoursWait(costHoursWait);
	}

	public void updateTransportationModalityHoursKilometers (HoursKilometers hoursKilometers, Vehicle vehicle, double costKilometersRout, double costHours, double costKilometersRoutAdditionals, double costHoursAdditionals) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		this.updateTransportationModalityHoursKilometersLogic(hoursKilometers, vehicle, costKilometersRout, costHours, costKilometersRoutAdditionals, costHoursAdditionals);
		hoursKilometers.update(); // se actualiza la informacion de la modalidad en la base de datos
	}

	public void updateTransportationModalityHoursKilometersLogic (HoursKilometers hoursKilometers, Vehicle vehicle, double costKilometersRout, double costHours, double costKilometersRoutAdditionals, double costHoursAdditionals) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		hoursKilometers.setVehicle(vehicle);
		hoursKilometers.setCostKilometersRout(costKilometersRout);
		hoursKilometers.setCostHours(costHours);
		hoursKilometers.setCostKilometersRoutAdditionals(costKilometersRoutAdditionals);
		hoursKilometers.setCostHoursAdditionals(costHoursAdditionals);
	}

	public void updateTransportationModalityEstablishedRoute (EstablishedRoute establishedRoute, Vehicle vehicle, String descriptionRout, double costGoing, double costLap) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		this.updateTransportationModalityEstablishedRouteLogic(establishedRoute, vehicle, descriptionRout, costGoing, costLap);
		establishedRoute.update(); // se actualiza la informacion de la modalidad en la base de datos
	}

	public void updateTransportationModalityEstablishedRouteLogic (EstablishedRoute establishedRoute, Vehicle vehicle, String descriptionRout, double costGoing, double costLap) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		establishedRoute.setVehicle(vehicle);
		establishedRoute.setDescriptionRout(descriptionRout);
		establishedRoute.setCostGoing(costGoing);
		establishedRoute.setCostLap(costLap);
	}

	@Override
	public void insert() throws SQLException {
		this.id = CarrierContractDAO.getInstancie().insert(this);
		this.insertModalitysIntoDataBase();
	}

	@Override
	public void update() throws SQLException {
		CarrierContractDAO.getInstancie().update(this);
	}

	// Metodos de carga
	@Override
	public void cargarModalities() throws SQLException {
		this.modalitys = new ArrayList<Modality>(TransportModalityDAO.getInstancie().selectIntoCarrierContract(this));
	}

	@Override
	public void actualizarDatos() throws SQLException {
		this.cargarModalities();

	}

	// Fin de Metodos de carga

	// Fin Metodos para el control de las modalidades

}
