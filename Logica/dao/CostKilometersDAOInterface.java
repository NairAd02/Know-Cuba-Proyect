package dao;

import java.sql.SQLException;
import java.util.List;
import logica.CostKilometers;


public interface CostKilometersDAOInterface extends DAO <CostKilometers> {
	public List<CostKilometers> selectIntoCarrierContract (int idCarrierContract) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte tipo costo por kilometraje de un contrato con transportista especifico
	public List<CostKilometers> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte tipo costo por kilometraje de un paquete turistico especifico
}
