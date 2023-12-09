package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaTransportModalityHoursKilometers extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	
	

	public ModeloTablaTransportModalityHoursKilometers(){
		String[] columnNames = {"Contract","Vehicle", "Cost kilometers rout", "Cost hours","Cost kilometers rout additionals", "Cost hours additionals", "Price"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
 
	}

	public void addTransportModalityCostKilometers(){
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
