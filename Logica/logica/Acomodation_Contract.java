package logica;

import java.time.LocalDate;

public class Acomodation_Contract extends Contract{
	private int surcharge;

	public Acomodation_Contract(int id, LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description,
			boolean state, int surcharge) {
		super(id, contract_start_date, contract_termination_date,
				contract_reconciliation_date, description, state);
		this.surcharge = surcharge;
	}

	public int getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(int surcharge) {
		this.surcharge = surcharge;
	}

}
