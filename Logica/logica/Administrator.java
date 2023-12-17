package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.AdministratorDAO;
import utils.ConnectionDataBase;

public class Administrator extends User {

	public Administrator(int id, String userName, String password, Rol rol, LocalDate startDateConnection,
			LocalDate lastDateConnection, boolean connected) {
		super(id, userName, password, rol, startDateConnection, lastDateConnection, connected);
		// TODO Auto-generated constructor stub
	} // constructor nivel de base de datos

	public Administrator(String userName, String password, Rol rol) {
		super( userName, password, rol);
		// TODO Auto-generated constructor stub
	} // constructor nivel de logica

	@Override
	public void update() throws SQLException {
		AdministratorDAO.getInstancie().update(this);
	}

	@Override
	public void insert() throws SQLException {	
		this.id = AdministratorDAO.getInstancie().insert(this);
	}

	

}
