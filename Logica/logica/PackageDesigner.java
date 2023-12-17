package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.PackageDesignerDAO;
import utils.ConnectionDataBase;

public class PackageDesigner extends User {

	public PackageDesigner(int id, String userName, String password, Rol rol, LocalDate startDateConnection,
			LocalDate lastDateConnection, boolean connected) {
		super(id, userName, password, rol, startDateConnection, lastDateConnection, connected);
		// TODO Auto-generated constructor stub
	} // constructor nivel de base de datos

	public PackageDesigner(String userName, String password, Rol rol) {
		super(userName, password, rol);
		// TODO Auto-generated constructor stub
	} // constructor nivel de logica

	@Override
	public void update() throws SQLException {
		PackageDesignerDAO.getInstancie().update(this);
	}

	@Override
	public void insert() throws SQLException {	
		this.id = PackageDesignerDAO.getInstancie().insert(this);
	}

}
