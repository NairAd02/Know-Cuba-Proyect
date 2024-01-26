package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelosTablas.ModeloTablaAccommodationProvider;
import modelosTablas.ModeloTablaServiceProvider;
import utils.ConnectionDataBase;
import utils.Semaphore;
import javax.swing.SwingConstants;
import java.awt.Font;
import JFrames.FrameAdvertencia;
import JFrames.FrameDecisor;
import JFrames.FrameGerenteAsociacionEmpresaProveedorServicio;
import JFrames.FramePrincipal;
import logica.Controller;
import logica.Provider;
import logica.ServiceProvider;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class PanelGerenteAsociacionEmpresaProveedorServicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable tableServiceProviders;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private JTextField textFieldBuscadorName;
	private String searchName;
	private JLabel lblShowActivities;
	private JLabel lblName;
	private JPanel panelSuperior;
	private JLabel lblTitleSeccion;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelFiltros;
	private JPanel panelBotones;

	/**
	 * Create the panel.
	 */

	private class Eliminar extends Thread { // se crea un hilo para realizar la operacion de elimninacion

		public void run () {
			synchronized (Semaphore.samaphore) {
				try {
					Semaphore.samaphore.wait(); // se duerme al hilo hasta esperar la confirmacion del usuario
					if (Controller.getInstancie().isConfirmacion()) { // si el usuario dió el consentimiento de realizar la operación
						deleteElements(); // se eliminan los elementos seleccionados
						actualizarTablaServicieProviders(); // se actualiza la tabla de los proveedores de servicio
						Controller.getInstancie().setConfirmacion(false); // se establece el estado de la confirmación por defecto			
					}
				} catch (InterruptedException e) {

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
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia("Han sido elimanado correctamente los proveedores de servicio");
		frameAdvertencia.setVisible(true);
	}

	public PanelGerenteAsociacionEmpresaProveedorServicio() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableServiceProviders.clearSelection();
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

		panelTable = new JPanel();
		panelTable.setOpaque(false);

		panelTable.setLayout(new BorderLayout(0, 0));
		panelContenedorTable.add(panelTable, BorderLayout.CENTER);
		scrollPaneTable = new JScrollPane();

		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableServiceProviders = new JTable();
		tableServiceProviders.setModel(new ModeloTablaServiceProvider());
		tableServiceProviders.setRowHeight(30);
		tableServiceProviders.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableServiceProviders.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableServiceProviders.getTableHeader().setForeground(Color.black);
		tableServiceProviders.getTableHeader().setBackground(SystemColor.black);
		tableServiceProviders.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		
		scrollPaneTable.setViewportView(tableServiceProviders);
		scrollPaneTable.getViewport().setBackground(SystemColor.inactiveCaptionBorder);
		panelOpciones = new JPanel();
		panelOpciones.setBackground(new Color(18, 95, 115));
		panelContenedorTable.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(new BorderLayout(0, 0));

		panelFiltros = new JPanel();
		panelFiltros.setBackground(new Color(18, 95, 115));
		FlowLayout flowLayout = (FlowLayout) panelFiltros.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelOpciones.add(panelFiltros, BorderLayout.WEST);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelBotones, BorderLayout.EAST);

		panelSuperior = new JPanel();
		panelSuperior.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelSuperior.setBackground(SystemColor.inactiveCaptionBorder);
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));



		lblTitleSeccion = new JLabel("Services Providers");
		lblTitleSeccion.setBorder(null);
		lblTitleSeccion.setBackground(SystemColor.inactiveCaptionBorder);
		lblTitleSeccion.setOpaque(true);
		lblTitleSeccion.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitleSeccion.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblTitleSeccion);



		this.componentes();
		this.actualizarTablaServicieProviders();
		
	}

	private void componentes () {
		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(86, 34, 68, 22);
		panelFiltros.add(lblName);

		textFieldBuscadorName = new JTextField();
		textFieldBuscadorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBuscadorName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				searchName = "";
				if(e.getKeyChar() != ''){ // si la key es distinta del boton delete
					searchName = textFieldBuscadorName.getText()+ e.getKeyChar();

				}
				else{
					searchName = textFieldBuscadorName.getText();
				}

				actualizarTablaServicieProviders(); // se actualiza la informacion de los provedores de serivicios en la tabla de servicios
			}
		});
		textFieldBuscadorName.setColumns(10);
		textFieldBuscadorName.setBounds(29, 64, 182, 20);
		panelFiltros.add(textFieldBuscadorName);

		lblAnnadir = new JLabel("");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorServicio frameAddProvedorServicio = new FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio.this, new ServiceProvider()); // se manda por parametro null ya que se desea adicionar un provedor de servicios
				frameAddProvedorServicio.setVisible(true);
				FramePrincipal.getIntancie().setEnabled(false); // se inabilita el frame principal
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
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Plus.png")));
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(245, 19, 68, 52);
		panelBotones.add(lblAnnadir);

		lblDelete = new JLabel("");
		lblDelete.setOpaque(true);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Trash.png")));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					Eliminar eliminarThread = new Eliminar(); // se crea el hilo para la eliminacion
					eliminarThread.start(); // se ejecuta el hilo
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

		lblShowActivities = new JLabel("");
		lblShowActivities.setOpaque(true);
		lblShowActivities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowActivities.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Edit.png")));
		lblShowActivities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblShowActivities.isEnabled()) {
					FrameGerenteAsociacionEmpresaProveedorServicio frameAddProvedorServicio = new FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio.this, 
							((ModeloTablaServiceProvider)	tableServiceProviders.getModel()).getElement(tableServiceProviders.getSelectedRow())); // se manda por parametro null ya que se desea adicionar un provedor de servicios
					frameAddProvedorServicio.setVisible(true);
					FramePrincipal.getIntancie().setEnabled(false); // se inabilita el frame principal
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblShowActivities.isEnabled()) 
					lblShowActivities.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblShowActivities.isEnabled()) 
				lblShowActivities.setBackground(new Color(18, 95, 115));
			}
		});
		lblShowActivities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowActivities.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowActivities.setBackground(new Color(18, 95, 115));
		lblShowActivities.setBounds(564, 19, 67, 52);
		panelBotones.add(lblShowActivities);

	}

	public void actualizarTablaServicieProviders () {
		if (!searchName.equalsIgnoreCase(""))
			this.actualizarTablaServicieProviders(Controller.getInstancie().getTouristAgency().getProviders(searchName, Provider.serviceProvider));
		else
			this.actualizarTablaServicieProviders(Controller.getInstancie().getTouristAgency().getProviders(Provider.serviceProvider));
		
		this.actualizarEstadoButtons();
	}

	private void deleteElements () {
		try {
			deleteElementsTable(); // se eliminan los elementos seleccionados
			ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos
			crearFrameNotificacion(); // se crea el frame que notifica que la operacion han sido efectuados con exito
			this.actualizarTablaServicieProviders(); // se actualiza la tabla de los proveedores de servicio
		} catch (SQLException e1) {
			try {
				ConnectionDataBase.roolback();  // se cancelan las operaciones realizadas a la base de datos
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			e1.printStackTrace();
		} 
	}


	private void actualizarTablaServicieProviders(ArrayList<Provider> providers){
		reiniciarTable(this.tableServiceProviders);


		for (Provider serv : providers) {
			((ModeloTablaServiceProvider) tableServiceProviders.getModel()).addElement((ServiceProvider) serv);
		}
	}

	public void deleteElementsTable () throws SQLException {
		int[] rows = this.tableServiceProviders.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {	
			Controller.getInstancie().getTouristAgency().deleteProvider(((ModeloTablaServiceProvider) tableServiceProviders.getModel()).getElement(rows[i]));// se eliminan los Provedores de servicios seleccionados de la base de datos	
		}
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaServiceProvider) table.getModel()).deleteElement(i);
		}
	}
	
	public void actualizarEstadoButtons () {
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();
	}

	private void actualizarEstadoButtonDelete () {
		if (tableServiceProviders.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}

	private void actualizarEstadoButtonShow () {
		if (tableServiceProviders.getSelectedRowCount() == 1)
			lblShowActivities.setEnabled(true);
		else
			lblShowActivities.setEnabled(false);
	}
}
