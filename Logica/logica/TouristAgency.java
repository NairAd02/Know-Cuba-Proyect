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


public class TouristAgency {
	private ArrayList<TouristPackage> touristPackages; // los paquetes turisticos disponible en la empresa
	private HashMap<Integer, ArrayList<Provider>> providers; // los proveedores de la agencia hasheados por su tipo
	private HashMap<Integer, ArrayList<Contract>> contracts; // los contratos de la agencia hasheados por su tipo


	public TouristAgency () throws SQLException { // Singlenton
		this.cargarPaquetesTuristicos(); // se cargan los paquetes turisticos de la base de datos
		this.cargarProvedores(); // se cargan los provedores desde la base de datos
		this.cargarContratos(); // se cargan los contractos desde la base de datos
	}

	// Metodos de Carga de Informacion 
	// Detalle a tener en cuenta es crear una tabla que defina los tipos de proveedores existentes. De esta forma se puede hacer genericidad la construccion del hashMap
	public void cargarProvedores () throws SQLException {
		this.providers = new HashMap<Integer, ArrayList<Provider>>();
		this.providers.put(Provider.serviceProvider, new ArrayList<Provider>(ServiceProviderDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de serivicio desde la base de datos
		this.providers.put(Provider.transportationProvider, new ArrayList<Provider>(TransportationProviderDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de transporte desde la base de datos
		this.providers.put(Provider.accommodationProvider, new ArrayList<Provider>(HotelDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de alojamiento desde la base de datos
	}

	public void cargarContratos () throws SQLException {
		this.contracts = new HashMap<Integer, ArrayList<Contract>>();
		this.contracts.put(Contract.serviceContract, new ArrayList<Contract>(ServiceContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de serivicio desde la base de datos
		this.contracts.put(Contract.carrierContract, new ArrayList<Contract>(CarrierContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de transportista desde la base de datos
		this.contracts.put(Contract.accommodationContract, new ArrayList<Contract>(AccommodationContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de alojamiento desde la base de datos
	}

	public void cargarPaquetesTuristicos () throws SQLException {
		this.touristPackages = new ArrayList<TouristPackage>();
		this.touristPackages = (ArrayList<TouristPackage>) TouristPackageDAO.getInstancie().selectAll(); // se cargan todos los paquetes turisticos de la base de datos;
	}


	// Fin Metodos de Carga de Informacion

	// Metodos para la inserccion de datos

	public void addContract (Contract contract) throws SQLException {
		contract.insert(); // se inserta el contrato en la base de datos

		if (contract instanceof ServiceContract) {		
			this.contracts.get(Contract.serviceContract).add(contract); // se inserta el contrato en la logica del negocio
		}
		else if (contract instanceof CarrierContract) {	
			this.contracts.get(Contract.carrierContract).add(contract); // se inserta el contrato en la logica del negocio
		}
		else if (contract instanceof AccommodationContract) {	
			this.contracts.get(Contract.accommodationContract).add(contract); // se inserta el contrato en la logica del negocio
		}
	}

	public void addProvider (Provider provider) throws SQLException {
		provider.insert() ; // se inserta el provedor de alojamiento en la base de datos

		if (provider instanceof ServiceProvider) {
			this.providers.get(Provider.serviceProvider).add(provider); // se inserta el provedor de servicios en la logica del negocio
		}
		else if (provider instanceof TransportationProvider) {
			this.providers.get(Provider.transportationProvider).add(provider); // se inserta el provedor de transporte en la logica del negocio
		}
		else if (provider instanceof Hotel) {
			this.providers.get(Provider.accommodationProvider).add(provider); // se inserta el provedor de alojamiento en la logica del negocio
		}
	}

	public void addTouristPackage (TouristPackage touristPackage) throws SQLException {
		touristPackage.insert(); // se inserta el paquete en la base de datos
		this.touristPackages.add(touristPackage); // se inserta el paquete en la logica del negocio	
	}

	// Fin de los Metodos para la inserccion de datos

	// Metodos de eliminacion de los datos

	public void deleteContract (Contract contract) throws SQLException {
		contract.delete(); // se elimina al contrato de la base de datos

		if (contract instanceof ServiceContract) {
			this.contracts.get(Contract.serviceContract).remove(contract); // se elimina el contrato de la logica del negocio
		}
		else if (contract instanceof CarrierContract) {
			this.contracts.get(Contract.carrierContract).remove(contract); // se elimina el contrato de la logica del negocio
		}
		else if (contract instanceof AccommodationContract) {
			this.contracts.get(Contract.accommodationContract).remove(contract); // se elimina el contrato de la logica del negocio
		}
	}

	public void deleteProvider (Provider provider) throws SQLException {
		provider.delete(); // se elimina al provedor de la base de datos
		if (provider instanceof ServiceProvider)
			this.providers.get(Provider.serviceProvider).remove(provider); // se elimina al provedor de la logica del negocio
		else if (provider instanceof TransportationProvider)
			this.providers.get(Provider.transportationProvider).remove(provider); // se elimina al provedor de la logica del negocio
		else if (provider instanceof Hotel)
			this.providers.get(Provider.accommodationProvider).remove(provider); // se elimina al provedor de la logica del negocio
	}

	public void deleteTouristPackage (TouristPackage touristPackage) throws SQLException {
		touristPackage.delete(); // se elimina el paquete turistico de la base de datos
		this.touristPackages.remove(touristPackage); // se elimina el paquete turistico de la logica del negocio
	}

	// Fin Metodos de eliminacion de los datos

	// Metodos para la actualizacion de los datos

	public void updateContract (Contract contract, LocalDate contractStrartDate, LocalDate contractTerminationDate, String contractDescription, double surcharge) throws SQLException { // TEMPORAL
		// se actualiza la informacion del contrato a nivel de logica
		contract.setStartDate(contractStrartDate);
		contract.setTerminationDate(contractTerminationDate);
		contract.setDescription(contractDescription);
		contract.setSurcharge(surcharge);
		contract.update(); // se actualiza la informacion del contrato en la base de datos
	}


	public void updateHotel (Hotel hotel, String name, String hotelChain, String province, String address, int hotelCategory) throws SQLException {
		// se actualiza la informacion del provedor a nivel de logica
		hotel.setName(name);
		hotel.setHotelChain(hotelChain);
		hotel.setProvince(province);
		hotel.setAddress(address);
		hotel.setHotelCategory(hotelCategory);
		hotel.update(); // se actualiza la informacion del provedor a nivel de base de datos
	}

	public void updateServiceProvider (ServiceProvider serviceProvider, String name, String province) throws SQLException {
		// se actualiza la informacion del provedor a nivel de logica
		serviceProvider.setName(name);
		serviceProvider.setProvince(province);
		serviceProvider.update(); // se actualiza la informacion del provedor a nivel de base de datos
	}

	public void updateTransportationProvider (TransportationProvider transportationProviver, String name, String province) throws SQLException {
		// se actualiza la informacion del provedor a nivel de logica
		transportationProviver.setName(name);
		transportationProviver.setProvince(province);
		transportationProviver.update(); // se actualiza la informacion del provedor a nivel de base de datos
	}

	public void updateTouristPackage (TouristPackage touristPackage, String name) throws SQLException {
		// se actualiza la informacion del paquete turistico a nivel de logica
		touristPackage.setName(name);
		touristPackage.update(); // se actualiza el paquete turistico en la base de datos
	}

	// Fin Metodos para la actualizacion de los datos

	// Metodos para la obtencion de los datos

	public ArrayList<Contract> getContracts () { // metodo para obtener todos los contrato
		ArrayList<Contract> contract = new ArrayList<Contract>();
		contract.addAll(this.contracts.get(Contract.serviceContract)); // se añaden todos los contratos de servicios
		contract.addAll(this.contracts.get(Contract.carrierContract)); // se añaden todos los contratos de transporte
		contract.addAll(this.contracts.get(Contract.accommodationContract)); // se añaden todos los contratos de alojamiento

		return contract;
	}

	public ArrayList<Contract> getContracts (int typeOfContract) { // metodo para obtener los contratos de un tipo
		return this.contracts.get(typeOfContract);
	}

	public ArrayList<Provider> getProviders () { // metodo para obtener todos los contrato
		ArrayList<Provider> providers = new ArrayList<Provider>();
		providers.addAll(this.providers.get(Provider.serviceProvider)); // se añaden todos los provedores de servicios
		providers.addAll(this.providers.get(Provider.transportationProvider)); // se añaden todos los provedores de transporte
		providers.addAll(this.providers.get(Provider.accommodationProvider)); // se añaden todos los provedores de alojamiento

		return providers;
	}

	public ArrayList<Provider> getProviders (int typeOfProvider) { // metodo para obtener los provedores de un tipo
		return this.providers.get(typeOfProvider);
	}

	public ArrayList<Provider> getProviders (String name, int typeOfProvider) { // metodo para obtener los provedores de un tipo
		ArrayList<Provider> provideres = new ArrayList<Provider>();

		for (Provider provider : this.providers.get(typeOfProvider)) {
			if (provider.isSameName(name))
				provideres.add(provider);
		}
		return provideres;
	}

	// Fin Metodos para la obtencion de los datos


	// Metodos de busqueda y obtencion

}
