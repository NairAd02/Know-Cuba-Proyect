package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaActivies extends DefaultTableModel {


	private static final long serialVersionUID = 1L;
	
	public ModeloTablaActivies(){
		String[] columnNames = {"Description"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());


	}

	public void addActivity(){
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
