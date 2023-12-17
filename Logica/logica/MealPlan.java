package logica;

import java.sql.SQLException;
import dao.MealPlanDAO;

public class MealPlan implements DUILogic{
	private int id;
	private String name;
	
	public MealPlan(int id, String name) { // Constructor a nivel de base de datos
		super();
		this.id = id;
		this.name = name;
	}
	
	public MealPlan(String name) { // Constructor a nivel de logica
		this.name = name;
	}

	public MealPlan (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
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
		this.id = MealPlanDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		MealPlanDAO.getInstancie().update(this);
	}

	@Override
	public void delete() throws SQLException {
		MealPlanDAO.getInstancie().delete(this.id);
	}
	 
	public void insertIntoHotel (int idHotel) throws SQLException { // metodo para insertar el plan alimenticio como parte de un hotel en especifico
		MealPlanDAO.getInstancie().insertIntoHotel(idHotel, this.id);
	}
	
	public void deleteFromHotel (int idHotel) throws SQLException {  // metodo para eliminar el plan alimenticio de un hotel en especifico
		MealPlanDAO.getInstancie().deleteFromHotel(idHotel, this.id);
	}
	
	public String toString () { // Metodo para definir como se muestra la informacion de la clase
		return this.name;
	}

}
