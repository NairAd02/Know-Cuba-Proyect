package JFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelosTablas.ModeloTablaServiceModality;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameTrabajadorModalidadServicioAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAvailableServices;
	private JTable tableAssignedServices;

	
	public FrameTrabajadorModalidadServicioAnnadir() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(5, 150, 177));
		panel.setLayout(null);
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 600, 360);
		contentPane.add(panel);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(562, 0, 38, 38);
		panel.add(lblX);
		
		JLabel lblAvailableServices = new JLabel("AVAILABLE SERVICES");
		lblAvailableServices.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableServices.setBounds(25, 33, 275, 19);
		panel.add(lblAvailableServices);
		
		JLabel lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(435, 40, 155, 20);
		panel.add(lblAnnadir);
		
		JLabel lblAssignedServices = new JLabel("ASSIGNED SERVICES");
		lblAssignedServices.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedServices.setBounds(25, 173, 275, 19);
		panel.add(lblAssignedServices);
		
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
		
		JPanel panelAvailableServices = new JPanel();
		panelAvailableServices.setBounds(10, 71, 580, 86);
		panel.add(panelAvailableServices);
		panelAvailableServices.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelAvailableServices.add(scrollPane, BorderLayout.CENTER);
		
		tableAvailableServices = new JTable();
		tableAvailableServices.setModel(new ModeloTablaServiceModality());
		scrollPane.setViewportView(tableAvailableServices);
		
		JPanel panelAssignedServices = new JPanel();
		panelAssignedServices.setOpaque(false);
		panelAssignedServices.setBounds(10, 214, 580, 86);
		panel.add(panelAssignedServices);
		panelAssignedServices.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedServices.add(scrollPane_1, BorderLayout.CENTER);
		
		tableAssignedServices = new JTable();
		tableAssignedServices.setModel(new ModeloTablaServiceModality());
		scrollPane_1.setViewportView(tableAssignedServices);
	}

}
