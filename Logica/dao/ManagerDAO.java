package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.Manager;
import logica.Request;
import utils.ConnectionDataBase;

public class ManagerDAO implements ManagerDAOInterface {
	private static ManagerDAO managerDAO;
	private HashMap<Integer, Manager> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private ManagerDAO () {
		this.cache = new HashMap<Integer, Manager>();
	}

	public static ManagerDAO getInstancie () {
		if (managerDAO == null)
			managerDAO = new ManagerDAO();

		return managerDAO;
	}

	@Override
	public int insert(Manager manager) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_manager(?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setString(2, manager.getUserName());
		cs.setString(3, manager.getPassword());
		cs.setInt(4, manager.getRolId());
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idManager) throws SQLException {
		UserDAO.getInstancie().delete(idManager);
	}

	@Override
	public void update(Manager manager) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_manager(?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setInt(1, manager.getId());
		cs.setString(2, manager.getUserName());
		cs.setString(3, manager.getPassword());
		cs.setDate(4, Date.valueOf(manager.getStartDateConnection()));
		cs.setDate(5, Date.valueOf(manager.getLastDateConnection()));
		cs.setBoolean(6, manager.isConnected());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public Manager select(int idManager) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_manager(?)}");
		Manager manager = null;
		cs.setInt(1, idManager); // se define el paraemtro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			manager = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion

		return manager;
	}

	@Override
	public List<Manager> selectAll() throws SQLException {
		List<Manager> listManager = new ArrayList<Manager>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_manager()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listManager.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listManager;
	}

	@Override
	public Manager mapEntity(CallableStatement cs) throws SQLException {
		Manager manager = this.cache.get(cs.getResultSet().getInt("id"));

		if (manager == null) { // si no se encuentra una referencia con ese id
			if (cs.getResultSet().getDate("start_date_connection") == null || cs.getResultSet().getDate("last_date_connection") == null )
				manager = new Manager(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
						null, null, cs.getResultSet().getBoolean("connected"), (ArrayList<Request>) RequestDAO.getInstancie().selectIntoUser(cs.getResultSet().getInt("id")), cs.getResultSet().getBoolean("state_password")); // se crea una nueva referencia para el objeto
			else
				manager = new Manager(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
						cs.getResultSet().getDate("start_date_connection").toLocalDate(), cs.getResultSet().getDate("last_date_connection").toLocalDate(), cs.getResultSet().getBoolean("connected"),
						(ArrayList<Request>) RequestDAO.getInstancie().selectIntoUser(cs.getResultSet().getInt("id")), cs.getResultSet().getBoolean("state_password")); // se crea una nueva referencia para el objeto

			this.cache.put(manager.getId(), manager); // se almacena en cache la referencia que representa a la entidad usuario
		}

		return manager;
	}
}
