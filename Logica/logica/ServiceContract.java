package logica;

import java.time.LocalDate;
import java.util.ArrayList;


public class ServiceContract extends Contract{

	public ServiceContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, ArrayList<ServiceModality> modalitys, boolean state,
			String typeOfContract) {
		super(id, startDate, terminationDate, reconciliationDate, description, provider, new ArrayList<Modality>(modalitys), state, typeOfContract);
		// TODO Auto-generated constructor stub
	}

	public ServiceContract(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


}
