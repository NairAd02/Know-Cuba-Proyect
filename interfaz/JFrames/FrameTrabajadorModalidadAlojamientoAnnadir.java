package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameTrabajadorModalidadAlojamientoAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAvailableAccommodation;
	private JTable tableAssignedAccommodations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTrabajadorModalidadAlojamientoAnnadir frame = new FrameTrabajadorModalidadAlojamientoAnnadir();
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
	public FrameTrabajadorModalidadAlojamientoAnnadir() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 600, 360);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(562, 0, 38, 38);
		panel.add(lblX);
		
		JLabel lblAvailableAccommodation = new JLabel("AVAILABLE ACCOMMODATION");
		lblAvailableAccommodation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableAccommodation.setBounds(25, 33, 275, 19);
		panel.add(lblAvailableAccommodation);
		
		JLabel lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(435, 40, 155, 20);
		panel.add(lblAnnadir);
		
		JLabel lblAssignedAccommodations = new JLabel("ASSIGNED ACCOMMODATIONS");
		lblAssignedAccommodations.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedAccommodations.setBounds(25, 173, 275, 19);
		panel.add(lblAssignedAccommodations);
		
		JLabel lblDenegar = new JLabel("DENY");
		lblDenegar.setOpaque(true);
		lblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(SystemColor.info);
		lblDenegar.setBounds(435, 183, 155, 20);
		panel.add(lblDenegar);
		
		JLabel lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(182, 311, 235, 35);
		panel.add(lblConfirm);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setOpaque(true);
		lblFondo.setBackground(new Color(5, 150, 177));
		lblFondo.setBounds(0, 0, 600, 360);
		panel.add(lblFondo);
		
		JPanel panelAvailableAccommodation = new JPanel();
		panelAvailableAccommodation.setBounds(10, 71, 580, 86);
		panel.add(panelAvailableAccommodation);
		panelAvailableAccommodation.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelAvailableAccommodation.add(scrollPane, BorderLayout.CENTER);
		
		tableAvailableAccommodation = new JTable();
		scrollPane.setColumnHeaderView(tableAvailableAccommodation);
		
		JPanel panelAssignedAccommodations = new JPanel();
		panelAssignedAccommodations.setOpaque(false);
		panelAssignedAccommodations.setBounds(10, 214, 580, 86);
		panel.add(panelAssignedAccommodations);
		panelAssignedAccommodations.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedAccommodations.add(scrollPane_1, BorderLayout.CENTER);
		
		tableAssignedAccommodations = new JTable();
		scrollPane_1.setColumnHeaderView(tableAssignedAccommodations);
	}
}
