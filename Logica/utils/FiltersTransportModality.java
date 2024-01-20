package utils;

import logica.CostKilometers;
import logica.EstablishedRoute;
import logica.HoursKilometers;
import logica.Modality;

import java.util.ArrayList;

public class FiltersTransportModality {

    // FILTROS HORAS POR KILOMETRAJE
    public static ArrayList<Modality> filterCostKilometersRout(ArrayList<Modality> modalitys, double costKilometersRoutMin, double costKilometersRoutMax) { // Filtro CostKilometersRout
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            HoursKilometers hoursKilometers = (HoursKilometers) modality;
            if (hoursKilometers.getCostKilometersRout() >= costKilometersRoutMin && hoursKilometers.getCostKilometersRout() <= costKilometersRoutMax) // si el costo esta en el rango
                modalitysFilter.add(hoursKilometers);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterCostHours(ArrayList<Modality> modalitys, double costHoursMin, double costHoursMax) { // Filtro CostHours
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            HoursKilometers hoursKilometers = (HoursKilometers) modality;
            if (hoursKilometers.getCostHours() >= costHoursMin && hoursKilometers.getCostHours() <= costHoursMax) // si el costo esta en el rango
                modalitysFilter.add(hoursKilometers);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterCostKilometersRoutAdditionals(ArrayList<Modality> modalitys, double costKilometersRoutAdditionalsMin, double costKilometersRoutAdditionalsMax) { // Filtro CostKilometersRoutAdditionals
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            HoursKilometers hoursKilometers = (HoursKilometers) modality;
            if (hoursKilometers.getCostKilometersRoutAdditionals() >= costKilometersRoutAdditionalsMin && hoursKilometers.getCostKilometersRoutAdditionals() <= costKilometersRoutAdditionalsMax) // si el costo esta en el rango
                modalitysFilter.add(hoursKilometers);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterCostHoursAdditionals(ArrayList<Modality> modalitys, double costHoursAdditionalsMin, double costHoursAdditionalsMax) { // Filtro CostHoursAdditionals
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            HoursKilometers hoursKilometers = (HoursKilometers) modality;
            if (hoursKilometers.getCostHoursAdditionals() >= costHoursAdditionalsMin && hoursKilometers.getCostHoursAdditionals() <= costHoursAdditionalsMax) // si el costo esta en el rango
                modalitysFilter.add(hoursKilometers);
        }

        return modalitysFilter;
    }

    // FIN FILTROS HORAS POR KILOMETRAJE

    // FILTROS COSTO POR KILOMETRAJE
    public static ArrayList<Modality> filterCostKilometersGoing(ArrayList<Modality> modalitys, double costKilometersGoingMin, double costKilometersGoingMax) { // Filtro CostKilometersGoing
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            CostKilometers costKilometers = (CostKilometers) modality;
            if (costKilometers.getCostKilometersGoing() >= costKilometersGoingMin && costKilometers.getCostKilometersGoing() <= costKilometersGoingMax) // si el costo esta en rango
                modalitysFilter.add(costKilometers);
        }
        return modalitysFilter;
    }

    public static ArrayList<Modality> filterCostKilometersLap(ArrayList<Modality> modalitys, double costKilometersLapMin, double costKilometersLapMax) { // Filtro CostKilometersLap
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            CostKilometers costKilometers = (CostKilometers) modality;
            if (costKilometers.getCostKilometersLap() >= costKilometersLapMin && costKilometers.getCostKilometersLap() <= costKilometersLapMax) // si el costo esta en rango
                modalitysFilter.add(costKilometers);
        }
        return modalitysFilter;
    }

    public static ArrayList<Modality> filterCostHoursWait(ArrayList<Modality> modalitys, double costHoursWaitMin, double costHoursWaitMax) { // Filtro CostHoursWait
        ArrayList<Modality> modalitysFilter = new ArrayList<Modality>();

        for (Modality modality : modalitys) {
            CostKilometers costKilometers = (CostKilometers) modality;
            if (costKilometers.getCostHoursWait() >= costHoursWaitMin && costKilometers.getCostHoursWait() <= costHoursWaitMax) // si el costo esta en rango
                modalitysFilter.add(costKilometers);

        }
        return modalitysFilter;
    }


// FIN FILTROS COSTO POR KILOMETRAJE

    // FILTROS RECORRIDOS ESTABLECIDOS
    public static ArrayList<Modality> filterCostGoing(ArrayList<Modality> modalitys, double costGoingMin, double costGoingMax) { // Filtro CostGoing
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            EstablishedRoute establishedRoute = (EstablishedRoute) modality;
            if (establishedRoute.getCostGoing() >= costGoingMin && establishedRoute.getCostGoing() <= costGoingMax) // si el costo esta en el rango
                modalitysFilter.add(establishedRoute);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterCostLap(ArrayList<Modality> modalitys, double costLapMin, double costLapMax) { // Filtro CostLap
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            EstablishedRoute establishedRoute = (EstablishedRoute) modality;
            if (establishedRoute.getCostLap() >= costLapMin && establishedRoute.getCostLap() <= costLapMax) // si el costo esta en el rango
                modalitysFilter.add(establishedRoute);
        }

        return modalitysFilter;
    }

    // FIN DE FILTROS RECORRIDOS ESTABLECIDOS

}
