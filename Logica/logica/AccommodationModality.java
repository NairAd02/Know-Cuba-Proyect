package logica;

public class AccommodationModality extends Modality{
	private TypeOfRoom typeOfRoomSelect;
	private MealPlan mealPlanSelect;
	private int cantDaysAccommodation;
	private double price;

	public AccommodationModality(int id, Contract contract,
			TypeOfRoom typeOfRoomSelect, MealPlan mealPlanSelect,
			int cantDaysAccommodation, double price) {
		super(id, contract);
		this.typeOfRoomSelect = typeOfRoomSelect;
		this.mealPlanSelect = mealPlanSelect;
		this.cantDaysAccommodation = cantDaysAccommodation;
		this.price = price;
	}

	public TypeOfRoom getTypeOfRoomSelect() {
		return this.typeOfRoomSelect;
	}

	public void setTypeOfRoomSelect(TypeOfRoom typeOfRoomSelect) {
		this.typeOfRoomSelect = typeOfRoomSelect;
	}

	public MealPlan getMealPlanSelect() {
		return this.mealPlanSelect;
	}

	public void setMealPlanSelect(MealPlan mealPlanSelect) {
		this.mealPlanSelect = mealPlanSelect;
	}

	public int getCantDaysAccommodation() {
		return this.cantDaysAccommodation;
	}

	public void setCantDaysAccommodation(int cantDaysAccommodation) {
		this.cantDaysAccommodation = cantDaysAccommodation;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
