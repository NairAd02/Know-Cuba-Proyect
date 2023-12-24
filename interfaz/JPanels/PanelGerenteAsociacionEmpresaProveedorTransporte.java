package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelosTablas.ModeloTablaTransportationProvider;
import utils.ConnectionDataBase;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import JFrames.FrameGerente;
import JFrames.FrameGerenteAsociacionEmpresaProveedorTransporte;
import logica.Controller;
import logica.Provider;
import logica.TransportationProvider;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;

public class PanelGerenteAsociacionEmpresaProveedorTransporte extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JTable tableTransportationProviders;
	private JTextField textFieldBuscador;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private String searchName;
	private JLabel lblShowVehicle;
	private JLabel lblName;
	private JPanel panelSuperior;
	private JPanel panelContenedorTable;
	private JPanel panelFiltros;
	private JPanel panelOpciones;
	private JPanel panelBotones;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorTransporte() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableTransportationProviders.clearSelection();
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		searchName = "";
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(0, 41, 712, 678);
		setLayout(new BorderLayout(0, 0));

		panelSuperior = new JPanel();
		panelSuperior.setBackground(SystemColor.inactiveCaptionBorder);
		panelSuperior.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitleSeccion = new JLabel("Transportation Provider");
		lblTitleSeccion.setOpaque(true);
		lblTitleSeccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSeccion.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitleSeccion.setBorder(null);
		lblTitleSeccion.setBackground(SystemColor.inactiveCaptionBorder);
		panelSuperior.add(lblTitleSeccion);

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







		this.addTable();
		this.addButtons();
		this.actualizarTablaTransportationProviders();
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();
	}

	private void addTable () {
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 94, 692, 573);
		panelContenedorTable.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableTransportationProviders = new JTable();
		tableTransportationProviders.setRowHeight(30);
		tableTransportationProviders.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tableTransportationProviders.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		tableTransportationProviders.setModel(new ModeloTablaTransportationProvider());
		tableTransportationProviders.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		tableTransportationProviders.getTableHeader().setForeground(Color.black);
		tableTransportationProviders.getTableHeader().setBackground(SystemColor.black);
		scrollPaneTable.setViewportView(tableTransportationProviders);
		scrollPaneTable.getViewport().setBackground(SystemColor.inactiveCaptionBorder);
		
	}

	private void addButtons () {
		lblDelete = new JLabel("");
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Trash.png")));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deleteElementsTable();
					ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos
				} catch (SQLException e1) {
					try {
						ConnectionDataBase.roolback();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} // se cancelan las operaciones realizadas a la base de datos
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		
				lblAnnadir = new JLabel("");
				lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Plus.png")));
				lblAnnadir.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						FrameGerenteAsociacionEmpresaProveedorTransporte frameTransportationProvider = new FrameGerenteAsociacionEmpresaProveedorTransporte(PanelGerenteAsociacionEmpresaProveedorTransporte.this, null);
						frameTransportationProvider.setVisible(true);
						FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
					}
					@Override
					public void mouseEntered(MouseEvent e) {

					}
					@Override
					public void mouseExited(MouseEvent e) {

					}
				});
				lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
				lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
				lblAnnadir.setBackground(SystemColor.info);
				lblAnnadir.setBounds(245, 19, 68, 52);
				panelBotones.add(lblAnnadir);



		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(405, 19, 67, 52);
		panelBotones.add(lblDelete);

		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(86, 34, 68, 22);
		panelFiltros.add(lblName);

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

				actualizarTablaTransportationProviders(); // se actualiza la informacion de los provedores de transporte en la tabla de provedores de transportes
			}
		});
		textFieldBuscador.setColumns(10);
		textFieldBuscador.setBounds(29, 64, 182, 20);
		panelFiltros.add(textFieldBuscador);

		lblShowVehicle = new JLabel("");
		lblShowVehicle.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Edit.png")));
		lblShowVehicle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblShowVehicle.isEnabled()) { // Si esta habilitado el button
					FrameGerenteAsociacionEmpresaProveedorTransporte frameTransportationProvider = new FrameGerenteAsociacionEmpresaProveedorTransporte(PanelGerenteAsociacionEmpresaProveedorTransporte.this, 
							((ModeloTablaTransportationProvider) tableTransportationProviders.getModel()).getElement(tableTransportationProviders.getSelectedRow()));
					frameTransportationProvider.setVisible(true);
					FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblShowVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowVehicle.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowVehicle.setBackground(SystemColor.info);
		lblShowVehicle.setBounds(564, 19, 67, 52);
		panelBotones.add(lblShowVehicle);
	}

	public void actualizarTablaTransportationProviders () {
		if (!searchName.equalsIgnoreCase(""))
			this.actualizarTablaTransportationProviders(Controller.getInstancie().getTouristAgency().getProviders(searchName, Provider.transportationProvider));
		else
			this.actualizarTablaTransportationProviders(Controller.getInstancie().getTouristAgency().getProviders(Provider.transportationProvider));
	}


	private void actualizarTablaTransportationProviders(ArrayList<Provider> transportationProvider){
		reiniciarTable(this.tableTransportationProviders);


		for (Provider prov : transportationProvider) {
			((ModeloTablaTransportationProvider) tableTransportationProviders.getModel()).addElement((TransportationProvider) prov);
		}
	}

	private void deleteElementsTable () throws SQLException {
		((ModeloTablaTransportationProvider) tableTransportationProviders.getModel()).deleteElements(this.tableTransportationProviders.getSelectedRows());
		this.actualizarTablaTransportationProviders();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportationProvider) table.getModel()).deleteElement(i);
		}

	}

	private void actualizarEstadoButtonDelete () {
		if (tableTransportationProviders.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}

	private void actualizarEstadoButtonShow () {
		if (tableTransportationProviders.getSelectedRowCount() == 1)
			lblShowVehicle.setEnabled(true);
		else
			lblShowVehicle.setEnabled(false);
	}
}
