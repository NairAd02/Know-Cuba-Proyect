package logica;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.AccommodationContractDAO;
import dao.AccommodationModalityDAO;
import dao.SeasonDAO;
import utils.AusentFilter;
import utils.FiltersAccommodationModality;
import utils.FiltersSeasons;


public class AccommodationContract extends Contract {
	ArrayList<Season> seasons;


	public AccommodationContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, boolean state, String typeOfContract,
			double surcharge, ArrayList<Season> seasons) throws SQLException { // Constructor a nivel de base de datos
		super(id, startDate, terminationDate, reconciliationDate, description, provider, state,
				typeOfContract, surcharge);
		this.seasons = seasons;

	}

	public AccommodationContract(LocalDate startDate, LocalDate terminationDate,
			String description, Provider provider, ArrayList<Modality> modalitys,
			double surcharge, ArrayList<Season> seasons) { // Constructor a nivel de logica
		super(startDate, terminationDate, description, provider, modalitys,
				surcharge);
		this.seasons = seasons;
		this.typeOfContract = "Accommodation Contract";

	}

	public AccommodationContract() { // Constructor temporal
		super();
		this.seasons = new ArrayList<Season>();
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}


	// Metodos para el control de las modalidades


	public void updateAccommodationModality(AccommodationModality accommodationModality, TypeOfRoom typeOfRoom, MealPlan mealPlan, double price, int cantDaysAccommodation) throws SQLException {
		// se actualiza la informacion a nivel de logica
		this.updateAccommodationModalityLogic(accommodationModality, typeOfRoom, mealPlan, price, cantDaysAccommodation);
		accommodationModality.update(); // se actualiza la informacion de la modalidad en la base de datos
	}

	public void updateAccommodationModalityLogic(AccommodationModality accommodationModality, TypeOfRoom typeOfRoom, MealPlan mealPlan, double price, int cantDaysAccommodation) throws SQLException {
		// se actualiza la informacion a nivel de logica
		accommodationModality.setTypeOfRoomSelect(typeOfRoom);
		accommodationModality.setMealPlanSelect(mealPlan);
		accommodationModality.setPrice(price);
		accommodationModality.setCantDaysAccommodation(cantDaysAccommodation);
	}

	// Fin Metodos para el control de las modalidades

	// Metodos para el control de las temporadas

	public void addSeason(Season season) throws SQLException {
		season.insert(); // se inserta la tamporada en la base de datos
		this.addSeasonLogic(season); // se inserta la temporada en la logica del negocio
	}

	public void addSeasonLogic(Season season) throws SQLException {
		this.seasons.add(season); // se inserta la temporada en la logica del negocio
	}

	public void deleteSeason(Season season) throws SQLException {
		season.delete(); // se elimina la temporada de la base de datos
		this.deleteSeasonLogic(season); // se elimina la temporada de la logica del negocio
	}

	public void deleteSeasonLogic(Season season) throws SQLException {
		this.seasons.remove(season);
	}

	public void updateSeason(Season season, String seasonName, LocalDate seasonStrartDate, LocalDate seasonTerminationDate, String seasonDescription, String typeOfSeason) throws SQLException {
		// se actualizan los datos de la temporada en la logica del negocio
		this.updateSeasonLogic(season, seasonName, seasonStrartDate, seasonTerminationDate, seasonDescription, typeOfSeason);
		season.update(); // se actualiza la temporada en la base de datos
	}

	public void updateSeasonLogic(Season season, String seasonName, LocalDate seasonStrartDate, LocalDate seasonTerminationDate, String seasonDescription, String typeOfSeason) throws SQLException {
		// se actualizan los datos de la temporada en la logica del negocio
		season.setName(seasonName);
		season.setStartDate(seasonStrartDate);
		season.setTerminationDate(seasonTerminationDate);
		season.setDescription(seasonDescription);
		season.setTypeOfSeason(typeOfSeason);
	}

	public void insertSeasonIntoDataBase() throws SQLException {

		for (Season season : this.seasons) {
			season.setAccommodationContractId(this.id); // se actualiza el id del contrato para reflejar que esta temporada pertenece a este contrato
			season.insert(); // se inserta la temporada en la base de datos
		}

	}

	@Override
	public void insert() throws SQLException {
		this.id = AccommodationContractDAO.getInstancie().insert(this);
		this.insertModalitysIntoDataBase();
		this.insertSeasonIntoDataBase();
	}

	@Override
	public void update() throws SQLException {
		AccommodationContractDAO.getInstancie().update(this);
	}

	@Override
	public void cargarModalities() throws SQLException {
		this.modalitys = new ArrayList<Modality>(AccommodationModalityDAO.getInstancie().selectIntoAccommodationContract(this));
	}

	public void cargarSeasons() throws SQLException {
		this.seasons = (ArrayList<Season>) SeasonDAO.getInstancie().selectIntoAccommodationContract(this.id);
	}

	@Override
	public void actualizarDatos() throws SQLException {
		this.cargarModalities();
		this.cargarSeasons();
	}

	// Fin Metodos para el control de las temporadas

	// Operaciones

	public ArrayList<TypeOfRoom> getTypesOfRoomsHotel() {
		return ((Hotel) this.provider).getTypesOfRooms();
	}

	public ArrayList<MealPlan> getMealPlansHotel() {
		return ((Hotel) this.provider).getMealsPlans();
	}

	public ArrayList<HotelModality> getHotelsModalitiesHotel() { // Metodo para obtener las modalidades hoteleras del proveedor de alojamiento
		return ((Hotel) this.provider).getHotelsModalitys();
	}
	// Fin de Operaciones


	// Metodos para la obtencion de los datos

	// Metodos para la Obtenecion de las Modalidades
	// Metodo de obtencion con Filtros aplicados
	public ArrayList<Modality> getModalitys(TypeOfRoom typeOfRoom, MealPlan mealPlan, HotelModality hotelModality, double priceMin, double priceMax, int cantDaysAccommodationMin, int cantDaysAccommodationMax) {
		ArrayList<Modality> modalitys = this.modalitys;
		// Se aplican los filtros
		// Filtro Tipo de Habitacion
		if (typeOfRoom != null)
			modalitys = FiltersAccommodationModality.filterTypeOfRoom(modalitys, typeOfRoom); // se filtra por tipo de habitacion
		// Filtro plan Alimenticio
		if (mealPlan != null)
			modalitys = FiltersAccommodationModality.filterMealPlan(modalitys, mealPlan); // se filtra por plan alimenticio
		// Filtro Modalidad de Hotel
		if (hotelModality != null)
			modalitys = FiltersAccommodationModality.filterHotelModality(modalitys, hotelModality); // se filtra por modalidad de hotel

		// Se filtra por precio
		if (priceMin != AusentFilter.spinnerField && priceMax != AusentFilter.spinnerField)
			modalitys = FiltersAccommodationModality.filterPrice(modalitys, priceMin, priceMax); // se filtra por el rango de precios
		else if (priceMin != AusentFilter.spinnerField)
			modalitys = FiltersAccommodationModality.filterPrice(modalitys, priceMin, Double.MAX_VALUE); // solo se filtra por el precio minimo
		else if (priceMax != AusentFilter.spinnerField)
			modalitys = FiltersAccommodationModality.filterPrice(modalitys, Double.MIN_VALUE, priceMax); // solo se filtra por el precio maximo

		// Se filtra por cantidad de dias de alojamiento

		if (cantDaysAccommodationMin != AusentFilter.spinnerField && cantDaysAccommodationMax != AusentFilter.spinnerField)
			modalitys = FiltersAccommodationModality.filterCantDaysAccommodations(modalitys, cantDaysAccommodationMin, cantDaysAccommodationMax); // se filtra por el rango de dias
		else if (cantDaysAccommodationMin != AusentFilter.spinnerField)
			modalitys = FiltersAccommodationModality.filterCantDaysAccommodations(modalitys, cantDaysAccommodationMin, Integer.MAX_VALUE); // se filtra por la cantidad de dias de alojamiento minima
		else if (cantDaysAccommodationMax != AusentFilter.spinnerField)
			modalitys = FiltersAccommodationModality.filterCantDaysAccommodations(modalitys, Integer.MIN_VALUE, cantDaysAccommodationMax); // se filtra por la cantidad de dias de alojamiento maxima

		return modalitys;
	}


	// Fin de Metodos para la Obtenecion de las Modalidades

	// Metodos para la obtencion de las temporadas

	public ArrayList<Season> getSeasons(String name, String typeSeason, LocalDate startDateMin, LocalDate startDateMax, LocalDate terminationDateMin, LocalDate terminationDateMax) {
		ArrayList<Season> seasons = this.seasons;
		// Se aplican los filtros

		// Filtro Nombre
		if (name != null)
			seasons = FiltersSeasons.filterName(seasons, name); // se filtra por nombre

		// Filtro Tipo de Temporada
		if (typeSeason != null)
			seasons = FiltersSeasons.filterTypeSeason(seasons, typeSeason); // se filtra por tipo de temporada

		// Filtro Fecha de Inicio
		if (startDateMin != null && startDateMax != null)
			seasons = FiltersSeasons.filterStartDate(seasons, startDateMin, startDateMax); // se filtra por el rango de fechas
		else if (startDateMin != null)
			seasons = FiltersSeasons.filterStartDate(seasons, startDateMin, LocalDate.MAX); // se filtra por la fecha minima
		else if (startDateMax != null)
			seasons = FiltersSeasons.filterStartDate(seasons, LocalDate.MIN, startDateMax); // se filtra por la fecha maxima

		// Filtro Fecha de Terminacion
		if (terminationDateMin != null && terminationDateMax != null)
			seasons = FiltersSeasons.filterTerminationDate(seasons, terminationDateMin, terminationDateMax); // se filtra por el rango de fechas
		else if (terminationDateMin != null)
			seasons = FiltersSeasons.filterTerminationDate(seasons, terminationDateMin, LocalDate.MAX); // se filtra por la fecha minima
		else if (terminationDateMax != null)
			seasons = FiltersSeasons.filterTerminationDate(seasons, LocalDate.MIN, terminationDateMax); // se filtra por la fecha maxima

		return seasons;

	}

	// Implementar

	// Fin de Metodos para la obtencion de las temporadas

	// Metodos para la obtencion de los datos
}
