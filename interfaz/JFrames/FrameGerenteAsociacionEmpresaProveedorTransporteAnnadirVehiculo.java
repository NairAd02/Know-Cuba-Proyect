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
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo frame = new FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo();
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
	public FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransportProviderVehicles = new JLabel("TRANSPORT PROVIDER VEHICLES");
		lblTransportProviderVehicles.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblTransportProviderVehicles.setBounds(27, 11, 341, 30);
		contentPane.add(lblTransportProviderVehicles);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(384, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblLock = new JLabel("LOCK :");
		lblLock.setForeground(SystemColor.info);
		lblLock.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLock.setBounds(112, 74, 70, 23);
		contentPane.add(lblLock);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(93, 141, 235, 35);
		contentPane.add(lblAdd);
		
		textField = new JTextField();
		textField.setBounds(219, 75, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
