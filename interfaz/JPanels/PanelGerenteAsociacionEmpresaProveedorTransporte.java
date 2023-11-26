package JPanels;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelosTablas.ModeloTablaTransportationProvider;

public class PanelGerenteAsociacionEmpresaProveedorTransporte extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorTransporte() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 104, 692, 574);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new ModeloTablaTransportationProvider());
		scrollPaneTable.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Imagen3.jpg")));
		lblNewLabel.setBounds(0, 0, 712, 678);
		add(lblNewLabel);

	}
}
