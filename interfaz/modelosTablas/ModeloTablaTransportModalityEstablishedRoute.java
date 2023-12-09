package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaTransportModalityEstablishedRoute extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public ModeloTablaTransportModalityEstablishedRoute(){
		String[] columnNames = {"Contract", "Vehicle", "Description rout", "Cost_going","Cost lap", "Price"};   
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
