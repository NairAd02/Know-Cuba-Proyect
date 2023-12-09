package JPanels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import modelosTablas.ModeloTablaAccommodationModality;

import javax.swing.ImageIcon;

public class PanelPaqueteModalidadAlojamiento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableModalitys;
	private JScrollPane scrollPane;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;

	/**
	 * Create the panel.
	 */
	public PanelPaqueteModalidadAlojamiento() {
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 84, 630, 359);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));



		tableModalitys = new JTable();
		tableModalitys.setModel(new ModeloTablaAccommodationModality());

		scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(tableModalitys);


		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(PanelPaqueteModalidadAlojamiento.class.getResource("/images/flecha.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(279, 452, 91, 38);
		add(lblAtras);

		JLabel lblAccommodation = new JLabel("ACCOMMODATION");
		lblAccommodation.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblAccommodation.setBounds(28, 11, 206, 30);
		add(lblAccommodation);

		if (true) { // si se cumple con la condicion de que el usuario sea TRABAJADOR
		this.addButtonAdd();
		this.addButtonDelete();
		}
			
	}

	public void addButtonAdd () {
		lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(317, 53, 155, 20);
		add(lblAnnadir);
	}

	public void addButtonDelete () {
		lblEliminar = new JLabel("DELETE");
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(485, 53, 155, 20);
		add(lblEliminar);
	}

}
