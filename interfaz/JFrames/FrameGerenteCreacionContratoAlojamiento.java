package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import JPanels.PanelCreacionContratoAlojamientoAccommodationModality;
import JPanels.PanelGerenteCreacionContrato;
import JPanels.PanelGerenteCreacionContratoAlojamientoTemporada;
import logica.AccommodationContract;
import logica.Controller;
import logica.Hotel;
import logica.Provider;
import utils.ConnectionDataBase;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
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

public class FrameGerenteCreacionContratoAlojamiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblShowAccommodationModalities;
	private JLabel lblConfirm;
	private JLabel lblShowSeasons;
	private int mouseX, mouseY;
	private PanelGerenteCreacionContrato panelGerenteCreacionContrato;
	private AccommodationContract accommodationContract;
	private JDateChooser dateChooserStartDate;
	private JDateChooser dateChooserEndDate;
	private JComboBox <Provider> comboBoxProvider;
	private JSpinner spinnerRecargo;
	private JLabel lblRestore;
	private JTextPane textPaneDescription;
	private JLabel lblTituloFrame, lblProviderContratado;
	private JPanel panelAccommodationContract;
	private JLabel lblX;
	private PanelCreacionContratoAlojamientoAccommodationModality panelCreacionContratoAlojamientoAccommodationModality;
	private PanelGerenteCreacionContratoAlojamientoTemporada panelGerenteCreacionContratoAlojamientoTemporada;





	public JPanel getPanelAccommodationContract() {
		return panelAccommodationContract;
	}

	public void setPanelAccommodationContract(JPanel panelAccommodationContract) {
		this.panelAccommodationContract = panelAccommodationContract;
	}

	public AccommodationContract getAccommodationContract() {
		return accommodationContract;
	}

	public void setAccommodationContract(AccommodationContract accommodationContract) {
		this.accommodationContract = accommodationContract;
	}

	public FrameGerenteCreacionContratoAlojamiento(PanelGerenteCreacionContrato pg, AccommodationContract ac) {
		this.panelGerenteCreacionContrato = pg;
		this.accommodationContract = ac;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 512);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelAccommodationContract = new JPanel();
		panelAccommodationContract.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAccommodationContract.setLayout(null);
		panelAccommodationContract.setBackground(new Color(5, 150, 177));
		panelAccommodationContract.setBounds(0, 0, 700, 512);
		contentPane.add(panelAccommodationContract);

		lblTituloFrame = new JLabel("ACCOMMODATION CONTRACT");
		lblTituloFrame.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTituloFrame.setBounds(27, 11, 422, 30);
		panelAccommodationContract.add(lblTituloFrame);

		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(58, 72, 119, 23);
		panelAccommodationContract.add(lblStartDate);

		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.info);
		lblEndDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEndDate.setBounds(232, 72, 98, 23);
		panelAccommodationContract.add(lblEndDate);

		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.info);
		lblProvider.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvider.setBounds(385, 72, 98, 23);
		panelAccommodationContract.add(lblProvider);

		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setBounds(58, 106, 119, 22);
		panelAccommodationContract.add(dateChooserStartDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setBounds(222, 106, 119, 22);
		panelAccommodationContract.add(dateChooserEndDate);


		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(71, 201, 124, 30);
		panelAccommodationContract.add(lblDescription);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (accommodationContract.getId() == -1) {
					if (verificarCampos()) {
						try {
							addAccommodationContract();
							ConnectionDataBase.commit(); // se confirman las operaciones realizadas sobre la base de datos
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas sobre la base de datos
							} catch (SQLException e2) {
								e2.printStackTrace();
							} 
							e1.printStackTrace();
						}
					}
				}
				else {
					if (verificarCampos()) {
						try {
							updateAccommodationContract();
							ConnectionDataBase.commit(); // se confirman las operaciones realizadas sobre la base de datos
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas sobre la base de datos
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
		panelAccommodationContract.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (accommodationContract.getId() != -1) {
					try {
						ConnectionDataBase.roolback(); // se cancelan todos los cambios realizados a la base de datos
						accommodationContract.actualizarDatos(); // se actualizan los datos del contrato
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		lblX.setBounds(662, 0, 38, 38);
		panelAccommodationContract.add(lblX);

		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.info);
		lblSurcharge.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSurcharge.setBounds(538, 69, 124, 30);
		panelAccommodationContract.add(lblSurcharge);

		spinnerRecargo = new JSpinner();
		spinnerRecargo.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinnerRecargo.setBounds(551, 106, 98, 22);
		panelAccommodationContract.add(spinnerRecargo);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(58, 242, 591, 195);
		panelAccommodationContract.add(textPaneDescription);

		lblShowAccommodationModalities = new JLabel("SHOW ACCOMMODATION MODALITIES");
		lblShowAccommodationModalities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(panelCreacionContratoAlojamientoAccommodationModality); // se cambia a la seccion de accommodations modalities
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblShowAccommodationModalities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAccommodationModalities.setOpaque(true);
		lblShowAccommodationModalities.setForeground(SystemColor.info);
		lblShowAccommodationModalities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblShowAccommodationModalities.setBounds(58, 149, 348, 30);
		lblShowAccommodationModalities.setBackground(new Color(18, 95, 115));
		panelAccommodationContract.add(lblShowAccommodationModalities);

		lblShowSeasons = new JLabel("SHOW SEASONS");
		lblShowSeasons.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(panelGerenteCreacionContratoAlojamientoTemporada);
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblShowSeasons.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowSeasons.setOpaque(true);
		lblShowSeasons.setForeground(SystemColor.info);
		lblShowSeasons.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblShowSeasons.setBounds(496, 149, 153, 30);
		lblShowSeasons.setBackground(new Color(18, 95, 115));
		panelAccommodationContract.add(lblShowSeasons);

		this.definirComponentes();
		this.definirTexto();
		this.crearSecciones(); // se crean las secciones (seccion accommodation modality y seccion temporadas)

	}

	private void crearSecciones () {
		this.panelCreacionContratoAlojamientoAccommodationModality = new PanelCreacionContratoAlojamientoAccommodationModality(FrameGerenteCreacionContratoAlojamiento.this);
		this.panelGerenteCreacionContratoAlojamientoTemporada = new PanelGerenteCreacionContratoAlojamientoTemporada(FrameGerenteCreacionContratoAlojamiento.this);
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
		panelAccommodationContract.add(lblRestore);
	}

	private void definirComponentes () {
		if (this.accommodationContract.getId() == -1)
			this.addComboboxProviders();
		else {
			this.addLblProviderContradado();
			this.addRestore();
		}
	}

	private void addLblProviderContradado () {
		lblProviderContratado = new JLabel(this.accommodationContract.getProvider().getName());
		lblProviderContratado.setForeground(SystemColor.infoText);
		lblProviderContratado.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProviderContratado.setBounds(364, 106, 140, 22);
		panelAccommodationContract.add(lblProviderContratado);
	}

	private void addComboboxProviders () {
		comboBoxProvider = new JComboBox<Provider>();
		this.llenarComboBoxProviders();
		comboBoxProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accommodationContract.setProvider((Provider)comboBoxProvider.getSelectedItem());
				accommodationContract.clearModalitys(); // se vacian las modalidades insertdadas del otro provedor
			}
		});

		this.accommodationContract.setProvider((Provider) comboBoxProvider.getSelectedItem());
		comboBoxProvider.setBounds(376, 106, 152, 22);
		panelAccommodationContract.add(comboBoxProvider);
	}

	private void llenarComboBoxProviders () {
		ArrayList<Provider> providers = Controller.getInstancie().getTouristAgency().getProviders(Provider.accommodationProvider); // se obtienen todos los provedores de alojamiento del sistema

		for (Provider serviceProvider : providers) { 
			comboBoxProvider.addItem((Hotel) serviceProvider);
		}
	}


	private boolean verificarCampos () {
		return (dateChooserStartDate.getDate() != null && dateChooserEndDate.getDate() != null && accommodationContract.getModalitys().size() != 0 && accommodationContract.getSeasons().size() != 0);
	}

	private void definirTexto () {
		String textoLblTitulo = "";

		if (accommodationContract.getId() == -1) { // add
			textoLblTitulo = "ADD ACCOMMODATION CONTRACT";
			dateChooserStartDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			dateChooserEndDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}	
		else {
			textoLblTitulo = "UPDATE ACCOMMODATION CONTRACT";
			this.dateChooserStartDate.setDate(Date.from(this.accommodationContract.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant())); // se establece la fecha de inicio del contrato
			this.dateChooserEndDate.setDate(Date.from(this.accommodationContract.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant())); // se establece la fecha de terminacion del contrato
			this.textPaneDescription.setText(this.accommodationContract.getDescription()); // se establece la descripcion del contrato
			this.spinnerRecargo.setValue(this.accommodationContract.getSurcharge()); // se establece el recargo del contrato
		}

		this.lblTituloFrame.setText(textoLblTitulo);


	}

	public void addAccommodationContract () throws SQLException { // add
		Controller.getInstancie().getTouristAgency().addContract(new AccommodationContract(dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), this.accommodationContract.getProvider(), 
				this.accommodationContract.getModalitys(), (Double) spinnerRecargo.getValue(), this.accommodationContract.getSeasons())); // se inserta el contrato de alojamiento a nivel de base de datos
		panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
	}

	public void updateAccommodationContract () throws SQLException { // update
		Controller.getInstancie().getTouristAgency().updateContract(this.accommodationContract, dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
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
