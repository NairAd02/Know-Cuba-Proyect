package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.Rol;
import utils.ConnectionDataBase;

public class RolDAO implements RolDAOInterface {
	private static RolDAO rolDAO;
	private HashMap<Integer, Rol> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private RolDAO () {
		this.cache = new HashMap<Integer, Rol>();
	}

	public static RolDAO getInstancie () {
		if (rolDAO == null)
			rolDAO = new RolDAO();

		return rolDAO;
	}

	@Override
	public int insert(Rol rol) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_rol(?)}");
		cs.setString(1, rol.getName()); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion;
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idRol) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_rol(?)}");
		cs.setInt(1, idRol); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion	
	}

	@Override
	public void update(Rol rol) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_rol(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, rol.getId());
		cs.setString(1, rol.getName());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public Rol select(int idRol) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_rol(?)}");
		cs.setInt(1, idRol); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero
		Rol rol = mapEntity(cs); // se mapea la entidad de objeto
		cs.close(); // se cierra la llamada a la funcion

		return rol;
	}

	@Override
	public List<Rol> selectAll() throws SQLException {
		List<Rol> listRoles = new ArrayList<Rol>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_rol()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listRoles.add(mapEntity(cs)); // se inserta en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listRoles;
	}

	@Override
	public Rol mapEntity(CallableStatement cs) throws SQLException {
		Rol rol = this.cache.get(cs.getResultSet().getInt("id"));

		if (rol == null) { // si no se encuentra una referencia con ese id
			rol = new Rol(cs.getResultSet().getInt("id"), cs.getResultSet().getString("rol_name")); // se crea una nueva referencia que representa a la entidad
			this.cache.put(rol.getId(), rol); // se alamacena la referencia creada en cache
		}

		return rol;
	}

}
