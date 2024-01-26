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
import utils.Operations;

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
import java.awt.Insets;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class FrameGerenteCreacionContratoTransporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblConfirm;
	private JDateChooser dateChooserStartDate;
	private JDateChooser dateChooserEndDate;
	private JComboBox <Provider> comboBoxProvider;
	private JSpinner spinnerRecargo;
	private JLabel lblRestore; 
	private JPanel panelTransportationContract;
	private PanelGerenteCreacionContrato panelGerenteCreacionContrato;
	private CarrierContract carrierContract;
	private JLabel lblProviderContratado;
	private JLabel lblTituloFrame;
	private JLabel lblShowTransportationModalities;
	private JLabel lblX;
	private int mouseX, mouseY;
	private JTextPane textPaneDescription;
	private JLabel lblNewLabel;



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
		setBounds(100, 100, 853, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(18, 95, 115));
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
		panelTransportationContract.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTransportationContract.setBounds(0, 0, 853, 577);
		panelTransportationContract.setBackground(new Color(18, 95, 115));
		contentPane.add(panelTransportationContract);
		panelTransportationContract.setLayout(null);

		lblTituloFrame = new JLabel("TRANSPORTATION CONTRACT");
		lblTituloFrame.setForeground(SystemColor.textHighlightText);
		lblTituloFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloFrame.setFont(new Font("Dialog", Font.BOLD, 26));
		lblTituloFrame.setBounds(139, 36, 574, 30);
		panelTransportationContract.add(lblTituloFrame);

		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.textHighlightText);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblStartDate.setBounds(66, 88, 119, 23);
		panelTransportationContract.add(lblStartDate);

		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		dateChooserStartDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dateChooserStartDate.setBounds(54, 122, 150, 22);
		panelTransportationContract.add(dateChooserStartDate);

		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.textHighlightText);
		lblEndDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEndDate.setBounds(281, 88, 98, 23);
		panelTransportationContract.add(lblEndDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		dateChooserEndDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dateChooserEndDate.setBounds(254, 122, 150, 22);
		panelTransportationContract.add(dateChooserEndDate);

		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.textHighlightText);
		lblProvider.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProvider.setBounds(486, 88, 98, 23);
		panelTransportationContract.add(lblProvider);


		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.textHighlightText);
		lblSurcharge.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSurcharge.setBounds(663, 84, 124, 30);
		panelTransportationContract.add(lblSurcharge);

		spinnerRecargo = new JSpinner();
		spinnerRecargo.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerRecargo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Operations.crearJSpinnerNumericoDouble(spinnerRecargo, new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinnerRecargo.setBounds(663, 122, 124, 22);
		panelTransportationContract.add(spinnerRecargo);

		lblShowTransportationModalities = new JLabel("SHOW TRANSPORTATION MODALITIES");
		lblShowTransportationModalities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowTransportationModalities.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblShowTransportationModalities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelCreacionContratoTransporteTransportModality(FrameGerenteCreacionContratoTransporte.this)); // se cambia para la seccion de añadir modalidades de transporte
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
		lblShowTransportationModalities.setForeground(SystemColor.textText);
		lblShowTransportationModalities.setFont(new Font("Dialog", Font.BOLD, 16));
		lblShowTransportationModalities.setBackground(SystemColor.info);
		lblShowTransportationModalities.setBounds(252, 189, 348, 30);
		panelTransportationContract.add(lblShowTransportationModalities);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					if (carrierContract.getId() == -1) { // add
						try {
							addCarrierContract();
							ConnectionDataBase.getConnectionDataBase().commit(); // se confirman las operaciones realizadas en la base de datos
							panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
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
							panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
							FramePrincipal.mostarFrameNotificacion("It has been successfully modified the contract"); // se notifica de la accion realizada al usuario
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
		lblConfirm.setFont(new Font("Dialog", Font.BOLD, 16));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(311, 531, 235, 35);
		panelTransportationContract.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (carrierContract.getId() != -1) {
					try {
						restoreModalities();
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
				lblX.setForeground(SystemColor.textHighlightText);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(SystemColor.textHighlightText);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(815, 0, 38, 38);
		panelTransportationContract.add(lblX);
		
		textPaneDescription = new JTextPane();
		textPaneDescription.setMargin(new Insets(8, 8, 8, 8));
		textPaneDescription.setForeground(SystemColor.textText);
		textPaneDescription.setFont(new Font("Dialog", Font.PLAIN, 18));
		textPaneDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneDescription.setBackground(SystemColor.inactiveCaptionBorder);
		textPaneDescription.setBounds(10, 271, 833, 235);
		panelTransportationContract.add(textPaneDescription);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setForeground(SystemColor.textHighlightText);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDescription.setBounds(27, 230, 124, 30);
		panelTransportationContract.add(lblDescription);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameGerenteCreacionContratoTransporte.class.getResource("/images/Logo 38x38.png")));
		lblNewLabel.setBounds(10, 16, 38, 38);
		panelTransportationContract.add(lblNewLabel);

		this.definirComponentes();
		this.definirTexto();
	
	}

	
	private void addRestore () {
		lblRestore = new JLabel("RESTORE");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation();
				FramePrincipal.mostarFrameNotificacion("Los datos del contrato de transporte: " + carrierContract.getId() + " han sido restaurados con éxito"); // se notifica de la accion realizada al usuario
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestore.setForeground(SystemColor.textHighlightText);
		lblRestore.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRestore.setBounds(689, 43, 98, 23);
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
		lblProviderContratado.setHorizontalAlignment(SwingConstants.CENTER);
		lblProviderContratado.setForeground(SystemColor.info);
		lblProviderContratado.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProviderContratado.setBounds(454, 122, 150, 22);
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
		comboBoxProvider.setBounds(461, 122, 150, 22);
		panelTransportationContract.add(comboBoxProvider);
	}
	
	private void restoreInformation () { // Metodo definido para restaurar la informacion del contrato
		try {
			this.restoreModalities();
			this.definirTexto();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	private void restoreModalities () throws SQLException { // Metodo para restuarar la modalidades del paquete por las que tenia antes de su modificacion
		ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas en la base de datos
		this.carrierContract.actualizarDatos(); // se actualizan los datos del contrato en la base de datos
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
		CarrierContract carrierContract = new CarrierContract(dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), this.carrierContract.getProvider(), 
				this.carrierContract.getModalitys(), (Double) spinnerRecargo.getValue());
		
		Controller.getInstancie().getTouristAgency().addContract(carrierContract); // se inserta el contrato de alojamiento a nivel de base de datos
		FramePrincipal.mostarFrameNotificacion("It has been added successfully the contract"); // se notifica de la accion realizada al usuario
		
	}

	private void updateCarrierContract () throws SQLException { // update
		Controller.getInstancie().getTouristAgency().updateContract(this.carrierContract, dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (Double) spinnerRecargo.getValue());
	}

	private void cerrarFrame () {
		FramePrincipal.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
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
