package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import logica.Controller;
import utils.Semaphore;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class FrameDecisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JLabel lblX;
	private JLabel lblYes;
	private JLabel lblNo;
	private JTextPane textPaneMensaje;
	private int mouseX, mouseY;

	public FrameDecisor(JFrame f,String mensaje) {
		this.frame = f;
		setUndecorated(true);

		setSize(422, 201);
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
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(new Color(18, 95, 115));
		panel.setBounds(0, 0, 422, 201);
		contentPane.add(panel);

		lblYes = new JLabel("YES");
		lblYes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancie().setConfirmacion(true); // se establece la confirmacion en true (se pueden ejecutar operaciones criticas)
				cerrarFrame(); // se cierra el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblYes.setOpaque(true);
		lblYes.setHorizontalAlignment(SwingConstants.CENTER);
		lblYes.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblYes.setBackground(SystemColor.info);
		lblYes.setBounds(53, 132, 138, 35);
		panel.add(lblYes);

		lblNo = new JLabel("NO");
		lblNo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancie().setConfirmacion(false); // se establece la confirmacion en false (no se puede ejecutar las operaciones criticas)
				cerrarFrame(); // se cierra el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblNo.setOpaque(true);
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNo.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNo.setBackground(SystemColor.info);
		lblNo.setBounds(236, 132, 138, 35);
		panel.add(lblNo);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancie().setConfirmacion(false); // se establece la confirmacion en false (no se puede ejecutar las operaciones criticas)
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
		lblX.setBounds(384, 0, 38, 38);
		panel.add(lblX);

		textPaneMensaje = new JTextPane();
		textPaneMensaje.setText(mensaje + "?");
		textPaneMensaje.setForeground(SystemColor.textHighlightText);
		textPaneMensaje.setFont(new Font("Dialog", Font.PLAIN, 21));
		textPaneMensaje.setEditable(false);
		textPaneMensaje.setBackground(new Color(18, 95, 115));
		textPaneMensaje.setBounds(68, 42, 324, 79);
		panel.add(textPaneMensaje);
	}

	private void cerrarFrame () {
		synchronized (Semaphore.samaphore) {
			Semaphore.samaphore.notifyAll(); // se notifica al hilo de la operacion que se despierte
		}
		frame.setEnabled(true);
		dispose();
	}
}
