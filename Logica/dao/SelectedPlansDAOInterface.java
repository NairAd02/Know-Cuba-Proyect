package dao;

import logica.SelectedPlans;

public interface SelectedPlansDAOInterface {
	public boolean insert(SelectedPlans selectedPlans);
	public boolean delete(SelectedPlans selectedPlans);
	public boolean update(SelectedPlans selectedPlans);
	public boolean select(int idSelectedPlans);
	public boolean selectAll();
}
