package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import JPanels.AddTransportModalityOperations;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import JPanels.PanelSeccionVehicles;
import logica.CarrierContract;
import logica.EstablishedRoute;
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
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido extends JFrame implements AddTransportModalityOperations {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private int mouseX, mouseY;
	private JLabel lblAdd, lblRestoreInformation;
	private JLabel lblX;
	private JSpinner spinnerCostGoing;
	private JSpinner spinnerCostLap;
	private JTextPane textPaneDescription;
	private JPanel panelTransportationMode;
	private EstablishedRoute establishedRoute;
	private JLabel lblNewLabel;
	
	@Override
	public JPanel getPanel() {
		
		return this.panelTransportationMode;
	}

	public FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido(PanelCreacionContratoTransporteTransportModality tm, EstablishedRoute e) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		this.establishedRoute = e;
		if (this.carrierContract.getId() != -1) // se crea un save point si se trata de una operacion de modificacion
			this.createSavePoint();
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		
		panelTransportationMode = new JPanel();
		panelTransportationMode.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTransportationMode.setBackground(new Color(18, 95, 115));
		panelTransportationMode.setBounds(0, 0, 548, 414);
		contentPane.add(panelTransportationMode);
		panelTransportationMode.setLayout(null);
		
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (establishedRoute.getId() != -1) { // Update
					try {
						restoreVehicles();
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

		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setForeground(SystemColor.textHighlightText);
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(127, 11, 294, 30);
		panelTransportationMode.add(lblTransportationMode);

		JLabel lblEstablishedRoute = new JLabel("( Established Route ) ");
		lblEstablishedRoute.setForeground(SystemColor.textHighlightText);
		lblEstablishedRoute.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblEstablishedRoute.setBounds(185, 35, 177, 30);
		panelTransportationMode.add(lblEstablishedRoute);

		JLabel lblDescriptionRout = new JLabel("DESCRIPTION ROUT :");
		lblDescriptionRout.setForeground(SystemColor.textHighlightText);
		lblDescriptionRout.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescriptionRout.setBounds(80, 176, 199, 23);
		panelTransportationMode.add(lblDescriptionRout);

		JLabel lblCostGoing = new JLabel("COST GOING :");
		lblCostGoing.setForeground(SystemColor.textHighlightText);
		lblCostGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostGoing.setBounds(80, 248, 136, 23);
		panelTransportationMode.add(lblCostGoing);

		spinnerCostGoing = new JSpinner();
		spinnerCostGoing.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostGoing.setBounds(362, 252, 100, 20);
		panelTransportationMode.add(spinnerCostGoing);

		JLabel lblCostLap = new JLabel("COST LAP :");
		lblCostLap.setForeground(SystemColor.textHighlightText);
		lblCostLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostLap.setBounds(80, 282, 119, 23);
		panelTransportationMode.add(lblCostLap);

		spinnerCostLap = new JSpinner();
		spinnerCostLap.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostLap.setBounds(362, 286, 100, 20);
		panelTransportationMode.add(spinnerCostLap);

		lblAdd = new JLabel();
		lblAdd.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (validarCampos()) {
					if (establishedRoute.getId() == -1) { // Add
						try {
							addEstablishedRoute();
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
							updateEstablishedRoute();
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

		textPaneDescription = new JTextPane();
		textPaneDescription.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textPaneDescription.setBounds(289, 176, 173, 61);
		panelTransportationMode.add(textPaneDescription);
		
		JLabel lblSeccionVehicles = new JLabel("SECCION VEHICLES");
		lblSeccionVehicles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelSeccionVehicles(FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido.this));
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
		lblSeccionVehicles.setBounds(100, 120, 348, 30);
		panelTransportationMode.add(lblSeccionVehicles);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido.class.getResource("/images/Logo 38x38.png")));
		lblNewLabel.setBounds(10, 16, 38, 38);
		panelTransportationMode.add(lblNewLabel);
		
		this.definirComponentes();
	}

	private void definirComponentes () {
		if (this.establishedRoute.getId() != -1) { // Update
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
				FramePrincipal.mostarFrameNotificacion("Los datos de la modalidad de tipo recorrido establecido han sido restaurados con éxito"); // se notifica de la accion al usuario
			}
		});
		lblRestoreInformation.setForeground(SystemColor.textHighlightText);
		lblRestoreInformation.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblRestoreInformation.setBounds(167, 81, 213, 30);
		panelTransportationMode.add(lblRestoreInformation);
	}
	
	private boolean validarCampos () {
		return  (!this.textPaneDescription.getText().equalsIgnoreCase("") && this.establishedRoute.getVehicles().size() != 0);
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
		this.establishedRoute.actualizarDatos(); // se actualiza los datos de los vehiculos de la modalidad con la informacion de la base de datos
	}

	private void definirTextos () {

		if (this.establishedRoute.getId() != -1) { // update
			this.definirFormulario();	
			this.lblAdd.setText("UPDATE");
		}
		else
			this.lblAdd.setText("ADD");
	}

	private void definirFormulario () {
		this.textPaneDescription.setText(this.establishedRoute.getDescriptionRout());
		this.spinnerCostGoing.setValue(this.establishedRoute.getCostGoing());
		this.spinnerCostLap.setValue(this.establishedRoute.getCostLap());
	}

	private void addEstablishedRoute () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModality(new EstablishedRoute(this.carrierContract, this.establishedRoute.getVehicles(), this.textPaneDescription.getText(), 
					(Double) this.spinnerCostGoing.getValue(), (Double) this.spinnerCostLap.getValue()));
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModalityLogic(new EstablishedRoute(this.establishedRoute.getVehicles(), this.textPaneDescription.getText(), 
					(Double) this.spinnerCostGoing.getValue(), (Double) this.spinnerCostLap.getValue()));
	}
	
	private void updateEstablishedRoute () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.updateTransportationModalityEstablishedRoute(this.establishedRoute, this.textPaneDescription.getText(), 
					(Double) this.spinnerCostGoing.getValue(), (Double) this.spinnerCostLap.getValue());
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.updateTransportationModalityEstablishedRouteLogic(this.establishedRoute, this.textPaneDescription.getText(), 
					(Double) this.spinnerCostGoing.getValue(), (Double) this.spinnerCostLap.getValue());
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

		return this.establishedRoute;
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
