package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Request;
import utils.ConnectionDataBase;

public class RequestDAO implements RequestDAOInterface {
	private static RequestDAO requestDAO;


	// PATRON SINGLENTON
	private RequestDAO () {

	}

	public static RequestDAO getInstancie () {
		if (requestDAO == null)
			requestDAO = new RequestDAO();

		return requestDAO;
	}
	@Override
	public void delete(int idRequest) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_request(?)}");
		cs.setInt(1, idRequest); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion	
	}

	@Override
	public List<Request> selectIntoUser(int idUser) throws SQLException { // Implementacion temporal
		List<Request> listRequest = new ArrayList<Request>(PasswordChangeRequestDAO.getInstancie().selectIntoUser(idUser));

		return listRequest;
	}

}
