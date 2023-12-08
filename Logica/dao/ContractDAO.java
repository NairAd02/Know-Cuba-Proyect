package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;

import utils.ConnectionDataBase;

public class ContractDAO implements ContractDAOInterface {
	private static ContractDAO contractDAO;
	

	// PATRON SINGLENTON
	private ContractDAO () {
		
	}

	public static ContractDAO getInstancie () {
		if (contractDAO == null)
			contractDAO = new ContractDAO();

		return contractDAO;
	}
	
	@Override
	public void delete(int idContract) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_contract(?)}");
		cs.setInt(1, idContract); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

}
