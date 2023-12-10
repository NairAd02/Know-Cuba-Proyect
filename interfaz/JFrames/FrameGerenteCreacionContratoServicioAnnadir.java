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
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;

public class FrameGerenteCreacionContratoServicioAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteCreacionContratoServicioAnnadir frame = new FrameGerenteCreacionContratoServicioAnnadir();
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
	public FrameGerenteCreacionContratoServicioAnnadir() {
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
		
		JLabel lblActivity = new JLabel("ACTIVITY :");
		lblActivity.setForeground(SystemColor.info);
		lblActivity.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblActivity.setBounds(82, 72, 119, 23);
		contentPane.add(lblActivity);
		
		JLabel lblDate = new JLabel("DATE :");
		lblDate.setForeground(SystemColor.info);
		lblDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDate.setBounds(82, 123, 77, 23);
		contentPane.add(lblDate);
		
		JLabel lblStartDate = new JLabel("PRICE :");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(82, 174, 77, 23);
		contentPane.add(lblStartDate);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(107, 243, 235, 35);
		contentPane.add(lblAdd);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(206, 72, 161, 22);
		contentPane.add(comboBox);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(206, 123, 161, 23);
		contentPane.add(dateChooser);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(267, 178, 100, 20);
		contentPane.add(spinner);
		
		JLabel lblServiceMode = new JLabel("SERVICE MODE");
		lblServiceMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblServiceMode.setBounds(27, 11, 191, 30);
		contentPane.add(lblServiceMode);
	}
}
