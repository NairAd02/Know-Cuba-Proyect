package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import utils.ConnectionDataBase;

public class ProviderDAO implements ProviderDAOInterface {
	private static ProviderDAO providerDAO;
	

	// PATRON SINGLENTON
	private ProviderDAO () {

	}

	public static ProviderDAO getInstancie () {
		if (providerDAO == null)
			providerDAO = new ProviderDAO();

		return providerDAO;
	}
	
	@Override
	public void delete(int idProvider) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_provider(?)}");
		cs.setInt(1, idProvider); // se define el parametro de la funcion
		cs.execute(); // se ejectuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

}
