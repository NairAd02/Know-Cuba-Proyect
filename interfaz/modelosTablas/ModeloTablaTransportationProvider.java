package modelosTablas;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Controller;
import logica.TransportationProvider;

public class ModeloTablaTransportationProvider extends DefaultTableModel implements ModelOperations<TransportationProvider> {

	private static final long serialVersionUID = 1L;
	private ArrayList<TransportationProvider> elements;

	public ModeloTablaTransportationProvider(){
		String[] columnNames = {"Name","Province", "Vehicles"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<TransportationProvider>();
	}

	@Override
	public void addElement(TransportationProvider transportationProvider) {
		Object[] newRow =  null;
		this.elements.add(transportationProvider);
		newRow = new Object[]{transportationProvider.getName(), transportationProvider.getProvince(), "Show Vehicles"};

		addRow(newRow);

	}

	public void deleteElements (int[] rows) throws SQLException {

		for (int i = 0; i < rows.length; i++) {	
			Controller.getInstancie().getTouristAgency().deleteProvider(this.elements.get(rows[i] - i));// se eliminan los Provedores de servicios seleccionados de la base de datos
			this.deleteElement(rows[i] - i); // se eliminan de la tabla y de la logica del negocio
		}
	}

	public TransportationProvider deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public TransportationProvider getElement (int pos) {
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
