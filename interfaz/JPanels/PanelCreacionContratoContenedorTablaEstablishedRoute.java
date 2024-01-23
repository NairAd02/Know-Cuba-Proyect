package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import logica.*;
import modelosTablas.ModeloTablaTransportModalityEstablishedRoute;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PanelCreacionContratoContenedorTablaEstablishedRoute extends JPanel implements PanelTransportModalityOperations {
	private static final long serialVersionUID = 1L;
	private JTable tableEstablishedRoute;
	private CarrierContract carrierContract;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private JSpinner spinnerCostGoingMin;
	private JSpinner spinnerCostGoingMax;
	private JSpinner spinnerCostLapMin;
	private JSpinner spinnerPriceMin;
	private JSpinner spinnerPriceMax;
	private JSpinner spinnerCostLapMax;

	
	public JTable getTable() { // Metodo para devolver la tabla definida en el panel
		return this.tableEstablishedRoute;
	}

	@Override
	public TransportModality getTransportModalitySelected() {
		return ((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRoute.getModel()).getElement(this.tableEstablishedRoute.getSelectedRow()); // se retorna la modalidad de transporte seleccionada
	}

	public PanelCreacionContratoContenedorTablaEstablishedRoute(PanelCreacionContratoTransporteTransportModality p) {
		setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clearSelectionTable();
				actualizarEstadoButtons();
			}
		});
		this.panelCreacionContratoTransporteTransportModality = p;
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		setBounds(0, 88, 853, 489);
		setLayout(null);
		setBackground(new Color(18, 95, 115));
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTable.setBounds(0, 78, 853, 411);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTable = new JScrollPane();
		panelTable.add(scrollPaneTable, BorderLayout.CENTER);

		tableEstablishedRoute = new JTable();
		tableEstablishedRoute.setRowHeight(30);
		tableEstablishedRoute.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableEstablishedRoute.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableEstablishedRoute.getTableHeader().setForeground(Color.black);
		tableEstablishedRoute.getTableHeader().setBackground(SystemColor.black);
		tableEstablishedRoute.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons();
			}
		});
		tableEstablishedRoute.setModel(new ModeloTablaTransportModalityEstablishedRoute());
		scrollPaneTable.setViewportView(tableEstablishedRoute);

		JLabel lblMealPlan = new JLabel("Cost Going:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(268, 0, 112, 27);
		add(lblMealPlan);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(278, 30, 30, 14);
		add(lblMin_2);

		spinnerCostGoingMin = new JSpinner();
		spinnerCostGoingMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		
		spinnerCostGoingMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostGoingMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostGoingMin.setBounds(313, 27, 53, 20);
		add(spinnerCostGoingMin);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(278, 51, 40, 14);
		add(lblMax_2);

		spinnerCostGoingMax = new JSpinner();
		spinnerCostGoingMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
	
		spinnerCostGoingMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostGoingMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostGoingMax.setBounds(313, 48, 53, 20);
		add(spinnerCostGoingMax);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(463, 30, 30, 14);
		add(lblMin);

		JLabel lblCantDaysAccommodation = new JLabel("Cost Lap:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(463, 0, 95, 27);
		add(lblCantDaysAccommodation);

		spinnerCostLapMin = new JSpinner();
		spinnerCostLapMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		
		spinnerCostLapMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostLapMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostLapMin.setBounds(498, 27, 53, 20);
		add(spinnerCostLapMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(463, 51, 40, 14);
		add(lblMax);

		spinnerCostLapMax = new JSpinner();
		spinnerCostLapMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
	
		spinnerCostLapMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostLapMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostLapMax.setBounds(498, 48, 53, 20);
		add(spinnerCostLapMax);

		JLabel lblMin_1 = new JLabel("Max:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(740, 51, 40, 14);
		add(lblMin_1);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(740, 3, 103, 20);
		add(lblPrice);

		spinnerPriceMax = new JSpinner();
		spinnerPriceMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		
		spinnerPriceMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMax.setBounds(780, 48, 53, 20);
		add(spinnerPriceMax);

		JLabel lblMax_1 = new JLabel("Min:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(740, 30, 40, 14);
		add(lblMax_1);

		spinnerPriceMin = new JSpinner();
		spinnerPriceMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
	
		spinnerPriceMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMin.setBounds(779, 27, 53, 20);
		add(spinnerPriceMin);

	}
	// Metodos para la actualizacion de la informacion de la tabla
	public void actualizarTablaModalitys() {
		this.actualizarTablaModalitys(this.carrierContract.getEstablishedRoute((Double)  this.spinnerCostGoingMin.getValue(), (Double) this.spinnerCostGoingMax.getValue(), 
				(Double) this.spinnerCostLapMin.getValue(), (Double) this.spinnerCostLapMax.getValue(), (Double) this.spinnerPriceMin.getValue(), (Double) this.spinnerPriceMax.getValue()));
		this.actualizarEstadoButtons();
	}



	private void actualizarTablaModalitys(ArrayList<Modality> modalitys) {
		reiniciarTable(this.tableEstablishedRoute);

		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityEstablishedRoute) tableEstablishedRoute.getModel()).addElement((EstablishedRoute) mod);
		}
	}

	public void deleteElementsTable() throws SQLException {
		int[] rows = tableEstablishedRoute.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.carrierContract.getId() == -1)
				this.carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) tableEstablishedRoute.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				this.carrierContract.deleteModality(((ModeloTablaTransportModalityEstablishedRoute) tableEstablishedRoute.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table) {

		for (int i = 0; table.getRowCount() != 0; i++) {
			i = 0;
			((ModeloTablaTransportModalityEstablishedRoute) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtons() {
		this.panelCreacionContratoTransporteTransportModality.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	@Override
	public void clearSelectionTable() {
		this.tableEstablishedRoute.clearSelection();
	}

	@Override
	public void restoreFilters() {
		this.spinnerCostGoingMax.setValue(0);
		this.spinnerCostGoingMin.setValue(0);
		this.spinnerPriceMax.setValue(0);
		this.spinnerPriceMin.setValue(0);
		this.spinnerCostLapMax.setValue(0);
		this.spinnerCostLapMin.setValue(0);
	}
	// Fin de Metodos para la actualizacion de la informacion de la tabla
}
