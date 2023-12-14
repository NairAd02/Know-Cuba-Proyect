package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.AccommodationContractDAO;



public class AccommodationContract extends Contract{
	ArrayList<Season> seasons;




	public AccommodationContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, ArrayList<AccommodationModality> modalitys, boolean state, String typeOfContract,
			double surcharge, ArrayList<Season> seasons) {
		super(id, startDate, terminationDate, reconciliationDate, description, provider, new ArrayList<Modality>(modalitys), state,
				typeOfContract, surcharge);
		this.seasons = seasons;

	}

	public AccommodationContract (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		super(id);
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}


	// Metodos para el control de las modalidades



	public void updateAccommodationModality (AccommodationModality accommodationModality, TypeOfRoom typeOfRoom, MealPlan mealPlan, double price, int cantDaysAccommodation ) throws SQLException {
		// se actualiza la informacion a nivel de logica
		accommodationModality.setTypeOfRoomSelect(typeOfRoom);
		accommodationModality.setMealPlanSelect(mealPlan);
		accommodationModality.setPrice(price);
		accommodationModality.setCantDaysAccommodation(cantDaysAccommodation);
		accommodationModality.update(); // se actualiza la informacion de la modalidad en la base de datos

	}

	// Fin Metodos para el control de las modalidades

	// Metodos para el control de las temporadas

	public void addSeason (Season season) throws SQLException {
		season.insert(); // se inserta la tamporada en la base de datos
		this.seasons.add(season); // se inserta la temporada en la logica del negocio
	}

	public void deleteSeason (Season season) throws SQLException {
		season.delete(); // se elimina la temporada de la base de datos
		this.seasons.remove(season);
	}

	public void updateSeason (Season season, String seasonName, LocalDate seasonStrartDate, LocalDate seasonTerminationDate, String seasonDescription, String typeOfSeason) throws SQLException {
		// se actualizan los datos de la temporada en la logica del negocio
		season.setName(seasonName);
		season.setStartDate(seasonStrartDate);
		season.setTerminationDate(seasonTerminationDate);
		season.setDescription(seasonDescription);
		season.setTypeOfSeason(typeOfSeason);
		season.update(); // se actualiza la temporada en la base de datos
	}

	@Override
	public void insert() throws SQLException {

		this.id = AccommodationContractDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		AccommodationContractDAO.getInstancie().update(this);

	}

	// Fin Metodos para el control de las temporadas

}
