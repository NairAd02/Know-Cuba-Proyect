package logica;

import java.sql.SQLException;

import dao.ActivityDAO;

public class Activity implements DUILogic {
	private int id;
	private String description;
	private int idServiceProvider;

	public Activity(int id, String description, int idServiceProvider) { // Constructor a nivel de base de datos
		this.id = id;
		this.description = description;
		this.idServiceProvider = idServiceProvider;
	}

	public Activity(String description, int idServiceProvider) { // Constructor a nivel de logica
		this.description = description;
		this.idServiceProvider = idServiceProvider;
	}

	public Activity(String description) { // Constructor a nivel de logica (proceso de creacion del objeto)
		this.description = description;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdServiceProvider() {
		return idServiceProvider;
	}

	public void setIdServiceProvider(int idServiceProvider) {
		this.idServiceProvider = idServiceProvider;
	}

	@Override
	public void insert() throws SQLException {
		this.id = ActivityDAO.getInstancie().insert(this);		
	}

	@Override
	public void update() throws SQLException {
		ActivityDAO.getInstancie().update(this);
	}

	@Override
	public void delete() throws SQLException {
		ActivityDAO.getInstancie().delete(this.id);
	}

	public String toString () { // Metodo para definir como será mostrada la informacion de la clase
		return this.description;
	}

}
