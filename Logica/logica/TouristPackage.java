package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import dao.TouristPackageDAO;


public class TouristPackage implements DUILogic {
	private int id;
	private String name;
	HashMap<Integer, ArrayList<Modality>> modalitys; // modalidades hasheadas por su tipo

	public TouristPackage(int id, String name, HashMap<Integer, ArrayList<Modality>> modalitys) {
		super();
		this.id = id;
		this.name = name;
		this.modalitys = modalitys;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Integer, ArrayList<Modality>> getModalitys() {
		return modalitys;
	}
	public void setModalitys(HashMap<Integer, ArrayList<Modality>> modalitys) {
		this.modalitys = modalitys;
	}


	// Metodos para el control de las modalidades

	public void addModality (Modality modality) throws SQLException {
		modality.insertIntoPackageTourist(this.id); // se inserta la modalidad en la base de datos como modalidad perteneciente a este paquete turistico

		// se inserta la modalidad en la logica del negocio
		if (modality instanceof ServiceModality) {
			this.modalitys.get(Modality.serviceModality).add(modality);
		}
		else if (modality instanceof CostKilometers) {
			this.modalitys.get(Modality.costKilometers).add(modality);
		}
		else if (modality instanceof HoursKilometers) {
			this.modalitys.get(Modality.hoursKilometers).add(modality);
		}
		else if (modality instanceof EstablishedRoute) {
			this.modalitys.get(Modality.establishedRoute).add(modality);
		}
		else if (modality instanceof AccommodationModality) {
			this.modalitys.get(Modality.accommodationModality).add(modality);
		}
	}

	public void deleteModality (Modality modality) throws SQLException {
		modality.deleteFromPackageTourist(this.id); // se elimina la modalidad en la base de datos como modalidad perteneciente a este paquete turistico

		// se elimina la modalidad en la logica del negocio
		if (modality instanceof ServiceModality) {
			this.modalitys.get(Modality.serviceModality).remove(modality);
		}
		else if (modality instanceof CostKilometers) {
			this.modalitys.get(Modality.costKilometers).remove(modality);
		}
		else if (modality instanceof HoursKilometers) {
			this.modalitys.get(Modality.hoursKilometers).remove(modality);
		}
		else if (modality instanceof EstablishedRoute) {
			this.modalitys.get(Modality.establishedRoute).remove(modality);
		}
		else if (modality instanceof AccommodationModality) {
			this.modalitys.get(Modality.accommodationModality).remove(modality);
		}

	}

	/*public void updateModality (Modality modality) throws SQLException {

		if (modality instanceof ServiceModality) {
			ServiceModalityDAO.getInstancie().update((ServiceModality) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof CostKilometers) {
			CostKilometersDAO.getInstancie().update((CostKilometers) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof HoursKilometers) {
			HoursKilometersDAO.getInstancie().update((HoursKilometers) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof EstablishedRoute) {
			EstablishedRouteDAO.getInstancie().update((EstablishedRoute) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof AccommodationModality) {
			AccommodationModalityDAO.getInstancie().update((AccommodationModality) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
	}*/

	public ArrayList<Modality> getModalitys (int typeOfModality) { // se obtiene la lista de un tipo de modalidad en especifico
		return this.modalitys.get(typeOfModality);
	}


	@Override
	public void insert() throws SQLException {
		this.id = TouristPackageDAO.getInstancie().insert(this);
	}


	@Override
	public void update() throws SQLException {
		TouristPackageDAO.getInstancie().update(this);
	}


	@Override
	public void delete() throws SQLException {
		TouristPackageDAO.getInstancie().delete(this.id);
	}

	// Fin Metodos para el control de las modalidades

}
