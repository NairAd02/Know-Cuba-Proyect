package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.HotelDAO;
import dao.HotelModalityDAO;
import dao.MealPlanDAO;
import dao.TypeOfRoomDAO;


public class Hotel extends Provider {
    private String hotelChain;
    private int hotelCategory;
    private String address;
    private int phone;
    private String fax;
    private String email;
    private int cantRooms;
    private int cantFloors;
    private String locationHotel;
    private double distanceNearestCity;
    private double distanceAirport;
    private ArrayList<TypeOfRoom> typesOfRooms;
    private ArrayList<MealPlan> mealsPlans;
    private LocalDate dateBuild;
    private ArrayList<HotelModality> hotelsModalitys;


    public Hotel(int id, String name, String province, String hotelChain, int hotelCategory, String address, int phone,
                 String fax, String email, int cantRooms, int cantFloors, String locationHotel, double distanceNearestCity,
                 double distanceAirport, LocalDate dateBuild, ArrayList<TypeOfRoom> typesOfRooms, ArrayList<MealPlan> mealsPlans, ArrayList<HotelModality> hotelsModalitys) { // Constructor a nivel de base de datos
        super(id, name, province);
        this.hotelChain = hotelChain;
        this.hotelCategory = hotelCategory;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.cantRooms = cantRooms;
        this.cantFloors = cantFloors;
        this.locationHotel = locationHotel;
        this.distanceNearestCity = distanceNearestCity;
        this.distanceAirport = distanceAirport;
        this.dateBuild = dateBuild;
        this.typesOfRooms = typesOfRooms;
        this.mealsPlans = mealsPlans;
        this.hotelsModalitys = hotelsModalitys;
    }

    public Hotel(String name, String province, String hotelChain, int hotelCategory, String address, int phone,
                 String fax, String email, int cantRooms, int cantFloors, String locationHotel, double distanceNearestCity,
                 double distanceAirport, LocalDate dateBuild, ArrayList<TypeOfRoom> typesOfRooms, ArrayList<MealPlan> mealsPlans, ArrayList<HotelModality> hotelsModalitys) { // Constructor a nivel de logica
        super(name, province);
        this.hotelChain = hotelChain;
        this.hotelCategory = hotelCategory;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.cantRooms = cantRooms;
        this.cantFloors = cantFloors;
        this.locationHotel = locationHotel;
        this.distanceNearestCity = distanceNearestCity;
        this.distanceAirport = distanceAirport;
        this.dateBuild = dateBuild;
        this.typesOfRooms = typesOfRooms;
        this.mealsPlans = mealsPlans;
        this.hotelsModalitys = hotelsModalitys;
    }

    public Hotel() { // Construccion para operaciones temporales
        super();
        this.typesOfRooms = new ArrayList<TypeOfRoom>();
        this.mealsPlans = new ArrayList<MealPlan>();
        this.hotelsModalitys = new ArrayList<HotelModality>();
    }

    public LocalDate getDateBuild() {
        return dateBuild;
    }

    public void setDateBuild(LocalDate dateBuild) {
        this.dateBuild = dateBuild;
    }

    public ArrayList<HotelModality> getHotelsModalitys() {
        return hotelsModalitys;
    }

