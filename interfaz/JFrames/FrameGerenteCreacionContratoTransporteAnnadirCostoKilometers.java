package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.AddTransportModalityOperations;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import JPanels.PanelSeccionVehicles;
import logica.CarrierContract;
import logica.CostKilometers;
import logica.TransportModality;
import utils.ConnectionDataBase;

import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers extends JFrame implements AddTransportModalityOperations{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner spinnerCostKilometersGoing;
	private JSpinner spinnerCostKilometersLap;
	private JSpinner spinnerCostHoursWait;
	private JLabel lblAdd;
	private JLabel lblX;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private int mouseX, mouseY;
	private JLabel lblSeccionVehicles;
	private CostKilometers costKilometers;
	private JLabel lblRestoreInformation;
	private JPanel panelTransportationMode;

	@Override
	public JPanel getPanel() {

		return this.panelTransportationMode;
	}


	public FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers(PanelCreacionContratoTransporteTransportModality tm, CostKilometers c) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.costKilometers = c;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		if (this.carrierContract.getId() != -1) // se crea un save point si se trata de una operacion de modificacion
			this.createSavePoint(); // se crea un savePoint

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.setBackground(new Color(18, 95, 115));

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
			}
		});

		panelTransportationMode = new JPanel();
		panelTransportationMode.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTransportationMode.setBackground(new Color(18, 95, 115));
		panelTransportationMode.setBounds(0, 0, 548, 414);
		contentPane.add(panelTransportationMode);
		panelTransportationMode.setLayout(null);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (costKilometers.getId() != -1) { // update
					try {
						restoreVehicles();
						deleteSavePoint(); // se elimina el savePointCreado
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		panelTransportationMode.add(lblX);

		setLocationRelativeTo(null);
		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setForeground(SystemColor.textHighlightText);
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(127, 27, 294, 30);
		panelTransportationMode.add(lblTransportationMode);

		JLabel lblCostKilometers = new JLabel("( Cost Kilometers ) ");
		lblCostKilometers.setForeground(SystemColor.textHighlightText);
		lblCostKilometers.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCostKilometers.setBounds(179, 51, 173, 30);
		panelTransportationMode.add(lblCostKilometers);

		JLabel lblCostKilometersGoing = new JLabel("COST KILOMETERS GOING :");
		lblCostKilometersGoing.setForeground(SystemColor.textHighlightText);
		lblCostKilometersGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersGoing.setBounds(71, 174, 249, 23);
		panelTransportationMode.add(lblCostKilometersGoing);

		spinnerCostKilometersGoing = new JSpinner();
		spinnerCostKilometersGoing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCostKilometersGoing.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersGoing.setBounds(352, 175, 100, 20);
		panelTransportationMode.add(spinnerCostKilometersGoing);

		JLabel lblCostKilometersLap = new JLabel("COST KILOMETERS LAP :");
		lblCostKilometersLap.setForeground(SystemColor.textHighlightText);
		lblCostKilometersLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersLap.setBounds(71, 214, 225, 23);
		panelTransportationMode.add(lblCostKilometersLap);

		spinnerCostKilometersLap = new JSpinner();
		spinnerCostKilometersLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCostKilometersLap.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersLap.setBounds(352, 215, 100, 20);
		panelTransportationMode.add(spinnerCostKilometersLap);

		JLabel lblCostHoursWait = new JLabel("COST HOURS WAIT :");
		lblCostHoursWait.setForeground(SystemColor.textHighlightText);
		lblCostHoursWait.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursWait.setBounds(71, 254, 278, 23);
		panelTransportationMode.add(lblCostHoursWait);

		spinnerCostHoursWait = new JSpinner();
		spinnerCostHoursWait.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCostHoursWait.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHoursWait.setBounds(352, 255, 100, 20);
		panelTransportationMode.add(spinnerCostHoursWait);

		lblAdd = new JLabel();
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.setText("ADD");
		lblAdd.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validarCampos()) {
					if (costKilometers.getId() == -1) { // Add
						try {
							addCostKilometers();
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
							updateCostKilometers();
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
		panelTransportationMode.add(lblAdd);

		lblSeccionVehicles = new JLabel("SECCION VEHICLES");
		lblSeccionVehicles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSeccionVehicles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelSeccionVehicles(FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers.this));
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
		panelTransportationMode.add(lblSeccionVehicles);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers.class.getResource("/images/Logo 38x38.png")));
		lblNewLabel.setBounds(10, 11, 38, 38);
		panelTransportationMode.add(lblNewLabel);




		this.definirComponentes();


	}

	private void definirComponentes () {
		if (this.costKilometers.getId() != -1) { // Update
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
				FramePrincipal.mostarFrameNotificacion("Los datos de la modalidad de tipo costo por kilometro han sido restaurados con éxito"); // se notifica de la accion al usuario
			}
		});
		lblRestoreInformation.setForeground(SystemColor.textHighlightText);
		lblRestoreInformation.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblRestoreInformation.setBounds(167, 81, 213, 30);
		panelTransportationMode.add(lblRestoreInformation);
	}

	private boolean validarCampos () {
		return this.costKilometers.getVehicles().size() != 0;
	}


	private void addCostKilometers () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModality(new CostKilometers(this.carrierContract, this.costKilometers.getVehicles(), (Double) spinnerCostKilometersGoing.getValue(), 
					(Double) this.spinnerCostKilometersLap.getValue(), (Double) this.spinnerCostHoursWait.getValue()));
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModalityLogic(new CostKilometers(this.costKilometers.getVehicles(), (Double) spinnerCostKilometersGoing.getValue(), 
					(Double) this.spinnerCostKilometersLap.getValue(), (Double) this.spinnerCostHoursWait.getValue()));

	}

	private void updateCostKilometers () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.updateTransportModalityCostKilometers(this.costKilometers, (Double) spinnerCostKilometersGoing.getValue(), 
					(Double) this.spinnerCostKilometersLap.getValue(), (Double) this.spinnerCostHoursWait.getValue());
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.updateTransportModalityCostKilometersLogic(this.costKilometers, (Double) spinnerCostKilometersGoing.getValue(), 
					(Double) this.spinnerCostKilometersLap.getValue(), (Double) this.spinnerCostHoursWait.getValue());
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
		this.costKilometers.actualizarDatos(); // se actualiza los datos de los vehiculos de la modalidad con la informacion de la base de datos
	}

	private void definirTextos () {

		if (this.costKilometers.getId() != -1) { // update
			this.definirFormulario();	
			this.lblAdd.setText("UPDATE");
		}
		else
			this.lblAdd.setText("ADD");
	}

	private void definirFormulario () {
		this.spinnerCostHoursWait.setValue(this.costKilometers.getCostHoursWait());
		this.spinnerCostKilometersGoing.setValue(this.costKilometers.getCostKilometersGoing());
		this.spinnerCostKilometersLap.setValue(this.costKilometers.getCostKilometersLap());
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

		return this.costKilometers;
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
