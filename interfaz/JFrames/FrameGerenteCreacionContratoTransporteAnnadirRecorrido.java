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
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class FrameGerenteCreacionContratoTransporteAnnadirRecorrido extends JFrame {

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
					FrameGerenteCreacionContratoTransporteAnnadirRecorrido frame = new FrameGerenteCreacionContratoTransporteAnnadirRecorrido();
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
	public FrameGerenteCreacionContratoTransporteAnnadirRecorrido() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(27, 11, 294, 30);
		contentPane.add(lblTransportationMode);
		
		JLabel lblEstablishedRoute = new JLabel("( Established Route ) ");
		lblEstablishedRoute.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblEstablishedRoute.setBounds(79, 35, 177, 30);
		contentPane.add(lblEstablishedRoute);
		
		JLabel lblVehicle = new JLabel("VEHICLE :");
		lblVehicle.setForeground(SystemColor.info);
		lblVehicle.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVehicle.setBounds(43, 76, 119, 23);
		contentPane.add(lblVehicle);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(264, 79, 161, 22);
		contentPane.add(comboBox);
		
		JLabel lblDescriptionRout = new JLabel("DESCRIPTION ROUT :");
		lblDescriptionRout.setForeground(SystemColor.info);
		lblDescriptionRout.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescriptionRout.setBounds(43, 118, 199, 23);
		contentPane.add(lblDescriptionRout);
		
		JLabel lblCostGoing = new JLabel("COST GOING :");
		lblCostGoing.setForeground(SystemColor.info);
		lblCostGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostGoing.setBounds(43, 190, 136, 23);
		contentPane.add(lblCostGoing);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(325, 194, 100, 20);
		contentPane.add(spinner);
		
		JLabel lblCostLap = new JLabel("COST LAP :");
		lblCostLap.setForeground(SystemColor.info);
		lblCostLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostLap.setBounds(43, 224, 119, 23);
		contentPane.add(lblCostLap);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(325, 228, 100, 20);
		contentPane.add(spinner_1);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(115, 270, 235, 35);
		contentPane.add(lblAdd);
		
		textField = new JTextField();
		textField.setBounds(252, 118, 173, 61);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
