package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.CostKilometersDAO;
import dao.EstablishedRouteDAO;
import dao.HoursKilometersDAO;



public  class CarrierContract extends Contract{

	public CarrierContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, ArrayList<TransportModality> modalitys, boolean state,
			String typeOfContract) {
		super(id, startDate, terminationDate, reconciliationDate, description, provider, new ArrayList<Modality>(modalitys), state, typeOfContract);
		// TODO Auto-generated constructor stub
	}

	public CarrierContract(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	// Metodos de obtencion

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

	public void addModality (Modality modality) throws SQLException {
		int idInsertado = -1;

		if (modality instanceof CostKilometers)
			idInsertado = CostKilometersDAO.getInstancie().insert((CostKilometers) modality); // se inserta la modalidad en la base de datos
		else if (modality instanceof HoursKilometers)
			idInsertado = HoursKilometersDAO.getInstancie().insert((HoursKilometers) modality); // se inserta la modalidad en la base de datos
		else if (modality instanceof EstablishedRoute)
			idInsertado = EstablishedRouteDAO.getInstancie().insert((EstablishedRoute) modality); // se inserta la modalidad en la base de datos

		super.addModality(modality);
		modality.setId(idInsertado); // se asigna el id autoincrementable
	}

	@Override
	public void updateModality(Modality modality) throws SQLException {

		if (modality instanceof CostKilometers)
			CostKilometersDAO.getInstancie().update((CostKilometers) modality); // se actualiza la informacion de la modalidad en la base de datos
		else if (modality instanceof HoursKilometers)
			HoursKilometersDAO.getInstancie().update((HoursKilometers) modality); // se actualiza la informacion de la modalidad en la base de datos
		else if (modality instanceof EstablishedRoute)
			EstablishedRouteDAO.getInstancie().update((EstablishedRoute) modality); // se actualiza la informacion de la modalidad en la base de datos

	}

	// Fin Metodos para el control de las modalidades

}
