package modelosTablas;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.TransportationProvider;
import logica.Vehicle;

public class ModeloTablaVehicles extends DefaultTableModel implements ModelOperations<Vehicle>{
	
	private static final long serialVersionUID = 1L;
	private TransportationProvider transportationProvider;
	private ArrayList<Vehicle> elements;
	private ArrayList<Vehicle> temporalVehicles;

	public ModeloTablaVehicles(TransportationProvider transportationProvider){
		this.transportationProvider = transportationProvider;
		String[] columnNames = {"Vehicle-Lock"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Vehicle>();
		if (this.transportationProvider == null)
			this.temporalVehicles = new ArrayList<Vehicle>();
	}


	
	public TransportationProvider getTransportationProvider() {
		return transportationProvider;
	}



	public void setTransportationProvider(TransportationProvider transportationProvider) {
		this.transportationProvider = transportationProvider;
	}



	public ArrayList<Vehicle> getElements() {
		return elements;
	}



	public void setElements(ArrayList<Vehicle> elements) {
		this.elements = elements;
	}



	public ArrayList<Vehicle> getTemporalVehicles() {
		return temporalVehicles;
	}



	public void setTemporalVehicles(ArrayList<Vehicle> temporalVehicles) {
		this.temporalVehicles = temporalVehicles;
	}



	@Override
	public void addElement(Vehicle vehicle) {
		Object[] newRow =  null;
		this.elements.add(vehicle);
		newRow = new Object[]{vehicle.getLock()};
		addRow(newRow);
	}

	public void addElementTemporal (Vehicle vehicle) {
		this.temporalVehicles.add(vehicle);
	}

	public void deleteElements (int[] rows) throws SQLException {

		for (int i = 0; i < rows.length; i++) {
			if (this.transportationProvider != null)
				this.transportationProvider.deleteVehicle(this.elements.get(rows[i] - i));// se eliminan los vehiculos seleccionados de la base de datos y de la logica del negocio
			else
				this.temporalVehicles.remove(rows[i] - i); // se elimian de la lista temporal

			this.deleteElement(rows[i] - i); // se eliminan de la tabla y de la logica del negocio

		}
	}

	public void deleteElement (int i) {
		this.elements.remove(i);
		this.removeRow(i);
	}

	public Vehicle getElement (int pos) {
		return this.elements.get(pos);
	}

	public boolean isCellEditable(int row, int column){
		boolean x=false;
		if(column==2)
			x=true;
		else
			x=false;

		return x;


	}
}
