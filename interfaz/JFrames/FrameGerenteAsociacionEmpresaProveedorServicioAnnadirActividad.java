package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Activity;
import logica.ServiceProvider;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import javax.swing.border.LineBorder;

public class FrameGerenteAsociacionEmpresaProveedorServicioAnnadirActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FrameGerenteAsociacionEmpresaProveedorServicio frameGerenteAsociacionEmpresaProveedorSerivicio;
	private ServiceProvider serviceProvider;
	private JTextPane textPaneActivityDescription;
	private JLabel lblX;
	private int mouseX, mouseY;


	public FrameGerenteAsociacionEmpresaProveedorServicioAnnadirActividad(FrameGerenteAsociacionEmpresaProveedorServicio ps) {
		this.frameGerenteAsociacionEmpresaProveedorSerivicio = ps;
		this.serviceProvider = this.frameGerenteAsociacionEmpresaProveedorSerivicio.getServiceProvider();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 201);
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

		JLabel lblServiceProviderActivities = new JLabel("SERVICE PROVIDER ACTIVITIES");
		lblServiceProviderActivities.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblServiceProviderActivities.setBounds(27, 11, 332, 30);
		contentPane.add(lblServiceProviderActivities);

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

		JLabel lblDescription = new JLabel("DESCRIPTION :");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(27, 52, 132, 23);
		contentPane.add(lblDescription);

		JLabel lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textPaneActivityDescription.getText().equalsIgnoreCase("")) {
					try {
						addActivity();
						cerrarFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		lblAdd.setBounds(92, 141, 235, 35);
		contentPane.add(lblAdd);

		textPaneActivityDescription = new JTextPane();
		textPaneActivityDescription.setBounds(169, 52, 210, 78);
		contentPane.add(textPaneActivityDescription);
	}

	private void addActivity () throws SQLException {
		if (this.serviceProvider != null) { // si es distinto de null se añade a la logica del negocio y a la base de datos 
			this.serviceProvider.addActivity(new Activity(textPaneActivityDescription.getText(), this.serviceProvider.getId()));
		}
		else { // si es igual a null se añade temporalmente en la tabla
			frameGerenteAsociacionEmpresaProveedorSerivicio.addActivityTemporal(new Activity(textPaneActivityDescription.getText()));
		}
		frameGerenteAsociacionEmpresaProveedorSerivicio.actualizarTablaActivities(); // se actualiza la informacion de la tabla de las actividades
	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorSerivicio.setEnabled(true);
		dispose();
	}
}
