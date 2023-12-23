package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.ServiceModalityDAO;

public class ServiceModality extends Modality{
	private Activity activity;
	private LocalDate releasedDate;
	private double price;


	public ServiceModality(int id, Contract contract, String typeOfModality, Activity activity, LocalDate releasedDate,
			double price) { // constructo a nivel de base de datos
		super(id, contract, typeOfModality);
		this.activity = activity;
		this.releasedDate = releasedDate;
		this.price = price;
	}
	
	public ServiceModality(Contract contract, Activity activity, LocalDate releasedDate,
			double price) { // constructo a nivel de logica  
		super(contract);
		this.activity = activity;
		this.releasedDate = releasedDate;
		this.price = price;
		this.typeOfModality = "Service Modality";
	}
	
	public ServiceModality(Activity activity, LocalDate releasedDate,
			double price) { // Constructor a nivel de logica (proceso de creacion del objeto)
		super();
		this.activity = activity;
		this.releasedDate = releasedDate;
		this.price = price;
		this.typeOfModality = "Service Modality";
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public LocalDate getReleasedDate() {
		return this.releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

	public double price() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getActivityId () {
		return this.activity.getId();
	}

	@Override
	public void insert() throws SQLException {
		
		this.id = ServiceModalityDAO.getInstancie().insert(this);
	}

	@Override
	public void update() throws SQLException {
		ServiceModalityDAO.getInstancie().update(this);
		
	}

}
