package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.AddTransportModalityOperations;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import JPanels.PanelSeccionVehicles;
import logica.CarrierContract;
import logica.HoursKilometers;
import logica.TransportModality;
import utils.ConnectionDataBase;

import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers extends JFrame implements AddTransportModalityOperations {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private int mouseX, mouseY;
	private JLabel lblX;
	private JLabel lblAdd;
	private JSpinner spinnerCostKilometersRout;
	private JSpinner spinnerCostHours;
	private JSpinner spinnerCostKilometersRoutAdd;
	private JSpinner spinnerCostHoursAdd;
	private JLabel lblSeccionVehicles, lblRestoreInformation;
	private JLabel lblNewLabel_1;
	private HoursKilometers hoursKilometers;
	private JPanel panelHoursKilometersMode;



	public JPanel getPanel() {
		return panelHoursKilometersMode;
	}



	public FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers(PanelCreacionContratoTransporteTransportModality tm, HoursKilometers h) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		this.hoursKilometers = h;
		if (this.carrierContract.getId() != -1) // se crea un save point si se trata de una operacion de modificacion
			this.createSavePoint();
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.setBackground(new Color(18, 95, 115));
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
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		panelHoursKilometersMode = new JPanel();
		panelHoursKilometersMode.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelHoursKilometersMode.setBackground(new Color(18, 95, 115));
		panelHoursKilometersMode.setBounds(0, 0, 548, 414);
		contentPane.add(panelHoursKilometersMode);
		panelHoursKilometersMode.setLayout(null);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (hoursKilometers.getId() != -1) { // update
					try {
						restoreVehicles();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				cerrarFrame(); // se cierra el frame actual
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
		lblX.setBounds(510, 0, 38, 38);
		panelHoursKilometersMode.add(lblX);

		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setForeground(SystemColor.textHighlightText);
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(142, 11, 294, 30);
		panelHoursKilometersMode.add(lblTransportationMode);

		JLabel lblCostKilometersRout = new JLabel("COST KILOMETERS ROUT :");
		lblCostKilometersRout.setForeground(SystemColor.textHighlightText);
		lblCostKilometersRout.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersRout.setBounds(73, 170, 249, 23);
		panelHoursKilometersMode.add(lblCostKilometersRout);

		JLabel lblCostHours = new JLabel("COST HOURS :");
		lblCostHours.setForeground(SystemColor.textHighlightText);
		lblCostHours.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHours.setBounds(73, 204, 175, 23);
		panelHoursKilometersMode.add(lblCostHours);

		spinnerCostHours = new JSpinner();
		spinnerCostHours.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHours.setBounds(386, 205, 100, 20);
		panelHoursKilometersMode.add(spinnerCostHours);

		JLabel lblCostKilometersRoutAdd = new JLabel("COST KILOMETERS ROUT ADD :");
		lblCostKilometersRoutAdd.setForeground(SystemColor.textHighlightText);
		lblCostKilometersRoutAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersRoutAdd.setBounds(73, 238, 278, 23);
		panelHoursKilometersMode.add(lblCostKilometersRoutAdd);

		spinnerCostKilometersRoutAdd = new JSpinner();
		spinnerCostKilometersRoutAdd.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersRoutAdd.setBounds(386, 239, 100, 20);
		panelHoursKilometersMode.add(spinnerCostKilometersRoutAdd);

		lblAdd = new JLabel();
		lblAdd.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (validarCampos()) {
					if (hoursKilometers.getId() == -1) { // Add
						try {
							addHoursKilometers();
							panelCreacionContratoTransporteTransportModality.actualizarPanelTablaModality(); // se actualiza la informacion de la tablas de las modalidades del contrato
							FramePrincipal.mostarFrameNotificacion("Ha sido insertada con éxito la modalidad"); // se notifica de la accion realiza al usuario
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else { // Update
						try {
							updateHoursKilometers();
							panelCreacionContratoTransporteTransportModality.actualizarPanelTablaModality(); // se actualiza la informacion de la tablas de las modalidades del contrato
							FramePrincipal.mostarFrameNotificacion("Ha sido modificada con éxito la información de la modalidad"); // se notifica de la accion realiza al usuario
							deleteSavePoint(); // se elimina el punto de guarado
							cerrarFrame(); // se cierra el frame actual
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
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(156, 357, 235, 35);
		panelHoursKilometersMode.add(lblAdd);

		spinnerCostKilometersRout = new JSpinner();
		spinnerCostKilometersRout.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersRout.setBounds(386, 171, 100, 20);
		panelHoursKilometersMode.add(spinnerCostKilometersRout);

		JLabel lblCostHoursAdd = new JLabel("COST HOURS ADD :");
		lblCostHoursAdd.setForeground(SystemColor.textHighlightText);
		lblCostHoursAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursAdd.setBounds(73, 272, 175, 23);
		panelHoursKilometersMode.add(lblCostHoursAdd);

		spinnerCostHoursAdd = new JSpinner();
		spinnerCostHoursAdd.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHoursAdd.setBounds(386, 273, 100, 20);
		panelHoursKilometersMode.add(spinnerCostHoursAdd);

		JLabel lblNewLabel = new JLabel("( Hours Kilometers ) ");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(188, 35, 173, 30);
		panelHoursKilometersMode.add(lblNewLabel);

		lblSeccionVehicles = new JLabel("SECCION VEHICLES");
		lblSeccionVehicles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelSeccionVehicles(FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers.this));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblSeccionVehicles.setOpaque(true);
		lblSeccionVehicles.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionVehicles.setForeground(SystemColor.textText);
		lblSeccionVehicles.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionVehicles.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblSeccionVehicles.setBackground(SystemColor.info);
		lblSeccionVehicles.setBounds(88, 122, 348, 30);
		panelHoursKilometersMode.add(lblSeccionVehicles);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers.class.getResource("/images/Logo 38x38.png")));
		lblNewLabel_1.setBounds(10, 11, 38, 38);
		panelHoursKilometersMode.add(lblNewLabel_1);



		this.definirComponentes();


	}

	private void definirComponentes () {
		if (this.hoursKilometers.getId() != -1) { // Update
			this.addLblRestore();
		}
		this.definirTextos();
	}

	private void addLblRestore () {
		lblRestoreInformation = new JLabel("Restore Information");
		lblRestoreInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation(); // se restaura la informacion de la modalidad de transporte antes de la modificacion
				FramePrincipal.mostarFrameNotificacion("Los datos de la modalidad de tipo horas por kilometro han sido restaurados con éxito"); // se notifica de la accion al usuario
			}
		});
		lblRestoreInformation.setForeground(SystemColor.textHighlightText);
		lblRestoreInformation.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblRestoreInformation.setBounds(167, 81, 213, 30);
		panelHoursKilometersMode.add(lblRestoreInformation);
	}

	private boolean validarCampos () {
		return this.hoursKilometers.getVehicles().size() != 0;
	}

	private void restoreInformation () {
		try {
			this.restoreVehicles();
			this.definirFormulario(); 
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void restoreVehicles () throws SQLException {
		ConnectionDataBase.roolback(ConnectionDataBase.peekSavePoint()); // se cancelan las operaciones realizadas sobre la base de datos hasta ese save point
		this.hoursKilometers.actualizarDatos(); // se actualiza los datos de los vehiculos de la modalidad con la informacion de la base de datos
	}

	private void definirTextos () {
		if (this.hoursKilometers.getId() != -1) { // Update
			this.definirFormulario();
			this.lblAdd.setText("UPDATE");
		}
		else // Add
			this.lblAdd.setText("ADD");
	}

	private void definirFormulario () {
		this.spinnerCostHours.setValue(this.hoursKilometers.getCostHours());
		this.spinnerCostHoursAdd.setValue(this.hoursKilometers.getCostHoursAdditionals());
		this.spinnerCostKilometersRout.setValue(this.hoursKilometers.getCostKilometersRout());
		this.spinnerCostKilometersRoutAdd.setValue(this.hoursKilometers.getCostKilometersRoutAdditionals());
	}



	private void addHoursKilometers () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModality(new HoursKilometers(this.carrierContract, this.hoursKilometers.getVehicles(), 
					(Double)	this.spinnerCostKilometersRout.getValue(), (Double) this.spinnerCostHours.getValue(), (Double) this.spinnerCostKilometersRoutAdd.getValue(), (Double) this.spinnerCostHoursAdd.getValue()));
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModalityLogic(new HoursKilometers(this.hoursKilometers.getVehicles(), 
					(Double)	this.spinnerCostKilometersRout.getValue(), (Double) this.spinnerCostHours.getValue(), (Double) this.spinnerCostKilometersRoutAdd.getValue(), (Double) this.spinnerCostHoursAdd.getValue()));

	}

	private void updateHoursKilometers () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.updateTransportationModalityHoursKilometers(this.hoursKilometers, (Double)	this.spinnerCostKilometersRout.getValue(), (Double) this.spinnerCostHours.getValue(), 
					(Double) this.spinnerCostKilometersRoutAdd.getValue(), (Double) this.spinnerCostHoursAdd.getValue());
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.updateTransportationModalityHoursKilometersLogic(this.hoursKilometers, (Double) this.spinnerCostKilometersRout.getValue(), (Double) this.spinnerCostHours.getValue(), 
					(Double) this.spinnerCostKilometersRoutAdd.getValue(), (Double) this.spinnerCostHoursAdd.getValue());

	}

	private void cerrarFrame () {
		this.frameGerenteCreacionContratoTransporte.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}

	@Override
	public CarrierContract getCarrierContract() {

		return this.carrierContract;
	}

	@Override
	public void cambioDePanel(JPanel panel) {
		this.contentPane.remove(this.contentPane.getComponentCount() - 1); // se elimina el ultimo componente insertado
		this.contentPane.add(panel);
		this.repintarFrame(); // se repinta la informacion del frame
	}

	@Override
	public TransportModality getTransporModality() {

		return this.hoursKilometers;
	}

	private void repintarFrame () {
		repaint();
		revalidate();
	}
	
	private void deleteSavePoint () {
		try {
			ConnectionDataBase.popSavePoint();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void createSavePoint () {
		try {
			ConnectionDataBase.pushSavePoint("SavePointModality");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
