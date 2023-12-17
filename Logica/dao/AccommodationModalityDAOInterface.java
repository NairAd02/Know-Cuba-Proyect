package dao;

import java.sql.SQLException;
import java.util.List;

import logica.AccommodationContract;
import logica.AccommodationModality;

public interface AccommodationModalityDAOInterface extends DAO <AccommodationModality> {
public List<AccommodationModality> selectIntoAccommodationContract (AccommodationContract accommodationContract) throws SQLException; // Metodo para seleccionar todas la modalidades de un contrato en especifico
public List<AccommodationModality> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para seleccionar todas las modalidades de una paquete turistico en especifico
}
