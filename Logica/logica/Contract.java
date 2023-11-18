package logica;

import java.time.LocalDate;

public abstract class Contract {
	protected int id;
	protected LocalDate contract_start_date;
	protected LocalDate contract_termination_date;
	protected LocalDate contract_reconciliation_date;
	protected String description;
	protected boolean state; // true si esta cerrado, falso si no
	

	public Contract(int id, LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description,
			boolean state) {
		super();
		this.id = id;
		this.contract_start_date = contract_start_date;
		this.contract_termination_date = contract_termination_date;
		this.contract_reconciliation_date = contract_reconciliation_date;
		this.description = description;
		this.state = state;
	}

	public Contract(LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description,
			boolean state) {
		super();
		this.contract_start_date = contract_start_date;
		this.contract_termination_date = contract_termination_date;
		this.contract_reconciliation_date = contract_reconciliation_date;
		this.description = description;
		this.state = state;
	}

	public LocalDate getContract_start_date() {
		return contract_start_date;
	}


	public void setContract_start_date(LocalDate contract_start_date) {
		this.contract_start_date = contract_start_date;
	}


	public LocalDate getContract_termination_date() {
		return contract_termination_date;
	}


	public void setContract_termination_date(LocalDate contract_termination_date) {
		this.contract_termination_date = contract_termination_date;
	}


	public LocalDate getContract_reconciliation_date() {
		return contract_reconciliation_date;
	}


	public void setContract_reconciliation_date(
			LocalDate contract_reconciliation_date) {
		this.contract_reconciliation_date = contract_reconciliation_date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isState() {
		return state;
	}


	public void setState(boolean state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
