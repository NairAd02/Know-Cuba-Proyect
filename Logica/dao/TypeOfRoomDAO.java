package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logica.TypeOfRoom;
import utils.ConnectionDataBase;

public class TypeOfRoomDAO implements TypeOfRoomDAOInterface {
	private static TypeOfRoomDAO typeOfRoomDAO;
	private HashMap<Integer, TypeOfRoom> cache; // Atributo para guardar en cache cada referencia creada

	// PATRON SINGLENTON
	private TypeOfRoomDAO () {
		this.cache = new HashMap<Integer, TypeOfRoom>();
	}

	public static TypeOfRoomDAO getInstancie () {
		if (typeOfRoomDAO == null)
			typeOfRoomDAO = new TypeOfRoomDAO();

		return typeOfRoomDAO;
	}

	@Override
	public int insert(TypeOfRoom typeOfRoom) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_type_of_room(?)}");
		cs.setString(1, typeOfRoom.getName()); // se define el parametro de la funcion
		cs.execute();  // se ejecuta la consulta de llamada a la funcion
		int idIsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
		cs.close(); // se cierra la llamada a la funcion

		return idIsertado;
	}

	@Override
	public void delete(int idTypeOfRoom) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_type_of_room(?)}");
		cs.setInt(1, idTypeOfRoom); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}

	@Override
	public void update(TypeOfRoom typeOfRoom) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_type_of_room(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, typeOfRoom.getId());
		cs.setString(2, typeOfRoom.getName());
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion
	}


	@Override
	public List<TypeOfRoom> selectAll() throws SQLException {
		List<TypeOfRoom> listTypesOfRoom = new ArrayList<TypeOfRoom>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_type_of_room()}");
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		// se crea la lista de tipos de habitacion con la información obtenida de la base de datos
		while (cs.getResultSet().next()) {
			listTypesOfRoom.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listTypesOfRoom;
	}

	@Override
	public List<TypeOfRoom> selectTypeOfRoomIntoHotel(int idHotel) throws SQLException {

		List<TypeOfRoom> listTypesOfRoom = new ArrayList<TypeOfRoom>();
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_type_of_room_hotel(?)}");
		cs.setInt(1, idHotel); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		// se crea la lista de tipos de habitacion con la información obtenida de la base de datos
		while (cs.getResultSet().next()) {
			listTypesOfRoom.add(mapEntity(cs));
		}

		cs.close(); // se cierra la llamada a la funcion

		return listTypesOfRoom;
	}

	@Override
	public void insertIntoHotel(int idHotel, int idTypeOfRoom) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call insert_type_of_room_hotel(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, idHotel);
		cs.setInt(2, idTypeOfRoom);
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion

	}

	@Override
	public void deleteFromHotel(int idHotel, int idTypeOfRoom) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_type_of_room_hotel(?, ?)}");
		// se definen los parametros de la funcion
		cs.setInt(1, idHotel);
		cs.setInt(2, idTypeOfRoom);
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.close(); // se cierra la llamada a la funcion	
	}

	@Override
	public TypeOfRoom select(int idTypeOfRoom) throws SQLException {
		CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_type_of_room(?)}");
		cs.setInt(1, idTypeOfRoom); // se define el parametro de la funcion
		cs.execute(); // se ejecuta la consulta de llamada a la funcion
		cs.getResultSet().next(); // se situa el puntero
		TypeOfRoom typeOfRoom = mapEntity(cs);

		cs.close(); // se cierra la llamada a la funcion

		return typeOfRoom;
	}

	@Override
	public TypeOfRoom mapEntity(CallableStatement cs) throws SQLException {
		TypeOfRoom typeOfRoom = this.cache.get(cs.getResultSet().getInt("id"));
		
		if (typeOfRoom == null) {
			typeOfRoom = new TypeOfRoom(cs.getResultSet().getInt("id"), cs.getResultSet().getString("type_of_room_name"));
			this.cache.put(typeOfRoom.getId(), typeOfRoom); // se alamacena en cache la referencia del tipo de habitacion
		}
		
		return typeOfRoom;
	}

}
