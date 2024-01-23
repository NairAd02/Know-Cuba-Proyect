package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.ModalityDAO;

public abstract class Modality implements DUILogic {
	// Atributos estaticos para el hash
	public static final int accommodationModality = 0;
	public static final int serviceModality = 1;
	public static final int costKilometers = 2;
	public static final int hoursKilometers = 3;
	public static final int establishedRoute = 4;

	public static ArrayList<Integer> getKeys () { // Metodo para obtener una lista con las llaves de Modality
		ArrayList<Integer> keys = new ArrayList<Integer>();
		keys.add(accommodationModality);
		keys.add(serviceModality);
		keys.add(costKilometers);
		keys.add(hoursKilometers);
		keys.add(establishedRoute);

		return keys;
	}
	// Fin Atributos estaticos para el hash

	protected int id;
	protected Contract contract;
	protected String typeOfModality;

	public Modality(int id, Contract contract, String typeOfModality) { // Contructor a nivel de base de datos
		this.id = id;
		this.contract = contract;
		this.typeOfModality = typeOfModality;
	}

	protected void actualizarCampos (Contract contract, String typeOfModality) { // Metodo para actualizar los atributos de la clase
		this.contract = contract;
		this.typeOfModality = typeOfModality;
	}

	public Modality(Contract contract) { // Contructor a nivel de logica // Constructor a nivel de logica (proceso de creacion del objeto)
		this.contract = contract;
	}

	public Modality() { // Constructor a nivel de logica (proceso de creacion del objeto)

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getTypeOfModality() {
		return typeOfModality;
	}

	public void setTypeOfModality(String typeOfModality) {
		this.typeOfModality = typeOfModality;
	}

	public int getContractId() {
		return this.contract.getId();
	}

	public void insertIntoPackageTourist (int idPackageTourist) throws SQLException { // metodo para insertar la modalidad en un paquete turistico dado
		ModalityDAO.getInstancie().insertIntoTouristPackage(this.id, idPackageTourist);
	}

	public void deleteFromPackageTourist (int idPackageTourist) throws SQLException { // metodo para eliminar la modalidad de un paquete turistico dado
		ModalityDAO.getInstancie().deleteFromTouristPackage(this.id, idPackageTourist);
	}

	public  void delete() throws SQLException { // Metodo para eliminar la modalidad en la base de datos
		ModalityDAO.getInstancie().delete(this.id);
	}

	public abstract double price ();

}
