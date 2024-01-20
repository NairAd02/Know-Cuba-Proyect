package JPanels;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import JFrames.FrameGerenteCreacionContratoServicioAnnadirServiceModality;
import JFrames.FrameGerenteCreacionContratoServivio;
import logica.Activity;
import logica.Modality;
import logica.ServiceContract;
import logica.ServiceModality;
import modelosTablas.ModeloTablaServiceModality;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import com.toedter.calendar.JDateChooser;
import utils.AusentFilter;

import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PanelCreacionContratoServicioServiceModality extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableServiceModalitys;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private FrameGerenteCreacionContratoServivio frameGerenteCreacionContratoServivio;
	private ServiceContract serviceContract;
	private JPanel panelBotones;
	private int mouseX, mouseY;
	private JComboBox<Activity> comboBoxActivities;
	private JDateChooser dateChooserReleaseDateMax;
	private JDateChooser dateChooserReleaseDateMin;
	private JSpinner spinnerPriceMin;
	private JSpinner spinnerPriceMax;
	private boolean isRestoreFilters;


	public FrameGerenteCreacionContratoServivio getFrameGerenteCreacionContratoServivio() {
		return frameGerenteCreacionContratoServivio;
	}

	public void setFrameGerenteCreacionContratoServivio(
			FrameGerenteCreacionContratoServivio frameGerenteCreacionContratoServivio) {
		this.frameGerenteCreacionContratoServivio = frameGerenteCreacionContratoServivio;
	}

	public ServiceContract getServiceContract() {
		return serviceContract;
	}

	public void setServiceContract(ServiceContract serviceContract) {
		this.serviceContract = serviceContract;
	}

	public PanelCreacionContratoServicioServiceModality(FrameGerenteCreacionContratoServivio cs) {
		this.isRestoreFilters = false;
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				frameGerenteCreacionContratoServivio.setLocation(x - mouseX, y - mouseY);

			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				tableServiceModalitys.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoServivio = cs;
		this.serviceContract = this.frameGerenteCreacionContratoServivio.getServiceContract();
		setLayout(null);
		setBackground(new Color(18, 95, 115));
		setBounds(0, 0, 853, 577);
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTable.setBounds(0, 163, 853, 414);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableServiceModalitys = new JTable();
		tableServiceModalitys.setRowHeight(30);
		tableServiceModalitys.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableServiceModalitys.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableServiceModalitys.getTableHeader().setForeground(Color.black);
		tableServiceModalitys.getTableHeader().setBackground(SystemColor.black);
		tableServiceModalitys.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableServiceModalitys.setModel(new ModeloTablaServiceModality());
		scrollPane.setViewportView(tableServiceModalitys);

		JLabel lblSeason = new JLabel("Service Modalities");
		lblSeason.setForeground(SystemColor.textHighlightText);
		lblSeason.setFont(new Font("Dialog", Font.BOLD, 26));
		lblSeason.setBounds(308, 24, 236, 30);
		add(lblSeason);


		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameGerenteCreacionContratoServivio.cambioDePanel(frameGerenteCreacionContratoServivio.getPanelServiceContract());
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAtras.setIcon(new ImageIcon(PanelCreacionContratoServicioServiceModality.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(10, 11, 53, 43);
		add(lblAtras);

		comboBoxActivities = new JComboBox<Activity>();
		this.llenarComboBoxActivities(); // se llena el combobox de las actividades con la informacion de las actitivades del proveedor de servicios elgido para el contrato
		comboBoxActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaModalitys();
			}
		});
		comboBoxActivities.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxActivities.setBounds(31, 120, 133, 22);
		add(comboBoxActivities);

		JLabel lblActivity = new JLabel("Activity:");
		lblActivity.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivity.setForeground(SystemColor.textHighlightText);
		lblActivity.setFont(new Font("Dialog", Font.BOLD, 18));
		lblActivity.setBounds(31, 94, 133, 19);
		add(lblActivity);

		JLabel lblReleaseDate = new JLabel("Release Date:");
		lblReleaseDate.setForeground(SystemColor.textHighlightText);
		lblReleaseDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblReleaseDate.setBounds(346, 91, 133, 22);
		add(lblReleaseDate);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(267, 125, 30, 14);
		add(lblMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(408, 125, 40, 14);
		add(lblMax);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(670, 86, 103, 30);
		add(lblPrice);

		JLabel lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(619, 121, 30, 14);
		add(lblMin_1);

		spinnerPriceMin = new JSpinner();
		spinnerPriceMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFilters)
					actualizarTablaModalitys();
			}
		});
		spinnerPriceMin.setModel(new SpinnerNumberModel(Double.valueOf(-1), null, null, Double.valueOf(1)));
		spinnerPriceMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMin.setBounds(659, 121, 53, 20);
		add(spinnerPriceMin);

		JLabel lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(722, 121, 40, 14);
		add(lblMax_1);

		spinnerPriceMax = new JSpinner();
		spinnerPriceMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFilters)
					actualizarTablaModalitys();
				
				

			}
		});
		spinnerPriceMax.setModel(new SpinnerNumberModel(Double.valueOf(-1), null, null, Double.valueOf(1)));
		spinnerPriceMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMax.setBounds(761, 121, 53, 20);
		add(spinnerPriceMax);

		dateChooserReleaseDateMin = new JDateChooser();
		dateChooserReleaseDateMin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFilters) 
					actualizarTablaModalitys();
			}
		});

		dateChooserReleaseDateMin.setFont(new Font("Dialog", Font.PLAIN, 11));
		dateChooserReleaseDateMin.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserReleaseDateMin.setBounds(307, 120, 91, 22);
		add(dateChooserReleaseDateMin);

		dateChooserReleaseDateMax = new JDateChooser();
		dateChooserReleaseDateMax.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFilters)
					actualizarTablaModalitys();
			}
		});

		dateChooserReleaseDateMax.setFont(new Font("Dialog", Font.PLAIN, 11));
		dateChooserReleaseDateMax.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserReleaseDateMax.setBounds(452, 120, 91, 22);
		add(dateChooserReleaseDateMax);

		panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelBotones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelBotones.setBackground(new Color(18, 95, 115));
		panelBotones.setBounds(664, 28, 189, 60);
		add(panelBotones);

		JLabel lblRestoreFilters = new JLabel("Restore Filters");
		lblRestoreFilters.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFilters(); // se restuaran los filtros con sus valores iniciales
				
				
			}
		});
		lblRestoreFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFilters.setForeground(SystemColor.textHighlightText);
		lblRestoreFilters.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRestoreFilters.setBounds(20, 65, 150, 19);
		add(lblRestoreFilters);

		this.addButtons();
		this.actualizarTablaModalitys(); // se actualiza la tabla de las modalidades con las modalidades definidas en el contrato

	}


	private void llenarComboBoxActivities() {
		ArrayList<Activity> activities = this.serviceContract.getActivitiesServiceProvider();

		comboBoxActivities.addItem(new Activity("All"));

		for (Activity activity : activities) {
			comboBoxActivities.addItem(activity);
		}
	}
	
	private void restoreFilters () {
		this.isRestoreFilters = true; // se indica que va a empezar una restauracion de los filtros
		this.comboBoxActivities.setSelectedIndex(0);
		// se restuara el filtro del precio
		this.spinnerPriceMax.setValue(-1.0);
		this.spinnerPriceMin.setValue(-1.0);
		// se restaura el filtro de la fecha de realizacion
		this.dateChooserReleaseDateMax.setDate(null);
		this.dateChooserReleaseDateMin.setDate(null);
		actualizarTablaModalitys();
		this.isRestoreFilters = false; // se indica que se terminó la restauracion de los filtros
	}

	private void addButtons() {
		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelCreacionContratoServicioServiceModality.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoServicioAnnadirServiceModality frameServiceModalityAdd = new FrameGerenteCreacionContratoServicioAnnadirServiceModality(PanelCreacionContratoServicioServiceModality.this);
				frameServiceModalityAdd.setVisible(true);
				frameGerenteCreacionContratoServivio.setEnabled(false); // se inhabilita el frame
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(485, 32, 155, 20);
		panelBotones.add(lblAnnadir);

		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(PanelCreacionContratoServicioServiceModality.class.getResource("/images/Trash.png")));
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblEliminar.isEnabled()) {
					try {
						deleteElementsTable();
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
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(new Color(18, 95, 115));
		lblEliminar.setBounds(485, 53, 155, 20);
		panelBotones.add(lblEliminar);
	}


	public void actualizarTablaModalitys() {
		this.actualizarTablaModalitys(this.serviceContract.getModalitys(this.definirActivitySeleccionada(),
				(this.dateChooserReleaseDateMin.getDate() != null) ? this.dateChooserReleaseDateMin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null,
						(this.dateChooserReleaseDateMax.getDate() != null) ? this.dateChooserReleaseDateMax.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, (Double) this.spinnerPriceMin.getValue(),
								(Double) this.spinnerPriceMax.getValue())); // se obtienen las actividades del proveedor de servicios
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	private Activity definirActivitySeleccionada() {
		Activity activity = (Activity) this.comboBoxActivities.getSelectedItem();

		if (activity.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si es igual a All
			activity = null;


		return activity;

	}

	private void actualizarTablaModalitys(ArrayList<Modality> modalitys) {
		reiniciarTable(this.tableServiceModalitys);


		for (Modality mod : modalitys) {
			((ModeloTablaServiceModality) tableServiceModalitys.getModel()).addElement((ServiceModality) mod);
		}
	}

	private void deleteElementsTable() throws SQLException {
		int[] rows = tableServiceModalitys.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (serviceContract.getId() == -1)
				serviceContract.deleteModalityLogic(((ModeloTablaServiceModality) tableServiceModalitys.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				serviceContract.deleteModality(((ModeloTablaServiceModality) tableServiceModalitys.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table) {

		for (int i = 0; table.getRowCount() != 0; i++) {
			i = 0;
			((ModeloTablaServiceModality) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtons() {
		if (this.tableServiceModalitys.getSelectedRowCount() != 0)
			this.lblEliminar.setEnabled(true);
		else
			this.lblEliminar.setEnabled(false);
	}
}
