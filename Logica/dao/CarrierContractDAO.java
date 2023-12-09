package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.CarrierContract;
import logica.TransportModality;
import utils.ConnectionDataBase;

public class CarrierContractDAO implements CarrierContractDAOInterface {
	private static CarrierContractDAO carrierContractDAO;
	private HashMap<Integer, CarrierContract> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private CarrierContractDAO () {
		this.cache = new HashMap<Integer, CarrierContract>();
	}

	public static CarrierContractDAO getInstancie () {
		if (carrierContractDAO == null)
			carrierContractDAO = new CarrierContractDAO();

		return carrierContractDAO;
	}

	@Override
	public int insert(CarrierContract carrierContract) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_carrier_contract(?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setDate(1, Date.valueOf(carrierContract.getStartDate()));
		cs.setDate(2, Date.valueOf(carrierContract.getTerminationDate()));
		cs.setString(3, carrierContract.getDescription());
		cs.setString(4, carrierContract.getTypeOfContract());
		cs.setInt(5, carrierContract.getProviderId());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idCarrierContract) throws SQLException {
		ContractDAO.getInstancie().delete(idCarrierContract);
	}

	@Override
	public void update(CarrierContract carrierContract) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_carrier_contract(?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setDate(1, Date.valueOf(carrierContract.getStartDate()));
		cs.setDate(2, Date.valueOf(carrierContract.getTerminationDate()));
		cs.setString(3, carrierContract.getDescription());
		cs.setInt(4, carrierContract.getId());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public CarrierContract select(int idCarrierContract) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_carrier_contract(?)}");
		cs.setInt(1, idCarrierContract); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero
		CarrierContract carrierContract = mapEntity(cs); // se mapea el objeto
		cs.close(); // se cierra la llamada a la funcion

		return carrierContract;
	}

	@Override
	public List<CarrierContract> selectAll() throws SQLException {
		List<CarrierContract> listCarrierContract = new ArrayList<CarrierContract>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_carrier_contract()}");
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		
		while (cs.getResultSet().next()) {
		listCarrierContract.add(mapEntity(cs)); // se añade a la lista la entidad mapeada a objeto	
		}
		
		cs.close(); // se cierra la llamada a la funcion
		
		return listCarrierContract;
	}

	@Override
	public CarrierContract mapEntity(CallableStatement cs) throws SQLException {
		CarrierContract carrierContract = this.cache.get(cs.getResultSet().getInt("id_contract"));

		if (carrierContract == null) { // si no se encuentra referenciado en cache
			carrierContract = new CarrierContract(cs.getResultSet().getInt("id_contract"), cs.getResultSet().getDate("contract_start_date").toLocalDate(), 
					cs.getResultSet().getDate("contract_termination_date").toLocalDate(), cs.getResultSet().getDate("contract_reconcilation_date").toLocalDate(), cs.getResultSet().getString("contract_description"), 
					TransportationProviderDAO.getInstancie().select(cs.getResultSet().getInt("transportation_provider_id")), 
				( ArrayList<TransportModality> )  TransportModalityDAO.getInstancie().selectIntoCarrierContract(cs.getResultSet().getInt("id_contract")), 
					cs.getResultSet().getBoolean("state"), cs.getResultSet().getString("type_of_contract")); // se crea una nueva referencia

			this.cache.put(carrierContract.getId(), carrierContract); // se alamacena la referencia del contrato de transportista en cache
		}

		return carrierContract;
	}

}