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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class PanelGerenteCreacionContratoAlojamientoTemporada extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableSeason;
	private FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento;
	private AccommodationContract accommodationContract;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;

	/**
	 * Create the panel.
	 */


	public PanelGerenteCreacionContratoAlojamientoTemporada(FrameGerenteCreacionContratoAlojamiento ca) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoAlojamiento = ca;
		this.accommodationContract = this.frameGerenteCreacionContratoAlojamiento.getAccommodationContract();
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		setBounds(0, 0, 700, 512);
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 84, 680, 359);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableSeason = new JTable();
		tableSeason.setModel(new ModeloTablaSeasons());
		scrollPane.setViewportView(tableSeason);

		JLabel lblSeason = new JLabel("SEASONS");
		lblSeason.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSeason.setBounds(28, 11, 116, 30);
		add(lblSeason);

		lblAnnadir = new JLabel("ADD");
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
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(535, 32, 155, 20);
		add(lblAnnadir);

		lblEliminar = new JLabel("DELETE");
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
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(535, 53, 155, 20);
		add(lblEliminar);

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
		lblAtras.setIcon(new ImageIcon(PanelGerenteCreacionContratoAlojamientoTemporada.class.getResource("/images/flecha.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(304, 454, 91, 38);
		add(lblAtras);

		this.actualizarTablaSeasons();
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

	public void actualizarTablaSeasons () {	
		this.actualizarTablaSeasons(accommodationContract.getSeasons()); // se obtienen las actividades del provedor de servicios
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
				accommodationContract.deleteSeasonLogic(((ModeloTablaSeasons) tableSeason.getModel()).deleteElement(rows[i] - i)); // se elimina solo la temporada de la logica del negocio
			else
				accommodationContract.deleteSeason(((ModeloTablaSeasons) tableSeason.getModel()).deleteElement(rows[i] - i)); // se elimina la temporada de la logica del negocio y de la base de datos
		}


		this.actualizarTablaSeasons();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaSeasons) table.getModel()).deleteElement(i);
		}
	}
}
