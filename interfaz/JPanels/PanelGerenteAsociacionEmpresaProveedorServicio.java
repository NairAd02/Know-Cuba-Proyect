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
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorServicio() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		lblNewLabel_2 = new JLabel("DELETE");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(505, 27, 108, 33);
		add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("ADD");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(100, 27, 108, 33);
		add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Imagen2.jpg")));
		lblNewLabel.setBounds(0, 0, 712, 678);
		add(lblNewLabel);
		
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
