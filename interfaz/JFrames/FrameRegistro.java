package JFrames;



import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrator;
import logica.Controller;
import logica.Dependent;
import logica.Manager;
import logica.PackageDesigner;
import logica.Rol;
import logica.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	private JLabel labelAceptar;
	private JLabel lblCancelar;
	private JComboBox <Rol> comboBoxRoles;


	public FrameRegistro() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 371, 430);
		panel.setBackground(new Color(18, 95, 115));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRole = new JLabel("");
		lblRole.setIcon(new ImageIcon(FrameRegistro.class.getResource("/images/rol.png")));
		lblRole.setForeground(SystemColor.info);
		lblRole.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblRole.setBounds(159, 44, 53, 56);
		panel.add(lblRole);

		JLabel labelUserName = new JLabel("Username");
		labelUserName.setForeground(SystemColor.info);
		labelUserName.setFont(new Font("Arial Black", Font.PLAIN, 14));
		labelUserName.setBounds(37, 154, 90, 21);
		panel.add(labelUserName);

		textFieldUserName = new JTextField();
		textFieldUserName.setOpaque(false);
		textFieldUserName.setColumns(10);
		textFieldUserName.setBorder(null);
		textFieldUserName.setBounds(37, 176, 288, 20);
		panel.add(textFieldUserName);

		JLabel labelBarraUserName = new JLabel("________________________________________________");
		labelBarraUserName.setBounds(37, 186, 325, 21);
		panel.add(labelBarraUserName);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setForeground(SystemColor.info);
		labelPassword.setFont(new Font("Arial Black", Font.PLAIN, 14));
		labelPassword.setBounds(37, 250, 90, 21);
		panel.add(labelPassword);

		JCheckBox checkBoxShowPassword = new JCheckBox("Show password");
		checkBoxShowPassword.setOpaque(false);
		checkBoxShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		checkBoxShowPassword.setBounds(37, 307, 121, 23);
		panel.add(checkBoxShowPassword);

		labelAceptar = new JLabel("ACCEPT");
		labelAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addUser();
					FrameAdministrador.getInstancie().actualizarTablaUsuarios();
					FrameAdministrador.getInstancie().setEnabled(true); // se vuelve a habilitar el frame administrador
					dispose(); // se cierra el frame actual
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				labelAceptar.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				labelAceptar.setBackground(SystemColor.info);
			}
		});
		labelAceptar.setOpaque(true);
		labelAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		labelAceptar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelAceptar.setBackground(SystemColor.info);
		labelAceptar.setBounds(37, 357, 138, 35);
		panel.add(labelAceptar);

		textFieldPassword = new JTextField();
		textFieldPassword.setOpaque(false);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBorder(null);
		textFieldPassword.setBounds(37, 273, 288, 20);
		panel.add(textFieldPassword);

		JLabel labelBarraPassword = new JLabel("________________________________________________");
		labelBarraPassword.setBounds(37, 282, 325, 21);
		panel.add(labelBarraPassword);

		lblCancelar = new JLabel("CANCEL");
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancelar.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancelar.setBackground(SystemColor.info);
			}
		});
		lblCancelar.setOpaque(true);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblCancelar.setBackground(SystemColor.info);
		lblCancelar.setBounds(187, 357, 138, 35);
		panel.add(lblCancelar);

		JLabel lblRegistro = new JLabel("To Register :");
		lblRegistro.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblRegistro.setBounds(26, 11, 152, 30);
		panel.add(lblRegistro);

		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(313, 0, 38, 38);
		panel.add(lblX);

		comboBoxRoles = new JComboBox <Rol>();
		this.llenarComboBoxRol();
		comboBoxRoles.setBounds(116, 111, 138, 22);
		panel.add(comboBoxRoles);
	}

	private void llenarComboBoxRol () {
		ArrayList<Rol> roles = Controller.getInstancie().getRoles();
		for (Rol r : roles) {
			comboBoxRoles.addItem(r);
		}
	}

	public void addUser () throws SQLException {
		User user = null; // usuario que se va a añadir

		if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.administrator)
			user = new Administrator(textFieldUserName.getText(), textFieldPassword.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo administrador administrador 
		else if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.dependent) 
			user = new Dependent(textFieldUserName.getText(), textFieldPassword.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo dependiente 
		else if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.manager) 
			user = new Manager(textFieldUserName.getText(), textFieldPassword.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo gerente
		else if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.packageDesigner) 
			user = new PackageDesigner(textFieldUserName.getText(), textFieldPassword.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo diseñador de paquete

		Controller.getInstancie().addUser(user); // se añade el usuario al sistema

	}
}
