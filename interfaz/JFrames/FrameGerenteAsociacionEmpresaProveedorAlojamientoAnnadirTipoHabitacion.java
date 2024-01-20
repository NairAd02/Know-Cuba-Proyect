package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import dao.TypeOfRoomDAO;
import logica.Hotel;
import logica.HotelModality;
import logica.MealPlan;
import logica.TypeOfRoom;
import modelosTablas.ModeloTablaTypeOfRoom;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox <TypeOfRoom> comboBoxTypeOfRoom;
	private FrameGerenteAsociacionEmpresaProveedorAlojamiento frameGerenteAsociacionEmpresaProveedorAlojamiento;
	private Hotel hotel;
	private JLabel lblX;
	private int mouseX, mouseY;
	private JLabel lblAdd;


	public FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion(FrameGerenteAsociacionEmpresaProveedorAlojamiento pa) {
		this.frameGerenteAsociacionEmpresaProveedorAlojamiento = pa;
		this.hotel = frameGerenteAsociacionEmpresaProveedorAlojamiento.getHotel();
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

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
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

		JLabel lblSelectRoomType = new JLabel("SELECT ROOM TYPE");
		lblSelectRoomType.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSelectRoomType.setBounds(27, 11, 230, 30);
		contentPane.add(lblSelectRoomType);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					try {
						addTypeOfRoom();
						cerrarFrame(); // se cierra el frame actual
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAdd.setBackground(SystemColor.textHighlightText);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAdd.setBackground(SystemColor.info);
			}
		});
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(93, 141, 235, 35);
		contentPane.add(lblAdd);

		JLabel lblRoomTypes = new JLabel("ROOM TYPES :");
		lblRoomTypes.setForeground(SystemColor.info);
		lblRoomTypes.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomTypes.setBounds(44, 74, 138, 23);
		contentPane.add(lblRoomTypes);

		comboBoxTypeOfRoom = new JComboBox <TypeOfRoom>();
		try {
			this.llenarComboBoxTypeOfRoom();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBoxTypeOfRoom.setBounds(212, 74, 170, 22);
		contentPane.add(comboBoxTypeOfRoom);
	}

	private void llenarComboBoxTypeOfRoom () throws SQLException {
		ArrayList<TypeOfRoom> typesOfRoom = (ArrayList<TypeOfRoom>) TypeOfRoomDAO.getInstancie().selectAll(); // se obtienen todos los tipos de habitacion de la base de datos

		for (TypeOfRoom type : typesOfRoom) {
			if (!((ModeloTablaTypeOfRoom) frameGerenteAsociacionEmpresaProveedorAlojamiento.getTableRoomTypes().getModel()).isFindElement(type)) // se no se encuentra añadido ya a la tabla
				comboBoxTypeOfRoom.addItem(type);
		}

		if (comboBoxTypeOfRoom.getItemCount() == 0)
			comboBoxTypeOfRoom.addItem(new TypeOfRoom("No Disponible"));
	}

	private boolean verificarCampos () {
		return !(((TypeOfRoom) this.comboBoxTypeOfRoom.getSelectedItem()).getName().equalsIgnoreCase("No Disponible"));
	}

	private void addTypeOfRoom () throws SQLException {
		if (this.hotel.getId() != -1) { // si su id es diferente de 1 se trata de un objeto hotel real
			this.hotel.addTypeOfRoom((TypeOfRoom) comboBoxTypeOfRoom.getSelectedItem());
		}
		else { // si es igual a -1 se trata de un objeto temporal
			this.hotel.addTypeOfRoomLogic((TypeOfRoom) comboBoxTypeOfRoom.getSelectedItem()); // se añade de forma temporal
		}
		frameGerenteAsociacionEmpresaProveedorAlojamiento.actualizarTablaTypesOfRooms(); // se actualiza la informacion de la tabla de los tipos de habitacion
	}

	private void cerrarFrame () {
		frameGerenteAsociacionEmpresaProveedorAlojamiento.setEnabled(true);
		dispose();
	}
}
