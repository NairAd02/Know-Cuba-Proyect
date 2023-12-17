package modelosTablas;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.ServiceModality;

public class ModeloTablaServiceModality extends DefaultTableModel implements ModelOperations<ServiceModality> {

	private static final long serialVersionUID = 1L;
	private ArrayList<ServiceModality> elements;

	public ModeloTablaServiceModality(){
		String[] columnNames = {"Activity", "Release_date", "Price"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<ServiceModality>();
	}

	@Override
	public void addElement(ServiceModality serviceModality) {
		Object[] newRow =  null;
		this.elements.add(serviceModality);
		newRow = new Object[]{serviceModality.getActivity().getDescription(), serviceModality.getReleasedDate(), serviceModality.getPrice()};
		addRow(newRow);

	}

	

	public ServiceModality deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public ServiceModality getElement (int pos) {
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
