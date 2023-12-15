package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAvailableTransportation;
	private JTable tableAssignedTransports;


	public FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(5, 150, 177));
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
		
		JLabel lblAvailableTransportation = new JLabel("AVAILABLE TRANSPORTATION");
		lblAvailableTransportation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableTransportation.setBounds(25, 11, 275, 19);
		panel.add(lblAvailableTransportation);
		
		JLabel lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(435, 40, 155, 20);
		panel.add(lblAnnadir);
		
		JLabel lblAssignedTransports = new JLabel("ASSIGNED TRANSPORTS");
		lblAssignedTransports.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedTransports.setBounds(25, 173, 275, 19);
		panel.add(lblAssignedTransports);
		
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/flecha_izquierda.png")));
		label.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label.setBounds(123, 44, 99, 12);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/flecha_derecha.png")));
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label_1.setBounds(257, 44, 99, 12);
		panel.add(label_1);
		
		JPanel panelAvailableTransportation = new JPanel();
		panelAvailableTransportation.setBounds(10, 71, 580, 86);
		panel.add(panelAvailableTransportation);
		panelAvailableTransportation.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelAvailableTransportation.add(scrollPane, BorderLayout.CENTER);
		
		tableAvailableTransportation = new JTable();
		
		scrollPane.setColumnHeaderView(tableAvailableTransportation);
		
		JPanel panelAssignedTransports = new JPanel();
		panelAssignedTransports.setOpaque(false);
		panelAssignedTransports.setBounds(10, 214, 580, 86);
		panel.add(panelAssignedTransports);
		panelAssignedTransports.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedTransports.add(scrollPane_1, BorderLayout.CENTER);
		
		tableAssignedTransports = new JTable();
		scrollPane_1.setColumnHeaderView(tableAssignedTransports);
		
		JLabel lblNewLabel = new JLabel("( Hours Kilometers ) ");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(308, 5, 173, 30);
		panel.add(lblNewLabel);
	}

}