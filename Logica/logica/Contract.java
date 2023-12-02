package logica;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Contract {
	protected int id;
	protected LocalDate StartDate;
	protected LocalDate terminationDate;
	protected LocalDate reconciliationDate;
	protected String description;
	protected Provider provider;
	protected LinkedList<Modality> modalitys;
	protected boolean state; // true si esta cerrado, falso si no
	public Contract(int id, LocalDate startDate, LocalDate terminationDate,
			LocalDate reconciliationDate, String description,
			Provider provider, LinkedList<Modality> modalitys, boolean state) {
		super();
		this.id = id;
		StartDate = startDate;
		this.terminationDate = terminationDate;
		this.reconciliationDate = reconciliationDate;
		this.description = description;
		this.provider = provider;
		this.modalitys = modalitys;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
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
	public LinkedList<Modality> getModalitys() {
		return modalitys;
	}
	public void setModalitys(LinkedList<Modality> modalitys) {
		this.modalitys = modalitys;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
