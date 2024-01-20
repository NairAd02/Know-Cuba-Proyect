package JFrames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import logica.TransportationProvider;
import logica.Vehicle;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.ZoneId;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import javax.swing.SpinnerNumberModel;

public class FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldLock;
	private FrameGerenteAsociacionEmpresaProveedorTransporte frameGerenteAsociacionEmpresaProveedorTransporte;
	private int mouseX, mouseY;
	private JLabel lblAdd;
	private TransportationProvider transportationProvider;
	private JLabel lblX;
	private JTextField textFieldBrand;
	private JLabel lblMarca;
	private JLabel lblCapacidadSinEquipaje;
	private JSpinner spinnercapacityWithoutLuggage;
	private JLabel lblCapacidadConEquipaje;
	private JSpinner spinnerCapacityWithLuggage;
	private JLabel lblFechaDeProduccin;
	private JDateChooser dateChooserDateProduction;


	public FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo(FrameGerenteAsociacionEmpresaProveedorTransporte pt) {
		this.frameGerenteAsociacionEmpresaProveedorTransporte = pt;
		this.transportationProvider = this.frameGerenteAsociacionEmpresaProveedorTransporte.getTransportationProvider();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 327);
		contentPane = new JPanel();
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
		contentPane.setBackground(new Color(18, 95, 115));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTransportProviderVehicles = new JLabel("TRANSPORT PROVIDER VEHICLES");
		lblTransportProviderVehicles.setForeground(SystemColor.textHighlightText);
		lblTransportProviderVehicles.setFont(new Font("Dialog", Font.BOLD, 21));
		lblTransportProviderVehicles.setBounds(27, 11, 363, 30);
		contentPane.add(lblTransportProviderVehicles);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lblX.setBounds(459, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblLock = new JLabel("Chapa :");
		lblLock.setForeground(SystemColor.textHighlightText);
		lblLock.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLock.setBounds(22, 52, 70, 23);
		contentPane.add(lblLock);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					try {
						addVehicle();
						frameGerenteAsociacionEmpresaProveedorTransporte.actualizarTablaVehicles(); // se actualiza la informacion de la tabla de las actividades
						cerrarFrame();
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
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(133, 281, 235, 35);
		contentPane.add(lblAdd);

		textFieldLock = new JTextField();
		textFieldLock.setBounds(95, 56, 100, 20);
		contentPane.add(textFieldLock);
		textFieldLock.setColumns(10);

		textFieldBrand = new JTextField();
		textFieldBrand.setColumns(10);
		textFieldBrand.setBounds(384, 56, 100, 20);
		contentPane.add(textFieldBrand);

		lblMarca = new JLabel("Marca :");
		lblMarca.setForeground(SystemColor.textHighlightText);
		lblMarca.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMarca.setBounds(311, 52, 70, 23);
		contentPane.add(lblMarca);

		lblCapacidadSinEquipaje = new JLabel("Capacidad sin Equipaje :");
		lblCapacidadSinEquipaje.setForeground(SystemColor.textHighlightText);
		lblCapacidadSinEquipaje.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCapacidadSinEquipaje.setBounds(22, 103, 226, 23);
		contentPane.add(lblCapacidadSinEquipaje);

		spinnercapacityWithoutLuggage = new JSpinner();
		spinnercapacityWithoutLuggage.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnercapacityWithoutLuggage.setBounds(95, 137, 70, 20);
		contentPane.add(spinnercapacityWithoutLuggage);

		lblCapacidadConEquipaje = new JLabel("Capacidad con Equipaje :");
		lblCapacidadConEquipaje.setForeground(SystemColor.textHighlightText);
		lblCapacidadConEquipaje.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCapacidadConEquipaje.setBounds(258, 103, 226, 23);
		contentPane.add(lblCapacidadConEquipaje);

		spinnerCapacityWithLuggage = new JSpinner();
		spinnerCapacityWithLuggage.setBounds(352, 137, 70, 20);
		contentPane.add(spinnerCapacityWithLuggage);

		lblFechaDeProduccin = new JLabel("Fecha de Producción :");
		lblFechaDeProduccin.setForeground(SystemColor.textHighlightText);
		lblFechaDeProduccin.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblFechaDeProduccin.setBounds(143, 178, 211, 23);
		contentPane.add(lblFechaDeProduccin);

		dateChooserDateProduction = new JDateChooser();
		dateChooserDateProduction.setBounds(198, 212, 102, 20);
		contentPane.add(dateChooserDateProduction);
	}

	private boolean verificarCampos () {
		return !this.textFieldBrand.getText().equalsIgnoreCase("") && !this.textFieldLock.getText().equalsIgnoreCase("") && this.dateChooserDateProduction.getDate() != null;
	}

	private void addVehicle () throws SQLException {
		if (this.transportationProvider.getId() != -1) { // si es distinto de -1 se añade a la logica del negocio y a la base de datos 
			this.transportationProvider.addVehicle(new Vehicle(this.textFieldLock.getText(), this.textFieldBrand.getText(), (Integer) this.spinnercapacityWithoutLuggage.getValue(),
					(Integer)  this.spinnerCapacityWithLuggage.getValue(), (Integer) this.spinnercapacityWithoutLuggage.getValue(),
					this.dateChooserDateProduction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.transportationProvider.getId()));
		}
		else { // si es igual a -1 se añade solamente a la logica del negocio
			this.transportationProvider.addVehicleLogic(new Vehicle(this.textFieldLock.getText(), this.textFieldBrand.getText(), (Integer) this.spinnercapacityWithoutLuggage.getValue(),
					(Integer)  this.spinnerCapacityWithLuggage.getValue(), (Integer) this.spinnercapacityWithoutLuggage.getValue(),
					this.dateChooserDateProduction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
		}

	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorTransporte.setEnabled(true);
		dispose();
	}
}
