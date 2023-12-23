package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import logica.CarrierContract;
import logica.EstablishedRoute;
import logica.Vehicle;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextPane;

public class FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private int mouseX, mouseY;
	private JLabel lblAdd;
	private JLabel lblX;
	private JComboBox <Vehicle> comboBoxVehicles;
	private JSpinner spinnerCostGoing;
	private JSpinner spinnerCostLap;
	private JTextPane textPaneDescription;


	public FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido(PanelCreacionContratoTransporteTransportModality tm) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));
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
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(27, 11, 294, 30);
		contentPane.add(lblTransportationMode);

		JLabel lblEstablishedRoute = new JLabel("( Established Route ) ");
		lblEstablishedRoute.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblEstablishedRoute.setBounds(79, 35, 177, 30);
		contentPane.add(lblEstablishedRoute);

		JLabel lblVehicle = new JLabel("VEHICLE :");
		lblVehicle.setForeground(SystemColor.info);
		lblVehicle.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVehicle.setBounds(43, 76, 119, 23);
		contentPane.add(lblVehicle);

		comboBoxVehicles = new JComboBox <Vehicle>();
		comboBoxVehicles.setBounds(264, 79, 161, 22);
		contentPane.add(comboBoxVehicles);

		JLabel lblDescriptionRout = new JLabel("DESCRIPTION ROUT :");
		lblDescriptionRout.setForeground(SystemColor.info);
		lblDescriptionRout.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescriptionRout.setBounds(43, 118, 199, 23);
		contentPane.add(lblDescriptionRout);

		JLabel lblCostGoing = new JLabel("COST GOING :");
		lblCostGoing.setForeground(SystemColor.info);
		lblCostGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostGoing.setBounds(43, 190, 136, 23);
		contentPane.add(lblCostGoing);

		spinnerCostGoing = new JSpinner();
		spinnerCostGoing.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostGoing.setBounds(325, 194, 100, 20);
		contentPane.add(spinnerCostGoing);

		JLabel lblCostLap = new JLabel("COST LAP :");
		lblCostLap.setForeground(SystemColor.info);
		lblCostLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostLap.setBounds(43, 224, 119, 23);
		contentPane.add(lblCostLap);

		spinnerCostLap = new JSpinner();
		spinnerCostLap.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostLap.setBounds(325, 228, 100, 20);
		contentPane.add(spinnerCostLap);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addEstablishedRoute();
					cerrarFrame(); // se cierra el frame actual
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
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(115, 270, 235, 35);
		contentPane.add(lblAdd);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(252, 118, 173, 61);
		contentPane.add(textPaneDescription);

		this.llenarComboBoxVehicles();
	}

	private void llenarComboBoxVehicles () {
		ArrayList<Vehicle> vehicles = this.carrierContract.getVehiclesProvider(); // se obtienen los vehiculos del provedor transporte del contrato
		// Se añaden al comboBox
		for (Vehicle vehicle : vehicles) { 
			comboBoxVehicles.addItem(vehicle);
		}
	}


	private void addEstablishedRoute () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModality(new EstablishedRoute(this.carrierContract, (Vehicle) this.comboBoxVehicles.getSelectedItem(), this.textPaneDescription.getText(), 
					(Double) this.spinnerCostGoing.getValue(), (Double) this.spinnerCostLap.getValue()));
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModalityLogic(new EstablishedRoute((Vehicle) this.comboBoxVehicles.getSelectedItem(), this.textPaneDescription.getText(), 
					(Double) this.spinnerCostGoing.getValue(), (Double) this.spinnerCostLap.getValue()));

		this.panelCreacionContratoTransporteTransportModality.actualizarTablaModalitys(); // se actualiza la informacion de la tablas de las modalidades del contrato


	}

	private void cerrarFrame () {
		this.frameGerenteCreacionContratoTransporte.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}
}
