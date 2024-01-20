package utils;

import logica.TouristPackage;

import java.time.LocalDate;
import java.util.ArrayList;

public class FiltersTouristPackage {

    // Filtros Paquete Turistico
    public static ArrayList<TouristPackage> filterName (ArrayList<TouristPackage> touristPackages, String name) { // Filtro Name
        ArrayList<TouristPackage> touristPackagesFilter = new ArrayList<>();

        for (TouristPackage touristPackage : touristPackages) {
            if (touristPackage.isSameName(name)) // si se encuentra un paquete turistico con nombre similar
                touristPackagesFilter.add(touristPackage);
        }

        return touristPackagesFilter;
    }

    public static ArrayList<TouristPackage> filterStartDate (ArrayList<TouristPackage> touristPackages, LocalDate startDateMin, LocalDate startDateMax) { // Filtro StartDate
        ArrayList<TouristPackage> touristPackagesFilter = new ArrayList<>();

        for (TouristPackage touristPackage : touristPackages) {
            if (touristPackage.getStartDate().isAfter(startDateMin) && touristPackage.getStartDate().isBefore(startDateMax)) // si se encuentra un paquete turistico con la fecha de inicio en el rango de fechas
                touristPackagesFilter.add(touristPackage);
        }

        return touristPackagesFilter;
    }

    public static ArrayList<TouristPackage> filterTerminationDate (ArrayList<TouristPackage> touristPackages, LocalDate terminationDateMin, LocalDate terminationDateMax) { // Filtro TerminationDate
        ArrayList<TouristPackage> touristPackagesFilter = new ArrayList<>();

        for (TouristPackage touristPackage : touristPackages) {
            if (touristPackage.getTerminationDate().isAfter(terminationDateMin) && touristPackage.getTerminationDate().isBefore(terminationDateMax)) // si se encuentra un paquete turistico con la fecha de inicio en el rango de fechas
                touristPackagesFilter.add(touristPackage);
        }

        return touristPackagesFilter;
    }

    public static ArrayList<TouristPackage> filterPrice (ArrayList<TouristPackage> touristPackages, double priceMin, double priceMax) { // Filtro Price
        ArrayList<TouristPackage> touristPackagesFilter = new ArrayList<>();

        for (TouristPackage touristPackage : touristPackages) {
            if (touristPackage.price() >= priceMin && touristPackage.price() <= priceMax) // si se encuentra un paquete turistico con el precio en el rango de precios
                touristPackagesFilter.add(touristPackage);
        }

        return touristPackagesFilter;
    }

    public static ArrayList<TouristPackage> filterCantAviablePax (ArrayList<TouristPackage> touristPackages, int cantAviablePaxMin, int cantAviablePaxMax) { // Filtro CantAviablePax
        ArrayList<TouristPackage> touristPackagesFilter = new ArrayList<>();

        for (TouristPackage touristPackage : touristPackages) {
            if (touristPackage.cantAviablePax() >= cantAviablePaxMin && touristPackage.cantAviablePax() <= cantAviablePaxMax) // si se encuentra un paquete turistico con el precio en el rango de precios
                touristPackagesFilter.add(touristPackage);
        }

        return touristPackagesFilter;
    }

    // Fin de Filtros Paquete Turistico

}
