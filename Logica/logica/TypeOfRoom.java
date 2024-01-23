package logica;

import java.sql.SQLException;

import dao.TypeOfRoomDAO;

public class TypeOfRoom implements DUILogic {
	private int id;
	private String name;

	public TypeOfRoom(int id, String name) { // Constructor a nivel de base de datos
		super();
		this.id = id;
		this.name = name;
	}

	public void actualizarCampos (String name) { // Metodo para actualizar los atributos de la clase
		this.name = name;
	}

	public TypeOfRoom(String name) { // Constructor a nivel de logica
		this.name = name;
	}

	public TypeOfRoom (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void insert() throws SQLException {
		this.id = TypeOfRoomDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		TypeOfRoomDAO.getInstancie().update(this);
	}

	@Override
	public void delete() throws SQLException {
		TypeOfRoomDAO.getInstancie().delete(this.id);
	}

	public void insertIntoHotel (int idHotel) throws SQLException { // metodo para insertar el tipo de habitacion como parte de un hotel en especifico
		TypeOfRoomDAO.getInstancie().insertIntoHotel(idHotel, this.id);
	}

	public void deleteFromHotel (int idHotel) throws SQLException { // metodo para eliminar el tipo de habitacion de un hotel en especifico
		TypeOfRoomDAO.getInstancie().deleteFromHotel(idHotel, this.id);
	}

	public String toString () { // Metodo para definir como se muestra la informacion de la clase
		return this.name;
	}

}
