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
import javax.swing.ImageIcon;

public class PanelDependientePaqueteAlojamiento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelDependientePaqueteAlojamiento() {
		setLayout(null);
		
		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(PanelDependientePaqueteAlojamiento.class.getResource("/images/flecha.png")));
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
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setOpaque(true);
		lblFondo.setBounds(0, 0, 650, 501);
		lblFondo.setBackground(new Color(5, 150, 177));
		add(lblFondo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 84, 630, 359);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);

	}
}
