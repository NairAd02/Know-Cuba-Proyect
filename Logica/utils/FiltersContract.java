package utils;

import logica.Contract;
import logica.Provider;
import logica.TouristAgency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FiltersContract {

    // Filtros Contratos
    public static ArrayList<Contract> filterTypeOfContract(HashMap<Integer, ArrayList<Contract>> contracts, int typeOfContract) { // metodo para obtener los contratos de un tipo (filtro tipo)
        return contracts.get(typeOfContract);
    }
    public static HashMap<Integer, ArrayList<Contract>> filterProvider(HashMap<Integer, ArrayList<Contract>> contracts, Provider provider) { // metodo para obtener los contratos de un provedor (filtro proveedor)
        HashMap<Integer, ArrayList<Contract>> contractsFilter = TouristAgency.hashMapContractsOf(); // se inicializa el hash map
        ArrayList<Integer> keys = Contract.getKeys(); // se obtienen las llaves del mapa

        // Se itera el mapa

        for (Integer i : keys) { // se itera la lista de llaves
            for (Contract contract : contracts.get(i)) { // se itera la lista de contratos asociada a cada clave
                if (contract.verificarProvedor(provider))
                    contractsFilter.get(i).add(contract); // se añade el el contrato al mapa de filtro
            }
        }

        return contractsFilter;
    }

    public static HashMap<Integer, ArrayList<Contract>> filterState(HashMap<Integer, ArrayList<Contract>> contracts, boolean state) { // metodo para obtener los contratos con un estado en especifico (filtro estado)
        HashMap<Integer, ArrayList<Contract>> contractsFilter = TouristAgency.hashMapContractsOf(); // se inicializa el hash map
        ArrayList<Integer> keys = Contract.getKeys(); // se obtienen las llaves del mapa

        // Se itera el mapa

        for (Integer i : keys) { // se itera la lista de llaves
            for (Contract contract : contracts.get(i)) { // se itera la lista de contratos asociada a cada clave
                if (contract.isState() == state)
                    contractsFilter.get(i).add(contract); // se añade el el contrato al mapa de filtro
            }
        }

        return contractsFilter;
    }

    public static HashMap<Integer, ArrayList<Contract>> filterStratDate(HashMap<Integer, ArrayList<Contract>> contracts, LocalDate startDateMin, LocalDate startDateMax) { // metodo para obtener los contratos con una fecha de inicio en un rango en especifico (filtro fecha de inicio)
        HashMap<Integer, ArrayList<Contract>> contractsFilter = TouristAgency.hashMapContractsOf(); // se inicializa el hash map
        ArrayList<Integer> keys = Contract.getKeys(); // se obtienen las llaves del mapa

        // Se itera el mapa

        for (Integer i : keys) { // se itera la lista de llaves
            for (Contract contract : contracts.get(i)) { // se itera la lista de contratos asociada a cada clave
                if ( (contract.getStartDate().isAfter(startDateMin) || contract.getStartDate().isEqual(startDateMin)) && (contract.getStartDate().isBefore(startDateMax) || contract.getStartDate().isEqual(startDateMax) ) ) // si la fecha del contrato esta en rango
                    contractsFilter.get(i).add(contract); // se añade el el contrato al mapa de filtro
            }
        }

        return contractsFilter;
    }

    public static HashMap<Integer, ArrayList<Contract>> filterTerminationDate(HashMap<Integer, ArrayList<Contract>> contracts, LocalDate terminationDateMin, LocalDate terminationDateMax) { // metodo para obtener los contratos con una fecha de terminacion en un rango en especifico (filtro fecha de terminacion)
        HashMap<Integer, ArrayList<Contract>> contractsFilter = TouristAgency.hashMapContractsOf(); // se inicializa el hash map
        ArrayList<Integer> keys = Contract.getKeys(); // se obtienen las llaves del mapa

        // Se itera el mapa

        for (Integer i : keys) { // se itera la lista de llaves
            for (Contract contract : contracts.get(i)) { // se itera la lista de contratos asociada a cada clave
                if ( (contract.getTerminationDate().isAfter(terminationDateMin) || contract.getTerminationDate().isEqual(terminationDateMin) ) && ( contract.getTerminationDate().isBefore(terminationDateMax) || contract.getTerminationDate().isEqual(terminationDateMax) )) // si la fecha del contrato esta en rango
                    contractsFilter.get(i).add(contract); // se añade el el contrato al mapa de filtro
            }
        }

        return contractsFilter;
    }

    // Fin de Filtros Contratos

}
