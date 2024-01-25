package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.JTextField;
import java.awt.Cursor;

public class FrameGerenteAsociacionEmpresaProveedorServicioAnnadirActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FrameGerenteAsociacionEmpresaProveedorServicio frameGerenteAsociacionEmpresaProveedorSerivicio;
	private ServiceProvider serviceProvider;
	private JTextPane textPaneActivityDescription;
	private JLabel lblX;
	private int mouseX, mouseY;
	private JTextField textFieldNameActivity;


	public FrameGerenteAsociacionEmpresaProveedorServicioAnnadirActividad(FrameGerenteAsociacionEmpresaProveedorServicio ps) {
		this.frameGerenteAsociacionEmpresaProveedorSerivicio = ps;
		this.serviceProvider = this.frameGerenteAsociacionEmpresaProveedorSerivicio.getServiceProvider();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 331);
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

		JLabel lblServiceProviderActivities = new JLabel("SERVICE PROVIDER ACTIVITIES");
		lblServiceProviderActivities.setForeground(SystemColor.textHighlightText);
		lblServiceProviderActivities.setFont(new Font("Dialog", Font.BOLD, 21));
		lblServiceProviderActivities.setBounds(27, 11, 332, 30);
		contentPane.add(lblServiceProviderActivities);

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
		lblX.setBounds(384, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblDescription = new JLabel("DESCRIPTION :");
		lblDescription.setForeground(SystemColor.textHighlightText);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescription.setBounds(80, 125, 262, 23);
		contentPane.add(lblDescription);

		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					try {
						addActivity();
						frameGerenteAsociacionEmpresaProveedorSerivicio.actualizarTablaActivities(); // se actualiza la informacion de la tabla de las actividades
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
		lblAdd.setBounds(94, 267, 235, 35);
		contentPane.add(lblAdd);

		textPaneActivityDescription = new JTextPane();
		textPaneActivityDescription.setBounds(80, 159, 262, 78);
		contentPane.add(textPaneActivityDescription);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(80, 83, 74, 23);
		contentPane.add(lblName);
		
		textFieldNameActivity = new JTextField();
		textFieldNameActivity.setBounds(164, 87, 178, 20);
		contentPane.add(textFieldNameActivity);
		textFieldNameActivity.setColumns(10);
	}
	
	private boolean verificarCampos () {
		return !this.textFieldNameActivity.getText().equalsIgnoreCase("");
	}

	private void addActivity () throws SQLException {
		if (this.serviceProvider.getId() != -1){ // si es distinto a -1 se añade a la logica del negocio y a la base de datos 
			this.serviceProvider.addActivity(new Activity(this.textFieldNameActivity.getText(), textPaneActivityDescription.getText(), this.serviceProvider.getId()));
		}
		else { // si es igual a -1 se añade a la logica del negocio
			this.serviceProvider.addActivityLogic(new Activity(this.textFieldNameActivity.getText(), textPaneActivityDescription.getText()));
		}
		
	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorSerivicio.setEnabled(true);
		dispose();
	}
}
