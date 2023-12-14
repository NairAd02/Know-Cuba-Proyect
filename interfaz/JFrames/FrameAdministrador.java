package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logica.Controller;
import logica.Rol;
import logica.User;
import modelosTablas.ModeloTablaUsers;
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

	
	public static FrameAdministrador getInstancie () {
		if (framAdministrador == null)
			framAdministrador = new FrameAdministrador();
		
		return framAdministrador;
	}

	private FrameAdministrador() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 782);
		contentPane = new JPanel();
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
				} catch (SQLException e1) {
					
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
		panelUsuario.setOpaque(false);
		panelUsuario.setBounds(10, 110, 692, 598);
		panel_2.add(panelUsuario);
		panelUsuario.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelUsuario.add(scrollPane, BorderLayout.CENTER);

		tableUsuario = new JTable();
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

		JLabel lblUsers = new JLabel("USERS");
		lblUsers.setForeground(SystemColor.info);
		lblUsers.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblUsers.setBounds(29, 69, 84, 30);
		panel_2.add(lblUsers);

		JLabel lblNewLabel = new JLabel("Rol");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(123, 65, 39, 14);
		panel_2.add(lblNewLabel);

		comboBoxRoles = new JComboBox <Rol>();
		this.llenarComboBoxRol();
		comboBoxRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaUsuarios();
			}
		});

		comboBoxRoles.setBounds(172, 62, 119, 22);
		panel_2.add(comboBoxRoles);

		this.actualizarTablaUsuarios(Controller.getInstancie().getUsers());
	}

	private void llenarComboBoxRol () {
		ArrayList<Rol> roles = Controller.getInstancie().getRoles();
		comboBoxRoles.addItem(new Rol("All"));
		for (Rol r : roles) {
			comboBoxRoles.addItem(r);
		}
	}
	
	public void actualizarTablaUsuarios () {
		if (!((Rol)comboBoxRoles.getSelectedItem()).getName().equalsIgnoreCase("All"))
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers(((Rol)comboBoxRoles.getSelectedItem()).getId()));
		else
			this.actualizarTablaUsuarios(Controller.getInstancie().getUsers());
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


	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((DefaultTableModel) table.getModel()).removeRow(i);
		}
	}
}
