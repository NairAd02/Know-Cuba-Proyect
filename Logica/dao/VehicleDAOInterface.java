package dao;

import java.sql.SQLException;
import java.util.List;

import logica.Vehicle;

public interface VehicleDAOInterface extends DAO<Vehicle> {
public List<Vehicle> selectIntoTransportationProvider (int idTransportationProvider) throws SQLException;
}
