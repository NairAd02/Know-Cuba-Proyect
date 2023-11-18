package logica;

import java.time.LocalDate;

public class Carrier_Contract extends Contract{
	
	private Transportation_Provider provider;

	public Carrier_Contract(LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description, boolean state, Transportation_Provider provider) {
		super(contract_start_date, contract_termination_date,
				contract_reconciliation_date, description, state);
		this.setProvider(provider);
	}

	public Transportation_Provider getProvider() {
		return provider;
	}

	public void setProvider(Transportation_Provider provider) {
		this.provider = provider;
	}

}
