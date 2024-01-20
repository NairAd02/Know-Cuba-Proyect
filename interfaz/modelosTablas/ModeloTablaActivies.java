package modelosTablas;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Activity;


public class ModeloTablaActivies extends DefaultTableModel implements ModelOperations<Activity> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Activity> elements;

	public ModeloTablaActivies(){
		String[] columnNames = {"Name", "Description"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Activity>();
	}




	@Override
	public void addElement(Activity activity) {
		Object[] newRow =  null;
		this.elements.add(activity);
		newRow = new Object[]{activity.getName(), activity.getDescription()};
		addRow(newRow);

	}



	public Activity deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public Activity getElement (int pos) {
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
