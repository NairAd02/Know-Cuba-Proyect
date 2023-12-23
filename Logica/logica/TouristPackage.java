package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import dao.ModalityDAO;
import dao.TouristPackageDAO;



public class TouristPackage implements DUILogic {
	private int id;
	private String name;
	private HashMap<Integer, ArrayList<Modality>> modalitys; // modalidades hasheadas por su tipo

	public TouristPackage(int id, String name, HashMap<Integer, ArrayList<Modality>> modalitys) { // Constructor a nivel de base de datos
		this.id = id;
		this.name = name;
		this.modalitys = modalitys;
	}

	public TouristPackage(String name, ArrayList<Modality> accommodationModalitys, ArrayList<Modality> serviceModalitys, ArrayList<Modality> transportModalitysCostKilometers,
			ArrayList<Modality> transportModalitysHoursKilometers, ArrayList<Modality> transportModalitysEstablishedRoute) { // Constructor a nivel de logica
		this.name = name;
		this.construirHashMapModalitys(accommodationModalitys, serviceModalitys, transportModalitysCostKilometers, transportModalitysHoursKilometers, transportModalitysEstablishedRoute); // se construye el hashmap
	}

	private void construirHashMapModalitys (ArrayList<Modality> accommodationModalitys, ArrayList<Modality> serviceModalitys, ArrayList<Modality> transportModalitysCostKilometers,
			ArrayList<Modality> transportModalitysHoursKilometers, ArrayList<Modality> transportModalitysEstablishedRoute) {
		this.modalitys = new HashMap<Integer, ArrayList<Modality>>();
		this.modalitys.put(Modality.accommodationModality, (accommodationModalitys != null) ? accommodationModalitys : new ArrayList<Modality>()); // se asignan las modalidades de alojamiento
		this.modalitys.put(Modality.serviceModality, (serviceModalitys != null) ? serviceModalitys : new ArrayList<Modality>()); // se asignan las modalidades de servicio
		this.modalitys.put(Modality.costKilometers, (transportModalitysCostKilometers != null) ? transportModalitysCostKilometers : new ArrayList<Modality>()); // se asignan las modalidades de transporte de tipo costo por kilometraje
		this.modalitys.put(Modality.hoursKilometers, (transportModalitysHoursKilometers != null) ? transportModalitysHoursKilometers : new ArrayList<Modality>()); // se asignan las modalidades de transporte de tipo horas por kilometraje
		this.modalitys.put(Modality.establishedRoute, (transportModalitysEstablishedRoute != null) ? transportModalitysEstablishedRoute : new ArrayList<Modality>()); // se asignan las modalidades de transporte de tipo recorridos establecidos
	}

	private void construirHashMapModalitys () {
		this.modalitys = new HashMap<Integer, ArrayList<Modality>>();
		this.modalitys.put(Modality.accommodationModality, new ArrayList<Modality>()); // se construyen las modalidades de alojamiento
		this.modalitys.put(Modality.serviceModality, new ArrayList<Modality>()); // se construyen las modalidades de servicio
		this.modalitys.put(Modality.costKilometers, new ArrayList<Modality>()); // se construyen las modalidades de transporte de tipo costo por kilometraje
		this.modalitys.put(Modality.hoursKilometers, new ArrayList<Modality>()); // se construyen las modalidades de transporte de tipo horas por kilometraje
		this.modalitys.put(Modality.establishedRoute, new ArrayList<Modality>()); // se construyen las modalidades de transporte de tipo recorridos establecidos
	}

	public TouristPackage() { // Constructor Temporal
		this.id = -1; // se marca como objeto temporal
		this.construirHashMapModalitys();
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

	// Metodos de Carga

	public void actualizarDatos () throws SQLException {
		this.cargarModalitys();
	}

	private void cargarModalitys () throws SQLException {
		this.modalitys = ModalityDAO.getInstancie().selectIntoTouristPackage(this.id); // se obtienen todas las modalidades pertenecientes al paquete

	}

	// Fin de Metodos de Carga


	// Metodos para el control de las modalidades

	public void addModality (Modality modality) throws SQLException {
		modality.insertIntoPackageTourist(this.id); // se inserta la modalidad en la base de datos como modalidad perteneciente a este paquete turistico

		// se inserta la modalidad en la logica del negocio
		this.addModalityLogic(modality);
	}

	public double costo() {
		ArrayList<Integer> keys = Modality.getKeys(); // se obtienen todas la llaves para iterar el mapa
		double suma = 0;
		// Se itera el mapa
		for (Integer i : keys) {
			for (Modality modality : this.modalitys.get(i)) { // se itera la lista de modalidades asociadas a esa clave
				suma += modality.price();
			}
		}

		return suma;
	}

	public void addModalityLogic (Modality modality)  {

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
		this.deleteModalityLogic(modality);

	}

	public void deleteModalityLogic (Modality modality)  {

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
		this.insertModalityTouristPackageIntoDataBase();
	}

	private void insertModalityTouristPackageIntoDataBase () throws SQLException { // Metodo para una vez insertado el objeto insertar cada una de sus modalidades en la base de datos
		ArrayList<Integer> keys = Modality.getKeys(); // se obtienen todas la llaves para iterar el mapa

		// Se itera el mapa
		for (Integer i : keys) {
			for (Modality modality : this.modalitys.get(i)) { // se itera la lista de modalidades asociadas a esa clave
				modality.insertIntoPackageTourist(this.id); // se inserta cada modalidad como parte del paquete turistico
			}
		}
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

	//Operaciones

	public boolean isContainsModality (int tpyOfModality, Modality modality) {
		boolean isContain = false;
		ArrayList<Modality> modalitys = this.modalitys.get(tpyOfModality);

		for (int i = 0; i < modalitys.size() && !isContain; i++) {
			if (modalitys.get(i).equals(modality))
				isContain = true;
		}

		return isContain;
	}

	// Fin de Operaciones

}
