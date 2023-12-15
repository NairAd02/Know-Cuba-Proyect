package modelosTablas;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Controller;
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

	public void deleteElements (int[] rows) throws SQLException {

		for (int i = 0; i < rows.length; i++) {	
				Controller.getInstancie().deleteUser(this.elements.get(rows[i] - i)); // se eliminan los usuarios seleccionados de la base de datos
				this.deleteElement(rows[i] - i); // se eliminan de la tabla y de la logica del negocio
		
		}
	}

	public void deleteElement (int i) {
		this.elements.remove(i);
		this.removeRow(i);
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
