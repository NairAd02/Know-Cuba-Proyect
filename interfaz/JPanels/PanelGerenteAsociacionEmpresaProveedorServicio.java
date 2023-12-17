package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelosTablas.ModeloTablaServiceProvider;
import utils.ConnectionDataBase;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import JFrames.FrameGerente;
import JFrames.FrameGerenteAsociacionEmpresaProveedorServicio;
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

public class PanelGerenteAsociacionEmpresaProveedorServicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTable;
	private JScrollPane scrollPaneTable;
	private JTable tableServiceProviders;
	private JLabel lblImage;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private JTextField textFieldBuscadorName;
	private String searchName;
	private JLabel lblShowActivities;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorServicio() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableServiceProviders.clearSelection();
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		searchName = "";
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		setBounds(0, 41, 712, 678);
		panelTable = new JPanel();
		panelTable.setOpaque(false);
		panelTable.setBounds(10, 93, 692, 574);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableServiceProviders = new JTable();
		tableServiceProviders.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		tableServiceProviders.setModel(new ModeloTablaServiceProvider());
		scrollPaneTable.setViewportView(tableServiceProviders);

		lblAnnadir = new JLabel("ADD");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorServicio frameAddProvedorServicio = new FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio.this, null); // se manda por parametro null ya que se desea adicionar un provedor de servicios
				frameAddProvedorServicio.setVisible(true);
				FrameGerente.getIntancie().setEnabled(false); // se inabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadir.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(547, 11, 155, 20);
		add(lblAnnadir);

		lblDelete = new JLabel("DELETE");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tableServiceProviders.getSelectedRowCount() != 0) {
					try {
						deleteElementsTable();
						ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos
					} catch (SQLException e1) {
						try {
							ConnectionDataBase.roolback();  // se cancelan las operaciones realizadas a la base de datos
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						e1.printStackTrace();
					} // se eliminan los elementos seleccionados
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(547, 32, 155, 20);
		add(lblDelete);

		textFieldBuscadorName = new JTextField();
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
		textFieldBuscadorName.setBounds(10, 64, 182, 20);
		add(textFieldBuscadorName);

		lblShowActivities = new JLabel("SHOW ACTIVITIES");
		lblShowActivities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblShowActivities.isEnabled()) {
					FrameGerenteAsociacionEmpresaProveedorServicio frameAddProvedorServicio = new FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio.this, 
							((ModeloTablaServiceProvider)	tableServiceProviders.getModel()).getElement(tableServiceProviders.getSelectedRow())); // se manda por parametro null ya que se desea adicionar un provedor de servicios
					frameAddProvedorServicio.setVisible(true);
					FrameGerente.getIntancie().setEnabled(false); // se inabilita el frame principal
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblShowActivities.setOpaque(true);
		lblShowActivities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowActivities.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowActivities.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblShowActivities.setBackground(SystemColor.info);
		lblShowActivities.setBounds(547, 52, 155, 20);
		add(lblShowActivities);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Imagen2.jpg")));
		lblImage.setBounds(0, 0, 712, 678);
		add(lblImage);



		this.actualizarTablaServicieProviders();
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();
	}

	public void actualizarTablaServicieProviders () {
		if (!searchName.equalsIgnoreCase(""))
			this.actualizarTablaServicieProviders(Controller.getInstancie().getTouristAgency().getProviders(searchName, Provider.serviceProvider));
		else
			this.actualizarTablaServicieProviders(Controller.getInstancie().getTouristAgency().getProviders(Provider.serviceProvider));
	}


	private void actualizarTablaServicieProviders(ArrayList<Provider> providers){
		reiniciarTable(this.tableServiceProviders);


		for (Provider serv : providers) {
			((ModeloTablaServiceProvider) tableServiceProviders.getModel()).addElement((ServiceProvider) serv);
		}
	}

	private void deleteElementsTable () throws SQLException {
		((ModeloTablaServiceProvider) tableServiceProviders.getModel()).deleteElements(this.tableServiceProviders.getSelectedRows());
		this.actualizarTablaServicieProviders();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaServiceProvider) table.getModel()).deleteElement(i);
		}
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
