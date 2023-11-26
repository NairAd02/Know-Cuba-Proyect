package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaAccommodationProvider extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public ModeloTablaAccommodationProvider(){
		String[] columnNames = {"Name","Province", "Chain", "Address", "Category", "Meals Plans", "Types of Rooms"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());


	}

	public void addServiceProvider(){
		Object[] newRow =  null;
         
		newRow = new Object[]{}; 
         

		addRow(newRow);

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
