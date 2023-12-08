package dao;

import java.sql.SQLException;
import java.util.List;

import logica.Season;

public interface SeasonDAOInterface extends DAO <Season> {
	public List<Season> selectIntoAccommodationContract (int idAccommodationContract) throws SQLException; // metodo para obtener todas las temoporadas pertenecientes a un contrato
}
