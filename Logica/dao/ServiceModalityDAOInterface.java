package dao;

import java.sql.SQLException;
import java.util.List;
import logica.ServiceModality;

public interface ServiceModalityDAOInterface extends DAO <ServiceModality> {
public List<ServiceModality> selectIntoServiceContract (int idServiceContract) throws SQLException; // Metodo para obtener todas las modalidades de servicio de un contrato de servicio dado
public List<ServiceModality> selectIntoTouristPackage (int idTouristPackage) throws SQLException; // metodo para obtener todas las modalidades de servicio de un paquete turistico dado
}
