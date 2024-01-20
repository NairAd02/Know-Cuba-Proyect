package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.PanelGerenteAsociacionEmpresaProveedorTransporte;
import logica.Controller;
import logica.TransportationProvider;
import logica.Vehicle;
import modelosTablas.ModeloTablaVehicles;
import utils.ConnectionDataBase;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class FrameGerenteAsociacionEmpresaProveedorTransporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldProvince;
	private JTable tableVehicles;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAdd;
	private PanelGerenteAsociacionEmpresaProveedorTransporte panelGerenteAsociacionEmpresaProveedorTransporte;
	private TransportationProvider transportationProvider;
	private JLabel lblX;
	private int mouseX, mouseY;
	private JLabel lblRestore;




	public TransportationProvider getTransportationProvider() {
		return transportationProvider;
	}

	public void setTransportationProvider(TransportationProvider transportationProvider) {
		this.transportationProvider = transportationProvider;
	}

	public FrameGerenteAsociacionEmpresaProveedorTransporte(PanelGerenteAsociacionEmpresaProveedorTransporte ps, TransportationProvider t) {
		this.panelGerenteAsociacionEmpresaProveedorTransporte = ps;
		this.transportationProvider = t;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 392);
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
				tableVehicles.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(18, 95, 115));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTransportationProvider = new JLabel("PROVEEDOR TRANSPORTE");
		lblTransportationProvider.setForeground(SystemColor.textHighlightText);
		lblTransportationProvider.setFont(new Font("Dialog", Font.BOLD, 21));
		lblTransportationProvider.setBounds(10, 11, 316, 30);
		contentPane.add(lblTransportationProvider);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (transportationProvider.getId() != -1) {
					try {
						ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
						transportationProvider.actualizarVehicles(); // se actualizan los datos del provedor de transporte para evitar inconsistencias
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
		lblX.setBounds(511, 0, 38, 38);
		contentPane.add(lblX);

		this.addSeccionName();
		this.addSeccionProvince();
		this.addButtonADD();

		JLabel lblVehicles = new JLabel("VEHICLES");
		lblVehicles.setForeground(SystemColor.textHighlightText);
		lblVehicles.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVehicles.setBounds(129, 130, 100, 23);
		contentPane.add(lblVehicles);



		JPanel panelVehicles = new JPanel();
		panelVehicles.setBounds(10, 164, 529, 134);
		contentPane.add(panelVehicles);
		panelVehicles.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelVehicles.add(scrollPane, BorderLayout.CENTER);

		tableVehicles = new JTable();
		tableVehicles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableVehicles.setModel(new ModeloTablaVehicles());
		scrollPane.setViewportView(tableVehicles);

		lblAnnadir = new JLabel("ADD");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo frameAddVehicle = new FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo(FrameGerenteAsociacionEmpresaProveedorTransporte.this);
				frameAddVehicle.setVisible(true);
				setEnabled(false); // se inhabilita el frame
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(259, 133, 71, 20);
		contentPane.add(lblAnnadir);

		lblEliminar = new JLabel("DELETE");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tableVehicles.getSelectedRowCount() != 0) {
					try {
						deleteElementsTable();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(349, 133, 67, 20);
		contentPane.add(lblEliminar);


		if (this.transportationProvider.getId() != -1) { // Update
			this.definirTextos();
			this.actualizarTablaVehicles();
			this.addLblRestore();		
		}
		else
			this.actualizarEstadoButtons();
	}

	private void addLblRestore () {
		lblRestore = new JLabel("Restore");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (transportationProvider.getId() != -1) {
					restoreInformation();
				}
			}
		});
		lblRestore.setForeground(SystemColor.textHighlightText);
		lblRestore.setFont(new Font("Dialog", Font.BOLD, 21));
		lblRestore.setBounds(326, 11, 80, 30);
		contentPane.add(lblRestore);
	}



	private void restoreInformation () {
		try {
			this.restoreVehicles();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.actualizarTablaVehicles();
		this.definirTextos();
	}

	private void definirTextos () {
		this.textFieldName.setText(this.transportationProvider.getName());
		this.textFieldProvince.setText(this.transportationProvider.getProvince());
	}

	private void restoreVehicles () throws SQLException {
		ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
		this.transportationProvider.actualizarVehicles(); // se actualiza la informacion de las actividades
	}

	private void addSeccionName () {
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(129, 52, 80, 23);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(219, 56, 197, 20);
		contentPane.add(textFieldName);
	}

	private void addSeccionProvince () {
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.textHighlightText);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvince.setBounds(129, 86, 109, 23);
		contentPane.add(lblProvince);

		textFieldProvince = new JTextField();
		textFieldProvince.setColumns(10);
		textFieldProvince.setBounds(258, 90, 158, 20);
		contentPane.add(textFieldProvince);
	}

	private void addButtonADD () {
		String nameButton = "";
		if (transportationProvider.getId() == -1)
			nameButton = "ADD";
		else
			nameButton = "CONFIRM";
		lblAdd = new JLabel(nameButton);
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (transportationProvider.getId() == -1) {
					if (verificarCampos()) {
						try {
							addTransportationProvider();
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							panelGerenteAsociacionEmpresaProveedorTransporte.actualizarTablaTransportationProviders(); // se actualiza la informacion de la tabla de provedores
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} 
							e1.printStackTrace();
						} // se añade el provedor de transporte
					}
				}
				else {
					if (verificarCampos()) {
						try {
							updateTransportationProvider(); // se actualiza la informacion del proveedor de transporte
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							panelGerenteAsociacionEmpresaProveedorTransporte.actualizarTablaTransportationProviders(); // se actualiza la informacion de la tabla de provedores
							cerrarFrame(); 
						} catch (SQLException e1) {

							e1.printStackTrace();
						} 

					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(157, 325, 235, 35);
		contentPane.add(lblAdd);
	}



	public void actualizarTablaVehicles () {	
		this.actualizarTablaVehicles(transportationProvider.getVehicles()); // se obtienen los vehiculos del provedor de transporte
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}


	private void actualizarTablaVehicles(ArrayList<Vehicle> vehicles){
		reiniciarTable(this.tableVehicles);


		for (Vehicle veh : vehicles) {
			((ModeloTablaVehicles) tableVehicles.getModel()).addElement(veh);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableVehicles.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (transportationProvider.getId() == -1)
				transportationProvider.deleteVehicleLogic(((ModeloTablaVehicles) tableVehicles.getModel()).getElement(rows[i])); // se elimina solo el la actividad de la logica del negocio
			else
				transportationProvider.deleteVehicle(((ModeloTablaVehicles) tableVehicles.getModel()).getElement(rows[i])); // se elimina la actividad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaVehicles();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaVehicles) table.getModel()).deleteElement(i);
		}
	}

	private boolean verificarCampos () {
		return (!this.textFieldName.getText().equalsIgnoreCase("") && !this.textFieldProvince.getText().equalsIgnoreCase("") && this.transportationProvider.cantVehicles() != 0);
	}


	private void addTransportationProvider () throws SQLException {
		Controller.getInstancie().getTouristAgency().addProvider(new TransportationProvider(textFieldName.getText(), textFieldProvince.getText(), 
				transportationProvider.getVehicles())); // se inserta el provedor de servicios a nivel de base de datos

	}

	private void updateTransportationProvider () throws SQLException {
		Controller.getInstancie().getTouristAgency().updateTransportationProvider(this.transportationProvider, this.textFieldName.getText(), this.textFieldProvince.getText());
	}

	private void cerrarFrame () {
		FramePrincipal.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
		dispose(); // se cierra el frame
	}

	private void actualizarEstadoButtons () {

		// Estado boton eliminar modalidades de hotel
		if (this.tableVehicles.getSelectedRowCount() != 0)
			this.lblEliminar.setEnabled(true);
		else
			this.lblEliminar.setEnabled(false);


	}

}
