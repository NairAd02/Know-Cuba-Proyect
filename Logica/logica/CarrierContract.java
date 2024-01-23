package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CarrierContractDAO;
import dao.TransportModalityDAO;
import utils.AusentFilter;
import utils.FiltersModality;
import utils.FiltersTransportModality;
import utils.FiltersVehicle;


public class CarrierContract extends Contract {


    public CarrierContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
                           String description, Provider provider, boolean state, String typeOfContract,
                           double surcharge) throws SQLException { // Constructor a nivel de base de datos
        super(id, startDate, terminationDate, reconciliationDate, description, provider, state, typeOfContract,
                surcharge);

    }

    public CarrierContract(LocalDate startDate, LocalDate terminationDate,
                           String description, Provider provider, ArrayList<Modality> modalitys,
                           double surcharge) { // Constructor a nivel de logica
        super(startDate, terminationDate, description, provider, modalitys,
                surcharge);
        this.typeOfContract = "Carrier Contract";
    }

    public CarrierContract() { // Constructor temporal
        super();
    }

    public ArrayList<Vehicle> getVehiclesProvider() {
        return ((TransportationProvider) this.provider).getVehicles();
    }


    // Metodos para el control de las modalidades

    // Metodos para la modificacion de las modalidades
    public void updateTransportModalityCostKilometers(CostKilometers costKilometers, double costKilometersGoing, double costKilometersLap, double costHoursWait) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        this.updateTransportModalityCostKilometersLogic(costKilometers, costKilometersGoing, costKilometersLap, costHoursWait);
        costKilometers.update(); // se actualiza la informacion de la modalidad en la base de datos
    }

    public void updateTransportModalityCostKilometersLogic(CostKilometers costKilometers, double costKilometersGoing, double costKilometersLap, double costHoursWait) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        costKilometers.setCostKilometersGoing(costKilometersGoing);
        costKilometers.setCostKilometersLap(costKilometersLap);
        costKilometers.setCostHoursWait(costHoursWait);
    }

    public void updateTransportationModalityHoursKilometers(HoursKilometers hoursKilometers, double costKilometersRout, double costHours, double costKilometersRoutAdditionals, double costHoursAdditionals) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        this.updateTransportationModalityHoursKilometersLogic(hoursKilometers, costKilometersRout, costHours, costKilometersRoutAdditionals, costHoursAdditionals);
        hoursKilometers.update(); // se actualiza la informacion de la modalidad en la base de datos
    }

    public void updateTransportationModalityHoursKilometersLogic(HoursKilometers hoursKilometers, double costKilometersRout, double costHours, double costKilometersRoutAdditionals, double costHoursAdditionals) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        hoursKilometers.setCostKilometersRout(costKilometersRout);
        hoursKilometers.setCostHours(costHours);
        hoursKilometers.setCostKilometersRoutAdditionals(costKilometersRoutAdditionals);
        hoursKilometers.setCostHoursAdditionals(costHoursAdditionals);
    }

    public void updateTransportationModalityEstablishedRoute(EstablishedRoute establishedRoute, String descriptionRout, double costGoing, double costLap) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        this.updateTransportationModalityEstablishedRouteLogic(establishedRoute, descriptionRout, costGoing, costLap);
        establishedRoute.update(); // se actualiza la informacion de la modalidad en la base de datos
    }

    public void updateTransportationModalityEstablishedRouteLogic(EstablishedRoute establishedRoute, String descriptionRout, double costGoing, double costLap) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        establishedRoute.setDescriptionRout(descriptionRout);
        establishedRoute.setCostGoing(costGoing);
        establishedRoute.setCostLap(costLap);
    }

    // Fin de Metodos para la modificacion de las modalidades

    @Override
    public void insert() throws SQLException {
        this.id = CarrierContractDAO.getInstancie().insert(this);
        this.insertModalitysIntoDataBase();
    }

    @Override
    public void update() throws SQLException {
        CarrierContractDAO.getInstancie().update(this);
    }

    // Metodos de carga
    @Override
    public void cargarModalities() throws SQLException {
        this.modalitys = new ArrayList<Modality>(TransportModalityDAO.getInstancie().selectIntoCarrierContract(this));
    }

    @Override
    public void actualizarDatos() throws SQLException {
        this.cargarModalities();

    }

    // Fin de Metodos de carga

    // Metodos de obtencion


    // Metodos para la obtenecion de modalidades tipo Costo por Kilometraje
    public ArrayList<Modality> getCostKilometers() { // Sin filtros
        ArrayList<Modality> modalitys = new ArrayList<Modality>();

        for (Modality m : this.modalitys) {
            if (m instanceof CostKilometers)
                modalitys.add(m);
        }

        return modalitys;
    }

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getCostKilometers(double costKilometersGoingMin, double costKilometersGoingMax, double costKilometersLapMin, double costKilometersLapMax, double costHoursWaitMin, double costHoursWaitMax,
                                                 double priceMin, double priceMax) {
        ArrayList<Modality> costsKilometers = this.getCostKilometers();

        // Se aplican los filtros

        // Filtro CostKilometersGoing
        if (costKilometersGoingMin != AusentFilter.spinnerField && costKilometersGoingMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersGoing(costsKilometers, costKilometersGoingMin, costKilometersGoingMax); // se filtra por el rango de costos


        // Filtro CostKilometersLap
        if (costKilometersLapMin != AusentFilter.spinnerField && costKilometersLapMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersLap(costsKilometers, costKilometersLapMin, costKilometersLapMax); // se filtra por el rango de costos


        // Filtro CostHoursWait
        if (costHoursWaitMin != AusentFilter.spinnerField && costHoursWaitMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostHoursWait(costsKilometers, costHoursWaitMin, costHoursWaitMax);  // se filtra por el rango de costos


        // Filtro Price
        if (priceMin != AusentFilter.spinnerField && priceMax != AusentFilter.spinnerField)
            FiltersModality.filterPrice(modalitys, priceMin, priceMax); // se aplica el filtro para el rango de precios


        return costsKilometers;
    }


// Fin de Metodos para la obtenecion de modalidades tipo Costo por Kilometraje

    // Metodos para la obtencion de modalidades tipo Horas por Kilometraje

    public ArrayList<Modality> getHoursKilometers() { // Sin Filtros
        ArrayList<Modality> modalitys = new ArrayList<Modality>();

        for (Modality m : this.modalitys) {
            if (m instanceof HoursKilometers)
                modalitys.add(m);
        }

        return modalitys;
    }

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getHoursKilometers(double costKilometersRoutMin, double costKilometersRoutMax, double costHoursMin, double costHoursMax,
                                                  double costKilometersRoutAdditionalsMin, double costKilometersRoutAdditionalsMax, double costHoursAdditionalsMin, double costHoursAdditionalsMax,
                                                  double priceMin, double priceMax) {
        ArrayList<Modality> hoursKilometersList = this.getHoursKilometers();

        // Se aplican los filtros

        // Filtro CostKilometersRout
        if (costKilometersRoutMin != AusentFilter.spinnerField && costKilometersRoutMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRout(hoursKilometersList, costKilometersRoutMin, costKilometersRoutMax); // se aplica el filtro para el rango de costos


        // Filtro CostHours
        if (costHoursMin != AusentFilter.spinnerField && costHoursMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHours(hoursKilometersList, costHoursMin, costHoursMax); // se aplica el filtro para el rango de costos


        // Filtro CostKilometersRoutAdditionals
        if (costKilometersRoutAdditionalsMin != AusentFilter.spinnerField && costKilometersRoutAdditionalsMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRoutAdditionals(hoursKilometersList, costKilometersRoutAdditionalsMin, costKilometersRoutAdditionalsMax); // se aplica el filtro para el rango de costos


        // Filtro CostHoursAdditionals
        if (costHoursAdditionalsMin != AusentFilter.spinnerField && costHoursAdditionalsMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHoursAdditionals(hoursKilometersList, costHoursAdditionalsMin, costHoursAdditionalsMax); // se aplica el filtro para el rango de costos


        // Filtro Price
        if (priceMin != AusentFilter.spinnerField && priceMax != AusentFilter.spinnerField)
            FiltersModality.filterPrice(modalitys, priceMin, priceMax); // se aplica el filtro para el rango de precios


        return hoursKilometersList;
    }

    // Fin de Metodos para la obtencion de modalidades tipo Horas por Kilometraje

    // Metodos para la obtencion de modalidades tipo Recorridos Establecidos

    public ArrayList<Modality> getEstablishedRoute() {
        ArrayList<Modality> modalitys = new ArrayList<Modality>();

        for (Modality m : this.modalitys) {
            if (m instanceof EstablishedRoute)
                modalitys.add(m);
        }

        return modalitys;
    }

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getEstablishedRoute(double costGoingMin, double costGoingMax, double costLapMin, double costLapMax,
                                                   double priceMin, double priceMax) {
        ArrayList<Modality> establishedRouteList = this.getEstablishedRoute();

        // Se aplican los filtros

        // Filtro CostGoing
        if (costGoingMin != AusentFilter.spinnerField && costGoingMax != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, costGoingMin, costGoingMax); // se aplica el filtro para el rango de costos


        // Filtro CostLap
        if (costLapMin != AusentFilter.spinnerField && costLapMax != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, costLapMin, costLapMax); // se aplica el filtro para el rango de costos


        // Filtro Price
        if (priceMin != AusentFilter.spinnerField && priceMax != AusentFilter.spinnerField)
            FiltersModality.filterPrice(modalitys, priceMin, priceMax); // se aplica el filtro para el rango de precios


        return establishedRouteList;
    }

    // Fin de Metodos para la obtencion de modalidades tipo Recorridos Establecidos

    public ArrayList<Vehicle> getVehiclesProvider(String lock) {
        return ((TransportationProvider) this.provider).getVehicles(lock);
    }

    // Fin Metodos de obtencion
    // Fin Metodos para el control de las modalidades

}
