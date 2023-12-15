package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.TransportationProvider;
import logica.Vehicle;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldLock;
	private FrameGerenteAsociacionEmpresaProveedorTransporte frameGerenteAsociacionEmpresaProveedorTransporte;
	private int mouseX, mouseY;
	private JLabel lblAdd;
	private TransportationProvider transportationProvider;
	private JLabel lblX;


	public FrameGerenteAsociacionEmpresaProveedorTransporteAnnadirVehiculo(FrameGerenteAsociacionEmpresaProveedorTransporte pt) {
		this.frameGerenteAsociacionEmpresaProveedorTransporte = pt;
		this.transportationProvider = this.frameGerenteAsociacionEmpresaProveedorTransporte.getTransportationProvider();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 201);
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
		contentPane.setBackground(new Color(5, 150, 177));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTransportProviderVehicles = new JLabel("TRANSPORT PROVIDER VEHICLES");
		lblTransportProviderVehicles.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblTransportProviderVehicles.setBounds(27, 11, 341, 30);
		contentPane.add(lblTransportProviderVehicles);

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
		lblX.setBounds(384, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblLock = new JLabel("LOCK :");
		lblLock.setForeground(SystemColor.info);
		lblLock.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLock.setBounds(112, 74, 70, 23);
		contentPane.add(lblLock);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textFieldLock.getText().equalsIgnoreCase("")) {
					try {
						addVehicle();
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
		lblAdd.setBounds(93, 141, 235, 35);
		contentPane.add(lblAdd);

		textFieldLock = new JTextField();
		textFieldLock.setBounds(219, 75, 100, 20);
		contentPane.add(textFieldLock);
		textFieldLock.setColumns(10);
	}

	private void addVehicle () throws SQLException {
		if (this.transportationProvider != null) { // si es distinto de null se añade a la logica del negocio y a la base de datos 
			this.transportationProvider.addVehicle(new Vehicle(textFieldLock.getText(), transportationProvider.getId()));
		}
		else { // si es igual a null se añade temporalmente en la tabla
			frameGerenteAsociacionEmpresaProveedorTransporte.addVehicleTemporal(new Vehicle(textFieldLock.getText()));
		}
		frameGerenteAsociacionEmpresaProveedorTransporte.actualizarTablaVehicles(); // se actualiza la informacion de la tabla de las actividades
	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorTransporte.setEnabled(true);
		dispose();
	}


}
