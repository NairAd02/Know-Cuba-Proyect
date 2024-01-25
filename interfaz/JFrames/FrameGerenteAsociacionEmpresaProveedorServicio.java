package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelosTablas.ModeloTablaActivies;
import utils.ConnectionDataBase;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import JPanels.PanelGerenteAsociacionEmpresaProveedorServicio;
import logica.Activity;
import logica.Controller;
import logica.ServiceProvider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Cursor;

public class FrameGerenteAsociacionEmpresaProveedorServicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldServiceProviderName;
	private JTextField textFieldProvince;
	private JTable tableActivities;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private ServiceProvider serviceProvider;
	private PanelGerenteAsociacionEmpresaProveedorServicio panelGerenteAsociacionEmpresaProveedorServicio;
	private JLabel lblX;
	private int mouseX, mouseY;
	private JLabel lblAdd;
	private JLabel lblRestore;




	public JTable getTableActivities() {
		return tableActivities;
	}

	public void setTableActivities(JTable tableActivities) {
		this.tableActivities = tableActivities;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}


	private void crearFrameNotificacion (String mensaje) {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia(mensaje);
		frameAdvertencia.setVisible(true);
	}

	public FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio ps, ServiceProvider s) {
		this.panelGerenteAsociacionEmpresaProveedorServicio = ps;
		this.serviceProvider = s;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);

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
				tableActivities.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(18, 95, 115));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblServiceProvider = new JLabel("SERVICE PROVIDER");
		lblServiceProvider.setForeground(SystemColor.textHighlightText);
		lblServiceProvider.setFont(new Font("Dialog", Font.BOLD, 21));
		lblServiceProvider.setBounds(27, 11, 231, 30);
		contentPane.add(lblServiceProvider);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (serviceProvider.getId() != -1) {
					try {
						ConnectionDataBase.roolback();
						serviceProvider.actualizarActivities(); // se actualiza la informacion de las actividades
						cerrarFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // se cancelan las transacciones realizadas
				}
				else
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
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);


		this.addSeccionName();
		this.addSeccionProvince();
		this.addButtonADD();


		JLabel lblActivities = new JLabel("ACTIVITIES");
		lblActivities.setForeground(SystemColor.textHighlightText);
		lblActivities.setFont(new Font("Dialog", Font.BOLD, 16));
		lblActivities.setBounds(90, 130, 117, 23);
		contentPane.add(lblActivities);





		JPanel panelActivities = new JPanel();
		panelActivities.setBounds(87, 164, 290, 99);
		contentPane.add(panelActivities);
		panelActivities.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelActivities.add(scrollPane, BorderLayout.CENTER);

		tableActivities = new JTable();
		tableActivities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableActivities.setModel(new ModeloTablaActivies());
		scrollPane.setViewportView(tableActivities);



		lblAnnadir = new JLabel("ADD");
		lblAnnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorServicioAnnadirActividad frameAddActivity = new FrameGerenteAsociacionEmpresaProveedorServicioAnnadirActividad(FrameGerenteAsociacionEmpresaProveedorServicio.this);
				frameAddActivity.setVisible(true);
				setEnabled(false);
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
		lblAnnadir.setBounds(220, 133, 71, 20);
		contentPane.add(lblAnnadir);

		lblEliminar = new JLabel("DELETE");
		lblEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tableActivities.getSelectedRowCount() != 0) {
					try {
						deleteElementsTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(310, 133, 67, 20);
		contentPane.add(lblEliminar);



		if (this.serviceProvider.getId() != -1) { // Update
			this.addLblRestore();
			this.actualizarTablaActivities();
			this.definirTextos();
		}
		else
			this.actualizarEstadoButtons(); // se actualiza el estado de los botones


	}

	private void addLblRestore () {
		lblRestore = new JLabel("Restore");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (serviceProvider.getId() != -1) {
					restoreInformation();
				}
			}
		});
		lblRestore.setForeground(SystemColor.textHighlightText);
		lblRestore.setFont(new Font("Dialog", Font.BOLD, 21));
		lblRestore.setBounds(268, 11, 109, 30);
		contentPane.add(lblRestore);
	}

	private void restoreInformation () {
		try {
			this.restoreActivities();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.actualizarTablaActivities();
		this.definirTextos();
	}

	private void restoreActivities () throws SQLException {
		ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
		serviceProvider.actualizarActivities(); // se actualiza la informacion de las actividades
	}

	private void definirTextos () {
		if (this.serviceProvider.getId() != -1) { // Update
			this.textFieldServiceProviderName.setText(this.serviceProvider.getName());
			this.textFieldProvince.setText(this.serviceProvider.getProvince());
		}

	}

	private void addSeccionName () {
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(90, 52, 80, 23);
		contentPane.add(lblName);

		textFieldServiceProviderName = new JTextField();
		textFieldServiceProviderName.setBounds(180, 56, 197, 20);
		contentPane.add(textFieldServiceProviderName);
		textFieldServiceProviderName.setColumns(10);
	}

	private void addSeccionProvince () {
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.textHighlightText);
		lblProvince.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProvince.setBounds(90, 86, 109, 23);
		contentPane.add(lblProvince);

		textFieldProvince = new JTextField();
		textFieldProvince.setColumns(10);
		textFieldProvince.setBounds(219, 90, 158, 20);
		contentPane.add(textFieldProvince);
	}




	private void addButtonADD () {
		String nameLabel = "";
		if (serviceProvider.getId() == -1)
			nameLabel = "ADD";
		else
			nameLabel = "CONFIRM";

		lblAdd = new JLabel("ADD");
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (serviceProvider.getId() == -1) {
					if (verificarCampos()) {
						try {
							addServiceProvider();
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							crearFrameNotificacion("Se ha insertado con exito el proveedor " + textFieldServiceProviderName.getText());
							panelGerenteAsociacionEmpresaProveedorServicio.actualizarTablaServicieProviders(); // se actualiza la informacion de la tabla de provedores
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								crearFrameNotificacion("Ha ocurrido un error durante la inserccion");
								ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} 
							e1.printStackTrace();
						}
					}
				}
				else {
					if (verificarCampos()) {
						cerrarFrame();
						try {
							updateServiceProvider();
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							crearFrameNotificacion("Ha sido actualizada correcatamente la información del proveedor de servicios");
							panelGerenteAsociacionEmpresaProveedorServicio.actualizarTablaServicieProviders(); // se actualiza la informacion de la tabla de provedores
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
		lblAdd.setBounds(115, 276, 235, 35);
		contentPane.add(lblAdd);


	}

	public void actualizarTablaActivities () {	
		this.actualizarTablaActivities(serviceProvider.getActivities()); // se obtienen las actividades del provedor de servicios
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}


	private void actualizarTablaActivities(ArrayList<Activity> activities){
		reiniciarTable(this.tableActivities);


		for (Activity actv : activities) {
			((ModeloTablaActivies) tableActivities.getModel()).addElement(actv);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableActivities.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (serviceProvider.getId() == -1)
				serviceProvider.deleteActivityLogic( ((ModeloTablaActivies) tableActivities.getModel()).getElement(rows[i])); // se elimina solo el la actividad de la logica del negocio
			else
				serviceProvider.deleteActivity(((ModeloTablaActivies) tableActivities.getModel()).getElement(rows[i])); // se elimina la actividad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaActivities();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaActivies) table.getModel()).deleteElement(i);
		}
	}

	private boolean verificarCampos () {
		return (!this.textFieldServiceProviderName.getText().equalsIgnoreCase("") && !this.textFieldProvince.getText().equalsIgnoreCase("") && 
				serviceProvider.cantActivities() != 0);
	}



	private void addServiceProvider () throws SQLException {
		Controller.getInstancie().getTouristAgency().addProvider(new ServiceProvider(textFieldServiceProviderName.getText(), textFieldProvince.getText(), 
				serviceProvider.getActivities())); // se inserta el provedor de servicios a nivel de base de datos
	}

	private void updateServiceProvider () throws SQLException {
		Controller.getInstancie().getTouristAgency().updateServiceProvider(this.serviceProvider, this.textFieldServiceProviderName.getText(), this.textFieldProvince.getText());
	}

	private void cerrarFrame () {
		FramePrincipal.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
		dispose(); // se cierra este frame
	}

	private void actualizarEstadoButtons () {

		// Estado boton eliminar modalidades de hotel
		if (this.tableActivities.getSelectedRowCount() != 0)
			this.lblEliminar.setEnabled(true);
		else
			this.lblEliminar.setEnabled(false);


	}

}
