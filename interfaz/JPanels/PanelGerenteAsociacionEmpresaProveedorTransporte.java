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

public class PanelGerenteAsociacionEmpresaProveedorTransporte extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JTable tableTransportationProviders;
	private JLabel lblImage;
	private JTextField textFieldBuscador;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private String searchName;
	private JLabel lblShowVehicle;
	private JLabel lblName;

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
		setLayout(null);
		setBounds(0, 41, 712, 678);

		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 94, 692, 573);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableTransportationProviders = new JTable();
		tableTransportationProviders.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		tableTransportationProviders.setModel(new ModeloTablaTransportationProvider());
		scrollPaneTable.setViewportView(tableTransportationProviders);

		lblDelete = new JLabel("");
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/eliminar1.png")));
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
		
		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblName.setBounds(86, 34, 68, 22);
		add(lblName);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(405, 19, 67, 52);
		add(lblDelete);

		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/a\u00F1adir2 - copia.png")));
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
		add(lblAnnadir);

		textFieldBuscador = new JTextField();
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
		add(textFieldBuscador);

		lblShowVehicle = new JLabel("");
		lblShowVehicle.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/mostrar.png")));
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
		add(lblShowVehicle);

		lblImage = new JLabel("");
		lblImage.setOpaque(true);
		lblImage.setBounds(0, 0, 712, 678);
		lblImage.setBackground(new Color(5, 150, 177));
		add(lblImage);




		this.actualizarTablaTransportationProviders();
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();
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
