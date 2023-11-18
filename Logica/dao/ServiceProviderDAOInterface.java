package dao;

import logica.ServiceProvider;

public interface ServiceProviderDAOInterface {
	public boolean insert(ServiceProvider serviceProvider);
	public boolean delete(ServiceProvider serviceProvider);
	public boolean update(ServiceProvider serviceProvider);
	public boolean select(int idServiceProvider);
	public boolean selectAll();
}
