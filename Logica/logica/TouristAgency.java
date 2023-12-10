package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import dao.AccommodationContractDAO;
import dao.CarrierContractDAO;
import dao.ContractDAO;
import dao.HotelDAO;
import dao.ProviderDAO;
import dao.ServiceContractDAO;
import dao.ServiceProviderDAO;
import dao.TouristPackageDAO;
import dao.TransportationProviderDAO;


public class TouristAgency {
	private ArrayList<TouristPackage> touristPackages; // los paquetes turisticos disponible en la empresa
	private HashMap<Integer, ArrayList<Provider>> providers; // los proveedores de la agencia hasheados por su tipo
	private HashMap<Integer, ArrayList<Contract>> contracts; // los contratos de la agencia hasheados por su tipo


	public TouristAgency () throws SQLException { // Singlenton
		this.cargarPaquetesTuristicos();
		this.providers = new HashMap<Integer, ArrayList<Provider>>();
		this.cargarProvedores(); // se cargan los provedores desde la base de datos
		this.contracts = new HashMap<Integer, ArrayList<Contract>>();
		this.cargarContratos(); // se cargan los contractos desde la base de datos
	}

	// Metodos de Carga de Informacion 
	// Detalle a tener en cuenta es crear una tabla que defina los tipos de proveedores existentes. De esta forma se puede hacer genericidad la construccion del hashMap
	public void cargarProvedores () throws SQLException {
		this.providers.put(Provider.serviceProvider, new ArrayList<Provider>(ServiceProviderDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de serivicio desde la base de datos
		this.providers.put(Provider.transportationProvider, new ArrayList<Provider>(TransportationProviderDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de transporte desde la base de datos
		this.providers.put(Provider.accommodationProvider, new ArrayList<Provider>(HotelDAO.getInstancie().selectAll())); // se añaden al sistema los provedores de alojamiento desde la base de datos
	}

	public void cargarContratos () throws SQLException {
		this.contracts.put(Contract.serviceContract, new ArrayList<Contract>(ServiceContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de serivicio desde la base de datos
		this.contracts.put(Contract.carrierContract, new ArrayList<Contract>(CarrierContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de transportista desde la base de datos
		this.contracts.put(Contract.accommodationContract, new ArrayList<Contract>(AccommodationContractDAO.getInstancie().selectAll())); // se añaden al sistema los contratos de alojamiento desde la base de datos
	}

	public void cargarPaquetesTuristicos () throws SQLException {
		this.touristPackages = (ArrayList<TouristPackage>) TouristPackageDAO.getInstancie().selectAll(); // se cargan todos los paquetes turisticos de la base de datos;
	}
	

	// Fin Metodos de Carga de Informacion

	// Metodos para la inserccion de datos

	public void addContract (Contract contract) throws SQLException {
		int idInsertado = -1;

		if (contract instanceof ServiceContract) {
			idInsertado = ServiceContractDAO.getInstancie().insert((ServiceContract) contract); // se inserta el contrato en la base de datos
			this.contracts.get(Contract.serviceContract).add(contract); // se inserta el contrato en la logica del negocio
		}
		else if (contract instanceof CarrierContract) {
			idInsertado = CarrierContractDAO.getInstancie().insert((CarrierContract) contract); // se inserta el contrato en la base de datos
			this.contracts.get(Contract.carrierContract).add(contract); // se inserta el contrato en la logica del negocio
		}
		else if (contract instanceof AccommodationContract) {
			idInsertado = AccommodationContractDAO.getInstancie().insert((AccommodationContract) contract); // se inserta el contrato en la base de datos
			this.contracts.get(Contract.accommodationContract).add(contract); // se inserta el contrato en la logica del negocio
		}

		contract.setId(idInsertado); // se asigna el id autoincrementable de la base de datos

	}

	public void addProvider (Provider provider) throws SQLException {
		int idInsertado = -1;

		if (provider instanceof ServiceProvider) {
			idInsertado = ServiceProviderDAO.getInstancie().insert((ServiceProvider) provider); // se inserta el provedor de servicios en la base de datos
			this.providers.get(Provider.serviceProvider).add(provider); // se inserta el provedor de servicios en la logica del negocio
		}
		else if (provider instanceof TransportationProvider) {
			idInsertado = TransportationProviderDAO.getInstancie().insert((TransportationProvider) provider); // se inserta el provedor de transporte en la base de datos
			this.providers.get(Provider.transportationProvider).add(provider); // se inserta el provedor de transporte en la logica del negocio
		}
		else if (provider instanceof Hotel) {
			idInsertado = HotelDAO.getInstancie().insert((Hotel) provider); // se inserta el provedor de alojamiento en la base de datos
			this.providers.get(Provider.accommodationProvider).add(provider); // se inserta el provedor de alojamiento en la logica del negocio
		}

		provider.setId(idInsertado); // se asigna el id autoincrementable de la base de datos
	}

	public void addTouristPackage (TouristPackage touristPackage) throws SQLException {
		int idInsertado = TouristPackageDAO.getInstancie().insert(touristPackage); // se inserta el paqueta en la base de datos
		this.touristPackages.add(touristPackage); // se inserta el paquete en la logica del negocio
		touristPackage.setId(idInsertado); // se asigna el id autoincrementable de la base de datos
	}

	// Fin de los Metodos para la inserccion de datos

	// Metodos de eliminacion de los datos

	public void deleteContract (Contract contract) throws SQLException {
		ContractDAO.getInstancie().delete(contract.getId()); // se elimina al contrato de la base de datos

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
		ProviderDAO.getInstancie().delete(provider.getId()); // se elimina al provedor de la base de datos

		if (provider instanceof ServiceProvider)
			this.providers.get(Provider.serviceProvider).remove(provider); // se elimina al provedor de la logica del negocio
		else if (provider instanceof TransportationProvider)
			this.providers.get(Provider.transportationProvider).remove(provider); // se elimina al provedor de la logica del negocio
		else if (provider instanceof Hotel)
			this.providers.get(Provider.accommodationProvider).remove(provider); // se elimina al provedor de la logica del negocio

	}

	public void deleteTouristPackage (TouristPackage touristPackage) throws SQLException {
		TouristPackageDAO.getInstancie().delete(touristPackage.getId()); // se elimina el paquete turistico de la base de datos
		this.touristPackages.remove(touristPackage); // se elimina el paquete turistico de la logica del negocio

	}

	// Fin Metodos de eliminacion de los datos

	// Metodos para la actualizacion de los datos

	public void updateContract (Contract contract) throws SQLException {
		// Se actualiza en la base de de datos la infomracion de cada contrato en dependencia de su tipo
		if (contract instanceof ServiceContract)
			ServiceContractDAO.getInstancie().update((ServiceContract) contract);
		else if (contract instanceof CarrierContract)
			CarrierContractDAO.getInstancie().update((CarrierContract) contract);
		else if (contract instanceof AccommodationContract)
			AccommodationContractDAO.getInstancie().update((AccommodationContract) contract);

	}


	public void updateProvider (Provider provider) throws SQLException {
		// Se actualiza en la base de datos la informacion de cada contrato en dependencia de su tipo
		if (provider instanceof ServiceProvider)
			ServiceProviderDAO.getInstancie().update((ServiceProvider) provider);
		else if (provider instanceof TransportationProvider)
			TransportationProviderDAO.getInstancie().update((TransportationProvider) provider);
		else if (provider instanceof Hotel)
			HotelDAO.getInstancie().update((Hotel) provider);

	}

	public void updateTouristPackage (TouristPackage touristPackage) throws SQLException {
		TouristPackageDAO.getInstancie().update(touristPackage); // se actualiza el paquete turistico en la base de datos
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


	
	// Fin Metodos para la obtencion de los datos


	// Metodos de busqueda y obtencion

}
