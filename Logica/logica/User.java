package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.UserDAO;

public abstract class User implements DUILogic {
	// Atributos estaticos para el hash
	public static int administrator = 1;
	public static int manager = 2;
	public static int dependent = 3;
	public static int packageDesigner = 4;

	public static ArrayList<Integer> getKeys () {
		ArrayList<Integer> keys = new ArrayList<Integer>(4);
		keys.add(administrator);
		keys.add(dependent);
		keys.add(manager);
		keys.add(packageDesigner);

		return keys;
	}
	// fin
	protected int id;
	protected String userName;
	protected String password;
	protected Rol rol;
	protected LocalDate startDateConnection;
	protected LocalDate lastDateConnection;
	protected boolean connected;


	public User(int id, String userName, String password, Rol rol, LocalDate startDateConnection,
			LocalDate lastDateConnection, boolean connected) { // constructor nivel de base de datos
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.rol = rol;
		this.startDateConnection = startDateConnection;
		this.lastDateConnection = lastDateConnection;
		this.connected = connected;
	}

	public User(String userName, String password, Rol rol) { // constructor nivel de logica
		super();
		this.id = -1;
		this.userName = userName;
		this.password = password;
		this.rol = rol;
		this.startDateConnection = null;
		this.lastDateConnection = null;
		this.connected = false;
	}

	public void establecerConexion () throws SQLException { // metodo para indicar que el usuario se logueo al sistema
		if (this.startDateConnection == null)
			this.startDateConnection = LocalDate.now();
		this.lastDateConnection = LocalDate.now();
		this.connected = true;
		this.update(); // se actualiza la informacion del usuario
	}

	public void cerrarConexion () throws SQLException { // metodo para indicar que el usuario cerró sesión 
		this.connected = false;
		this.update(); // se actualiza la informacion del usuario
	}



	public  void delete() throws SQLException { // Metodo para eliminar al usuario en la base de datos
		UserDAO.getInstancie().delete(this.id);
	}


	public String getNameRol () {
		return this.rol.getName();
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
