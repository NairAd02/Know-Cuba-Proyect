package JPanels;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelosTablas.ModeloTablaTransportationProvider;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelGerenteAsociacionEmpresaProveedorTransporte extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JLabel lblImage;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorTransporte() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/a\u00F1adir2 - copia.png")));
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBounds(127, 35, 71, 33);
		add(lblAnnadir);
		
		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/eliminar1.png")));
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBounds(491, 35, 108, 33);
		add(lblEliminar);
		
		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Imagen3.jpg")));
		lblImage.setBounds(0, 0, 712, 678);
		add(lblImage);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 94, 692, 573);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new ModeloTablaTransportationProvider());
		scrollPaneTable.setViewportView(table);

	}
}
