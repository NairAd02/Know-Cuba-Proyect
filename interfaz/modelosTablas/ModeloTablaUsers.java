package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.User;

public class ModeloTablaUsers extends DefaultTableModel implements ModelOperations<User> {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> elements;

	public ModeloTablaUsers(){
		this.elements = new ArrayList<User>();
		String[] columnNames = {"User Name", "Password", "Rol", "Start Date Connection", "Last Date Connection", "State Connection"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());


	}

	@Override
	public void addElement(User user) {
		Object[] newRow =  null;
		this.elements.add(user);
		newRow = new Object[]{user.getUserName(), user.getPassword(), user.getNameRol(), 
				(user.getStartDateConnection() != null) ? user.getStartDateConnection() : "----", (user.getLastDateConnection() != null) ? user.getLastDateConnection() : "---", (user.isConnected()) ? "Connected" : "Disconected" };

		addRow(newRow);

	}



	public User deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public User getElement (int pos) {
		return this.elements.get(pos);
	}

	/*public boolean isCellEditable(int row, int column){
		boolean x=false;
		if(column==2)
			x=true;
		else
			x=false;

		return x;


	}*/



}
