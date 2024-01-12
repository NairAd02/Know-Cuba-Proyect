package dao;

import logica.HotelModality;
import logica.TypeOfRoom;

import java.sql.SQLException;
import java.util.List;

public interface HotelModalityDAOInterface extends DAO<HotelModality> {
    public List<HotelModality> selectIntoHotel (int idHotel) throws SQLException; // Metodo para seleccionar todas las modalidades de hotel de un hotel en especifico
    public void insertIntoHotel (int idHotel, int idHotelModality) throws SQLException; // Metodo para insertar una modalidad de hotel en un hotel en especifico
    public void deleteFromHotel (int idHotel, int idHotelModality) throws SQLException; // Metodo para eliminar una modalidad de hotel de un hotel en especifico
}
