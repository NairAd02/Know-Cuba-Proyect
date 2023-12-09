package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Modality;
import utils.ConnectionDataBase;

public class ModalityDAO implements ModalityDAOInterface{
	private static ModalityDAO modalityDAO;

	// PATRON SINGLENTON
	private ModalityDAO () {

	}

	public static ModalityDAO getInstancie () {
		if (modalityDAO == null)
			modalityDAO = new ModalityDAO();

		return modalityDAO;
	}
	
	@Override
	public void delete(int idModality) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_modality(?}");
		cs.setInt(1, idModality); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public void deleteFromTouristPackage(int idModality, int idTouristPackage) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_modality_tourist_package(?, ?}");
		// se definen los parametros de la funcion
		cs.setInt(1, idModality);
		cs.setInt(2, idTouristPackage);
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public List<Modality> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<Modality> listModalitys = new ArrayList<Modality>();
		listModalitys.addAll(AccommodationModalityDAO.getInstancie().selectIntoTouristPackage(idTouristPackage)); // se obtienen todas las modalidades de alojamiento del paquete turistico
		listModalitys.addAll(ServiceModalityDAO.getInstancie().selectIntoTouristPackage(idTouristPackage)); // se obtienen todas las modalidades de servicio del paquete turistico
		listModalitys.addAll(TransportModalityDAO.getInstancie().selectIntoTouristPackage(idTouristPackage)); // se obtienen todas las modalidades de transporte del paquete turistico
		
		return listModalitys;
	}

}
