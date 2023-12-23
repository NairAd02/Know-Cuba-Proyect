package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import logica.CarrierContract;
import logica.CostKilometers;
import logica.Vehicle;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SpinnerNumberModel;

public class FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox <Vehicle> comboBoxVehicles;
	private JSpinner spinnerCostKilometersGoing;
	private JSpinner spinnerCostKilometersLap;
	private JSpinner spinnerCostHoursWait;
	private JLabel lblAdd;
	private JLabel lblX;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private int mouseX, mouseY;


	public FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers(PanelCreacionContratoTransporteTransportModality tm) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

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
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);
		setLocationRelativeTo(null);
		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(27, 11, 294, 30);
		contentPane.add(lblTransportationMode);

		JLabel lblCostKilometers = new JLabel("( Cost Kilometers ) ");
		lblCostKilometers.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCostKilometers.setBounds(79, 35, 173, 30);
		contentPane.add(lblCostKilometers);

		JLabel lblRoomType = new JLabel("VEHICLE :");
		lblRoomType.setForeground(SystemColor.info);
		lblRoomType.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomType.setBounds(43, 90, 119, 23);
		contentPane.add(lblRoomType);

		comboBoxVehicles = new JComboBox <Vehicle>();
		comboBoxVehicles.setBounds(264, 90, 161, 22);
		contentPane.add(comboBoxVehicles);

		JLabel lblCostKilometersGoing = new JLabel("COST KILOMETERS GOING :");
		lblCostKilometersGoing.setForeground(SystemColor.info);
		lblCostKilometersGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersGoing.setBounds(43, 130, 249, 23);
		contentPane.add(lblCostKilometersGoing);

		spinnerCostKilometersGoing = new JSpinner();
		spinnerCostKilometersGoing.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersGoing.setBounds(324, 131, 100, 20);
		contentPane.add(spinnerCostKilometersGoing);

		JLabel lblCostKilometersLap = new JLabel("COST KILOMETERS LAP :");
		lblCostKilometersLap.setForeground(SystemColor.info);
		lblCostKilometersLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersLap.setBounds(43, 170, 225, 23);
		contentPane.add(lblCostKilometersLap);

		spinnerCostKilometersLap = new JSpinner();
		spinnerCostKilometersLap.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersLap.setBounds(324, 171, 100, 20);
		contentPane.add(spinnerCostKilometersLap);

		JLabel lblCostHoursWait = new JLabel("COST HOURS WAIT :");
		lblCostHoursWait.setForeground(SystemColor.info);
		lblCostHoursWait.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursWait.setBounds(43, 210, 278, 23);
		contentPane.add(lblCostHoursWait);

		spinnerCostHoursWait = new JSpinner();
		spinnerCostHoursWait.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHoursWait.setBounds(324, 211, 100, 20);
		contentPane.add(spinnerCostHoursWait);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addCostKilometers();
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

		this.llenarComboBoxVehicles();
	}

	private void llenarComboBoxVehicles () {
		ArrayList<Vehicle> vehicles = this.carrierContract.getVehiclesProvider(); // se obtienen los vehiculos del provedor transporte del contrato
		// Se añaden al comboBox
		for (Vehicle vehicle : vehicles) { 
			comboBoxVehicles.addItem(vehicle);
		}
	}


	private void addCostKilometers () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModality(new CostKilometers(this.carrierContract, (Vehicle) this.comboBoxVehicles.getSelectedItem(), (Double) spinnerCostKilometersGoing.getValue(), 
					(Double) this.spinnerCostKilometersLap.getValue(), (Double) this.spinnerCostHoursWait.getValue()));
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModalityLogic(new CostKilometers((Vehicle) this.comboBoxVehicles.getSelectedItem(), (Double) spinnerCostKilometersGoing.getValue(), 
					(Double) this.spinnerCostKilometersLap.getValue(), (Double) this.spinnerCostHoursWait.getValue()));

		this.panelCreacionContratoTransporteTransportModality.actualizarTablaModalitys(); // se actualiza la informacion de la tablas de las modalidades del contrato


	}

	private void cerrarFrame () {
		this.frameGerenteCreacionContratoTransporte.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}

}
