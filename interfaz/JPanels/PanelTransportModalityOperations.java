package JPanels;

import logica.TransportModality;

import java.sql.SQLException;

import javax.swing.JTable;

public interface PanelTransportModalityOperations {
	public void actualizarTablaModalitys(); // Metodo para actualizar la informacion de la tabla de las modalidaes
	public void deleteElementsTable() throws SQLException; // Metodo para eliminar la informacion de la tabla de las modalidades
	public JTable getTable(); // Metodo para devolver la tabla definida en el panel

	public TransportModality getTransportModalitySelected (); // Metodo para obtener la modalidad de transporte seleccionada

	public void clearSelectionTable (); // Metodo para deseleccionar los elementos seleccionados de la tabla

	public void restoreFilters (); // Metodo para restaurar los filtros de la tabla de las modalidades
}
