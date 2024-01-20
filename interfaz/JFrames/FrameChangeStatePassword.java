package JFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDAO;
import logica.Controller;
import logica.User;
import utils.ConnectionDataBase;
import utils.Semaphore;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class FrameChangeStatePassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPassword;
	private JLabel lblCancelar;
	private JLabel lblConfirm;
	private FrameLogin frameLogin;
	private int mouseX, mouseY;




	public FrameChangeStatePassword(FrameLogin f) {
		this.frameLogin = f;
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 418);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblIntroductaNombreDe = new JLabel("Introduzca su nueva Contraseña:");
		lblIntroductaNombreDe.setForeground(SystemColor.textText);
		lblIntroductaNombreDe.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblIntroductaNombreDe.setBounds(95, 228, 255, 21);
		contentPane.add(lblIntroductaNombreDe);

		textFieldPassword = new JTextField();
		textFieldPassword.setOpaque(false);
		textFieldPassword.setForeground(Color.BLACK);
		textFieldPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldPassword.setBounds(10, 260, 420, 20);
		contentPane.add(textFieldPassword);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (verificarCampos()) {
					actualizarEstadoPassword();
					try {
						frameLogin.iniciarAlPrograma();
						dispose();
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
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(10, 372, 142, 35);
		contentPane.add(lblConfirm);

		lblCancelar = new JLabel("CANCEL");
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarFrame();
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

		JTextPane txtpnLeDamosLa = new JTextPane();
		txtpnLeDamosLa.setText("Le damos la bienvenida al sistema.\r\nEs un placer para nosotros gestionar sus datos\r\n");
		txtpnLeDamosLa.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtpnLeDamosLa.setBounds(10, 31, 420, 97);
		contentPane.add(txtpnLeDamosLa);

		JTextPane txtpnPorFavorPara = new JTextPane();
		txtpnPorFavorPara.setText("Por favor para logearse al sistema ha de introducir una contraseña\r\n");
		txtpnPorFavorPara.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtpnPorFavorPara.setBounds(10, 139, 420, 69);
		contentPane.add(txtpnPorFavorPara);
	}

	private boolean verificarCampos () {
		return !this.textFieldPassword.getText().equalsIgnoreCase("");
	}

	private void actualizarEstadoPassword () {
		try {
			Controller.getInstancie().getUser().actualizarEstadoPassword(this.textFieldPassword.getText());
			ConnectionDataBase.commit(); // se confirman las operaciones realizadas a la base de datos
		} catch (SQLException e) {
			try {
				ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas a la base de datos
			} catch (SQLException e1) {

				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
	}


	private void cerrarFrame () {
		this.frameLogin.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); 
	}
}
