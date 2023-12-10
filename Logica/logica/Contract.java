package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.ModalityDAO;

public abstract class Contract {

	// Atributos estaticos para el hash
	public static final int serviceContract = 0;
	public static final int accommodationContract = 1;
	public static final int carrierContract = 2;
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

	public Contract(int id, LocalDate startDate, LocalDate terminationDate,
			LocalDate reconciliationDate, String description,
			Provider provider, ArrayList<Modality> modalitys, boolean state, String typeOfContract) { // Constructor a nivel de logica
		super();
		this.id = id;
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.reconciliationDate = reconciliationDate;
		this.description = description;
		this.typeOfContract = typeOfContract;
		this.provider = provider;
		this.modalitys = modalitys;
		this.state = state;
	}


	public Contract (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		this.id = id;
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

	public int getProviderId () { // Metodo para obtener el indentificador del provedor
		return this.provider.getId();
	}

	// Metodos para el control de las modalidades

	public void addModality (Modality modality) throws SQLException {
		this.modalitys.add(modality); // se inserta la modalidad en la logica del negocio
	}

	public void deleteModality (Modality modality) throws SQLException {
		ModalityDAO.getInstancie().delete(modality.getId()); // se elimina la modalidad de la base de datos 
		this.modalitys.remove(modality); // se elimina la modalidad de la logica del negocio
	}
	
	public abstract void updateModality (Modality modality) throws SQLException;
	
	
	// Fin Metodos para el control de las modalidades
}
