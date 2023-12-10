package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameGerenteAsociacionEmpresaProveedorTransporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable tableVehicles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteAsociacionEmpresaProveedorTransporte frame = new FrameGerenteAsociacionEmpresaProveedorTransporte();
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
	public FrameGerenteAsociacionEmpresaProveedorTransporte() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransportationProvider = new JLabel("TRANSPORTATION PROVIDER");
		lblTransportationProvider.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationProvider.setBounds(27, 11, 323, 30);
		contentPane.add(lblTransportationProvider);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(90, 52, 80, 23);
		contentPane.add(lblName);
		
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.info);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvince.setBounds(90, 86, 109, 23);
		contentPane.add(lblProvince);
		
		JLabel lblVehicles = new JLabel("VEHICLES");
		lblVehicles.setForeground(SystemColor.info);
		lblVehicles.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVehicles.setBounds(90, 130, 100, 23);
		contentPane.add(lblVehicles);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(180, 56, 197, 20);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(219, 90, 158, 20);
		contentPane.add(textField_1);
		
		JPanel panelVehicles = new JPanel();
		panelVehicles.setBounds(87, 164, 290, 99);
		contentPane.add(panelVehicles);
		panelVehicles.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelVehicles.add(scrollPane, BorderLayout.CENTER);
		
		tableVehicles = new JTable();
		scrollPane.setColumnHeaderView(tableVehicles);
		
		JLabel lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(220, 133, 71, 20);
		contentPane.add(lblAnnadir);
		
		JLabel lblEliminar = new JLabel("DELETE");
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(310, 133, 67, 20);
		contentPane.add(lblEliminar);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(115, 276, 235, 35);
		contentPane.add(lblAdd);
	}

}
