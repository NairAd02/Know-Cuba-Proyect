package logica;

public abstract class Modality {
	protected int id;
	protected Contract contract;
	protected String typeOfModality;

	public Modality(int id, Contract contract, String typeOfModality) {
		super();
		this.id = id;
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
