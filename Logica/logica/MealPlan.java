package logica;

public class MealPlan {
	private int id;
	private String name;
	public MealPlan(int id, String name) {
		super();
		this.id = id;
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
	
}
