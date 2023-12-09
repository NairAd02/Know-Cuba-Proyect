package dao;

import java.sql.SQLException;

public interface ProviderDAOInterface {
public void delete (int idProvider) throws SQLException; // metodo para eliminar un registro de la tabla provedor y sus relaciones
}
