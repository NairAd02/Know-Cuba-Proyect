package logica;

import java.time.LocalDate;

public class ServiceContract extends Contract{

	public ServiceContract(LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description, boolean state) {
		super(contract_start_date, contract_termination_date,
				contract_reconciliation_date, description, state);
		// TODO Auto-generated constructor stub
	}

}
