package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Contract;

public class ModeloTablaContract extends DefaultTableModel implements ModelOperations<Contract> {


	private static final long serialVersionUID = 1L;
	private ArrayList<Contract> elements;
	public ModeloTablaContract(){
		String[] columnNames = {"ID","Type","Start Date","Termination Date", "Reconciliation Date", "State", "Provider", "Given Information"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Contract>();

	}

	@Override
	public void addElement(Contract contract) {
		Object[] newRow =  null;
		this.elements.add(contract);
		newRow = new Object[]{contract.getId(), contract.getTypeOfContract(), contract.getStartDate(), contract.getTerminationDate(), contract.getReconciliationDate(), 
				(contract.isState()) ? "Close" : "In Procesing",contract.getProvider().getName(), "Show Modalities" };

		addRow(newRow);

	}


	public Contract deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public Contract getElement (int pos) {
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
