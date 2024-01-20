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


	public ServiceProvider deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
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
