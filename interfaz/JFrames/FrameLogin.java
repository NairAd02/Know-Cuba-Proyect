package JFrames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import dao.UserDAO;
import logica.Administrator;
import logica.Controller;
import logica.Dependent;
import logica.Manager;
import logica.PackageDesigner;
import logica.User;
import utils.ConnectionDataBase;
import utils.Semaphore;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.border.LineBorder;



import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;


public class FrameLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblLogin;
	private JLabel lblNotificacionRegistro;
	private JLabel lblX;
	private int mouseX, mouseY;


	
	public FrameLogin() {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 438);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );

			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();

		panel.setBounds(0, 0, 931, 438);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 3, 3, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(0, 0, 536, 438);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblImage = new JLabel("");
		lblImage.setBorder(new MatteBorder(3, 3, 3, 0, (Color) new Color(0, 0, 0)));
		lblImage.setOpaque(true);
		lblImage.setIcon(new ImageIcon(FrameLogin.class.getResource("/images/13_Cuba - copia.jpg")));
		lblImage.setBounds(0, 0, 536, 438);
		panel_1.add(lblImage);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 0, 3, 3, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(535, 0, 396, 438);
		panel_2.setBackground(new Color(18, 95, 115));
		panel.add(panel_2);
		panel_2.setLayout(null);


		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(SystemColor.textHighlightText);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsername.setBounds(37, 132, 325, 21);
		panel_2.add(lblUsername);

		JLabel lblIntroducirUsername = new JLabel("________________________________________________");
		lblIntroducirUsername.setBounds(37, 173, 325, 21);
		panel_2.add(lblIntroducirUsername);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setForeground(SystemColor.textHighlightText);
		labelPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		labelPassword.setBounds(37, 226, 325, 21);
		panel_2.add(labelPassword);

		JLabel labelIntroducirPassword = new JLabel("________________________________________________");
		labelIntroducirPassword.setBounds(37, 266, 325, 21);
		panel_2.add(labelIntroducirPassword);

		JCheckBox chckbxShowPassword = new JCheckBox("Show password");
		chckbxShowPassword.setForeground(SystemColor.textHighlightText);
		chckbxShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		chckbxShowPassword.setOpaque(false);
		chckbxShowPassword.setBounds(37, 294, 121, 23);
		panel_2.add(chckbxShowPassword);

		lblLogin = new JLabel("LOGIN");
		lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					loguarseAlSistema();
					ConnectionDataBase.commit(); // se confirman las transacciones realizadas
				} catch (SQLException e1) {
					try {
						ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogin.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLogin.setBackground(SystemColor.info);
				lblNotificacionRegistro.setVisible(false);
			}
		});
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLogin.setOpaque(true);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(35, 358, 325, 35);
		lblLogin.setBackground(SystemColor.info);
		panel_2.add(lblLogin);

		textFieldUsername = new JTextField();
		textFieldUsername.setForeground(Color.BLACK);
		textFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldUsername.setOpaque(false);
		textFieldUsername.setBounds(37, 163, 288, 20);
		textFieldUsername.setBorder(null);
		panel_2.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setForeground(Color.BLACK);
		textFieldPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldPassword.setOpaque(false);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(37, 255, 288, 20);
		textFieldPassword.setBorder(null);
		panel_2.add(textFieldPassword);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FrameLogin.class.getResource("/images/WhatsApp Image 2023-11-14 at 8.59.57 PM - copia - copia.jpeg")));
		lblLogo.setBounds(155, 23, 85, 80);
		panel_2.add(lblLogo);

		lblNotificacionRegistro = new JLabel("You have not been registered in the system");
		lblNotificacionRegistro.setVisible(false);
		lblNotificacionRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificacionRegistro.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNotificacionRegistro.setBounds(50, 324, 295, 21);
		lblNotificacionRegistro.setForeground(Color.RED);
		panel_2.add(lblNotificacionRegistro);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.textHighlightText);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(SystemColor.textHighlightText);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(358, 0, 38, 38);
		panel_2.add(lblX);
	}

	public void loguarseAlSistema () throws SQLException {
		User user = UserDAO.getInstancie().select(textFieldUsername.getText(), textFieldPassword.getText()); // se obtiene la informacion del usuario con ese nombre de usuario y contraseña

		if (user != null) { // se realmente existe un usuario con ese nombre de usuario y contraseña
			Controller.getInstancie(user); // se crea el sistema logico
			if (user.isStatePassword()) { // si el usuario ya cambió su constraseña
				this.iniciarAlPrograma();
			}
			else { // si no la cambió
				this.abrirFrameChangeStatePassword(); // se abre el frame para el cambio de la contraseña
			}
		}
		else {
			lblNotificacionRegistro.setVisible(true);
		}
	}
	
	private void abrirFrameChangeStatePassword () {
		FrameChangeStatePassword frameChangeStatePassword = new FrameChangeStatePassword(FrameLogin.this);
		frameChangeStatePassword.setVisible(true);
		setEnabled(false); // se inhabilita el frame actual
	}
	
	public void iniciarAlPrograma () throws SQLException {
		Controller.getInstancie().getUser().establecerConexion(); // se indica que el usuario a iniciado sesion en el sistema
		FramePrincipal framGerente = FramePrincipal.getIntancie();
		framGerente.setVisible(true);
		dispose(); // se cierra el frame
	}


}
