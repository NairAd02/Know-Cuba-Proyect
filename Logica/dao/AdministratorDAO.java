package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.Administrator;
import utils.ConnectionDataBase;

public class AdministratorDAO implements AdministratorDAOInterface {
	private static AdministratorDAO administratorDAO;
	private HashMap<Integer, Administrator> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private AdministratorDAO () {
		this.cache = new HashMap<Integer, Administrator>();
	}

	public static AdministratorDAO getInstancie () {
		if (administratorDAO == null)
			administratorDAO = new AdministratorDAO();

		return administratorDAO;
	}

	@Override
	public int insert(Administrator administrator) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_administrator(?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setString(2, administrator.getUserName());
		cs.setString(3, administrator.getPassword());
		cs.setInt(4, administrator.getRolId());
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idAdministrator) throws SQLException {
		UserDAO.getInstancie().delete(idAdministrator);
	}

	@Override
	public void update(Administrator administrator) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_administrator(?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setInt(1, administrator.getId());
		cs.setString(2, administrator.getUserName());
		cs.setString(3, administrator.getPassword());
		cs.setDate(4, Date.valueOf(administrator.getStartDateConnection()));
		cs.setDate(5, Date.valueOf(administrator.getLastDateConnection()));
		cs.setBoolean(6, administrator.isConnected());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public Administrator select(int idAdministrator) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_administrator(?)}");
		Administrator administrator = null;
		cs.setInt(1, idAdministrator); // se define el paraemtro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			administrator = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion

		return administrator;
	}

	@Override
	public List<Administrator> selectAll() throws SQLException {
		List<Administrator> listAdministrator = new ArrayList<Administrator>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_administrator()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listAdministrator.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listAdministrator;
	}

	@Override
	public Administrator mapEntity(CallableStatement cs) throws SQLException {
		Administrator administrator = this.cache.get(cs.getResultSet().getInt("id"));

		if (administrator == null) { // si no se encuentra una referencia con ese id
			if (cs.getResultSet().getDate("start_date_connection") == null || cs.getResultSet().getDate("last_date_connection") == null )
				administrator = new Administrator(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
						null, null, cs.getResultSet().getBoolean("connected")); // se crea una nueva referencia para el objeto
			else
				administrator = new Administrator(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
						cs.getResultSet().getDate("start_date_connection").toLocalDate(), cs.getResultSet().getDate("last_date_connection").toLocalDate(), cs.getResultSet().getBoolean("connected")); // se crea una nueva referencia para el objeto
			this.cache.put(administrator.getId(), administrator); // se almacena en cache la referencia que representa a la entidad usuario

		}

		return administrator;
	}
}
