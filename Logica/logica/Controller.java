package logica;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	private static Controller controller; // Singlenton
	private User user; // atributo que representa al usuario logeado en el sistema
	private ArrayList<User> users; // atributo que representa los usuarios del sistema (Solo cargados con el Rol "Administrator")
	private ArrayList<Rol> roles; // atributo que representa los roles del sistema (Solo cargados con el Rol "Administrator")
	private TouristAgency touristAgency;
	
	
	private Controller (User user) throws SQLException {
		this.user = user;
		if (this.user instanceof Administrator) {
			this.cargarUsuarios();
			this.cargarRoles();
		}
		this.touristAgency = new TouristAgency();
	}
	
	
	public static Controller getInstancie (User user) throws SQLException {
		if (controller == null)
			controller = new Controller(user);
		
		return controller;
	}
	
	public static Controller getInstancie () throws SQLException {
			
		return controller;
	}

	// Metodos de Carga de Informacion 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Rol> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<Rol> roles) {
		this.roles = roles;
	}

	public TouristAgency getTouristAgency() {
		return touristAgency;
	}

	public void setTouristAgency(TouristAgency touristAgency) {
		this.touristAgency = touristAgency;
	}




	public void cargarUsuarios () {
		
	}
	
	public void cargarRoles () {
		
	}
	// Fin Metodos de Carga de Informacion 
	
}
