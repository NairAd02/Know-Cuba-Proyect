package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.HoursKilometers;
import utils.ConnectionDataBase;

public class HoursKilometersDAO implements HoursKilometersDAOInterface {
	private static HoursKilometersDAO hoursKilometersDAO;
	private HashMap<Integer, HoursKilometers> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private HoursKilometersDAO () {
		this.cache = new HashMap<Integer, HoursKilometers>();
	}

	public static HoursKilometersDAO getInstancie () {
		if (hoursKilometersDAO == null)
			hoursKilometersDAO = new HoursKilometersDAO();

		return hoursKilometersDAO;
	}


	@Override
	public int insert(HoursKilometers hoursKilometers) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_transport_modality_hours_kilometers(?, ?, ?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setString(1, hoursKilometers.getTypeOfModality());
		cs.setInt(2, hoursKilometers.getVehicleId());
		cs.setInt(3, hoursKilometers.getContractId());
		cs.setString(4, hoursKilometers.getTypeTransportModality());
		cs.setDouble(5, hoursKilometers.getCostKilometersRout());
		cs.setDouble(6, hoursKilometers.getCostHours());
		cs.setDouble(7, hoursKilometers.getCostKilometersRoutAdditionals());
		cs.setDouble(8, hoursKilometers.getCostHoursAdditionals());
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1);
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idHoursKilometers) throws SQLException {
		ModalityDAO.getInstancie().delete(idHoursKilometers);
	}

	@Override
	public void update(HoursKilometers hoursKilometers) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_transport_modality_hours_kilometers(?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setInt(1, hoursKilometers.getId());
		cs.setInt(2, hoursKilometers.getVehicleId());
		cs.setDouble(3, hoursKilometers.getCostKilometersRout());
		cs.setDouble(4, hoursKilometers.getCostHours());
		cs.setDouble(5, hoursKilometers.getCostKilometersRoutAdditionals());
		cs.setDouble(6, hoursKilometers.getCostHoursAdditionals());
		cs.execute(); // se ejectuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public HoursKilometers select(int idHoursKilometers) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transport_modality_hours_kilometers(?)}");
		cs.setInt(1, idHoursKilometers); // se define el parametro de la funcion
		cs.execute(); // se ejectuta la llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero
		HoursKilometers hoursKilometers = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion
		
		return hoursKilometers;
	}

	@Override
	public List<HoursKilometers> selectAll() throws SQLException {
		List<HoursKilometers> listHoursKilometers = new ArrayList<HoursKilometers>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_transport_modality_hours_kilometers()}");
		cs.execute(); // se ejectuta la llamada a la funcion
		
		while (cs.getResultSet().next()) {
			listHoursKilometers.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listHoursKilometers;
	}
	
	@Override
	public List<HoursKilometers> selectIntoCarrierContract(int idCarrierContract) throws SQLException {
		List<HoursKilometers> listHoursKilometers = new ArrayList<HoursKilometers>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transports_modalitys_hours_kilometers_carrier_contract(?)}");
		cs.setInt(1, idCarrierContract); // se define el parametro de la funcion
		cs.execute(); // se ejectuta la llamada a la funcion
		
		while (cs.getResultSet().next()) {
			listHoursKilometers.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listHoursKilometers;
	}

	@Override
	public List<HoursKilometers> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<HoursKilometers> listHoursKilometers = new ArrayList<HoursKilometers>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_transport_modality_hours_kilometers_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se define el parametro de la funcion
		cs.execute(); // se ejectuta la llamada a la funcion
		
		while (cs.getResultSet().next()) {
			listHoursKilometers.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listHoursKilometers;
	}

	@Override
	public HoursKilometers mapEntity(CallableStatement cs) throws SQLException {
		HoursKilometers hoursKilometers = this.cache.get(cs.getResultSet().getInt("modality_id"));
		
		if (hoursKilometers == null) { // si no se encuentra alamacenada una referencia con ese id
			hoursKilometers = new HoursKilometers(cs.getResultSet().getInt("modality_id"), CarrierContractDAO.getInstancie().select(cs.getResultSet().getInt("carrier_contract_id")), 
					cs.getResultSet().getString("type_of_modality"), VehicleDAO.getInstancie().select(cs.getResultSet().getInt("vehicle_id")), cs.getString("type_transport_modality"), 
					cs.getResultSet().getDouble("cost_kilometers_rout"), cs.getResultSet().getDouble("cost_hours"), cs.getResultSet().getDouble("cost_kilometers_rout_additionals"), 
					cs.getResultSet().getDouble("cost_hours_additionals")); // se crea una nueva referencia para la modalidad con ese id
			
			this.cache.put(hoursKilometers.getId(), hoursKilometers); // se alamacena en cache la referencia de la modalidad de transporte
		}
		
		return hoursKilometers;
	}

}