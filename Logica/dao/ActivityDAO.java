package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.Activity;
import utils.ConnectionDataBase;

public class ActivityDAO implements ActivityDAOInterface {
	private static ActivityDAO activityDAO;
	private HashMap<Integer, Activity> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private ActivityDAO () {
		this.cache = new HashMap<Integer, Activity>();
	}

	public static ActivityDAO getInstancie () {
		if (activityDAO == null)
			activityDAO = new ActivityDAO();

		return activityDAO;
	}

	@Override
	public int insert(Activity activity) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_activity(?, ?, ?)}");
		// se definen los parámetros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setString(2, activity.getDescription());
		cs.setInt(3, activity.getIdServiceProvider());
		cs.setString(4, activity.getName());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idActivity) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_activity(?)}");
		cs.setInt(1, idActivity); // se define el parámetros de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public void update(Activity activity) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_activity(?, ?)}");
		// se definen los parámetros de la funcion
		cs.setInt(1, activity.getId());
		cs.setString(2, activity.getDescription());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}



	@Override
	public List<Activity> selectAll() throws SQLException {
		List<Activity> listActivities = new ArrayList<Activity>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_activity()}");
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		// se crea la lista de actividades con la información obtenida de la base de datos
		while (cs.getResultSet().next()) {
			listActivities.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listActivities;
	}

	@Override
	public List<Activity> selectIntoServiceProvider(int idServiceProvider) throws SQLException {
		List<Activity> listActivities = new ArrayList<Activity>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_activity_service_provider(?)}");
		cs.setInt(1, idServiceProvider); // se define el parámetros de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		// se crea la lista de actividades con la información obtenida de la base de datos
		while (cs.getResultSet().next()) {
			listActivities.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion


		return listActivities;
	}

	@Override
	public Activity select(int idActivity) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_activity(?)}");
		Activity activity = null;
		cs.setInt(1, idActivity); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			activity = mapEntity(cs);

		cs.close(); // se cierra la llamada a la funcion

		return activity;
	}

	@Override
	public Activity mapEntity(CallableStatement cs) throws SQLException {
		Activity activity = this.cache.get(cs.getResultSet().getInt("id_activity"));

		if (activity == null) {
			activity = new Activity(cs.getResultSet().getInt("id_activity"), 
					cs.getResultSet().getString("name"), cs.getResultSet().getString("activity_description"), cs.getResultSet().getInt("service_provider_id"));

			this.cache.put(activity.getId(), activity); // se almacena en cache la referencia de la actividad
		}

		return activity;
	}

}
