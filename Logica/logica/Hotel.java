package logica;

import java.util.ArrayList;

public class Hotel extends Provider {
	private String hotelChain;
	private int hotelCategory;
	private String address;
	private ArrayList<TypeOfRoom> typesOfRooms;
	private ArrayList<MealPlan> mealsPlans;
	
	
	public Hotel(int id, String name, String province, String hotelChain, int hotelCategory, String address,
			ArrayList<TypeOfRoom> typesOfRooms, ArrayList<MealPlan> mealsPlans) {
		super(id, name, province);
		this.hotelChain = hotelChain;
		this.hotelCategory = hotelCategory;
		this.address = address;
		this.typesOfRooms = typesOfRooms;
		this.mealsPlans = mealsPlans;
	}
	
	public Hotel (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		super(id);
	}

	public String getHotelChain() {
		return hotelChain;
	}

	public void setHotelChain(String hotelChain) {
		this.hotelChain = hotelChain;
	}

	public int getHotelCategory() {
		return hotelCategory;
	}

	public void setHotelCategory(int hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
