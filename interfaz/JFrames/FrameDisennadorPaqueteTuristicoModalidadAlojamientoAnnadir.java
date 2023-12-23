package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import logica.AccommodationModality;
import logica.Controller;
import logica.Modality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaAccommodationModality;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class FrameDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAvailableAccommodation;
	private JTable tableAssignedAccommodations;
	private JLabel lblAnnadir;
	private JLabel lblConfirm;
	private JLabel lblDenegar;
	private FrameInformacionPaquete frameInformacionPaquete;
	private TouristPackage touristPackage;
	private int mouseX, mouseY;
	private JLabel lblX;


	public FrameDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir(FrameInformacionPaquete f) {
		this.frameInformacionPaquete = f;
		this.touristPackage = this.frameInformacionPaquete.getTouristPackage();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 360);
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
				tableAssignedAccommodations.clearSelection();
				tableAvailableAccommodation.clearSelection();
				actualizarEstadosBotones();
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(5, 150, 177));
		setBackground(new Color(5, 150, 177));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 600, 360);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarFrame();
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

		JLabel lblAvailableAccommodation = new JLabel("AVAILABLE ACCOMMODATION");
		lblAvailableAccommodation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableAccommodation.setBounds(25, 33, 275, 19);
		panel.add(lblAvailableAccommodation);

		lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblAnnadir.isEnabled()) {
					try {
						asign();
						actualizarEstadosBotones();
					} catch (SQLException e1) {
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
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(435, 40, 155, 20);
		panel.add(lblAnnadir);

		JLabel lblAssignedAccommodations = new JLabel("ASSIGNED ACCOMMODATIONS");
		lblAssignedAccommodations.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedAccommodations.setBounds(25, 173, 275, 19);
		panel.add(lblAssignedAccommodations);

		lblDenegar = new JLabel("DENY");
		lblDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDenegar.isEnabled()) {
					try {
						deny();
						actualizarEstadosBotones();
					} catch (SQLException e1) {
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
		lblDenegar.setOpaque(true);
		lblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(SystemColor.info);
		lblDenegar.setBounds(435, 183, 155, 20);
		panel.add(lblDenegar);

		lblConfirm = new JLabel("CERRAR");
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

		JPanel panelAvailableAccommodation = new JPanel();
		panelAvailableAccommodation.setBounds(10, 71, 580, 86);
		panel.add(panelAvailableAccommodation);
		panelAvailableAccommodation.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAvailableAccommodation.add(scrollPane, BorderLayout.CENTER);

		tableAvailableAccommodation = new JTable();
		tableAvailableAccommodation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAvailableAccommodation.setModel(new ModeloTablaAccommodationModality());
		scrollPane.setViewportView(tableAvailableAccommodation);

		JPanel panelAssignedAccommodations = new JPanel();
		panelAssignedAccommodations.setOpaque(false);
		panelAssignedAccommodations.setBounds(10, 214, 580, 86);
		panel.add(panelAssignedAccommodations);
		panelAssignedAccommodations.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedAccommodations.add(scrollPane_1, BorderLayout.CENTER);

		tableAssignedAccommodations = new JTable();
		tableAssignedAccommodations.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAssignedAccommodations.setModel(new ModeloTablaAccommodationModality());
		scrollPane_1.setViewportView(tableAssignedAccommodations);

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
		this.actualizarEstadosBotones();
	}

	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableAccommodations () {	
		this.actualizarTablaAviableAccommodations(Controller.getInstancie().getTouristAgency().getAccommodationModalitys()); 
	}


	private void actualizarTablaAviableAccommodations(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableAccommodation);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.accommodationModality, mod))
				((ModeloTablaAccommodationModality) tableAvailableAccommodation.getModel()).addElement((AccommodationModality) mod); // se obtienen todas las modalidades del alojamiento de la agencia turistica
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedAccommodations () {	
		this.actualizarTablaAssignedAccommodations(touristPackage.getModalitys(Modality.accommodationModality)); // se obtienen todas las modalidades del alojamiento pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedAccommodations(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedAccommodations);


		for (Modality mod : modalitys) {
			((ModeloTablaAccommodationModality) tableAssignedAccommodations.getModel()).addElement((AccommodationModality) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaAccommodationModality) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableAvailableAccommodation.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaAccommodationModality) this.tableAvailableAccommodation.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaAccommodationModality) this.tableAvailableAccommodation.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	private void actualizarTablas () {
		this.actualizarTablaAssignedAccommodations();
		this.actualizarTablaAviableAccommodations();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableAssignedAccommodations.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaAccommodationModality) this.tableAssignedAccommodations.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaAccommodationModality) this.tableAssignedAccommodations.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
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
		if (tableAvailableAccommodation.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableAssignedAccommodations.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}

}
