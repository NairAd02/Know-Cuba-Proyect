package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ModalityDAO;
import dao.TouristPackageDAO;
import utils.*;


public class TouristPackage implements DUILogic, LikeName {
    private int id;
    private String name;
    private int cantMaxPax;
    private int cantReserves;
    private LocalDate startDate;

    private LocalDate terminationDate;

    private HashMap<Integer, ArrayList<Modality>> modalitys; // modalidades hasheadas por su tipo
    protected BusquedaResultado busquedaResultado; // Atributo para las tareas de busqueda

    public TouristPackage(int id, String name, int cantMaxPax, int cantReserves, LocalDate startDate, LocalDate terminationDate, HashMap<Integer, ArrayList<Modality>> modalitys) { // Constructor a nivel de base de datos
        this.id = id;
        this.name = name;
        this.cantMaxPax = cantMaxPax;
        this.cantReserves = cantReserves;
        this.startDate = startDate;
        this.terminationDate = terminationDate;
        this.modalitys = modalitys;
    }

    public TouristPackage(String name, int cantMaxPax, int cantReserves, LocalDate startDate, LocalDate terminationDate, ArrayList<Modality> accommodationModalitys, ArrayList<Modality> serviceModalitys, ArrayList<Modality> transportModalitysCostKilometers,
                          ArrayList<Modality> transportModalitysHoursKilometers, ArrayList<Modality> transportModalitysEstablishedRoute) { // Constructor a nivel de logica
        this.name = name;
        this.cantMaxPax = cantMaxPax;
        this.cantReserves = cantReserves;
        this.startDate = startDate;
        this.terminationDate = terminationDate;
        this.construirHashMapModalitys(accommodationModalitys, serviceModalitys, transportModalitysCostKilometers, transportModalitysHoursKilometers, transportModalitysEstablishedRoute); // se construye el hashmap
    }

    private void construirHashMapModalitys(ArrayList<Modality> accommodationModalitys, ArrayList<Modality> serviceModalitys, ArrayList<Modality> transportModalitysCostKilometers,
                                           ArrayList<Modality> transportModalitysHoursKilometers, ArrayList<Modality> transportModalitysEstablishedRoute) {
        this.modalitys = new HashMap<Integer, ArrayList<Modality>>();
        this.modalitys.put(Modality.accommodationModality, (accommodationModalitys != null) ? accommodationModalitys : new ArrayList<Modality>()); // se asignan las modalidades de alojamiento
        this.modalitys.put(Modality.serviceModality, (serviceModalitys != null) ? serviceModalitys : new ArrayList<Modality>()); // se asignan las modalidades de servicio
        this.modalitys.put(Modality.costKilometers, (transportModalitysCostKilometers != null) ? transportModalitysCostKilometers : new ArrayList<Modality>()); // se asignan las modalidades de transporte de tipo costo por kilometraje
        this.modalitys.put(Modality.hoursKilometers, (transportModalitysHoursKilometers != null) ? transportModalitysHoursKilometers : new ArrayList<Modality>()); // se asignan las modalidades de transporte de tipo horas por kilometraje
        this.modalitys.put(Modality.establishedRoute, (transportModalitysEstablishedRoute != null) ? transportModalitysEstablishedRoute : new ArrayList<Modality>()); // se asignan las modalidades de transporte de tipo recorridos establecidos
    }

    private void construirHashMapModalitys() {
        this.modalitys = new HashMap<Integer, ArrayList<Modality>>();
        this.modalitys.put(Modality.accommodationModality, new ArrayList<Modality>()); // se construyen las modalidades de alojamiento
        this.modalitys.put(Modality.serviceModality, new ArrayList<Modality>()); // se construyen las modalidades de servicio
        this.modalitys.put(Modality.costKilometers, new ArrayList<Modality>()); // se construyen las modalidades de transporte de tipo costo por kilometraje
        this.modalitys.put(Modality.hoursKilometers, new ArrayList<Modality>()); // se construyen las modalidades de transporte de tipo horas por kilometraje
        this.modalitys.put(Modality.establishedRoute, new ArrayList<Modality>()); // se construyen las modalidades de transporte de tipo recorridos establecidos
    }

