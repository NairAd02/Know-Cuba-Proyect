package logica;

public abstract class Modality {
	// Atributos estaticos para el hash
	public static final int accommodationModality = 0;
	public static final int serviceModality = 1;
	public static final int transportModality = 2;
	public static final int costKilometers = 3;
	public static final int hoursKilometers = 4;
	public static final int establishedRoute = 5;
	// Fin Atributos estaticos para el hash
	
	protected int id;
	protected Contract contract;
	protected String typeOfModality;

	public Modality(int id, Contract contract, String typeOfModality) { // Contructor a nivel de base de datos
		super();
		this.id = id;
		this.contract = contract;
		this.typeOfModality = typeOfModality;
	}
	
	public Modality(Contract contract, String typeOfModality) { // Contructor a nivel de logica
		super();
		this.contract = contract;
		this.typeOfModality = typeOfModality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getTypeOfModality() {
		return typeOfModality;
	}

	public void setTypeOfModality(String typeOfModality) {
		this.typeOfModality = typeOfModality;
	}

	public int getContractId() {
		return this.contract.getId();
	}
}
