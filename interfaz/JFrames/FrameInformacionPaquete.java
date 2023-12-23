package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Controller;
import logica.Modality;
import logica.TouristPackage;
import utils.ConnectionDataBase;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FrameInformacionPaquete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblOperation;
	private JLabel lblModalities;
	private JLabel lblAccommodationModalitys;
	private JLabel lblTransportModalitys;
	private JLabel lblServiceModalitys;
	private JPanel panel;
	private JLabel lblTitleFrame;
	private TouristPackage touristPackage;
	private JLabel lblName;
	private JTextField textFieldName;
	private JLabel lblX;
	private int mouseX, mouseY;


	public TouristPackage getTouristPackage() {
		return touristPackage;
	}



	public void setTouristPackage(TouristPackage touristPackage) {
		this.touristPackage = touristPackage;
	}



	public FrameInformacionPaquete(TouristPackage t) {
		this.touristPackage = t;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 650, 501);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);

		lblModalities = new JLabel("MODALITIES ");
		lblModalities.setForeground(SystemColor.info);
		lblModalities.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblModalities.setBounds(237, 132, 151, 30);
		panel.add(lblModalities);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (touristPackage.getId() != -1) {
					try {
						ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas en la base de datos
						touristPackage.actualizarDatos(); // se actualizan los datos del paquete turistico para evitar inconsistencias
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
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
		lblX.setBounds(612, 0, 38, 38);
		panel.add(lblX);

		lblAccommodationModalitys = new JLabel("");
		lblAccommodationModalitys.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir frameDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir = new FrameDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir(FrameInformacionPaquete.this);
				frameDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir.setVisible(true);
				setEnabled(false); // se inhabilita el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAccommodationModalitys.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAccommodationModalitys.setIcon(new ImageIcon(FrameInformacionPaquete.class.getResource("/images/hotel1.png")));
		lblAccommodationModalitys.setOpaque(true);
		lblAccommodationModalitys.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccommodationModalitys.setBackground(new Color(18, 95, 115));
		lblAccommodationModalitys.setBounds(88, 192, 181, 87);
		panel.add(lblAccommodationModalitys);

		lblTransportModalitys = new JLabel("");
		lblTransportModalitys.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir frameAddTransporModality = new FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir(FrameInformacionPaquete.this);
				frameAddTransporModality.setVisible(true);
				setEnabled(false); // se inhabilita el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblTransportModalitys.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTransportModalitys.setIcon(new ImageIcon(FrameInformacionPaquete.class.getResource("/images/carro3.png")));
		lblTransportModalitys.setOpaque(true);
		lblTransportModalitys.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportModalitys.setBackground(new Color(18, 95, 115));
		lblTransportModalitys.setBounds(370, 192, 181, 87);
		panel.add(lblTransportModalitys);

		lblServiceModalitys = new JLabel("");
		lblServiceModalitys.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameDisennadorPaqueteTuristicoModalidadServicioAnnadir frameAddModalityService = new FrameDisennadorPaqueteTuristicoModalidadServicioAnnadir(FrameInformacionPaquete.this);
				frameAddModalityService.setVisible(true);
				setEnabled(false); // se inhabilita el frame
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblServiceModalitys.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblServiceModalitys.setIcon(new ImageIcon(FrameInformacionPaquete.class.getResource("/images/actividad4.png")));
		lblServiceModalitys.setOpaque(true);
		lblServiceModalitys.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceModalitys.setBackground(new Color(18, 95, 115));
		lblServiceModalitys.setBounds(237, 304, 181, 87);
		panel.add(lblServiceModalitys);

		lblOperation = new JLabel();
		lblOperation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
	
				if (verificarCampos()) {
					
					if (touristPackage.getId() == -1) { // add
						try {
							addTouristPackage();
							ConnectionDataBase.getConnectionDataBase().commit(); // se confirman las operaciones realizadas en la base de datos
							FrameAdvertencia frameAv = new FrameAdvertencia("Se ha añadido correctamente el paquete");
							frameAv.setVisible(true);
							FramePaquetes.getInstancie().actualizarPanelPaquetes();
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan los cambios realizados a la base de datos
							} catch (SQLException e2) {

								e2.printStackTrace();
							} 
							e1.printStackTrace();
						}
					}
					else { // update
						try {
							updateTouristPackage();
							ConnectionDataBase.getConnectionDataBase().commit(); // se confirman las operaciones realizadas en la base de datos
							FramePaquetes.getInstancie().actualizarPanelPaquetes();
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan los cambios realizados a la base de datos
							} catch (SQLException e2) {
								e2.printStackTrace();
							} 
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
		lblOperation.setOpaque(true);
		lblOperation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperation.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblOperation.setBackground(SystemColor.info);
		lblOperation.setBounds(207, 421, 235, 35);
		panel.add(lblOperation);

		lblTitleFrame = new JLabel();
		lblTitleFrame.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTitleFrame.setBounds(27, 11, 415, 30);
		panel.add(lblTitleFrame);

		lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblName.setBounds(237, 35, 151, 30);
		panel.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(218, 90, 170, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);



		this.definirTexto();

	}



	private void definirTexto () {
		this.definirTextoLblTitle();
		this.definirTextoLblOperation();
	}

	private void definirTextoLblTitle () {
		String text = "";
		if (touristPackage.getId() == -1) // add
			text = "ADD TOURIST PACKAGE";	
		else
			text = "UPDATE TOURIST PACKAGE: " + this.touristPackage.getId();	

		this.lblTitleFrame.setText(text);



	}

	private boolean verificarCampos () {
		return (!this.textFieldName.getText().equalsIgnoreCase("") && this.touristPackage.getModalitys(Modality.accommodationModality).size() != 0 && 
				this.touristPackage.getModalitys(Modality.serviceModality).size() != 0 && (this.touristPackage.getModalitys(Modality.costKilometers).size() != 0 ||
				this.touristPackage.getModalitys(Modality.hoursKilometers).size() != 0 || this.touristPackage.getModalitys(Modality.establishedRoute).size() != 0 ));
	}

	private void addTouristPackage () throws SQLException {
		Controller.getInstancie().getTouristAgency().addTouristPackage(new TouristPackage(textFieldName.getText(), this.touristPackage.getModalitys(Modality.accommodationModality), 
				this.touristPackage.getModalitys(Modality.serviceModality), this.touristPackage.getModalitys(Modality.costKilometers), this.touristPackage.getModalitys(Modality.hoursKilometers), 
				this.touristPackage.getModalitys(Modality.establishedRoute)));
	}

	private void updateTouristPackage () throws SQLException {
		Controller.getInstancie().getTouristAgency().updateTouristPackage(this.touristPackage, textFieldName.getText());
	}

	private void definirTextoLblOperation () {
		String text = "";
		if (touristPackage.getId() == -1) // add
			text = "CREATE";	
		else
			text = "UPDATE";	

		this.lblOperation.setText(text);
	}

	private void cerrarFrame () {
		FramePaquetes.getInstancie().setEnabled(true);
		dispose();
	}
}
