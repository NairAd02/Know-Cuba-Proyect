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

public class FrameGerenteCreacionContratoTransporteAnnadirHoras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteCreacionContratoTransporteAnnadirHoras frame = new FrameGerenteCreacionContratoTransporteAnnadirHoras();
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
	public FrameGerenteCreacionContratoTransporteAnnadirHoras() {
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
		
		JLabel lblVehicle = new JLabel("VEHICLE :");
		lblVehicle.setForeground(SystemColor.info);
		lblVehicle.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVehicle.setBounds(27, 79, 119, 23);
		contentPane.add(lblVehicle);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(279, 79, 161, 22);
		contentPane.add(comboBox);
		
		JLabel lblCostKilometersRout = new JLabel("COST KILOMETERS ROUT :");
		lblCostKilometersRout.setForeground(SystemColor.info);
		lblCostKilometersRout.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersRout.setBounds(27, 113, 249, 23);
		contentPane.add(lblCostKilometersRout);
		
		JLabel lblCostHours = new JLabel("COST HOURS :");
		lblCostHours.setForeground(SystemColor.info);
		lblCostHours.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHours.setBounds(27, 147, 175, 23);
		contentPane.add(lblCostHours);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(340, 148, 100, 20);
		contentPane.add(spinner);
		
		JLabel lblCostKilometersRoutAdd = new JLabel("COST KILOMETERS ROUT ADD :");
		lblCostKilometersRoutAdd.setForeground(SystemColor.info);
		lblCostKilometersRoutAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersRoutAdd.setBounds(27, 181, 278, 23);
		contentPane.add(lblCostKilometersRoutAdd);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(340, 182, 100, 20);
		contentPane.add(spinner_1);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(115, 270, 235, 35);
		contentPane.add(lblAdd);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(340, 114, 100, 20);
		contentPane.add(spinner_2);
		
		JLabel lblCostHoursAdd = new JLabel("COST HOURS ADD :");
		lblCostHoursAdd.setForeground(SystemColor.info);
		lblCostHoursAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursAdd.setBounds(27, 215, 175, 23);
		contentPane.add(lblCostHoursAdd);
		
		JSpinner spinner_1_1 = new JSpinner();
		spinner_1_1.setBounds(340, 216, 100, 20);
		contentPane.add(spinner_1_1);
		
		JLabel lblNewLabel = new JLabel("( Hours Kilometers ) ");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(73, 35, 173, 30);
		contentPane.add(lblNewLabel);
	}
}
