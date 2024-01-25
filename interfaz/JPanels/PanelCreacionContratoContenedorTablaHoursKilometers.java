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
import modelosTablas.ModeloTablaTransportModalityHoursKilometers;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PanelCreacionContratoContenedorTablaHoursKilometers extends JPanel implements PanelTransportModalityOperations {
	private static final long serialVersionUID = 1L;
	private JTable tableHoursKilometers;
	private CarrierContract carrierContract;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private JSpinner spinnerCostKilometersRoutMin;
	private JSpinner spinnerCostKilometersRoutMax;
	private JSpinner spinnerCostHoursMin;
	private JSpinner spinnerCostHoursMax;
	private JSpinner spinnerCostKilometersRoutAdditionalsMin;
	private JSpinner spinnerCostKilometersRoutAdditionalsMax;
	private JSpinner spinnerCostHoursAdditionalsMin;
	private JSpinner spinnerCostHoursAdditionalsMax;
	private JSpinner spinnerPriceMin;
	private JSpinner spinnerPriceMax;



	public JTable getTable() { // Metodo para devolver la tabla definida en el panel
		return this.tableHoursKilometers;
	}

	@Override
	public TransportModality getTransportModalitySelected() {
		return ((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometers.getModel()).getElement(this.tableHoursKilometers.getSelectedRow()); // se retorna la modalidad de transporte seleccionada
	}

	public PanelCreacionContratoContenedorTablaHoursKilometers(PanelCreacionContratoTransporteTransportModality p) {
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

		tableHoursKilometers = new JTable();
		tableHoursKilometers.setRowHeight(30);
		tableHoursKilometers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableHoursKilometers.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableHoursKilometers.getTableHeader().setForeground(Color.black);
		tableHoursKilometers.getTableHeader().setBackground(SystemColor.black);
		tableHoursKilometers.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons();
			}
		});
		tableHoursKilometers.setModel(new ModeloTablaTransportModalityHoursKilometers());
		scrollPaneTable.setViewportView(tableHoursKilometers);

		JLabel lblNewLabel = new JLabel("Cost Kilometers Rout:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(18, 11, 216, 20);
		add(lblNewLabel);

		JLabel lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3.setBounds(78, 38, 30, 14);
		add(lblMin_3);

		spinnerCostKilometersRoutMin = new JSpinner();
		spinnerCostKilometersRoutMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		spinnerCostKilometersRoutMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
	
		spinnerCostKilometersRoutMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutMin.setBounds(113, 36, 53, 20);
		add(spinnerCostKilometersRoutMin);

		JLabel lblMax_3 = new JLabel("Max:");
		lblMax_3.setForeground(SystemColor.textHighlightText);
		lblMax_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3.setBounds(78, 60, 40, 14);
		add(lblMax_3);

		spinnerCostKilometersRoutMax = new JSpinner();
		spinnerCostKilometersRoutMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		spinnerCostKilometersRoutMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		
		spinnerCostKilometersRoutMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutMax.setBounds(113, 59, 53, 20);
		add(spinnerCostKilometersRoutMax);

		JLabel lblMealPlan = new JLabel("Cost Hours:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(219, 8, 112, 27);
		add(lblMealPlan);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(229, 38, 30, 14);
		add(lblMin_2);

		spinnerCostHoursMin = new JSpinner();
		spinnerCostHoursMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		spinnerCostHoursMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
	
		spinnerCostHoursMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursMin.setBounds(264, 36, 53, 20);
		add(spinnerCostHoursMin);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(229, 59, 40, 14);
		add(lblMax_2);

		spinnerCostHoursMax = new JSpinner();
		spinnerCostHoursMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		spinnerCostHoursMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		
		spinnerCostHoursMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursMax.setBounds(264, 58, 53, 20);
		add(spinnerCostHoursMax);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(394, 38, 30, 14);
		add(lblMin);

		JLabel lblCantDaysAccommodation = new JLabel("Cost Kilometers Rout Addi:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(329, 6, 252, 30);
		add(lblCantDaysAccommodation);

		spinnerCostKilometersRoutAdditionalsMin = new JSpinner();
		spinnerCostKilometersRoutAdditionalsMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		spinnerCostKilometersRoutAdditionalsMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		
		spinnerCostKilometersRoutAdditionalsMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutAdditionalsMin.setBounds(429, 36, 53, 20);
		add(spinnerCostKilometersRoutAdditionalsMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(394, 59, 40, 14);
		add(lblMax);

		spinnerCostKilometersRoutAdditionalsMax = new JSpinner();
		spinnerCostKilometersRoutAdditionalsMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
		spinnerCostKilometersRoutAdditionalsMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
	
		spinnerCostKilometersRoutAdditionalsMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutAdditionalsMax.setBounds(429, 58, 53, 20);
		add(spinnerCostKilometersRoutAdditionalsMax);

		JLabel lblMin_1 = new JLabel("Max:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(740, 62, 40, 14);
		add(lblMin_1);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(740, 11, 103, 20);
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
		spinnerPriceMax.setBounds(780, 58, 53, 20);
		add(spinnerPriceMax);

		JLabel lblMax_1 = new JLabel("Min:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(740, 36, 40, 14);
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
		spinnerPriceMin.setBounds(779, 36, 53, 20);
		add(spinnerPriceMin);

		JLabel lblCostHoursAddi = new JLabel("Cost Hours Addi:");
		lblCostHoursAddi.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostHoursAddi.setForeground(SystemColor.textHighlightText);
		lblCostHoursAddi.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCostHoursAddi.setBounds(573, 11, 157, 20);
		add(lblCostHoursAddi);

		JLabel lblMax_1_1 = new JLabel("Min:");
		lblMax_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1.setBounds(597, 36, 40, 14);
		add(lblMax_1_1);

		JLabel lblMin_1_1 = new JLabel("Max:");
		lblMin_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1.setBounds(597, 62, 40, 14);
		add(lblMin_1_1);

		spinnerCostHoursAdditionalsMin = new JSpinner();
		spinnerCostHoursAdditionalsMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
	
		spinnerCostHoursAdditionalsMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursAdditionalsMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursAdditionalsMin.setBounds(636, 36, 53, 20);
		add(spinnerCostHoursAdditionalsMin);

		spinnerCostHoursAdditionalsMax = new JSpinner();
		spinnerCostHoursAdditionalsMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!panelCreacionContratoTransporteTransportModality.isRestoreFilters())
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
		});
	
		spinnerCostHoursAdditionalsMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursAdditionalsMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursAdditionalsMax.setBounds(637, 58, 53, 20);
		add(spinnerCostHoursAdditionalsMax);

	}
	// Metodos para la actualizacion de la informacion de la tabla
	public void actualizarTablaModalitys() {
		this.actualizarTablaModalitys(this.carrierContract.getHoursKilometers((Double) this.spinnerCostKilometersRoutMin.getValue(), (Double) this.spinnerCostKilometersRoutMax.getValue(), 
				(Double) this.spinnerCostHoursMin.getValue(), (Double) this.spinnerCostHoursMax.getValue(), (Double) this.spinnerCostKilometersRoutAdditionalsMin.getValue(), 
				(Double) this.spinnerCostKilometersRoutAdditionalsMax.getValue(), (Double) this.spinnerCostHoursAdditionalsMin.getValue(), (Double) this.spinnerCostHoursAdditionalsMax.getValue(), 
				(Double) this.spinnerPriceMin.getValue(), (Double) this.spinnerPriceMax.getValue()));
		this.actualizarEstadoButtons();
	}



	private void actualizarTablaModalitys(ArrayList<Modality> modalitys) {
		reiniciarTable(this.tableHoursKilometers);

		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityHoursKilometers) tableHoursKilometers.getModel()).addElement((HoursKilometers) mod);
		}
	}

	public void deleteElementsTable() throws SQLException {
		int[] rows = tableHoursKilometers.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.carrierContract.getId() == -1)
				this.carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) tableHoursKilometers.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				this.carrierContract.deleteModality(((ModeloTablaTransportModalityHoursKilometers) tableHoursKilometers.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table) {

		for (int i = 0; table.getRowCount() != 0; i++) {
			i = 0;
			((ModeloTablaTransportModalityHoursKilometers) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtons() {
		this.panelCreacionContratoTransporteTransportModality.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	@Override
	public void clearSelectionTable() {
		this.tableHoursKilometers.clearSelection();
	}

	@Override
	public void restoreFilters() {
		this.spinnerPriceMax.setValue(0.0);
		this.spinnerPriceMin.setValue(0.0);
		this.spinnerCostHoursAdditionalsMax.setValue(0.0);
		this.spinnerCostHoursAdditionalsMin.setValue(0.0);
		this.spinnerCostHoursMax.setValue(0.0);
		this.spinnerCostHoursMin.setValue(0.0);
		this.spinnerCostKilometersRoutMax.setValue(0.0);
		this.spinnerCostKilometersRoutMin.setValue(0.0);
		this.spinnerCostKilometersRoutAdditionalsMax.setValue(0.0);
		this.spinnerCostKilometersRoutAdditionalsMin.setValue(0.0);
	}
	// Fin de Metodos para la actualizacion de la informacion de la tabla
}
