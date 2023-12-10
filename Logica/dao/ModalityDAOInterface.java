package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import logica.Modality;

public interface ModalityDAOInterface {
	public void insertIntoTouristPackage (int idModality, int idTouristPackage) throws SQLException;
	public void delete (int idModality) throws SQLException; // Metodo para eliminar una modalidad
	public void deleteFromTouristPackage (int idModality, int idTouristPackage) throws SQLException; // Metodo para eliminar una modalidad de un paquete turistico
	public HashMap<Integer, ArrayList<Modality>> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para obtener todas las modalidades de un paquete turistico especifico
}
