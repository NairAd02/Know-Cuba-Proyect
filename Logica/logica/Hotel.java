package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.HotelDAO;


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
		typeOfRoom.insertIntoHotel(this.id); // se inserta el tipo de habitacion como parte de este hotel en la base de datos
		this.typesOfRooms.add(typeOfRoom); // se inserta el tipo de habitacion en la logica del negocio
	}

	public void deleteTypeOfRoom (TypeOfRoom typeOfRoom) throws SQLException {
		typeOfRoom.deleteFromHotel(this.id); // se elimina el tipo de habitacion de este hotel en la base de datos
		this.typesOfRooms.remove(typeOfRoom); // se elimina el tipo de habitacion de la logica del negocio
	}


	// Fin Metodos para el control de los tipos de habitaciones

	public void addMealPlan (MealPlan mealPlan) throws SQLException {
		mealPlan.insertIntoHotel(this.id); // se inserta el plan alimenticio como parte de este hotel en la base de datos
		this.mealsPlans.add(mealPlan); // se inserta el plan alimenticio en la logica del negocio
	}

	public void deleteMealPlan (MealPlan mealPlan) throws SQLException {
		mealPlan.deleteFromHotel(this.id); // se elimina el plan alimenticio de este hotel en la base de datos
		this.mealsPlans.remove(mealPlan); // se elimina el plan alimenticio de la logica del negocio
	}

	@Override
	public void insert() throws SQLException {
		this.id = HotelDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		HotelDAO.getInstancie().update(this);
	}

	// Metodos para el control de los planes alimenticios


	// Fin Metodos para el control de los planes alimenticios

}
