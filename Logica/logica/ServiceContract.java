package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.ServiceContractDAO;
import dao.ServiceModalityDAO;




public class ServiceContract extends Contract{



	public ServiceContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, boolean state, String typeOfContract,
			double surcharge) throws SQLException { // Constructor a nivel de base de datos
		super(id, startDate, terminationDate, reconciliationDate, description, provider, state, typeOfContract,
				surcharge);

	}

	public ServiceContract(LocalDate startDate, LocalDate terminationDate,
			String description, Provider provider, ArrayList<Modality> modalitys,
			double surcharge) { // Constructor a nivel de logica
		super(startDate, terminationDate, description, provider, modalitys,
				surcharge);
		this.typeOfContract = "Service Contract";

	}

	public ServiceContract() { // Constructor temporal
		super();
	}

	// Operaciones logicas

	public ArrayList<Activity> getActivitiesServiceProvider () { // metodos para obtener todas las actiidades del provedor de servicios con el que se estableció contrato
		return ((ServiceProvider) this.provider).getActivities();
	}

	// Fin de Operaciones logicas


	// Metodos para el control de las modalidades


	public void updateServiceModality (ServiceModality serviceModality, Activity activity, LocalDate releaseDate, double price) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		this.updateServiceModalityLogic(serviceModality, activity, releaseDate, price);
		serviceModality.update(); // se actualiza la informacion de la modalidad en la base de datos
	}

	public void updateServiceModalityLogic (ServiceModality serviceModality, Activity activity, LocalDate releaseDate, double price) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		serviceModality.setActivity(activity);
		serviceModality.setReleasedDate(releaseDate);
		serviceModality.setPrice(price);
	}
	// Fin Metodos para el control de las modalidades

	@Override
	public void insert() throws SQLException {
		this.id = ServiceContractDAO.getInstancie().insert(this);
		this.insertModalitysIntoDataBase();
	}

	@Override
	public void update() throws SQLException {
		ServiceContractDAO.getInstancie().update(this);
	}

	// Metodos de carga

	@Override
	public void cargarModalities() throws SQLException {
		this.modalitys = new ArrayList<Modality>(ServiceModalityDAO.getInstancie().selectIntoServiceContract(this));
	}

	@Override
	public void actualizarDatos() throws SQLException {
		this.cargarModalities();
	}

	// Fin de Metodos de carga

	// Metodos para la Obtenecion de las Modalidades

	public ArrayList<Modality> getModalitys (Activity activity) { // Filtro Actividad
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.getActivity().equals(activity))
				modalitys.add(modality);
		}

		return modalitys;
	}

	public ArrayList<Modality> getModalitys (LocalDate releasedDateMin, LocalDate releasedDateMax) { // Filtro Actividad
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.getReleasedDate().isAfter(releasedDateMin) && serviceModality.getReleasedDate().isBefore(releasedDateMax)) // se verifica que la fecha esté en rango
				modalitys.add(modality);
		}

		return modalitys;
	}

	public ArrayList<Modality> getModalitys (double precioMin, double precioMax) { // Filtro Percio
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.price() >= precioMin && serviceModality.price() <= precioMax) // se verifica que el precio esté en rango
				modalitys.add(modality);
		}

		return modalitys;
	}

	public ArrayList<Modality> getModalitys (Activity activity, LocalDate releasedDateMin, LocalDate releasedDateMax) { // Filtro Actividad + Filtro Precio
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.getActivity().equals(activity) && serviceModality.getReleasedDate().isAfter(releasedDateMin) && serviceModality.getReleasedDate().isBefore(releasedDateMax)) // se verifica que la modalidad contenga la misma actividad y que la fecha esté en rango
				modalitys.add(modality);
		}

		return modalitys;
	}

	public ArrayList<Modality> getModalitys (Activity activity, double precioMin, double precioMax) { // Filtro Actividad + Filtro Percio
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.getActivity().equals(activity) && serviceModality.price() >= precioMin && serviceModality.price() <= precioMax) // se verifica que el precio esté en rango y que contenga la actividad
				modalitys.add(modality);
		}

		return modalitys;
	}

	public ArrayList<Modality> getModalitys (LocalDate releasedDateMin, LocalDate releasedDateMax, double precioMin, double precioMax) { // Filtro Fecha de Realizacion + Filtro Percio
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.getReleasedDate().isAfter(releasedDateMin) && serviceModality.getReleasedDate().isBefore(releasedDateMax) &&
					serviceModality.price() >= precioMin && serviceModality.price() <= precioMax) // se verifica que el precio esté en rango y .............
				modalitys.add(modality);
		}

		return modalitys;
	}

	public ArrayList<Modality> getModalitys (Activity activity, LocalDate releasedDateMin, LocalDate releasedDateMax, double precioMin, double precioMax) { // Filtro Fecha de Realizacion + Filtro Percio
		ArrayList<Modality> modalitys = new ArrayList<>();

		for (Modality modality : this.modalitys) {
			ServiceModality serviceModality = (ServiceModality) modality;
			if (serviceModality.getActivity().equals(activity) && serviceModality.getReleasedDate().isAfter(releasedDateMin) && serviceModality.getReleasedDate().isBefore(releasedDateMax) &&
					serviceModality.price() >= precioMin && serviceModality.price() <= precioMax) // se verifica que el precio esté en rango y .........
				modalitys.add(modality);
		}

		return modalitys;
	}


	// Fin Metodos para la Obtenecion de las Modalidades


}
