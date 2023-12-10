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

public class FrameGerenteCreacionContratoAlojamientoAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteCreacionContratoAlojamientoAnnadir frame = new FrameGerenteCreacionContratoAlojamientoAnnadir();
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
	public FrameGerenteCreacionContratoAlojamientoAnnadir() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
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
		lblX.setBounds(412, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblRoomType = new JLabel("ROOM TYPE :");
		lblRoomType.setForeground(SystemColor.info);
		lblRoomType.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomType.setBounds(72, 68, 119, 23);
		contentPane.add(lblRoomType);
		
		JLabel lblMealPlan = new JLabel("MEAL PLAN :");
		lblMealPlan.setForeground(SystemColor.info);
		lblMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMealPlan.setBounds(72, 102, 119, 23);
		contentPane.add(lblMealPlan);
		
		JLabel lblNumberOfDays = new JLabel("NUMBER OF DAYS :");
		lblNumberOfDays.setForeground(SystemColor.info);
		lblNumberOfDays.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNumberOfDays.setBounds(72, 144, 175, 23);
		contentPane.add(lblNumberOfDays);
		
		JLabel lblPrice = new JLabel("PRICE :");
		lblPrice.setForeground(SystemColor.info);
		lblPrice.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPrice.setBounds(72, 186, 119, 23);
		contentPane.add(lblPrice);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(107, 243, 235, 35);
		contentPane.add(lblAdd);
		
		JLabel lblTransportationMode = new JLabel("ACCOMMODATION MODE");
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(27, 11, 294, 30);
		contentPane.add(lblTransportationMode);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(218, 68, 161, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(218, 102, 161, 22);
		contentPane.add(comboBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(279, 145, 100, 20);
		contentPane.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(279, 187, 100, 20);
		contentPane.add(spinner_1);
	}

}
