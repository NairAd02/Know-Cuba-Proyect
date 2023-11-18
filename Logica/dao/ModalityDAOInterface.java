package dao;

import logica.Modality;

public interface ModalityDAOInterface {
	public boolean insert(Modality modality);
	public boolean delete(Modality modality);
	public boolean update(Modality modality);
	public boolean select(int idModality);
	public boolean selectAll();
}
