package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.ContractDAO;




public abstract class Contract implements DUILogic {

	// Atributos estaticos para el hash
	public static final int serviceContract = 0;
	public static final int accommodationContract = 1;
	public static final int carrierContract = 2;

	public static ArrayList<Integer> getKeys() {
		ArrayList<Integer> keys = new ArrayList<Integer>(3);
		keys.add(serviceContract);
		keys.add(accommodationContract);
		keys.add(carrierContract);

		return keys;
	}
	// fin
	protected int id;
	protected LocalDate startDate;
	protected LocalDate terminationDate;
	protected LocalDate reconciliationDate;
	protected String description;
	protected Provider provider;
	protected ArrayList<Modality> modalitys;
	protected boolean state; // true si esta cerrado, falso si no
	protected String typeOfContract;
	protected double surcharge;


	public Contract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, boolean state, String typeOfContract,
			double surcharge) throws SQLException { // Constructor a nivel de base de datos
		this.id = id;
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.reconciliationDate = reconciliationDate;
		this.description = description;
		this.provider = provider;
		this.cargarModalities();
		this.state = state;
		this.typeOfContract = typeOfContract;
		this.surcharge = surcharge;
	}

	public Contract(LocalDate startDate, LocalDate terminationDate,
			String description, Provider provider, ArrayList<Modality> modalitys,
			double surcharge) { // Constructor a nivel de logica
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.reconciliationDate = null;
		this.description = description;
		this.provider = provider;
		this.modalitys = modalitys;
		this.state = false;
		this.surcharge = surcharge;
	}

	public Contract () { // Constructor temporal
		this.id = -1; // se marca como objeto temporal
		this.modalitys = new ArrayList<Modality>();
	}

	public double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return this.startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}
	public LocalDate getReconciliationDate() {
		return reconciliationDate;
	}
	public void setReconciliationDate(LocalDate reconciliationDate) {
		this.reconciliationDate = reconciliationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public ArrayList<Modality> getModalitys() {
		return modalitys;
	}
	public void setModalitys(ArrayList<Modality> modalitys) {
		this.modalitys = modalitys;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

	public String getTypeOfContract() {
		return typeOfContract;
	}

	public void setTypeOfContract(String typeOfContract) {
		this.typeOfContract = typeOfContract;
	}

	public void clearModalitys () {
		this.modalitys.clear();
	}



	public  void delete() throws SQLException { // Metodo para eliminar el contrato en la base de datos
		ContractDAO.getInstancie().delete(this.id);
	}

	

	public int getProviderId () { // Metodo para obtener el indentificador del provedor
		return this.provider.getId();
	}

	// Metodos de Carga

	public abstract void actualizarDatos () throws SQLException;
	public abstract void cargarModalities () throws SQLException;
	// Fin de Metodos de Carga

	// Metodos para el control de las modalidades

	public void addModality (Modality modality) throws SQLException {
		modality.insert(); // se inserta la modalidad en la base de datos
		this.addModalityLogic(modality); // se inserta la modalidad en la logica del negocio
	}

	public void addModalityLogic (Modality modality) throws SQLException {
		this.modalitys.add(modality); // se inserta la modalidad en la logica del negocio
	}

	public void deleteModality (Modality modality) throws SQLException {
		modality.delete(); // se elimina la modalidad de la base de datos 
		this.deleteModalityLogic(modality); // se elimina la modalidad de la logica del negocio
	}

	public void deleteModalityLogic (Modality modality) throws SQLException {
		this.modalitys.remove(modality); // se elimina la modalidad de la logica del negocio
	}

	public void insertModalitysIntoDataBase () throws SQLException { // Metodo para cuando ocurra la inserccion del contrato se actualicen las modalidades del mismo

		for (Modality modality : this.modalitys) {
			modality.setContract(this); // se le asigna a la modalidad la referencia de este contrato
			modality.insert();
		}
	}

	// Fin Metodos para el control de las modalidades
	
	// Operaciones
	public boolean verificarProvedor (Provider provider) {
		return this.provider.equals(provider);
	}
	// Fin de Operaciones
	
	
}
