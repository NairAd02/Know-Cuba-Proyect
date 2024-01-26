package modelosTablas;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import logica.AccommodationModality;

public class ModeloTablaAccommodationModalityWithHotel extends DefaultTableModel implements ModelOperations<AccommodationModality> {
	private static final long serialVersionUID = 1L;
	private ArrayList<AccommodationModality> elements;

	public ModeloTablaAccommodationModalityWithHotel(){
		String[] columnNames = {"Hotel", "Type of room", "Meal plan", "Hotel Modality" , "Days accommodation", "Price"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<AccommodationModality>();

	}
	
	@Override
	public void addElement(AccommodationModality accommodationModality) {
		Object[] newRow =  null;
		this.elements.add(accommodationModality);
		newRow = new Object[]{accommodationModality.getContract().getProvider(), accommodationModality.getTypeOfRoomSelect().getName(), accommodationModality.getMealPlanSelect().getName(), accommodationModality.getHotelModality().getName(), 
				accommodationModality.getCantDaysAccommodation(), accommodationModality.price()};
		addRow(newRow);
	}



	public AccommodationModality deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public AccommodationModality getElement (int pos) {
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
