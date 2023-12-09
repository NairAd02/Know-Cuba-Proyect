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
import javax.swing.ImageIcon;

public class FrameDependientePaquete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDependientePaquete frame = new FrameDependientePaquete();
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
	public FrameDependientePaquete() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 650, 501);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPackage = new JLabel("PACKAGE : ");
		lblPackage.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblPackage.setBounds(32, 11, 123, 30);
		panel.add(lblPackage);
		
		JLabel labelCampoPaquete = new JLabel("");
		labelCampoPaquete.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		labelCampoPaquete.setBounds(166, 11, 34, 30);
		panel.add(labelCampoPaquete);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrameDependientePaquete.class.getResource("/images/hotel1.png")));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(97, 127, 181, 87);
		lblNewLabel.setBackground(new Color(18, 95, 115));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrameDependientePaquete.class.getResource("/images/carro3.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(369, 127, 181, 87);
		lblNewLabel_1.setBackground(new Color(18, 95, 115));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(FrameDependientePaquete.class.getResource("/images/actividad4.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBounds(234, 250, 181, 87);
		lblNewLabel_1_1.setBackground(new Color(18, 95, 115));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblReserve = new JLabel("RESERVE");
		lblReserve.setOpaque(true);
		lblReserve.setHorizontalAlignment(SwingConstants.CENTER);
		lblReserve.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblReserve.setBackground(SystemColor.info);
		lblReserve.setBounds(207, 421, 235, 35);
		panel.add(lblReserve);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(612, 0, 38, 38);
		panel.add(lblX);
	}
}
