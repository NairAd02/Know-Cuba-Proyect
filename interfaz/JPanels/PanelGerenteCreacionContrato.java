package JPanels;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelosTablas.ModeloTablaContract;

public class PanelGerenteCreacionContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelGerenteCreacionContrato() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 712, 69);
		panel.setBackground(new Color(18, 95, 115));
		add(panel);
		panel.setLayout(null);
		
		JLabel lblAdd = new JLabel("ADD Service Contract");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(10, 21, 159, 26);
		panel.add(lblAdd);
		
		JLabel lblAdd_1 = new JLabel("ADD Transportation Contract");
		lblAdd_1.setOpaque(true);
		lblAdd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAdd_1.setBackground(SystemColor.info);
		lblAdd_1.setBounds(177, 21, 216, 26);
		panel.add(lblAdd_1);
		
		JLabel lblAddAccommodationContract = new JLabel("ADD Accommodation Contract");
		lblAddAccommodationContract.setOpaque(true);
		lblAddAccommodationContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAccommodationContract.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAddAccommodationContract.setBackground(SystemColor.info);
		lblAddAccommodationContract.setBounds(401, 21, 234, 26);
		panel.add(lblAddAccommodationContract);
		
		JLabel lblDelete = new JLabel("DELETE");
		lblDelete.setBounds(643, 21, 58, 26);
		panel.add(lblDelete);
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 68, 712, 651);
		panel_1.setBackground(new Color(5, 150, 177));
		add(panel_1);
		
		panelTable = new JPanel();
		panelTable.setBounds(10, 93, 692, 547);
		panel_1.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new ModeloTablaContract());
		scrollPaneTable.setViewportView(table);

	}

}
