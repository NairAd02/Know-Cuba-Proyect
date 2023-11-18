package dao;

import logica.Hotel;

public interface HotelDAOInterface {
	public boolean insert(Hotel hotel);
	public boolean delete(Hotel hotel);
	public boolean update(Hotel hotel);
	public boolean select(int idHotel);
	public boolean selectAll();
}
