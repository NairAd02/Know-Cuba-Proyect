package logica;

import java.sql.SQLException;

import dao.ActivityDAO;

public class Activity implements DUILogic {
	private int id;
	private String description;
	private int idServiceProvider;

	public Activity(int id, String description, int idServiceProvider) {
		super();
		this.id = id;

		this.description = description;
		this.idServiceProvider = idServiceProvider;
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

}
