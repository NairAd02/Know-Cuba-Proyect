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

public class FrameGerenteCreacionContratoTransporteAnnadirCosto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteCreacionContratoTransporteAnnadirCosto frame = new FrameGerenteCreacionContratoTransporteAnnadirCosto();
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
	public FrameGerenteCreacionContratoTransporteAnnadirCosto() {
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
		
		JLabel lblCostKilometers = new JLabel("( Cost Kilometers ) ");
		lblCostKilometers.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCostKilometers.setBounds(79, 35, 173, 30);
		contentPane.add(lblCostKilometers);
		
		JLabel lblRoomType = new JLabel("VEHICLE :");
		lblRoomType.setForeground(SystemColor.info);
		lblRoomType.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomType.setBounds(43, 90, 119, 23);
		contentPane.add(lblRoomType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(264, 90, 161, 22);
		contentPane.add(comboBox);
		
		JLabel lblCostKilometersGoing = new JLabel("COST KILOMETERS GOING :");
		lblCostKilometersGoing.setForeground(SystemColor.info);
		lblCostKilometersGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersGoing.setBounds(43, 130, 249, 23);
		contentPane.add(lblCostKilometersGoing);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(324, 131, 100, 20);
		contentPane.add(spinner_2);
		
		JLabel lblCostKilometersLap = new JLabel("COST KILOMETERS LAP :");
		lblCostKilometersLap.setForeground(SystemColor.info);
		lblCostKilometersLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersLap.setBounds(43, 170, 225, 23);
		contentPane.add(lblCostKilometersLap);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(324, 171, 100, 20);
		contentPane.add(spinner);
		
		JLabel lblCostHoursWait = new JLabel("COST HOURS WAIT :");
		lblCostHoursWait.setForeground(SystemColor.info);
		lblCostHoursWait.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursWait.setBounds(43, 210, 278, 23);
		contentPane.add(lblCostHoursWait);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(324, 211, 100, 20);
		contentPane.add(spinner_1);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(115, 270, 235, 35);
		contentPane.add(lblAdd);
	}

}
