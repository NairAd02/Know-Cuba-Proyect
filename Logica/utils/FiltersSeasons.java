package utils;

import logica.Season;

import java.time.LocalDate;
import java.util.ArrayList;

public class FiltersSeasons {

    public static ArrayList<Season> filterName(ArrayList<Season> seasons, String name) { // Filtro Nombre
        ArrayList<Season> seasonsFilter = new ArrayList<Season>();

        for (Season season : seasons) {
           if (season.isSameName(name)) // si la temporada tiene nombre parecido a la temporada del filtro
               seasonsFilter.add(season);
        }

        return seasonsFilter;
    }

    public static ArrayList<Season> filterTypeSeason(ArrayList<Season> seasons, String typeSeason) { // Filtro Tipo de Temporada
        ArrayList<Season> seasonsFilter = new ArrayList<Season>();

        for (Season season : seasons) {
            if (season.isEqualsType(typeSeason)) // si la temporada tiene el mismo tipo del filtro
                seasonsFilter.add(season);
        }

        return seasonsFilter;
    }

    public static ArrayList<Season> filterStartDate(ArrayList<Season> seasons, LocalDate startDateMin, LocalDate startDateMax) { // Filtro StartDate
        ArrayList<Season> seasonsFilter = new ArrayList<Season>();

        for (Season season : seasons) {
            if ( (season.getStartDate().isAfter(startDateMin) || season.getStartDate().isEqual(startDateMin) ) && (season.getStartDate().isBefore(startDateMax) || season.getStartDate().isEqual(startDateMax)) ) // si la fecha de inicio de la temporada se encuentra dentro del rango de fecha del filto
                seasonsFilter.add(season);
        }

        return seasonsFilter;
    }

    public static ArrayList<Season> filterTerminationDate(ArrayList<Season> seasons, LocalDate terminationDateMin, LocalDate terminationDateMax) { // Filtro TerminationDate
        ArrayList<Season> seasonsFilter = new ArrayList<Season>();

        for (Season season : seasons) {
            if ( (season.getTerminationDate().isAfter(terminationDateMin) || season.getTerminationDate().isEqual(terminationDateMin) ) && (season.getTerminationDate().isBefore(terminationDateMax) || season.getTerminationDate().isEqual(terminationDateMax)) ) // si la fecha de inicio de la temporada se encuentra dentro del rango de fecha del filto
                seasonsFilter.add(season);
        }

        return seasonsFilter;
    }
}
