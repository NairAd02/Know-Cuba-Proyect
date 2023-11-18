package dao;

import logica.Activity;

public interface ActivityDAOInterface {
public boolean insert(Activity activity);
public boolean delete(Activity activity);
public boolean update(Activity activity);
public boolean select(int idActivity);
public boolean selectAll();
}
