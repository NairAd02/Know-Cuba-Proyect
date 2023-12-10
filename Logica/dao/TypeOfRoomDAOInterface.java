package dao;

import java.sql.SQLException;
import java.util.List;

import logica.TypeOfRoom;

public interface TypeOfRoomDAOInterface extends DAO <TypeOfRoom> {

	public List<TypeOfRoom> selectTypeOfRoomIntoHotel (int idHotel) throws SQLException; // Metodo para seleccionar todos los tipos de habitacion de una hotel en especifico
	public void insertIntoHotel (int idHotel, int idTypeOfRoom) throws SQLException; // Metodo para insertar un tipo de habitacion en un hotel en especifico
	public void deleteFromHotel (int idHotel, int idTypeOfRoom) throws SQLException; // Metodo para eliminar un tipo de habitacion de un hotel en especifico	
}
