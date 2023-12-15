package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelosTablas.ModeloTablaTransportationProvider;
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

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorTransporte() {
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
		tableTransportationProviders.setModel(new ModeloTablaTransportationProvider());
		scrollPaneTable.setViewportView(tableTransportationProviders);

		lblDelete = new JLabel("DELETE");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deleteElementsTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(547, 31, 155, 20);
		add(lblDelete);

		lblAnnadir = new JLabel("ADD");
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
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(547, 10, 155, 20);
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
		textFieldBuscador.setBounds(10, 63, 182, 20);
		add(textFieldBuscador);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Imagen3.jpg")));
		lblImage.setBounds(0, 0, 712, 678);
		add(lblImage);


		this.actualizarTablaTransportationProviders();

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

}
