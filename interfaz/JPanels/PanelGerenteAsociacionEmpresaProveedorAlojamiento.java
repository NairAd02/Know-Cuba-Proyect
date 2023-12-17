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

import javax.swing.border.LineBorder;
import JFrames.FrameGerente;
import JFrames.FrameGerenteAsociacionEmpresaProveedorAlojamiento;
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

public class PanelGerenteAsociacionEmpresaProveedorAlojamiento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneTable;
	private JPanel panelTable;
	private JTable tableAccommodationProvider;
	private JLabel lblImage;
	private JTextField textFieldBuscador;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private JLabel lblShowPlans;
	private String searchName;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorAlojamiento() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableAccommodationProvider.clearSelection();
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		searchName = "";
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		setBounds(0, 41, 712, 678);

		panelTable = new JPanel();
		panelTable.setBounds(10, 93, 692, 574);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableAccommodationProvider = new JTable();
		tableAccommodationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		tableAccommodationProvider.setModel(new ModeloTablaAccommodationProvider());
		scrollPaneTable.setViewportView(tableAccommodationProvider);


		lblShowPlans = new JLabel("SHOW PLANS");
		lblShowPlans.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblShowPlans.isEnabled()) {
					FrameGerenteAsociacionEmpresaProveedorAlojamiento frameAlojamiento = new FrameGerenteAsociacionEmpresaProveedorAlojamiento(PanelGerenteAsociacionEmpresaProveedorAlojamiento.this, 
							((ModeloTablaAccommodationProvider)   tableAccommodationProvider.getModel()).getElement(tableAccommodationProvider.getSelectedRow()));
					frameAlojamiento.setVisible(true);
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
		lblShowPlans.setOpaque(true);
		lblShowPlans.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlans.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowPlans.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblShowPlans.setBackground(SystemColor.info);
		lblShowPlans.setBounds(547, 52, 155, 20);
		add(lblShowPlans);

		lblDelete = new JLabel("DELETE");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					try {
						deleteElementsTable();
						ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos
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
		lblDelete.setBounds(547, 32, 155, 20);
		add(lblDelete);

		lblAnnadir = new JLabel("ADD");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorAlojamiento frameAccommodationProvider = new FrameGerenteAsociacionEmpresaProveedorAlojamiento(PanelGerenteAsociacionEmpresaProveedorAlojamiento.this , null);
				frameAccommodationProvider.setVisible(true);
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
		lblAnnadir.setBounds(547, 11, 155, 20);
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

				actualizarTablaAccommodationProviders(); // se actualiza la informacion de los provedores de transporte en la tabla de provedores de transportes
			}
		});
		textFieldBuscador.setColumns(10);
		textFieldBuscador.setBounds(10, 64, 182, 20);
		add(textFieldBuscador);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Imagen1.jpg")));
		lblImage.setBounds(0, 0, 712, 678);
		add(lblImage);

		this.actualizarTablaAccommodationProviders();
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();

	}

	public void actualizarTablaAccommodationProviders () {
		if (!searchName.equalsIgnoreCase(""))
			this.actualizarTablaAccommodationProviders(Controller.getInstancie().getTouristAgency().getProviders(searchName, Provider.accommodationProvider));
		else
			this.actualizarTablaAccommodationProviders(Controller.getInstancie().getTouristAgency().getProviders(Provider.accommodationProvider));
	}


	private void actualizarTablaAccommodationProviders(ArrayList<Provider> accommodationProviders){
		reiniciarTable(this.tableAccommodationProvider);


		for (Provider accprov : accommodationProviders) {
			((ModeloTablaAccommodationProvider) tableAccommodationProvider.getModel()).addElement((Hotel) accprov);
		}
	}

	private void deleteElementsTable () throws SQLException {
		((ModeloTablaAccommodationProvider) tableAccommodationProvider.getModel()).deleteElements(this.tableAccommodationProvider.getSelectedRows());
		this.actualizarTablaAccommodationProviders();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaAccommodationProvider) table.getModel()).deleteElement(i);
		}

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
}
