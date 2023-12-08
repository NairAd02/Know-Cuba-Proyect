package logica;

public class AccommodationModality extends Modality{
	private TypeOfRoom typeOfRoomSelect;
	private MealPlan mealPlanSelect;
	private int cantDaysAccommodation;
	private double price;

	public AccommodationModality(int id, Contract contract, String typeOfModality, TypeOfRoom typeOfRoomSelect,
			MealPlan mealPlanSelect, int cantDaysAccommodation, double price) { // constructor a nivel de logica
		super(id, contract, typeOfModality);
		this.typeOfRoomSelect = typeOfRoomSelect;
		this.mealPlanSelect = mealPlanSelect;
		this.cantDaysAccommodation = cantDaysAccommodation;
		this.price = price;
	}

	
	public TypeOfRoom getTypeOfRoomSelect() {
		return typeOfRoomSelect;
	}

	public void setTypeOfRoomSelect(TypeOfRoom typeOfRoomSelect) {
		this.typeOfRoomSelect = typeOfRoomSelect;
	}

	public MealPlan getMealPlanSelect() {
		return mealPlanSelect;
	}

	public void setMealPlanSelect(MealPlan mealPlanSelect) {
		this.mealPlanSelect = mealPlanSelect;
	}

	public int getCantDaysAccommodation() {
		return cantDaysAccommodation;
	}

	public void setCantDaysAccommodation(int cantDaysAccommodation) {
		this.cantDaysAccommodation = cantDaysAccommodation;
	}

	public double getPrice() {
		return price;
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

}
