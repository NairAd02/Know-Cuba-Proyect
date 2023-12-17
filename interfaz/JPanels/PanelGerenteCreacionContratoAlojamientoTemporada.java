package JPanels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class PanelGerenteCreacionContratoAlojamientoTemporada extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableSeason;

	/**
	 * Create the panel.
	 */
	public PanelGerenteCreacionContratoAlojamientoTemporada() {
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		setBounds(0, 0, 700, 512);
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 84, 680, 359);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		tableSeason = new JTable();
		scrollPane.setColumnHeaderView(tableSeason);
		
		JLabel lblSeason = new JLabel("SEASONS");
		lblSeason.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSeason.setBounds(28, 11, 116, 30);
		add(lblSeason);
		
		JLabel lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(535, 32, 155, 20);
		add(lblAnnadir);
		
		JLabel lblEliminar = new JLabel("DELETE");
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(535, 53, 155, 20);
		add(lblEliminar);
		
		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(PanelGerenteCreacionContratoAlojamientoTemporada.class.getResource("/images/flecha.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(304, 454, 91, 38);
		add(lblAtras);

	}
}
