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
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;

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
	private JLabel lblName;
	private JPanel panelSuperior;
	private JPanel panelTitle;
	private JLabel lblTitleSeccion;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelFiltros;
	private JPanel panelBotones;

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
		tableServiceProviders.setRowHeight(30);
		tableServiceProviders.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tableServiceProviders.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtonDelete();
				actualizarEstadoButtonShow();
			}
		});
		tableServiceProviders.setModel(new ModeloTablaServiceProvider());
		tableServiceProviders.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		tableServiceProviders.getTableHeader().setForeground(Color.black);
		tableServiceProviders.getTableHeader().setBackground(SystemColor.black);
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
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonShow();

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
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorServicio frameAddProvedorServicio = new FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio.this, new ServiceProvider()); // se manda por parametro null ya que se desea adicionar un provedor de servicios
				frameAddProvedorServicio.setVisible(true);
				FrameGerente.getIntancie().setEnabled(false); // se inabilita el frame principal
			}
		});
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Plus.png")));
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(245, 19, 68, 52);
		panelBotones.add(lblAnnadir);

		lblDelete = new JLabel("");
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Trash.png")));
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
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(405, 19, 67, 52);
		panelBotones.add(lblDelete);

		lblShowActivities = new JLabel("");
		lblShowActivities.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorServicio.class.getResource("/images/Edit.png")));
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
		lblShowActivities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowActivities.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowActivities.setBackground(SystemColor.info);
		lblShowActivities.setBounds(564, 19, 67, 52);
		panelBotones.add(lblShowActivities);

		lblImage = new JLabel("");
		lblImage.setOpaque(true);
		lblImage.setBounds(0, 0, 712, 678);
		lblImage.setBackground(new Color(5, 150, 177));
		panelBotones.add(lblImage);

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
