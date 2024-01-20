package JPanels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import JFrames.FrameGerenteCreacionContratoAlojamiento;
import JFrames.FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir;
import logica.AccommodationContract;
import logica.Season;
import modelosTablas.ModeloTablaSeasons;
import utils.AusentFilter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelGerenteCreacionContratoAlojamientoTemporada extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableSeason;
	private FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento;
	private AccommodationContract accommodationContract;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private JPanel panelBotones;
	private JTextField textFieldName;
	private JComboBox <String> comboBoxTypeOfSeason;
	private JLabel lblRestoreFilters;
	private JDateChooser dateChooserStratDateMin;
	private JDateChooser dateChooserStratDateMax;
	private JDateChooser dateChooserTerminationDateMin;
	private JDateChooser dateChooserTerminationDateMax;
	private int mouseX, mouseY;
	private String searchName;
	private boolean isRestoreFilters; // atributo para definir el estado del proceso de restauracion de los filtros

	/**
	 * Create the panel.
	 */


	public PanelGerenteCreacionContratoAlojamientoTemporada(FrameGerenteCreacionContratoAlojamiento ca) {
		this.isRestoreFilters = false;
		this.searchName = "";
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				frameGerenteCreacionContratoAlojamiento.setLocation(x - mouseX, y - mouseY);

			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				tableSeason.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoAlojamiento = ca;
		this.accommodationContract = this.frameGerenteCreacionContratoAlojamiento.getAccommodationContract();
		setLayout(null);
		setBackground(new Color(18, 95, 115));
		setBounds(0, 0, 853, 577);
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new MatteBorder(1, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTable.setBounds(0, 163, 853, 414);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableSeason = new JTable();
		tableSeason.setRowHeight(30);
		tableSeason.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableSeason.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableSeason.getTableHeader().setForeground(Color.black);
		tableSeason.getTableHeader().setBackground(SystemColor.black);
		tableSeason.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableSeason.setModel(new ModeloTablaSeasons());
		scrollPane.setViewportView(tableSeason);

		JLabel lblSeason = new JLabel("SEASONS");
		lblSeason.setForeground(SystemColor.textHighlightText);
		lblSeason.setFont(new Font("Dialog", Font.BOLD, 26));
		lblSeason.setBounds(338, 24, 134, 30);
		add(lblSeason);



		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameGerenteCreacionContratoAlojamiento.cambioDePanel(frameGerenteCreacionContratoAlojamiento.getPanelAccommodationContract());
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAtras.setIcon(new ImageIcon(PanelGerenteCreacionContratoAlojamientoTemporada.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(0, 0, 53, 43);
		add(lblAtras);

		panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelBotones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelBotones.setBackground(new Color(18, 95, 115));
		panelBotones.setBounds(664, 28, 189, 60);
		add(panelBotones);

		comboBoxTypeOfSeason = new JComboBox <String>();
		this.llenarComboBoxTypeOfSeason(); // se llena el combobox de tipo de temporada con las posibles temporadas de un contrato
		comboBoxTypeOfSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFilters)
					actualizarTablaSeasons(); // se actualiza la informacion de la tabla seasons
			}
		});
		comboBoxTypeOfSeason.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxTypeOfSeason.setBounds(184, 118, 133, 22);
		add(comboBoxTypeOfSeason);

		JLabel lblNewLabel = new JLabel("Type:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(184, 85, 133, 22);
		add(lblNewLabel);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Dialog", Font.BOLD, 18));
		lblName.setBounds(10, 85, 68, 22);
		add(lblName);

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
				}
				actualizarTablaSeasons(); // se actualiza la informacion de la tabla seasons
			}
		});
		textFieldName.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldName.setBounds(10, 120, 133, 22);
		add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate.setForeground(SystemColor.textHighlightText);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblStartDate.setBounds(365, 85, 236, 22);
		add(lblStartDate);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(433, 112, 30, 14);
		add(lblMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(433, 137, 33, 14);
		add(lblMax);

		JLabel lblTerminationDate = new JLabel("Termination Date:");
		lblTerminationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminationDate.setForeground(SystemColor.textHighlightText);
		lblTerminationDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTerminationDate.setBounds(611, 85, 236, 22);
		add(lblTerminationDate);

		lblRestoreFilters = new JLabel("Restore Filters");
		lblRestoreFilters.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFilters(); // se restuaran los filtros
			}
		});
		lblRestoreFilters.setForeground(SystemColor.textHighlightText);
		lblRestoreFilters.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRestoreFilters.setBounds(10, 64, 140, 14);
		add(lblRestoreFilters);

		dateChooserStratDateMin = new JDateChooser();
		dateChooserStratDateMin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (!isRestoreFilters && e.getPropertyName().equals("date"))
					actualizarTablaSeasons(); // se actualiza la informacion de la tabla seasons
			}
		});
		dateChooserStratDateMin.setBounds(473, 112, 97, 20);
		add(dateChooserStratDateMin);

		dateChooserStratDateMax = new JDateChooser();
		dateChooserStratDateMax.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (!isRestoreFilters && e.getPropertyName().equals("date"))
					actualizarTablaSeasons(); // se actualiza la informacion de la tabla seasons
			}
		});
		dateChooserStratDateMax.setBounds(473, 137, 97, 20);
		add(dateChooserStratDateMax);

		JLabel lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(660, 112, 30, 14);
		add(lblMin_1);

		dateChooserTerminationDateMin = new JDateChooser();
		dateChooserTerminationDateMin.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (!isRestoreFilters && e.getPropertyName().equals("date"))
					actualizarTablaSeasons(); // se actualiza la informacion de la tabla seasons
			}
		});
		dateChooserTerminationDateMin.setBounds(700, 112, 97, 20);
		add(dateChooserTerminationDateMin);

		JLabel lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(660, 137, 33, 14);
		add(lblMax_1);

		dateChooserTerminationDateMax = new JDateChooser();
		dateChooserTerminationDateMax.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (!isRestoreFilters && e.getPropertyName().equals("date"))
					actualizarTablaSeasons(); // se actualiza la informacion de la tabla seasons
			}
		});
		dateChooserTerminationDateMax.setBounds(700, 137, 97, 20);
		add(dateChooserTerminationDateMax);

		this.addButtons();
		this.actualizarTablaSeasons();
	}

	private void addButtons () {
		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteCreacionContratoAlojamientoTemporada.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir frameAddSeason = new FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir(PanelGerenteCreacionContratoAlojamientoTemporada.this);
				frameAddSeason.setVisible(true);
				frameGerenteCreacionContratoAlojamiento.setEnabled(false); // se inhabilita el frame
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
		lblAnnadir.setBounds(535, 32, 155, 20);
		panelBotones.add(lblAnnadir);

		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(PanelGerenteCreacionContratoAlojamientoTemporada.class.getResource("/images/Trash.png")));
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deleteElementsTable(); // se eliminan las temporadas seleccionadas
				} catch (SQLException e1) {

					e1.printStackTrace();
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
		lblEliminar.setBounds(535, 53, 155, 20);
		panelBotones.add(lblEliminar);
	}

	public FrameGerenteCreacionContratoAlojamiento getFrameGerenteCreacionContratoAlojamiento() {
		return frameGerenteCreacionContratoAlojamiento;
	}

	public void setFrameGerenteCreacionContratoAlojamiento(
			FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento) {
		this.frameGerenteCreacionContratoAlojamiento = frameGerenteCreacionContratoAlojamiento;
	}

	public AccommodationContract getAccommodationContract() {
		return accommodationContract;
	}

	public void setAccommodationContract(AccommodationContract accommodationContract) {
		this.accommodationContract = accommodationContract;
	}
	
	private void llenarComboBoxTypeOfSeason () { // Temporal
		this.comboBoxTypeOfSeason.addItem("All");
		this.comboBoxTypeOfSeason.addItem("Alta");
		this.comboBoxTypeOfSeason.addItem("Baja");
		this.comboBoxTypeOfSeason.addItem("Media");
	}

	private void restoreFilters () {
		this.isRestoreFilters = true; // se define el estado de restuaracion de los filtros para evitar llamadas innecesarias a los metodos de actualizar
		// Se restuaran los filtros por defecto
		this.textFieldName.setText("");
		this.comboBoxTypeOfSeason.setSelectedIndex(0);
		this.dateChooserStratDateMin.setDate(null);
		this.dateChooserStratDateMax.setDate(null);
		this.dateChooserTerminationDateMin.setDate(null);
		this.dateChooserTerminationDateMax.setDate(null);
		this.isRestoreFilters = false; // se notifica de la finalizacion del proceso de restauracion
		this.actualizarTablaSeasons(); // se actualiza la información de la tabla de las temporadas
	}

	public void actualizarTablaSeasons () {	
		this.actualizarTablaSeasons(accommodationContract.getSeasons(this.definirName(), this.definirTypeOfSeason(), 
				(this.dateChooserStratDateMin.getDate() != null) ? this.dateChooserStratDateMin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null,
						(this.dateChooserStratDateMax.getDate() != null) ? this.dateChooserStratDateMax.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, 
								(this.dateChooserTerminationDateMin.getDate() != null) ? this.dateChooserTerminationDateMin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, 
										(this.dateChooserTerminationDateMax.getDate() != null) ? this.dateChooserTerminationDateMax.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null)); // se obtienen las actividades del provedor de servicios
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	private String definirName () { // Metodo para definir el nombre seleccionado en el filtro
		String name = this.textFieldName.getText();

		if (name.equalsIgnoreCase("")) // si el filtro del nombre está vacio
			name = null;

		return name;
	}

	private String definirTypeOfSeason () { // Metodo para definir el tipo de temporada seleccionado en el filtro
		String typeOfSeason = (String) this.comboBoxTypeOfSeason.getSelectedItem();

		if (typeOfSeason.equalsIgnoreCase(AusentFilter.entintyField)) // si el filtro del nombre está vacio
			typeOfSeason = null;

		return typeOfSeason;
	}



	private void actualizarTablaSeasons(ArrayList<Season> seasons){
		reiniciarTable(this.tableSeason);


		for (Season sea : seasons) {
			((ModeloTablaSeasons) tableSeason.getModel()).addElement(sea);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableSeason.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (accommodationContract.getId() == -1)
				accommodationContract.deleteSeasonLogic(((ModeloTablaSeasons) tableSeason.getModel()).getElement(rows[i])); // se elimina solo la temporada de la logica del negocio
			else
				accommodationContract.deleteSeason(((ModeloTablaSeasons) tableSeason.getModel()).getElement(rows[i])); // se elimina la temporada de la logica del negocio y de la base de datos
		}


		this.actualizarTablaSeasons();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaSeasons) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtons() {
		if (this.tableSeason.getSelectedRowCount() != 0)
			this.lblEliminar.setEnabled(true);
		else
			this.lblEliminar.setEnabled(false);
	}
}
