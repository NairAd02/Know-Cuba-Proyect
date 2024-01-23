package utils;

import logica.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class FiltersServiceModality {

    // FILTROS MODALIDAD DE SERVICIOS
    public static ArrayList<Modality> filterActivity(ArrayList<Modality> modalitys, Activity activity) { // Filtro Actividad
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            ServiceModality serviceModality = (ServiceModality) modality;
            if (serviceModality.getActivity().equals(activity))
                modalitysFilter.add(modality);
        }

        return modalitysFilter;
    }

    public static ArrayList<Modality> filterReleasedDate(ArrayList<Modality> modalitys, LocalDate releasedDateMin, LocalDate releasedDateMax) { // Filtro Actividad
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            ServiceModality serviceModality = (ServiceModality) modality;
            if ( (serviceModality.getReleasedDate().isAfter(releasedDateMin) || serviceModality.getReleasedDate().isEqual(releasedDateMin)  ) && (serviceModality.getReleasedDate().isBefore(releasedDateMax) || serviceModality.getReleasedDate().isEqual(releasedDateMax)) ) // se verifica que la fecha esté en rango
                modalitysFilter.add(modality);
        }

        return modalitysFilter;
    }


    // FIN DE FILTROS MODALIDAD DE SERVICIOS

}
