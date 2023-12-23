package modelosTablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.Season;

public class ModeloTablaSeasons extends DefaultTableModel implements ModelOperations<Season> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Season> elements;

	public ModeloTablaSeasons(){
		String[] columnNames = {"Type", "Name", "Start Date", "Termination Date", "Description"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<Season>();

	}

	@Override
	public void addElement(Season season) {
		Object[] newRow =  null;
		this.elements.add(season);
		newRow = new Object[]{season.getTypeOfSeason(), season.getName(), season.getStartDate(), season.getTerminationDate(), season.getDescription()};
		addRow(newRow);
	}



	public Season deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public int cantElements () {
		return this.elements.size();
	}

	public Season getElement (int pos) {
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
