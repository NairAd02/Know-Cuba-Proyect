package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.ServiceContract;
import utils.ConnectionDataBase;

public class ServiceContractDAO implements ServiceContractDAOInterface{
	private static ServiceContractDAO serviceContractDAO;
	private HashMap<Integer, ServiceContract> cache;

	// PATRON SINGLENTON
	private ServiceContractDAO () {
		this.cache = new HashMap<Integer, ServiceContract>();
	}

	public static ServiceContractDAO getInstancie () {
		if (serviceContractDAO == null)
			serviceContractDAO = new ServiceContractDAO();

		return serviceContractDAO;
	}

	@Override
	public int insert(ServiceContract serviceContract) throws SQLException { 
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_service_contract(?, ?, ?, ?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setDate(2, Date.valueOf(serviceContract.getStartDate()));
		cs.setDate(3, Date.valueOf(serviceContract.getTerminationDate()));
		cs.setString(4, serviceContract.getDescription());
		cs.setString(5, serviceContract.getTypeOfContract());
		cs.setInt(6, serviceContract.getProviderId());
		cs.setDouble(7, serviceContract.getSurcharge());
		cs.execute(); // se ejecuta la consulta de la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idServiceContract) throws SQLException {
		ContractDAO.getInstancie().delete(idServiceContract);	
	}

	@Override
	public void update(ServiceContract serviceContract) throws SQLException { 
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_service_contract(?, ?, ?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.setDate(1, Date.valueOf(serviceContract.getStartDate()));
		cs.setDate(2, Date.valueOf(serviceContract.getTerminationDate()));
		cs.setString(3, serviceContract.getDescription());
		cs.setInt(4, serviceContract.getId());
		cs.setDouble(5, serviceContract.getSurcharge());
		cs.execute(); // se ejecuta la consulta de la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public ServiceContract select(int idServiceContract) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_service_contract(?)}");
		ServiceContract serviceContract = null;
		cs.setInt(1, idServiceContract); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			serviceContract = mapEntity(cs);
		cs.close(); // se cierra la llamada a la funcion

		return serviceContract;
	}

	@Override
	public List<ServiceContract> selectAll() throws SQLException {
		List<ServiceContract> listContracts = new ArrayList<ServiceContract>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_service_contract()}");
		cs.execute(); // se ejecuta la consulta de la funcion

		while (cs.getResultSet().next()) {
			listContracts.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listContracts;
	}

	@Override
	public ServiceContract mapEntity(CallableStatement cs) throws SQLException {
		ServiceContract serviceContract = this.cache.get(cs.getResultSet().getInt("id_contract"));

		if (serviceContract == null) { // se no se encuentra alamacena en cache una referencia con ese id
			if (cs.getResultSet().getDate("contract_reconcilation_date") != null)
				serviceContract = new ServiceContract(cs.getResultSet().getInt("id_contract"), cs.getResultSet().getDate("contract_start_date").toLocalDate(), 
						cs.getResultSet().getDate("contract_termination_date").toLocalDate(), cs.getResultSet().getDate("contract_reconcilation_date").toLocalDate(), 
						cs.getResultSet().getString("contract_description"), ServiceProviderDAO.getInstancie().select(cs.getResultSet().getInt("service_provider_id")), 
						cs.getResultSet().getBoolean("state"), 
						cs.getResultSet().getString("type_of_contract"), cs.getResultSet().getDouble("surcharge"));
			else
				serviceContract = new ServiceContract(cs.getResultSet().getInt("id_contract"), cs.getResultSet().getDate("contract_start_date").toLocalDate(), 
						cs.getResultSet().getDate("contract_termination_date").toLocalDate(), null, 
						cs.getResultSet().getString("contract_description"), ServiceProviderDAO.getInstancie().select(cs.getResultSet().getInt("service_provider_id")), 
						cs.getResultSet().getBoolean("state"), 
						cs.getResultSet().getString("type_of_contract"), cs.getResultSet().getDouble("surcharge"));

			this.cache.put(serviceContract.getId(), serviceContract); // se alamacena en cache la referencia del contrato de servicio
		}

		return serviceContract;
	}

}
