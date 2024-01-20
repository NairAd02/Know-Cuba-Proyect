package logica;

import java.sql.SQLException;
import dao.RolDAO;


public class Rol implements DUILogic {
	private int id;
	private String name;


	public Rol(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Rol (String name) { // Constructor temporal
		this.id = -1;
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String toString () { // metodo para definir como se va a mostrar la informacion de la clase
		return this.name;
	}

	@Override
	public void insert() throws SQLException {
		this.id = RolDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		RolDAO.getInstancie().update(this);
	}

	@Override
	public void delete() throws SQLException {
		RolDAO.getInstancie().delete(this.id);
	}

}
