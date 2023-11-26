package JFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DebugGraphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.UIManager;

public class FrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
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
	public FrameLogin() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 931, 438);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 536, 438);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setOpaque(true);
		lblImage.setIcon(new ImageIcon(FrameLogin.class.getResource("/images/13_Cuba - copia.jpg")));
		lblImage.setBounds(0, 0, 536, 438);
		panel_1.add(lblImage);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(535, 0, 396, 438);
		panel_2.setBackground(new Color(18, 95, 115));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(SystemColor.info);
		lblUsername.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblUsername.setBounds(37, 132, 325, 21);
		panel_2.add(lblUsername);
		
		JLabel lblIntroducirUsername = new JLabel("________________________________________________");
		lblIntroducirUsername.setBounds(37, 173, 325, 21);
		panel_2.add(lblIntroducirUsername);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setForeground(SystemColor.info);
		labelPassword.setFont(new Font("Arial Black", Font.PLAIN, 14));
		labelPassword.setBounds(37, 226, 325, 21);
		panel_2.add(labelPassword);
		
		JLabel labelIntroducirPassword = new JLabel("________________________________________________");
		labelIntroducirPassword.setBounds(37, 266, 325, 21);
		panel_2.add(labelIntroducirPassword);
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show password");
		chckbxShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxShowPassword.setOpaque(false);
		chckbxShowPassword.setBounds(37, 294, 121, 23);
		panel_2.add(chckbxShowPassword);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblLogin.setOpaque(true);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(37, 358, 325, 35);
		lblLogin.setBackground(SystemColor.info);
		panel_2.add(lblLogin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setOpaque(false);
		textFieldUsername.setBounds(37, 163, 288, 20);
		textFieldUsername.setBorder(null);
		panel_2.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setOpaque(false);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(37, 255, 288, 20);
		textFieldPassword.setBorder(null);
		panel_2.add(textFieldPassword);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrameLogin.class.getResource("/images/WhatsApp Image 2023-11-14 at 8.59.57 PM - copia - copia.jpeg")));
		lblLogo.setBounds(155, 23, 85, 80);
		panel_2.add(lblLogo);
		
		JLabel lblNotificacionRegistro = new JLabel("Usted no se ha registrado en el sistema");
		lblNotificacionRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNotificacionRegistro.setBounds(96, 324, 203, 14);
		lblNotificacionRegistro.setForeground(new Color(255, 51, 0));
		panel_2.add(lblNotificacionRegistro);
		
		JLabel lblNewLabel = new JLabel("Registrar");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(268, 298, 57, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(358, 0, 38, 38);
		panel_2.add(lblX);
	}
}
