package JFrames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import JPanels.PanelGerenteCreacionContratoAlojamientoTemporada;
import logica.AccommodationContract;
import logica.Season;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_1;
	private JTextField textFieldName;
	private JLabel lblAdd;
	private JDateChooser dateChooserStratDate;
	private JDateChooser dateChooserEndDate;
	private JTextPane textPaneDescription;
	private JComboBox <String> comboBoxTypeOfSeason;
	private JLabel lblX;
	private PanelGerenteCreacionContratoAlojamientoTemporada panelGerenteCreacionContratoAlojamientoTemporada;
	private AccommodationContract accommodationContract;
	private FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento;
	private int mouseX, mouseY;
	private Season season;
	private JLabel lblRestore;
	private JLabel lblErrorName;


	public FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir(PanelGerenteCreacionContratoAlojamientoTemporada at, Season s) {
		this.panelGerenteCreacionContratoAlojamientoTemporada = at;
		this.accommodationContract = this.panelGerenteCreacionContratoAlojamientoTemporada.getAccommodationContract();
		this.frameGerenteCreacionContratoAlojamiento = this.panelGerenteCreacionContratoAlojamientoTemporada.getFrameGerenteCreacionContratoAlojamiento();
		this.season = s;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 370);
		contentPane_1 = new JPanel();
		contentPane_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );
			}
		});
		contentPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		contentPane_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		contentPane_1.setBackground(new Color(18, 95, 115));
		setLocationRelativeTo(null);
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);

		JLabel lblSeasonMode = new JLabel("SEASON MODE");
		lblSeasonMode.setForeground(SystemColor.textHighlightText);
		lblSeasonMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSeasonMode.setBounds(27, 11, 169, 30);
		contentPane_1.add(lblSeasonMode);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lblX.setBounds(427, 0, 38, 38);
		contentPane_1.add(lblX);

		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(27, 52, 90, 23);
		contentPane_1.add(lblName);

		JLabel lblStartDate = new JLabel("START DATE :");
		lblStartDate.setForeground(SystemColor.textHighlightText);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(27, 86, 249, 23);
		contentPane_1.add(lblStartDate);

		JLabel lblTer = new JLabel("TERMINATION DATE :");
		lblTer.setForeground(SystemColor.textHighlightText);
		lblTer.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTer.setBounds(27, 120, 197, 23);
		contentPane_1.add(lblTer);

		JLabel lblDescription = new JLabel("DESCRIPTION :");
		lblDescription.setForeground(SystemColor.textHighlightText);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(27, 154, 146, 23);
		contentPane_1.add(lblDescription);

		JLabel lblTypeSeason = new JLabel("TYPE SEASON :");
		lblTypeSeason.setForeground(SystemColor.textHighlightText);
		lblTypeSeason.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTypeSeason.setBounds(27, 278, 146, 23);
		contentPane_1.add(lblTypeSeason);

		lblAdd = new JLabel();
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.setText("ADD");
		lblAdd.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					if (season.getId() == -1) { // Add
						try {
							addSeason();
							panelGerenteCreacionContratoAlojamientoTemporada.actualizarTablaSeasons(); // se actualiza la información de la tabla de las tempordadas
							FramePrincipal.mostarFrameNotificacion("It has been added successfully the season"); // se notifica de la accion realiza al usuario
							cerrarFrame();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else { // Update
						try {
							updateSeason();
							panelGerenteCreacionContratoAlojamientoTemporada.actualizarTablaSeasons(); // se actualiza la información de la tabla de las tempordadas
							FramePrincipal.mostarFrameNotificacion("It has been successfully modified the season"); // se notifica de la accion realiza al usuario
							cerrarFrame();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else
					mostrarErrores();

			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ocultarErrores();
			}
		});
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(117, 324, 235, 35);
		contentPane_1.add(lblAdd);

		textFieldName = new JTextField();
		textFieldName.setBounds(315, 56, 125, 20);
		contentPane_1.add(textFieldName);
		textFieldName.setColumns(10);

		dateChooserStratDate = new JDateChooser();
		dateChooserStratDate.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date")) {
					season.setStartDate(dateChooserStratDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					dateChooserEndDate.setMinSelectableDate(Date.from(season.getStartDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
			}
		});
		dateChooserStratDate.setBounds(315, 89, 125, 20);
		dateChooserStratDate.setMinSelectableDate(Date.from(this.accommodationContract.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		contentPane_1.add(dateChooserStratDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date")) {
					season.setTerminationDate(dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					dateChooserStratDate.setMaxSelectableDate(Date.from(season.getTerminationDate().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
			}
		});
		dateChooserEndDate.setBounds(315, 120, 125, 20);
		if (this.season.getId() == -1) // add
		dateChooserEndDate.setMinSelectableDate(Date.from(this.accommodationContract.getStartDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		dateChooserEndDate.setMaxSelectableDate(Date.from(this.accommodationContract.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		contentPane_1.add(dateChooserEndDate);

		comboBoxTypeOfSeason = new JComboBox <String>();
		comboBoxTypeOfSeason.setBounds(315, 281, 125, 22);
		contentPane_1.add(comboBoxTypeOfSeason);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(220, 157, 220, 109);
		contentPane_1.add(textPaneDescription);

		lblErrorName = new JLabel("Incorrect name");
		lblErrorName.setVisible(false);
		lblErrorName.setForeground(Color.RED);
		lblErrorName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblErrorName.setBounds(329, 31, 111, 14);
		contentPane_1.add(lblErrorName);



		this.llenarComboBoxTypeOfSeason();
		this.definirTexto();
		this.definirComponentes();
	}

	private void definirComponentes () {
		if (this.season.getId() != -1) // Update
			this.addLblRestore();
	}

	private void mostrarErrores () {
		if (!this.season.verificarIntervaloFechas() || !this.season.verificarFechasInContract(this.accommodationContract.getStartDate(), this.accommodationContract.getTerminationDate()))
			FramePrincipal.mostrarFrameInformacion(FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir.this, "Selected dates are incorrect");
		if (this.textFieldName.getText().equalsIgnoreCase(""))
			lblErrorName.setVisible(true);
	}

	private void ocultarErrores () {
		lblErrorName.setVisible(false);	
	}



	private void addLblRestore () {
		lblRestore = new JLabel("Restore");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation();
			}
		});
		lblRestore.setForeground(SystemColor.textHighlightText);
		lblRestore.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRestore.setBounds(234, 12, 75, 30);
		contentPane_1.add(lblRestore);
	}

	private void restoreInformation () {
		definirTexto();
	}

	private void definirTexto () {
		if (this.season.getId() != -1) { // Update 
			this.dateChooserStratDate.setDate(Date.from(this.season.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			this.dateChooserEndDate.setDate(Date.from(this.season.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			this.textFieldName.setText(this.season.getName());
			this.textPaneDescription.setText(this.season.getDescription());
			this.comboBoxTypeOfSeason.setSelectedItem(this.season.getTypeOfSeason());
			this.lblAdd.setText("UPDATE");
		}
		else {
			this.dateChooserStratDate.setDate(Date.from(this.accommodationContract.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			this.dateChooserEndDate.setDate(Date.from(this.accommodationContract.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			this.season.setStartDate(this.accommodationContract.getStartDate());
			this.season.setTerminationDate(this.accommodationContract.getTerminationDate());
			this.lblAdd.setText("ADD");
		}
	}


	private void llenarComboBoxTypeOfSeason () { // Temporal
		this.comboBoxTypeOfSeason.addItem("Alta");
		this.comboBoxTypeOfSeason.addItem("Baja");
		this.comboBoxTypeOfSeason.addItem("Media");
	}

	private boolean verificarCampos () {
		return (!this.textFieldName.getText().equalsIgnoreCase("") && this.season.verificarIntervaloFechas() && 
				this.season.verificarFechasInContract(this.accommodationContract.getStartDate(), this.accommodationContract.getTerminationDate()));
	}

	private void addSeason () throws SQLException {
		if (this.accommodationContract.getId() != -1) { // se asigna a la base de datos
			this.accommodationContract.addSeason(new Season(textFieldName.getText(), dateChooserStratDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
					dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (String) comboBoxTypeOfSeason.getSelectedItem(), this.accommodationContract.getId()));	
		}
		else {
			this.accommodationContract.addSeasonLogic(new Season(textFieldName.getText(), dateChooserStratDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
					dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (String) comboBoxTypeOfSeason.getSelectedItem()));
		}
	}

	private void updateSeason () throws SQLException {
		if (this.accommodationContract.getId() != -1) { // se asigna a la base de datos
			this.accommodationContract.updateSeason(this.season, textFieldName.getText(), dateChooserStratDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
					dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (String) comboBoxTypeOfSeason.getSelectedItem());	
		}
		else {
			this.accommodationContract.updateSeasonLogic(this.season, textFieldName.getText(), dateChooserStratDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
					dateChooserEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), textPaneDescription.getText(), (String) comboBoxTypeOfSeason.getSelectedItem());
		}
	}


	private void cerrarFrame () {
		frameGerenteCreacionContratoAlojamiento.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}
}
