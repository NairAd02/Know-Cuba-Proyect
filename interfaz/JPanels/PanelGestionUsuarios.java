package JPanels;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import JFrames.FrameAdvertencia;
import JFrames.FrameDecisor;
import JFrames.FramePrincipal;
import JFrames.FrameRegistro;
import logica.Controller;
import logica.Rol;
import logica.User;
import modelosTablas.ModeloTablaUsers;
import utils.ConnectionDataBase;
import utils.Semaphore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

public class PanelGestionUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTitle;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelTable;
	private JScrollPane scrollPane;
	private JTable tableUsuario;
	private JPanel panelFiltros;
	private JPanel panelBotones;
	private JLabel lblUsers;
	private JTextField textFieldBuscador;
	private JComboBox <Rol> comboBoxRoles;
	private JComboBox<String> comboBoxConexion;
	private JLabel lblToRegister, lblDelete;
	private String searchName;
	private JLabel lblUpdate;
	
	private class Eliminar extends Thread { // Hilo para la eliminacion

		public void run () {
			synchronized (Semaphore.samaphore) { 
				try {
					Semaphore.samaphore.wait(); // se duerme al hilo hasta esperar la confirmacion del usuario
					if (Controller.getInstancie().isConfirmacion()) { // si el usuario dió el consentimiento de realizar la operación
						deleteElements(); // se eliminan los elementos seleccionados
						Controller.getInstancie().setConfirmacion(false); // se establece el estado de la confirmación por defecto
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	private void crearFrameDecisor () {
		FrameDecisor frameDecisor = new FrameDecisor(FramePrincipal.getIntancie(), "Seguro que desea eliminar los usuarios seleccionados");
		frameDecisor.setVisible(true);
		FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
	}

	private void crearFrameNotificacion () {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia("Han sido elimanados correctamente los usuarios seleccionados");
		frameAdvertencia.setVisible(true);
	}


	public PanelGestionUsuarios() {
		this.searchName = "";
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableUsuario.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		setLayout(new BorderLayout(0, 0));

		panelTitle = new JPanel();
		panelTitle.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelTitle.setBackground(SystemColor.inactiveCaptionBorder);
		add(panelTitle, BorderLayout.NORTH);

		lblUsers = new JLabel("USERS");
		lblUsers.setBackground(SystemColor.inactiveCaptionBorder);
		lblUsers.setOpaque(true);
		lblUsers.setForeground(SystemColor.textText);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 32));
		panelTitle.add(lblUsers);

		panelContenedorTable = new JPanel();
		add(panelContenedorTable, BorderLayout.CENTER);
		panelContenedorTable.setLayout(new BorderLayout(0, 0));

		panelOpciones = new JPanel();
		panelOpciones.setBackground(new Color(18, 95, 115));
		panelContenedorTable.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(new BorderLayout(0, 0));

		panelFiltros = new JPanel();
		panelFiltros.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelFiltros, BorderLayout.WEST);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelBotones, BorderLayout.EAST);

		panelTable = new JPanel();
		panelContenedorTable.add(panelTable, BorderLayout.CENTER);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableUsuario = new JTable();
		tableUsuario.setRowHeight(30);
		tableUsuario.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tableUsuario.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		tableUsuario.getTableHeader().setForeground(Color.black);
		tableUsuario.getTableHeader().setBackground(SystemColor.black);
		tableUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableUsuario.setModel(new ModeloTablaUsers());
		scrollPane.setViewportView(tableUsuario);
		scrollPane.getViewport().setBackground(SystemColor.inactiveCaptionBorder);

		this.addBotones();
		this.actualizarEstadoButtons();
		this.actualizarTablaUsuarios(); // se actualiza la informacion de la tabla de usuarios

	}

	private void addBotones () {
		JLabel lblUsers = new JLabel("User name");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setForeground(SystemColor.textHighlightText);
		lblUsers.setFont(new Font("Dialog", Font.BOLD, 21));
		panelFiltros.add(lblUsers); 

		textFieldBuscador = new JTextField();
		textFieldBuscador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBuscador.setColumns(10);
		
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

		panelFiltros.add(textFieldBuscador);

		JLabel lblNewLabel = new JLabel("Rol");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 21));
		panelFiltros.add(lblNewLabel);

		comboBoxRoles = new JComboBox <Rol>();
		comboBoxRoles.setFont(new Font("Dialog", Font.PLAIN, 21));
		comboBoxRoles.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboBoxRol();
		comboBoxRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaUsuarios();
			}
		});

		panelFiltros.add(comboBoxRoles);

		JLabel lblNewLabel_1 = new JLabel("State Connection");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 21));
		panelFiltros.add(lblNewLabel_1);

		comboBoxConexion = new JComboBox<String>();
		comboBoxConexion.setFont(new Font("Dialog", Font.PLAIN, 21));
		comboBoxConexion.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboBoxConexion();
		comboBoxConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaUsuarios();
			}
		});

		panelFiltros.add(comboBoxConexion);

		lblToRegister = new JLabel("");
		lblToRegister.setIcon(new ImageIcon(PanelGestionUsuarios.class.getResource("/images/Plus.png")));
		lblToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblToRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameRegistro frameRegistro = new FrameRegistro(PanelGestionUsuarios.this, null); // se inicia el frame registro
				frameRegistro.setVisible(true);
				FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblToRegister.setBackground(SystemColor.activeCaptionBorder);
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
		panelBotones.add(lblToRegister);

		lblDelete = new JLabel("");
		lblDelete.setIcon(new ImageIcon(PanelGestionUsuarios.class.getResource("/images/Trash.png")));
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					Eliminar eliminarThread = new Eliminar(); // se crea el hilo para la eliminacion
					eliminarThread.start(); // se inicia la ejecución del hilo
					crearFrameDecisor(); // se crea el frame decisor, donde el usuario dará su confirmación
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblDelete.isEnabled())
				lblDelete.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblDelete.isEnabled())
				lblDelete.setBackground(new Color(18, 95, 115));
			}
		});
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setForeground(SystemColor.info);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblDelete.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblDelete);

		lblUpdate = new JLabel("");
		lblUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblUpdate.isEnabled()) {
				FrameRegistro frameRegistro = new FrameRegistro(PanelGestionUsuarios.this, ((ModeloTablaUsers) tableUsuario.getModel()).getElement(tableUsuario.getSelectedRow()) ); // se inicia el frame registro
				frameRegistro.setVisible(true);
				FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblUpdate.isEnabled())
					lblUpdate.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblUpdate.isEnabled())
					lblUpdate.setBackground(new Color(18, 95, 115));
			}
		});
		lblUpdate.setIcon(new ImageIcon(PanelGestionUsuarios.class.getResource("/images/Edit.png")));
		lblUpdate.setOpaque(true);
		lblUpdate.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblUpdate);
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
	
	private void deleteElements () {
		try {
			deleteElementsTable(); // se eliminan los elementos seleccionados
			ConnectionDataBase.commit(); // // se confirman las transacciones realizadas
			crearFrameNotificacion(); // se crea el frame que notifica que la operacion han sido efectuados con exito
			actualizarEstadoButtons(); // se actualiza el estado de los botones
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
			if (k != 1)
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

	public void actualizarEstadoButtons () {
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonUpdate();
	}

	private void actualizarEstadoButtonDelete () {
		if (tableUsuario.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}

	private void actualizarEstadoButtonUpdate () {
		if (tableUsuario.getSelectedRowCount() != 0)
			lblUpdate.setEnabled(true);
		else
			lblUpdate.setEnabled(false);
	}

}
