package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ActivityDAO;

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
		int idInsertado = ActivityDAO.getInstancie().insert(activity); // se inserta la actividad en la base de datos	
		this.activities.add(activity); // se inserta la actividad en la logica del negocio
		activity.setId(idInsertado); // se asigna el id autoincrementable de la base de datos
	}

	public void deleteActivity (Activity activity) throws SQLException {
		ActivityDAO.getInstancie().delete(activity.getId()); // se elimina la actividad de la base de datos
		this.activities.remove(activity);
	}
	
	public void update (Activity activity) throws SQLException {
		ActivityDAO.getInstancie().update(activity); // se actualiza la informacion de la actividad en la base de datos
	}

	// Fin Metodos para el control de las actividades

}

