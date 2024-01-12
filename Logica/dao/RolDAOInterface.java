package dao;

import java.sql.SQLException;
import java.util.List;

import logica.Rol;

public interface RolDAOInterface extends DAO<Rol> {
public List<Rol> selectAllDiferentAdministrator () throws SQLException;
}
