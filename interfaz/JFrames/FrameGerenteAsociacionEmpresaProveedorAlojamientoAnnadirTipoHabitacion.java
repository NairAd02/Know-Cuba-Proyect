package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion frame = new FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(384, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblSelectRoomType = new JLabel("SELECT ROOM TYPE");
		lblSelectRoomType.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblSelectRoomType.setBounds(27, 11, 230, 30);
		contentPane.add(lblSelectRoomType);
		
		JLabel lblAdd = new JLabel("ADD");
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(212, 74, 170, 22);
		contentPane.add(comboBox_1);
	}
}