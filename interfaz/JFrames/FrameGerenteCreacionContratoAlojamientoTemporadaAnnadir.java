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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;

public class FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir extends JFrame {

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
					FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir frame = new FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir();
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
	public FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeasonMode = new JLabel("SEASON MODE");
		lblSeasonMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSeasonMode.setBounds(27, 11, 169, 30);
		contentPane.add(lblSeasonMode);
		
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
		lblName.setBounds(27, 52, 90, 23);
		contentPane.add(lblName);
		
		JLabel lblStartDate = new JLabel("START DATE :");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(27, 86, 249, 23);
		contentPane.add(lblStartDate);
		
		JLabel lblTer = new JLabel("TERMINATION DATE :");
		lblTer.setForeground(SystemColor.info);
		lblTer.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTer.setBounds(27, 120, 197, 23);
		contentPane.add(lblTer);
		
		JLabel lblDescription = new JLabel("DESCRIPTION :");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(27, 154, 146, 23);
		contentPane.add(lblDescription);
		
		JLabel lblTypeSeason = new JLabel("TYPE SEASON :");
		lblTypeSeason.setForeground(SystemColor.info);
		lblTypeSeason.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTypeSeason.setBounds(27, 278, 146, 23);
		contentPane.add(lblTypeSeason);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(117, 324, 235, 35);
		contentPane.add(lblAdd);
		
		textField = new JTextField();
		textField.setBounds(315, 56, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(315, 89, 125, 20);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(315, 120, 125, 20);
		contentPane.add(dateChooser_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(315, 281, 125, 22);
		contentPane.add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(220, 157, 220, 109);
		contentPane.add(textPane);
	}
}
