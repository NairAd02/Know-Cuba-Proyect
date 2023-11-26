package JFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class FrameGerente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerente frame = new FrameGerente();
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
	public FrameGerente() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 990, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblManager = new JLabel("MANAGER");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblManager.setBounds(59, 16, 142, 32);
		panel.add(lblManager);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlText);
		separator.setBackground(SystemColor.controlText);
		separator.setBounds(189, 39, 801, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 39, 70, 2);
		panel.add(separator_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(952, 0, 38, 38);
		panel.add(lblX);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.controlText);
		separator_2.setBackground(SystemColor.controlText);
		separator_2.setBounds(0, 63, 990, 2);
		panel.add(separator_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 65, 279, 717);
		panel_1.setBackground(new Color(5, 150, 177));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(SystemColor.controlText);
		separator_2_1.setBackground(SystemColor.controlText);
		separator_2_1.setBounds(277, 0, 2, 717);
		panel_1.add(separator_2_1);
		
		JLabel lblNewLabel = new JLabel("ASSOCIATION-COMPANY");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel.setBackground(new Color(0, 183, 194));
		lblNewLabel.setBounds(0, 358, 279, 45);
		panel_1.add(lblNewLabel);
		
		JLabel lblContractCreation = new JLabel("CONTRACT CREATION");
		lblContractCreation.setForeground(new Color(0, 0, 0));
		lblContractCreation.setOpaque(true);
		lblContractCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblContractCreation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblContractCreation.setBackground(new Color(0, 183, 194));
		lblContractCreation.setBounds(0, 425, 279, 45);
		panel_1.add(lblContractCreation);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(FrameGerente.class.getResource("/images/Imagen5.png")));
		lblNewLabel_4.setBounds(0, 0, 279, 717);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(278, 63, 712, 719);
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 712, 41);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(234, 0, 2, 41);
		panel_3.add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setForeground(Color.BLACK);
		separator_1_1_1.setBackground(Color.BLACK);
		separator_1_1_1.setBounds(483, 0, 2, 41);
		panel_3.add(separator_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Service Provider");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(new Color(18, 95, 115));
		lblNewLabel_1.setBounds(0, 0, 236, 41);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Transportation Provider");
		lblNewLabel_2.setForeground(SystemColor.info);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(18, 95, 115));
		lblNewLabel_2.setBounds(235, 0, 250, 41);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Accommodation Provider");
		lblNewLabel_3.setForeground(SystemColor.info);
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(new Color(18, 95, 115));
		lblNewLabel_3.setBounds(483, 0, 229, 41);
		panel_3.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 41, 712, 678);
		panel_4.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
	}
}
