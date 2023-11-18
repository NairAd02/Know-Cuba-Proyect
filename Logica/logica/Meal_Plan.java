package logica;

public class Meal_Plan {
	private int id;
	private String meal_plan_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeal_plan_name() {
		return meal_plan_name;
	}
	public void setMeal_plan_name(String meal_plan_name) {
		this.meal_plan_name = meal_plan_name;
	}
	
	public Meal_Plan(String meal_plan_name) {
		this.meal_plan_name = meal_plan_name;
	}
}
