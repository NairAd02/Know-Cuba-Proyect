package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.ServiceModality;
import utils.ConnectionDataBase;

public class ServiceModalityDAO implements ServiceModalityDAOInterface {
	private static ServiceModalityDAO serviceModalityDAO;
	private HashMap<Integer, ServiceModality> cache;

	// PATRON SINGLENTON
	private ServiceModalityDAO () {
		this.cache = new HashMap<Integer, ServiceModality>();
	}

	public static ServiceModalityDAO getInstancie () {
		if (serviceModalityDAO == null)
			serviceModalityDAO = new ServiceModalityDAO();

		return serviceModalityDAO;
	}

	@Override
	public int insert(ServiceModality serviceModality) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_service_modality(?, ?, ?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.setString(1, serviceModality.getTypeOfModality());
		cs.setInt(2, serviceModality.getContractId());
		cs.setInt(3, serviceModality.getActivityId());
		cs.setDate(4, Date.valueOf(serviceModality.getReleasedDate()));
		cs.setDouble(5, serviceModality.getPrice());
		cs.execute(); // se ejecuta la consulta de la funcion
		int idIsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idIsertado;
	}

	@Override
	public void delete(int idServiceModality) throws SQLException {
		ModalityDAO.getInstancie().delete(idServiceModality);
	}

	@Override
	public void update(ServiceModality serviceModality) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_service_modality(?, ?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, serviceModality.getId());
		cs.setInt(2, serviceModality.getActivityId());
		cs.setDate(3, Date.valueOf(serviceModality.getReleasedDate()) );
		cs.setDouble(4, serviceModality.getPrice());
		cs.execute(); // se ejecuta la consulta de la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public ServiceModality select(int idServiceModality) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_service_modality(?)}");
		ServiceModality serviceModality = null;
		cs.setInt(1, idServiceModality); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			serviceModality = mapEntity(cs);
		cs.close(); // se cierra la llamada a la funcion

		return serviceModality;
	}

	@Override
	public List<ServiceModality> selectAll() throws SQLException {
		List<ServiceModality> listServiceModality = new ArrayList<ServiceModality>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_service_modality()}");
		cs.execute(); // se ejecuta la consulta de la funcion

		while (cs.getResultSet().next()) {
			listServiceModality.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listServiceModality;
	}

	@Override
	public List<ServiceModality> selectIntoServiceContract(int idServiceContract) throws SQLException {
		List<ServiceModality> listServiceModality = new ArrayList<ServiceModality>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_service_modality_service_contract(?)}");
		cs.setInt(1, idServiceContract); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de la funcion

		while (cs.getResultSet().next()) {
			listServiceModality.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listServiceModality;
	}

	@Override
	public List<ServiceModality> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<ServiceModality> listServiceModality = new ArrayList<ServiceModality>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_service_modality_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de la funcion

		while (cs.getResultSet().next()) {
			listServiceModality.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listServiceModality;
	}

	@Override
	public ServiceModality mapEntity(CallableStatement cs) throws SQLException {
		ServiceModality serviceModality = this.cache.get(cs.getInt("modality_id"));

		if (serviceModality == null) { // Si no se encuentra en cache una referencia con ese id
			serviceModality = new ServiceModality(cs.getResultSet().getInt("modality_id"), ServiceContractDAO.getInstancie().select(cs.getResultSet().getInt("service_contract_id")), cs.getResultSet().getString("type_of_modality"), 
					ActivityDAO.getInstancie().select(cs.getInt("activity_id")), cs.getResultSet().getDate("release_date").toLocalDate(), cs.getResultSet().getDouble("price"));

			this.cache.put(serviceModality.getId(), serviceModality); // se alamacena en cache la referencia de la modalidad de servicio
		}

		return serviceModality;
	}

}
