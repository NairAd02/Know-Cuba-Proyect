package logica;

import java.sql.SQLException;
import dao.ActivityDAO;


public class Activity implements DUILogic {
	private int id;
	private String name;
	private String description;
	private int idServiceProvider;


	public Activity(int id, String name, String description, int idServiceProvider) { // Constructor a nivel de base de datos
		this.id = id;
		this.name = name;
		this.description = description;
		this.idServiceProvider = idServiceProvider;
	}

	public Activity(String name, String description, int idServiceProvider) { // Constructor a nivel de logica
		this.name = name;
		this.description = description;
		this.idServiceProvider = idServiceProvider;
	}

	public Activity(String name, String description) { // Constructor a nivel de logica (proceso de creacion del objeto)
		this.name = name;
		this.description = description;
	}

	public void actualizarCampos (String name, String description, int idServiceProvider) { // Metodo para actualizar los campos de la clase
		this.name = name;
		this.description = description;
		this.idServiceProvider = idServiceProvider;
	}

	public Activity (String name) { // Constructor temporal para los filtros
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return this.name;
	}

}
