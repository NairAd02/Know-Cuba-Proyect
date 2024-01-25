package JFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import dao.HotelModalityDAO;
import logica.Hotel;
import logica.HotelModality;
import modelosTablas.ModeloTablaHotelModality;
import java.awt.Cursor;


public class FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirHotelModality extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox <HotelModality> comboBoxHotelModality;
	private FrameGerenteAsociacionEmpresaProveedorAlojamiento frameGerenteAsociacionEmpresaProveedorAlojamiento;
	private Hotel hotel;
	private JLabel lblX;
	private int mouseX, mouseY;


	public FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirHotelModality(FrameGerenteAsociacionEmpresaProveedorAlojamiento pa) {
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

		JLabel lblSelectMealPlan = new JLabel("HOTEL MODALITY SELECTION");
		lblSelectMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSelectMealPlan.setBounds(10, 8, 346, 30);
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
		lblX.setBackground(SystemColor.textHighlightText);
		lblX.setBounds(384, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblTypesOfMeal = new JLabel("Hotel Modality :");
		lblTypesOfMeal.setForeground(SystemColor.info);
		lblTypesOfMeal.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTypesOfMeal.setBounds(27, 73, 162, 23);
		contentPane.add(lblTypesOfMeal);

		comboBoxHotelModality = new JComboBox <HotelModality>();
		try {
			llenarComboBoxHotelModality();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		comboBoxHotelModality.setBounds(193, 73, 148, 22);
		contentPane.add(comboBoxHotelModality);

		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					
					try {
						addHotelModality(); // se añade la modalidad de hotel seleccionada
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

	private void llenarComboBoxHotelModality () throws SQLException {
		ArrayList<HotelModality> hotelsModalitys = (ArrayList<HotelModality>) HotelModalityDAO.getInstancie().selectAll(); // se obtienen todos los planes alimenticios de la base de datos

		for (HotelModality hotelModalityeal : hotelsModalitys) {
			if (!((ModeloTablaHotelModality) frameGerenteAsociacionEmpresaProveedorAlojamiento.getTableHotelModality().getModel()).isFindElement(hotelModalityeal)) // se no se encuentra añadido ya a la tabla
				comboBoxHotelModality.addItem(hotelModalityeal);
		}
		if (comboBoxHotelModality.getItemCount() == 0)
			comboBoxHotelModality.addItem(new HotelModality("No Disponible"));
	}

	private boolean verificarCampos () {
		return !(((HotelModality) this.comboBoxHotelModality.getSelectedItem()).getName().equalsIgnoreCase("No Disponible"));
	}

	private void addHotelModality () throws SQLException {
		if (this.hotel.getId() != -1) { // si su id es diferente de 1 se trata de un objeto hotel real
			this.hotel.addHotetModality((HotelModality) comboBoxHotelModality.getSelectedItem()); 
		}
		else { // si es igual a 1 se trata de un objeto temporal
			this.hotel.addHotelModalityLogic((HotelModality) comboBoxHotelModality.getSelectedItem()); // solo se inserta en la logica del negocio
		}
		frameGerenteAsociacionEmpresaProveedorAlojamiento.actualizarTablaHotelModality(); // se actualiza la informacion de la tabla de las modalidades de hotel
	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorAlojamiento.setEnabled(true);
		dispose();
	}

}
