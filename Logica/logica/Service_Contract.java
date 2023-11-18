package logica;

import java.time.LocalDate;

public class Service_Contract extends Contract{

	public Service_Contract(LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description, boolean state) {
		super(contract_start_date, contract_termination_date,
				contract_reconciliation_date, description, state);
		// TODO Auto-generated constructor stub
	}

}
