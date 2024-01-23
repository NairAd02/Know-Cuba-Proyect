package utils;

import logica.Modality;
import java.util.ArrayList;

public class FiltersModality {

    // FILTROS GENERALES

    public static ArrayList<Modality> filterPrice(ArrayList<Modality> modalitys, double priceMin, double priceMax) { // Filtro Price
        ArrayList<Modality> modalitysFilter = new ArrayList<>();

        for (Modality modality : modalitys) {
            if (modality.price() >= priceMin && modality.price() <= priceMax) // si el costo esta en el rango
                modalitysFilter.add(modality);
        }

        return modalitysFilter;
    }

    // FIN DE FILTROS GENERALES
}
