package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logica.Controller;
import utils.Semaphore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameDecisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JLabel lblX;
	private JLabel lblYes;
	private JLabel lblNo;

	public FrameDecisor(JFrame f,String mensaje) {
		this.frame = f;
		setUndecorated(true);
		
		setSize(422, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(frame);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(new Color(18, 95, 115));
		panel.setBounds(0, 0, 422, 201);
		contentPane.add(panel);

		lblYes = new JLabel("YES");
		lblYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		lblYes.setBounds(55, 120, 138, 35);
		panel.add(lblYes);

		lblNo = new JLabel("NO");
		lblNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		lblNo.setBounds(233, 120, 138, 35);
		panel.add(lblNo);

		JLabel lblMensaje = new JLabel(mensaje);
		lblMensaje.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblMensaje.setBounds(32, 54, 365, 30);
		panel.add(lblMensaje);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Controller.getInstancie().setConfirmacion(false); // se establece la confirmacion en false (no se puede ejecutar las operaciones criticas)
				cerrarFrame(); // se cierra el frame actual
			}	
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.black);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(384, 0, 38, 38);
		panel.add(lblX);
	}

	private void cerrarFrame () {
		synchronized (Semaphore.samaphore) {
			Semaphore.samaphore.notifyAll(); // se notifica al hilo principal que se despierte
		}
		frame.setEnabled(true);
		dispose();
	}


}
