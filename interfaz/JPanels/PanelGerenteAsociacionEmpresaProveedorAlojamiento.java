package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelosTablas.ModeloTablaAccommodationProvider;
import utils.ConnectionDataBase;
import utils.Semaphore;
import JFrames.FrameAdvertencia;
import JFrames.FrameDecisor;
import JFrames.FrameGerenteAsociacionEmpresaProveedorAlojamiento;
import JFrames.FramePrincipal;
import logica.Controller;
import logica.Hotel;
import logica.Provider;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import java.awt.Cursor;

public class PanelGerenteAsociacionEmpresaProveedorAlojamiento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JPanel panelTable;
	private JTable tableAccommodationProvider;
	private JTextField textFieldBuscador;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private JLabel lblShowPlans;
	private String searchName;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelBotones;
	private JPanel panelFiltros;
	private JPanel panelSuperior;
	private JLabel lblTitleSeccion;

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
		FrameDecisor frameDecisor = new FrameDecisor(FramePrincipal.getIntancie(), "Seguro que desea eliminar los proveedores seleccionados");
		frameDecisor.setVisible(true);
		FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
	}

	private void crearFrameNotificacion () {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia("Han sido elimanado correctamente los proveedores de alojamiento");
		frameAdvertencia.setVisible(true);
	}
	
	public PanelGerenteAsociacionEmpresaProveedorAlojamiento() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAccommodationProvider.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		searchName = "";
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(0, 41, 712, 678);
		setLayout(new BorderLayout(0, 0));

		panelContenedorTable = new JPanel();
		add(panelContenedorTable, BorderLayout.CENTER);
		panelContenedorTable.setLayout(new BorderLayout(0, 0));

		panelOpciones = new JPanel();
		
		panelContenedorTable.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(new BorderLayout(0, 0));
		panelOpciones.setBackground(new Color(18, 95, 115));
		
		panelFiltros = new JPanel();
		panelFiltros.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelFiltros, BorderLayout.WEST);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelBotones, BorderLayout.EAST);
		
		panelSuperior = new JPanel();
		panelSuperior.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelSuperior.setBackground(SystemColor.inactiveCaptionBorder);
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblTitleSeccion = new JLabel("Accommodation Provider");
		lblTitleSeccion.setOpaque(true);
		lblTitleSeccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSeccion.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitleSeccion.setBorder(null);
		lblTitleSeccion.setBackground(SystemColor.inactiveCaptionBorder);
		panelSuperior.add(lblTitleSeccion);


		this.addTable();
		this.addButtons();



		this.actualizarTablaAccommodationProviders();
		

	}

	public void actualizarTablaAccommodationProviders () {
		if (!searchName.equalsIgnoreCase(""))
			this.actualizarTablaAccommodationProviders(Controller.getInstancie().getTouristAgency().getProviders(searchName, Provider.accommodationProvider));
		else
			this.actualizarTablaAccommodationProviders(Controller.getInstancie().getTouristAgency().getProviders(Provider.accommodationProvider));
		
		this.actualizarEstadoButtons();
	}


	private void actualizarTablaAccommodationProviders(ArrayList<Provider> accommodationProviders){
		reiniciarTable(this.tableAccommodationProvider);


		for (Provider accprov : accommodationProviders) {
			((ModeloTablaAccommodationProvider) tableAccommodationProvider.getModel()).addElement((Hotel) accprov);
		}
	}
	
	private void deleteElements () {
		try {
			deleteElementsTable();
			ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos
			crearFrameNotificacion(); // se crea el frame que notifica al usuario que las operaciones fuerón realizadas con éxito
			this.actualizarTablaAccommodationProviders(); // se actualiza la informacion de la tabla de los proveedores de servicio
		} catch (SQLException e1) {
			try {
				ConnectionDataBase.roolback(); // se confirman las operaciones realizadas a la base de datos
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
			e1.printStackTrace();
		}
	}

	public void deleteElementsTable () throws SQLException {
		int[] rows = this.tableAccommodationProvider.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {	
			Controller.getInstancie().getTouristAgency().deleteProvider(((ModeloTablaAccommodationProvider) tableAccommodationProvider.getModel()).getElement(rows[i]));// se eliminan los Provedores de alojamiento seleccionados de la base de datos	
		}
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaAccommodationProvider) table.getModel()).deleteElement(i);
		}

	}
	
	public void actualizarEstadoButtons () {
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();
	}

	private void actualizarEstadoButtonDelete () {
		if (tableAccommodationProvider.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}

	private void actualizarEstadoButtonShow () {
		if (tableAccommodationProvider.getSelectedRowCount() == 1)
			lblShowPlans.setEnabled(true);
		else
			lblShowPlans.setEnabled(false);
	}

	private void addTable () {
		panelTable = new JPanel();
		panelTable.setBounds(10, 93, 692, 574);
		panelContenedorTable.add(panelTable, BorderLayout.CENTER);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableAccommodationProvider = new JTable();
		tableAccommodationProvider.setRowHeight(30);
		tableAccommodationProvider.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tableAccommodationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		tableAccommodationProvider.setModel(new ModeloTablaAccommodationProvider());
		tableAccommodationProvider.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		tableAccommodationProvider.getTableHeader().setForeground(Color.black);
		tableAccommodationProvider.getTableHeader().setBackground(SystemColor.black);
		scrollPaneTable.setViewportView(tableAccommodationProvider);
		scrollPaneTable.getViewport().setBackground(SystemColor.inactiveCaptionBorder);
	}

	private void addButtons () {

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(86, 34, 68, 22);
		panelFiltros.add(lblName);
		
				lblAnnadir = new JLabel("");
				lblAnnadir.setOpaque(true);
				lblAnnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Plus.png")));
				lblAnnadir.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						FrameGerenteAsociacionEmpresaProveedorAlojamiento frameAccommodationProvider = new FrameGerenteAsociacionEmpresaProveedorAlojamiento(PanelGerenteAsociacionEmpresaProveedorAlojamiento.this , new Hotel());
						frameAccommodationProvider.setVisible(true);
						FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						lblAnnadir.setBackground(SystemColor.activeCaptionBorder);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						lblAnnadir.setBackground(new Color(18, 95, 115));
					}
				});
				lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
				lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
				lblAnnadir.setBackground(new Color(18, 95, 115));
				lblAnnadir.setBounds(245, 19, 68, 52);
				panelBotones.add(lblAnnadir);

		lblDelete = new JLabel("");
		lblDelete.setOpaque(true);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Trash.png")));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					Eliminar eliminar = new Eliminar(); // se crea el nuevo hilo
					eliminar.start(); // se ejecuta el nuevo hilo
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
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(new Color(18, 95, 115));
		lblDelete.setBounds(405, 19, 67, 52);
		panelBotones.add(lblDelete);
		lblShowPlans = new JLabel("");
		lblShowPlans.setOpaque(true);
		lblShowPlans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowPlans.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Edit.png")));
		lblShowPlans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblShowPlans.isEnabled()) {
					FrameGerenteAsociacionEmpresaProveedorAlojamiento frameAlojamiento = new FrameGerenteAsociacionEmpresaProveedorAlojamiento(PanelGerenteAsociacionEmpresaProveedorAlojamiento.this, 
							((ModeloTablaAccommodationProvider)   tableAccommodationProvider.getModel()).getElement(tableAccommodationProvider.getSelectedRow()));
					frameAlojamiento.setVisible(true);
					FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblShowPlans.isEnabled())
				lblShowPlans.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblShowPlans.isEnabled())
				lblShowPlans.setBackground(new Color(18, 95, 115));
			}
		});
		lblShowPlans.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlans.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowPlans.setBackground(new Color(18, 95, 115));
		lblShowPlans.setBounds(564, 19, 67, 52);
		panelBotones.add(lblShowPlans);

		textFieldBuscador = new JTextField();
		textFieldBuscador.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

				actualizarTablaAccommodationProviders(); // se actualiza la informacion de los provedores de transporte en la tabla de provedores de transportes
			}
		});
		textFieldBuscador.setColumns(10);
		panelFiltros.add(textFieldBuscador);
	}
}
