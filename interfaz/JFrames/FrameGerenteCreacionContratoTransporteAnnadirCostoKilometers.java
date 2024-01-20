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
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers extends JFrame {

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


	public FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers(PanelCreacionContratoTransporteTransportModality tm) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
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
		lblX.setBounds(510, 0, 38, 38);
		contentPane.add(lblX);
		setLocationRelativeTo(null);
		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setForeground(SystemColor.textHighlightText);
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(127, 27, 294, 30);
		contentPane.add(lblTransportationMode);

		JLabel lblCostKilometers = new JLabel("( Cost Kilometers ) ");
		lblCostKilometers.setForeground(SystemColor.textHighlightText);
		lblCostKilometers.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCostKilometers.setBounds(179, 51, 173, 30);
		contentPane.add(lblCostKilometers);

		JLabel lblCostKilometersGoing = new JLabel("COST KILOMETERS GOING :");
		lblCostKilometersGoing.setForeground(SystemColor.textHighlightText);
		lblCostKilometersGoing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersGoing.setBounds(71, 174, 249, 23);
		contentPane.add(lblCostKilometersGoing);

		spinnerCostKilometersGoing = new JSpinner();
		spinnerCostKilometersGoing.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCostKilometersGoing.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersGoing.setBounds(352, 175, 100, 20);
		contentPane.add(spinnerCostKilometersGoing);

		JLabel lblCostKilometersLap = new JLabel("COST KILOMETERS LAP :");
		lblCostKilometersLap.setForeground(SystemColor.textHighlightText);
		lblCostKilometersLap.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersLap.setBounds(71, 214, 225, 23);
		contentPane.add(lblCostKilometersLap);

		spinnerCostKilometersLap = new JSpinner();
		spinnerCostKilometersLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCostKilometersLap.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersLap.setBounds(352, 215, 100, 20);
		contentPane.add(spinnerCostKilometersLap);

		JLabel lblCostHoursWait = new JLabel("COST HOURS WAIT :");
		lblCostHoursWait.setForeground(SystemColor.textHighlightText);
		lblCostHoursWait.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursWait.setBounds(71, 254, 278, 23);
		contentPane.add(lblCostHoursWait);

		spinnerCostHoursWait = new JSpinner();
		spinnerCostHoursWait.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCostHoursWait.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHoursWait.setBounds(352, 255, 100, 20);
		contentPane.add(spinnerCostHoursWait);

		lblAdd = new JLabel("ADD");
		lblAdd.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
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
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(163, 357, 235, 35);
		contentPane.add(lblAdd);
		
		lblSeccionVehicles = new JLabel("SECCION VEHICLES");
		lblSeccionVehicles.setOpaque(true);
		lblSeccionVehicles.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionVehicles.setForeground(SystemColor.textText);
		lblSeccionVehicles.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionVehicles.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblSeccionVehicles.setBackground(SystemColor.info);
		lblSeccionVehicles.setBounds(88, 122, 348, 30);
		contentPane.add(lblSeccionVehicles);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers.class.getResource("/images/Logo 38x38.png")));
		lblNewLabel.setBounds(10, 11, 38, 38);
		contentPane.add(lblNewLabel);

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
