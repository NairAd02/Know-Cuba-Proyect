package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.PasswordChangeRequestDAO;

public class PasswordChangeRequest extends Request {
	
	
	public PasswordChangeRequest(int id, String typeRequest, LocalDate dateRequest, int userId) { // Constructor a nivel de base de datos
		super(id, typeRequest, dateRequest, userId);
		
	}

	public PasswordChangeRequest(String typeRequest, LocalDate dateRequest, int userId) { // Constructor a nivel de logica
		super(typeRequest, dateRequest, userId);
	}

	@Override
	public void insert() throws SQLException {
		PasswordChangeRequestDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		// no tiene implementacion
		
	}

}
