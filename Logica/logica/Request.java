package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.RequestDAO;

public abstract class Request implements DUILogic {
	private int id;
	private String typeRequest;
	private LocalDate dateRequest;
	private int userId;

	public Request(int id, String typeRequest, LocalDate dateRequest, int userId) { // Constructor a nivel de base de datos
		this.typeRequest = typeRequest;
		this.dateRequest = dateRequest;
		this.userId = userId;
	}

	public Request(String typeRequest, LocalDate dateRequest, int userId) { // Constructor a nivel de logica
		this.typeRequest = typeRequest;
		this.dateRequest = dateRequest;
		this.userId = userId;

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeRequest() {
		return typeRequest;
	}
	public void setTypeRequest(String typeRequest) {
		this.typeRequest = typeRequest;
	}
	public LocalDate getDateRequest() {
		return dateRequest;
	}
	public void setDateRequest(LocalDate dateRequest) {
		this.dateRequest = dateRequest;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public void delete() throws SQLException {
		RequestDAO.getInstancie().delete(this.id);
	}

}
