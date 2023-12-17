package JFrames;

import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import JPanels.PanelCreacionContratoServicioServiceModality;
import logica.Activity;
import logica.ServiceContract;
import logica.ServiceModality;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class FrameGerenteCreacionContratoServicioAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox <Activity> comboBoxActivities;
	private JDateChooser dateChooserReleaseDate;
	private JSpinner  spinnerPrice;
	private JLabel lblAdd;
	private PanelCreacionContratoServicioServiceModality panelCreacionContratoAlojamientoServiceModality;
	private FrameGerenteCreacionContratoServivio frameGerenteCreacionContratoServivio;
	private ServiceContract serviceContract;
	private int mouseX, mouseY;
	private JLabel lblX;


	public FrameGerenteCreacionContratoServicioAnnadir(PanelCreacionContratoServicioServiceModality pm) {
		this.panelCreacionContratoAlojamientoServiceModality = pm;
		this.frameGerenteCreacionContratoServivio = this.panelCreacionContratoAlojamientoServiceModality.getFrameGerenteCreacionContratoServivio();
		this.serviceContract = this.panelCreacionContratoAlojamientoServiceModality.getServiceContract();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
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
		lblX.setBounds(412, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblActivity = new JLabel("ACTIVITY :");
		lblActivity.setForeground(SystemColor.info);
		lblActivity.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblActivity.setBounds(82, 72, 119, 23);
		contentPane.add(lblActivity);

		JLabel lblDate = new JLabel("DATE :");
		lblDate.setForeground(SystemColor.info);
		lblDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDate.setBounds(82, 123, 77, 23);
		contentPane.add(lblDate);

		JLabel lblStartDate = new JLabel("PRICE :");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(82, 174, 77, 23);
		contentPane.add(lblStartDate);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (verificarCampos()) {
					try {
						addServiceModality();
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
		lblAdd.setBounds(107, 243, 235, 35);
		contentPane.add(lblAdd);

		comboBoxActivities = new JComboBox <Activity>();
		comboBoxActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBoxActivities.setBounds(206, 72, 161, 22);
		contentPane.add(comboBoxActivities);

		dateChooserReleaseDate = new JDateChooser();
		dateChooserReleaseDate.setBounds(206, 123, 161, 23);
		contentPane.add(dateChooserReleaseDate);

		spinnerPrice = new JSpinner();
		spinnerPrice.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerPrice.setBounds(267, 178, 100, 20);
		contentPane.add(spinnerPrice);

		JLabel lblServiceMode = new JLabel("SERVICE MODE");
		lblServiceMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblServiceMode.setBounds(27, 11, 191, 30);
		contentPane.add(lblServiceMode);

		this.llenarComboboxActivities();
	}

	private void llenarComboboxActivities () {
		ArrayList<Activity> activities = this.serviceContract.getActivitiesServiceProvider();

		for (Activity activity : activities) {
			comboBoxActivities.addItem(activity);
		}
	}

	private boolean verificarCampos () {
		return (dateChooserReleaseDate.getDate() != null);
	}

	private void addServiceModality () throws SQLException {
		if (this.serviceContract.getId() != -1) { // si es distinto de -1 se trata de un objeto real, entonces se adiciona a la logica del negocio a la base de datos
			this.serviceContract.addModality(new ServiceModality(this.serviceContract, (Activity) comboBoxActivities.getSelectedItem(), 
					dateChooserReleaseDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (Double) spinnerPrice.getValue()));
		}
		else { // se forma contraria se almacena solo en la logica del negocio
			this.serviceContract.addModalityLogic(new ServiceModality((Activity) comboBoxActivities.getSelectedItem(), 
					dateChooserReleaseDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (Double) spinnerPrice.getValue()));
		}

		panelCreacionContratoAlojamientoServiceModality.actualizarTablaModalitys(); // se actualiza la informacion de las modalidades en la tabla de modalidades
	}

	private void cerrarFrame () {
		frameGerenteCreacionContratoServivio.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}
}
