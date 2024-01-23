package JFrames;

import java.awt.Color;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import JPanels.PanelCreacionContratoServicioServiceModality;
import JPanels.PanelGerenteCreacionContrato;
import logica.Controller;
import logica.Provider;
import logica.ServiceContract;
import logica.ServiceProvider;
import utils.ConnectionDataBase;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
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
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class FrameGerenteCreacionContratoServivio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblConfirm;
	private JDateChooser dateChooserStartDate;
	private JDateChooser dateChooserEndDate;
	private JComboBox <ServiceProvider> comboBoxProvider;
	private PanelGerenteCreacionContrato panelGerenteCreacionContrato;
	private ServiceContract serviceContract;
	private JSpinner spinnerRecargo;
	private JLabel lblShowServicesModalities;
	private JPanel panelServiceContract;
	private JTextPane textPaneDescription;
	private int mouseX, mouseY;
	private JLabel lblTituloFrame;
	private JLabel lblProviderContratado;
	private JLabel lblRestore;
	private JLabel lblX;
	private JLabel lblNewLabel;

	public ServiceContract getServiceContract() {
		return serviceContract;
	}



	public PanelGerenteCreacionContrato getPanelGerenteCreacionContrato() {
		return panelGerenteCreacionContrato;
	}



	public JPanel getPanelServiceContract() {
		return panelServiceContract;
	}



	public void setPanelServiceContract(JPanel panelServiceContract) {
		this.panelServiceContract = panelServiceContract;
	}



	public void setPanelGerenteCreacionContrato(PanelGerenteCreacionContrato panelGerenteCreacionContrato) {
		this.panelGerenteCreacionContrato = panelGerenteCreacionContrato;
	}



	public void setServiceContract(ServiceContract serviceContract) {
		this.serviceContract = serviceContract;
	}

	public FrameGerenteCreacionContratoServivio(PanelGerenteCreacionContrato pg, ServiceContract sc) {
		this.panelGerenteCreacionContrato = pg;
		this.serviceContract = sc;
		setUndecorated(true);
		setLocationRelativeTo(FramePrincipal.getIntancie());
		setSize(853, 577);
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

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelServiceContract = new JPanel();
		panelServiceContract.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelServiceContract.setBounds(0, 0, 853, 577);
		panelServiceContract.setBackground(new Color(18, 95, 115));
		contentPane.add(panelServiceContract);
		panelServiceContract.setLayout(null);
		setLocationRelativeTo(null);
		lblTituloFrame = new JLabel("ADD SERVICE CONTRACT");
		lblTituloFrame.setForeground(SystemColor.textHighlightText);
		lblTituloFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloFrame.setFont(new Font("Dialog", Font.BOLD, 26));
		lblTituloFrame.setBounds(139, 36, 574, 30);
		panelServiceContract.add(lblTituloFrame);

		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.textHighlightText);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblStartDate.setBounds(66, 88, 119, 23);
		panelServiceContract.add(lblStartDate);

		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.textHighlightText);
		lblEndDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEndDate.setBounds(281, 88, 98, 23);
		panelServiceContract.add(lblEndDate);

		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.textHighlightText);
		lblProvider.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProvider.setBounds(486, 88, 98, 23);
		panelServiceContract.add(lblProvider);

		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		dateChooserStartDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserStartDate.setBounds(54, 122, 150, 22);
		panelServiceContract.add(dateChooserStartDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		dateChooserEndDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserEndDate.setBounds(254, 122, 150, 22);
		panelServiceContract.add(dateChooserEndDate);

		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setForeground(SystemColor.textHighlightText);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDescription.setBounds(27, 230, 124, 30);
		panelServiceContract.add(lblDescription);

		JPanel panelDescription = new JPanel();
		panelDescription.setBounds(10, 271, 833, 235);
		panelServiceContract.add(panelDescription);
		panelDescription.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelDescription.add(scrollPane, BorderLayout.CENTER);

		textPaneDescription = new JTextPane();
		textPaneDescription.setForeground(SystemColor.textText);
		textPaneDescription.setFont(new Font("Dialog", Font.PLAIN, 18));
		textPaneDescription.setBounds(58, 242, 591, 195);
		scrollPane.setViewportView(textPaneDescription);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (serviceContract.getId() == -1) {
					if (verificarCampos()) {
						try {
							addServiceContract();
							ConnectionDataBase.commit(); // se confirman las operaciones realizadas sobre la base de datos
							panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback();  // se cancelan las operaciones realizadas sobre la base de datos
							} catch (SQLException e2) {
								e2.printStackTrace();
							} // se cancelan las operaciones realizadas sobre la base de datos
							e1.printStackTrace();
						}
					}
				}
				else {
					if (verificarCampos()) {
						try {
							updateServiceContract();
							ConnectionDataBase.commit(); // se confirman las operaciones realizadas sobre la base de datos
							panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
							FramePrincipal.mostarFrameNotificacion("Ha sido modificada correctamente la información del contrato de servicio: " + serviceContract.getId()); // se notifica de la accion realizada al usuario
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback();  // se cancelan las operaciones realizadas sobre la base de datos
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
		panelServiceContract.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (serviceContract.getId() != -1) {
					try {
						ConnectionDataBase.roolback(); // se cancelan todos los cambios realizados a la base de datos
						serviceContract.actualizarDatos(); // se actualizan los datos del contrato
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
		panelServiceContract.add(lblX);

		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.textHighlightText);
		lblSurcharge.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSurcharge.setBounds(663, 84, 124, 30);
		panelServiceContract.add(lblSurcharge);

		spinnerRecargo = new JSpinner();
		spinnerRecargo.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerRecargo.setBorder(new LineBorder(new Color(0, 0, 0)));
		spinnerRecargo.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinnerRecargo.setBounds(663, 122, 124, 22);
		panelServiceContract.add(spinnerRecargo);

		lblShowServicesModalities = new JLabel("SHOW SERVICES MODALITIES");
		lblShowServicesModalities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowServicesModalities.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblShowServicesModalities.setOpaque(true);
		lblShowServicesModalities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowServicesModalities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(new PanelCreacionContratoServicioServiceModality(FrameGerenteCreacionContratoServivio.this));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblShowServicesModalities.setForeground(SystemColor.textText);
		lblShowServicesModalities.setFont(new Font("Dialog", Font.BOLD, 16));
		lblShowServicesModalities.setBounds(10, 189, 348, 30);
		lblShowServicesModalities.setBackground(SystemColor.info);
		panelServiceContract.add(lblShowServicesModalities);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameGerenteCreacionContratoServivio.class.getResource("/images/Logo 38x38.png")));
		lblNewLabel.setBounds(10, 16, 38, 38);
		panelServiceContract.add(lblNewLabel);




		this.definirComponentes();
		this.definirTexto();
	}

	private void addRestore () {
		lblRestore = new JLabel("RESTORE");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation(); // se restaura la informacion del contrato
				FramePrincipal.mostarFrameNotificacion("Los datos del contrato de servicio: " + serviceContract.getId() + " han sido restaurados con éxito"); // se notifica de la accion realizada al usuario
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
		panelServiceContract.add(lblRestore);
	}

	private void definirComponentes () {
		if (this.serviceContract.getId() == -1)
			this.addComboboxProviders();
		else {
			this.addLblProviderContradado();
			this.addRestore();
		}

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
		this.serviceContract.actualizarDatos(); // se actualizan los datos del contrato
	}

	private void addLblProviderContradado () {
		lblProviderContratado = new JLabel(this.serviceContract.getProvider().getName());
		lblProviderContratado.setHorizontalAlignment(SwingConstants.CENTER);
		lblProviderContratado.setForeground(SystemColor.info);
		lblProviderContratado.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProviderContratado.setBounds(454, 122, 150, 22);
		panelServiceContract.add(lblProviderContratado);
	}

	private void addComboboxProviders () {
		comboBoxProvider = new JComboBox<ServiceProvider>();
		this.llenarComboBoxProviders();
		comboBoxProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serviceContract.setProvider((Provider)comboBoxProvider.getSelectedItem());
				serviceContract.clearModalitys(); // se vacian las modalidades insertdadas del otro provedor
			}
		});

		this.serviceContract.setProvider((Provider) comboBoxProvider.getSelectedItem());
		comboBoxProvider.setBounds(461, 122, 150, 22);
		panelServiceContract.add(comboBoxProvider);
	}

	private void llenarComboBoxProviders () {
		ArrayList<Provider> providers = Controller.getInstancie().getTouristAgency().getProviders(Provider.serviceProvider); // se obtienen todos los provedores de servicios del sistema

		for (Provider serviceProvider : providers) { // se itera la lista correspondiente a cada clave del mapa
			comboBoxProvider.addItem((ServiceProvider) serviceProvider);
		}
	}


	private boolean verificarCampos () {
		return (dateChooserStartDate.getDate() != null && dateChooserEndDate.getDate() != null && serviceContract.getModalitys().size() != 0);
	}


	private void addServiceContract () throws SQLException  { // Modo add
		ServiceContract serviceContract = new ServiceContract(dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), this.serviceContract.getProvider(), 
				this.serviceContract.getModalitys(), (Double) spinnerRecargo.getValue());

		Controller.getInstancie().getTouristAgency().addContract(serviceContract); // se inserta el contrato de servicio a nivel de base de datos
		FramePrincipal.mostarFrameNotificacion("Ha sido insertado correctamente el contrato de servicio: " + serviceContract.getId()); // se notifica de la accion realizada al usuario
	}

	public void updateServiceContract () throws SQLException { // Modo update
		Controller.getInstancie().getTouristAgency().updateContract(serviceContract, dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (Double) spinnerRecargo.getValue());
	}

	private void definirTexto () {
		String textoLblTitulo = "";

		if (serviceContract.getId() == -1) { // add
			textoLblTitulo = "ADD SERVICE CONTRACT";
			dateChooserStartDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			dateChooserEndDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}	
		else {
			textoLblTitulo = "UPDATE SERVICE CONTRACT";
			this.dateChooserStartDate.setDate(Date.from(this.serviceContract.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant())); // se establece la fecha de inicio del contrato
			this.dateChooserEndDate.setDate(Date.from(this.serviceContract.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant())); // se establece la fecha de terminacion del contrato
			this.textPaneDescription.setText(this.serviceContract.getDescription()); // se establece la descripcion del contrato
			this.spinnerRecargo.setValue(this.serviceContract.getSurcharge()); // se establece el recargo del contrato
		}

		this.lblTituloFrame.setText(textoLblTitulo);


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
