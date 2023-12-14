package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.ServiceProviderDAO;

public class ServiceProvider extends Provider {
	private ArrayList<Activity> activities;

	public ServiceProvider(int id, String name, String province,
			ArrayList<Activity> activities) {
		super(id, name, province);
		this.activities = activities;
	}

	public ServiceProvider (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		super(id);
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	// Metodos para el control de las actividades

	public void addActivity (Activity activity) throws SQLException {
		activity.insert(); // se inserta la actividad en la base de datos	
		this.activities.add(activity); // se inserta la actividad en la logica del negocio
	}

	public void deleteActivity (Activity activity) throws SQLException {
		activity.delete(); // se elimina la actividad de la base de datos
		this.activities.remove(activity);
	}

	public void update (Activity activity, String activityDescription) throws SQLException {
		// se actualizan los datos de la actividad en la logica del negocio
		activity.setDescription(activityDescription);
		activity.update(); // se actualiza la informacion de la actividad en la base de datos
	}

	@Override
	public void insert() throws SQLException {
		this.id = ServiceProviderDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		ServiceProviderDAO.getInstancie().update(this);
	}

	// Fin Metodos para el control de las actividades

}

