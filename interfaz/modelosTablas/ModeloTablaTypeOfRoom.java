package modelosTablas;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.TypeOfRoom;

public class ModeloTablaTypeOfRoom extends DefaultTableModel implements ModelOperations<TypeOfRoom> {

	private static final long serialVersionUID = 1L;
	private ArrayList<TypeOfRoom> elements;


	public ModeloTablaTypeOfRoom(){
		String[] columnNames = {"Name"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<TypeOfRoom>();

	}


	@Override
	public void addElement(TypeOfRoom typeOfRoom) {
		Object[] newRow =  null;
		this.elements.add(typeOfRoom);
		newRow = new Object[]{typeOfRoom.getName()};
		addRow(newRow);

	}




	public boolean isFindElement (TypeOfRoom typeOfRoom) { // Metodo para verificar la existencia de un elemento en la tabla
		boolean find = false;

		for (int i = 0; i < this.elements.size() && !find; i++) {
			if (this.elements.get(i).equals(typeOfRoom))
				find = true;
		}

		return find;
	}

	public TypeOfRoom deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);
	}

	public TypeOfRoom getElement (int pos) {
		return this.elements.get(pos);
	}

	public int cantElements () {
		return this.elements.size();
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
