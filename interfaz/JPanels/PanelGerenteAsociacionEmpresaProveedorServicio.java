package JPanels;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelosTablas.ModeloTablaServiceProvider;

public class PanelGerenteAsociacionEmpresaProveedorServicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorServicio() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		panelTable = new JPanel();
		panelTable.setBounds(10, 93, 692, 574);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new ModeloTablaServiceProvider());
		scrollPaneTable.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Imagen2.jpg")));
		lblNewLabel.setBounds(0, 0, 712, 678);
		add(lblNewLabel);

	}
}
