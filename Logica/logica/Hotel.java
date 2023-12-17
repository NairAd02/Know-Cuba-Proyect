package logica;

import java.sql.SQLException;

import java.util.ArrayList;
import dao.HotelDAO;
import dao.MealPlanDAO;
import dao.TypeOfRoomDAO;


public class Hotel extends Provider {
	private String hotelChain;
	private int hotelCategory;
	private String address;
	private ArrayList<TypeOfRoom> typesOfRooms; 
	private ArrayList<MealPlan> mealsPlans;


	public Hotel(int id, String name, String province, String hotelChain, int hotelCategory, String address,
			ArrayList<TypeOfRoom> typesOfRooms, ArrayList<MealPlan> mealsPlans) { // Constructor a nivel de base de datos
		super(id, name, province);
		this.hotelChain = hotelChain;
		this.hotelCategory = hotelCategory;
		this.address = address;
		this.typesOfRooms = typesOfRooms;
		this.mealsPlans = mealsPlans;
	}

	public Hotel(String name, String province, String hotelChain, int hotelCategory, String address,
			ArrayList<TypeOfRoom> typesOfRooms, ArrayList<MealPlan> mealsPlans) { // Constructor a nivel de logica
		super(name, province);
		this.hotelChain = hotelChain;
		this.hotelCategory = hotelCategory;
		this.address = address;
		this.typesOfRooms = typesOfRooms;
		this.mealsPlans = mealsPlans;
	}

	public Hotel () { // Construccion para operaciones temporales
		super();
		this.typesOfRooms = new ArrayList<TypeOfRoom>();
		this.mealsPlans = new ArrayList<MealPlan>();
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

	// Metodos de carga


	public void actualizarDatos () throws SQLException {
		this.actualizarTypesOfRoom();
		this.actualizarMealPlans();
	}

	private void actualizarTypesOfRoom () throws SQLException {
		this.typesOfRooms = (ArrayList<TypeOfRoom>) TypeOfRoomDAO.getInstancie().selectTypeOfRoomIntoHotel(this.id);
	}

	private void actualizarMealPlans () throws SQLException {
		this.mealsPlans = (ArrayList<MealPlan>) MealPlanDAO.getInstancie().selectMealPlanIntoHotel(this.id);
	}

	// Fin de Metodos de carga 

	// Metodos para el control de los tipos de habitaciones

	public int cantTypeOfRoom () {
		return this.typesOfRooms.size();
	}

	public int cantMealPlan () {
		return this.mealsPlans.size();
	}

	public void addTypeOfRoom (TypeOfRoom typeOfRoom) throws SQLException {
		typeOfRoom.insertIntoHotel(this.id); // se inserta el tipo de habitacion como parte de este hotel en la base de datos
		this.addTypeOfRoomLogic(typeOfRoom); // se inserta el tipo de habitacion en la logica del negocio
	}

	public void deleteTypeOfRoom (TypeOfRoom typeOfRoom) throws SQLException {
		typeOfRoom.deleteFromHotel(this.id); // se elimina el tipo de habitacion de este hotel en la base de datos
		this.deleteTypeOfRoomLogic(typeOfRoom); // se elimina el tipo de habitacion de la logica del negocio
	}

	public void addTypeOfRoomLogic (TypeOfRoom typeOfRoom) throws SQLException {
		this.typesOfRooms.add(typeOfRoom); // se inserta el tipo de habitacion en la logica del negocio
	}

	public void deleteTypeOfRoomLogic (TypeOfRoom typeOfRoom) throws SQLException {
		this.typesOfRooms.remove(typeOfRoom); // se elimina el tipo de habitacion de la logica del negocio
	}


	// Fin Metodos para el control de los tipos de habitaciones

	// Metodos para el control de los planes alimenticios

	public void addMealPlan (MealPlan mealPlan) throws SQLException {
		mealPlan.insertIntoHotel(this.id); // se inserta el plan alimenticio como parte de este hotel en la base de datos
		this.addMealPlanLogic(mealPlan); // se inserta el plan alimenticio en la logica del negocio
	}

	public void deleteMealPlan (MealPlan mealPlan) throws SQLException {
		mealPlan.deleteFromHotel(this.id); // se elimina el plan alimenticio de este hotel en la base de datos
		this.deleteMealPlanLogic(mealPlan); // se elimina el plan alimenticio de la logica del negocio
	}



	// Metodos para inserccion y eliminaciones  en la logica del programa

	public void addMealPlanLogic (MealPlan mealPlan) throws SQLException {
		this.mealsPlans.add(mealPlan); // se inserta el plan alimenticio en la logica del negocio
	}

	public void deleteMealPlanLogic (MealPlan mealPlan) throws SQLException {
		this.mealsPlans.remove(mealPlan); // se elimina el plan alimenticio de la logica del negocio
	}

	// Fin de Metodos para inserccion y eliminaciones  en la logica del programa

	// Fin de Metodos para el control de los planes alimenticios



	private void insertMealPlanAndTypeOfRoomIntoDataBase () throws SQLException {

		// Se insertan los planes alimenticios en la base de datos
		for (MealPlan mealPlan : this.mealsPlans) {
			mealPlan.insertIntoHotel(this.id);
		}

		// Se insertan los tipos de habitacion en la base de datos
		for (TypeOfRoom typeOfRoom : this.typesOfRooms) {
			typeOfRoom.insertIntoHotel(this.id);
		}
	}

	@Override
	public void insert() throws SQLException {
		this.id = HotelDAO.getInstancie().insert(this);
		this.insertMealPlanAndTypeOfRoomIntoDataBase(); // (Importante)
	}

	@Override
	public void update() throws SQLException {
		HotelDAO.getInstancie().update(this);	
	}


}
