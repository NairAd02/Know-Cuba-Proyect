package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import dao.AccommodationContractDAO;
import dao.CarrierContractDAO;
import dao.HotelDAO;
import dao.ServiceContractDAO;
import dao.ServiceProviderDAO;
import dao.TouristPackageDAO;
import dao.TransportationProviderDAO;
import utils.AusentFilter;
import utils.FiltersContract;
import utils.FiltersTouristPackage;


public class TouristAgency {
    private ArrayList<TouristPackage> touristPackages; // los paquetes turisticos disponible en la empresa
    private HashMap<Integer, ArrayList<Provider>> providers; // los proveedores de la agencia hasheados por su tipo
    private HashMap<Integer, ArrayList<Contract>> contracts; // los contratos de la agencia hasheados por su tipo


    public TouristAgency() throws SQLException { // Singlenton
        this.cargarPaquetesTuristicos(); // se cargan los paquetes turisticos de la base de datos
        this.cargarProvedores(); // se cargan los provedores desde la base de datos
        this.cargarContratos(); // se cargan los contractos desde la base de datos
    }

    // Metodos de Carga de Informacion
    // Detalle a tener en cuenta es crear una tabla que defina los tipos de proveedores existentes. De esta forma se puede hacer genericidad la construccion del hashMap
    public void cargarProvedores() throws SQLException {
        this.providers = new HashMap<Integer, ArrayList<Provider>>();
        this.providers.put(Provider.serviceProvider, new ArrayList<Provider>(ServiceProviderDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de serivicio desde la base de datos
        this.providers.put(Provider.transportationProvider, new ArrayList<Provider>(TransportationProviderDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de transporte desde la base de datos
        this.providers.put(Provider.accommodationProvider, new ArrayList<Provider>(HotelDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de alojamiento desde la base de datos
    }

    public void cargarContratos() throws SQLException {
        this.contracts = new HashMap<Integer, ArrayList<Contract>>();
        this.contracts.put(Contract.serviceContract, new ArrayList<Contract>(ServiceContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de serivicio desde la base de datos
        this.contracts.put(Contract.carrierContract, new ArrayList<Contract>(CarrierContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de transportista desde la base de datos
        this.contracts.put(Contract.accommodationContract, new ArrayList<Contract>(AccommodationContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de alojamiento desde la base de datos
    }

    public void cargarPaquetesTuristicos() throws SQLException {
        this.touristPackages = new ArrayList<TouristPackage>();
        this.touristPackages = (ArrayList<TouristPackage>) TouristPackageDAO.getInstancie().selectAll(); // se cargan todos los paquetes turisticos de la base de datos;
    }


    // Fin Metodos de Carga de Informacion

    // Metodos para la inserccion de datos

    public void addContract(Contract contract) throws SQLException {
        contract.insert(); // se inserta el contrato en la base de datos

        if (contract instanceof ServiceContract) {
            this.contracts.get(Contract.serviceContract).add(contract); // se inserta el contrato en la logica del negocio
        } else if (contract instanceof CarrierContract) {
            this.contracts.get(Contract.carrierContract).add(contract); // se inserta el contrato en la logica del negocio
        } else if (contract instanceof AccommodationContract) {
            this.contracts.get(Contract.accommodationContract).add(contract); // se inserta el contrato en la logica del negocio
        }
    }

    public void addProvider(Provider provider) throws SQLException {
        provider.insert(); // se inserta el provedor de alojamiento en la base de datos

        if (provider instanceof ServiceProvider) {
            this.providers.get(Provider.serviceProvider).add(provider); // se inserta el provedor de servicios en la logica del negocio
        } else if (provider instanceof TransportationProvider) {
            this.providers.get(Provider.transportationProvider).add(provider); // se inserta el provedor de transporte en la logica del negocio
        } else if (provider instanceof Hotel) {
            this.providers.get(Provider.accommodationProvider).add(provider); // se inserta el provedor de alojamiento en la logica del negocio
        }
    }

    public void addTouristPackage(TouristPackage touristPackage) throws SQLException {
        touristPackage.insert(); // se inserta el paquete en la base de datos
        this.touristPackages.add(touristPackage); // se inserta el paquete en la logica del negocio
    }

    // Fin de los Metodos para la inserccion de datos

    // Metodos de eliminacion de los datos

    public void deleteContract(Contract contract) throws SQLException {
        contract.delete(); // se elimina al contrato de la base de datos

        if (contract instanceof ServiceContract) {
            this.contracts.get(Contract.serviceContract).remove(contract); // se elimina el contrato de la logica del negocio
        } else if (contract instanceof CarrierContract) {
            this.contracts.get(Contract.carrierContract).remove(contract); // se elimina el contrato de la logica del negocio
        } else if (contract instanceof AccommodationContract) {
            this.contracts.get(Contract.accommodationContract).remove(contract); // se elimina el contrato de la logica del negocio
        }
    }

    public void deleteProvider(Provider provider) throws SQLException {
        provider.delete(); // se elimina al provedor de la base de datos
        if (provider instanceof ServiceProvider)
            this.providers.get(Provider.serviceProvider).remove(provider); // se elimina al provedor de la logica del negocio
        else if (provider instanceof TransportationProvider)
            this.providers.get(Provider.transportationProvider).remove(provider); // se elimina al provedor de la logica del negocio
        else if (provider instanceof Hotel)
            this.providers.get(Provider.accommodationProvider).remove(provider); // se elimina al provedor de la logica del negocio
    }

    public void deleteTouristPackage(TouristPackage touristPackage) throws SQLException {
        touristPackage.delete(); // se elimina el paquete turistico de la base de datos
        this.touristPackages.remove(touristPackage); // se elimina el paquete turistico de la logica del negocio
    }

    // Fin Metodos de eliminacion de los datos

    // Metodos para la actualizacion de los datos

    public void updateContract(Contract contract, LocalDate contractStrartDate, LocalDate contractTerminationDate, String contractDescription, double surcharge) throws SQLException { // TEMPORAL
        // se actualiza la informacion del contrato a nivel de logica
        contract.setStartDate(contractStrartDate);
        contract.setTerminationDate(contractTerminationDate);
        contract.setDescription(contractDescription);
        contract.setSurcharge(surcharge);
        contract.update(); // se actualiza la informacion del contrato en la base de datos
    }


    public void updateHotel(Hotel hotel, String name, String province, String hotelChain, int hotelCategory, String address, int phone,
                            String fax, String email, int cantRooms, int cantFloors, String locationHotel, double distanceNearestCity,
                            double distanceAirport, LocalDate dateBuild) throws SQLException {
        // se actualiza la informacion del provedor a nivel de logica
        hotel.setName(name);
        hotel.setHotelChain(hotelChain);
        hotel.setProvince(province);
        hotel.setAddress(address);
        hotel.setHotelCategory(hotelCategory);
        hotel.setPhone(phone);
        hotel.setFax(fax);
        hotel.setEmail(email);
        hotel.setCantRooms(cantRooms);
        hotel.setCantFloors(cantFloors);
        hotel.setLocationHotel(locationHotel);
        hotel.setDistanceNearestCity(distanceNearestCity);
        hotel.setDistanceAirport(distanceAirport);
        hotel.setDateBuild(dateBuild);

        hotel.update(); // se actualiza la informacion del provedor a nivel de base de datos
    }

    public void updateServiceProvider(ServiceProvider serviceProvider, String name, String province) throws SQLException {
        // se actualiza la informacion del provedor a nivel de logica
        serviceProvider.setName(name);
        serviceProvider.setProvince(province);
        serviceProvider.update(); // se actualiza la informacion del provedor a nivel de base de datos
    }

    public void updateTransportationProvider(TransportationProvider transportationProviver, String name, String province) throws SQLException {
        // se actualiza la informacion del provedor a nivel de logica
        transportationProviver.setName(name);
        transportationProviver.setProvince(province);
        transportationProviver.update(); // se actualiza la informacion del provedor a nivel de base de datos
    }

    public void updateTouristPackage(TouristPackage touristPackage, String name, int cantMaxPax, int cantRerserves) throws SQLException {
        // se actualiza la informacion del paquete turistico a nivel de logica
        touristPackage.setName(name);
        touristPackage.setCantMaxPax(cantMaxPax);
        touristPackage.setCantReserves(cantRerserves);
        touristPackage.update(); // se actualiza el paquete turistico en la base de datos
    }

    // Fin Metodos para la actualizacion de los datos

    // Metodos para la obtencion de los datos

    // Metodos para la obtencion de los contratos

    public HashMap<Integer, ArrayList<Contract>> getContracts() { // metodo para obtener todos los contrato (sin filtros)
        return this.contracts;
    }

    public static HashMap<Integer, ArrayList<Contract>> hashMapContractsOf() { // Metodo que define una implementacion unica para inicializar los hashMap que trabajen con los contratos
        HashMap<Integer, ArrayList<Contract>> contract = new HashMap<>();
        contract.put(Contract.accommodationContract, new ArrayList<Contract>()); // Se crea la seccion de contratos de alojamiento
        contract.put(Contract.serviceContract, new ArrayList<Contract>()); // Se crea la seccion de contratos de servicio
        contract.put(Contract.carrierContract, new ArrayList<Contract>()); // Se crea la seccion de contratos de transporte

        return contract;
    }

    // Metodo de obtencion con filtros
    public HashMap<Integer, ArrayList<Contract>> getContracts(int typeOfContract, Provider provider, int state, LocalDate startDateMin, LocalDate startDateMax,
                                                              LocalDate terminationDateMin, LocalDate terminationDateMax) {
        HashMap<Integer, ArrayList<Contract>> contracts = this.hashMapContractsOf(); // se inicializa el hash map

        // Se aplican los filtros

        // Filtro Tipo de Contrato
        if (typeOfContract != -1)
            contracts.put(typeOfContract, FiltersContract.filterTypeOfContract(this.contracts, typeOfContract));
        else
            contracts = this.contracts;

        // Filtro Proveedor

        if (provider != null)
            contracts = FiltersContract.filterProvider(contracts, provider); // se filtra por proveedor

        // Filtro Estado

        if (state != AusentFilter.stateLess) // Si fue seleccionado estado como filtro
            contracts = FiltersContract.filterState(contracts, (state == Contract.closeState)); // se filtra por estado

        // Filtro Fecha de Inicio

        if (startDateMin != null && startDateMax != null)
            contracts = FiltersContract.filterStratDate(contracts, startDateMin, startDateMax); // se filtra por el rango de fechas
        else if (startDateMin != null)
            contracts = FiltersContract.filterStratDate(contracts, startDateMin, LocalDate.MAX); // se filtra por la fecha minima
        else if (startDateMax != null)
            contracts = FiltersContract.filterStratDate(contracts, LocalDate.MIN, startDateMax); // se filtra por la fecha maxima

        // Filtro Fecha de Terminacion

        if (terminationDateMin != null && terminationDateMax != null)
            contracts = FiltersContract.filterTerminationDate(contracts, terminationDateMin, terminationDateMax); // se filtra por el rango de fechas
        else if (terminationDateMin != null)
            contracts = FiltersContract.filterTerminationDate(contracts, terminationDateMin, LocalDate.MAX); // se filtra por la fecha minima
        else if (terminationDateMax != null)
            contracts = FiltersContract.filterTerminationDate(contracts, LocalDate.MIN, terminationDateMax); // se filtra por la fecha maxima


        return contracts;
    }

    // Fin de Metodos para la obtencion de los contratos

    // Metodos para la obtencion de los proveedores

    // IMPLEMENTAR FILTROS

    public HashMap<Integer, ArrayList<Provider>> getProviders() { // metodo para obtener todos los contrato
        return this.providers;
    }

    public ArrayList<Provider> getProviders(int typeOfProvider) { // metodo para obtener los provedores de un tipo
        return this.providers.get(typeOfProvider);
    }

    public ArrayList<Provider> getProviders(String name, int typeOfProvider) { // metodo para obtener los provedores de un tipo
        ArrayList<Provider> provideres = new ArrayList<Provider>();

        for (Provider provider : this.providers.get(typeOfProvider)) {
            if (provider.isSameName(name))
                provideres.add(provider);
        }
        return provideres;
    }

    // Fin de Metodos para la obtencion de los proveedores


    // Metodos para la obtencion de modalidades


    // Metodo de obtencion de modalidades de alojamiento con filtros
    public ArrayList<Modality> getAccommodationModalitys(TypeOfRoom typeOfRoom, MealPlan mealPlan, HotelModality hotelModality, double priceMin, double priceMax, int cantDaysAccommodationMin, int cantDaysAccommodationMax) { // Metodo para obtener todas las modalidades de alojamiento de la agencia
        ArrayList<Modality> accommodationModalitys = new ArrayList<Modality>();

        for (Contract accommodationContract : this.contracts.get(Contract.accommodationContract)) {
            accommodationModalitys.addAll(((AccommodationContract) accommodationContract).getModalitys(typeOfRoom, mealPlan, hotelModality, priceMin, priceMax, cantDaysAccommodationMin, cantDaysAccommodationMax));
        }
        return accommodationModalitys;
    }

    // Metodo de obtencion de modalidades de servicios con filtros
    public ArrayList<Modality> getServiceModalitys(Activity activity, LocalDate releasedDateMin, LocalDate releasedDateMax, double precioMin, double precioMax) { // Metodo para obtener todas las modalidades de servicio de la agencia
        ArrayList<Modality> serviceModalitys = new ArrayList<Modality>();

        for (Contract serviceContract : this.contracts.get(Contract.serviceContract)) {
            serviceModalitys.addAll(((ServiceContract) serviceContract).getModalitys(activity, releasedDateMin, releasedDateMax, precioMin, precioMax));
        }

        return serviceModalitys;
    }

    // Metodo de obtencion de modalidades de transporte de tipo costo por kilometraje con filtros
    public ArrayList<Modality> getTransportModalityCostKilometers(double costKilometersGoingMin, double costKilometersGoingMax, double costKilometersLapMin,
                                                                  double costKilometersLapMax, double costHoursWaitMin, double costHoursWaitMax,
                                                                  double priceMin, double priceMax) { // Metodo para obtener todas las modalidades de transporte tipo costo por kilometraje de la agencia
        ArrayList<Modality> transportModalitysCostKilometers = new ArrayList<Modality>();

        for (Contract carrierContract : this.contracts.get(Contract.carrierContract)) {
            transportModalitysCostKilometers.addAll(((CarrierContract) carrierContract).getCostKilometers(costKilometersGoingMin, costKilometersGoingMax,
                    costKilometersLapMin, costKilometersLapMax, costHoursWaitMin, costHoursWaitMax, priceMin, priceMax));
        }

        return transportModalitysCostKilometers;
    }

    // Metodo de obtencion de modalidades de transporte de tipo horas por kilometraje con filtros
    public ArrayList<Modality> getTransportModalityHoursKilometers(double costKilometersRoutMin, double costKilometersRoutMax, double costHoursMin, double costHoursMax,
                                                                   double costKilometersRoutAdditionalsMin, double costKilometersRoutAdditionalsMax, double costHoursAdditionalsMin, double costHoursAdditionalsMax,
                                                                   double priceMin, double priceMax) { // Metodo para obtener todas las modalidades de transporte tipo horas por kilometraje de la agencia
        ArrayList<Modality> transportModalitysHoursKilometers = new ArrayList<Modality>();

        for (Contract carrierContract : this.contracts.get(Contract.carrierContract)) {
            transportModalitysHoursKilometers.addAll(((CarrierContract) carrierContract).getHoursKilometers(costKilometersRoutMin, costKilometersRoutMax, costHoursMin, costHoursMax,
                    costKilometersRoutAdditionalsMin, costKilometersRoutAdditionalsMax, costHoursAdditionalsMin, costHoursAdditionalsMax, priceMin, priceMax));
        }

        return transportModalitysHoursKilometers;
    }

    // Metodo de obtencion de modalidades de transporte de tipo horas por kilometraje con filtros
    public ArrayList<Modality> getTransportModalityEstablishedRoute(double costGoingMin, double costGoingMax, double costLapMin, double costLapMax, double priceMin, double priceMax) { // Metodo para obtener todas las modalidades de transporte tipo recorridos establecidos de la agencia
        ArrayList<Modality> transportModalitysEstablishedRoute = new ArrayList<Modality>();

        for (Contract carrierContract : this.contracts.get(Contract.carrierContract)) {
            transportModalitysEstablishedRoute.addAll(((CarrierContract) carrierContract).getEstablishedRoute(costGoingMin, costGoingMax, costLapMin, costLapMax, priceMin, priceMax));
        }

        return transportModalitysEstablishedRoute;
    }

    // Fin de Metodos para la obtencion de modalidades

    // Metodos para la obtencion de los Paquetes Turisticos

    // Fin de Metodos para la obtencion de los Paquetes Turisticos

    public ArrayList<TouristPackage> getTouristPackages() {
        return this.touristPackages;
    }

    public ArrayList<TouristPackage> getTouristPackages(String name, LocalDate startDateMin, LocalDate startDateMax, LocalDate terminationDateMin, LocalDate terminationDateMax,
                                                        double priceMin, double priceMax, int cantAviablePaxMin, int cantAviablePaxMax) {
        ArrayList<TouristPackage> touristPackages = this.touristPackages;

        // Se aplican los filtros

        // Filtro Name
        if (name != null)
            touristPackages = FiltersTouristPackage.filterName(touristPackages, name); // Se filtra por name

        // Filtro StartDate
        if (startDateMin != null && startDateMax != null)
            touristPackages = FiltersTouristPackage.filterStartDate(touristPackages, startDateMin, startDateMax); // se filtra por el rango de fechas
        else if (startDateMin != null)
            touristPackages = FiltersTouristPackage.filterStartDate(touristPackages, startDateMin, LocalDate.MAX); // se filtra por la fecha minima
        else if (startDateMax != null)
            touristPackages = FiltersTouristPackage.filterStartDate(touristPackages, LocalDate.MIN, startDateMax); // se filtra por la fecha maxima

        // Filtro TerminationDate
        if (terminationDateMin != null && terminationDateMax != null)
            touristPackages = FiltersTouristPackage.filterTerminationDate(touristPackages, terminationDateMin, terminationDateMax); // se filtra por el rango de fechas
        else if (terminationDateMin != null)
            touristPackages = FiltersTouristPackage.filterTerminationDate(touristPackages, terminationDateMin, LocalDate.MAX); // se filtra por la fecha minima
        else if (terminationDateMax != null)
            touristPackages = FiltersTouristPackage.filterTerminationDate(touristPackages, LocalDate.MIN, terminationDateMax); // se filtra por la fecha maxima

        // Filtro Price
        if (priceMin != AusentFilter.spinnerField && priceMax != AusentFilter.spinnerField)
            touristPackages = FiltersTouristPackage.filterPrice(touristPackages, priceMin, priceMax); // se filtra por el rango de precios


        // Filtro CantAviablePax
        if (cantAviablePaxMin != AusentFilter.spinnerField && cantAviablePaxMax != AusentFilter.spinnerField)
            touristPackages = FiltersTouristPackage.filterCantAviablePax(touristPackages, cantAviablePaxMin, cantAviablePaxMax); // se filtra por el rango de cantAviablePax


        return touristPackages;

    }

    // Fin Metodos para la obtencion de los datos

    // Operaciones
    public int cantServicesProviders() { // Metodo para obtener la cantidad de proveedores de servicio de la agencia
        return this.providers.get(Provider.serviceProvider).size();
    }

    public int cantAccommodationProviders() { // Metodo para obtener la cantidad de proveedores de alojamiento de la agencia
        return this.providers.get(Provider.accommodationProvider).size();
    }

    public int cantTransportProviders() { // Metodo para obtener la cantidad de proveedores de transporte de la agencia
        return this.providers.get(Provider.transportationProvider).size();
    }

    // Fin de Operaciones

}
