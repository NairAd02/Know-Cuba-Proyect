package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.HoursKilometers;

public class ModeloTablaTransportModalityHoursKilometers extends DefaultTableModel implements ModelOperations<HoursKilometers>{

	private static final long serialVersionUID = 1L;
	private ArrayList<HoursKilometers> elements;


	public ModeloTablaTransportModalityHoursKilometers(){
		String[] columnNames = {"Vehicle", "Cost Kilometers Rout", "Cost Hours", "Cost Kilometers Rout Additionals", "Cost Hours Additionals", "Price"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<HoursKilometers>();
	}

	@Override
	public void addElement(HoursKilometers hoursKilometers) {
		Object[] newRow =  null;
		this.elements.add(hoursKilometers);
		newRow = new Object[]{hoursKilometers.getVehicle(), hoursKilometers.getCostKilometersRout(), hoursKilometers.getCostHours(), hoursKilometers.getCostKilometersRoutAdditionals(), 
				hoursKilometers.getCostHoursAdditionals(), hoursKilometers.price()};
		addRow(newRow);
	}


	public HoursKilometers deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public HoursKilometers getElement (int pos) {
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
