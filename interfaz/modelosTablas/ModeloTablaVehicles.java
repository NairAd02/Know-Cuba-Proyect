package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Vehicle;

public class ModeloTablaVehicles extends DefaultTableModel implements ModelOperations<Vehicle>{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Vehicle> elements;


	public ModeloTablaVehicles(){
		String[] columnNames = {"Vehicle-Lock"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Vehicle>();
	}


	
	@Override
	public void addElement(Vehicle vehicle) {
		Object[] newRow =  null;
		this.elements.add(vehicle);
		newRow = new Object[]{vehicle.getLock()};
		addRow(newRow);
	}

	
	public Vehicle deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}
	
	public int cantElements () {
		return this.elements.size();
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
