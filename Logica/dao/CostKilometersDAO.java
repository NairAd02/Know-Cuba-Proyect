package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.CostKilometers;
import utils.ConnectionDataBase;

public class CostKilometersDAO implements CostKilometersDAOInterface {
	private static CostKilometersDAO costKilometersDAO;
	private HashMap<Integer, CostKilometers> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private CostKilometersDAO () {
		this.cache = new HashMap<Integer, CostKilometers>();
	}

	public static CostKilometersDAO getInstancie () {
		if (costKilometersDAO == null)
			costKilometersDAO = new CostKilometersDAO();

		return costKilometersDAO;
	}

	@Override
	public int insert(CostKilometers costKilometers) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_transport_modality_cost_kilometers(?, ?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setString(1, costKilometers.getTypeOfModality());
		cs.setInt(2, costKilometers.getVehicleId());
		cs.setInt(3, costKilometers.getContractId());
		cs.setString(4, costKilometers.getTypeTransportModality());
		cs.setDouble(5, costKilometers.getCostKilometersGoing());
		cs.setDouble(6, costKilometers.getCostKilometersLap());
		cs.setDouble(7, costKilometers.getCostHoursWait());
		cs.execute(); // se ejecuta la consulta de llamda a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idCostKilometers) throws SQLException {
		ModalityDAO.getInstancie().delete(idCostKilometers);
	}

	@Override
	public void update(CostKilometers costKilometers) throws SQLException { 
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_transport_modality_cost_kilometers(?, ?, ?, ?, ?)}");
	// Se definen los parametros de la funcion
		cs.setInt(1, costKilometers.getId());
		cs.setInt(2, costKilometers.getVehicleId());
		cs.setDouble(3, costKilometers.getCostKilometersGoing());
		cs.setDouble(4, costKilometers.getCostKilometersLap());
		cs.setDouble(5, costKilometers.getCostHoursWait());
		cs.execute(); // se ejecuta la consulta de llamda a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public CostKilometers select(int idCostKilometers) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transport_modality_cost_kilometers(?)}");
		cs.setInt(1, idCostKilometers); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamda a la funcion
		cs.getResultSet().next(); // se situa el cursor
		
		return null;
	}

	@Override
	public List<CostKilometers> selectAll() throws SQLException {
		List<CostKilometers> listCostKilometers = new ArrayList<CostKilometers>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_transport_modality_cost_kilometers()}");
		cs.execute(); // se ejecuta la consulta de llamda a la funcion
		
		while (cs.getResultSet().next()) {
			listCostKilometers.add(mapEntity(cs));
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listCostKilometers;
	}
	
	@Override
	public List<CostKilometers> selectIntoCarrierContract(int idCarrierContract) throws SQLException {
		List<CostKilometers> listCostKilometers = new ArrayList<CostKilometers>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transports_modalitys_cost_kilometers_carrier_contract(?)}");
		cs.setInt(1, idCarrierContract); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamda a la funcion
		
		while (cs.getResultSet().next()) {
			listCostKilometers.add(mapEntity(cs));
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listCostKilometers;
	}

	@Override
	public List<CostKilometers> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<CostKilometers> listCostKilometers = new ArrayList<CostKilometers>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transport_modality_cost_kilometers_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamda a la funcion
		
		while (cs.getResultSet().next()) {
			listCostKilometers.add(mapEntity(cs));
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listCostKilometers;
	}

	@Override
	public CostKilometers mapEntity(CallableStatement cs) throws SQLException {
		CostKilometers costKilometers = this.cache.get(cs.getResultSet().getInt("modality_id"));
		
		if (costKilometers == null) { // si no esta referenciado en cache
			costKilometers = new CostKilometers(cs.getResultSet().getInt("modality_id"), CarrierContractDAO.getInstancie().select(cs.getResultSet().getInt("carrier_contract_id")), 
					cs.getResultSet().getString("type_of_modality"), VehicleDAO.getInstancie().select(cs.getResultSet().getInt("vehicle_id")), cs.getResultSet().getString("type_transport_modality"), 
					cs.getResultSet().getDouble("cost_kilometers_going"), cs.getResultSet().getDouble("cost_kilometers_lap"), cs.getResultSet().getDouble("cost_hours_wait"));
			
			this.cache.put(costKilometers.getId(), costKilometers); // se alamacena en cache la referencia del la modalidad de transporte
		}
		return null;
	}
	
}