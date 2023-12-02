package logica;

public abstract class Modality {
	protected int id;
	protected Contract contract;
	public Modality(int id, Contract contract) {
		super();
		this.id = id;
		this.contract = contract;
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

}
