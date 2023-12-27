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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

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
	private PanelCreacionContratoServicioServiceModality panelCreacionContratoAlojamientoServiceModality;
	private JPanel panelServiceContract;
	private JTextPane textPaneDescription;
	private int mouseX, mouseY;
	private JLabel lblTituloFrame;
	private JLabel lblProviderContratado;
	private JLabel lblRestore;
	private JLabel lblX;

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
		setSize(700, 512);
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
		panelServiceContract.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelServiceContract.setBounds(0, 0, 700, 512);
		panelServiceContract.setBackground(new Color(5, 150, 177));
		contentPane.add(panelServiceContract);
		panelServiceContract.setLayout(null);
		setLocationRelativeTo(null);
		lblTituloFrame = new JLabel("ADD SERVICE CONTRACT");
		lblTituloFrame.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTituloFrame.setBounds(27, 11, 404, 30);
		panelServiceContract.add(lblTituloFrame);

		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(58, 72, 119, 23);
		panelServiceContract.add(lblStartDate);

		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.info);
		lblEndDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEndDate.setBounds(232, 72, 98, 23);
		panelServiceContract.add(lblEndDate);

		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.info);
		lblProvider.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvider.setBounds(385, 72, 98, 23);
		panelServiceContract.add(lblProvider);

		dateChooserStartDate = new JDateChooser();
		dateChooserStartDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserStartDate.setBounds(58, 106, 119, 22);
		panelServiceContract.add(dateChooserStartDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserEndDate.setBounds(210, 106, 119, 22);
		panelServiceContract.add(dateChooserEndDate);



		this.panelCreacionContratoAlojamientoServiceModality = new PanelCreacionContratoServicioServiceModality(FrameGerenteCreacionContratoServivio.this);
		JLabel lblModalities = new JLabel("DESCRIPTION");
		lblModalities.setForeground(SystemColor.info);
		lblModalities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblModalities.setBounds(71, 201, 128, 30);
		panelServiceContract.add(lblModalities);

		JPanel panelModalities = new JPanel();
		panelModalities.setBounds(58, 242, 591, 195);
		panelServiceContract.add(panelModalities);
		panelModalities.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelModalities.add(scrollPane, BorderLayout.CENTER);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(58, 242, 591, 195);
		scrollPane.setViewportView(textPaneDescription);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (serviceContract.getId() == -1) {
					if (verificarCampos()) {
						try {
							addServiceContract();
							ConnectionDataBase.commit(); // se confirman las operaciones realizadas sobre la base de datos
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
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(232, 458, 235, 35);
		panelServiceContract.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
				lblX.setForeground(SystemColor.black);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(662, 0, 38, 38);
		panelServiceContract.add(lblX);

		JLabel lblSurcharge = new JLabel("SURCHARGE");
		lblSurcharge.setForeground(SystemColor.info);
		lblSurcharge.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSurcharge.setBounds(538, 69, 124, 30);
		panelServiceContract.add(lblSurcharge);

		spinnerRecargo = new JSpinner();
		spinnerRecargo.setBorder(new LineBorder(new Color(0, 0, 0)));
		spinnerRecargo.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinnerRecargo.setBounds(551, 106, 98, 22);
		panelServiceContract.add(spinnerRecargo);

		lblShowServicesModalities = new JLabel("SHOW SERVICES MODALITIES");
		lblShowServicesModalities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowServicesModalities.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblShowServicesModalities.setOpaque(true);
		lblShowServicesModalities.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowServicesModalities.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambioDePanel(panelCreacionContratoAlojamientoServiceModality);
				panelCreacionContratoAlojamientoServiceModality.actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblShowServicesModalities.setForeground(SystemColor.info);
		lblShowServicesModalities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblShowServicesModalities.setBackground(new Color(18, 95, 115));
		lblShowServicesModalities.setBounds(176, 149, 348, 30);
		panelServiceContract.add(lblShowServicesModalities);




		this.definirComponentes();
		this.definirTexto();
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

	private void addLblProviderContradado () {
		lblProviderContratado = new JLabel(this.serviceContract.getProvider().getName());
		lblProviderContratado.setForeground(SystemColor.infoText);
		lblProviderContratado.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProviderContratado.setBounds(393, 86, 152, 22);
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
		comboBoxProvider.setBounds(393, 86, 152, 22);
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
		Controller.getInstancie().getTouristAgency().addContract(new ServiceContract(dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), this.serviceContract.getProvider(), 
				this.serviceContract.getModalitys(), (Double) spinnerRecargo.getValue())); // se inserta el contrato de servicio a nivel de base de datos
		panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos

	}

	public void updateServiceContract () throws SQLException { // Modo update
		Controller.getInstancie().getTouristAgency().updateContract(serviceContract, dateChooserStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (Double) spinnerRecargo.getValue());
		panelGerenteCreacionContrato.actualizarTablaContracts();// se actualiza la informacion de la tabla de contratos
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
