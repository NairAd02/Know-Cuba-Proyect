package JFrames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import logica.Controller;
import javax.swing.JTextField;

public class FrameReporteElegirParametros extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JLabel lblX;
	private JLabel lblConfirm;
	private int mouseX, mouseY;
	private JLabel lblNewLabel;
	private JLabel lblHotelChain;
	private JTextField textFieldHotelChain;
	private JLabel lblProvince;
	private JTextField textFieldProvince;

	public FrameReporteElegirParametros(JFrame f) {
		this.frame = f;
		setUndecorated(true);

		setSize(442, 245);
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
		setLocationRelativeTo(frame);
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(new Color(18, 95, 115));
		panel.setBounds(0, 0, 442, 245);
		contentPane.add(panel);

		lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
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
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(152, 199, 138, 35);
		panel.add(lblConfirm);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
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
		lblX.setBounds(404, 0, 38, 38);
		panel.add(lblX);
		
		lblNewLabel = new JLabel("Introduzca los dos Parametros");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 26));
		lblNewLabel.setBounds(30, 38, 382, 28);
		panel.add(lblNewLabel);
		
		lblHotelChain = new JLabel("Hotel Chain:");
		lblHotelChain.setForeground(SystemColor.textHighlightText);
		lblHotelChain.setFont(new Font("Dialog", Font.BOLD, 21));
		lblHotelChain.setBounds(70, 77, 124, 28);
		panel.add(lblHotelChain);
		
		textFieldHotelChain = new JTextField();
		textFieldHotelChain.setBounds(204, 85, 146, 20);
		panel.add(textFieldHotelChain);
		textFieldHotelChain.setColumns(10);
		
		lblProvince = new JLabel("Province:");
		lblProvince.setForeground(SystemColor.textHighlightText);
		lblProvince.setFont(new Font("Dialog", Font.BOLD, 21));
		lblProvince.setBounds(70, 127, 124, 28);
		panel.add(lblProvince);
		
		textFieldProvince = new JTextField();
		textFieldProvince.setColumns(10);
		textFieldProvince.setBounds(204, 135, 146, 20);
		panel.add(textFieldProvince);
		
	}
	
	private void cerrarFrame () {
		frame.setEnabled(true);
		dispose();
	}
}
