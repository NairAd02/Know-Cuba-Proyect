package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.TransportationProvider;
import logica.Vehicle;
import utils.ConnectionDataBase;

public class TransportationProviderDAO implements TransportationProviderDAOInterface {
	private static TransportationProviderDAO transportationProviderDAO;
	private HashMap<Integer, TransportationProvider> cache;

	// PATRON SINGLENTON
	private TransportationProviderDAO () {
		this.cache = new HashMap<Integer, TransportationProvider>();
	}

	public static TransportationProviderDAO getInstancie () {
		if (transportationProviderDAO == null)
			transportationProviderDAO = new TransportationProviderDAO();

		return transportationProviderDAO;
	}



	@Override
	public int insert(TransportationProvider transportationProvider) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_transportation_provider(?, ?)}");
		// se definen los parametros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setString(2, transportationProvider.getName());
		cs.setString(3, transportationProvider.getProvince());
		cs.execute(); // se ejecuta la consulta de la funcion
		int idInsertado = cs.getInt(1); // se obtiene el id del provedor de transporte insertado
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idTransportationProvider) throws SQLException {
		ProviderDAO.getInstancie().delete(idTransportationProvider);
	}

	@Override
	public void update(TransportationProvider transportationProvider) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_transportation_provider(?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, transportationProvider.getId());
		cs.setString(2, transportationProvider.getName());
		cs.setString(3, transportationProvider.getProvince());
		cs.execute(); // se ejecuta la consulta de la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public TransportationProvider select(int idTransportationProvider) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transportation_provider(?)}");
		TransportationProvider transportationProvider = null;
		cs.setInt(1, idTransportationProvider); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			transportationProvider = mapEntity(cs);
		cs.close(); // se cierra la llamada a la funcion

		return transportationProvider;

	}

	@Override
	public List<TransportationProvider> selectAll() throws SQLException {
		List<TransportationProvider> listTransportationProvider = new ArrayList<TransportationProvider>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_transportation_provider()}");
		cs.execute(); // se ejecuta la consulta de la funcion

		while (cs.getResultSet().next()) {
			listTransportationProvider.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listTransportationProvider;
	}

	@Override
	public TransportationProvider mapEntity(CallableStatement cs) throws SQLException {
		TransportationProvider transportationProvider = this.cache.get(cs.getResultSet().getInt("id"));

		if (transportationProvider == null) { // si no se encuentra almacenado en cache
			transportationProvider = new TransportationProvider(cs.getResultSet().getInt("id"), cs.getResultSet().getString("provider_name"), cs.getResultSet().getString("province"), 
					(ArrayList<Vehicle>) VehicleDAO.getInstancie().selectIntoTransportationProvider(cs.getResultSet().getInt("id")));

			this.cache.put(transportationProvider.getId(), transportationProvider); // se alamacena en cache la referencia del provedor de transporte
		}
		return transportationProvider;
	}

}
