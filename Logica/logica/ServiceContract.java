package logica;

import java.sql.SQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import dao.ServiceModalityDAO;


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
	
	// Metodos para el control de las modalidades
	
	public void addModality (Modality modality) throws SQLException {
		// Verificar instaceof
		int idInsertado = ServiceModalityDAO.getInstancie().insert((ServiceModality) modality); // se inserta la modalidad en la base de datos
		super.addModality(modality);
		modality.setId(idInsertado); // se asigna el id autoincrementable
	}
	
	@Override
	public void updateModality(Modality modality) throws SQLException {
		ServiceModalityDAO.getInstancie().update((ServiceModality) modality); // se actualiza la informacion de la modalidad en la base de datos
	}
	// Fin Metodos para el control de las modalidades

	


}
