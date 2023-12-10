package modelosTablas;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaUsers extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	
	public ModeloTablaUsers(){
		String[] columnNames = {"User Name", "Password", "Rol", "Start Date Connection", "Last Date Connection", "Connected"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());


	}

	public void addUser(){
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
