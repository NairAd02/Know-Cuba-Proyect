package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logica.CarrierContract;
import logica.EstablishedRoute;
import utils.ConnectionDataBase;

public class EstablishedRouteDAO implements EstablishedRouteDAOInterface {
	private static EstablishedRouteDAO establishedRouteDAO;
	private HashMap<Integer, EstablishedRoute> cache; // Atributo para guardar en cache cada referencia creada
	private CarrierContract carrierContract;

	// PATRON SINGLENTON
	private EstablishedRouteDAO () {
		this.cache = new HashMap<Integer, EstablishedRoute>();
	}

	public static EstablishedRouteDAO getInstancie () {
		if (establishedRouteDAO == null)
			establishedRouteDAO = new EstablishedRouteDAO();

		return establishedRouteDAO;
	}

	@Override
	public int insert(EstablishedRoute establishedRoute) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_transport_modality_established_route(?, ?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setString(1, establishedRoute.getTypeOfModality());
		cs.setInt(2, establishedRoute.getVehicleId());
		cs.setInt(3, establishedRoute.getContractId());
		cs.setString(4, establishedRoute.getTypeTransportModality());
		cs.setString(5, establishedRoute.getDescriptionRout());
		cs.setDouble(6, establishedRoute.getCostGoing());
		cs.setDouble(7, establishedRoute.getCostLap());
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idEstablishedRoute) throws SQLException {
		ModalityDAO.getInstancie().delete(idEstablishedRoute);
	}

	@Override
	public void update(EstablishedRoute establishedRoute) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_transport_modality_established_route(?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setInt(1, establishedRoute.getId());
		cs.setInt(2, establishedRoute.getVehicleId());
		cs.setString(3, establishedRoute.getDescriptionRout());
		cs.setDouble(4, establishedRoute.getCostGoing());
		cs.setDouble(5, establishedRoute.getCostLap());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public EstablishedRoute select(int idEstablishedRoute) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transport_modality_established_route(?)}");
		EstablishedRoute establishedRoute = null;
		cs.setInt(1, idEstablishedRoute); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		if (cs.getResultSet().next())
			establishedRoute = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion

		return establishedRoute;
	}

	@Override
	public List<EstablishedRoute> selectAll() throws SQLException {
		List<EstablishedRoute> listEstablishedRoute = new ArrayList<EstablishedRoute>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_transport_modality_established_route()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listEstablishedRoute.add(mapEntity(cs)); //se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listEstablishedRoute;
	}

	@Override
	public List<EstablishedRoute> selectIntoCarrierContract(CarrierContract carrierContract) throws SQLException {
		this.carrierContract = carrierContract;
		List<EstablishedRoute> listEstablishedRoute = new ArrayList<EstablishedRoute>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transports_modalitys_established_route_carrier_contract(?)}");
		cs.setInt(1, this.carrierContract.getId()); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listEstablishedRoute.add(mapEntity(cs)); //se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listEstablishedRoute;
	}

	@Override
	public List<EstablishedRoute> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<EstablishedRoute> listEstablishedRoute = new ArrayList<EstablishedRoute>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transport_modality_established_route_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listEstablishedRoute.add(mapEntity(cs)); //se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listEstablishedRoute;
	}

	@Override
	public EstablishedRoute mapEntity(CallableStatement cs) throws SQLException {
		EstablishedRoute establishedRoute = this.cache.get(cs.getResultSet().getInt("modality_id"));

		if (establishedRoute == null) { // se no se encuentra en cache una referencia con ese id
			establishedRoute = new EstablishedRoute(cs.getResultSet().getInt("modality_id"), this.carrierContract, 
					cs.getResultSet().getString("type_of_modality"), VehicleDAO.getInstancie().select(cs.getResultSet().getInt("vehicle_id")), cs.getResultSet().getString("type_transport_modality"), 
					cs.getResultSet().getString("description_rout"), cs.getResultSet().getDouble("cost_going"), cs.getResultSet().getDouble("cost_lap"));

			this.cache.put(establishedRoute.getId(), establishedRoute); // se alamacena en cache la referencia de la modalidad de transporte

		}

		return establishedRoute;
	}
}
