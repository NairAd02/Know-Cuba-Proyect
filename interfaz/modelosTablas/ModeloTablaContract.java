package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaContract extends DefaultTableModel {

	
	private static final long serialVersionUID = 1L;
	
	public ModeloTablaContract(){
		String[] columnNames = {"ID","Type","Start Date","Termination Date", "Reconciliation Date", "State", "Given Information"};   
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
