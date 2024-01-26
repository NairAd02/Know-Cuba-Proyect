package JPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

import logica.CarrierContract;
import logica.EstablishedRoute;
import logica.Modality;
import logica.TransportModality;
import logica.Vehicle;
import modelosTablas.ModeloTablaTransportModalityEstablishedRoute;
import modelosTablas.ModeloTablaVehicles;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelSeccionVehicles extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblAtras;
	private JTextField textFieldBuscadorVehiclesDisponibles;
	private JTextField textFieldBuscadorVehiclesSeleccionados;
	private JPanel panelTablaVehiculosDisponibles;
	private JPanel panelVehiculosSeleccionados;
	private JTable tableVehiclesDisponibles;
	private JTable tableVehiclesSeleccionados;
	private AddTransportModalityOperations frameAddTransportModality;
	private TransportModality transportModality;
	private CarrierContract carrierContract;
	private JLabel lblAsiganar;
	private JLabel lblDesasignar;
	private String searchNameSeleccionados, searchNameDisponibles;



	public PanelSeccionVehicles(AddTransportModalityOperations a) {
		this.searchNameDisponibles = "";
		this.searchNameSeleccionados = "";
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableVehiclesDisponibles.clearSelection();
				tableVehiclesSeleccionados.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.frameAddTransportModality = a;
		this.transportModality = this.frameAddTransportModality.getTransporModality();
		this.carrierContract = this.frameAddTransportModality.getCarrierContract();
		setBounds(0, 0, 548, 414);
		setBackground(new Color(18, 95, 115));
		setLayout(null);

		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameAddTransportModality.cambioDePanel(frameAddTransportModality.getPanel()); // se regresa a la pantalla anterior
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAtras.setOpaque(true);
		lblAtras.setIcon(new ImageIcon(PanelSeccionVehicles.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(SystemColor.textHighlightText);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(new Color(18, 95, 115));
		lblAtras.setBounds(10, 11, 53, 43);
		add(lblAtras);

		panelTablaVehiculosDisponibles = new JPanel();
		panelTablaVehiculosDisponibles.setBounds(10, 113, 528, 107);
		add(panelTablaVehiculosDisponibles);
		panelTablaVehiculosDisponibles.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneDisponibles = new JScrollPane();
		panelTablaVehiculosDisponibles.add(scrollPaneDisponibles, BorderLayout.CENTER);

		tableVehiclesDisponibles = new JTable();
		tableVehiclesDisponibles.setRowHeight(30);
		tableVehiclesDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableVehiclesDisponibles.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableVehiclesDisponibles.getTableHeader().setForeground(Color.black);
		tableVehiclesDisponibles.getTableHeader().setBackground(SystemColor.black);
		tableVehiclesDisponibles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableVehiclesDisponibles.setModel(new ModeloTablaVehicles());
		scrollPaneDisponibles.setViewportView(tableVehiclesDisponibles);

		panelVehiculosSeleccionados = new JPanel();
		panelVehiculosSeleccionados.setBounds(10, 283, 528, 107);
		add(panelVehiculosSeleccionados);
		panelVehiculosSeleccionados.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneSeleccionados = new JScrollPane();
		panelVehiculosSeleccionados.add(scrollPaneSeleccionados, BorderLayout.CENTER);

		tableVehiclesSeleccionados = new JTable();
		tableVehiclesSeleccionados.setRowHeight(30);
		tableVehiclesSeleccionados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableVehiclesSeleccionados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableVehiclesSeleccionados.getTableHeader().setForeground(Color.black);
		tableVehiclesSeleccionados.getTableHeader().setBackground(SystemColor.black);
		tableVehiclesSeleccionados.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableVehiclesSeleccionados.setModel(new ModeloTablaVehicles());
		scrollPaneSeleccionados.setViewportView(tableVehiclesSeleccionados);

		lblDesasignar = new JLabel("");
		lblDesasignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDesasignar.isEnabled()) {
					try {
						deleteElementsTableSelecionados(); // se elimina el vehiculo de la modalidad de transporte
						actualizarTablas(); // se actualiza la informacion de las tablas 
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 

				}
			}
		});
		lblDesasignar.setIcon(new ImageIcon(PanelSeccionVehicles.class.getResource("/images/Trash.png")));
		lblDesasignar.setOpaque(true);
		lblDesasignar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesasignar.setForeground(SystemColor.textHighlightText);
		lblDesasignar.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblDesasignar.setBackground(new Color(18, 95, 115));
		lblDesasignar.setBounds(485, 231, 53, 43);
		add(lblDesasignar);

		lblAsiganar = new JLabel("");
		lblAsiganar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblAsiganar.isEnabled()) {
					try {
						deleteElementsTableDisponibles(); // se asignan los vehiculos seleccionados a la modalidad de transporte
						actualizarTablas(); // se actualiza la informacion de las tablas 
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		lblAsiganar.setIcon(new ImageIcon(PanelSeccionVehicles.class.getResource("/images/Plus.png")));
		lblAsiganar.setOpaque(true);
		lblAsiganar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsiganar.setForeground(SystemColor.textHighlightText);
		lblAsiganar.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAsiganar.setBackground(new Color(18, 95, 115));
		lblAsiganar.setBounds(485, 59, 53, 43);
		add(lblAsiganar);

		JLabel lblVehicles = new JLabel("VEHICLES");
		lblVehicles.setForeground(SystemColor.textHighlightText);
		lblVehicles.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblVehicles.setBounds(216, 11, 116, 30);
		add(lblVehicles);

		JLabel lblDisponible = new JLabel("Available");
		lblDisponible.setForeground(SystemColor.textHighlightText);
		lblDisponible.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDisponible.setBounds(10, 53, 107, 23);
		add(lblDisponible);

		JLabel lblSeleccionados = new JLabel("Assigned");
		lblSeleccionados.setForeground(SystemColor.textHighlightText);
		lblSeleccionados.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeleccionados.setBounds(10, 225, 136, 20);
		add(lblSeleccionados);

		JLabel lblName = new JLabel("Lock:");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(10, 79, 63, 23);
		add(lblName);

		JLabel lblName_1 = new JLabel("Lock:");
		lblName_1.setForeground(SystemColor.textHighlightText);
		lblName_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName_1.setBounds(10, 251, 63, 23);
		add(lblName_1);

		textFieldBuscadorVehiclesDisponibles = new JTextField();
		textFieldBuscadorVehiclesDisponibles.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				searchNameDisponibles = "";
				if(e.getKeyChar() != ''){ // si la key es distinta del boton delete
					searchNameDisponibles = textFieldBuscadorVehiclesDisponibles.getText()+ e.getKeyChar();

				}
				else{
					searchNameDisponibles = textFieldBuscadorVehiclesDisponibles.getText();
				}

				actualizarTablasVehiclesDesiponibles(); // se actualiza la informacion de la tabla de los vehiculos disponibles
			}
		});
		textFieldBuscadorVehiclesDisponibles.setBounds(69, 83, 107, 20);
		add(textFieldBuscadorVehiclesDisponibles);
		textFieldBuscadorVehiclesDisponibles.setColumns(10);

		textFieldBuscadorVehiclesSeleccionados = new JTextField();
		textFieldBuscadorVehiclesSeleccionados.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				searchNameSeleccionados = "";
				if(e.getKeyChar() != ''){ // si la key es distinta del boton delete
					searchNameSeleccionados = textFieldBuscadorVehiclesSeleccionados.getText()+ e.getKeyChar();

				}
				else{
					searchNameSeleccionados = textFieldBuscadorVehiclesSeleccionados.getText();
				}

				actualizarTablasVehiclesSeleccionados(); // se actualiza la informacion de la tabla de los vehiculos seleccionados
			}
		});
		textFieldBuscadorVehiclesSeleccionados.setColumns(10);
		textFieldBuscadorVehiclesSeleccionados.setBounds(69, 252, 107, 20);
		add(textFieldBuscadorVehiclesSeleccionados);

		this.actualizarTablas(); // se actualiza la informacion de las tablas 
	}

	// Metodos para la actualizacion de la informacion de las tablas

	// Metodos para la actualizacion de la informacion de la tabla Vehicles Seleccionados
	public void actualizarTablas() {
		this.actualizarTablasVehiclesDesiponibles(); // se actualiza la informacion de la tabla de los vehiculos disponibles
		this.actualizarTablasVehiclesSeleccionados(); // se actualiza la informacion de la tabla de los vehiculos seleccionados
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	private void actualizarTablasVehiclesDesiponibles () {
		this.actualizarTablaVehiclesDisponibles(this.carrierContract.getVehiclesProvider(this.definirLockDisponibles())); // se actualiza la tabla de los vehiculos disponibles con los vehiculos del proveedor de transporte
	}

	private void actualizarTablasVehiclesSeleccionados () {
		this.actualizarTablaVehiclesSeleccionados(this.transportModality.getVehicles(this.definirLockSeleccionados())); // se actualiza la tabla de los vehiculos seleccionados con los vehiculos de la modalidad de transporte
	}

	private String definirLockDisponibles () { // Metodo para definir el nombre introducido en el filto de los vehiculos disponibles
		String lock = searchNameDisponibles;

		if (lock.equalsIgnoreCase(""))
			lock = null;

		return lock;
	}

	private String definirLockSeleccionados () { // Metodo para definir el nombre introducido en el filto de los vehiculos seleccionados
		String lock = searchNameSeleccionados;

		if (lock.equalsIgnoreCase(""))
			lock = null;

		return lock;
	}


	private void actualizarTablaVehiclesSeleccionados(ArrayList<Vehicle> vehicle) {
		reiniciarTable(this.tableVehiclesSeleccionados);

		for (Vehicle veh : vehicle) {
			((ModeloTablaVehicles) tableVehiclesSeleccionados.getModel()).addElement(veh);
		}
	}

	public void deleteElementsTableSelecionados() throws SQLException {
		int[] rows = tableVehiclesSeleccionados.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.transportModality.getId() == -1)
				this.transportModality.deleteVehicleLogic(((ModeloTablaVehicles) tableVehiclesSeleccionados.getModel()).getElement(rows[i])); // se elimina el vehiculo solo de la logica del negocio
			else
				this.transportModality.deleteVehicle(((ModeloTablaVehicles) tableVehiclesSeleccionados.getModel()).getElement(rows[i])); // se elimina el vehiculo de la logica del negocio y de la base de datos
		}

	}

	// Fin de Metodos para la actualizacion de la informacion de la tabla Vehicles Seleccionados


	// Metodos para la actualizacion de la informacion de la tabla Vehicles Disponibles


	private void actualizarTablaVehiclesDisponibles(ArrayList<Vehicle> vehicle) {
		reiniciarTable(this.tableVehiclesDisponibles);

		for (Vehicle veh : vehicle) {
			if (!this.transportModality.verificarExistenciaDeVehicle(veh)) // si el vehiculo no se encuentra en a lista de vehiculos de la modalidad de transporte
				((ModeloTablaVehicles) tableVehiclesDisponibles.getModel()).addElement(veh);
		}
	}

	public void deleteElementsTableDisponibles() throws SQLException {
		int[] rows = tableVehiclesDisponibles.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.transportModality.getId() == -1)
				this.transportModality.addVehicleLogic(((ModeloTablaVehicles) tableVehiclesDisponibles.getModel()).getElement(rows[i])); // se añade el vehiculo solo de la logica del negocio
			else
				this.transportModality.addVehicle(((ModeloTablaVehicles) tableVehiclesDisponibles.getModel()).getElement(rows[i])); // se añade el vehiculo de la logica del negocio y de la base de datos
		}

	}

	// Fin de Metodos para la actualizacion de la informacion de la tabla Vehicles Disponibles



	private void reiniciarTable(JTable table) {

		for (int i = 0; table.getRowCount() != 0; i++) {
			i = 0;
			((ModeloTablaVehicles) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtons() {
		if (this.tableVehiclesDisponibles.getSelectedRowCount() != 0)
			lblAsiganar.setEnabled(true);
		else
			lblAsiganar.setEnabled(false);

		if (this.tableVehiclesSeleccionados.getSelectedRowCount() != 0)
			lblDesasignar.setEnabled(true);
		else
			lblDesasignar.setEnabled(false);

	}
	// Fin de Metodos para la actualizacion de la informacion de la tabla
}
