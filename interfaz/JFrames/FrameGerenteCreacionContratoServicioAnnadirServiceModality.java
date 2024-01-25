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
import java.util.Date;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class FrameGerenteCreacionContratoServicioAnnadirServiceModality extends JFrame {

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
	private ServiceModality serviceModality;
	private JLabel lblRestore;


	public FrameGerenteCreacionContratoServicioAnnadirServiceModality(PanelCreacionContratoServicioServiceModality pm, ServiceModality s) {
		this.panelCreacionContratoAlojamientoServiceModality = pm;
		this.frameGerenteCreacionContratoServivio = this.panelCreacionContratoAlojamientoServiceModality.getFrameGerenteCreacionContratoServivio();
		this.serviceContract = this.panelCreacionContratoAlojamientoServiceModality.getServiceContract();
		this.serviceModality = s;
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
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane.setBackground(new Color(18, 95, 115));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		lblX.setBounds(412, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblActivity = new JLabel("ACTIVITY :");
		lblActivity.setForeground(SystemColor.textHighlightText);
		lblActivity.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblActivity.setBounds(82, 72, 119, 23);
		contentPane.add(lblActivity);

		JLabel lblDate = new JLabel("DATE :");
		lblDate.setForeground(SystemColor.textHighlightText);
		lblDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDate.setBounds(82, 123, 77, 23);
		contentPane.add(lblDate);

		JLabel lblStartDate = new JLabel("PRICE :");
		lblStartDate.setForeground(SystemColor.textHighlightText);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(82, 174, 77, 23);
		contentPane.add(lblStartDate);

		lblAdd = new JLabel();
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.setText("ADD");
		lblAdd.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					if (serviceModality == null) { // Add
						try {
							addServiceModality();
							panelCreacionContratoAlojamientoServiceModality.actualizarTablaModalitys(); // se actualiza la informacion de las modalidades en la tabla de modalidades
							FramePrincipal.mostarFrameNotificacion("Ha sido insertada con éxito la modalidad"); // se notifica de la accion realiza al usuario
							cerrarFrame();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else { // Update
						try {
							updateServiceModality();
							panelCreacionContratoAlojamientoServiceModality.actualizarTablaModalitys(); // se actualiza la informacion de las modalidades en la tabla de modalidades
							FramePrincipal.mostarFrameNotificacion("Ha sido insertada con éxito la modalidad"); // se notifica de la accion realiza al usuario
							cerrarFrame();
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
		lblAdd.setBounds(107, 243, 235, 35);
		contentPane.add(lblAdd);

		comboBoxActivities = new JComboBox <Activity>();
		comboBoxActivities.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBoxActivities.setBounds(206, 72, 161, 22);
		contentPane.add(comboBoxActivities);

		dateChooserReleaseDate = new JDateChooser();
		dateChooserReleaseDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooserReleaseDate.setBounds(206, 123, 161, 23);
		contentPane.add(dateChooserReleaseDate);

		spinnerPrice = new JSpinner();
		spinnerPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerPrice.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerPrice.setBounds(267, 178, 100, 20);
		contentPane.add(spinnerPrice);

		JLabel lblTransportationMode = new JLabel("PLANNED ACTIVITY");
		lblTransportationMode.setForeground(SystemColor.textHighlightText);
		lblTransportationMode.setFont(new Font("Dialog", Font.BOLD, 26));
		lblTransportationMode.setBounds(10, 8, 265, 30);
		contentPane.add(lblTransportationMode);


		this.definirComponentes();
		this.llenarComboboxActivities();
		this.definirTextos();
	}

	private void definirComponentes () {
		if (this.serviceModality != null) // Update
			this.addLblRestore();
	}

	private void addLblRestore () {
		lblRestore = new JLabel("Restore");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestore.setForeground(SystemColor.textHighlightText);
		lblRestore.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRestore.setBounds(309, 11, 77, 30);
		contentPane.add(lblRestore);
	}

	private void restoreInformation () {
		this.definirTextos();
	}

	private void definirTextos () {
		if (this.serviceModality != null) { // Update
			this.comboBoxActivities.setSelectedItem(this.serviceModality.getActivity());
			this.dateChooserReleaseDate.setDate(Date.from(this.serviceModality.getReleasedDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			this.spinnerPrice.setValue(this.serviceModality.price());
			this.lblAdd.setText("UPDATE");
		}
		else // Add
			this.lblAdd.setText("ADD");
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

	}

	private void updateServiceModality () throws SQLException {
		if (this.serviceContract.getId() != -1) { // si es distinto de -1 se trata de un objeto real, entonces se adiciona a la logica del negocio a la base de datos
			this.serviceContract.updateServiceModality(this.serviceModality, (Activity) comboBoxActivities.getSelectedItem(), 
					dateChooserReleaseDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (Double) spinnerPrice.getValue());
		}
		else { // se forma contraria se almacena solo en la logica del negocio
			this.serviceContract.updateServiceModalityLogic(this.serviceModality, (Activity) comboBoxActivities.getSelectedItem(), 
					dateChooserReleaseDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (Double) spinnerPrice.getValue());
		}

	}

	private void cerrarFrame () {
		frameGerenteCreacionContratoServivio.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}
}
