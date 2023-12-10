package logica;

import java.time.LocalDate;

public class User {
	protected int id;
	protected String userName;
	protected String password;
	protected Rol rol;
	protected LocalDate startDateConnection;
	protected LocalDate lastDateConnection;
	protected boolean connected;


	public User(int id, String userName, String password, Rol rol, LocalDate startDateConnection,
			LocalDate lastDateConnection, boolean connected) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.rol = rol;
		this.startDateConnection = startDateConnection;
		this.lastDateConnection = lastDateConnection;
		this.connected = connected;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	public LocalDate getStartDateConnection() {
		return startDateConnection;
	}


	public void setStartDateConnection(LocalDate startDateConnection) {
		this.startDateConnection = startDateConnection;
	}


	public LocalDate getLastDateConnection() {
		return lastDateConnection;
	}


	public void setLastDateConnection(LocalDate lastDateConnection) {
		this.lastDateConnection = lastDateConnection;
	}


	public boolean isConnected() {
		return connected;
	}


	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public int getRolId () {
		return this.rol.getId();
	}

}
