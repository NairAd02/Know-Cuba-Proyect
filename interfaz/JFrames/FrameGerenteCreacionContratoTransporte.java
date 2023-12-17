package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class FrameGerenteCreacionContratoTransporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteCreacionContratoTransporte frame = new FrameGerenteCreacionContratoTransporte();
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
	public FrameGerenteCreacionContratoTransporte() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 700, 512);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTransportationContract = new JLabel("TRANSPORTATION CONTRACT");
		lblTransportationContract.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationContract.setBounds(27, 11, 331, 30);
		panel.add(lblTransportationContract);
		
		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(58, 72, 119, 23);
		panel.add(lblStartDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(58, 106, 119, 22);
		panel.add(dateChooser);
		
		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.info);
		lblEndDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEndDate.setBounds(232, 72, 98, 23);
		panel.add(lblEndDate);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(222, 106, 119, 22);
		panel.add(dateChooser_1);
		
		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.info);
		lblProvider.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvider.setBounds(385, 72, 98, 23);
		panel.add(lblProvider);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(364, 106, 140, 22);
		panel.add(comboBox);
		
		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.info);
		lblSurcharge.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSurcharge.setBounds(538, 69, 124, 30);
		panel.add(lblSurcharge);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(551, 106, 98, 22);
		panel.add(spinner);
		
		JLabel lblShowTransportationModalities = new JLabel("SHOW TRANSPORTATION MODALITIES");
		lblShowTransportationModalities.setOpaque(true);
		lblShowTransportationModalities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowTransportationModalities.setForeground(SystemColor.info);
		lblShowTransportationModalities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblShowTransportationModalities.setBackground(new Color(18, 95, 115));
		lblShowTransportationModalities.setBounds(176, 149, 348, 30);
		panel.add(lblShowTransportationModalities);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(71, 201, 124, 30);
		panel.add(lblDescription);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(58, 242, 591, 195);
		panel.add(textPane);
		
		JLabel lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(232, 458, 235, 35);
		panel.add(lblConfirm);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(662, 0, 38, 38);
		panel.add(lblX);
	}
}
