package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;


public interface DAO <T> {
	public int insert(T entidad) throws SQLException;
	public void delete(int idEntidad) throws SQLException;
	public void update(T entidad) throws SQLException;
	public T select (int idEntidad) throws SQLException;
	public List<T> selectAll() throws SQLException;
	public T mapEntity (CallableStatement cs) throws SQLException; // Metodo encargado del mapeo (Explicacion del mapeo en la documentación del proyecto)
}
