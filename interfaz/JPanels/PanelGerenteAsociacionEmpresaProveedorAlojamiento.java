package JPanels;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelosTablas.ModeloTablaAccommodationProvider;

public class PanelGerenteAsociacionEmpresaProveedorAlojamiento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JPanel panelTable;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorAlojamiento() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Imagen1.jpg")));
		lblNewLabel.setBounds(0, 0, 712, 678);
		add(lblNewLabel);
		
				panelTable = new JPanel();
				panelTable.setBounds(10, 93, 692, 574);
				add(panelTable);
				panelTable.setLayout(new BorderLayout(0, 0));
				
						scrollPaneTable = new JScrollPane();
						panelTable.add(scrollPaneTable, BorderLayout.CENTER);
						
								table = new JTable();
								table.setModel(new ModeloTablaAccommodationProvider());
								scrollPaneTable.setViewportView(table);

	}

}
