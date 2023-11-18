package logica;

import java.time.LocalDate;

public abstract class CarrierContract extends Contract{
	
	private TransportationProvider provider;

	public CarrierContract(LocalDate contract_start_date,
			LocalDate contract_termination_date,
			LocalDate contract_reconciliation_date, String description, boolean state, TransportationProvider provider) {
		super(contract_start_date, contract_termination_date,
				contract_reconciliation_date, description, state);
		this.setProvider(provider);
	}

	public TransportationProvider getProvider() {
		return provider;
	}

	public void setProvider(TransportationProvider provider) {
		this.provider = provider;
	}

}
