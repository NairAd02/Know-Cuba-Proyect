package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Controller;
import logica.Modality;
import logica.ServiceModality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaAccommodationModality;
import modelosTablas.ModeloTablaServiceModality;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class FrameDisennadorPaqueteTuristicoModalidadServicioAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAvailableServices;
	private JTable tableAssignedServices;
	private int mouseX, mouseY;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private FrameInformacionPaquete frameInformacionPaquete;
	private TouristPackage touristPackage;
	private JLabel lblX;


	public FrameDisennadorPaqueteTuristicoModalidadServicioAnnadir(FrameInformacionPaquete f) {
		this.frameInformacionPaquete = f;
		this.touristPackage = this.frameInformacionPaquete.getTouristPackage();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
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
				tableAssignedServices.clearSelection();
				tableAvailableServices.clearSelection();
				actualizarEstadosBotones();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(5, 150, 177));
		panel.setLayout(null);
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 600, 360);
		contentPane.add(panel);
		setLocationRelativeTo(null);
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.black);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(562, 0, 38, 38);
		panel.add(lblX);

		JLabel lblAvailableServices = new JLabel("AVAILABLE SERVICES");
		lblAvailableServices.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableServices.setBounds(25, 33, 275, 19);
		panel.add(lblAvailableServices);

		lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					asign(); // se ejecuta la operacion de asignar modalidad al paquete turistico
					actualizarEstadosBotones();
				} catch (SQLException e1) {
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
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(435, 40, 155, 20);
		panel.add(lblAnnadir);

		JLabel lblAssignedServices = new JLabel("ASSIGNED SERVICES");
		lblAssignedServices.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedServices.setBounds(25, 173, 275, 19);
		panel.add(lblAssignedServices);

		lblDenegar = new JLabel("DENY");
		lblDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deny(); // se ejecuta la operacion de desasignar modalidad del paquete turistico
					actualizarEstadosBotones();
				} catch (SQLException e1) {
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
		lblDenegar.setOpaque(true);
		lblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(SystemColor.info);
		lblDenegar.setBounds(435, 183, 155, 20);
		panel.add(lblDenegar);

		JLabel lblConfirm = new JLabel("CERRAR");
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(182, 311, 235, 35);
		panel.add(lblConfirm);

		JPanel panelAvailableServices = new JPanel();
		panelAvailableServices.setBounds(10, 71, 580, 86);
		panel.add(panelAvailableServices);
		panelAvailableServices.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAvailableServices.add(scrollPane, BorderLayout.CENTER);

		tableAvailableServices = new JTable();
		tableAvailableServices.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones(); 
			}
		});
		tableAvailableServices.setModel(new ModeloTablaServiceModality());
		scrollPane.setViewportView(tableAvailableServices);

		JPanel panelAssignedServices = new JPanel();
		panelAssignedServices.setOpaque(false);
		panelAssignedServices.setBounds(10, 214, 580, 86);
		panel.add(panelAssignedServices);
		panelAssignedServices.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedServices.add(scrollPane_1, BorderLayout.CENTER);

		tableAssignedServices = new JTable();
		tableAssignedServices.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAssignedServices.setModel(new ModeloTablaServiceModality());
		scrollPane_1.setViewportView(tableAssignedServices);
		this.actualizarTablas(); // se actualiza la informacion de las tablas
		this.actualizarEstadosBotones();
	}



	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableServicies () {	
		this.actualizarTablaAviableServicies(Controller.getInstancie().getTouristAgency().getServiceModalitys()); // se obtienen las modalidades de servicio disponibles en la agencia
	}


	private void actualizarTablaAviableServicies(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableServices);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.serviceModality, mod))
				((ModeloTablaServiceModality) tableAvailableServices.getModel()).addElement((ServiceModality) mod); // se obtienen todas las modalidades del alojamiento de la agencia turistica
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedServicies () {	
		this.actualizarTablaAssignedServicies(touristPackage.getModalitys(Modality.serviceModality)); // se obtienen todas las modalidades del alojamiento pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedServicies(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedServices);


		for (Modality mod : modalitys) {
			((ModeloTablaServiceModality) tableAssignedServices.getModel()).addElement((ServiceModality) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaServiceModality) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableAvailableServices.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaServiceModality) this.tableAvailableServices.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaServiceModality) this.tableAvailableServices.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	private void actualizarTablas () {
		this.actualizarTablaAssignedServicies();
		this.actualizarTablaAviableServicies();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableAssignedServices.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaServiceModality) this.tableAssignedServices.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaServiceModality) this.tableAssignedServices.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	// Fin de Operaciones



	private void cerrarFrame () {
		frameInformacionPaquete.setEnabled(true);
		dispose();
	}

	private void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableAvailableServices.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableAssignedServices.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}

}
