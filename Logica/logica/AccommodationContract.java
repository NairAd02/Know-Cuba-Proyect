package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.AccommodationContractDAO;
import dao.AccommodationModalityDAO;
import dao.SeasonDAO;


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
    // Fin de Operaciones


    // Metodos para la Obtenecion de las Modalidades

    public ArrayList<Modality> getModalitys(int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Filtro Cantidad de Dias de Alojamiento
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getCantDaysAccommodation() >= cantDaysAccommodationMin && accommodationModality.getCantDaysAccommodation() <= cantDaysAccommodationMax) // Si esta en el rango de dias
                modalitys.add(modality);
        }
        return modalitys;
    }

    public ArrayList<Modality> getModalitys(double priceMin, double priceMax) { // Filtro Precio
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.price() >= priceMin && accommodationModality.price() <= priceMax) // Si esta en el rango de precio
                modalitys.add(modality);
        }
        return modalitys;
    }

    public ArrayList<Modality> getModalitys(TypeOfRoom typeOfRoom) { // Filtro Tipo de Habitacion
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getTypeOfRoomSelect().equals(typeOfRoom)) // Si tiene el tipo de habitacion
                modalitys.add(modality);
        }

        return modalitys;
    }

    public ArrayList<Modality> getModalitys(MealPlan mealPlan) { // Filtro Plan Alimenticio
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getMealPlanSelect().equals(mealPlan)) // Si tiene el plan alimenticio
                modalitys.add(modality);
        }

        return modalitys;
    }


    public ArrayList<Modality> getModalitys(TypeOfRoom typeOfRoom, MealPlan mealPlan) { // Filtro Tipo de Habitacion + Filtro Plan Alimenticio
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getTypeOfRoomSelect().equals(typeOfRoom) && accommodationModality.getMealPlanSelect().equals(mealPlan)) // Si tiene el tipo de habitacion y el plan alimenticio
                modalitys.add(modality);
        }

        return modalitys;
    }

    public ArrayList<Modality> getModalitys(TypeOfRoom typeOfRoom, double priceMin, double priceMax) { // Filtro Tipo de Habitacion + Filtro Precio
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getTypeOfRoomSelect().equals(typeOfRoom) && accommodationModality.price() >= priceMin && accommodationModality.price() <= priceMax) // Si tiene el tipo de habitacion y el precio esta en el rango
                modalitys.add(modality);
        }

        return modalitys;
    }

    public ArrayList<Modality> getModalitys(TypeOfRoom typeOfRoom, int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Filtro Tipo de Habitacion + Filtro Cantidad de Dias de Alojamiento
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getTypeOfRoomSelect().equals(typeOfRoom) && accommodationModality.getCantDaysAccommodation() >= cantDaysAccommodationMin &&
                    accommodationModality.getCantDaysAccommodation() <= cantDaysAccommodationMax) // Si tiene el tipo de habitacion y la cantidad de dias esta en el rango
                modalitys.add(modality);
        }

        return modalitys;
    }

    public ArrayList<Modality> getModalitys(MealPlan mealPlan, double priceMin, double priceMax) { // Filtro Plan Alimenticio + Filtro Precio
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getMealPlanSelect().equals(mealPlan) && accommodationModality.price() >= priceMin && accommodationModality.price() <= priceMax) // Si tiene el plan alimenticio y el precio esta en rango
                modalitys.add(modality);
        }

        return modalitys;
    }

    public ArrayList<Modality> getModalitys(MealPlan mealPlan, int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Filtro Plan Alimenticio + Filtro Cantidad de Dias de Alojamiento
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getMealPlanSelect().equals(mealPlan) && accommodationModality.getCantDaysAccommodation() >= cantDaysAccommodationMin &&
                    accommodationModality.getCantDaysAccommodation() <= cantDaysAccommodationMax) // Si tiene el plan alimenticio y la cantidad de dias de alojamiento esta en rango
                modalitys.add(modality);
        }

        return modalitys;
    }

    public ArrayList<Modality> getModalitys(double priceMin, double priceMax, int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Filtro Precio + Filtro Cantidad de Dias de Alojamiento
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.price() >= priceMin && accommodationModality.price() <= priceMax && accommodationModality.getCantDaysAccommodation() >= cantDaysAccommodationMin &&
                    accommodationModality.getCantDaysAccommodation() <= cantDaysAccommodationMax) // Si el precio y la cantidad de dias de alojamiento estan en rango
                modalitys.add(modality);
        }
        return modalitys;
    }

    public ArrayList<Modality> getModalitys(TypeOfRoom typeOfRoom, MealPlan mealPlan, double priceMin, double priceMax, int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Filtro Tipo de Habitacion + Filtro Plan Alimenticio + Filtro Precio + Filtro Cantidad de Dias de Alojamiento
        ArrayList<Modality> modalitys = new ArrayList<>();

        for (Modality modality : this.modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getTypeOfRoomSelect().equals(typeOfRoom) && accommodationModality.getMealPlanSelect().equals(mealPlan) && accommodationModality.price() >= priceMin &&
                    accommodationModality.price() <= priceMax && accommodationModality.getCantDaysAccommodation() >= cantDaysAccommodationMin &&
                    accommodationModality.getCantDaysAccommodation() <= cantDaysAccommodationMax) // Si tiene el tipo de habitacion y el plan alimenticio
                modalitys.add(modality);
        }

        return modalitys;
    }

    // Fin de Metodos para la Obtenecion de las Modalidades

}
