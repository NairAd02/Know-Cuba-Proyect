package JFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDAO;
import logica.Controller;
import logica.User;
import utils.Semaphore;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class FrameNotificarEstadoContrasenna extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblCancelar;
	private JLabel lblNotify;


	
	private void crearFrameNotificacion () {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia("Han sido elimanado correctamente los proveedores de alojamiento");
		frameAdvertencia.setVisible(true);
	}
	

	public FrameNotificarEstadoContrasenna() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroductaNombreDe = new JLabel("Introduzca Nombre de Usuario:");
		lblIntroductaNombreDe.setForeground(SystemColor.textText);
		lblIntroductaNombreDe.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblIntroductaNombreDe.setBounds(90, 60, 237, 21);
		contentPane.add(lblIntroductaNombreDe);
		
		JLabel lblIntroducirUsername = new JLabel("________________________________________________");
		lblIntroducirUsername.setBounds(56, 102, 325, 21);
		contentPane.add(lblIntroducirUsername);
		
		textField = new JTextField();
		textField.setOpaque(false);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(56, 92, 288, 20);
		contentPane.add(textField);
		
		lblNotify = new JLabel("NOTIFY");
		lblNotify.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		lblNotify.setOpaque(true);
		lblNotify.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotify.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNotify.setBackground(SystemColor.info);
		lblNotify.setBounds(10, 372, 142, 35);
		contentPane.add(lblNotify);
		
		lblCancelar = new JLabel("CANCEL");
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		lblCancelar.setOpaque(true);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblCancelar.setBackground(SystemColor.info);
		lblCancelar.setBounds(288, 372, 142, 35);
		contentPane.add(lblCancelar);
	}
	
private void notifyStatePassword () throws SQLException { // metodo para notificar el estado de olvidado de la constraseña
		User user = UserDAO.getInstancie().select(textField.getText());
	}
}
