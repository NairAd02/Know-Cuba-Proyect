package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.AccommodationModality;
import utils.ConnectionDataBase;

public class AccommodationModalityDAO implements AccommodationModalityDAOInterface {
	private static AccommodationModalityDAO accommodationModalityDAO;
	private HashMap<Integer, AccommodationModality> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private AccommodationModalityDAO () {
		this.cache = new HashMap<Integer, AccommodationModality>();
	}

	public static AccommodationModalityDAO getInstancie () {
		if (accommodationModalityDAO == null)
			accommodationModalityDAO = new AccommodationModalityDAO();

		return accommodationModalityDAO;
	}

	@Override
	public int insert(AccommodationModality accommodationModality) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_accommodation_modality(?, ?, ?, ?, ?, ?)}");	
		// se definen los parámetros de la funcion
		cs.setString(1, accommodationModality.getTypeOfModality());
		cs.setInt(2, accommodationModality.getContractId());
		cs.setInt(3, accommodationModality.getTypeOfRoomId());
		cs.setInt(4, accommodationModality.getMealPlanId());
		cs.setDouble(5, accommodationModality.getPrice());
		cs.setInt(6, accommodationModality.getCantDaysAccommodation());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idAccommodationModality) throws SQLException {
		ModalityDAO.getInstancie().delete(idAccommodationModality); // se elimina la modalidad de la base de datos
	}

	@Override
	public void update(AccommodationModality accommodationModality) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call update_accommodation_modality(?, ?, ?, ?, ?)}");
		// se definen los parámetros de la funcion
		cs.setInt(1, accommodationModality.getId());
		cs.setInt(2, accommodationModality.getTypeOfRoomId());
		cs.setInt(3, accommodationModality.getMealPlanId());
		cs.setDouble(4, accommodationModality.getPrice());
		cs.setInt(5, accommodationModality.getCantDaysAccommodation());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public AccommodationModality select(int idAccommodationModality) throws SQLException  { // No se va a utilizar NUNCA
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_accommodation_modality(?)}");

		cs.setInt(1, idAccommodationModality); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero

		AccommodationModality accommodationModality = mapEntity(cs); 

		cs.close(); // se cierra la llamada a la funcion


		return accommodationModality;
	}

	@Override
	public List<AccommodationModality> selectAll() throws SQLException  { 

		List<AccommodationModality> listModalities = new ArrayList<AccommodationModality>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_accommodation_modality()}");
		cs.execute(); // se ejecuta la consulta de llamada a la funcion

		while (cs.getResultSet().next()) {
			listModalities.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listModalities;
	}

	@Override
	public List<AccommodationModality> selectIntoAccommodationContract(int idAccommodationContract) throws SQLException { 
		List<AccommodationModality> listModalities = new ArrayList<AccommodationModality>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_accommodation_modality_accommodation_contract(?)}");
		cs.setInt(1, idAccommodationContract); // se define el parametro de la funcion 
		cs.execute(); // se ejecuta la consulta de llamada a la funcion

		while (cs.getResultSet().next()) {
			listModalities.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listModalities;
	}
	
	@Override
	public List<AccommodationModality> selectIntoTouristPackage(int idTouristPackage) throws SQLException {
		List<AccommodationModality> listModalities = new ArrayList<AccommodationModality>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_accommodation_modality_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se define el parametro de la funcion 
		cs.execute(); // se ejecuta la consulta de llamada a la funcion

		while (cs.getResultSet().next()) {
			listModalities.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listModalities;
	}

	public AccommodationModality mapEntity (CallableStatement cs) throws SQLException { // Metodo encargado del mapeo (Explicacion del mapeo en la documentación del proyecto)
		AccommodationModality accommodationModality = this.cache.get(cs.getResultSet().getInt("id"));

		if (accommodationModality == null) {
			accommodationModality = new AccommodationModality(cs.getResultSet().getInt("id"), AccommodationContractDAO.getInstancie().select(cs.getResultSet().getInt("accommodation_contract_id")), 
					cs.getResultSet().getString("type_of_modality"), TypeOfRoomDAO.getInstancie().select(cs.getResultSet().getInt("type_of_room_id")), 
					MealPlanDAO.getInstancie().select(cs.getResultSet().getInt("meal_plan_id")), cs.getResultSet().getInt("cant_days_accommodation"), cs.getResultSet().getDouble("price"));
			this.cache.put(accommodationModality.getId(), accommodationModality); // se añade a cache
		}

		return accommodationModality;
	}
	
}
