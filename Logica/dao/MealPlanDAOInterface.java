package dao;

import java.sql.SQLException;
import java.util.List;

import logica.MealPlan;



public interface MealPlanDAOInterface extends DAO <MealPlan>{
	
	public List<MealPlan> selectMealPlanIntoHotel (int idHotel) throws SQLException; // Metodo para seleccionar todos los planes alimenticios de una hotel en especifico
	public void insertIntoHotel (int idHotel, int idMealPlan) throws SQLException; // Metodo para insertar un plan alimenticio en un hotel en especifico
	public void deleteFromHotel (int idHotel, int idMealPlan) throws SQLException; // metodo para eliminar un plan alimenticio de un hotel en especifico
}
