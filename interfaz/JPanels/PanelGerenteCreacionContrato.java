package JPanels;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import JFrames.FrameGerente;
import JFrames.FrameGerenteCreacionContratoAlojamiento;
import JFrames.FrameGerenteCreacionContratoServivio;
import JFrames.FrameGerenteCreacionContratoTransporte;
import logica.AccommodationContract;
import logica.CarrierContract;
import logica.Contract;
import logica.Controller;
import logica.Provider;
import logica.ServiceContract;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelosTablas.ModeloTablaContract;
import utils.ConnectionDataBase;
import utils.ProviderAux;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.border.LineBorder;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;


public class PanelGerenteCreacionContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable tableContracts;
	private JLabel lblAddServiceContract;
	private JLabel lblAddTransportationContract;
	private JLabel lblAddAccommodationContract;
	private JLabel lblDelete;
	private JComboBox<String> comboBoxTypeOfContrac;
	private JComboBox<String> comboBoxState;
	private JComboBox<Provider> comboBoxProvider;
	private JLabel lblNewLabel;
	private JLabel lblUpdate;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelTitle;
	private JLabel lblNewLabel_2;
	private JPanel panelFiltros;
	private JPanel panelBotones;

	/**
	 * Create the panel.
	 */
	public PanelGerenteCreacionContrato() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(278, 63, 712, 719);
		setLayout(new BorderLayout(0, 0));
		panelTitle = new JPanel();
		panelTitle.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelTitle.setBackground(SystemColor.inactiveCaptionBorder);
		add(panelTitle, BorderLayout.NORTH);
		
		lblNewLabel_2 = new JLabel("Contracts");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 32));
		panelTitle.add(lblNewLabel_2);

		

		panelContenedorTable = new JPanel();
		panelContenedorTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableContracts.clearSelection();
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonUpdate();
			}
		});
		panelContenedorTable.setBackground(new Color(5, 150, 177));
		add(panelContenedorTable);
		panelContenedorTable.setLayout(new BorderLayout(0, 0));

		panelTable = new JPanel();
		panelContenedorTable.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableContracts = new JTable();
		tableContracts.setRowHeight(30);
		tableContracts.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tableContracts.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		tableContracts.getTableHeader().setForeground(Color.black);
		tableContracts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonUpdate();
			}
		});
		tableContracts.setModel(new ModeloTablaContract());
		scrollPaneTable.setViewportView(tableContracts);
		
		panelOpciones = new JPanel();
		panelOpciones.setBackground(new Color(18, 95, 115));
		panelContenedorTable.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(new BorderLayout(0, 0));
		
		panelFiltros = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFiltros.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelFiltros.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelFiltros, BorderLayout.CENTER);
		
		panelBotones = new JPanel();
		panelBotones.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelBotones.setBackground(SystemColor.inactiveCaptionBorder);
		panelOpciones.add(panelBotones, BorderLayout.NORTH);

		this.addFiltres();
		this.addButtons();
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonUpdate();
		this.actualizarTablaContracts(); // se actualiza la informacion de la tabla de contratos
	}

	private void llenarComboBoxProviders () {
		HashMap<Integer, ArrayList<Provider>> providers = Controller.getInstancie().getTouristAgency().getProviders(); // se obtienen todos los provedores del sistema
		ArrayList<Integer> keys = Provider.getKeys(); // se obtienen las llaves del mapa

		comboBoxProvider.addItem(new ProviderAux("All"));
		// Se itera el mapa
		for (Integer i : keys) {
			for (Provider provider : providers.get(i)) { // se itera la lista correspondiente a cada clave del mapa
				comboBoxProvider.addItem(provider);
			}
		}

	}

	private void llenarComboboxStateContract () {
		comboBoxState.addItem("All");
		comboBoxState.addItem("Close");
		comboBoxState.addItem("In Procesing");
	}


	private void llenarComboBoxTypeOfContract () {
		comboBoxTypeOfContrac.addItem("All");
		comboBoxTypeOfContrac.addItem("Accommodation Contract");
		comboBoxTypeOfContrac.addItem("Service Contract");
		comboBoxTypeOfContrac.addItem("Carrier Contract");
	}

	private void actualizarEstadoButtonDelete () {
		if (tableContracts.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}

	private void actualizarEstadoButtonUpdate () {
		if (tableContracts.getSelectedRowCount() != 0)
			lblUpdate.setEnabled(true);
		else
			lblUpdate.setEnabled(false);
	}
	
	private void addFiltres () {
		comboBoxTypeOfContrac = new JComboBox<String>();
		comboBoxTypeOfContrac.setFont(new Font("Dialog", Font.PLAIN, 21));
		comboBoxTypeOfContrac.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboBoxTypeOfContract();
		comboBoxTypeOfContrac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaContracts(); // se actualiza la información de la tabla de contratos
			}
		});
		
				JLabel lblTypeOfContract = new JLabel("Type Of Contract");
				lblTypeOfContract.setForeground(SystemColor.textHighlightText);
				lblTypeOfContract.setHorizontalAlignment(SwingConstants.CENTER);
				lblTypeOfContract.setFont(new Font("Dialog", Font.BOLD, 21));
				lblTypeOfContract.setBounds(61, 24, 138, 14);
				panelFiltros.add(lblTypeOfContract);
		comboBoxTypeOfContrac.setBounds(61, 49, 138, 22);
		panelFiltros.add(comboBoxTypeOfContrac);

		JLabel lblNewLabel_1 = new JLabel("Contract State");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 21));
		lblNewLabel_1.setBounds(522, 24, 119, 14);
		panelFiltros.add(lblNewLabel_1);

		comboBoxState = new JComboBox<String>();
		comboBoxState.setFont(new Font("Dialog", Font.PLAIN, 21));
		comboBoxState.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboboxStateContract();
		comboBoxState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaContracts(); // se actualiza la información de la tabla de contratos
			}
		});
		comboBoxState.setBounds(522, 49, 119, 22);
		panelFiltros.add(comboBoxState);

		comboBoxProvider = new JComboBox<Provider>();
		comboBoxProvider.setFont(new Font("Dialog", Font.PLAIN, 21));
		comboBoxProvider.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.llenarComboBoxProviders();
		comboBoxProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaContracts(); // se actualiza la información de la tabla de contratos
			}
		});
		
				lblNewLabel = new JLabel("Provider");
				lblNewLabel.setForeground(SystemColor.textHighlightText);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 21));
				lblNewLabel.setBounds(301, 24, 119, 14);
				panelFiltros.add(lblNewLabel);
		comboBoxProvider.setBounds(301, 49, 119, 22);
		panelFiltros.add(comboBoxProvider);

	}
	
	private void addButtons () {
		lblAddServiceContract = new JLabel("ADD Service Contract");
		lblAddServiceContract.setForeground(SystemColor.textHighlightText);
		lblAddServiceContract.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAddServiceContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddServiceContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoServivio frameCracionServiceContract = new FrameGerenteCreacionContratoServivio(PanelGerenteCreacionContrato.this, new ServiceContract());
				frameCracionServiceContract.setVisible(true);
				FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblAddServiceContract.setOpaque(true);
		lblAddServiceContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddServiceContract.setFont(new Font("Dialog", Font.BOLD, 26));
		lblAddServiceContract.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblAddServiceContract);

		lblAddTransportationContract = new JLabel("ADD Transportation Contract");
		lblAddTransportationContract.setForeground(SystemColor.textHighlightText);
		lblAddTransportationContract.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAddTransportationContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddTransportationContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoTransporte frameAddCarrierContract = new FrameGerenteCreacionContratoTransporte(PanelGerenteCreacionContrato.this, new CarrierContract());
				frameAddCarrierContract.setVisible(true);
				FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAddTransportationContract.setOpaque(true);
		lblAddTransportationContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTransportationContract.setFont(new Font("Dialog", Font.BOLD, 26));
		lblAddTransportationContract.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblAddTransportationContract);

		lblAddAccommodationContract = new JLabel("ADD Accommodation Contract");
		lblAddAccommodationContract.setForeground(SystemColor.textHighlightText);
		lblAddAccommodationContract.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAddAccommodationContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddAccommodationContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoAlojamiento frameAddAccommodationContract = new FrameGerenteCreacionContratoAlojamiento(PanelGerenteCreacionContrato.this, new AccommodationContract());
				frameAddAccommodationContract.setVisible(true);
				FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAddAccommodationContract.setOpaque(true);
		lblAddAccommodationContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAccommodationContract.setFont(new Font("Dialog", Font.BOLD, 26));
		lblAddAccommodationContract.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblAddAccommodationContract);

		lblDelete = new JLabel("DELETE");
		lblDelete.setForeground(SystemColor.textHighlightText);
		lblDelete.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					try {
						deleteElementsTable();
						ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos 
					} catch (SQLException e1) {
						try {
							ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas a la base de datos 
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

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		panelBotones.add(lblDelete);
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Dialog", Font.BOLD, 26));
		lblDelete.setBackground(new Color(18, 95, 115));

		lblUpdate = new JLabel("UPDATE");
		lblUpdate.setForeground(SystemColor.textHighlightText);
		lblUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUpdate.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblUpdate.isEnabled()) {
					Contract contract = ((ModeloTablaContract) tableContracts.getModel()).getElement(tableContracts.getSelectedRow());
					if (contract instanceof ServiceContract) {
						FrameGerenteCreacionContratoServivio frameCracionServiceContract = new FrameGerenteCreacionContratoServivio(PanelGerenteCreacionContrato.this, (ServiceContract) contract);
						frameCracionServiceContract.setVisible(true);
						FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
					}
					else if (contract instanceof CarrierContract) {
						FrameGerenteCreacionContratoTransporte frameAddCarrierContract = new FrameGerenteCreacionContratoTransporte(PanelGerenteCreacionContrato.this, new CarrierContract());
						frameAddCarrierContract.setVisible(true);
						FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
					}
					else if (contract instanceof AccommodationContract) {
						FrameGerenteCreacionContratoAlojamiento frameAddAccommodationContract = new FrameGerenteCreacionContratoAlojamiento(PanelGerenteCreacionContrato.this, (AccommodationContract) contract);
						frameAddAccommodationContract.setVisible(true);
						FrameGerente.getIntancie().setEnabled(false); // se inhabilita el frame principal
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
		lblUpdate.setOpaque(true);
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("Dialog", Font.BOLD, 26));
		lblUpdate.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblUpdate);
	}

	public void actualizarTablaContracts () {
		if (((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && ((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				((Provider)	comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All")) // no se seleccionó ningún filtro 
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts());

		else if (!((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && ((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				((Provider)	comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All")) // filtro tipo
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts(this.determinarTypeOfContract((String) comboBoxTypeOfContrac.getSelectedItem())));

		else if (((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && ((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				!((Provider) comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All"))// filtro proveedor
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts((Provider) comboBoxProvider.getSelectedItem()));

		else if (((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && !((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				((Provider) comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All")) // filtro estado
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts((((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("Close")) ? true : false ));

		else if (!((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && ((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				!((Provider)	comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All")) // filtro tipo, proveedor
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts(this.determinarTypeOfContract( (String) comboBoxTypeOfContrac.getSelectedItem()),
					(Provider) comboBoxProvider.getSelectedItem()));

		else if (!((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && !((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				((Provider)	comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All")) // filtro tipo, estado
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts(this.determinarTypeOfContract((String) comboBoxTypeOfContrac.getSelectedItem()),
					(((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("Close")) ? true : false));

		else if (((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && !((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				!((Provider) comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All"))// filtro proveedor, estado
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts((Provider) comboBoxProvider.getSelectedItem(),
					(((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("Close")) ? true : false));

		else if (!((String) comboBoxTypeOfContrac.getSelectedItem()).equalsIgnoreCase("All") && !((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("All") && 
				!((Provider)	comboBoxProvider.getSelectedItem()).getName().equalsIgnoreCase("All")) // filtro tipo, proveedor, estado
			this.actualizarTablaContracts(Controller.getInstancie().getTouristAgency().getContracts(this.determinarTypeOfContract( (String) comboBoxTypeOfContrac.getSelectedItem()),
					(Provider) comboBoxProvider.getSelectedItem(), (((String) comboBoxState.getSelectedItem()).equalsIgnoreCase("Close")) ? true : false));

	}

	private int determinarTypeOfContract (String typeOfContract) {
		int type = -1;

		if (typeOfContract.equalsIgnoreCase("Accommodation Contract"))
			type = Contract.accommodationContract;
		else if (typeOfContract.equalsIgnoreCase("Service Contract"))
			type = Contract.serviceContract;
		else if (typeOfContract.equalsIgnoreCase("Carrier Contract"))
			type = Contract.carrierContract;

		return type;
	}


	private void actualizarTablaContracts(HashMap<Integer, ArrayList<Contract>> contracts){
		ArrayList<Integer> keys = Contract.getKeys(); // se obtienen las cavles del mapa
		reiniciarTable(this.tableContracts);

		for (Integer k : keys) {
			for (Contract contract : contracts.get(k)) {
				((ModeloTablaContract) tableContracts.getModel()).addElement(contract);
			}
		}
	}


	private void actualizarTablaContracts(ArrayList<Contract> contracts){
		reiniciarTable(this.tableContracts);


		for (Contract contr : contracts) {
			((ModeloTablaContract) tableContracts.getModel()).addElement(contr);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableContracts.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			Controller.getInstancie().getTouristAgency().deleteContract(((ModeloTablaContract) tableContracts.getModel()).deleteElement(rows[i] - i)); // se elimina el contrato de la base de datos y de la logica del negocio
		}

		this.actualizarTablaContracts();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaContract) table.getModel()).deleteElement(i);
		}
	}
}
