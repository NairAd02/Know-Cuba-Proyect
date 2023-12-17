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
		super.insert();
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




}
