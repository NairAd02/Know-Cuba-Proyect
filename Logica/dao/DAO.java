package dao;

import java.util.List;

public interface DAO <T> {
	public int insert(T entidad);
	public int delete(T entidad);
	public int update(T entidad);
	public T select(int idEntidad);
	public List<T> selectAll();
}
