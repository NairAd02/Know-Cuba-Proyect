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

public class PanelGerenteCreacionContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable tableContracts;
	private JLabel lblAdd;
	private JLabel lblAdd_1;
	private JLabel lblAddAccommodationContract;
	private JLabel lblDelete;
	private JComboBox<String> comboBoxTypeOfContrac;
	private JComboBox<String> comboBoxState;
	private JComboBox<Provider> comboBoxProvider;
	private JLabel lblNewLabel;
	private JLabel lblUpdate;

	/**
	 * Create the panel.
	 */
	public PanelGerenteCreacionContrato() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		setBounds(278, 63, 712, 719);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 712, 69);
		panel.setBackground(new Color(18, 95, 115));
		add(panel);
		panel.setLayout(null);

		lblAdd = new JLabel("ADD Service Contract");
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.addMouseListener(new MouseAdapter() {
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
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(10, 21, 159, 26);
		panel.add(lblAdd);

		lblAdd_1 = new JLabel("ADD Transportation Contract");
		lblAdd_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAdd_1.setOpaque(true);
		lblAdd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAdd_1.setBackground(SystemColor.info);
		lblAdd_1.setBounds(177, 21, 216, 26);
		panel.add(lblAdd_1);

		lblAddAccommodationContract = new JLabel("ADD Accommodation Contract");
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
		lblAddAccommodationContract.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAddAccommodationContract.setBackground(SystemColor.info);
		lblAddAccommodationContract.setBounds(401, 21, 234, 26);
		panel.add(lblAddAccommodationContract);

		lblDelete = new JLabel("DELETE");
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
		lblDelete.setBounds(645, 32, 58, 26);
		panel.add(lblDelete);
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);

		lblUpdate = new JLabel("UPDATE");
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
		lblUpdate.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblUpdate.setBackground(SystemColor.info);
		lblUpdate.setBounds(645, 5, 58, 26);
		panel.add(lblUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableContracts.clearSelection();
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonUpdate();
			}
		});
		panel_1.setLayout(null);
		panel_1.setBounds(0, 68, 712, 651);
		panel_1.setBackground(new Color(5, 150, 177));
		add(panel_1);

		panelTable = new JPanel();
		panelTable.setBounds(10, 93, 692, 547);
		panel_1.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableContracts = new JTable();
		tableContracts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonUpdate();
			}
		});
		tableContracts.setModel(new ModeloTablaContract());
		scrollPaneTable.setViewportView(tableContracts);

		comboBoxTypeOfContrac = new JComboBox<String>();
		this.llenarComboBoxTypeOfContract();
		comboBoxTypeOfContrac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaContracts(); // se actualiza la información de la tabla de contratos
			}
		});
		comboBoxTypeOfContrac.setBounds(10, 35, 138, 22);
		panel_1.add(comboBoxTypeOfContrac);

		JLabel lblTypeOfContract = new JLabel("Type Of Contract");
		lblTypeOfContract.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTypeOfContract.setBounds(10, 11, 110, 14);
		panel_1.add(lblTypeOfContract);

		JLabel lblNewLabel_1 = new JLabel("Contract State");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(583, 11, 119, 14);
		panel_1.add(lblNewLabel_1);

		comboBoxState = new JComboBox<String>();
		this.llenarComboboxStateContract();
		comboBoxState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaContracts(); // se actualiza la información de la tabla de contratos
			}
		});
		comboBoxState.setBounds(583, 35, 119, 22);
		panel_1.add(comboBoxState);

		comboBoxProvider = new JComboBox<Provider>();
		this.llenarComboBoxProviders();
		comboBoxProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaContracts(); // se actualiza la información de la tabla de contratos
			}
		});
		comboBoxProvider.setBounds(423, 35, 119, 22);
		panel_1.add(comboBoxProvider);

		lblNewLabel = new JLabel("Provider");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(423, 11, 119, 14);
		panel_1.add(lblNewLabel);

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
