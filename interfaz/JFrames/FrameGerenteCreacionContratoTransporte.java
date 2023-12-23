package JFrames;

import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import JPanels.PanelGerenteCreacionContrato;
import logica.CarrierContract;
import logica.Controller;
import logica.Provider;
import logica.TransportationProvider;
import utils.ConnectionDataBase;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;

public class FrameGerenteCreacionContratoTransporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblConfirm;
	private JDateChooser dateChooserStartDate;
	private JDateChooser dateChooserEndDate;
	private JComboBox <Provider> comboBoxProvider;
	private JSpinner spinnerRecargo;
	private JTextPane textPaneDescription;
	private JLabel lblRestore; 
	private JPanel panelTransportationContract;
	private PanelGerenteCreacionContrato panelGerenteCreacionContrato;
	private CarrierContract carrierContract;
	private JLabel lblProviderContratado;
	private JLabel lblTituloFrame;
	private JLabel lblShowTransportationModalities;
	private JLabel lblX;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private int mouseX, mouseY;



	public JPanel getPanelTransportationContract() {
		return panelTransportationContract;
	}

	public void setPanelTransportationContract(JPanel panelTransportationContract) {
		this.panelTransportationContract = panelTransportationContract;
	}

	public PanelGerenteCreacionContrato getPanelGerenteCreacionContrato() {
		return panelGerenteCreacionContrato;
	}

	public void setPanelGerenteCreacionContrato(PanelGerenteCreacionContrato panelGerenteCreacionContrato) {
		this.panelGerenteCreacionContrato = panelGerenteCreacionContrato;
	}

	public CarrierContract getCarrierContract() {
		return carrierContract;
	}

	public void setCarrierContract(CarrierContract carrierContract) {
		this.carrierContract = carrierContract;
	}

	public FrameGerenteCreacionContratoTransporte(PanelGerenteCreacionContrato pg, CarrierContract c) {
		this.panelGerenteCreacionContrato = pg;
		this.carrierContract = c;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
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

		panelTransportationContract = new JPanel();
		panelTransportationContract.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTransportationContract.setBounds(0, 0, 700, 512);
		panelTransportationContract.setBackground(new Color(5, 150, 177));
		contentPane.add(panelTransportationContract);
		panelTransportationContract.setLayout(null);

		lblTituloFrame = new JLabel("TRANSPORTATION CONTRACT");
		lblTituloFrame.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTituloFrame.setBounds(27, 11, 374, 30);
		panelTransportationContract.add(lblTituloFrame);

		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(58, 72, 119, 23);
		panelTransportationContract.add(lblStartDate);

		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setBounds(58, 106, 119, 22);
		panelTransportationContract.add(dateChooserStartDate);

		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.info);
		lblEndDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEndDate.setBounds(232, 72, 98, 23);
		panelTransportationContract.add(lblEndDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setBounds(210, 106, 119, 22);
		panelTransportationContract.add(dateChooserEndDate);

		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.info);
		lblProvider.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvider.setBounds(385, 72, 98, 23);
		panelTransportationContract.add(lblProvider);


		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.info);
		lblSurcharge.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSurcharge.setBounds(538, 69, 124, 30);
		panelTransportationContract.add(lblSurcharge);

		spinnerRecargo = new JSpinner();
		spinnerRecargo.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinnerRecargo.setBounds(551, 106, 98, 22);
		panelTransportationContract.add(spinnerRecargo);

		lblShowTransportationModalities = new JLabel("SHOW TRANSPORTATION MODALITIES");
		lblShowTransportationModalities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(panelCreacionContratoTransporteTransportModality); // se cambia para la seccion de añadir modalidades de transporte
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblShowTransportationModalities.setOpaque(true);
		lblShowTransportationModalities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowTransportationModalities.setForeground(SystemColor.info);
		lblShowTransportationModalities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblShowTransportationModalities.setBackground(new Color(18, 95, 115));
		lblShowTransportationModalities.setBounds(176, 149, 348, 30);
		panelTransportationContract.add(lblShowTransportationModalities);

		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(71, 201, 124, 30);
		panelTransportationContract.add(lblDescription);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(58, 242, 591, 195);
		panelTransportationContract.add(textPaneDescription);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (verificarCampos()) {
					if (carrierContract.getId() == -1) { // add
						try {
							addCarrierContract();
							ConnectionDataBase.getConnectionDataBase().commit(); // se confirman las operaciones realizadas en la base de datos
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {	
							try {
								ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan las operaciones realizadas en la base de datos
							} catch (SQLException e2) {

								e2.printStackTrace();
							}
							e1.printStackTrace();
						}
					}
					else { 
						try {
							updateCarrierContract();
							ConnectionDataBase.getConnectionDataBase().commit(); // se confirman las operaciones realizadas en la base de datos
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan las operaciones realizadas en la base de datos
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
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(232, 458, 235, 35);
		panelTransportationContract.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (carrierContract.getId() != -1) {
					try {
						ConnectionDataBase.roolback();
						carrierContract.actualizarDatos(); // se actualizan los datos del contrato en la base de datos
					} catch (SQLException e1) {

						e1.printStackTrace();
					} 
				}
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
		lblX.setBounds(662, 0, 38, 38);
		panelTransportationContract.add(lblX);

		this.definirComponentes();
		this.definirTexto();
		this.crearSecciones();
	}

	private void crearSecciones () {
		this.panelCreacionContratoTransporteTransportModality = new PanelCreacionContratoTransporteTransportModality(FrameGerenteCreacionContratoTransporte.this);
	}

	private void addRestore () {
		lblRestore = new JLabel("RESTORE");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				definirTexto();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestore.setForeground(SystemColor.infoText);
		lblRestore.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRestore.setBounds(500, 18, 98, 23);
		panelTransportationContract.add(lblRestore);
	}

	private void definirComponentes () {
		if (this.carrierContract.getId() == -1)
			this.addComboboxProviders();
		else {
			this.addLblProviderContradado();
			this.addRestore();
		}
	}

	private void addLblProviderContradado () {
		lblProviderContratado = new JLabel(this.carrierContract.getProvider().getName());
		lblProviderContratado.setForeground(SystemColor.infoText);
		lblProviderContratado.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProviderContratado.setBounds(364, 106, 140, 22);
		panelTransportationContract.add(lblProviderContratado);
	}

	private void addComboboxProviders () {
		comboBoxProvider = new JComboBox<Provider>();
		this.llenarComboBoxProviders();
		comboBoxProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carrierContract.setProvider((Provider)comboBoxProvider.getSelectedItem());
				carrierContract.clearModalitys(); // se vacian las modalidades insertdadas del otro provedor
			}
		});

		this.carrierContract.setProvider((Provider) comboBoxProvider.getSelectedItem());
		comboBoxProvider.setBounds(376, 106, 152, 22);
		panelTransportationContract.add(comboBoxProvider);
	}

	private void llenarComboBoxProviders () {
		ArrayList<Provider> providers = Controller.getInstancie().getTouristAgency().getProviders(Provider.transportationProvider); // se obtienen todos los provedores de transporte del sistema

		for (Provider serviceProvider : providers) { 
			comboBoxProvider.addItem((TransportationProvider) serviceProvider);
		}
	}


	private boolean verificarCampos () {
		return (dateChooserStartDate.getDate() != null && dateChooserEndDate.getDate() != null && carrierContract.getModalitys().size() != 0);
	}

	private void definirTexto () {
		String textoLblTitulo = "";

		if (carrierContract.getId() == -1) { // add
			textoLblTitulo = "ADD CARRIER CONTRACT";
			dateChooserStartDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			dateChooserEndDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}	
		else {
			textoLblTitulo = "UPDATE CARRIER CONTRACT";
			this.dateChooserStartDate.setDate(Date.from(this.carrierContract.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant())); // se establece la fecha de inicio del contrato
			this.dateChooserEndDate.setDate(Date.from(this.carrierContract.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant())); // se establece la fecha de terminacion del contrato
			this.textPaneDescription.setText(this.carrierContract.getDescription()); // se establece la descripcion del contrato
			this.spinnerRecargo.setValue(this.carrierContract.getSurcharge()); // se establece el recargo del contrato
		}

		this.lblTituloFrame.setText(textoLblTitulo);


	}

	private void addCarrierContract () throws SQLException { // add
		Controller.getInstancie().getTouristAgency().addContract(new CarrierContract(dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), this.carrierContract.getProvider(), 
				this.carrierContract.getModalitys(), (Double) spinnerRecargo.getValue())); // se inserta el contrato de alojamiento a nivel de base de datos
		panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
	}

	private void updateCarrierContract () throws SQLException { // update
		Controller.getInstancie().getTouristAgency().updateContract(this.carrierContract, dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (Double) spinnerRecargo.getValue());
		panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
	}

	private void cerrarFrame () {
		FrameGerente.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
		dispose(); // se cierra este frame
	}

	public void cambioDePanel (JPanel panel) {
		this.contentPane.remove(this.contentPane.getComponentCount() - 1); // se elimina el ultimo componente insertado (Anterior Panel)
		this.contentPane.add(panel);
		this.repintarFrame();
	}

	public void repintarFrame () {
		repaint();
		revalidate();
	}
}
