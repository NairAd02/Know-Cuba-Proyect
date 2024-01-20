package utils;

import logica.*;

import java.util.ArrayList;

public class FiltersAccommodationModality {

    // FILTROS MODALIDAD DE ALOJAMIENTO
    public static ArrayList<Modality> filterCantDaysAccommodations(ArrayList<Modality> modalitys, int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Filtro Cantidad de Dias de Alojamiento
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getCantDaysAccommodation() >= cantDaysAccommodationMin && accommodationModality.getCantDaysAccommodation() <= cantDaysAccommodationMax) // Si esta en el rango de dias
                modalitysFilter.add(modality);
        }
        return modalitysFilter;
    }

    public static ArrayList<Modality> filterPrice(ArrayList<Modality> modalitys, double priceMin, double priceMax) { // Filtro Precio
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.price() >= priceMin && accommodationModality.price() <= priceMax) // Si esta en el rango de precio
                modalitysFilter.add(modality);
        }
        return modalitysFilter;
    }

    public static ArrayList<Modality> filterTypeOfRoom(ArrayList<Modality> modalitys, TypeOfRoom typeOfRoom) { // Filtro Tipo de Habitacion
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getTypeOfRoomSelect().equals(typeOfRoom)) // Si tiene el tipo de habitacion
                modalitysFilter.add(modality);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterMealPlan(ArrayList<Modality> modalitys, MealPlan mealPlan) { // Filtro Plan Alimenticio
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getMealPlanSelect().equals(mealPlan)) // Si tiene el plan alimenticio
                modalitysFilter.add(modality);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterHotelModality(ArrayList<Modality> modalitys, HotelModality hotelModality) { // Filtro Modalidad de Hotel
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            AccommodationModality accommodationModality = (AccommodationModality) modality;
            if (accommodationModality.getHotelModality().equals(hotelModality)) // Si tiene la modalidad de hotel
                modalitysFilter.add(modality);
        }

        return modalitysFilter;
    }

    // FIN DE FILTROS MODALIDAD DE ALOJAMIENTO
}
