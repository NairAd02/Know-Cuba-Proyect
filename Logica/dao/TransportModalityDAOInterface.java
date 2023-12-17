package dao;

import java.sql.SQLException;
import java.util.List;

import logica.CarrierContract;
import logica.TransportModality;

public interface TransportModalityDAOInterface {
public List<TransportModality> selectIntoCarrierContract (CarrierContract carrierContract) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte de un contrato con transportista especifico
public List<TransportModality> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // Metodo para obtener todas las modalidades de tranposrte de un paquete turistico especifico
}
