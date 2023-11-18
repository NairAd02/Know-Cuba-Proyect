package dao;


import logica.MealPlan;

public interface MealPlanDAOInterface {
	public boolean insert(MealPlan mealPlan);
	public boolean delete(MealPlan mealPlan);
	public boolean update(MealPlan mealPlan);
	public boolean select(int idMealPlan);
	public boolean selectAll();
}
