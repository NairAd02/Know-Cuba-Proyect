package utils;

import logica.Vehicle;

import java.util.ArrayList;

public class FiltersVehicle {

    public static ArrayList<Vehicle> filterLock(ArrayList<Vehicle> vehicles, String lock) {
        ArrayList<Vehicle> vehiclesFilter = new ArrayList<Vehicle>();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.isSameName(lock)) // se el vehiculo tiene la chapa parecida a la del filtro
                vehiclesFilter.add(vehicle);
        }

        return vehiclesFilter;
    }
}
