package logica;

import java.time.LocalDate;
import java.util.LinkedList;

public class ServiceContract extends Contract{

	public ServiceContract(int id, LocalDate startDate,
			LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider,
			LinkedList<Modality> modalitys, boolean state) {
		super(id, startDate, terminationDate, reconciliationDate, description,
				provider, modalitys, state);
	}

}
