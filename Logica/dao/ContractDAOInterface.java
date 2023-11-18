package dao;


import logica.Contract;

public interface ContractDAOInterface {
	public boolean insert(Contract contract);
	public boolean delete(Contract contract);
	public boolean update(Contract contract);
	public boolean select(int idContract);
	public boolean selectAll();
}
