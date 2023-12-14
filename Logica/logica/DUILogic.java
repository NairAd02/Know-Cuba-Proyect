package logica;

import java.sql.SQLException;

public interface DUILogic {
	public void insert() throws SQLException; // Metodo para insertar el objeto en la base de datos
	public void update() throws SQLException;  // Metodo para actualizar el objeto en la base de datos
	public void delete() throws SQLException;  // Metodo para eliminar el objeto en la base de datos
}
