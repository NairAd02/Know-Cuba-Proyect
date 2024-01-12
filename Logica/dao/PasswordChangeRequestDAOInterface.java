package dao;

import java.sql.SQLException;
import java.util.List;
import logica.PasswordChangeRequest;

public interface PasswordChangeRequestDAOInterface extends DAO<PasswordChangeRequest> {
public List<PasswordChangeRequest> selectIntoUser (int idUser) throws SQLException; // metodo para obtener todas las solicitudes de cambio de contraseña de un usuario
}
