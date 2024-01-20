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
import javax.swing.border.MatteBorder;
import java.awt.Insets;

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
		setBounds(100, 100, 853, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(18, 95, 115));
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
		panelAccommodationContract.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelAccommodationContract.setLayout(null);
		panelAccommodationContract.setBackground(new Color(18, 95, 115));
		panelAccommodationContract.setBounds(0, 0, 853, 577);
		contentPane.add(panelAccommodationContract);

		lblTituloFrame = new JLabel("ACCOMMODATION CONTRACT");
		lblTituloFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloFrame.setForeground(SystemColor.textHighlightText);
		lblTituloFrame.setFont(new Font("Dialog", Font.BOLD, 26));
		lblTituloFrame.setBounds(139, 36, 574, 30);
		panelAccommodationContract.add(lblTituloFrame);

		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.textHighlightText);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblStartDate.setBounds(66, 88, 119, 23);
		panelAccommodationContract.add(lblStartDate);

		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.textHighlightText);
		lblEndDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEndDate.setBounds(281, 88, 98, 23);
		panelAccommodationContract.add(lblEndDate);

		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.textHighlightText);
		lblProvider.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProvider.setBounds(486, 88, 98, 23);
		panelAccommodationContract.add(lblProvider);

		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dateChooserStartDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		dateChooserStartDate.setBounds(54, 122, 150, 22);
		panelAccommodationContract.add(dateChooserStartDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dateChooserEndDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		dateChooserEndDate.setBounds(254, 122, 150, 22);
		panelAccommodationContract.add(dateChooserEndDate);


		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setForeground(SystemColor.textHighlightText);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDescription.setBounds(27, 230, 124, 30);
		panelAccommodationContract.add(lblDescription);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (accommodationContract.getId() == -1) {
					if (verificarCampos()) {
						try {
							addAccommodationContract();
							ConnectionDataBase.commit(); // se confirman las operaciones realizadas sobre la base de datos
							panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
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
							panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
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
		lblConfirm.setFont(new Font("Dialog", Font.BOLD, 16));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(311, 531, 235, 35);
		panelAccommodationContract.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				lblX.setForeground(SystemColor.textHighlightText);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(SystemColor.textHighlightText);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(815, 0, 38, 38);
		panelAccommodationContract.add(lblX);

		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.textHighlightText);
		lblSurcharge.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSurcharge.setBounds(663, 84, 124, 30);
		panelAccommodationContract.add(lblSurcharge);

		spinnerRecargo = new JSpinner();
		spinnerRecargo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		spinnerRecargo.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerRecargo.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinnerRecargo.setBounds(663, 122, 124, 22);
		panelAccommodationContract.add(spinnerRecargo);

		textPaneDescription = new JTextPane();
		textPaneDescription.setMargin(new Insets(8, 8, 8, 8));
		textPaneDescription.setForeground(SystemColor.textText);
		textPaneDescription.setFont(new Font("Dialog", Font.PLAIN, 18));
		textPaneDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneDescription.setBackground(SystemColor.inactiveCaptionBorder);
		textPaneDescription.setBounds(10, 271, 833, 235);
		panelAccommodationContract.add(textPaneDescription);

		lblShowAccommodationModalities = new JLabel("SHOW ACCOMMODATION MODALITIES");
		lblShowAccommodationModalities.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblShowAccommodationModalities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelCreacionContratoAlojamientoAccommodationModality(FrameGerenteCreacionContratoAlojamiento.this)); // se cambia a la seccion de accommodations modalities
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
		lblShowAccommodationModalities.setForeground(SystemColor.textText);
		lblShowAccommodationModalities.setFont(new Font("Dialog", Font.BOLD, 16));
		lblShowAccommodationModalities.setBounds(10, 189, 348, 30);
		lblShowAccommodationModalities.setBackground(SystemColor.info);
		panelAccommodationContract.add(lblShowAccommodationModalities);

		lblShowSeasons = new JLabel("SHOW SEASONS");
		lblShowSeasons.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblShowSeasons.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelGerenteCreacionContratoAlojamientoTemporada(FrameGerenteCreacionContratoAlojamiento.this));
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
		lblShowSeasons.setForeground(SystemColor.textText);
		lblShowSeasons.setFont(new Font("Dialog", Font.BOLD, 16));
		lblShowSeasons.setBounds(690, 189, 153, 30);
		lblShowSeasons.setBackground(SystemColor.info);
		panelAccommodationContract.add(lblShowSeasons);

		this.definirComponentes();
		this.definirTexto();

	}



	private void addRestore () {
		lblRestore = new JLabel("RESTORE");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation(); // se restuara la informacion del contrato antes de las modificaciones
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
		panelAccommodationContract.add(lblRestore);
	}

	private void restoreInformation () { // Metodo para restaurar la informacion del contrato antes de las modificaciones
		try {
			this.restoreModalitys();
			this.definirTexto();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void restoreModalitys () throws SQLException { // Metodo para restaurar la informacion de las modalidades del contrato antes de las modificaciones
		ConnectionDataBase.roolback(); // se cancelan todos los cambios realizados a la base de datos
		accommodationContract.actualizarDatos(); // se actualizan los datos del contrato
	}

	private void definirComponentes () {
		if (this.accommodationContract.getId() == -1) // Add
			this.addComboboxProviders();
		else {
			this.addLblProviderContradado();
			this.addRestore();
		}

	}

	private void addLblProviderContradado () {
		lblProviderContratado = new JLabel(this.accommodationContract.getProvider().getName());
		lblProviderContratado.setHorizontalAlignment(SwingConstants.CENTER);
		lblProviderContratado.setForeground(SystemColor.info);
		lblProviderContratado.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProviderContratado.setBounds(454, 122, 150, 22);
		panelAccommodationContract.add(lblProviderContratado);
	}

	private void addComboboxProviders () {
		comboBoxProvider = new JComboBox<Provider>();
		comboBoxProvider.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.llenarComboBoxProviders();
		comboBoxProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accommodationContract.setProvider((Provider)comboBoxProvider.getSelectedItem());
				accommodationContract.clearModalitys(); // se vacian las modalidades insertdadas del otro provedor
			}
		});

		this.accommodationContract.setProvider((Provider) comboBoxProvider.getSelectedItem());
		comboBoxProvider.setBounds(461, 122, 150, 22);
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

	}

	public void updateAccommodationContract () throws SQLException { // update
		Controller.getInstancie().getTouristAgency().updateContract(this.accommodationContract, dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
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
