package JPanels;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelosTablas.ModeloTablaServiceProvider;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelGerenteAsociacionEmpresaProveedorServicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable table;
	private JLabel lblImage;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorServicio() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/eliminar1.png")));
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setBounds(491, 35, 108, 33);
		add(lblEliminar);
		
		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/a\u00F1adir2 - copia.png")));
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setBounds(127, 35, 71, 33);
		add(lblAnnadir);
		
		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Imagen2.jpg")));
		lblImage.setBounds(0, 0, 712, 678);
		add(lblImage);
		
		panelTable = new JPanel();
		panelTable.setOpaque(false);
		panelTable.setBounds(10, 93, 692, 574);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new ModeloTablaServiceProvider());
		scrollPaneTable.setViewportView(table);

	}
}
