package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.MealPlanDAO;
import dao.TypeOfRoomDAO;

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

	// Metodos para el control de los tipos de habitaciones

	public void addTypeOfRoom (TypeOfRoom typeOfRoom) throws SQLException {
		TypeOfRoomDAO.getInstancie().insertIntoHotel(this.id, typeOfRoom.getId());
		this.typesOfRooms.add(typeOfRoom); // se inserta el tipo de habitacion en la logica del negocio
	}

	public void deleteTypeOfRoom (TypeOfRoom typeOfRoom) throws SQLException {
		TypeOfRoomDAO.getInstancie().deleteFromHotel(this.id, typeOfRoom.getId());
		this.typesOfRooms.remove(typeOfRoom); // se elimina el tipo de habitacion de la logica del negocio
	}


	// Fin Metodos para el control de los tipos de habitaciones

	public void addMealPlan (MealPlan mealPlan) throws SQLException {
		MealPlanDAO.getInstancie().insertIntoHotel(this.id, mealPlan.getId());
		this.mealsPlans.add(mealPlan); // se inserta el plan alimenticio en la logica del negocio
	}

	public void deleteMealPlan (MealPlan mealPlan) throws SQLException {
		MealPlanDAO.getInstancie().deleteFromHotel(this.id, mealPlan.getId());
		this.mealsPlans.remove(mealPlan); // se elimina el plan alimenticio de la logica del negocio
	}

	// Metodos para el control de los planes alimenticios


	// Fin Metodos para el control de los planes alimenticios

}
