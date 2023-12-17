package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import JFrames.FrameGerenteCreacionContratoAlojamiento;
import JFrames.FrameGerenteCreacionContratoAlojamientoAnnadir;
import logica.AccommodationContract;
import logica.AccommodationModality;
import logica.Modality;
import modelosTablas.ModeloTablaAccommodationModality;


public class PanelCreacionContratoAlojamientoAccommodationModality extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableAccommodationModalitys;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento;
	private AccommodationContract accommodationContract;




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

	public PanelCreacionContratoAlojamientoAccommodationModality(FrameGerenteCreacionContratoAlojamiento ca) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoAlojamiento = ca;
		this.accommodationContract = this.frameGerenteCreacionContratoAlojamiento.getAccommodationContract();
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		setBounds(0, 0, 700, 512);
		JPanel panelTable = new JPanel();
		panelTable.setBounds(28, 93, 640, 359);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableAccommodationModalitys = new JTable();
		tableAccommodationModalitys.setModel(new ModeloTablaAccommodationModality());
		scrollPane.setViewportView(tableAccommodationModalitys);

		JLabel lblTitulo = new JLabel("Accommodation Modalities");
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTitulo.setBounds(28, 11, 303, 30);
		add(lblTitulo);

		lblAnnadir = new JLabel("ADD");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoAlojamientoAnnadir frameAddAccommodationModality = new FrameGerenteCreacionContratoAlojamientoAnnadir(PanelCreacionContratoAlojamientoAccommodationModality.this);
				frameAddAccommodationModality.setVisible(true);
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
		lblAnnadir.setBounds(485, 32, 155, 20);
		add(lblAnnadir);

		lblEliminar = new JLabel("DELETE");
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
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(485, 53, 155, 20);
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
		lblAtras.setBounds(317, 463, 91, 38);
		add(lblAtras);

	}

	public void actualizarTablaModalitys () {	
		this.actualizarTablaModalitys(accommodationContract.getModalitys()); // se obtienen las actividades del provedor de servicios
	}


	private void actualizarTablaModalitys(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAccommodationModalitys);


		for (Modality mod : modalitys) {
			((ModeloTablaAccommodationModality) tableAccommodationModalitys.getModel()).addElement((AccommodationModality) mod);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableAccommodationModalitys.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (accommodationContract.getId() == -1)
				accommodationContract.deleteModalityLogic(((ModeloTablaAccommodationModality) tableAccommodationModalitys.getModel()).deleteElement(rows[i] - i)); // se elimina solo la modalidad de la logica del negocio
			else
				accommodationContract.deleteModality(((ModeloTablaAccommodationModality) tableAccommodationModalitys.getModel()).deleteElement(rows[i] - i)); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaAccommodationModality) table.getModel()).deleteElement(i);
		}
	}
}
