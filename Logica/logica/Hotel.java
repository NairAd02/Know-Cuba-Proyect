package logica;

import java.util.ArrayList;

public class Hotel extends Provider {
	private String hotelChain;
	private String hotelCategory;
	private ArrayList<TypeOfRoom> typesOfRooms;
	private ArrayList<MealPlan> mealsPlans;
	
	public Hotel(int id, String name, String province, String hotelChain,
			String hotelCategory, ArrayList<TypeOfRoom> typesOfRooms,
			ArrayList<MealPlan> mealsPlans) {
		super(id, name, province);
		this.hotelChain = hotelChain;
		this.hotelCategory = hotelCategory;
		this.typesOfRooms = typesOfRooms;
		this.mealsPlans = mealsPlans;
	}

	public String getHotelChain() {
		return this.hotelChain;
	}

	public void setHotelChain(String hotelChain) {
		this.hotelChain = hotelChain;
	}

	public String getHotelCategory() {
		return this.hotelCategory;
	}

	public void setHotelCategory(String hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public ArrayList<TypeOfRoom> getTypesOfRooms() {
		return typesOfRooms;
	}

	public void setTypesOfRooms(ArrayList<TypeOfRoom> typesOfRooms) {
		this.typesOfRooms = typesOfRooms;
	}

	public ArrayList<MealPlan> getMealsPlans() {
		return mealsPlans;
	}

	public void setMealsPlans(ArrayList<MealPlan> mealsPlans) {
		this.mealsPlans = mealsPlans;
	}
		
}
