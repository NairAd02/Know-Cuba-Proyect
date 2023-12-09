package dao;

import java.sql.SQLException;
import java.util.List;
import logica.Activity;

public interface ActivityDAOInterface extends DAO <Activity> {

	public List<Activity> selectIntoServiceProvider(int idServiceProvider) throws SQLException;
}
