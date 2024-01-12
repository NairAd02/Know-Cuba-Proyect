package dao;

import java.sql.SQLException;
import java.util.List;
import logica.Request;


public interface RequestDAOInterface{
public void delete (int idRequest) throws SQLException;
public List<Request> selectIntoUser (int idUser) throws SQLException; // metodo para obtener todas las solicitudes de un usuario
}
