package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.EstablishedRoute;

public class ModeloTablaTransportModalityEstablishedRoute extends DefaultTableModel implements ModelOperations<EstablishedRoute> {

	private static final long serialVersionUID = 1L;
	private ArrayList<EstablishedRoute> elements;

	public ModeloTablaTransportModalityEstablishedRoute(){
		String[] columnNames = {"Description rout", "Cost_going","Cost lap", "Price"};
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<EstablishedRoute>();
	}

	@Override
	public void addElement(EstablishedRoute establishedRoute) {
		Object[] newRow =  null;
		this.elements.add(establishedRoute);
		newRow = new Object[]{establishedRoute.getDescriptionRout(), establishedRoute.getCostGoing(), establishedRoute.getCostLap(), establishedRoute.price()};
		addRow(newRow);
	}


	public EstablishedRoute deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public EstablishedRoute getElement (int pos) {
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
