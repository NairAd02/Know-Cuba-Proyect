package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.Dependent;
import utils.ConnectionDataBase;

public class DependentDAO implements DependentDAOInterface {
	private static DependentDAO dependentDAO;
	private HashMap<Integer, Dependent> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private DependentDAO () {
		this.cache = new HashMap<Integer, Dependent>();
	}

	public static DependentDAO getInstancie () {
		if (dependentDAO == null)
			dependentDAO = new DependentDAO();

		return dependentDAO;
	}

	@Override
	public int insert(Dependent dependent) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_dependent(?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setString(1, dependent.getUserName());
		cs.setString(2, dependent.getPassword());
		cs.setInt(3, dependent.getRolId());
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idDependent) throws SQLException {
		UserDAO.getInstancie().delete(idDependent);
	}

	@Override
	public void update(Dependent dependent) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_dependent(?, ?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setInt(1, dependent.getId());
		cs.setString(2, dependent.getUserName());
		cs.setString(3, dependent.getPassword());
		cs.setInt(4, dependent.getRolId());
		cs.setDate(5, Date.valueOf(dependent.getStartDateConnection()));
		cs.setDate(6, Date.valueOf(dependent.getLastDateConnection()));
		cs.setBoolean(7, dependent.isConnected());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public Dependent select(int idDependent) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_dependent(?)}");
		cs.setInt(1, idDependent); // se define el paraemtro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero
		Dependent dependent = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion

		return dependent;
	}

	@Override
	public List<Dependent> selectAll() throws SQLException {
		List<Dependent> listDependents = new ArrayList<Dependent>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_dependent()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listDependents.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listDependents;
	}

	@Override
	public Dependent mapEntity(CallableStatement cs) throws SQLException {
		Dependent dependent = this.cache.get(cs.getResultSet().getInt("id"));

		if (dependent == null) { // si no se encuentra una referencia con ese id
			dependent = new Dependent(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
					cs.getResultSet().getDate("start_date_connection").toLocalDate(), cs.getResultSet().getDate("last_date_connection").toLocalDate(), cs.getResultSet().getBoolean("connected")); // se crea una nueva referencia para el objeto

			this.cache.put(dependent.getId(), dependent); // se almacena en cache la referencia que representa a la entidad usuario

		}

		return dependent;
	}

}
