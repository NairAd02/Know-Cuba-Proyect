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

public class FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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


	public FrameGerenteCreacionContratoAlojamientoTemporadaAnnadir(PanelGerenteCreacionContratoAlojamientoTemporada at) {
		this.panelGerenteCreacionContratoAlojamientoTemporada = at;
		this.accommodationContract = this.panelGerenteCreacionContratoAlojamientoTemporada.getAccommodationContract();
		this.frameGerenteCreacionContratoAlojamiento = this.panelGerenteCreacionContratoAlojamientoTemporada.getFrameGerenteCreacionContratoAlojamiento();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 370);
		contentPane = new JPanel();
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
		contentPane.setBackground(new Color(5, 150, 177));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeasonMode = new JLabel("SEASON MODE");
		lblSeasonMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSeasonMode.setBounds(27, 11, 169, 30);
		contentPane.add(lblSeasonMode);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(27, 52, 90, 23);
		contentPane.add(lblName);

		JLabel lblStartDate = new JLabel("START DATE :");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(27, 86, 249, 23);
		contentPane.add(lblStartDate);

		JLabel lblTer = new JLabel("TERMINATION DATE :");
		lblTer.setForeground(SystemColor.info);
		lblTer.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTer.setBounds(27, 120, 197, 23);
		contentPane.add(lblTer);

		JLabel lblDescription = new JLabel("DESCRIPTION :");
		lblDescription.setForeground(SystemColor.info);
		lblDescription.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescription.setBounds(27, 154, 146, 23);
		contentPane.add(lblDescription);

		JLabel lblTypeSeason = new JLabel("TYPE SEASON :");
		lblTypeSeason.setForeground(SystemColor.info);
		lblTypeSeason.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTypeSeason.setBounds(27, 278, 146, 23);
		contentPane.add(lblTypeSeason);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (verificarCampos()) {
					try {
						addSeason();
						cerrarFrame();
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
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(117, 324, 235, 35);
		contentPane.add(lblAdd);

		textFieldName = new JTextField();
		textFieldName.setBounds(315, 56, 125, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		dateChooserStratDate = new JDateChooser();
		dateChooserStratDate.setBounds(315, 89, 125, 20);
		contentPane.add(dateChooserStratDate);

		dateChooserEndDate = new JDateChooser();
		dateChooserEndDate.setBounds(315, 120, 125, 20);
		contentPane.add(dateChooserEndDate);

		comboBoxTypeOfSeason = new JComboBox <String>();
		comboBoxTypeOfSeason.setBounds(315, 281, 125, 22);
		contentPane.add(comboBoxTypeOfSeason);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(220, 157, 220, 109);
		contentPane.add(textPaneDescription);

		this.definirTexto();
		this.llenarComboBoxTypeOfSeason();
	}

	private void definirTexto () {
		this.dateChooserStratDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		this.dateChooserEndDate.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	private void llenarComboBoxTypeOfSeason () {
		this.comboBoxTypeOfSeason.addItem("Alta");
		this.comboBoxTypeOfSeason.addItem("Baja");
		this.comboBoxTypeOfSeason.addItem("Media");
	}

	private boolean verificarCampos () {
		return (!this.textFieldName.getText().equalsIgnoreCase("") && this.dateChooserStratDate.getDate() != null && this.dateChooserEndDate.getDate() != null);
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

		this.panelGerenteCreacionContratoAlojamientoTemporada.actualizarTablaSeasons(); // se actualiza la información de la tabla de las tempordadas
	}

	private void cerrarFrame () {
		frameGerenteCreacionContratoAlojamiento.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}
}
