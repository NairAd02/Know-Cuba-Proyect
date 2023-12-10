package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import utils.ConnectionDataBase;

public class UserDAO implements UserDAOInterface {
	private static UserDAO userDAO;

	// PATRON SINGLENTON
	private UserDAO () {

	}

	public static UserDAO getInstancie () {
		if (userDAO == null)
			userDAO = new UserDAO();

		return userDAO;
	}

	@Override
	public void delete(int idUser) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_user(?)}");
		cs.setInt(1, idUser); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

}
