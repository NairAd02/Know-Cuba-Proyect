package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.TouristPackage;
import utils.ConnectionDataBase;

public class TouristPackageDAO implements TouristPackageDAOInterface{
	private static TouristPackageDAO touristPackageDAO;
	private HashMap<Integer, TouristPackage> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private TouristPackageDAO () {
		this.cache = new HashMap<Integer, TouristPackage>();
	}

	public static TouristPackageDAO getInstancie () {
		if (touristPackageDAO == null)
			touristPackageDAO = new TouristPackageDAO();

		return touristPackageDAO;
	}

	@Override
	public int insert(TouristPackage touristPackage) throws SQLException { 
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_tourist_package(?)}");
		cs.setString(1, touristPackage.getName()); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idTouristPackage) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se establece el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion	
	}

	@Override
	public void update(TouristPackage touristPackage) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_tourist_package(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, touristPackage.getId());
		cs.setString(2, touristPackage.getName());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion	
	}

	@Override
	public TouristPackage select(int idTouristPackage) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_tourist_package(?)}");
		cs.setInt(1, idTouristPackage); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.getResultSet().next(); // se situa el cursor
		TouristPackage touristPackage = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion	

		return touristPackage;
	}

	@Override
	public List<TouristPackage> selectAll() throws SQLException {
		List<TouristPackage> listTouristPackage = new ArrayList<TouristPackage>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_tourist_package()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listTouristPackage.add(mapEntity(cs)); // se añade a la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion	

		return listTouristPackage;
	}

	@Override
	public TouristPackage mapEntity(CallableStatement cs) throws SQLException {
		TouristPackage touristPackage = this.cache.get(cs.getResultSet().getInt("id_tourist_package"));

		if (touristPackage == null) { // si no se encuentra almacenado en cache una referencia con ese id
			touristPackage = new TouristPackage(cs.getResultSet().getInt("id_tourist_package"), cs.getResultSet().getString("name"),
					 ModalityDAO.getInstancie().selectIntoTouristPackage(cs.getResultSet().getInt("id_tourist_package")));

			this.cache.put(touristPackage.getId(), touristPackage); // se almacena en cache la referencia del paquete turistico
		}

		return touristPackage;
	}

}
