package modelosTablas;


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.MealPlan;


public class ModeloTablaMealPlan extends DefaultTableModel implements ModelOperations<MealPlan>{

	private static final long serialVersionUID = 1L;
	private ArrayList<MealPlan> elements;

	public ModeloTablaMealPlan(){
		
		String[] columnNames = {"Name"};   
		this.setColumnIdentifiers(columnNames);
		this.isCellEditable(getRowCount(), getColumnCount());
		this.elements = new ArrayList<MealPlan>();

	}





	@Override
	public void addElement(MealPlan mealPlan) {
		Object[] newRow =  null;
		this.elements.add(mealPlan);
		newRow = new Object[]{mealPlan.getName()};
		addRow(newRow);

	}



	public boolean isFindElement (MealPlan mealPlan) { // Metodo para verificar la existencia de un elemento en la tabla
		boolean find = false;

		for (int i = 0; i < this.elements.size() && !find; i++) {
			if (this.elements.get(i).equals(mealPlan))
				find = true;
		}

		return find;
	}

	public MealPlan deleteElement (int i) {
		this.removeRow(i);
		return this.elements.remove(i);

	}

	public MealPlan getElement (int pos) {
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
