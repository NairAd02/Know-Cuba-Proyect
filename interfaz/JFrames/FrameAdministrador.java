package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Controller;
import logica.Rol;
import logica.User;
import modelosTablas.ModeloTablaUsers;
import utils.ConnectionDataBase;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;


public class FrameAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FrameAdministrador framAdministrador;
	private JPanel contentPane;
	private JTable tableUsuario;
	private JLabel lblX;
	private JComboBox <Rol> comboBoxRoles;
	private int mouseX, mouseY;
	private JLabel lblDelete;
	private JLabel lblToRegister;
	private JTextField textFieldBuscador;
	private JComboBox<String> comboBoxConexion;
	private String searchName;
	private JScrollPane scrollPane;


	public static FrameAdministrador getInstancie () {
		if (framAdministrador == null)
			framAdministrador = new FrameAdministrador();

		return framAdministrador;
	}

	private FrameAdministrador() {
		searchName = "";
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 782);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableUsuario.clearSelection();
				actualizarEstadoButtonDelete();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		panel.setLayout(null);
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 990, 65);
		contentPane.add(panel);

		JLabel lblAdministrator = new JLabel("ADMINISTRATOR");
		lblAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrator.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblAdministrator.setBounds(59, 16, 181, 32);
		panel.add(lblAdministrator);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlText);
		separator.setBackground(SystemColor.controlText);
		separator.setBounds(245, 39, 745, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 39, 54, 9);
		panel.add(separator_1);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Controller.getInstancie().getUser().cerrarConexion();
					ConnectionDataBase.commit(); // confirmar las operaciones realizadas
				} catch (SQLException e1) {
					try {
						ConnectionDataBase.roolback(); // cancelar las operaciones realizadas
					} catch (SQLException e2) {
						
						e2.printStackTrace();
					} // confirmar las operaciones realizadas
					e1.printStackTrace();
				} // se cierra la sesión del usuario
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.black);
			}
		});
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
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(5, 150, 177));
		panel_1.setBounds(0, 65, 279, 717);
		contentPane.add(panel_1);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(SystemColor.controlText);
		separator_2_1.setBackground(SystemColor.controlText);
		separator_2_1.setBounds(277, 0, 2, 717);
		panel_1.add(separator_2_1);

		JLabel lblUserManagement = new JLabel("USER MANAGEMENT");
		lblUserManagement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUserManagement.setOpaque(true);
		lblUserManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserManagement.setForeground(Color.BLACK);
		lblUserManagement.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblUserManagement.setBackground(new Color(0, 183, 194));
		lblUserManagement.setBounds(0, 358, 279, 45);
		panel_1.add(lblUserManagement);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(FrameAdministrador.class.getResource("/images/Imagen5.png")));
		lblNewLabel_4.setBounds(0, 0, 279, 717);
		panel_1.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(278, 63, 712, 719);
		panel_2.setBackground(new Color(5, 150, 177));
		contentPane.add(panel_2);

		JPanel panelUsuario = new JPanel();
		panelUsuario.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelUsuario.setBackground(SystemColor.textHighlight);
		panelUsuario.setBounds(0, 110, 712, 609);
		panel_2.add(panelUsuario);
		panelUsuario.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setBackground(SystemColor.textHighlight);
		panelUsuario.add(scrollPane, BorderLayout.CENTER);

		tableUsuario = new JTable();
		tableUsuario.setForeground(SystemColor.textText);
		tableUsuario.setBackground(SystemColor.textHighlightText);
		tableUsuario.setGridColor(SystemColor.activeCaption);
		tableUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
			}
		});
		tableUsuario.setModel(new ModeloTablaUsers());
		scrollPane.setViewportView(tableUsuario);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 0, 712, 41);
		panel_2.add(panel_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(355, 0, 2, 41);
		panel_3.add(separator_1_1);

		lblToRegister = new JLabel("TO REGISTER");
		lblToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblToRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameRegistro frameRegistro = new FrameRegistro(); // se inicia el frame registro
				frameRegistro.setVisible(true);
				setEnabled(false); // se inhbilita el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblToRegister.setBackground(SystemColor.activeCaption);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblToRegister.setBackground(new Color(18, 95, 115));
			}
		});
		lblToRegister.setOpaque(true);
		lblToRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblToRegister.setForeground(SystemColor.info);
		lblToRegister.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblToRegister.setBackground(new Color(18, 95, 115));
		lblToRegister.setBounds(0, 0, 357, 41);
		panel_3.add(lblToRegister);

		lblDelete = new JLabel("DELETE");
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tableUsuario.getSelectedRowCount() != 0) {
					try {
						deleteElementsTable();
						ConnectionDataBase.commit(); // // se confirman las transacciones realizadas 
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
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDelete.setBackground(SystemColor.activeCaption);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblDelete.setBackground(new Color(18, 95, 115));
			}
		});
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setForeground(SystemColor.info);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblDelete.setBackground(new Color(18, 95, 115));
		lblDelete.setBounds(355, 0, 357, 41);
		panel_3.add(lblDelete);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setForeground(Color.BLACK);
		lblUsers.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblUsers.setBounds(118, 49, 68, 22);
		panel_2.add(lblUsers);

		JLabel lblNewLabel = new JLabel("Rol");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(358, 55, 39, 14);
		panel_2.add(lblNewLabel);

		comboBoxRoles = new JComboBox <Rol>();
		comboBoxRoles.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboBoxRol();
		comboBoxRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaUsuarios();
			}
		});

		comboBoxRoles.setBounds(318, 77, 119, 22);
		panel_2.add(comboBoxRoles);

		JLabel lblNewLabel_1 = new JLabel("State Connection");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(512, 52, 119, 14);
		panel_2.add(lblNewLabel_1);

		comboBoxConexion = new JComboBox<String>();
		comboBoxConexion.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboBoxConexion();
		comboBoxConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaUsuarios();
			}
		});
		comboBoxConexion.setBounds(512, 77, 119, 22);
		panel_2.add(comboBoxConexion);

		textFieldBuscador = new JTextField();
		textFieldBuscador.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				searchName = "";
				if(e.getKeyChar() != ''){ // si la key es distinta del boton delete
					searchName = textFieldBuscador.getText()+ e.getKeyChar();

				}
				else{
					searchName = textFieldBuscador.getText();
				}

				actualizarTablaUsuarios(); // se actualiza la informacion de los usuarios en la tabla usuario
			}
		});
		textFieldBuscador.setBounds(61, 79, 182, 20);
		panel_2.add(textFieldBuscador);
		textFieldBuscador.setColumns(10);

		this.actualizarTablaUsuarios();
		this.actualizarEstadoButtonDelete();
	}

	private void llenarComboBoxRol () {
		ArrayList<Rol> roles = Controller.getInstancie().getRoles();
		comboBoxRoles.addItem(new Rol("All"));
		for (Rol r : roles) {
			comboBoxRoles.addItem(r);
		}
	}

	private void llenarComboBoxConexion () {
		comboBoxConexion.addItem("All"); // item que define todos los tipos de connection
		comboBoxConexion.addItem("Connected"); // item que define el estado conectado
		comboBoxConexion.addItem("Disconected"); // item que define el estado conectado
	}

	public void actualizarTablaUsuarios () {
		if (((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				searchName.equalsIgnoreCase("")) // no tiene valor ningun filtro
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers());

		else if (!((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				searchName.equalsIgnoreCase("")) // tiene valor solo el filtro del rol
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(((Rol)comboBoxRoles.getSelectedItem()).getId()));

		else if (((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				!searchName.equalsIgnoreCase("")) // tiene valor solo el filtro de nombre 
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(searchName));

		else if (((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && !((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				searchName.equalsIgnoreCase("")) // tiene valor solo el filtro del estado de la conexion 
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("Connected")));

		else if (((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && !((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				!searchName.equalsIgnoreCase("")) // tiene valor  el filtro de nombre y el  filtro del estado de la coneccion
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(searchName, ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("Connected")));

		else if (!((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				!searchName.equalsIgnoreCase("")) // tiene valor  el filtro de nombre y el filtro rol
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(searchName, ((Rol)comboBoxRoles.getSelectedItem()).getId()));

		else if (!((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All") && !((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("All") && 
				searchName.equalsIgnoreCase("")) // tiene valor  el filtro del rol y el filtro estado de la conexion
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(((Rol)comboBoxRoles.getSelectedItem()).getId(), ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("Connected")));
		else // todos los filtros tienen valor
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(searchName, ((Rol)comboBoxRoles.getSelectedItem()).getId(), ((String) comboBoxConexion.getSelectedItem()).equalsIgnoreCase("Connected")));

	}

	private void actualizarTablaUsuarios(HashMap<Integer, ArrayList<User>> usuarios){
		ArrayList<Integer> keys = User.getKeys(); // se obtienen las cavles del mapa
		reiniciarTable(this.tableUsuario);

		for (Integer k : keys) {
			for (User user : usuarios.get(k)) {
				((ModeloTablaUsers) tableUsuario.getModel()).addElement(user);
			}
		}
	}

	private void actualizarTablaUsuarios(ArrayList<User> usuarios){
		reiniciarTable(this.tableUsuario);


		for (User user : usuarios) {
			((ModeloTablaUsers) tableUsuario.getModel()).addElement(user);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableUsuario.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			Controller.getInstancie().deleteUser( ((ModeloTablaUsers) tableUsuario.getModel()).deleteElement(rows[i] - i));
		}
		
		this.actualizarTablaUsuarios();

	}


	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaUsers) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtonDelete () {
		if (tableUsuario.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}
}
