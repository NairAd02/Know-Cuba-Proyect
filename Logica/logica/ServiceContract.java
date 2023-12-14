package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.ServiceContractDAO;



public class ServiceContract extends Contract{

	

	public ServiceContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
			String description, Provider provider, ArrayList<ServiceModality> modalitys, boolean state, String typeOfContract,
			double surcharge) {
		super(id, startDate, terminationDate, reconciliationDate, description, provider, new ArrayList<Modality>(modalitys), state, typeOfContract,
				surcharge);
		// TODO Auto-generated constructor stub
	}

	public ServiceContract(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	// Metodos para el control de las modalidades

	
	public void updateServiceModality (ServiceModality serviceModality, Activity activity, LocalDate releaseDate, double price) throws SQLException {
		// se actualiza la informacion de la modalidad a nivel de logica
		serviceModality.setActivity(activity);
		serviceModality.setReleasedDate(releaseDate);
		serviceModality.setPrice(price);
		serviceModality.update();; // se actualiza la informacion de la modalidad en la base de datos
	}
	// Fin Metodos para el control de las modalidades

	@Override
	public void insert() throws SQLException {
		
		this.id = ServiceContractDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		ServiceContractDAO.getInstancie().update(this);
		
	}




}
