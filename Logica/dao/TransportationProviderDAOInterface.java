package dao;

import logica.TransportationProvider;

public interface TransportationProviderDAOInterface {
	public boolean insert(TransportationProvider transportationProvider);
	public boolean delete(TransportationProvider transportationProvider);
	public boolean update(TransportationProvider transportationProvider);
	public boolean select(int idTransportationProvider);
	public boolean selectAll();
}
