package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.CarrierContract;
import logica.TransportModality;

public class TransportModalityDAO implements TransportModalityDAOInterface{
	private static TransportModalityDAO transportModalityDAO;
	private CarrierContract carrierContract;
	// PATRON SINGLENTON
	private TransportModalityDAO () {

	}

	public static TransportModalityDAO getInstancie () {
		if (transportModalityDAO == null)
			transportModalityDAO = new TransportModalityDAO();

		return transportModalityDAO;
	}

	// ESTOS DOS METODOS SE PUEDEN HACER MAS EFICIENTES PASANDO LA LISTA RESULTANTE POR PARAMETRO A LOS METODOS DE SELECCION DE LAS MODALIDADES DE TRANSPORTE VIEJAS
	@Override
	public List<TransportModality> selectIntoCarrierContract(CarrierContract carrierContract) throws SQLException {
		this.carrierContract = carrierContract;
		List<TransportModality> listTransportModality = new ArrayList<TransportModality>();
		// Se obtienen las lista respectivas de cada modalidad hija
		listTransportModality.addAll(CostKilometersDAO.getInstancie().selectIntoCarrierContract(this.carrierContract)); // se añade la lista de modalidades de transporte de tipo costo por kilometraje
		listTransportModality.addAll(EstablishedRouteDAO.getInstancie().selectIntoCarrierContract(this.carrierContract)); // se añade la lista de modalidades de transporte de tipo recorrido establecido
		listTransportModality.addAll(HoursKilometersDAO.getInstancie().selectIntoCarrierContract(this.carrierContract)); // se añade la lista de modalidades de transporte de tipo horas por kilometraje

		return listTransportModality;
	}

	@Override
	public List<TransportModality> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<TransportModality> listTransportModality = new ArrayList<TransportModality>();
		// Se obtienen las lista respectivas de cada modalidad hija
		listTransportModality.addAll(CostKilometersDAO.getInstancie().selectIntoTouristPackage(idTouristPackage)); // se añade la lista de modalidades de transporte de tipo costo por kilometraje
		listTransportModality.addAll(EstablishedRouteDAO.getInstancie().selectIntoTouristPackage(idTouristPackage)); // se añade la lista de modalidades de transporte de tipo recorrido establecido
		listTransportModality.addAll(HoursKilometersDAO.getInstancie().selectIntoTouristPackage(idTouristPackage)); // se añade la lista de modalidades de transporte de tipo horas por kilometraje

		return listTransportModality;
	}

}
