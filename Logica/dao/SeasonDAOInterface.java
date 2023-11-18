package dao;

import logica.Season;

public interface SeasonDAOInterface  {
	public boolean insert(Season season);
	public boolean delete(Season season);
	public boolean update(Season season);
	public boolean select(int idSeason);
	public boolean selectAll();
}
