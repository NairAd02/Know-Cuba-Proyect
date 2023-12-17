package modelosTablas;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Controller;
import logica.Hotel;


public class ModeloTablaAccommodationProvider extends DefaultTableModel implements ModelOperations<Hotel> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Hotel> elements;

	public ModeloTablaAccommodationProvider(){
		String[] columnNames = {"Name","Province", "Chain", "Address", "Category", "Meals Plans", "Types of Rooms"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Hotel>();

	}


	@Override
	public void addElement(Hotel hotel) {
		Object[] newRow =  null;
		this.elements.add(hotel);
		newRow = new Object[]{hotel.getName(), hotel.getProvince(), hotel.getHotelChain(), hotel.getAddress(), hotel.getHotelCategory(), "Show", "Show"};

		addRow(newRow);

	}

	public void deleteElements (int[] rows) throws SQLException {

		for (int i = 0; i < rows.length; i++) {	
			Controller.getInstancie().getTouristAgency().deleteProvider(this.elements.get(rows[i] - i));// se eliminan los Provedores de servicios seleccionados de la base de datos
			this.deleteElement(rows[i] - i); // se eliminan de la tabla y de la logica del negocio
		}
	}

	public Hotel deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public Hotel getElement (int pos) {
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
