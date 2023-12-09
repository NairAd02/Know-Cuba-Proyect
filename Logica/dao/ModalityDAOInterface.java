package dao;

import java.sql.SQLException;
import java.util.List;
import logica.Modality;

public interface ModalityDAOInterface {
public void delete (int idModality) throws SQLException; // Metodo para eliminar una modalidad
public void deleteFromTouristPackage (int idModality, int idTouristPackage) throws SQLException; // Metodo para eliminar una modalidad de un paquete turistico
public List<Modality> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para obtener todas las modalidades de un paquete turistico especifico
}
