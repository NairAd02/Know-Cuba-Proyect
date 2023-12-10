package JPanels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelPaqueteModalidadTransporte extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;

	/**
	 * Create the panel.
	 */
	public PanelPaqueteModalidadTransporte() {
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(PanelPaqueteModalidadTransporte.class.getResource("/images/flecha.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(279, 452, 91, 38);
		add(lblAtras);

		JLabel lblTransportation = new JLabel("TRANSPORTATION");
		lblTransportation.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportation.setBounds(28, 11, 206, 30);
		add(lblTransportation);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label.setIcon(new ImageIcon(PanelPaqueteModalidadTransporte.class.getResource("/images/flecha_izquierda.png")));
		label.setBounds(48, 53, 99, 20);
		add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label_1.setIcon(new ImageIcon(PanelPaqueteModalidadTransporte.class.getResource("/images/flecha_derecha.png")));
		label_1.setBounds(181, 53, 99, 20);
		add(label_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 84, 630, 359);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setColumnHeaderView(table);


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
		
		JLabel lblNewLabel = new JLabel("( Hours Kilometers ) ");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(239, 11, 173, 30);
		add(lblNewLabel);
	}
}
