package dao;

import java.sql.SQLException;
import java.util.List;

import logica.CarrierContract;
import logica.HoursKilometers;

public interface HoursKilometersDAOInterface extends DAO <HoursKilometers> {
	public List<HoursKilometers> selectIntoCarrierContract (CarrierContract carrierContract) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte tipo horas por kilometraje de un contrato con transportista especifico
	public List<HoursKilometers> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte tipo horas por kilometraje de un paquete turistico especifico
}
