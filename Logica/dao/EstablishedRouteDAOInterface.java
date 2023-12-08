package dao;

import java.sql.SQLException;
import java.util.List;
import logica.EstablishedRoute;


public interface EstablishedRouteDAOInterface extends DAO <EstablishedRoute> {
	public List<EstablishedRoute> selectIntoCarrierContract (int idCarrierContract) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte tipo recorridos establecidos de un contrato con transportista especifico
	public List<EstablishedRoute> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte tipo recorridos establecidos de un paquete turistico especifico
}
