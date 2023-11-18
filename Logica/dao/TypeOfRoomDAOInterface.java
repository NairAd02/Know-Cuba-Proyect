package dao;

import logica.TypeOfRoom;

public interface TypeOfRoomDAOInterface {
	public boolean insert(TypeOfRoom typeOfRoom);
	public boolean delete(TypeOfRoom typeOfRoom);
	public boolean update(TypeOfRoom typeOfRoom);
	public boolean select(int idTypeOfRoom);
	public boolean selectAll();
}
