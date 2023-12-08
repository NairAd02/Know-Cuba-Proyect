package logica;

 
import java.util.ArrayList;
import java.util.HashMap;
import trees.BinarySearchTree;

public class TouristAgency {
	private static TouristAgency touristAgency; // Singlenton
	private BinarySearchTree<TypeOfRoom> typesOfRoom;
	private BinarySearchTree<MealPlan> mealPlans;
	private HashMap<String, BinarySearchTree<Provider>> providers; // los proveedores de la agencia hasheados por su tipo
	private HashMap<String, ArrayList<Contract>> contracts; // los contratos de la agencia hasheados por su tipo


	private TouristAgency () { // Singlenton
		this.typesOfRoom = new BinarySearchTree<TypeOfRoom>();
		this.mealPlans = new BinarySearchTree<MealPlan>();
		this.providers = new HashMap<String, BinarySearchTree<Provider>>();
		this.contracts = new HashMap<String, ArrayList<Contract>>();
	}

	public static TouristAgency getInstancie() { // Singlenton
		if (touristAgency == null)
			touristAgency = new TouristAgency();

		return touristAgency;
	}


	// Metodos de busqueda y obtencion
	

	public ServiceProvider getServiceProvider (int idServiceProvider) throws Exception { // metodo para obtener un provedor de servicio en especifico
		return (ServiceProvider) this.providers.get("Service Provider").search(new ServiceProvider(idServiceProvider));
	}

	public Hotel getHotel (int idHotel) throws Exception { // metodo para obtener un hotel en especifico
		return (Hotel) this.providers.get("Hotel").search(new Hotel(idHotel));
	}

	public TypeOfRoom getTypeOfRoom (int idTypeOfRoom) throws Exception { // metodo para obtener un tipo de habitacion en especifico 
		return this.typesOfRoom.search(new TypeOfRoom(idTypeOfRoom));
	}

	public MealPlan getMealPlan (int idMealPlan) throws Exception { // metodo para obtener un plan alimenticio en especifico 
		return this.mealPlans.search(new MealPlan(idMealPlan));
	}


}
