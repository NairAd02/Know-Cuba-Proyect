package modelosTablas;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Controller;
import logica.ServiceProvider;




public class ModeloTablaServiceProvider extends DefaultTableModel implements ModelOperations<ServiceProvider> {

	private static final long serialVersionUID = 1L;
	private ArrayList<ServiceProvider> elements;

	public ModeloTablaServiceProvider(){
		String[] columnNames = {"Name","Province", "Activities"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<ServiceProvider>();


	}

	@Override
	public void addElement(ServiceProvider serviceProvider) {
		Object[] newRow =  null;
		this.elements.add(serviceProvider);
		newRow = new Object[]{serviceProvider.getName(), serviceProvider.getProvince(), "Show Activities"};

		addRow(newRow);

	}

	public void deleteElements (int[] rows) throws SQLException {

		for (int i = 0; i < rows.length; i++) {	
			Controller.getInstancie().getTouristAgency().deleteProvider(this.elements.get(rows[i] - i));// se eliminan los Provedores de servicios seleccionados de la base de datos
			this.deleteElement(rows[i] - i); // se eliminan de la tabla y de la logica del negocio
		}
	}

	public void deleteElement (int i) {
		this.elements.remove(i);
		this.removeRow(i);
	}

	public ServiceProvider getElement (int pos) {
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
