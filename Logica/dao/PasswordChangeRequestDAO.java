package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.PasswordChangeRequest;
import utils.ConnectionDataBase;

public class PasswordChangeRequestDAO implements PasswordChangeRequestDAOInterface {
	private static PasswordChangeRequestDAO passwordChangeRequestDAO;
	private HashMap<Integer, PasswordChangeRequest> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private PasswordChangeRequestDAO () {
		this.cache = new HashMap<Integer, PasswordChangeRequest>();
	}

	public static PasswordChangeRequestDAO getInstancie () {
		if (passwordChangeRequestDAO == null)
			passwordChangeRequestDAO = new PasswordChangeRequestDAO();

		return passwordChangeRequestDAO;
	}

	@Override
	public int insert(PasswordChangeRequest passwordChangeRequest) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_password_change_request(?, ?, ?, ?)}");
		// se definen los parametros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setString(2, passwordChangeRequest.getTypeRequest());
		cs.setDate(3, Date.valueOf(passwordChangeRequest.getDateRequest()));
		cs.setInt(4, passwordChangeRequest.getUserId());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idPasswordChangeRequest) throws SQLException {
		RequestDAO.getInstancie().delete(idPasswordChangeRequest);	
	}

	@Override
	public void update(PasswordChangeRequest entidad) throws SQLException {
		// No tiene implementacion

	}

	@Override
	public PasswordChangeRequest select(int idPasswordChangeRequest) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_password_change_request(?)}");
		PasswordChangeRequest passwordChangeRequest = null;
		cs.setInt(1, idPasswordChangeRequest); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion

		if (cs.getResultSet().next()) // se situa el puntero
			passwordChangeRequest = mapEntity(cs);

		cs.close(); // se cierra la llamada a la funcion


		return passwordChangeRequest;
	}

	@Override
	public List<PasswordChangeRequest> selectAll() throws SQLException {
		List<PasswordChangeRequest> listPasswordChangeRequests = new ArrayList<PasswordChangeRequest>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_password_change_request()}");
		cs.execute(); // se ejecuta la consulta de llamada a la funcion

		while (cs.getResultSet().next()) {
			listPasswordChangeRequests.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listPasswordChangeRequests;
	}
	
	@Override
	public List<PasswordChangeRequest> selectIntoUser(int idUser) throws SQLException {
		List<PasswordChangeRequest> listPasswordChangeRequests = new ArrayList<PasswordChangeRequest>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_password_change_request_user(?)}");
		cs.setInt(1, idUser); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion

		while (cs.getResultSet().next()) {
			listPasswordChangeRequests.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listPasswordChangeRequests;
	}

	@Override
	public PasswordChangeRequest mapEntity(CallableStatement cs) throws SQLException {
		PasswordChangeRequest passwordChangeRequest = this.cache.get(cs.getResultSet().getInt("id"));

		if (passwordChangeRequest == null) {
			passwordChangeRequest = new PasswordChangeRequest(cs.getResultSet().getInt("id"), cs.getResultSet().getString("type_request"), 
					cs.getResultSet().getDate("date_request").toLocalDate(), cs.getResultSet().getInt("user_id"));

			this.cache.put(passwordChangeRequest.getId(), passwordChangeRequest); // se almacena en chace la referencia de la solicitud de cambio de contraseña
		}

		return passwordChangeRequest;
	}



}
