package utils;

import logica.Controller;
import logica.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterUser {
    public static final int allTypes = -1;

    // Filtros Usuarios

    public static ArrayList<User> filterTypeUser(HashMap<Integer, ArrayList<User>> users, int typeOfUser) {  // Filtro Tipo de Usuario (Rol)
        return users.get(typeOfUser);
    }


    public static HashMap<Integer, ArrayList<User>> filterName(HashMap<Integer, ArrayList<User>> users, String name) {  // Filtro Name
        HashMap<Integer, ArrayList<User>> usersFilter = Controller.hashMapUsersOf(); // Se inicializa el hashMap de usuarios con la estructura definida

        for (Integer i : User.getKeys()) { // se recorren las llaves del mapa
            for (User user : users.get(i)) { // se recorre la lista de la clave del mapa
                if (user.isSameName(name)) // si el usuario tiene nombre semejante al del filtro
                    usersFilter.get(i).add(user);
            }
        }
        return usersFilter;
    }

    public static HashMap<Integer, ArrayList<User>> filterStateConnection(HashMap<Integer, ArrayList<User>> users, boolean connected) { // Filtro estado de la conexion
        HashMap<Integer, ArrayList<User>> usersFilter = Controller.hashMapUsersOf(); // Se inicializa el hashMap de usuarios con la estructura definida

        // se recorre el mapa

        for (Integer i : User.getKeys()) { // se recorren las llaves del mapa
            for (User user : users.get(i)) { // se recorre la lista de la clave del mapa
                if (user.isConnected() == connected) // si el estado de la conexion del usuario es el mismo que el del filtro
                    usersFilter.get(i).add(user);
            }
        }

        return usersFilter;
    }

    public static HashMap<Integer, ArrayList<User>> filterStartDateConnection(HashMap<Integer, ArrayList<User>> users, LocalDate startDateConnectionMin, LocalDate startDateConnectionMax) { // Filtro StartDateConnection
        HashMap<Integer, ArrayList<User>> usersFilter = Controller.hashMapUsersOf(); // Se inicializa el hashMap de usuarios con la estructura definida

        // se recorre el mapa

        for (Integer i : User.getKeys()) { // se recorren las llaves del mapa
            for (User user : users.get(i)) { // se recorre la lista de la clave del mapa
                if (user.isStatePassword() &&
                        ( user.getStartDateConnection().isAfter(startDateConnectionMin) || user.getStartDateConnection().isEqual(startDateConnectionMin ) ) && ( user.getStartDateConnection().isBefore(startDateConnectionMax) || user.getStartDateConnection().isEqual(startDateConnectionMax) )) // si la fecha inicial del usuario pertenece al rango del filtro
                    usersFilter.get(i).add(user);
            }
        }

        return usersFilter;
    }

    public static HashMap<Integer, ArrayList<User>> filterLastDateConnection(HashMap<Integer, ArrayList<User>> users, LocalDate lastDateConnectionMin, LocalDate lastDateConnectionMax) { // Filtro LastDateConnection
        HashMap<Integer, ArrayList<User>> usersFilter = Controller.hashMapUsersOf(); // Se inicializa el hashMap de usuarios con la estructura definida

        // se recorre el mapa

        for (Integer i : User.getKeys()) { // se recorren las llaves del mapa
            for (User user : users.get(i)) { // se recorre la lista de la clave del mapa
                if (user.isStatePassword() &&
                        (user.getLastDateConnection().isAfter(lastDateConnectionMin) || user.getLastDateConnection().isEqual(lastDateConnectionMin) ) && ( user.getLastDateConnection().isBefore(lastDateConnectionMax) || user.getLastDateConnection().isEqual(lastDateConnectionMax))) // si la fecha inicial del usuario pertenece al rango del filtro
                    usersFilter.get(i).add(user);
            }
        }

        return usersFilter;
    }

    // Fin de Filtros Usuarios
}
