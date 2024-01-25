package JPanels;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import logica.*;
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;



public class PanelCreacionContratoContenedorTablaCostKilometers extends JPanel implements PanelTransportModalityOperations {

	private static final long serialVersionUID = 1L;
	private JTable tableCostKilometers;
	private CarrierContract carrierContract;
	private JSpinner spinnerCostKilometersGoingMax;
	private JSpinner spinnerCostKilometersGoingMin;
	private JSpinner spinnerCostKilometersLapMin;
	private JSpinner spinnerCostKilometersLapMax;
	private JSpinner spinnerCostHoursWaitMin;
	private JSpinner spinnerCostHoursWaitMax;
	private JSpinner spinnerPriceMin;
	private JSpinner spinnerPriceMax;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;


	public JTable getTable() { // Metodo para devolver la tabla definida en el panel
		return this.tableCostKilometers;
	}

	@Override
	public TransportModality getTransportModalitySelected() {
		return ((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometers.getModel()).getElement(this.tableCostKilometers.getSelectedRow()); // se retorna la modalidad de transporte seleccionada
	}

	@Override
	public void clearSelectionTable() {
		this.tableCostKilometers.clearSelection();
	}

	@Override
	public void restoreFilters() {
		this.spinnerCostHoursWaitMax.setValue(0.0);
		this.spinnerCostHoursWaitMin.setValue(0.0);
		this.spinnerPriceMax.setValue(0.0);
		this.spinnerPriceMin.setValue(0.0);
		this.spinnerCostKilometersGoingMax.setValue(0.0);
		this.spinnerCostKilometersGoingMin.setValue(0.0);
		this.spinnerCostKilometersLapMax.setValue(0.0);
		this.spinnerCostKilometersLapMin.setValue(0.0);
	}

	public PanelCreacionContratoContenedorTablaCostKilometers(PanelCreacionContratoTransporteTransportModality p) {
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
		
		tableCostKilometers = new JTable();
		tableCostKilometers.setRowHeight(30);
		tableCostKilometers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableCostKilometers.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableCostKilometers.getTableHeader().setForeground(Color.black);
		tableCostKilometers.getTableHeader().setBackground(SystemColor.black);
		tableCostKilometers.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons();
			}
		});
		tableCostKilometers.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPaneTable.setViewportView(tableCostKilometers);
		
		JLabel lblNewLabel = new JLabel("Cost Kilometers Going:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 13, 216, 27);
		add(lblNewLabel);
		
		JLabel lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3.setBounds(20, 48, 30, 14);
		add(lblMin_3);
		
		spinnerCostKilometersGoingMin = new JSpinner();
		spinnerCostKilometersGoingMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersGoingMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerCostKilometersGoingMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersGoingMin.setBounds(55, 46, 53, 20);
		add(spinnerCostKilometersGoingMin);
		
		JLabel lblMax_3 = new JLabel("Max:");
		lblMax_3.setForeground(SystemColor.textHighlightText);
		lblMax_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3.setBounds(118, 48, 40, 14);
		add(lblMax_3);
		
		spinnerCostKilometersGoingMax = new JSpinner();
		spinnerCostKilometersGoingMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersGoingMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerCostKilometersGoingMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersGoingMax.setBounds(153, 47, 53, 20);
		add(spinnerCostKilometersGoingMax);
		
		JLabel lblMealPlan = new JLabel("Cost Kilometers Lap:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(244, 13, 200, 27);
		add(lblMealPlan);
		
		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(254, 48, 30, 14);
		add(lblMin_2);
		
		spinnerCostKilometersLapMin = new JSpinner();
		spinnerCostKilometersLapMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersLapMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerCostKilometersLapMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersLapMin.setBounds(289, 46, 53, 20);
		add(spinnerCostKilometersLapMin);
		
		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(352, 48, 40, 14);
		add(lblMax_2);
		
		spinnerCostKilometersLapMax = new JSpinner();
		spinnerCostKilometersLapMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersLapMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerCostKilometersLapMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersLapMax.setBounds(387, 47, 53, 20);
		add(spinnerCostKilometersLapMax);
		
		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(452, 50, 30, 14);
		add(lblMin);
		
		JLabel lblCantDaysAccommodation = new JLabel("Cost Hours wait:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(487, 11, 150, 30);
		add(lblCantDaysAccommodation);
		
		spinnerCostHoursWaitMin = new JSpinner();
		spinnerCostHoursWaitMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursWaitMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerCostHoursWaitMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursWaitMin.setBounds(487, 48, 53, 20);
		add(spinnerCostHoursWaitMin);
		
		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(550, 50, 40, 14);
		add(lblMax);
		
		spinnerCostHoursWaitMax = new JSpinner();
		spinnerCostHoursWaitMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursWaitMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerCostHoursWaitMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursWaitMax.setBounds(585, 49, 53, 20);
		add(spinnerCostHoursWaitMax);
		
		JLabel lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(648, 50, 30, 14);
		add(lblMin_1);
		
		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(699, 11, 103, 30);
		add(lblPrice);
		
		spinnerPriceMin = new JSpinner();
		spinnerPriceMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerPriceMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMin.setBounds(688, 46, 53, 20);
		add(spinnerPriceMin);
		
		JLabel lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(751, 46, 40, 14);
		add(lblMax_1);
		
		spinnerPriceMax = new JSpinner();
		spinnerPriceMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys();
			}
		});
		spinnerPriceMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMax.setBounds(790, 46, 53, 20);
		add(spinnerPriceMax);
		
	}
	// Metodos para la actualizacion de la informacion de la tabla
	public void actualizarTablaModalitys() {
		this.actualizarTablaModalitys(this.carrierContract.getCostKilometers((Double) this.spinnerCostKilometersGoingMin.getValue(), (Double) this.spinnerCostKilometersGoingMax.getValue(), 
				(Double) this.spinnerCostKilometersLapMin.getValue(), (Double) this.spinnerCostKilometersLapMax.getValue(), (Double) this.spinnerCostHoursWaitMin.getValue(), 
				(Double) this.spinnerCostHoursWaitMax.getValue(), (Double) this.spinnerPriceMin.getValue(), (Double) this.spinnerPriceMax.getValue()));
		this.actualizarEstadoButtons();
	}


	private void actualizarTablaModalitys(ArrayList<Modality> modalitys) {
		reiniciarTable(this.tableCostKilometers);

		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityCostKilometers) tableCostKilometers.getModel()).addElement((CostKilometers) mod);
		}
	}

	public void deleteElementsTable() throws SQLException {
		int[] rows = tableCostKilometers.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.carrierContract.getId() == -1)
				this.carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) tableCostKilometers.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				this.carrierContract.deleteModality(((ModeloTablaTransportModalityCostKilometers) tableCostKilometers.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table) {

		for (int i = 0; table.getRowCount() != 0; i++) {
			i = 0;
			((ModeloTablaTransportModalityCostKilometers) table.getModel()).deleteElement(i);
		}
	}
	
	private void actualizarEstadoButtons() {
		this.panelCreacionContratoTransporteTransportModality.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}
	// Fin de Metodos para la actualizacion de la informacion de la tabla
}
