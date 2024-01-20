package modelosTablas;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import logica.HotelModality;
import logica.MealPlan;


public class ModeloTablaHotelModality extends DefaultTableModel implements ModelOperations<HotelModality> {

	private static final long serialVersionUID = 1L;
	private ArrayList<HotelModality> elements;
	
	public ModeloTablaHotelModality(){
		
		String[] columnNames = {"Name"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<HotelModality>();

	}

	@Override
	public void addElement(HotelModality hotelModality) {
		Object[] newRow =  null;
		this.elements.add(hotelModality);
		newRow = new Object[]{hotelModality.getName()};
		addRow(newRow);

	}
	
	
	@Override
	public HotelModality getElement(int pos) {
		return this.elements.get(pos);
	}

	@Override
	public HotelModality deleteElement(int pos) {
		this.removeRow(pos);
		return this.elements.remove(pos);
	
	}

	public boolean isFindElement (HotelModality hotelModality) { // Metodo para verificar la existencia de un elemento en la tabla
		boolean find = false;

		for (int i = 0; i < this.elements.size() && !find; i++) {
			if (this.elements.get(i).equals(hotelModality))
				find = true;
		}

		return find;
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
