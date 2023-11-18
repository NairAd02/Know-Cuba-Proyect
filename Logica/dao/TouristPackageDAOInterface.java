package dao;

import logica.TouristPackage;

public interface TouristPackageDAOInterface {
	public boolean insert(TouristPackage touristPackage);
	public boolean delete(TouristPackage touristPackage);
	public boolean update(TouristPackage touristPackage);
	public boolean select(int idTouristPackage);
	public boolean selectAll();
}
