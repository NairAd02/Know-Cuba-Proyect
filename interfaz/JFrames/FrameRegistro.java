package JFrames;



import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import JPanels.PanelGestionUsuarios;
import logica.Administrator;
import logica.Controller;
import logica.Dependent;
import logica.Manager;
import logica.PackageDesigner;
import logica.Rol;
import logica.User;
import utils.ConnectionDataBase;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;


public class FrameRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JLabel labelAceptar;
	private JLabel lblCancelar;
	private JComboBox <Rol> comboBoxRoles;
	private JLabel lblX;
	private int mouseX, mouseY;
	private JLabel lblErrorCampos;
	private PanelGestionUsuarios panelGestionUsuarios;
	private User user;
	private JLabel lblTitleFrame;
	private JPanel panel;

	private void crearFrameNotificacion (String mensaje) {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia(mensaje);
		frameAdvertencia.setVisible(true);
	}

	public FrameRegistro(PanelGestionUsuarios p, User u) {
		this.user = u;
		this.panelGestionUsuarios = p;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 393);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 371, 393);
		panel.setBackground(new Color(18, 95, 115));
		contentPane.add(panel);
		panel.setLayout(null);

		this.addLblTitleFrame();

		JLabel lblRole = new JLabel("");
		lblRole.setIcon(new ImageIcon(FrameRegistro.class.getResource("/images/Add User Male.png")));
		lblRole.setForeground(SystemColor.info);
		lblRole.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblRole.setBounds(159, 98, 53, 56);
		panel.add(lblRole);

		JLabel labelUserName = new JLabel("Username");
		labelUserName.setForeground(SystemColor.textHighlightText);
		labelUserName.setFont(new Font("Dialog", Font.BOLD, 16));
		labelUserName.setBounds(37, 208, 90, 21);
		panel.add(labelUserName);

		textFieldUserName = new JTextField();
		textFieldUserName.setForeground(SystemColor.textHighlightText);
		textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUserName.setOpaque(false);
		textFieldUserName.setColumns(10);
		textFieldUserName.setBorder(null);
		textFieldUserName.setBounds(37, 230, 288, 20);
		panel.add(textFieldUserName);

		JLabel labelBarraUserName = new JLabel("________________________________________________");
		labelBarraUserName.setBounds(37, 240, 325, 21);
		panel.add(labelBarraUserName);

		labelAceptar = new JLabel("ACCEPT");
		labelAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) { // si los campos son validos
					if (user == null) { // add
						try {
							addUser();	
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
								crearFrameNotificacion("Ha ocurrido un error durante la insercción");	
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							e1.printStackTrace();
						}
					}
					else { // update
						try {
							updateUser();
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback();
								crearFrameNotificacion("Ha ocurrido un error durante la actualización"); // se cancelan las transacciones realizadas	 
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} 

							e1.printStackTrace();
						}
					}
				}
				else
					lblErrorCampos.setVisible(true);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				labelAceptar.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblErrorCampos.setVisible(false);
				labelAceptar.setBackground(SystemColor.info);
			}
		});
		labelAceptar.setOpaque(true);
		labelAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		labelAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		labelAceptar.setBackground(SystemColor.info);
		labelAceptar.setBounds(37, 324, 138, 35);
		panel.add(labelAceptar);

		lblCancelar = new JLabel("CANCEL");
		lblCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarFrame();
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
		lblCancelar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCancelar.setBackground(SystemColor.info);
		lblCancelar.setBounds(187, 324, 138, 35);
		panel.add(lblCancelar);

		if (true) // add
			this.addComboBoxRoles();

		lblErrorCampos = new JLabel("Obligatory Field");
		lblErrorCampos.setVisible(false);
		lblErrorCampos.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorCampos.setForeground(SystemColor.red);
		lblErrorCampos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblErrorCampos.setBounds(37, 281, 288, 21);
		panel.add(lblErrorCampos);

		lblX = new JLabel("X");
		lblX.setBounds(332, 0, 39, 40);
		panel.add(lblX);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarFrame();
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



		if (user != null) // update
			this.definirTextos();
	}

	private void addLblTitleFrame () {
		String text = "";

		if (user == null) // add
			text = "To Register";
		else // update
			text = "Update User: " + user.getUserName();

		lblTitleFrame = new JLabel(text);
		lblTitleFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleFrame.setForeground(SystemColor.textHighlightText);
		lblTitleFrame.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTitleFrame.setBounds(10, 46, 352, 30);
		panel.add(lblTitleFrame);
	}

	private void definirTextos () {
		this.textFieldUserName.setText(user.getUserName());
	}



	private void addComboBoxRoles () {
		comboBoxRoles = new JComboBox <Rol>();
		comboBoxRoles.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		this.llenarComboBoxRol();
		comboBoxRoles.setBounds(116, 165, 138, 21);
		panel.add(comboBoxRoles);
	}

	private boolean verificarCampos () {
		return (!this.textFieldUserName.getText().equalsIgnoreCase(""));
	}

	private void llenarComboBoxRol () {
		ArrayList<Rol> roles = Controller.getInstancie().getRoles();
		for (Rol r : roles) {
			comboBoxRoles.addItem(r);
		}
	}

	private void cerrarFrame() {
		FramePrincipal.getIntancie().setEnabled(true); // se vuelve a habilitar el frame administrador
		dispose(); // se cierra el frame actual
	}

	private void addUser () throws SQLException {
		User user = null; // usuario que se va a añadir


		if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.dependent) 
			user = new Dependent(textFieldUserName.getText(), textFieldUserName.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo dependiente 
		else if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.manager) 
			user = new Manager(textFieldUserName.getText(), textFieldUserName.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo gerente
		else if (((Rol)comboBoxRoles.getSelectedItem()).getId() == User.packageDesigner) 
			user = new PackageDesigner(textFieldUserName.getText(), textFieldUserName.getText(), (Rol) comboBoxRoles.getSelectedItem()); // se construye un usuario tipo diseñador de paquete

		Controller.getInstancie().addUser(user); // se añade el usuario al sistema

		ConnectionDataBase.commit(); // se confirman las transacciones realizadas 
		crearFrameNotificacion("Se a ingresado corrertamente el usuario " + user.getUserName());	
		panelGestionUsuarios.actualizarTablaUsuarios();

	}

	private void updateUser () throws SQLException {
		Controller.getInstancie().updateUser(user, this.textFieldUserName.getText());
		ConnectionDataBase.commit(); // se confirman las transacciones realizadas 
		crearFrameNotificacion("Se a actualizado corrertamente el usuario");	
		panelGestionUsuarios.actualizarTablaUsuarios();
	}
}
