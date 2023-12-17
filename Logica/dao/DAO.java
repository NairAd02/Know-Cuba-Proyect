package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;


public interface DAO <T> {
	public int insert(T entidad) throws SQLException; // Método para la insercción de un registro en la base de datos
	public void delete(int idEntidad) throws SQLException; // Método para la eliminación de un registro en la base de datos
	public void update(T entidad) throws SQLException; // Método para la actualización del registro en la base de datos
	public T select (int idEntidad) throws SQLException; // Método para seleccionar un registro en específico entidad en la base de datos
	public List<T> selectAll() throws SQLException; // Método para seleccionar todos los registros de una tabla de la base de datos
	public T mapEntity (CallableStatement cs) throws SQLException; // Método encargado del mapeo
}
