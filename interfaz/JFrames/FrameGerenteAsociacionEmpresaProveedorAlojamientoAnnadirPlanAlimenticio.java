package JFrames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import dao.MealPlanDAO;
import logica.Hotel;
import logica.HotelModality;
import logica.MealPlan;
import modelosTablas.ModeloTablaMealPlan;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.border.LineBorder;

public class FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirPlanAlimenticio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox <MealPlan> comboBoxMealPlans;
	private FrameGerenteAsociacionEmpresaProveedorAlojamiento frameGerenteAsociacionEmpresaProveedorAlojamiento;
	private Hotel hotel;
	private JLabel lblX;
	private int mouseX, mouseY;


	public FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirPlanAlimenticio(FrameGerenteAsociacionEmpresaProveedorAlojamiento pa) {
		this.frameGerenteAsociacionEmpresaProveedorAlojamiento = pa;
		this.hotel = this.frameGerenteAsociacionEmpresaProveedorAlojamiento.getHotel();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 201);
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

		JLabel lblSelectMealPlan = new JLabel("SELECT MEAL PLAN TYPES");
		lblSelectMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSelectMealPlan.setBounds(27, 11, 296, 30);
		contentPane.add(lblSelectMealPlan);

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
		lblX.setBounds(384, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblTypesOfMeal = new JLabel("TYPES OF MEAL PLAN :");
		lblTypesOfMeal.setForeground(SystemColor.info);
		lblTypesOfMeal.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTypesOfMeal.setBounds(27, 73, 213, 23);
		contentPane.add(lblTypesOfMeal);

		comboBoxMealPlans = new JComboBox <MealPlan>();
		try {
			llenarComboBoxMealPlans();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		comboBoxMealPlans.setBounds(250, 73, 148, 22);
		contentPane.add(comboBoxMealPlans);

		JLabel lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					try {
						addMealPlan();
						cerrarFrame(); // se cierra el frame actual
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		lblAdd.setBounds(93, 141, 235, 35);
		contentPane.add(lblAdd);
	}

	private void llenarComboBoxMealPlans () throws SQLException {
		ArrayList<MealPlan> mealPlans = (ArrayList<MealPlan>) MealPlanDAO.getInstancie().selectAll(); // se obtienen todos los planes alimenticios de la base de datos

		for (MealPlan meal : mealPlans) {
			if (!((ModeloTablaMealPlan) frameGerenteAsociacionEmpresaProveedorAlojamiento.getTableMealPlan().getModel()).isFindElement(meal)) // se no se encuentra añadido ya a la tabla
				comboBoxMealPlans.addItem(meal);
		}

		if (comboBoxMealPlans.getItemCount() == 0)
			comboBoxMealPlans.addItem(new MealPlan("No Disponible"));
	}

	private boolean verificarCampos () {
		return !(((MealPlan) this.comboBoxMealPlans.getSelectedItem()).getName().equalsIgnoreCase("No Disponible"));
	}

	private void addMealPlan () throws SQLException {
		if (this.hotel.getId() != -1) { // si su id es diferente de 1 se trata de un objeto hotel real
			this.hotel.addMealPlan((MealPlan) comboBoxMealPlans.getSelectedItem());
		}
		else { // si es igual a 1 se trata de un objeto temporal
			this.hotel.addMealPlanLogic((MealPlan) comboBoxMealPlans.getSelectedItem()); // solo se inserta en la logica del negocio
		}
		frameGerenteAsociacionEmpresaProveedorAlojamiento.actualizarTablaMealPlans(); // se actualiza la informacion de la tabla de los planes alimenticios
	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorAlojamiento.setEnabled(true);
		dispose();
	}

}
