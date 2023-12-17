package logica;

import java.sql.SQLException;

import java.util.ArrayList;

import dao.ActivityDAO;
import dao.ServiceProviderDAO;


public class ServiceProvider extends Provider {
	private ArrayList<Activity> activities;

	public ServiceProvider(int id, String name, String province,
			ArrayList<Activity> activities) { // Constructor a nivel de base de datos
		super(id, name, province);
		this.activities = activities;
	}

	public ServiceProvider(String name, String province,
			ArrayList<Activity> activities) { // Constructor a nivel de logica
		super(name, province);
		this.activities = activities;
	}



	public ServiceProvider () { // Constructor para operaciones temporales
		super();
		this.activities = new ArrayList<Activity>();
	}
	
	// Metodos de Carga y obtencion
	public void actualizarActivities () throws SQLException {
		this.activities = (ArrayList<Activity>) ActivityDAO.getInstancie().selectIntoServiceProvider(this.id);
	}
	
	// Fin de Metodos de Carga y obtencion

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	// Metodos para el control de las actividades

	public int cantActivities () {
		return this.activities.size();
	}

	public void addActivity (Activity activity) throws SQLException {
		activity.insert(); // se inserta la actividad en la base de datos	
		this.addActivityLogic(activity); // se inserta la actividad en la logica del negocio
	}

	public void addActivityLogic (Activity activity) throws SQLException {
		this.activities.add(activity); // se inserta la actividad en la logica del negocio
	}

	private void insertActivitiesIntoDataBase () throws SQLException { // metodo para insertar las actividades en la base de datos (Este metodo solo va a ser llamado despues de la inserccion del provedor de serivicios)

		for (Activity activity : this.activities) {
			activity.setIdServiceProvider(this.id); // se establece el id del provedor de servicios de esta actividad
			activity.insert();
		}

	}

	public void deleteActivity (Activity activity) throws SQLException {
		activity.delete(); // se elimina la actividad de la base de datos
		this.deleteActivityLogic(activity); // se elimina la actividad de la logica del negocio
	}

	public void deleteActivityLogic (Activity activity) throws SQLException {
		this.activities.remove(activity);
	}

	public void update (Activity activity, String activityDescription) throws SQLException {
		// se actualizan los datos de la actividad en la logica del negocio
		this.updateLogic(activity, activityDescription);
		activity.update(); // se actualiza la informacion de la actividad en la base de datos
	}

	public void updateLogic (Activity activity, String activityDescription) throws SQLException {
		// se actualizan los datos de la actividad en la logica del negocio
		activity.setDescription(activityDescription);
	}

	// Fin Metodos para el control de las actividades



	@Override
	public void insert() throws SQLException {
		this.id = ServiceProviderDAO.getInstancie().insert(this);
		this.insertActivitiesIntoDataBase(); // se inserta en la base de datos las actividades de este provedor de servicios (Es importante realizar las insercciones de los objetos por los que esta compuesta la clase)
	}

	@Override
	public void update() throws SQLException {
		ServiceProviderDAO.getInstancie().update(this);
		
	}



}

