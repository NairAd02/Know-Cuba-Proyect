package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import JFrames.FrameAdvertencia;
import JFrames.FrameDecisor;
import JFrames.FrameInformacionPaquete;
import JFrames.FramePrincipal;
import logica.Controller;
import logica.Hotel;
import logica.Provider;
import logica.TouristPackage;
import modelosTablas.ModeloTablaAccommodationProvider;
import utils.ConnectionDataBase;
import utils.Operations;
import utils.Semaphore;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.NumberFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class PanelGestionPaquetesTuristicos extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private JLabel lblShowPlans;
	private String searchName;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelBotones;
	private JPanel panelFiltros;
	private JPanel panelSuperior;
	private JLabel lblTitleSeccion;
	private JPanel panelPaquetesTuristicos;
	private JScrollPane scrollPane;
	private PanelLienzoPaquetesTuristicos panelLienzoPaquetesTuristicos;
	private JPanel panelFiltrosStartDate;
	private JLabel lblNewLabel;
	private JPanel panelMinMaxStartDate;
	private JLabel lblMin;
	private JPanel panelFilterName;
	private JPanel panelTextFieldName;
	private JTextField textFieldName;
	private JPanel panelFiltrosTerminationDate;
	private JLabel lblTerminationDate;
	private JPanel panelMinMaxTerminationDate;
	private JLabel lblMin_1;
	private JDateChooser dateChooserTerminationDateMin;
	private JLabel lblMax_1;
	private JDateChooser dateChooserTerminationDateMax;
	private JPanel panelFiltrosCantAviable;
	private JLabel lblCantAvailable;
	private JLabel lblMin_2;
	private JPanel panelMinMaxCantAvaible;
	private JLabel lblMin_3;
	private JSpinner spinnerCantAvaibleMin;
	private JLabel lblMin_4;
	private JSpinner spinnerCantAvaibleMax;
	private JPanel panelFiltrosPrice;
	private JLabel lblPrice;
	private JPanel panelMinMaxPrice;
	private JLabel lblMin_5;
	private JSpinner spinnerPriceMin;
	private JLabel lblMin_6;
	private JSpinner spinnerPriceMax;
	private JDateChooser dateChooserStratDateMin;
	private JDateChooser dateChooserStratDateMax;
	private boolean isRestoreFilters;
	private JLabel lblRestore;

	private class Eliminar extends Thread { // Hilo para la eliminacion

		public void run () {
			synchronized (Semaphore.samaphore) { 
				try {
					Semaphore.samaphore.wait(); // se duerme al hilo hasta esperar la confirmacion del usuario
					if (Controller.getInstancie().isConfirmacion()) { // si el usuario dió el consentimiento de realizar la operación
						Controller.getInstancie().setConfirmacion(false); // se establece el estado de la confirmación por defecto
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}


	private void crearFrameDecisor () {
		FrameDecisor frameDecisor = new FrameDecisor(FramePrincipal.getIntancie(), "Seguro que desea eliminar los paquetes turisticos seleccionados seleccionados");
		frameDecisor.setVisible(true);
		FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
	}

	private void crearFrameNotificacion () {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia("Han sido elimanado correctamente los paquetes turisticos");
		frameAdvertencia.setVisible(true);
	}

	public PanelGestionPaquetesTuristicos() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		this.isRestoreFilters = false;
		searchName = "";
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(0, 41, 1244, 678);
		setLayout(new BorderLayout(0, 0));

		panelContenedorTable = new JPanel();
		add(panelContenedorTable, BorderLayout.CENTER);
		panelContenedorTable.setLayout(new BorderLayout(0, 0));

		panelOpciones = new JPanel();

		panelContenedorTable.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(new BorderLayout(0, 0));
		panelOpciones.setBackground(new Color(18, 95, 115));

		panelFiltros = new JPanel();
		panelFiltros.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelFiltros, BorderLayout.WEST);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelBotones, BorderLayout.EAST);

		panelPaquetesTuristicos = new JPanel();
		panelContenedorTable.add(panelPaquetesTuristicos, BorderLayout.CENTER);
		panelPaquetesTuristicos.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelPaquetesTuristicos.add(scrollPane, BorderLayout.CENTER);

		panelLienzoPaquetesTuristicos = new PanelLienzoPaquetesTuristicos();
		scrollPane.setViewportView(panelLienzoPaquetesTuristicos);

		panelSuperior = new JPanel();
		panelSuperior.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelSuperior.setBackground(SystemColor.inactiveCaptionBorder);
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTitleSeccion = new JLabel("Tourist Package");
		lblTitleSeccion.setOpaque(true);
		lblTitleSeccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSeccion.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitleSeccion.setBorder(null);
		lblTitleSeccion.setBackground(SystemColor.inactiveCaptionBorder);
		panelSuperior.add(lblTitleSeccion);



		this.addButtons();

		this.actualizarPanelPaquetes();


	}

	private void addButtons () {

		lblAnnadir = new JLabel("");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameInformacionPaquete frameInformacionPaqueteTuristico = new FrameInformacionPaquete(PanelGestionPaquetesTuristicos.this, new TouristPackage());
				frameInformacionPaqueteTuristico.setVisible(true);
				FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAnnadir.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAnnadir.setBackground(new Color(18, 95, 115));
			}
		});
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(245, 19, 68, 52);
		panelBotones.add(lblAnnadir);

		lblDelete = new JLabel("");
		lblDelete.setOpaque(true);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Trash.png")));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					Eliminar eliminar = new Eliminar(); // se crea el nuevo hilo
					eliminar.start(); // se ejecuta el nuevo hilo
					crearFrameDecisor(); // se crea el frame decisor, donde el usuario dará su confirmación
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblDelete.isEnabled()) 
					lblDelete.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblDelete.isEnabled())
					lblDelete.setBackground(new Color(18, 95, 115));
			}
		});
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(new Color(18, 95, 115));
		lblDelete.setBounds(405, 19, 67, 52);
		panelBotones.add(lblDelete);
		lblShowPlans = new JLabel("");
		lblShowPlans.setOpaque(true);
		lblShowPlans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowPlans.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Edit.png")));
		lblShowPlans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblShowPlans.isEnabled()) {

				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblShowPlans.isEnabled())
					lblShowPlans.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblShowPlans.isEnabled())
					lblShowPlans.setBackground(new Color(18, 95, 115));
			}
		});
		lblShowPlans.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlans.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowPlans.setBackground(new Color(18, 95, 115));
		lblShowPlans.setBounds(564, 19, 67, 52);
		panelBotones.add(lblShowPlans);

		panelFilterName = new JPanel();
		panelFilterName.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFilterName);
		panelFilterName.setLayout(new BorderLayout(0, 0));

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFilterName.add(lblName, BorderLayout.NORTH);

		panelTextFieldName = new JPanel();
		panelTextFieldName.setBackground(new Color(18, 95, 115));
		panelFilterName.add(panelTextFieldName, BorderLayout.SOUTH);

		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!isRestoreFilters) {
					searchName = "";
					if(e.getKeyChar() != ''){ // si la key es distinta del boton delete
						searchName = textFieldName.getText()+ e.getKeyChar();

					}
					else{
						searchName = textFieldName.getText();
					}


					actualizarPanelPaquetes(); // se actualiza la informacion de los paquetes en pantalla

				}
			}
		});
		textFieldName.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelTextFieldName.add(textFieldName);
		textFieldName.setColumns(10);

		panelFiltrosStartDate = new JPanel();
		panelFiltrosStartDate.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosStartDate);
		panelFiltrosStartDate.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosStartDate.add(lblNewLabel, BorderLayout.NORTH);

		panelMinMaxStartDate = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMinMaxStartDate.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelMinMaxStartDate.setBackground(new Color(18, 95, 115));
		panelFiltrosStartDate.add(panelMinMaxStartDate, BorderLayout.SOUTH);

		lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxStartDate.add(lblMin);

		dateChooserStratDateMin = new JDateChooser();
		dateChooserStratDateMin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFilters) {
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes	
				}
			}
		});
		dateChooserStratDateMin.setPreferredSize(new Dimension(120, 22));
		dateChooserStratDateMin.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxStartDate.add(dateChooserStratDateMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxStartDate.add(lblMax);

		dateChooserStratDateMax = new JDateChooser();
		dateChooserStratDateMax.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFilters) {
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes		
				}
			}
		});
		dateChooserStratDateMax.setPreferredSize(new Dimension(120, 22));
		dateChooserStratDateMax.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxStartDate.add(dateChooserStratDateMax);

		panelFiltrosTerminationDate = new JPanel();
		panelFiltrosTerminationDate.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosTerminationDate);
		panelFiltrosTerminationDate.setLayout(new BorderLayout(0, 0));

		lblTerminationDate = new JLabel("Termination Date");
		lblTerminationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminationDate.setForeground(SystemColor.textHighlightText);
		lblTerminationDate.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosTerminationDate.add(lblTerminationDate, BorderLayout.NORTH);

		panelMinMaxTerminationDate = new JPanel();
		panelMinMaxTerminationDate.setBackground(new Color(18, 95, 115));
		panelFiltrosTerminationDate.add(panelMinMaxTerminationDate, BorderLayout.CENTER);

		lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxTerminationDate.add(lblMin_1);

		dateChooserTerminationDateMin = new JDateChooser();
		dateChooserTerminationDateMin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFilters) {
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes	
				}
			}
		});
		dateChooserTerminationDateMin.setPreferredSize(new Dimension(120, 22));
		dateChooserTerminationDateMin.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxTerminationDate.add(dateChooserTerminationDateMin);

		lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxTerminationDate.add(lblMax_1);

		dateChooserTerminationDateMax = new JDateChooser();
		dateChooserTerminationDateMax.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFilters) {
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes		
				}
			}
		});
		dateChooserTerminationDateMax.setPreferredSize(new Dimension(120, 22));
		dateChooserTerminationDateMax.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxTerminationDate.add(dateChooserTerminationDateMax);

		panelFiltrosCantAviable = new JPanel();
		panelFiltrosCantAviable.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosCantAviable);
		panelFiltrosCantAviable.setLayout(new BorderLayout(0, 0));

		lblCantAvailable = new JLabel("Cant Available");
		lblCantAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantAvailable.setForeground(SystemColor.textHighlightText);
		lblCantAvailable.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosCantAviable.add(lblCantAvailable, BorderLayout.NORTH);

		panelMinMaxCantAvaible = new JPanel();
		panelMinMaxCantAvaible.setBackground(new Color(18, 95, 115));
		FlowLayout flowLayout_1 = (FlowLayout) panelMinMaxCantAvaible.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelFiltrosCantAviable.add(panelMinMaxCantAvaible, BorderLayout.SOUTH);

		lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxCantAvaible.add(lblMin_3);

		spinnerCantAvaibleMin = new JSpinner();
		Operations.crearJSpinnerNumericoInteger(spinnerCantAvaibleMin, new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantAvaibleMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFilters) {
					actualizarPanelPaquetes(); // se actualiza la informacion del paquete				
				}
			}
		});
		spinnerCantAvaibleMin.setPreferredSize(new Dimension(70, 20));
		spinnerCantAvaibleMin.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxCantAvaible.add(spinnerCantAvaibleMin);

		lblMin_4 = new JLabel("Max:");
		lblMin_4.setForeground(SystemColor.textHighlightText);
		lblMin_4.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxCantAvaible.add(lblMin_4);

		spinnerCantAvaibleMax = new JSpinner();
		spinnerCantAvaibleMax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		Operations.crearJSpinnerNumericoInteger(spinnerCantAvaibleMax, new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantAvaibleMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFilters) {
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes		
				}
			}
		});
		spinnerCantAvaibleMax.setPreferredSize(new Dimension(70, 20));
		spinnerCantAvaibleMax.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxCantAvaible.add(spinnerCantAvaibleMax);

		panelFiltrosPrice = new JPanel();
		panelFiltrosPrice.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosPrice);
		panelFiltrosPrice.setLayout(new BorderLayout(0, 0));

		lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosPrice.add(lblPrice, BorderLayout.NORTH);

		panelMinMaxPrice = new JPanel();
		panelMinMaxPrice.setBackground(new Color(18, 95, 115));
		panelFiltrosPrice.add(panelMinMaxPrice, BorderLayout.SOUTH);

		lblMin_5 = new JLabel("Min:");
		lblMin_5.setForeground(SystemColor.textHighlightText);
		lblMin_5.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxPrice.add(lblMin_5);

		spinnerPriceMin = new JSpinner();
		spinnerPriceMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMin, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFilters) {	
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes
				}
			}
		});
		spinnerPriceMin.setPreferredSize(new Dimension(70, 20));
		spinnerPriceMin.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxPrice.add(spinnerPriceMin);

		lblMin_6 = new JLabel("Max:");
		lblMin_6.setForeground(SystemColor.textHighlightText);
		lblMin_6.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxPrice.add(lblMin_6);

		spinnerPriceMax = new JSpinner();
		spinnerPriceMax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMax, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFilters) {
					actualizarPanelPaquetes(); // Se actualiza en pantalla la informacion de los paquetes
				}
			}
		});
		spinnerPriceMax.setPreferredSize(new Dimension(70, 20));
		spinnerPriceMax.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxPrice.add(spinnerPriceMax);

		lblRestore = new JLabel("                       ");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFilters();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestore.setIcon(new ImageIcon(PanelGestionPaquetesTuristicos.class.getResource("/images/Restart.png")));
		panelFiltros.add(lblRestore);


	}

	private void restoreFilters () {
		this.isRestoreFilters = true; // se indica que empieza el proeso de restauracion de filtros
		this.textFieldName.setText("");
		this.dateChooserStratDateMin.setDate(null);
		this.dateChooserStratDateMax.setDate(null);
		this.dateChooserTerminationDateMin.setDate(null);
		this.dateChooserTerminationDateMax.setDate(null);
		this.spinnerCantAvaibleMin.setValue(0);
		this.spinnerCantAvaibleMax.setValue(0);
		this.spinnerPriceMin.setValue(0.0);
		this.spinnerPriceMax.setValue(0.0);
		this.actualizarPanelPaquetes(); // se actualiza en pantalla la informacion de los paquetes
		this.isRestoreFilters = false; // se indica el fin del proceso de restauracion de los filtros

	}

	public void actualizarPanelPaquetes () {
		try {
			this.actualizarPanelPaquetes(Controller.getInstancie().getTouristAgency().getTouristPackages(this.searchName, 
					(this.dateChooserStratDateMin.getDate() != null) ? this.dateChooserStratDateMin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, 
							(this.dateChooserStratDateMax.getDate() != null) ? this.dateChooserStratDateMax.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, 
									(this.dateChooserTerminationDateMin.getDate() != null) ? this.dateChooserTerminationDateMin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, 
											(this.dateChooserTerminationDateMax.getDate() != null) ? this.dateChooserTerminationDateMax.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, 
													(Double) this.spinnerPriceMin.getValue(), (Double) this.spinnerPriceMax.getValue(), (Integer) this.spinnerCantAvaibleMin.getValue(), (Integer) this.spinnerCantAvaibleMax.getValue()));
			ConnectionDataBase.commit(); // se confirman las operaciones realizadas en la base de datos
		} catch (SQLException e) {
			try {
				ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas en la base de datos
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
	}



	private void actualizarPanelPaquetes (ArrayList<TouristPackage> touristPackages) throws SQLException {

		this.panelLienzoPaquetesTuristicos.clearElements();
		for (int i = 0; i < touristPackages.size(); i++) {
			if (touristPackages.get(i).verificarPaquete()) // Se comprueba si el paquete es valido (Se contiene al menos una modalidad)
				this.panelLienzoPaquetesTuristicos.addPanelPaqueteTuristico(new PanelPaqueteTuristico(touristPackages.get(i)));
			else {
				Controller.getInstancie().getTouristAgency().deleteTouristPackage(touristPackages.get(i)); // se elimina el paquete de la logica del negocio y de la base de datos*/
				i--; // se decrementa por la eliminacion
			}
		}

		this.repintarPanel(); // se repinta la infromacion del panel
	}

	/*private void actualizarEstadoButtons () {
		this.actualizarEstadoButtonDelete();
		this.actualizarEstadoButtonUpdate();
	}

	private void actualizarEstadoButtonDelete () {
		if (tableContracts.getSelectedRowCount() != 0)
			lblDelete.setEnabled(true);
		else
			lblDelete.setEnabled(false);
	}

	private void actualizarEstadoButtonUpdate () {
		if (tableContracts.getSelectedRowCount() != 0)
			lblUpdate.setEnabled(true);
		else
			lblUpdate.setEnabled(false);
	}*/

	private void repintarPanel () {
		repaint();
		revalidate();
	}
}
