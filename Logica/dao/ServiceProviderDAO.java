package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.Activity;
import logica.ServiceProvider;
import utils.ConnectionDataBase;

public class ServiceProviderDAO implements ServiceProviderDAOInterface {
	private static ServiceProviderDAO serviceProviderDAO;
	private HashMap<Integer, ServiceProvider> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private ServiceProviderDAO () {
		this.cache = new HashMap<Integer, ServiceProvider>();
	}

	public static ServiceProviderDAO getInstancie () {
		if (serviceProviderDAO == null)
			serviceProviderDAO = new ServiceProviderDAO();

		return serviceProviderDAO;
	}

	@Override
	public int insert(ServiceProvider serviceProvider) throws SQLException { 
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_service_provider(?, ?)}");
		// se definen los parametros de la funcion
		cs.setString(1, serviceProvider.getName());
		cs.setString(2, serviceProvider.getProvince());
		cs.execute(); // se ejectuta la llamada a la funcion
		int idIsertado = cs.getInt(1); // se obtiene el retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idIsertado;
	}

	@Override
	public void delete(int idServiceProvider) throws SQLException { 
		ProviderDAO.getInstancie().delete(idServiceProvider);
	}

	@Override
	public void update(ServiceProvider serviceProvider) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_service_provider(?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, serviceProvider.getId());
		cs.setString(2, serviceProvider.getName());
		cs.setString(3, serviceProvider.getProvince());
		cs.execute(); // se ejectuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public ServiceProvider select(int idServiceProvider) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_service_provider(?)}");
		cs.setInt(1, idServiceProvider); // se define el parametro de la funcion
		cs.execute(); // se ejectuta la llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero
		ServiceProvider serviceProvider = mapEntity(cs);
		cs.close(); // se cierra la llamada a la funcion

		return serviceProvider;
	}

	@Override
	public List<ServiceProvider> selectAll() throws SQLException {
		List<ServiceProvider> listServiceProviders = new ArrayList<ServiceProvider>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_service_provider()}");
		cs.execute(); // se ejectuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listServiceProviders.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listServiceProviders;
	}

	@Override
	public ServiceProvider mapEntity(CallableStatement cs) throws SQLException {
		ServiceProvider serviceProvider = this.cache.get(cs.getInt("id"));

		if (serviceProvider == null) { // si no está almacenado en caché
			serviceProvider = new ServiceProvider(cs.getResultSet().getInt("id"), cs.getResultSet().getString("service_name"), cs.getResultSet().getString("province"), 
					(ArrayList<Activity>) ActivityDAO.getInstancie().selectIntoServiceProvider(cs.getResultSet().getInt("id")));

			this.cache.put(serviceProvider.getId(), serviceProvider); // se alamacena en cache la referencia del provedor de servicio
		}

		return serviceProvider;
	}

}
