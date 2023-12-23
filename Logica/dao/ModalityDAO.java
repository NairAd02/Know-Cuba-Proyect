package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_modality(?)}");
		cs.setInt(1, idModality); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public void deleteFromTouristPackage(int idModality, int idTouristPackage) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_modality_tourist_package(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, idModality);
		cs.setInt(2, idTouristPackage);
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public HashMap<Integer, ArrayList<Modality>> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		HashMap<Integer, ArrayList<Modality>> listModalitys = new HashMap<Integer, ArrayList<Modality>> ();
		listModalitys.put(Modality.accommodationModality, new ArrayList<Modality>(AccommodationModalityDAO.getInstancie().selectIntoTouristPackage(idTouristPackage))); // se obtienen todas las modalidades de alojamiento del paquete turistico
		listModalitys.put(Modality.serviceModality, new ArrayList<Modality>(ServiceModalityDAO.getInstancie().selectIntoTouristPackage(idTouristPackage))); // se obtienen todas las modalidades de servicio del paquete turistico
		listModalitys.put(Modality.costKilometers, new ArrayList<Modality>(CostKilometersDAO.getInstancie().selectIntoTouristPackage(idTouristPackage))); // se obtienen todas las modalidades de transporte tipo costo por kilometraje del paquete turistico
		listModalitys.put(Modality.hoursKilometers, new ArrayList<Modality>(HoursKilometersDAO.getInstancie().selectIntoTouristPackage(idTouristPackage))); // se obtienen todas las modalidades de transporte tipo horas por kilometraje del paquete turistico
		listModalitys.put(Modality.establishedRoute, new ArrayList<Modality>(EstablishedRouteDAO.getInstancie().selectIntoTouristPackage(idTouristPackage))); // se obtienen todas las modalidades de transporte tipo recorridos establecidos del paquete turistico
		return listModalitys;
	}

	@Override
	public void insertIntoTouristPackage(int idModality, int idTouristPackage) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call insert_modality_tourist_package(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, idModality);
		cs.setInt(2, idTouristPackage);
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}
	
}
