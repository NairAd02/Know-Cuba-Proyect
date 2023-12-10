package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.AccommodationModalityDAO;
import dao.SeasonDAO;


public class AccommodationContract extends Contract{
	ArrayList<Season> seasons;
	private double surcharge;

	public AccommodationContract(int id, LocalDate startDate,
			LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider,
			ArrayList<AccommodationModality> modalitys, boolean state, String typeOfContract,
			ArrayList<Season> seasons, double surcharge) {
		super(id, startDate, terminationDate, reconciliationDate, description,
				provider, new ArrayList<Modality>(modalitys), state, typeOfContract);
		this.seasons = seasons;
		this.surcharge = surcharge;
	} // Constructor Nivel de logica


	public AccommodationContract (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		super(id);
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

	public double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}

	// Metodos para el control de las modalidades

	public void addModality (Modality modality) throws SQLException {
		// Verificar instaceof
		int idInsertado = AccommodationModalityDAO.getInstancie().insert((AccommodationModality) modality); // se inserta la modalidad en la base de datos
		super.addModality(modality);
		modality.setId(idInsertado); // se asigna el id autoincrementable
	}

	@Override
	public void updateModality(Modality modality) throws SQLException {
		AccommodationModalityDAO.getInstancie().update((AccommodationModality) modality); // se actualiza la informacion de la modalidad en la base de datos

	}

	// Fin Metodos para el control de las modalidades

	// Metodos para el control de las temporadas

	public void addSeason (Season season) throws SQLException {
		int idInsertado =  SeasonDAO.getInstancie().insert(season); // se inserta la tamporada en la base de datos
		this.seasons.add(season); // se inserta la temporada en la logica del negocio
		season.setId(idInsertado); // se asigna el id autoincrementable
	}

	public void deleteSeason (Season season) throws SQLException {
		SeasonDAO.getInstancie().delete(season.getId()); // se elimina la temporada de la base de datos
		this.seasons.remove(season);
	}
	
	public void updateSeason (Season season) throws SQLException {
		SeasonDAO.getInstancie().update(season); // se actualiza la temporada en la base de datos
	}

	// Fin Metodos para el control de las temporadas

}