    public TouristPackage() { // Constructor Temporal
        this.id = -1; // se marca como objeto temporal
        this.construirHashMapModalitys();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public int getCantMaxPax() {
        return cantMaxPax;
    }

    public void setCantMaxPax(int cantMaxPax) {
        this.cantMaxPax = cantMaxPax;
    }

    public int getCantReserves() {
        return cantReserves;
    }

    public void setCantReserves(int cantReserves) {
        this.cantReserves = cantReserves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, ArrayList<Modality>> getModalitys() {
        return modalitys;
    }

    public void setModalitys(HashMap<Integer, ArrayList<Modality>> modalitys) {
        this.modalitys = modalitys;
    }

    // Metodos de Carga

    public void actualizarDatos() throws SQLException {
        this.cargarModalitys();
    }

    private void cargarModalitys() throws SQLException {
        this.modalitys = ModalityDAO.getInstancie().selectIntoTouristPackage(this.id); // se obtienen todas las modalidades pertenecientes al paquete

    }

    // Fin de Metodos de Carga


    // Metodos para el control de las modalidades

    public void addModality(Modality modality) throws SQLException {
        if (this.validarModality(modality)) {
            modality.insertIntoPackageTourist(this.id); // se inserta la modalidad en la base de datos como modalidad perteneciente a este paquete turistico
            // se inserta la modalidad en la logica del negocio
            this.addModalityLogic(modality);
        }
    }


    public void addModalityLogic(Modality modality) {

        // se inserta la modalidad en la logica del negocio
        if (modality instanceof ServiceModality) {
            this.modalitys.get(Modality.serviceModality).add(modality);
        } else if (modality instanceof CostKilometers) {
            this.modalitys.get(Modality.costKilometers).add(modality);
        } else if (modality instanceof HoursKilometers) {
            this.modalitys.get(Modality.hoursKilometers).add(modality);
        } else if (modality instanceof EstablishedRoute) {
            this.modalitys.get(Modality.establishedRoute).add(modality);
        } else if (modality instanceof AccommodationModality) {
            this.modalitys.get(Modality.accommodationModality).add(modality);
        }
    }

    public void deleteModality(Modality modality) throws SQLException {
        modality.deleteFromPackageTourist(this.id); // se elimina la modalidad en la base de datos como modalidad perteneciente a este paquete turistico
        // se elimina la modalidad en la logica del negocio
        this.deleteModalityLogic(modality);

    }

    public void deleteModalityLogic(Modality modality) {

        // se elimina la modalidad en la logica del negocio
        if (modality instanceof ServiceModality) {
            this.modalitys.get(Modality.serviceModality).remove(modality);
        } else if (modality instanceof CostKilometers) {
            this.modalitys.get(Modality.costKilometers).remove(modality);
        } else if (modality instanceof HoursKilometers) {
            this.modalitys.get(Modality.hoursKilometers).remove(modality);
        } else if (modality instanceof EstablishedRoute) {
            this.modalitys.get(Modality.establishedRoute).remove(modality);
        } else if (modality instanceof AccommodationModality) {
            this.modalitys.get(Modality.accommodationModality).remove(modality);
        }

    }

	/*public void updateModality (Modality modality) throws SQLException {

		if (modality instanceof ServiceModality) {
			ServiceModalityDAO.getInstancie().update((ServiceModality) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof CostKilometers) {
			CostKilometersDAO.getInstancie().update((CostKilometers) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof HoursKilometers) {
			HoursKilometersDAO.getInstancie().update((HoursKilometers) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof EstablishedRoute) {
			EstablishedRouteDAO.getInstancie().update((EstablishedRoute) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
		else if (modality instanceof AccommodationModality) {
			AccommodationModalityDAO.getInstancie().update((AccommodationModality) modality); // se actualiza la informacion de la modalidad en la base de datos
		}
	}*/

    public ArrayList<Modality> getModalitys(int typeOfModality) { // se obtiene la lista de un tipo de modalidad en especifico
        return this.modalitys.get(typeOfModality);
    }


    // Metodos para la obtencion de las modalidades de Servicio

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getServicesModalitys(Activity activity, LocalDate releasedDateMin, LocalDate releasedDateMax, double precioMin, double precioMax) {
        ArrayList<Modality> modalitys = this.getModalitys(Modality.serviceModality);
        // Filtro Actividad
        if (activity != null)
            modalitys = FiltersServiceModality.filterActivity(modalitys, activity); // se filtra por actividad

        // Filtro Fecha de Realizacion
        if (releasedDateMin != null && releasedDateMax != null)
            modalitys = FiltersServiceModality.filterReleasedDate(modalitys, releasedDateMin, releasedDateMax); // se filtra por el rango de fechas
        else if (releasedDateMin != null)
            modalitys = FiltersServiceModality.filterReleasedDate(modalitys, releasedDateMin, LocalDate.MAX); // se filtra por fecha minima
        else if (releasedDateMax != null)
            modalitys = FiltersServiceModality.filterReleasedDate(modalitys, LocalDate.MIN, releasedDateMax); // se filtra por fecha maxima

        // Filtro Precio
        if (precioMin != AusentFilter.spinnerField && precioMax != AusentFilter.spinnerField)
            modalitys = FiltersServiceModality.filterPrice(modalitys, precioMin, precioMin); // se filtra por el rango de precios
        else if (precioMin != AusentFilter.spinnerField)
            modalitys = FiltersServiceModality.filterPrice(modalitys, precioMin, Double.MAX_VALUE);
        else if (precioMax != AusentFilter.spinnerField)
            modalitys = FiltersServiceModality.filterPrice(modalitys, Double.MIN_VALUE, precioMax);

        return modalitys;
    }
    // Fin de Metodos para la obtencion de las modalidades de Servicio

    // Metodos para la obtencion de las modalidades de Alojamiento
    public ArrayList<Modality> getAccommodationModalitys(TypeOfRoom typeOfRoom, MealPlan mealPlan, double priceMin, double priceMax, int cantDaysAccommodationMin, int cantDaysAccommodationMax) {
        ArrayList<Modality> modalitys = this.getModalitys(Modality.accommodationModality);
        // Se aplican los filtros
        // Filtro Tipo de Habitacion
        if (typeOfRoom != null)
            modalitys = FiltersAccommodationModality.filterTypeOfRoom(modalitys, typeOfRoom); // se filtra por tipo de habitacion
        // Filtro plan Alimenticio
        if (mealPlan != null)
            modalitys = FiltersAccommodationModality.filterMealPlan(modalitys, mealPlan); // se filtra por plan alimenticio

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
    // Fin de Metodos para la obtencion de las modalidades de Alojamiento

    // Metodos para la obtencion de las modalidades de Transporte

    // Metodos para la obtencion de las modalidades de Transporte de Tipo Costo por Kilometraje

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getTransportModalitysCostKilometers(double costKilometersGoingMin, double costKilometersGoingMax, double costKilometersLapMin, double costKilometersLapMax, double costHoursWaitMin, double costHoursWaitMax) {
        ArrayList<Modality> costsKilometers = this.getModalitys(Modality.costKilometers);

        // Se aplican los filtros

        // Filtro CostKilometersGoing
        if (costKilometersGoingMin != AusentFilter.spinnerField && costKilometersGoingMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersGoing(costsKilometers, costKilometersGoingMin, costKilometersGoingMax); // se filtra por el rango de costos
        else if (costKilometersGoingMin != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersGoing(costsKilometers, costKilometersGoingMin, Double.MAX_VALUE); // se filtra solo por le costo minimo
        else if (costKilometersGoingMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersGoing(costsKilometers, Double.MIN_VALUE, costKilometersGoingMax); // se filtra por el costo maximo

        // Filtro CostKilometersLap
        if (costKilometersLapMin != AusentFilter.spinnerField && costKilometersLapMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersLap(costsKilometers, costKilometersLapMin, costKilometersLapMax); // se filtra por el rango de costos
        else if (costKilometersLapMin != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersLap(costsKilometers, costKilometersLapMin, Double.MAX_VALUE); // se filtra solo por le costo minimo
        else if (costKilometersLapMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostKilometersLap(costsKilometers, Double.MIN_VALUE, costKilometersLapMax); // se filtra por el costo maximo

        // Filtro CostHoursWait
        if (costHoursWaitMin != AusentFilter.spinnerField && costHoursWaitMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostHoursWait(costsKilometers, costHoursWaitMin, costHoursWaitMax);  // se filtra por el rango de costos
        else if (costHoursWaitMin != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostHoursWait(costsKilometers, costHoursWaitMin, Double.MAX_VALUE);  // se filtra solo por le costo minimo
        else if (costHoursWaitMax != AusentFilter.spinnerField)
            costsKilometers = FiltersTransportModality.filterCostHoursWait(costsKilometers, Double.MIN_VALUE, costHoursWaitMax);  // se filtra por el costo maximo

        return costsKilometers;
    }

    // Fin de Metodos para la obtencion de las modalidades de Transporte de Tipo Costo por Kilometraje

    // Metodos para la obtencion de las modalidades de Transporte de Tipo Horas por Kilometraje

    public ArrayList<Modality> getTransportModalitysHoursKilometers(double costKilometersRoutMin, double costKilometersRoutMax, double costHoursMin, double costHoursMax,
                                                  double costKilometersRoutAdditionalsMin, double costKilometersRoutAdditionalsMax, double costHoursAdditionalsMin, double costHoursAdditionalsMax) {
        ArrayList<Modality> hoursKilometersList = this.getModalitys(Modality.hoursKilometers);

        // Se aplican los filtros

        // Filtro CostKilometersRout
        if (costKilometersRoutMin != AusentFilter.spinnerField && costKilometersRoutMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRout(hoursKilometersList, costKilometersRoutMin, costKilometersRoutMax); // se aplica el filtro para el rango de costos
        else if (costKilometersRoutMin != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRout(hoursKilometersList, costKilometersRoutMin, Double.MAX_VALUE); // se aplica el filtro para el minimo de costos
        else if (costKilometersRoutMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRout(hoursKilometersList, Double.MIN_VALUE, costKilometersRoutMax); // se aplica el filtro para el maximo de costos

        // Filtro CostHours
        if (costHoursMin != AusentFilter.spinnerField && costHoursMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHours(hoursKilometersList, costHoursMin, costHoursMax); // se aplica el filtro para el rango de costos
        else if (costHoursMin != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHours(hoursKilometersList, costHoursMin, Double.MAX_VALUE); // se aplica el filtro para el minimo de costos
        else if (costHoursMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHours(hoursKilometersList, Double.MIN_VALUE, costHoursMax); // se aplica el filtro para el maximo de costos

        // Filtro CostKilometersRoutAdditionals
        if (costKilometersRoutAdditionalsMin != AusentFilter.spinnerField && costKilometersRoutAdditionalsMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRoutAdditionals(hoursKilometersList, costKilometersRoutAdditionalsMin, costKilometersRoutAdditionalsMax); // se aplica el filtro para el rango de costos
        else if (costKilometersRoutAdditionalsMin != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRoutAdditionals(hoursKilometersList, costKilometersRoutAdditionalsMin, Double.MAX_VALUE); // se aplica el filtro para el minimo de costos
        else if (costKilometersRoutAdditionalsMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostKilometersRoutAdditionals(hoursKilometersList, Double.MIN_VALUE, costKilometersRoutAdditionalsMax); // se aplica el filtro para el maximo de costos

        // Filtro CostHoursAdditionals
        if (costHoursAdditionalsMin != AusentFilter.spinnerField && costHoursAdditionalsMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHoursAdditionals(hoursKilometersList, costHoursAdditionalsMin, costHoursAdditionalsMax); // se aplica el filtro para el rango de costos
        else if (costHoursAdditionalsMin != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHoursAdditionals(hoursKilometersList, costHoursAdditionalsMin, Double.MAX_VALUE); // se aplica el filtro para el minimo de costos
        else if (costHoursAdditionalsMax != AusentFilter.spinnerField)
            hoursKilometersList = FiltersTransportModality.filterCostHoursAdditionals(hoursKilometersList, Double.MIN_VALUE, costHoursAdditionalsMax); // se aplica el filtro para el maximo de costos

        return hoursKilometersList;
    }


    // Fin de Metodos para la obtencion de las modalidades de Transporte de Tipo Costo por Kilometraje

    // Metodos para la obtencion de las modalidades de Transporte de Tipo Recorridos Establecidos


    // Fin de Metodos para la obtencion de las modalidades de Transporte de Tipo Recorridos Establecidos

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getEstablishedRoute(double costGoingMin, double costGoingMax, double costLapMin, double costLapMax) {
        ArrayList<Modality> establishedRouteList = this.getModalitys(Modality.establishedRoute);

        // Se aplican los filtros

        // Filtro CostGoing
        if (costGoingMin != AusentFilter.spinnerField && costGoingMax != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, costGoingMin, costGoingMax); // se aplica el filtro para el rango de costos
        else if (costGoingMin != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, costGoingMin, Double.MAX_VALUE); // se aplica el filtro para el costo minimo
        else if (costGoingMax != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, Double.MIN_VALUE, costGoingMax); // se aplica el filtro para el costo maximo

        // Filtro CostLap
        if (costLapMin != AusentFilter.spinnerField && costLapMax != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, costLapMin, costLapMax); // se aplica el filtro para el rango de costos
        else if (costLapMin != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, costLapMin, Double.MAX_VALUE); // se aplica el filtro para el costo minimo
        else if (costLapMax != AusentFilter.spinnerField)
            establishedRouteList = FiltersTransportModality.filterCostGoing(establishedRouteList, Double.MIN_VALUE, costLapMax); // se aplica el filtro para el costo maximo

        return establishedRouteList;
    }

    // Fin de Metodos para la obtencion de las modalidades de Transporte



    // Fin Metodos para el control de las modalidades

    //Operaciones

    private boolean validarModality(Modality modality) {
        boolean isValidate = true;

        if (modality instanceof AccommodationModality
                && ((AccommodationModality) modality).getCantDaysAccommodation() > this.cantDiasDuracion()) // Se la modalidad es de alojamiento, se verifica que los dias de alojamiento sea mayor  que la cantidad de dias de duracion defininidos en el paquete
            isValidate = false;
        else if (modality instanceof ServiceModality && (((ServiceModality) modality).getReleasedDate().isBefore(this.startDate) || ((ServiceModality) modality).getReleasedDate().isAfter(this.terminationDate))) // Se verifica que la fecha de la actividad no esté en el rango de duracion del paquete
            isValidate = false;

        return isValidate;
    }

    public long cantDiasDuracion() {
        return ChronoUnit.DAYS.between(this.startDate, this.terminationDate);
    }

    public boolean isContainsModality(int tpyOfModality, Modality modality) {
        boolean isContain = false;
        ArrayList<Modality> modalitys = this.modalitys.get(tpyOfModality);

        for (int i = 0; i < modalitys.size() && !isContain; i++) {
            if (modalitys.get(i).equals(modality))
                isContain = true;
        }

        return isContain;
    }

    public int cantAviablePax () {
        return this.cantMaxPax - this.cantReserves;
    }

    public boolean verificarPaquete() { // Metodo para verificar la validadez de un paquete turistico
        return (!this.modalitys.get(Modality.accommodationModality).isEmpty() || !this.modalitys.get(Modality.serviceModality).isEmpty() || !this.modalitys.get(Modality.costKilometers).isEmpty()
                || !this.modalitys.get(Modality.establishedRoute).isEmpty() || !this.modalitys.get(Modality.hoursKilometers).isEmpty()); // Un paquete turistico es valido si al menos presenta una modalidad de cualquier tipo
    }

    public double price() { // Metodo para determinar el costo total de un paquete
        ArrayList<Integer> keys = Modality.getKeys(); // se obtienen todas la llaves para iterar el mapa
        double suma = 0;
        // Se itera el mapa
        for (Integer i : keys) {
            for (Modality modality : this.modalitys.get(i)) { // se itera la lista de modalidades asociadas a esa clave
                suma += modality.price();
            }
        }

        return suma;
    }

    public double precioAlojamiento() { // Metodo para determinar el precio de la seccion de alojamiento
        double precio = 0;
        // Se iteran las modadlidades de servicio para obtener el precio de cada una
        for (Modality accommodationModality : this.modalitys.get(Modality.accommodationModality)) {
            precio += accommodationModality.price();
        }
        return precio;
    }

    public double precioServicios() { // Metodo para determinar el precio de la seccion de servicios
        double precio = 0;
        // Se iteran las modadlidades de servicio para obtener el precio de cada una
        for (Modality serviceModality : this.modalitys.get(Modality.serviceModality)) {
            precio += serviceModality.price();
        }
        return precio;
    }

    public double precioTransporte() { // Metodo para determinar el precio de la seccion de transporte
        return this.precioTransporteCostKilometers() + this.precioTransporteHoursKilometers() + this.precioTransporteEstablishedRoute();
    }

    private double precioTransporteHoursKilometers() {
        double precio = 0;
        // Se iteran las modadlidades de trnasporte tipo horas por kilometro para obtener el precio de cada una
        for (Modality hoursKilometers : this.modalitys.get(Modality.hoursKilometers)) {
            precio += hoursKilometers.price();
        }
        return precio;
    }

    private double precioTransporteCostKilometers() {
        double precio = 0;
        // Se iteran las modadlidades de trnasporte tipo costo por kilometro para obtener el precio de cada una
        for (Modality costKilometers : this.modalitys.get(Modality.costKilometers)) {
            precio += costKilometers.price();
        }
        return precio;
    }

    private double precioTransporteEstablishedRoute() {
        double precio = 0;
        // Se iteran las modadlidades de trnasporte tipo recorridos establecidos para obtener el precio de cada una
        for (Modality establishedRoute : this.modalitys.get(Modality.establishedRoute)) {
            precio += establishedRoute.price();
        }
        return precio;
    }

    // Fin de Operaciones

    // Metodos DUILogic
    @Override
    public void insert() throws SQLException {
        this.id = TouristPackageDAO.getInstancie().insert(this);
        this.insertModalityTouristPackageIntoDataBase();
    }

    private void insertModalityTouristPackageIntoDataBase() throws SQLException { // Metodo para una vez insertado el objeto insertar cada una de sus modalidades en la base de datos
        ArrayList<Integer> keys = Modality.getKeys(); // se obtienen todas la llaves para iterar el mapa

        // Se itera el mapa
        for (Integer i : keys) {
            for (Modality modality : this.modalitys.get(i)) { // se itera la lista de modalidades asociadas a esa clave
                modality.insertIntoPackageTourist(this.id); // se inserta cada modalidad como parte del paquete turistico
            }
        }
    }

    @Override
    public void update() throws SQLException {
        TouristPackageDAO.getInstancie().update(this);
    }


    @Override
    public void delete() throws SQLException {
        TouristPackageDAO.getInstancie().delete(this.id);
    }



    // Fin de Metodos DUILogic

    // Metodo Like Name
    @Override
    public boolean isSameName(String name) {
        boolean veredicto = false;
        String nameComparar = "";
        if(!name.equalsIgnoreCase("")){
            for (int i = 0, j = 0, l = 0; i < this.name.length() && !veredicto ; i++) {

                nameComparar += this.name.charAt(i);

                j++;
                if (j == name.length()){
                    if (name.equalsIgnoreCase(nameComparar)){
                        veredicto = true;
                        this.busquedaResultado = new BusquedaResultado(nameComparar, i - (j - 1), i);
                    }
                    else{
                        nameComparar = "";
                        this.busquedaResultado = null;
                    }
                    j = 0;
                    i = l++;
                }
            }
        }
        else{
            veredicto = true;
            this.busquedaResultado = null;
        }

        return veredicto;
    }

}
