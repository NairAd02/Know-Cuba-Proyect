package dao;

import java.sql.SQLException;
import java.util.List;

import logica.Vehicle;

public interface VehicleDAOInterface extends DAO<Vehicle> {
public List<Vehicle> selectIntoTransportationProvider (int idTransportationProvider) throws SQLException; // Metodo para obtener los vehiculos de un proveedor de transporte en especifico
public List<Vehicle> selectIntoTransportModality (int idTransportModality) throws SQLException; // Metodo para obtener los vehiculos de una modalidad de transporte en especifico
public void insertIntoTransportModality (int idTransportModality, int idVehicle) throws SQLException; // Metodo para insertar un vehiculo en una modalidad de transporte en especifico
    public void deleteIntoTransportModality (int idTransportModality, int idVehicle) throws SQLException; // Metodo para eliminar un vehiculo en una modalidad de transporte en especifico
}
