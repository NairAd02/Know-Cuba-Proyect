package modelosTablas;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Activity;

import logica.ServiceProvider;

public class ModeloTablaActivies extends DefaultTableModel implements ModelOperations<Activity> {


	private static final long serialVersionUID = 1L;
	private ServiceProvider serviceProvider;
	private ArrayList<Activity> elements;
	private ArrayList<Activity> temporalActivities;

	public ModeloTablaActivies(ServiceProvider serviceProvider){
		this.serviceProvider = serviceProvider;
		String[] columnNames = {"Description"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Activity>();
		if (this.serviceProvider == null)
			this.temporalActivities = new ArrayList<Activity>();
	}



	public ArrayList<Activity> getTemporalActivities() {
		return temporalActivities;
	}



	public void setTemporalActivities(ArrayList<Activity> temporalActivities) {
		this.temporalActivities = temporalActivities;
	}



	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}



	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}



	public ArrayList<Activity> getElements() {
		return elements;
	}



	public void setElements(ArrayList<Activity> elements) {
		this.elements = elements;
	}



	@Override
	public void addElement(Activity activity) {
		Object[] newRow =  null;
		this.elements.add(activity);
		newRow = new Object[]{activity.getDescription()};
		addRow(newRow);

	}

	public void addElementTemporal (Activity activity) {
		this.temporalActivities.add(activity);
	}

	public void deleteElements (int[] rows) throws SQLException {

		for (int i = 0; i < rows.length; i++) {
			if (this.serviceProvider != null)
				this.serviceProvider.deleteActivity(this.elements.get(rows[i] - i));// se eliminan los provedores de servicios seleccionados de la base de datos y de la logica del negocio
			else
				this.temporalActivities.remove(rows[i] - i); // se elimian de la lista temporal

			this.deleteElement(rows[i] - i); // se eliminan de la tabla y de la logica del negocio

		}
	}

	public void deleteElement (int i) {
		this.elements.remove(i);
		this.removeRow(i);
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
