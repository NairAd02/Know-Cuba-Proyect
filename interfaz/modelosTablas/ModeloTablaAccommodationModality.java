package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaAccommodationModality extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	public ModeloTablaAccommodationModality(){
		String[] columnNames = {"Contract","Type of room", "Meal plan", "Cant days accommodation", "Price"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
 
	}

	public void addAccommodationModality(){
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
