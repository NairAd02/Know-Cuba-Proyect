package JPanels;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import JFrames.FrameGerenteCreacionContratoServicioAnnadir;
import JFrames.FrameGerenteCreacionContratoServivio;
import logica.Modality;
import logica.ServiceContract;
import logica.ServiceModality;
import modelosTablas.ModeloTablaServiceModality;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class PanelCreacionContratoServicioServiceModality extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableServiceModalitys;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private FrameGerenteCreacionContratoServivio frameGerenteCreacionContratoServivio;
	private ServiceContract serviceContract;



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
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoServivio = cs;
		this.serviceContract = this.frameGerenteCreacionContratoServivio.getServiceContract();
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		setBounds(0, 0, 700, 512);
		JPanel panelTable = new JPanel();
		panelTable.setBounds(28, 93, 640, 359);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableServiceModalitys = new JTable();
		tableServiceModalitys.setModel(new ModeloTablaServiceModality());
		scrollPane.setViewportView(tableServiceModalitys);

		JLabel lblSeason = new JLabel("Service Modalities");
		lblSeason.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSeason.setBounds(28, 11, 201, 30);
		add(lblSeason);

		lblAnnadir = new JLabel("ADD");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoServicioAnnadir frameServiceModalityAdd = new FrameGerenteCreacionContratoServicioAnnadir(PanelCreacionContratoServicioServiceModality.this);
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
				frameGerenteCreacionContratoServivio.cambioDePanel(frameGerenteCreacionContratoServivio.getPanelServiceContract());
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
		this.actualizarTablaModalitys(serviceContract.getModalitys()); // se obtienen las actividades del provedor de servicios
	}


	private void actualizarTablaModalitys(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableServiceModalitys);


		for (Modality mod : modalitys) {
			((ModeloTablaServiceModality) tableServiceModalitys.getModel()).addElement((ServiceModality) mod);
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableServiceModalitys.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (serviceContract.getId() == -1)
				serviceContract.deleteModalityLogic(((ModeloTablaServiceModality) tableServiceModalitys.getModel()).deleteElement(rows[i] - i)); // se elimina solo la modalidad de la logica del negocio
			else
				serviceContract.deleteModality(((ModeloTablaServiceModality) tableServiceModalitys.getModel()).deleteElement(rows[i] - i)); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaServiceModality) table.getModel()).deleteElement(i);
		}
	}

}