    public void setHotelsModalitys(ArrayList<HotelModality> hotelsModalitys) {
        this.hotelsModalitys = hotelsModalitys;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCantRooms() {
        return cantRooms;
    }

    public void setCantRooms(int cantRooms) {
        this.cantRooms = cantRooms;
    }

    public int getCantFloors() {
        return cantFloors;
    }

    public void setCantFloors(int cantFloors) {
        this.cantFloors = cantFloors;
    }

    public String getLocationHotel() {
        return locationHotel;
    }

    public void setLocationHotel(String locationHotel) {
        this.locationHotel = locationHotel;
    }

    public double getDistanceNearestCity() {
        return distanceNearestCity;
    }

    public void setDistanceNearestCity(double distanceNearestCity) {
        this.distanceNearestCity = distanceNearestCity;
    }

    public double getDistanceAirport() {
        return distanceAirport;
    }

    public void setDistanceAirport(double distanceAirport) {
        this.distanceAirport = distanceAirport;
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

    public void actualizarDatos() throws SQLException {
        this.actualizarTypesOfRoom();
        this.actualizarMealPlans();
        this.actualizarHotelsModalitys();
    }

    private void actualizarTypesOfRoom() throws SQLException {
        this.typesOfRooms = (ArrayList<TypeOfRoom>) TypeOfRoomDAO.getInstancie().selectTypeOfRoomIntoHotel(this.id); // Se obtienen desde la base de datos todos los tipos de habitacion del hotel
    }

    private void actualizarMealPlans() throws SQLException {
        this.mealsPlans = (ArrayList<MealPlan>) MealPlanDAO.getInstancie().selectMealPlanIntoHotel(this.id); // Se obtienen obtienen desde la base de datos todos los planes de alimenticios del hotel
    }

    private void actualizarHotelsModalitys() throws SQLException {
        this.hotelsModalitys = (ArrayList<HotelModality>) HotelModalityDAO.getInstancie().selectIntoHotel(this.id); // Se obtienen obtienen desde la base de datos todas las modadalidades de hotel del hotel
    }

    // Fin de Metodos de carga

    // Metodos para el control de los tipos de habitaciones

    public int cantTypeOfRoom() {
        return this.typesOfRooms.size();
    }

    public int cantMealPlan() {
        return this.mealsPlans.size();
    }

    public int cantHotelModality() {
        return this.hotelsModalitys.size();
    }

    public void addTypeOfRoom(TypeOfRoom typeOfRoom) throws SQLException {
        typeOfRoom.insertIntoHotel(this.id); // se inserta el tipo de habitacion como parte de este hotel en la base de datos
        this.addTypeOfRoomLogic(typeOfRoom); // se inserta el tipo de habitacion en la logica del negocio
    }

    public void deleteTypeOfRoom(TypeOfRoom typeOfRoom) throws SQLException {
        typeOfRoom.deleteFromHotel(this.id); // se elimina el tipo de habitacion de este hotel en la base de datos
        this.deleteTypeOfRoomLogic(typeOfRoom); // se elimina el tipo de habitacion de la logica del negocio
    }

    public void addTypeOfRoomLogic(TypeOfRoom typeOfRoom) throws SQLException {
        this.typesOfRooms.add(typeOfRoom); // se inserta el tipo de habitacion en la logica del negocio
    }

    public void deleteTypeOfRoomLogic(TypeOfRoom typeOfRoom) throws SQLException {
        this.typesOfRooms.remove(typeOfRoom); // se elimina el tipo de habitacion de la logica del negocio
    }


    // Fin Metodos para el control de los tipos de habitaciones

    // Metodos para el control de los planes alimenticios

    public void addMealPlan(MealPlan mealPlan) throws SQLException {
        mealPlan.insertIntoHotel(this.id); // se inserta el plan alimenticio como parte de este hotel en la base de datos
        this.addMealPlanLogic(mealPlan); // se inserta el plan alimenticio en la logica del negocio
    }

    public void deleteMealPlan(MealPlan mealPlan) throws SQLException {
        mealPlan.deleteFromHotel(this.id); // se elimina el plan alimenticio de este hotel en la base de datos
        this.deleteMealPlanLogic(mealPlan); // se elimina el plan alimenticio de la logica del negocio
    }


    // Metodos para inserccion y eliminaciones  en la logica del programa

    public void addMealPlanLogic(MealPlan mealPlan) throws SQLException {
        this.mealsPlans.add(mealPlan); // se inserta el plan alimenticio en la logica del negocio
    }

    public void deleteMealPlanLogic(MealPlan mealPlan) throws SQLException {
        this.mealsPlans.remove(mealPlan); // se elimina el plan alimenticio de la logica del negocio
    }

    // Fin de Metodos para inserccion y eliminaciones  en la logica del programa

    // Fin de Metodos para el control de los planes alimenticios

    // Metodos para el control de las modalidades de hotel

    public void addHotetModality(HotelModality hotelModality) throws SQLException {
        hotelModality.insertIntoHotel(this.id);
        this.addHotelModalityLogic(hotelModality); // se añade la modalidad de hotel a la logica del programa
    }

    public void addHotelModalityLogic(HotelModality hotelModality) { // metodo para insertar solo de forma logica
        this.hotelsModalitys.add(hotelModality);
    }

    public void deleteHotelModality(HotelModality hotelModality) throws SQLException {
        hotelModality.deleteFromHotel(this.id);
        this.deleteHotelModalityLogic(hotelModality); // se elimina de la logica del negocio la modalidad de hotel
    }

    public void deleteHotelModalityLogic(HotelModality hotelModality) {
        this.hotelsModalitys.remove(hotelModality);
    }


// Fin Metodos para el control de las modalidades de hotel


    private void insertMealPlanAndTypeOfRoomIntoDataBase() throws SQLException {

        // Se insertan los planes alimenticios en la base de datos
        for (MealPlan mealPlan : this.mealsPlans) {
            mealPlan.insertIntoHotel(this.id);
        }

        // Se insertan los tipos de habitacion en la base de datos
        for (TypeOfRoom typeOfRoom : this.typesOfRooms) {
            typeOfRoom.insertIntoHotel(this.id);
        }
    }

    private void insertHotelModalitysIntoDataBase() throws SQLException {
        // Se insertan las modalidades de hotel en la base de datos
        for (HotelModality hotelModality : this.hotelsModalitys) {
            hotelModality.insertIntoHotel(this.id);
        }
    }

    @Override
    public void insert() throws SQLException {
        this.id = HotelDAO.getInstancie().insert(this);
        this.insertMealPlanAndTypeOfRoomIntoDataBase(); // (Importante)
        this.insertHotelModalitysIntoDataBase(); // (Importante)
    }

    @Override
    public void update() throws SQLException {
        HotelDAO.getInstancie().update(this);
    }

}
