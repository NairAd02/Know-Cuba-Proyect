package JFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class FrameDependiente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDependiente frame = new FrameDependiente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameDependiente() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 990, 782);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 990, 112);
		panel_1.setBackground(new Color(5, 150, 177));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDependiente = new JLabel("DEPENDENT");
		lblDependiente.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblDependiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDependiente.setBounds(42, 11, 163, 39);
		panel_1.add(lblDependiente);
		
		JLabel lblNewLabel_3 = new JLabel("Cost-Max :");
		lblNewLabel_3.setForeground(SystemColor.info);
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(67, 61, 108, 20);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblCostomin = new JLabel("Cost-Min :");
		lblCostomin.setForeground(SystemColor.info);
		lblCostomin.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostomin.setBounds(388, 61, 108, 20);
		panel_1.add(lblCostomin);
		
		JLabel lblProvincia = new JLabel("Province :");
		lblProvincia.setForeground(SystemColor.info);
		lblProvincia.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvincia.setBounds(697, 61, 108, 20);
		panel_1.add(lblProvincia);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBox.setBounds(803, 61, 121, 20);
		panel_1.add(comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spinner.setBounds(171, 63, 70, 20);
		panel_1.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spinner_1.setBounds(494, 61, 70, 20);
		panel_1.add(spinner_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlText);
		separator.setBackground(SystemColor.controlText);
		separator.setBounds(196, 36, 794, 2);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 36, 51, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(SystemColor.controlText);
		separator_2.setForeground(SystemColor.controlText);
		separator_2.setBounds(0, 110, 990, 2);
		panel_1.add(separator_2);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(952, 0, 38, 38);
		panel_1.add(lblX);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 112, 990, 670);
		panel_2.setBackground(new Color(5, 150, 177));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(0, 228, 990, 217);
		panel_2.add(panel_4);
		
		JLabel lblPackage = new JLabel("PACKAGE : ");
		lblPackage.setForeground(SystemColor.info);
		lblPackage.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPackage.setBounds(441, 26, 101, 23);
		panel_4.add(lblPackage);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_1.setBounds(557, 26, 34, 23);
		panel_4.add(label_1);
		
		JLabel lblDescription = new JLabel("DESCRIPTION :");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(441, 56, 132, 23);
		panel_4.add(lblDescription);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setToolTipText("");
		panel_6.setOpaque(false);
		panel_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_6.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_6.setBounds(441, 85, 429, 67);
		panel_4.add(panel_6);
		
		JLabel lblDaysduration = new JLabel("DAYS-DURATION :");
		lblDaysduration.setForeground(SystemColor.info);
		lblDaysduration.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDaysduration.setBounds(441, 167, 163, 23);
		panel_4.add(lblDaysduration);
		
		JLabel label_4 = new JLabel("");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_4.setBounds(614, 166, 77, 23);
		panel_4.add(label_4);
		
		JLabel lblCost = new JLabel("COST :");
		lblCost.setForeground(SystemColor.info);
		lblCost.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCost.setBounds(805, 165, 72, 23);
		panel_4.add(lblCost);
		
		JLabel label_6 = new JLabel("");
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_6.setBounds(893, 166, 72, 23);
		panel_4.add(label_6);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrameDependiente.class.getResource("/images/Nuevo Presentaci\u00F3n de Microsoft PowerPoint (2) - copia (3).jpg")));
		lblNewLabel_1.setBounds(0, 0, 990, 217);
		panel_4.add(lblNewLabel_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(0, 456, 990, 214);
		panel_2.add(panel_5);
		
		JLabel lblPackage_1 = new JLabel("PACKAGE : ");
		lblPackage_1.setForeground(SystemColor.info);
		lblPackage_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPackage_1.setBounds(441, 25, 101, 23);
		panel_5.add(lblPackage_1);
		
		JLabel label_8 = new JLabel("");
		label_8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_8.setBounds(557, 26, 34, 23);
		panel_5.add(label_8);
		
		JLabel lblDescription_1 = new JLabel("DESCRIPTION :");
		lblDescription_1.setForeground(SystemColor.info);
		lblDescription_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription_1.setBounds(441, 53, 132, 23);
		panel_5.add(lblDescription_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setToolTipText("");
		panel_7.setOpaque(false);
		panel_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_7.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_7.setBounds(441, 81, 429, 67);
		panel_5.add(panel_7);
		
		JLabel lblDaysduration_1 = new JLabel("DAYS-DURATION :");
		lblDaysduration_1.setForeground(SystemColor.info);
		lblDaysduration_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDaysduration_1.setBounds(441, 166, 163, 23);
		panel_5.add(lblDaysduration_1);
		
		JLabel label_11 = new JLabel("");
		label_11.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_11.setBounds(614, 166, 77, 23);
		panel_5.add(label_11);
		
		JLabel lblCost_1 = new JLabel("COST :");
		lblCost_1.setForeground(SystemColor.info);
		lblCost_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCost_1.setBounds(805, 164, 72, 23);
		panel_5.add(lblCost_1);
		
		JLabel label_13 = new JLabel("");
		label_13.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_13.setBounds(893, 165, 72, 23);
		panel_5.add(label_13);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrameDependiente.class.getResource("/images/Nuevo Presentaci\u00F3n de Microsoft PowerPoint (2) - copia (3).jpg")));
		lblNewLabel_2.setBounds(0, 0, 990, 217);
		panel_5.add(lblNewLabel_2);
		
		JLabel label_14 = new JLabel("");
		label_14.setBounds(0, 466, 990, 217);
		panel_2.add(label_14);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 990, 217);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel labelPaquete = new JLabel("PACKAGE : ");
		labelPaquete.setForeground(SystemColor.info);
		labelPaquete.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelPaquete.setBounds(441, 23, 101, 23);
		panel_3.add(labelPaquete);
		
		JLabel labelCampoPaquete = new JLabel("");
		labelCampoPaquete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		labelCampoPaquete.setBounds(557, 23, 34, 23);
		panel_3.add(labelCampoPaquete);
		
		JLabel labelDescripcion = new JLabel("DESCRIPTION :");
		labelDescripcion.setForeground(SystemColor.info);
		labelDescripcion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelDescripcion.setBounds(441, 51, 132, 23);
		panel_3.add(labelDescripcion);
		
		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setLayout(null);
		panelDescripcion.setToolTipText("");
		panelDescripcion.setOpaque(false);
		panelDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelDescripcion.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelDescripcion.setBounds(441, 79, 429, 67);
		panel_3.add(panelDescripcion);
		
		JLabel labelDiasDuracion = new JLabel("DAYS-DURATION :");
		labelDiasDuracion.setForeground(SystemColor.info);
		labelDiasDuracion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelDiasDuracion.setBounds(441, 164, 159, 23);
		panel_3.add(labelDiasDuracion);
		
		JLabel labelCampoDiasDuracion = new JLabel("");
		labelCampoDiasDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		labelCampoDiasDuracion.setBounds(610, 164, 77, 23);
		panel_3.add(labelCampoDiasDuracion);
		
		JLabel labelCosto = new JLabel("COST :");
		labelCosto.setForeground(SystemColor.info);
		labelCosto.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelCosto.setBounds(805, 164, 72, 23);
		panel_3.add(labelCosto);
		
		JLabel labelCampoCosto = new JLabel("");
		labelCampoCosto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		labelCampoCosto.setBounds(893, 164, 72, 23);
		panel_3.add(labelCampoCosto);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(FrameDependiente.class.getResource("/images/Nuevo Presentaci\u00F3n de Microsoft PowerPoint (2) - copia (3).jpg")));
		lblImagen.setBounds(0, 0, 990, 217);
		panel_3.add(lblImagen);
	}
}
