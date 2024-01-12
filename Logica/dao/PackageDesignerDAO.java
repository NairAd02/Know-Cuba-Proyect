package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.PackageDesigner;
import logica.Request;
import utils.ConnectionDataBase;

public class PackageDesignerDAO implements PackageDesignerDAOInterface {
	private static PackageDesignerDAO packageDesigner;
	private HashMap<Integer, PackageDesigner> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private PackageDesignerDAO () {
		this.cache = new HashMap<Integer, PackageDesigner>();
	}

	public static PackageDesignerDAO getInstancie () {
		if (packageDesigner == null)
			packageDesigner = new PackageDesignerDAO();

		return packageDesigner;
	}

	@Override
	public int insert(PackageDesigner packageDesigner) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_package_designer(?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
		cs.setString(2, packageDesigner.getUserName());
		cs.setString(3, packageDesigner.getPassword());
		cs.setInt(4, packageDesigner.getRolId());
		cs.execute(); // se ejecuta la llamada a la funcion
		int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idInsertado;
	}

	@Override
	public void delete(int idPackageDesigner) throws SQLException {
		UserDAO.getInstancie().delete(idPackageDesigner);
	}

	@Override
	public void update(PackageDesigner packageDesigner) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_package_designer(?, ?, ?, ?, ?, ?)}");
		// Se definen los parametros de la funcion
		cs.setInt(1, packageDesigner.getId());
		cs.setString(2, packageDesigner.getUserName());
		cs.setString(3, packageDesigner.getPassword());
		cs.setDate(4, Date.valueOf(packageDesigner.getStartDateConnection()));
		cs.setDate(5, Date.valueOf(packageDesigner.getLastDateConnection()));
		cs.setBoolean(6, packageDesigner.isConnected());
		cs.execute(); // se ejecuta la llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public PackageDesigner select(int idPackageDesigner) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_package_designer(?)}");
		PackageDesigner packageDesigner = null;
		cs.setInt(1, idPackageDesigner); // se define el paraemtro de la funcion
		cs.execute(); // se ejecuta la llamada a la funcion
		if(cs.getResultSet().next()) // se situa el puntero
			packageDesigner = mapEntity(cs); // se mapea la entidad a objeto
		cs.close(); // se cierra la llamada a la funcion

		return packageDesigner;
	}

	@Override
	public List<PackageDesigner> selectAll() throws SQLException {
		List<PackageDesigner> listPackageDesigner = new ArrayList<PackageDesigner>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_package_designer()}");
		cs.execute(); // se ejecuta la llamada a la funcion

		while (cs.getResultSet().next()) {
			listPackageDesigner.add(mapEntity(cs)); // se alamacena en la lista la entidad mapeada a objeto
		}

		cs.close(); // se cierra la llamada a la funcion

		return listPackageDesigner;
	}

	@Override
	public PackageDesigner mapEntity(CallableStatement cs) throws SQLException {
		PackageDesigner packageDesigner = this.cache.get(cs.getResultSet().getInt("id"));

		if (packageDesigner == null) { // si no se encuentra una referencia con ese id
			if (cs.getResultSet().getDate("start_date_connection") == null || cs.getResultSet().getDate("last_date_connection") == null )
				packageDesigner = new PackageDesigner(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
						null, null, cs.getResultSet().getBoolean("connected"), (ArrayList<Request>) RequestDAO.getInstancie().selectIntoUser(cs.getResultSet().getInt("id")), cs.getResultSet().getBoolean("state_password")); // se crea una nueva referencia para el objeto
			else
				packageDesigner = new PackageDesigner(cs.getResultSet().getInt("id"), cs.getResultSet().getString("user_name"), cs.getResultSet().getString("user_password"), RolDAO.getInstancie().select(cs.getResultSet().getInt("id_rol")), 
						cs.getResultSet().getDate("start_date_connection").toLocalDate(), cs.getResultSet().getDate("last_date_connection").toLocalDate(), cs.getResultSet().getBoolean("connected"),
						(ArrayList<Request>) RequestDAO.getInstancie().selectIntoUser(cs.getResultSet().getInt("id")), cs.getResultSet().getBoolean("state_password")); // se crea una nueva referencia para el objeto

			this.cache.put(packageDesigner.getId(), packageDesigner); // se almacena en cache la referencia que representa a la entidad usuario
		}

		return packageDesigner;
	}
}
