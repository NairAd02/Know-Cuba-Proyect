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
	private JLabel lblShowActivities;




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




	public FrameGerenteAsociacionEmpresaProveedorServicio(PanelGerenteAsociacionEmpresaProveedorServicio ps, ServiceProvider s) {
		this.panelGerenteAsociacionEmpresaProveedorServicio = ps;
		if (s != null)
			this.serviceProvider = s;
		else
			this.serviceProvider = new ServiceProvider(); // objeto temporal

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
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(5, 150, 177));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblServiceProvider = new JLabel("SERVICE PROVIDER");
		lblServiceProvider.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblServiceProvider.setBounds(27, 11, 231, 30);
		contentPane.add(lblServiceProvider);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (serviceProvider.getId() != -1) {
					try {
						ConnectionDataBase.roolback();
						serviceProvider.actualizarActivities(); // se actualiza la informacion de las actividades
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // se cancelan las transacciones realizadas
				}
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
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);



		lblShowActivities = new JLabel("SHOW ACTIVITIES");
		lblShowActivities.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblShowActivities.setBounds(27, 52, 231, 30);
		contentPane.add(lblShowActivities);

		if (serviceProvider.getId() == -1) { // se habilita la inserccion
			this.addSeccionName();
			this.addSeccionProvince();
			this.lblShowActivities.setVisible(false);
		}


		this.addButtonADD();


		JLabel lblActivities = new JLabel("ACTIVITIES");
		lblActivities.setForeground(SystemColor.info);
		lblActivities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblActivities.setBounds(90, 130, 117, 23);
		contentPane.add(lblActivities);





		JPanel panelActivities = new JPanel();
		panelActivities.setBounds(87, 164, 290, 99);
		contentPane.add(panelActivities);
		panelActivities.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelActivities.add(scrollPane, BorderLayout.CENTER);

		tableActivities = new JTable();
		tableActivities.setModel(new ModeloTablaActivies());
		scrollPane.setViewportView(tableActivities);



		lblAnnadir = new JLabel("ADD");
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

		this.actualizarTablaActivities();
	}

	private void addSeccionName () {
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(90, 52, 80, 23);
		contentPane.add(lblName);

		textFieldServiceProviderName = new JTextField();
		textFieldServiceProviderName.setBounds(180, 56, 197, 20);
		contentPane.add(textFieldServiceProviderName);
		textFieldServiceProviderName.setColumns(10);
	}

	private void addSeccionProvince () {
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.info);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
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

		lblAdd = new JLabel(nameLabel);
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (serviceProvider.getId() == -1) {
					if (verificarCampos()) {
						try {
							addServiceProvider();
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							cerrarFrame();
						} catch (SQLException e1) {
							try {
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
					if (serviceProvider.cantActivities() != 0) {
						cerrarFrame();
						try {
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
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
				serviceProvider.deleteActivityLogic( ((ModeloTablaActivies) tableActivities.getModel()).deleteElement(rows[i] - i)); // se elimina solo el la actividad de la logica del negocio
			else
				serviceProvider.deleteActivity(((ModeloTablaActivies) tableActivities.getModel()).deleteElement(rows[i] - i)); // se elimina la actividad de la logica del negocio y de la base de datos
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
				serviceProvider.getActivities().size() != 0);
	}



	private void addServiceProvider () throws SQLException {
		Controller.getInstancie().getTouristAgency().addProvider(new ServiceProvider(textFieldServiceProviderName.getText(), textFieldProvince.getText(), 
				serviceProvider.getActivities())); // se inserta el provedor de servicios a nivel de base de datos
		panelGerenteAsociacionEmpresaProveedorServicio.actualizarTablaServicieProviders(); // se actualiza la informacion de la tabla de provedores

	}

	private void cerrarFrame () {
		FrameGerente.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
		dispose(); // se cierra este frame
	}

}
