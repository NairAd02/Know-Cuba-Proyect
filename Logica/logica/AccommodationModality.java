package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AccommodationModalityDAO;

public class AccommodationModality extends Modality {
    private TypeOfRoom typeOfRoomSelect;
    private MealPlan mealPlanSelect;

    private HotelModality hotelModality;
    private int cantDaysAccommodation;
    private double price;

    public AccommodationModality(int id, Contract contract, String typeOfModality, TypeOfRoom typeOfRoomSelect, HotelModality hotelModality,
                                 MealPlan mealPlanSelect, int cantDaysAccommodation, double price) { // constructor a nivel de base de datos
        super(id, contract, typeOfModality);
        this.typeOfRoomSelect = typeOfRoomSelect;
        this.mealPlanSelect = mealPlanSelect;
        this.hotelModality = hotelModality;
        this.cantDaysAccommodation = cantDaysAccommodation;
        this.price = price;
    }

    public AccommodationModality(Contract contract, TypeOfRoom typeOfRoomSelect, HotelModality hotelModality,
                                 MealPlan mealPlanSelect, int cantDaysAccommodation, double price) { // constructor a nivel de logica
        super(contract);
        this.typeOfRoomSelect = typeOfRoomSelect;
        this.mealPlanSelect = mealPlanSelect;
        this.hotelModality = hotelModality;
        this.cantDaysAccommodation = cantDaysAccommodation;
        this.price = price;
        this.typeOfModality = "Accommodation Modality";
    }

    public AccommodationModality(TypeOfRoom typeOfRoomSelect,
                                 MealPlan mealPlanSelect, HotelModality hotelModality, int cantDaysAccommodation, double price) { // Constructor a nivel de logica (proceso de creacion del objeto)
        super();
        this.typeOfRoomSelect = typeOfRoomSelect;
        this.mealPlanSelect = mealPlanSelect;
        this.hotelModality = hotelModality;
        this.cantDaysAccommodation = cantDaysAccommodation;
        this.price = price;
        this.typeOfModality = "Accommodation Modality";
    }

    public void actualizarCampos (Contract contract, String typeOfModality, TypeOfRoom typeOfRoomSelect,
                                  MealPlan mealPlanSelect, HotelModality hotelModality, int cantDaysAccommodation, double price ) { // Metodo para actualizar los atributos de la clase
        super.actualizarCampos(contract, typeOfModality);
        this.typeOfRoomSelect = typeOfRoomSelect;
        this.mealPlanSelect = mealPlanSelect;
        this.hotelModality = hotelModality;
        this.cantDaysAccommodation = cantDaysAccommodation;
        this.price = price;
    }


    public TypeOfRoom getTypeOfRoomSelect() {
        return typeOfRoomSelect;
    }


    public void setTypeOfRoomSelect(TypeOfRoom typeOfRoomSelect) {
        this.typeOfRoomSelect = typeOfRoomSelect;
    }

    public void setMealPlanSelect(MealPlan mealPlanSelect) {
        this.mealPlanSelect = mealPlanSelect;
    }

    public MealPlan getMealPlanSelect() {
        return mealPlanSelect;
    }

    public HotelModality getHotelModality() {
        return hotelModality;
    }

    public void setHotelModality(HotelModality hotelModality) {
        this.hotelModality = hotelModality;
    }

    public double getPrice() {
        return price;
    }

    public int getCantDaysAccommodation() {
        return cantDaysAccommodation;
    }

    public void setCantDaysAccommodation(int cantDaysAccommodation) {
        this.cantDaysAccommodation = cantDaysAccommodation;
    }

    public double price() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTypeOfRoomId() {
        return this.typeOfRoomSelect.getId();
    }

    public int getMealPlanId() {
        return this.mealPlanSelect.getId();
    }

    public int getHotelModalityId() {
        return this.hotelModality.getId();
    }

    @Override
    public void insert() throws SQLException {

        this.id = AccommodationModalityDAO.getInstancie().insert(this);
    }

    @Override
    public void update() throws SQLException {
        AccommodationModalityDAO.getInstancie().update(this);

    }

}
