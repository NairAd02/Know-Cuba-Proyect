package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.CostKilometers;

public class ModeloTablaTransportModalityCostKilometers extends DefaultTableModel implements ModelOperations<CostKilometers> {

	private static final long serialVersionUID = 1L;
	private ArrayList<CostKilometers> elements;


	public ModeloTablaTransportModalityCostKilometers(){
		String[] columnNames = {"Cost kilometers going", "Cost kilometers lap","Cost hours wait", "Price"};
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<CostKilometers>();

	}

	@Override
	public void addElement(CostKilometers costKilometers) {
		Object[] newRow =  null;
		this.elements.add(costKilometers);
		newRow = new Object[]{costKilometers.getCostKilometersGoing(), costKilometers.getCostKilometersLap(), costKilometers.getCostHoursWait(), costKilometers.price()};
		addRow(newRow);
	}


	public CostKilometers deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public CostKilometers getElement (int pos) {
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
